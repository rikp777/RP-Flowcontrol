package flowcontrol.article.model.entity;

import flowcontrol.article.repository.Generic.AbstractBaseEntity;
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

@Entity(name = "Color")
@Table(name = "color")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Color extends AbstractBaseEntity {

    private String name;


    // Relations
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "color")
    private Set<Cask> casks = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "color")
    private Set<Inset> insets = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "color")
    private Set<Article> articles = new HashSet<>();
}
