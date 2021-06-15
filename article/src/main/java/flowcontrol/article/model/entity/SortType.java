package flowcontrol.article.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import flowcontrol.article.repository.generic.AbstractBaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "SortType")
@Table(name = "sort_type")
@Getter
@Setter
@ToString
public class SortType extends AbstractBaseEntity {

    private String name;
    private String description;


    // Relations
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sortType")
    @JsonIgnore
    private Set<Article> articles = new HashSet<>();
}
