package flowcontrol.production.repository;

import flowcontrol.production.model.entity.Line;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineRepository extends JpaRepository<Line, Long> {
}
