package flowcontrol.gateway.model.general;

import flowcontrol.gateway.model.entity.User;
import flowcontrol.gateway.model.entity.base.DateAudit;
import flowcontrol.gateway.model.entity.enumeration.TokenStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity(name = "email_verification_token")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmailVerificationToken extends DateAudit {

    @Id
    @Column(name = "token_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "email_token_sequence")
    @SequenceGenerator(name = "email_token_sequence", allocationSize = 1)
    private Long id;

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

}
