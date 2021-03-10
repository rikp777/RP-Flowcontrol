package flowcontrol.transport.model.entity;

import flowcontrol.transport.model.general.Article;
import flowcontrol.transport.model.general.Cell;
import flowcontrol.transport.model.general.Farmer;
import flowcontrol.transport.model.general.PalletType;
import io.micrometer.core.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity(name = "palletLabel")
@Table(name = "pallet_labels")
@Data
@AllArgsConstructor
@NoArgsConstructor
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
            fetch = FetchType.LAZY,
            optional = true
    )
    @JoinColumn(
            name = "shipping_label_id"
    )
    private ShippingLabel shippingLabel;
}
