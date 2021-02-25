package flowcontrol.production.exception;

public class InterruptionNotFoundException extends ResourceAlreadyInUseException{


    public InterruptionNotFoundException(Object fieldValue) {
        super("Interruption", "Interruption not found", fieldValue);
    }
}
