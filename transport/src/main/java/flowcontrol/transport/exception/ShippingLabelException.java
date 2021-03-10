package flowcontrol.transport.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class ShippingLabelException extends RuntimeException {

    private final String action;
    private final String message;

    public ShippingLabelException(String action, String message, Object data) {
        super(String.format("ShippingLabel couldn't not preform %s, %s" , action, message, data));
        this.action = action;
        this.message = message;
    }
}
