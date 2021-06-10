package flowcontrol.article.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import flowcontrol.article.repository.Generic.AbstractBaseEntity;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Type")
@Table(name = "type")
@Getter
@Setter
@ToString
@Transactional
@NoArgsConstructor
public class Type extends AbstractBaseEntity {
    private String name;

    @OneToMany()
    @JsonIgnore
    private Set<Article> articles = new HashSet<>();
}
