package flowcontrol.article.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import flowcontrol.article.repository.generic.AbstractBaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "Group")
@Table(name = "article_groups")
@Getter
@Setter
@ToString
@NoArgsConstructor
@Transactional
public class Group extends AbstractBaseEntity {

    public Group(UUID id){
        super(id);
    }

    private String name;
    private String description;


    // Relations
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
    @JsonIgnore
    private Set<Article> articles = new HashSet<>();
}
