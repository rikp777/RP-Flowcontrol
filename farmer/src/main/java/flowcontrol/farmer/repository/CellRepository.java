package flowcontrol.farmer.repository;

import flowcontrol.farmer.model.entity.Cell;
import flowcontrol.farmer.model.entity.Farmer;
import flowcontrol.farmer.model.entity.FarmerCertificate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CellRepository extends AbstractBaseRepository<Cell, UUID> {
    List<Cell> getCellByFarmerId(UUID farmerId);
    Optional<Cell> getCellByFarmerIdAndId(UUID farmerId, UUID cellId);

}
