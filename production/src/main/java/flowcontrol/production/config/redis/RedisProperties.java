package flowcontrol.production.config.redis;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Data
@NoArgsConstructor
@Component
public class RedisProperties {

    @Value("${spring.redis.hostname}")
    private String hostName;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.password}")
    private String password;
}
