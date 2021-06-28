package flowcontrol.farmer.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "Certificate")
@Table(name = "certificate")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Certificate extends BaseEntity {

    public Certificate(UUID id){
        super(id);
    }

    private String name;

    @OneToMany(mappedBy = "certificate", fetch = FetchType.EAGER)
    @JsonIgnore
    Set<FarmerCertificate> farmerCertificates = new HashSet<>();
}
