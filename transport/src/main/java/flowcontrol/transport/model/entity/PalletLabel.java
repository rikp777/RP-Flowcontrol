package flowcontrol.transport.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity(name = "palletLabel")
@Table(name = "pallet_labels")
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "shippinglabel"})
public class PalletLabel extends BaseEntity{

    @Builder
    public PalletLabel(UUID id){
        super(id);
    }

    @Column(unique=true)
    private Long generalId;
    private String cropDate;
    private String note;
    private Long palletLabelFarmerId;
    private Integer articleAmount;

    private Integer harvestCycle;
    private Integer harvestCycleDay;

    @Column(name = "cell_id")
    private UUID cell;

    @Column(name = "article_id")
    private UUID article;

    @Column(name = "farmer_id")
    private UUID farmer;

    @Column(name = "pallet_type_id")
    private UUID palletType;

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
