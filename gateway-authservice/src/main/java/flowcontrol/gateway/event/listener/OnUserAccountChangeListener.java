package flowcontrol.gateway.event.listener;

import flowcontrol.gateway.event.OnUserAccountChangeEvent;
import flowcontrol.gateway.exception.MailSendException;
import flowcontrol.gateway.model.entity.User;
import flowcontrol.gateway.service.MailService;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.IOException;

@Component
@Slf4j
@AllArgsConstructor
public class OnUserAccountChangeListener implements ApplicationListener<OnUserAccountChangeEvent> {

    private final MailService mailService;

    /**
     * As soon as a registration event is complete, invoke the email verification
     * asynchronously in an another thread pool
     */
    @Override
    @Async
    public void onApplicationEvent(OnUserAccountChangeEvent onUserAccountChangeEvent) {
        sendAccountChangeEmail(onUserAccountChangeEvent);
    }

    /**
     * Send email verification to the user and persist the token in the database.
     */
    private void sendAccountChangeEmail(OnUserAccountChangeEvent event) {
        User user = event.getUser();
        String action = event.getAction();
        String actionStatus = event.getActionStatus();
        String recipientAddress = user.getEmail();

        try {
            mailService.sendAccountChangeEmail(action, actionStatus, recipientAddress);
        } catch (IOException | TemplateException | MessagingException e) {
            log.error(String.valueOf(e));
            throw new MailSendException(recipientAddress, "Account Change Mail");
        }
    }
}

