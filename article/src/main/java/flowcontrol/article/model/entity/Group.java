package flowcontrol.article.model.entity;

import flowcontrol.article.repository.Generic.AbstractBaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Group")
@Table(name = "article_groups")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Group extends AbstractBaseEntity {

    private String name;
    private String description;


    // Relations
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
    private Set<Article> articles = new HashSet<>();

    @ManyToOne()
    @JoinColumn(
            name = "color_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name="color_fk_in_group")
    )
    private Color color;
}
