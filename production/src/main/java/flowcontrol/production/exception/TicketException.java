package flowcontrol.production.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class TicketException extends RuntimeException{

    public TicketException(String message) {
        super(message);
    }

    public TicketException(String message, Throwable cause){
        super(message, cause);
    }

}
