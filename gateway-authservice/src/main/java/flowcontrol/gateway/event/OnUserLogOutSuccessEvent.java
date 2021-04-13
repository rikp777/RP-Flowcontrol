package flowcontrol.gateway.event;

import flowcontrol.gateway.model.request.LogOutRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
public class OnUserLogOutSuccessEvent extends ApplicationEvent {

    private final String userEmail;
    private final String token;
    private final transient LogOutRequest logOutRequest;
    private final Date evenTime;

    public OnUserLogOutSuccessEvent(String userEmail, String token, LogOutRequest logOutRequest) {
        super(userEmail);
        this.userEmail = userEmail;
        this.token = token;
        this.logOutRequest = logOutRequest;
        this.evenTime = Date.from(Instant.now());
    }
}
