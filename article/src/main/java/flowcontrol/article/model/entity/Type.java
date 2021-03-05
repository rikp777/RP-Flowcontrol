package flowcontrol.article.model.entity;

import flowcontrol.article.repository.Generic.AbstractBaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "Type")
@Table(name = "type")
@Getter
@Setter
@ToString
public class Type extends AbstractBaseEntity {
    private String name;
}
