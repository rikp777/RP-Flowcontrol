package flowcontrol.farmer.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "Cell")
@Table(name = "cultivation_cell")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cell extends BaseEntity{

    public Cell(UUID id){
        super(id);
    }

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
