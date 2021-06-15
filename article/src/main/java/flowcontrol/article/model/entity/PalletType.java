package flowcontrol.article.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import flowcontrol.article.repository.generic.AbstractBaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
