package flowcontrol.gateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class TokenInvalidRequestException extends RuntimeException {

    private final String tokenType;
    private final String token;
    private final String message;

    public TokenInvalidRequestException(String tokenType, String token, String message) {
        super(String.format("%s: [%s] token: [%s] ", message, tokenType, token));
        this.tokenType = tokenType;
        this.token = token;
        this.message = message;
    }
}
