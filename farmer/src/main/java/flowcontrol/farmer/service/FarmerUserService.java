package flowcontrol.farmer.service;

import flowcontrol.farmer.model.entity.Cell;
import flowcontrol.farmer.model.entity.Farmer;
import flowcontrol.farmer.model.entity.FarmerUser;
import flowcontrol.farmer.repository.FarmerRepository;
import flowcontrol.farmer.repository.FarmerUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FarmerUserService extends BaseService<FarmerUser> {

    private final FarmerUserRepository farmerUserRepository;

    public FarmerUserService(FarmerUserRepository farmerUserRepository) {
        super(farmerUserRepository);
        this.farmerUserRepository = farmerUserRepository;
    }


    public List<FarmerUser> getAllByUserId(UUID userId){
        return farmerUserRepository.getFarmerUserByUserId(userId);
    }
}
