package flowcontrol.gateway.model.general;

import flowcontrol.gateway.model.entity.BaseEntity;
import flowcontrol.gateway.model.entity.User;
import flowcontrol.gateway.model.entity.base.DateAudit;
import flowcontrol.gateway.model.entity.enumeration.TokenStatus;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Entity(name = "email_verification_token")
@Table(name = "email_verification_token")

@Builder

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailVerificationToken extends BaseEntity {

    @Column(name = "token", nullable = false, unique = true)
    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @Column(name = "token_status")
    @Enumerated(EnumType.STRING)
    private TokenStatus tokenStatus;

    @Column(name = "expiry_date", nullable = false)
    private Instant expiryDate;

    public void setConfirmedStatus() {
        this.setTokenStatus(TokenStatus.STATUS_CONFIRMED);
    }
}
