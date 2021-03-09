package flowcontrol.farmer.repository;

import flowcontrol.farmer.model.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {
}
