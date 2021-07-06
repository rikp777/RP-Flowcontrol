package flowcontrol.transport.repository;

import flowcontrol.transport.model.entity.PalletLabel;
import flowcontrol.transport.model.entity.ShippingLabel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShippingLabelRepository extends JpaRepository<ShippingLabel, UUID> {
}
