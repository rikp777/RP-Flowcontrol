package flowcontrol.farmer.security;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
public class JwtConfig {

    @Value("${security.jwt.uri:/auth/**}")
    private String Uri;

    @Value("${security.jwt.header:Authorization}")
    private String header;

    @Value("${security.jwt.prefix:Bearer }")
    private String prefix;

    @Value("${security.jwt.expiration:#{86400}}")
    private int expiration;

    @Value("${security.jwt.secret:!1fl0wC0NtrOlS3crEt$uP3rseCreTV3ryLong}")
    private String secret;
}

