package flowcontrol.farmer.repository;

import flowcontrol.farmer.model.entity.Cell;
import flowcontrol.farmer.model.entity.Farmer;
import flowcontrol.farmer.model.entity.FarmerCertificate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CellRepository extends AbstractBaseRepository<Cell, Long> {
    List<Cell> getCellByFarmerId(Long farmerId);
    Optional<Cell> getCellByFarmerIdAndId(Long farmerId, Long cellId);

}
