package flowcontrol.transport.repository.impl;

import flowcontrol.transport.exception.AppException;
import flowcontrol.transport.exception.ResourceNotFoundException;
import flowcontrol.transport.model.general.BearerTokenWrapper;
import flowcontrol.transport.model.general.Cell;
import flowcontrol.transport.model.general.Farmer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Repository
public class FarmerRepository {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private BearerTokenWrapper tokenWrapper;

    public Farmer findById(Long id) {
        Farmer farmer = webClientBuilder
                .filter(authHeader(tokenWrapper.getToken()))
                .build() //Gives you a client
                .get() // Method for the request
                .uri("http://127.0.0.1:7071/farmer/api/v1/farmers/" + id) // Url that you need to access
                .retrieve() // Go do the fetch
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new ResourceNotFoundException("Farmer", "Id", id)))
                .onStatus(HttpStatus::is5xxServerError,
                        error -> Mono.error(new AppException("Server is not responding")))
                .bodyToMono(Farmer.class) // Whatever body go get back map it to the class - Mono means you will get a
                // object back but not right away "async" //empty page but you will need to wait but you know it will come and than you can do stuff "promise"
                .block();  // converts async to sync

        return Optional.of(farmer).orElseThrow(() -> new ResourceNotFoundException("Farmer", "Id", id));
    }

    public List<Farmer> findAll() {
        List<Farmer> farmers = webClientBuilder.build()
                .get()
                .uri("http://localhost:7071/api/v1/farmers/")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Farmer>>() {})
                .block();

        return farmers;
    }

    private ExchangeFilterFunction authHeader(String token) {
        return (request, next) -> next.exchange(ClientRequest.from(request).headers((headers) -> {
            headers.setBearerAuth(token);
        }).build());
    }
}
