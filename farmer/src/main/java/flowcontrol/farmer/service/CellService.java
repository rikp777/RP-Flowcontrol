package flowcontrol.farmer.service;

import flowcontrol.farmer.exception.ResourceNotFoundException;
import flowcontrol.farmer.model.entity.Cell;
import flowcontrol.farmer.model.entity.Farmer;
import flowcontrol.farmer.repository.CellRepository;
import flowcontrol.farmer.repository.FarmerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class CellService {

    private final FarmerRepository farmerRepository;
    private final CellRepository cellRepository;


    public Iterable<Cell> getAll(Long farmerId){
        Farmer farmer = farmerRepository.getOne(farmerId);

        return farmer.getCells();
    }

    @Transactional
    public Optional<Cell> getById(Long farmerId, Long id){
        Optional<Cell> cell = farmerRepository.findById(farmerId).get()
                .getCells().stream().filter(item -> item.getId() == id).findFirst();

        return cell;
    }
}
