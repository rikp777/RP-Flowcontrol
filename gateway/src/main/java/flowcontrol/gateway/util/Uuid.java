package flowcontrol.gateway.util;

import java.util.UUID;

public class Uuid {

    private Uuid() {
        throw new UnsupportedOperationException("Cannot instantiate a Uuid util class");
    }

    public static String generateRandomUuid(){
        return UUID.randomUUID().toString();
    }
}
