package flowcontrol.article.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import flowcontrol.article.repository.generic.AbstractBaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "Inset")
@Table(name = "inset")
@Getter
@Setter
@ToString
@Transactional
@NoArgsConstructor
public class Inset extends AbstractBaseEntity {

    public Inset(UUID id){
        super(id);
    }

    private String excelCode;
    private String name;
    private String description;
    private Integer maxInsetAmount;

    // Relations
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inset")
    @JsonIgnore
    private Set<Article> articles = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "color_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name="color_fk_in_article")
    )
    @JsonIgnore
    private Color color;
}
