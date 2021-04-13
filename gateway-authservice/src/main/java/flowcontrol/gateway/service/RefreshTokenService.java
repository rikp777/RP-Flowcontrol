package flowcontrol.gateway.service;

import flowcontrol.gateway.exception.TokenRefreshException;
import flowcontrol.gateway.model.entity.RefreshToken;
import flowcontrol.gateway.repository.RefreshTokenRepository;
import flowcontrol.gateway.util.Uuid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${app.token.refresh.duration}")
    private Long refreshTokenDurationMs;

    @Autowired
    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public Optional<RefreshToken> findByToken(String token){
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken save(RefreshToken refreshToken){
        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken createRefreshToken(){
        RefreshToken newRefreshToken = new RefreshToken();
        newRefreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        newRefreshToken.setToken(Uuid.generateRandomUuid());
        newRefreshToken.setRefreshCount(0L);
        return newRefreshToken;
    }

    public void verifyExpiration(RefreshToken refreshToken){
        if(refreshToken.getExpiryDate().compareTo(Instant.now()) < 0){
            throw new TokenRefreshException(refreshToken.getToken(), "Expired token. Please send a new request");
        }
    }

    public void deleteById(Long id){
        refreshTokenRepository.deleteById(id);
    }

    public void increaseCount(RefreshToken refreshToken){
        refreshToken.incrementRefreshCount();
        save(refreshToken);
    }
}
