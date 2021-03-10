package flowcontrol.transport.repository;

import flowcontrol.transport.model.entity.PalletLabel;
import flowcontrol.transport.model.entity.ShippingLabel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingLabelRepository extends JpaRepository<ShippingLabel, Long> {
}
