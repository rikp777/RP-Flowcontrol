package flowcontrol.farmer.repository;

import flowcontrol.farmer.model.entity.Cell;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CellRepository extends JpaRepository<Cell, Long> {
}
