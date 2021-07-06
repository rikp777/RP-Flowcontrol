package flowcontrol.production.repository;

import flowcontrol.production.model.entity.InterruptionReason;
import flowcontrol.production.model.entity.Line;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LineRepository extends JpaRepository<Line, UUID> {
    Optional<Line> findByName(String name);
}
