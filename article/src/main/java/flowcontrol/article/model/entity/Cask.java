package flowcontrol.article.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import flowcontrol.article.repository.generic.AbstractBaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "Cask")
@Table(name = "casks")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Cask extends AbstractBaseEntity {

    public Cask(UUID id){
        super(id);
    }

    private String excelCode;
    private String name;
    private String description;
    private Integer weight;
    private Integer maxFillingQuantity;
    private String material;

    // Relations
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cask")
    @JsonIgnore
    private Set<Article> articles = new HashSet<>();

    @ManyToOne()
    @JoinColumn(
            name = "color_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name="color_fk_in_cask")
    )
    private Color color;
}
