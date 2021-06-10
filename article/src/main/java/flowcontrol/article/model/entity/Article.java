package flowcontrol.article.model.entity;


import flowcontrol.article.repository.Generic.AbstractBaseEntity;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity(name = "Article")
@DynamicUpdate(true)
@SQLDelete(sql =
        "UPDATE article " +
                "SET inactive = true " +
                "WHERE id = ?")
@Where(clause = "inactive = false")
@Table(name = "article")
@Getter
@Setter
@Accessors(chain = true)
@ToString
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Article extends AbstractBaseEntity {

    @Column(unique = true)
    private String excelCode;
    private Integer insetGram;
    private Integer insetLimit;
    private Integer palletLimit;
    private String origin;
    private boolean biologic;
    private boolean inactive;
    private String additionalInfo;

    // Relationships
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "type_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name="type_fk_in_article")
    )
    private Type type;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "pallet_type_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name="pallet_type_fk_in_article")
    )
    private PalletType palletType;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "cask_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name="cask_fk_in_article")
    )
    private Cask cask;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "inset_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name="inset_fk_in_article")
    )
    private Inset inset;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "article_group_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name="article_group_fk_in_article")
    )
    private Group group;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "sort_type_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name="sort_type_fk_in_article")
    )
    private SortType sortType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "color_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name="color_fk_in_group")
    )
    private Color color;


    private String name;

    public String getFullName() {
        String name = "";

        if(this.getColor() != null){
            if(!this.getColor().getName().isEmpty()){
                name = name + this.getColor().getName() + " ";
            }
        }
        if(this.getGroup() != null){
            if(!this.getGroup().getName().isEmpty()){
                name = name + this.getGroup().getName() + " ";
            }
        }
        if(this.getType() != null){
            if(!this.getType().getName().isEmpty()){
                name = name + this.getType().getName() + " ";
            }
        }
        if(this.getSortType() != null){
            if(!this.getSortType().getName().isEmpty()){
                name = name + this.getSortType().getName() + " ";
            }
        }
        if(this.getInsetLimit() != null){
            name = name + this.getInsetLimit() + " x ";
        }
        if(this.getInsetGram() != null){
            name = name + this.getInsetGram() + " ";
        }
        if(this.getInset() != null){
            if(!this.getInset().getName().isEmpty()){
                name = name + this.getInset().getName() + " ";
            }
        }
        if(this.origin != null){
            name = name + this.origin + " ";
        }
        if(this.isBiologic()){
            name = name + "BIO ";
        }

        if(this.getExcelCode() != null){
            name = name + "(" + this.getExcelCode() + ") ";
        }

        if(this.additionalInfo != null){
            name = name + this.additionalInfo;
        }

        return name.trim().substring(0, name.length() -1).trim();
    }


    @Override
    public String toString() {
        return "Article{" +
                "excelCode='" + excelCode + '\'' +
                ", insetGram=" + insetGram +
                ", insetLimit=" + insetLimit +
                ", palletLimit=" + palletLimit +
                ", origin='" + origin + '\'' +
                ", biologic=" + biologic +
                ", inactive=" + inactive +
                ", additionalInfo='" + additionalInfo + '\'' +
                ", type=" + type +
                ", palletType=" + palletType +
                ", cask=" + cask +
                ", inset=" + inset +
                ", group=" + group +
                ", sortType=" + sortType +
                ", color=" + color +
                '}';
    }
}
