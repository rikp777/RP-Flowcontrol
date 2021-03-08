//package flowcontrol.farmer.model.entity;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Entity;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//import java.util.HashSet;
//import java.util.Set;
//
//@Entity(name = "Certificate")
//@Table(name = "certificate")
//@Getter
//@Setter
//@ToString
//@NoArgsConstructor
//public class Certificate extends BaseEntity {
//
//    private String name;
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "certificate")
//    Set<FarmerCertificate> farmerCertificates = new HashSet<>();
//}
