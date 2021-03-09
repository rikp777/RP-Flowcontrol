package flowcontrol.farmer.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Entity(name = "Cell")
@Table(name = "cell")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cell extends BaseEntity{

    private String name;
    private String description;

    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(name="farmer_fk", nullable = false)
    @JsonBackReference
    private Farmer farmer;
}
