package flowcontrol.production.repository.impl;

import flowcontrol.production.model.general.PalletLabel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Repository
public class PalletLabelRepository {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public Optional<PalletLabel> findById(Long id) {
        PalletLabel palletLabel = webClientBuilder.build() //Gives you a client
                .get() // Method for the request
                .uri("http://localhost:7072/api/v1/palletlabels/" + id) // Url that you need to access
                .retrieve() // Go do the fetch
                .bodyToMono(PalletLabel.class) // Whatever body go get back map it to the class - Mono means you will get a object back but not right away "async" //empty page but you will need to wait but you know it will come and than you can do stuff "promise"
                .block();  // converts async to sync

        return Optional.of(palletLabel);
    }

    public List<PalletLabel> findAll() {
        List<PalletLabel> palletLabel = webClientBuilder.build()
                .get()
                .uri("http://localhost:7072/api/v1/palletlabels/")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<PalletLabel>>() {})
                .block();

        return palletLabel;
    }
}
