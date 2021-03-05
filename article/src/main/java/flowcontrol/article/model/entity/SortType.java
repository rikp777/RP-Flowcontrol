package flowcontrol.article.model.entity;


import flowcontrol.article.repository.Generic.AbstractBaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

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
    private Set<Article> articles = new HashSet<>();
}
