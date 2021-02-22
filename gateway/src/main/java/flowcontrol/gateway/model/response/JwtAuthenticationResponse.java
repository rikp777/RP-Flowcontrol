package flowcontrol.gateway.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAuthenticationResponse {

    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private Long expiryDuration;

    public JwtAuthenticationResponse(String accessToken, String refreshToken, Long expiryDuration) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiryDuration = expiryDuration;
        tokenType = "Bearer ";
    }
}
