package flowcontrol.gateway.model.entity;

import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;


@Entity(name = "password_reset_token")
@Table(name = "password_reset_token")

@Builder

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResetToken extends BaseEntity{
    public PasswordResetToken(UUID id){
        super(id);
    }

    @NaturalId
    @Column(name = "token_name", nullable = false, unique = true)
    private String token;

    @Column(name = "expiry_date", nullable = false)
    private Instant expiryDate;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
}
