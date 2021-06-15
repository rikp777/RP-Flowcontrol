package flowcontrol.farmer.repository;

import flowcontrol.farmer.model.entity.Certificate;
import flowcontrol.farmer.model.entity.FarmerCertificate;

import java.util.List;
import java.util.Optional;

public interface FarmerCertificateRepository extends AbstractBaseRepository<FarmerCertificate, Long>{

    List<FarmerCertificate> getFarmerCertificatesByFarmerId(Long farmerId);
    Optional<FarmerCertificate> getFarmerCertificatesByFarmerIdAndId(Long farmerId, Long farmerCertificateId);
}
