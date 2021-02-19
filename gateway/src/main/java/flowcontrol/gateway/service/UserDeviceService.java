package flowcontrol.gateway.service;

import flowcontrol.gateway.exception.TokenRefreshException;
import flowcontrol.gateway.model.entity.RefreshToken;
import flowcontrol.gateway.model.entity.UserDevice;
import flowcontrol.gateway.model.request.DeviceInfoRequest;
import flowcontrol.gateway.repository.UserDeviceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDeviceService {

    private final UserDeviceRepository userDeviceRepository;

    public Optional<UserDevice> findByUserId(long userId){
        return userDeviceRepository.findByUserId(userId);
    }

    public Optional<UserDevice> findByRefreshToken(RefreshToken refreshToken){
        return userDeviceRepository.findByRefreshToken(refreshToken);
    }

    public UserDevice createUserDevice(DeviceInfoRequest deviceInfo){
        UserDevice newUserDevice = new UserDevice();
        newUserDevice.setDeviceId(deviceInfo.getDeviceId());
        newUserDevice.setDeviceType(deviceInfo.getDeviceType());
        newUserDevice.setNotificationToken(deviceInfo.getNotificationToken());
        newUserDevice.setIsRefreshActive(true);
        return newUserDevice;
    }


    private void verifyRefreshAvailability(RefreshToken refreshToken){
        UserDevice userDevice = findByRefreshToken(refreshToken).orElseThrow(() -> new TokenRefreshException(refreshToken.getToken(), "No device found for the matching token. Please login again"));

        if(!userDevice.getIsRefreshActive()){
            throw new TokenRefreshException(refreshToken.getToken(), "Refresh blocked for the device. Please login through a different device");
        }
    }
}
