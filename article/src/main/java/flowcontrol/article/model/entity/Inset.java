package flowcontrol.article.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import flowcontrol.article.repository.Generic.AbstractBaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Inset")
@Table(name = "inset")
@Getter
@Setter
@ToString
@Transactional
public class Inset extends AbstractBaseEntity {

    private String excelCode;
    private String name;
    private String description;
    private Integer maxInsetAmount;

    // Relations
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inset")
    @JsonIgnore
    private Set<Article> articles = new HashSet<>();

    @ManyToOne()
    @JoinColumn(
            name = "color_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name="color_fk_in_inset")
    )
    private Color color;
}
