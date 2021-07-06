package flowcontrol.gateway.repository;

import flowcontrol.gateway.model.entity.RefreshToken;
import flowcontrol.gateway.model.entity.UserDevice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserDeviceRepository extends JpaRepository<UserDevice, UUID> {

    Optional<UserDevice> findById(UUID id);
    Optional<UserDevice> findByRefreshToken(RefreshToken refreshToken);
    Optional<UserDevice> findByUserId(UUID userId);


}
