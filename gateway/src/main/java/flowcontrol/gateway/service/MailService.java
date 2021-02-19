package flowcontrol.gateway.service;

import flowcontrol.gateway.model.general.Mail;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;


@Service
@AllArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;

    private final Configuration configurationTemplate;

    @Value("${app.velocity.template.location}")
    private String basePackagePath;

    @Value("${spring.mail.username}")
    private String mailFrom;

    @Value("{app.token.password.reset.duration}")
    private Long expiration;

    public void sendEmailVerification(String emailVerificationUrl, String to) throws MessagingException, TemplateException, IOException {
        Mail mail = new Mail();
        mail.setSubject("Email Verification [ Flowcontrol ]");
        mail.setTo(to);
        mail.setFrom(this.mailFrom);
        mail.getModel().put("userName", to);
        mail.getModel().put("userEmailTokenVerificationLink", emailVerificationUrl);

        configurationTemplate.setClassForTemplateLoading(getClass(), basePackagePath);
        Template template = configurationTemplate.getTemplate("email-verification.ft1");
        String mailContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, mail.getModel());
        mail.setContent(mailContent);
        this.send(mail);
    }

    public void sendResetLink(String resetPasswordLink, String to) throws TemplateException, IOException, MessagingException {
        Long expirationInMinutes = TimeUnit.MILLISECONDS.toMinutes(expiration);
        String expirationInMinutesString = expirationInMinutes.toString();

        Mail mail = new Mail();
        mail.setSubject("Password Reset Link [ Flowcontrol ]");
        mail.setTo(to);
        mail.setFrom(mailFrom);
        mail.getModel().put("userName", to);
        mail.getModel().put("userResetPasswordLink", resetPasswordLink);
        mail.getModel().put("expirationTime", expirationInMinutesString);

        configurationTemplate.setClassForTemplateLoading(getClass(), basePackagePath);
        Template template = configurationTemplate.getTemplate("reset-link.ft1");
        String mailContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, mail.getModel());
        mail.setContent(mailContent);
        this.send(mail);
    }

    public void sendAccountChangeEmail(String action, String actionStatus, String to) throws TemplateException, IOException, MessagingException {
        Mail mail = new Mail();
        mail.setSubject("Account Status Change [ Flowcontrol ]");
        mail.setTo(to);
        mail.setFrom(mailFrom);
        mail.getModel().put("userName", to);
        mail.getModel().put("action", action);
        mail.getModel().put("actionStatus", actionStatus);

        configurationTemplate.setClassForTemplateLoading(getClass(), basePackagePath);
        Template template = configurationTemplate.getTemplate("account-activity-change.ft1");
        String mailContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, mail.getModel());
        mail.setContent(mailContent);
        this.send(mail);
    }



    public void send(Mail mail) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        helper.setTo(mail.getTo());
        helper.setText(mail.getContent(),true);
        helper.setSubject(mail.getSubject());
        helper.setFrom(mail.getFrom());
        mailSender.send(message);
    }
}
