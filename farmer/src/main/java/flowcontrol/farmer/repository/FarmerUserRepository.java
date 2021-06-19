package flowcontrol.farmer.repository;

import flowcontrol.farmer.model.entity.Cell;
import flowcontrol.farmer.model.entity.Farmer;
import flowcontrol.farmer.model.entity.FarmerUser;

import java.util.List;
import java.util.Optional;

public interface FarmerUserRepository extends AbstractBaseRepository<FarmerUser, Long>{
    List<FarmerUser> getFarmerUserByUserId(Long userId);
}
