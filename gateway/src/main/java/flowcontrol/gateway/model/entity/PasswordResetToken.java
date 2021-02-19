package flowcontrol.gateway.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.time.Instant;

@Entity(name = "password_reset_token")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PasswordResetToken {

    @Id
    @Column(name = "token_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "password_reset_token_sequence")
    @SequenceGenerator(name = "password_reset_token_sequence", allocationSize = 1)
    private Long id;

    @NaturalId
    @Column(name = "token_name", nullable = false, unique = true)
    private String token;

    @Column(name = "expiry_date", nullable = false)
    private Instant expiryDate;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
}
