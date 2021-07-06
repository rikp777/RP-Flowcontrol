package flowcontrol.farmer.repository;

import flowcontrol.farmer.model.entity.Certificate;
import flowcontrol.farmer.model.entity.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CertificateRepository extends AbstractBaseRepository<Certificate, UUID> {
}
