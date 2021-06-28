package flowcontrol.production.model.general;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class Farmer implements Serializable {
    private UUID id;
    private String name;
}
