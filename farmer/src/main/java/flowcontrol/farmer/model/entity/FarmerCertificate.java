//package flowcontrol.farmer.model.entity;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;
//
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//@Entity(name = "FarmerCertificate")
//@Table(name = "farmer_cerfiticate")
//@Getter
//@Setter
//@ToString
//@NoArgsConstructor
//public class FarmerCertificate extends BaseEntity {
//    private String code;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Farmer farmer;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Certificate certificate;
//}
