package flowcontrol.gateway.repository;

import flowcontrol.gateway.model.entity.RefreshToken;
import flowcontrol.gateway.model.entity.UserDevice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDeviceRepository extends JpaRepository<UserDevice, Long> {

    Optional<UserDevice> findById(long id);
    Optional<UserDevice> findByRefreshToken(RefreshToken refreshToken);
    Optional<UserDevice> findByUserId(Long userId);


}
