package flowcontrol.article.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import flowcontrol.article.repository.generic.AbstractBaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "Type")
@Table(name = "type")
@Getter
@Setter
@ToString
@Transactional
@NoArgsConstructor
public class Type extends AbstractBaseEntity {

    public Type(UUID id){
        super(id);
    }

    private String name;

    @OneToMany()
    @JsonIgnore
    private Set<Article> articles = new HashSet<>();
}
