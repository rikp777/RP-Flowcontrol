package flowcontrol.transport.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "shippingLabel")
@Table(name = "shipping_labels")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "palletLabels"})
public class ShippingLabel extends BaseEntity{

    public ShippingLabel(UUID id){
        super(id);
    }

    private Long generalId;
    private String transportDate;
    private String transportDeliveryDate;
    private UUID farmerId;
    private UUID truckId;
    private UUID transportDriverId;

    @OneToMany(
            mappedBy = "shippingLabel",
            targetEntity = PalletLabel.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JsonManagedReference
    private Set<PalletLabel> palletLabels = new HashSet<PalletLabel>();
}
