package flowcontrol.gateway.security.chache;

import flowcontrol.gateway.event.OnUserLogOutSuccessEvent;
import flowcontrol.gateway.security.JwtTokenProvider;
import flowcontrol.gateway.security.JwtTokenValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.jodah.expiringmap.ExpiringMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class LoggedOutJwtTokenCache {

    private final ExpiringMap<String, OnUserLogOutSuccessEvent> tokenEventMap;
    private final JwtTokenProvider tokenProvider;

    @Autowired
    public LoggedOutJwtTokenCache(@Value("${app.cache.logoutToken.maxSize}") int maxSize, JwtTokenProvider tokenProvider){
        this.tokenProvider = tokenProvider;
        this.tokenEventMap = ExpiringMap.builder()
                .variableExpiration()
                .maxSize(maxSize)
                .build();
    }

    public void markLogOutEventForToken(OnUserLogOutSuccessEvent event){
        String token = event.getToken();

        if(tokenEventMap.containsKey(token)){
            log.info(String.format("Log out token for user [%s] is already present in the cache", event.getUserEmail()));

        }else{
            Date tokenExpiryDate = tokenProvider.getTokenExpiryFromJwt(token);

            long ttlForToken = getTTLForToken(tokenExpiryDate);
            log.info(String.format("Logout token cache set for [%s] with a TTl of [%s] seconds. Token is due expiry at [%s]", event.getUserEmail(), ttlForToken, tokenExpiryDate));
            tokenEventMap.put(token, event, ttlForToken, TimeUnit.SECONDS);
        }
    }

    public OnUserLogOutSuccessEvent getLogoutEventForToken(String token){
        return tokenEventMap.get(token);
    }

    private long getTTLForToken(Date date){
        long secondAtExpiry = date.toInstant().getEpochSecond();
        long secondAtLogout = Instant.now().getEpochSecond();
        return Math.max(0, secondAtExpiry - secondAtLogout);
    }
}
