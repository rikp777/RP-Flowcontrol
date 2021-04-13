package flowcontrol.gateway.event;

import flowcontrol.gateway.model.entity.PasswordResetToken;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
import org.springframework.web.util.UriComponentsBuilder;

@Getter
@Setter
public class OnGenerateResetLinkEvent extends ApplicationEvent {

    private transient UriComponentsBuilder redirectUrl;

    private transient PasswordResetToken passwordResetToken;

    public OnGenerateResetLinkEvent(PasswordResetToken passwordResetToken, UriComponentsBuilder redirectUrl){
        super(passwordResetToken);
        this.passwordResetToken = passwordResetToken;
        this.redirectUrl = redirectUrl;
    }
}
