package flowcontrol.production.model.response;

import lombok.Data;

import java.util.UUID;

@Data
public class LineResponse {
    private UUID id;
    private String name;
    private String description;
}
