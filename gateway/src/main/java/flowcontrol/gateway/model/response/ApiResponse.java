package flowcontrol.gateway.model.response;

import lombok.Data;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Data
public class ApiResponse {
    private final String data;
    private final Boolean success;
    private final ZonedDateTime timeStamp;
    private final String cause;
    private final String path;

    public ApiResponse(String data, Boolean success, String cause, String path) {
        this.data = data;
        this.success = success;
        this.cause = cause;
        this.path = path;
        this.timeStamp = this.getZonedDateTime();
    }

    public ApiResponse(String data, Boolean success) {
        this.data = data;
        this.success = success;
        this.cause = null;
        this.path = null;
        this.timeStamp = this.getZonedDateTime();
    }


    private ZonedDateTime getZonedDateTime(){
        return ZonedDateTime.now(ZoneId.of("UTC"));
    }
}
