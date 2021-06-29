package flowcontrol.transport.repository;

import flowcontrol.transport.model.entity.PalletLabel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PalletLabelRepository extends JpaRepository<PalletLabel, UUID> {
    Optional<PalletLabel> findByGeneralId(Long id);
}
