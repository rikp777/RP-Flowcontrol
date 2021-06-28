package flowcontrol.production.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@Entity( name = "Line" )
@Table( name = "line" )
@ToString
@NoArgsConstructor
public class Line extends BaseEntity{

    public Line(UUID id){
        super(id);
    }

    private String name;
    private String description;
}
