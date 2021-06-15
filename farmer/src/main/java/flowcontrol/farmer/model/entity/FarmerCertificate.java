package flowcontrol.farmer.model.entity;

        import com.fasterxml.jackson.annotation.JsonBackReference;
        import lombok.*;

        import javax.persistence.*;

@Entity(name = "FarmerCertificate")
@Table(name = "farmer_cerfiticate")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FarmerCertificate extends BaseEntity {
    private String code;

    @ManyToOne
    @JoinColumn(name="farmer_id", nullable = false)
    @JsonBackReference

    private Farmer farmer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Certificate certificate;
}
