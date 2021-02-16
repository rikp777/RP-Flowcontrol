package flowcontrol.gateway.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class PasswordUpdateException extends RuntimeException {

    private final String user;
    private final String message;

    public PasswordUpdateException(String user, String message) {
        super(String.format("Couldn't update password for [%s]: [%s]", user,message));
        this.user = user;
        this.message = message;
    }
}
