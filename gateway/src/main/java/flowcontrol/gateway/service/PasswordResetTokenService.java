package flowcontrol.gateway.service;

import flowcontrol.gateway.exception.TokenInvalidRequestException;
import flowcontrol.gateway.model.entity.PasswordResetToken;
import flowcontrol.gateway.repository.PasswordResetTokenRepository;
import flowcontrol.gateway.util.Uuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class PasswordResetTokenService {

    private final PasswordResetTokenRepository passwordResetTokenRepository;

    @Value("${app.token.password.reset.duration}")
    private Long expiration;

    @Autowired
    public PasswordResetTokenService(PasswordResetTokenRepository passwordResetTokenRepository) {
        this.passwordResetTokenRepository = passwordResetTokenRepository;
    }

    public PasswordResetToken save(PasswordResetToken passwordResetToken){
        return passwordResetTokenRepository.save(passwordResetToken);
    }

    public Optional<PasswordResetToken> findByToken(String token){
        return passwordResetTokenRepository.findByToken(token);
    }

    public PasswordResetToken createToken(){
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        String token = Uuid.generateRandomUuid();
        passwordResetToken.setToken(token);
        passwordResetToken.setExpiryDate(Instant.now().plusMillis(expiration));
        return passwordResetToken;
    }

    public void verifyExpiration(PasswordResetToken token){
        if(token.getExpiryDate().compareTo(Instant.now()) < 0 ){
            throw new TokenInvalidRequestException("Password Reset Token", token.getToken(), "Expired token. Please send new request");
        }
    }
}
