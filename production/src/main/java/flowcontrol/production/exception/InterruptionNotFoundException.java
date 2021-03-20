package flowcontrol.production.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class InterruptionNotFoundException extends ResourceNotFoundException {

    public InterruptionNotFoundException(String fieldName, Object fieldValue) {
        super("Interruption", fieldName, fieldValue);
    }
}
