package flowcontrol.production.repository;

import flowcontrol.production.model.entity.InterruptionReason;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterruptionReasonRepository extends JpaRepository<InterruptionReason, Long> {
}
