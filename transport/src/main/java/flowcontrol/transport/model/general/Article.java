package flowcontrol.transport.model.general;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Article {
    @JsonProperty("full_name")
    private String fullName;
}
