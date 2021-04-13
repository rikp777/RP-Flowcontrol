package flowcontrol.gateway.security;

import flowcontrol.gateway.event.OnUserLogOutSuccessEvent;
import flowcontrol.gateway.exception.TokenInvalidRequestException;
import flowcontrol.gateway.security.chache.LoggedOutJwtTokenCache;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtTokenValidator {

    private final String jwtSecret;
    private final LoggedOutJwtTokenCache loggedOutJwtTokenCache;


    @Autowired
    public JwtTokenValidator(@Value("${app.jwt.secret}") String jwtSecret, LoggedOutJwtTokenCache loggedOutJwtTokenCache){
        this.jwtSecret = jwtSecret;
        this.loggedOutJwtTokenCache = loggedOutJwtTokenCache;
    }


    public boolean validateToken(String authToken){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
        } catch (SignatureException exception){
            log.error("Invalid JWT signature");
            throw new TokenInvalidRequestException("JWT", authToken, "Incorrect signature");
        } catch (MalformedJwtException exception){
            log.error("Invalid JWT token");
            throw new TokenInvalidRequestException("JWT", authToken, "Malformed jwt token");
        } catch (ExpiredJwtException exception) {
            log.error("Expired JWT token");
            throw new TokenInvalidRequestException("JWT", authToken, "Token expired. Refresh required");
        } catch (UnsupportedJwtException exception) {
            log.error("Unsupported JWT token");
            throw new TokenInvalidRequestException("JWT", authToken, "Unsupported JWT token");
        } catch (IllegalArgumentException exception) {
            log.error("JWT claims String is empty");
            throw new TokenInvalidRequestException("JWT", authToken, "Illegal argument token");
        }
        validateTokenIsNotForALoggedOutDevice(authToken);
        return true;
    }

    private void validateTokenIsNotForALoggedOutDevice(String authToken){
        OnUserLogOutSuccessEvent previouslyLoggedOutEvent = loggedOutJwtTokenCache.getLogoutEventForToken(authToken);

        if(previouslyLoggedOutEvent != null){
            String userEmail = previouslyLoggedOutEvent.getUserEmail();
            Date logOutEventDate = previouslyLoggedOutEvent.getEvenTime();
            String errorMessage = String.format("Token corresponds to an already logged out user [%s] at [%s]. Please login again", userEmail, logOutEventDate);
            throw new TokenInvalidRequestException("JWT", authToken, errorMessage);
        }
    }
}
