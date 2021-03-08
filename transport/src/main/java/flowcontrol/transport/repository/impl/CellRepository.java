package flowcontrol.transport.repository.impl;

import flowcontrol.transport.model.general.Article;
import flowcontrol.transport.model.general.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Repository
public class CellRepository {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public Optional<Cell> findById(Long id) {
        Cell cell = webClientBuilder.build() //Gives you a client
                .get() // Method for the request
                .uri("http://localhost:7072/api/v1/cells/" + id) // Url that you need to access
                .retrieve() // Go do the fetch
                .bodyToMono(Cell.class) // Whatever body go get back map it to the class - Mono means you will get a
                // object back but not right away "async" //empty page but you will need to wait but you know it will come and than you can do stuff "promise"
                .block();  // converts async to sync

        return Optional.of(cell);
    }

    public List<Cell> findAll() {
        List<Cell> cells = webClientBuilder.build()
                .get()
                .uri("http://localhost:7072/api/v1/cells/")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Cell>>() {})
                .block();

        return cells;
    }
}
