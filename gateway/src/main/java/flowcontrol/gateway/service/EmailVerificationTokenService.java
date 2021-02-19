package flowcontrol.gateway.service;

import flowcontrol.gateway.exception.TokenInvalidRequestException;
import flowcontrol.gateway.model.entity.User;
import flowcontrol.gateway.model.entity.enumeration.TokenStatus;
import flowcontrol.gateway.model.general.EmailVerificationToken;
import flowcontrol.gateway.repository.EmailVerificationTokenRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class EmailVerificationTokenService {

    private final EmailVerificationTokenRepository emailVerificationTokenRepository;

    @Value("${app.token.email.verification.duration}")
    private Long emailVerificationTokenExpiryDuration;

    public void createVerificationToken(User user, String token){
        EmailVerificationToken emailVerificationToken = new EmailVerificationToken();
        emailVerificationToken.setToken(token);
        emailVerificationToken.setTokenStatus(TokenStatus.STATUS_PENDING);
        emailVerificationToken.setUser(user);
        emailVerificationToken.setExpiryDate(Instant.now().plusMillis(emailVerificationTokenExpiryDuration));
        log.info("Generated Email verification token [" + emailVerificationToken + "]");

        emailVerificationTokenRepository.save(emailVerificationToken);
    }

    public EmailVerificationToken updateExistingTokenWithNameAndExpiry(EmailVerificationToken existingToken){
        existingToken.setTokenStatus(TokenStatus.STATUS_PENDING);
        existingToken.setExpiryDate(Instant.now().plusMillis(emailVerificationTokenExpiryDuration));
        log.info("Update Email verification token [" + existingToken + "]");
        return save(existingToken);
    }

    public Optional<EmailVerificationToken> findByToken(String token){
        return emailVerificationTokenRepository.findByToken(token);
    }

    public EmailVerificationToken save(EmailVerificationToken emailVerificationToken){
        return emailVerificationTokenRepository.save(emailVerificationToken);
    }

    public String generateNewToken(){
        return UUID.randomUUID().toString();
    }

    public void verifyExpiration(EmailVerificationToken token){
        if(token.getExpiryDate().compareTo(Instant.now()) < 0 ){
            throw new TokenInvalidRequestException("Email Verification Token", token.getToken(), "Expired token. Please send a new request");
        }
    }
}
