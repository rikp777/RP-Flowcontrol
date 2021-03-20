package flowcontrol.production.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity( name = "Line" )
@Table( name = "line" )
@ToString
public class Line extends BaseEntity{

    private String name;
    private String description;
}
