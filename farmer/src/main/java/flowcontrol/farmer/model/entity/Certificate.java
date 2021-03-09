package flowcontrol.farmer.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Certificate")
@Table(name = "certificate")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Certificate extends BaseEntity {

    private String name;

    @OneToMany(mappedBy = "certificate", fetch = FetchType.EAGER)
    Set<FarmerCertificate> farmerCertificates = new HashSet<>();
}
