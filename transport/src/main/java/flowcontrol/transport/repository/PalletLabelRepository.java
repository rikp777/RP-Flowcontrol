package flowcontrol.transport.repository;

import flowcontrol.transport.model.entity.PalletLabel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PalletLabelRepository extends JpaRepository<PalletLabel, Long> {
}
