package flowcontrol.farmer.service;

import flowcontrol.farmer.model.entity.Cell;
import flowcontrol.farmer.model.entity.FarmerCertificate;
import flowcontrol.farmer.repository.CellRepository;
import flowcontrol.farmer.repository.FarmerCertificateRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FarmerCertificateService extends BaseService<FarmerCertificate> {

    private final FarmerCertificateRepository farmerCertificateRepository;

    public FarmerCertificateService(FarmerCertificateRepository farmerCertificateRepository) {
        super(farmerCertificateRepository);
        this.farmerCertificateRepository = farmerCertificateRepository;
    }

    public Optional<FarmerCertificate> getByIdAndFarmerId(UUID farmerCertificateId, UUID farmerId){
        return farmerCertificateRepository.getFarmerCertificatesByFarmerIdAndId(farmerId, farmerCertificateId);
    }
    public Iterable<FarmerCertificate> getAllByFarmerId(UUID farmerId){
        return farmerCertificateRepository.getFarmerCertificatesByFarmerId(farmerId);
    }
}