package flowcontrol.farmer.repository;

import flowcontrol.farmer.model.entity.Certificate;
import flowcontrol.farmer.model.entity.FarmerCertificate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FarmerCertificateRepository extends AbstractBaseRepository<FarmerCertificate, UUID>{

    List<FarmerCertificate> getFarmerCertificatesByFarmerId(UUID farmerId);
    Optional<FarmerCertificate> getFarmerCertificatesByFarmerIdAndId(UUID farmerId, UUID farmerCertificateId);
}
