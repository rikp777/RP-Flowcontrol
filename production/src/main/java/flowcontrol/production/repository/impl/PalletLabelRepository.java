package flowcontrol.production.repository.impl;

import flowcontrol.production.exception.AppException;
import flowcontrol.production.exception.ResourceNotFoundException;
import flowcontrol.production.model.general.BearerTokenWrapper;
import flowcontrol.production.model.general.PalletLabel;
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
public class PalletLabelRepository {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private BearerTokenWrapper tokenWrapper;

    public Optional<PalletLabel> findById(Long farmerId, Long id) {
        PalletLabel palletLabel = webClientBuilder
                .filter(authHeader(tokenWrapper.getToken()))
                .build() //Gives you a client
                .get() // Method for the request
                .uri("http://localhost:7072/api/v1/farmers/" + farmerId + "/palletlabels/" + id)
                .retrieve() // Go do the fetch
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new ResourceNotFoundException("PalletLabel", "Id", id)))
                .onStatus(HttpStatus::is5xxServerError,
                        error -> Mono.error(new AppException("Server is not responding")))
                .bodyToMono(PalletLabel.class) // Whatever body go get back map it to the class - Mono means you will get a object back but not right away "async" //empty page but you will need to wait but you know it will come and than you can do stuff "promise"
                .block();  // converts async to sync

        return Optional.of(palletLabel);
    }

    public List<PalletLabel> findAll(Long farmerId) {
        List<PalletLabel> palletLabel = webClientBuilder
                .filter(authHeader(tokenWrapper.getToken()))
                .build()
                .get()
                .uri("http://localhost:7072/api/v1/farmers/"+ farmerId + "/palletlabels")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<PalletLabel>>() {})
                .block();

        return palletLabel;
    }

    private ExchangeFilterFunction authHeader(String token) {
        return (request, next) -> next.exchange(ClientRequest.from(request).headers((headers) -> {
            headers.setBearerAuth(token);
        }).build());
    }
}
