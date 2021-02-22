package flowcontrol.gateway.event;

import flowcontrol.gateway.model.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
import org.springframework.web.util.UriComponentsBuilder;

@Getter
@Setter
public class OnUserRegistrationCompleteEvent extends ApplicationEvent {
    private transient UriComponentsBuilder redirectUrl;
    private User user;

    public OnUserRegistrationCompleteEvent(User user, UriComponentsBuilder redirectUrl){
        super(user);
        this.user = user;
        this.redirectUrl = redirectUrl;
    }
}
