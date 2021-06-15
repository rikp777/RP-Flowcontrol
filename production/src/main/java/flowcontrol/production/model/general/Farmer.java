package flowcontrol.production.model.general;

import lombok.Data;

import java.io.Serializable;

@Data
public class Farmer implements Serializable {
    private Long id;
    private String name;
}
