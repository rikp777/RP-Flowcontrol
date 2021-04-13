package flowcontrol.gateway.event.listener;

import flowcontrol.gateway.event.OnUserLogOutSuccessEvent;
import flowcontrol.gateway.model.request.DeviceInfoRequest;
import flowcontrol.gateway.security.chache.LoggedOutJwtTokenCache;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class OnUserLogoutSuccessEventListener implements ApplicationListener<OnUserLogOutSuccessEvent> {

    private final LoggedOutJwtTokenCache tokenCache;

    public void onApplicationEvent(OnUserLogOutSuccessEvent event) {
        if (null != event) {
            DeviceInfoRequest deviceInfo = event.getLogOutRequest().getDeviceInfo();
            log.info(String.format("Log out success event received for user [%s] for device [%s]", event.getUserEmail(), deviceInfo));
            tokenCache.markLogOutEventForToken(event);
        }
    }
}
