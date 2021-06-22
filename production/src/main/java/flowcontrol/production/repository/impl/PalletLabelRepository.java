package flowcontrol.production.repository.impl;

import flowcontrol.production.exception.AppException;
import flowcontrol.production.exception.ResourceNotFoundException;
import flowcontrol.production.model.general.BearerTokenWrapper;
import flowcontrol.production.model.general.PalletLabel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Repository
@Slf4j
public class PalletLabelRepository {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private BearerTokenWrapper tokenWrapper;


    @Autowired
    private RedisTemplate<String, PalletLabel> palletLabelRedisTemplate;
    private static final String REDIS_PREFIX_PALLETLABELS = "palletlabels";
    private static final String REDIS_KEYS_SEPARATOR = ":";

    private ValueOperations<String, PalletLabel> getValueOperations(){
        return palletLabelRedisTemplate.opsForValue();
    }

    private String getRedisKey(final String palletLabelId){
        return REDIS_PREFIX_PALLETLABELS + REDIS_KEYS_SEPARATOR + palletLabelId;
    }


    public Optional<PalletLabel> findById(Long farmerId, Long palletLabelId) {
//        palletLabelRedisTemplate.delete(getRedisKey(palletLabelId.toString()));
        //PalletLabel cachedPalletLabel = getValueOperations().get(getRedisKey(palletLabelId.toString()));

        //if(cachedPalletLabel == null) {
            PalletLabel palletLabel = webClientBuilder
                    .filter(authHeader(tokenWrapper.getToken()))
                    .build() //Gives you a client
                    .get() // Method for the request
                    .uri("http://localhost:7072/transport/api/v1/farmers/" + farmerId + "/palletlabels/" + palletLabelId)
                    .retrieve() // Go do the fetch
                    .onStatus(HttpStatus::is4xxClientError,
                            error -> Mono.error(new ResourceNotFoundException("PalletLabel", "Id", palletLabelId)))
                    .onStatus(HttpStatus::is5xxServerError,
                            error -> Mono.error(new AppException("Server is not responding")))
                    .bodyToMono(PalletLabel.class) // Whatever body go get back map it to the class - Mono means you will get a object back but not right away "async" //empty page but you will need to wait but you know it will come and than you can do stuff "promise"
                    .block();  // converts async to sync
            //getValueOperations().set(getRedisKey(palletLabelId.toString()), palletLabel);
            //log.info("Saved pallet label [" + palletLabelId + "] to cache");
            //palletLabelRedisTemplate.expire(getRedisKey(palletLabelId.toString()), 10, TimeUnit.SECONDS);
            return Optional.of(palletLabel);
        //}
        //log.info("Got pallet label: [" + palletLabelId + "] from cache");
        //return Optional.of(cachedPalletLabel);
    }

    public List<PalletLabel> findAll(Long farmerId) {
        List<PalletLabel> palletLabel = webClientBuilder
                .filter(authHeader(tokenWrapper.getToken()))
                .build()
                .get()
                .uri("http://localhost:7072/transport/api/v1/farmers/"+ farmerId + "/palletlabels")
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
