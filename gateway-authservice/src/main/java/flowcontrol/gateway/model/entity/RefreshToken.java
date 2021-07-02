package flowcontrol.gateway.model.entity;

import flowcontrol.gateway.model.entity.base.DateAudit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;


@Entity(name = "refresh_token")
@Table(name = "refresh_token")

@Builder

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken extends BaseEntity {

    public RefreshToken(UUID id){
        super(id);
    }

    @Column(name = "token", nullable = false, unique = true)
    @NaturalId(mutable = true)
    private String token;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_device_id", unique = true)
    private UserDevice userDevice;

    @Column(name = "refresh_count")
    private Long refreshCount;

    @Column(name = "expiry_date", nullable = false)
    private Instant ExpiryDate;

    public void incrementRefreshCount(){
        this.refreshCount = this.refreshCount + 1;
    }
}
