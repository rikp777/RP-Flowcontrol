package flowcontrol.transport.repository.impl;

import flowcontrol.transport.exception.AppException;
import flowcontrol.transport.exception.ResourceNotFoundException;
import flowcontrol.transport.model.general.Article;
import flowcontrol.transport.model.general.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Repository
public class CellRepository {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public Cell findById(Long farmerId, Long cellId) {
        Cell cell = webClientBuilder.build() //Gives you a client
                .get() // Method for the request
                .uri("http://localhost:7071/api/v1/farmers/"+ farmerId + "/cells/" + cellId) // Url that you need to
                .retrieve() // Go do the fetch
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new ResourceNotFoundException("Cell", "Id", cellId)))
                .onStatus(HttpStatus::is5xxServerError,
                        error -> Mono.error(new AppException("Server is not responding")))
                .bodyToMono(Cell.class) // Whatever body go get back map it to the class - Mono means you will get a
                // object back but not right away "async" //empty page but you will need to wait but you know it will come and than you can do stuff "promise"
                .block();  // converts async to sync

        return Optional.of(cell).orElseThrow(() -> new ResourceNotFoundException("Cell", "Id", cellId));
    }

    public List<Cell> findAll(Long farmerId) {
        List<Cell> cells = webClientBuilder.build()
                .get()
                .uri("http://localhost:7071/api/v1/farmers/"+ farmerId + "/cells")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Cell>>() {})
                .block();

        return cells;
    }
}
