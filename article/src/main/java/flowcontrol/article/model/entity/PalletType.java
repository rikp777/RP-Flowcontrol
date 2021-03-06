package flowcontrol.article.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import flowcontrol.article.repository.Generic.AbstractBaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "PalletType")
@Table(name = "pallet_type")
@Getter
@Setter
@ToString
@Transactional
public class PalletType extends AbstractBaseEntity {
    private String name;
    private Integer weight;
    private Double price;

    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Article> articles = new HashSet<>();
}
