package flowcontrol.article.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {

    private final String data;
    private final Boolean success;
    private final ZonedDateTime timeStamp;
    private final String cause;
    private final String path;
    private final ArrayList<CustomFieldError> errors;

    public ApiResponse(Boolean success, String data, String cause, String path){
        this.data = data;
        this.success = success;
        this.cause = cause;
        this.path = path;
        this.timeStamp = this.getZonedDateTime();
        this.errors = null;
    }

    public ApiResponse(Boolean success, ArrayList<CustomFieldError> errors, String cause, String path){
        this.data = null;
        this.errors = errors;
        this.success = success;
        this.cause = cause;
        this.path = path;
        this.timeStamp = this.getZonedDateTime();
    }

    public ApiResponse(Boolean success, String data){
        this.data = data;
        this.success = success;
        this.cause = null;
        this.path = null;
        this.timeStamp = this.getZonedDateTime();
        this.errors = null;
    }

    private ZonedDateTime getZonedDateTime(){
        return ZonedDateTime.now(ZoneId.of("UTC"));
    }
}
