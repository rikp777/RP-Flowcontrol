package flowcontrol.farmer.service;

import flowcontrol.farmer.model.entity.Farmer;
import flowcontrol.farmer.repository.FarmerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FarmerService extends BaseService<Farmer> {

    private final FarmerRepository farmerRepository;

    public FarmerService(FarmerRepository farmerRepository) {
        super(farmerRepository);
        this.farmerRepository = farmerRepository;
    }
}
