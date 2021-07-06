package flowcontrol.gateway.repository;

import flowcontrol.gateway.model.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {

    Optional<RefreshToken> findById(UUID id);

    Optional<RefreshToken> findByToken(String token);
}
