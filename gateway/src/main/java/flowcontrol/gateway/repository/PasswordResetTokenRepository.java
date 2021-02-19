package flowcontrol.gateway.repository;

import flowcontrol.gateway.model.entity.PasswordResetToken;

import java.util.Optional;

public interface PasswordResetTokenRepository {

    Optional<PasswordResetToken> findByToken(String token);
}
