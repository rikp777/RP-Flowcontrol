package flowcontrol.farmer.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;
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

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "farmer_id")
    private Set<FarmerCertificate> farmerCertificates = new HashSet<>();

    @OneToMany(
            mappedBy = "farmer",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<Cell> cells;
}

//https://stackoverflow.com/questions/58090291/obtain-column-data-from-one-to-many-join-relation-in-spring-data-jpa
