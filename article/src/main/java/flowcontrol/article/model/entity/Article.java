package flowcontrol.article.model.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import flowcontrol.article.repository.Generic.AbstractBaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Article")
@SQLDelete(sql =
        "UPDATE article " +
                "SET inactive = true " +
                "WHERE id = ?")
@Where(clause = "inactive = false")
@Table(name = "article")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Article extends AbstractBaseEntity {

    private String excelCode;
    private Integer insetGram;
    private Integer palletLimit;
    private String origin;
    private boolean biologic;
    private boolean inactive;

    // Relationships
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "cask_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name="cask_fk_in_article")
    )
    private Cask cask;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "inset_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name="inset_fk_in_article")
    )
    private Inset inset;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "article_group_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name="article_group_fk_in_article")
    )
    private Group group;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "sort_type_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name="sort_type_fk_in_article")
    )
    private SortType sortType;

}
