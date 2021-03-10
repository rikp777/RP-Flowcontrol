package flowcontrol.transport.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "shippingLabel")
@Table(name = "shipping_labels")
@Data

@AllArgsConstructor
@NoArgsConstructor
public class ShippingLabel {
    @Id
    @GeneratedValue
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    private Long generalId;
    private String transportDate;
    private String transportDeliveryDate;
    private Long farmerId;
    private Long truckId;
    private Long transportDriverId;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "shippingLabel",
            orphanRemoval = true
    )
    @JsonIgnore
    private Set<PalletLabel> palletLabels;
}
