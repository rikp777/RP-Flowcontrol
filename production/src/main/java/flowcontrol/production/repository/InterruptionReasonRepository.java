package flowcontrol.production.repository;

import flowcontrol.production.model.entity.InterruptionReason;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface InterruptionReasonRepository extends JpaRepository<InterruptionReason, UUID> {
    Optional<InterruptionReason> findByName(String name);
}
