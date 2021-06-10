package flowcontrol.production.model.general;

import lombok.Data;

import java.io.Serializable;

@Data
public class Article implements Serializable {
    private String name;
}
