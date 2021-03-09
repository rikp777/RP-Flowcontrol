package flowcontrol.farmer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class GeneralException extends RuntimeException {

    public GeneralException(String message){
        super(message);
    }

    public GeneralException(String message, Throwable cause){
        super(message, cause);
    }
}
