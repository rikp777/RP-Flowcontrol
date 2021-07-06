package flowcontrol.farmer.service;

import flowcontrol.farmer.exception.ResourceNotFoundException;
import flowcontrol.farmer.model.entity.Cell;
import flowcontrol.farmer.model.entity.Farmer;
import flowcontrol.farmer.model.entity.FarmerCertificate;
import flowcontrol.farmer.repository.CellRepository;
import flowcontrol.farmer.repository.FarmerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class CellService extends BaseService<Cell> {

    private final CellRepository cellRepository;

    public CellService(CellRepository cellRepository) {
        super(cellRepository);
        this.cellRepository = cellRepository;
    }

    public Optional<Cell> getByIdAndFarmerId(UUID farmerCertificateId, UUID farmerId){
        return cellRepository.getCellByFarmerIdAndId(farmerId, farmerCertificateId);
    }
    public Iterable<Cell> getAllByFarmerId(UUID farmerId){
        return cellRepository.getCellByFarmerId(farmerId);
    }
}
