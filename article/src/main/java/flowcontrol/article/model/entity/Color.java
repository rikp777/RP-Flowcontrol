package flowcontrol.article.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import flowcontrol.article.repository.generic.AbstractBaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "Color")
@Table(name = "color")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Color extends AbstractBaseEntity {

    public Color(UUID id){
        super(id);
    }

    private String name;

    // Relations
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "color")
    @JsonIgnore
    private Set<Cask> casks = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "color")
    @JsonIgnore
    private Set<Inset> insets = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "color")
    @JsonIgnore
    private Set<Article> articles = new HashSet<>();
}
