package flowcontrol.gateway.event;

import flowcontrol.gateway.model.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Setter
@Getter
public class OnUserAccountChangeEvent extends ApplicationEvent {

    private User user;
    private String action;
    private String actionStatus;

    public OnUserAccountChangeEvent(User user, String action, String actionStatus){
        super(user);
        this.user = user;
        this.action = action;
        this.actionStatus = actionStatus;
    }
}
