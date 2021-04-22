package flowcontrol.transport.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity(name = "palletLabel")
@Table(name = "pallet_labels")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "shippinglabel"})
public class PalletLabel {
    @Id @GeneratedValue
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;
    private Long generalId;
    private String cropDate;
    private String note;
    private Long palletLabelFarmerId;
    private Integer articleAmount;

    private Integer harvestCycle;
    private Integer harvestCycleDay;

    @Column(name = "cell_id")
    private Long cell;

    @Column(name = "article_id")
    private Long article;

    @Column(name = "farmer_id")
    private Long farmer;

    @Column(name = "pallet_type_id")
    private Long palletType;

//    private Cell cell;
//    private Article article;
//    private Farmer farmer;
//    private PalletType palletType;

    @ManyToOne(
            optional = true
    )
    @JoinColumn(
            name = "shipping_label_id"
    )
    @JsonBackReference
    private ShippingLabel shippingLabel;
}
