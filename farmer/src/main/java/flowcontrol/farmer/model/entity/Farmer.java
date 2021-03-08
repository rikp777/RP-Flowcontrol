package flowcontrol.farmer.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "Farmer")
@Table(name = "farmer")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Farmer extends BaseEntity{

    private String name;
    private String street;
    private String street_number;
    private String place;
    private String zipCode;
    private String country;
    private String province;
    private String email;
    private String phone;
    private String fax;
    private String kvk;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "farmer")
//    Set<FarmerCertificate> farmerCertificates = new HashSet<>();
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "farmer")
//    Set<Cell> cells = new HashSet<>();
}
