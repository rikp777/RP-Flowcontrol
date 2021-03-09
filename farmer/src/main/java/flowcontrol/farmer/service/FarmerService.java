package flowcontrol.farmer.service;

import flowcontrol.farmer.model.entity.Farmer;
import flowcontrol.farmer.repository.FarmerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class FarmerService {

    private final FarmerRepository farmerRepository;


    public Iterable<Farmer>  getAll(){
        return farmerRepository.findAll();
    }

    public Optional<Farmer> getById(Long id){
        return farmerRepository.findById(id);
    }
}
