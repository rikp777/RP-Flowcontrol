package flowcontrol.gateway.event;

import flowcontrol.gateway.model.entity.User;
import flowcontrol.gateway.model.general.EmailVerificationToken;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
import org.springframework.web.util.UriComponentsBuilder;

@Getter
@Setter
public class OnRegenerateEmailVerificationEvent extends ApplicationEvent {

    private transient UriComponentsBuilder redirectUrl;
    private User user;
    private transient EmailVerificationToken token;

    public OnRegenerateEmailVerificationEvent(User user, UriComponentsBuilder redirectUrl, EmailVerificationToken token){
        super(user);
        this.user = user;
        this.redirectUrl = redirectUrl;
        this.token = token;
    }
}
