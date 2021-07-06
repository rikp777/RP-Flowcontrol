package flowcontrol.farmer.repository;

import flowcontrol.farmer.model.entity.Cell;
import flowcontrol.farmer.model.entity.Farmer;
import flowcontrol.farmer.model.entity.FarmerUser;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FarmerUserRepository extends AbstractBaseRepository<FarmerUser, UUID>{
    List<FarmerUser> getFarmerUserByUserId(Long userId);
}
