package flowcontrol.farmer.repository;

import flowcontrol.farmer.model.entity.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FarmerRepository extends AbstractBaseRepository<Farmer, UUID> {
}
