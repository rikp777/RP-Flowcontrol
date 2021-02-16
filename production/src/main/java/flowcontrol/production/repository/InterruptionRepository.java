package flowcontrol.production.repository;

import flowcontrol.production.model.entity.Interruption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterruptionRepository extends JpaRepository<Interruption, String> {
}
