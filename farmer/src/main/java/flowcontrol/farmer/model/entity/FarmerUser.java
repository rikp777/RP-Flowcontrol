package flowcontrol.farmer.model.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "farmerUser")
@Table(name = "farmer_user")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FarmerUser extends BaseEntity {
    @ManyToOne
    @JoinColumn(name="farmer_id", nullable = false)
    private Farmer farmer;

    @org.hibernate.annotations.Type(type="uuid-char")
    private UUID userId;
    private String email;
}

