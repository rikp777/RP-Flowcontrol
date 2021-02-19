package flowcontrol.gateway.security;

import flowcontrol.gateway.model.entity.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.time.Instant;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final String jwtSecret;
    private final long jwtExpirationInMs;

    public JwtTokenProvider(@Value("${app.jwt.secret}") String jwtSecret, @Value("${app.jwt.expiration") long jwtExpirationInMs){
        this.jwtSecret = jwtSecret;
        this.jwtExpirationInMs = jwtExpirationInMs;
    }

    public String generateToken(CustomUserDetails customUserDetails){
        Instant expiryDate = Instant.now().plusMillis(jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(Long.toString(customUserDetails.getId()))
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(expiryDate))
                .signWith(SignatureAlgorithm.ES512, jwtSecret)
                .compact();
    }

    public String generateTokenFromUserId(Long userId){
        Instant expiryDate = Instant.now().plusMillis(jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(Long.toString(userId))
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(expiryDate))
                .signWith(SignatureAlgorithm.ES512, jwtSecret)
                .compact();
    }

    public Long getUserIdFromJwt(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public Date getTokenExpiryFromJwt(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getExpiration();
    }

    public long getJwtExpirationInMs(){
        return this.jwtExpirationInMs;
    }
}
