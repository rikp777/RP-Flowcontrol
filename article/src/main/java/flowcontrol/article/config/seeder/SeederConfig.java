package flowcontrol.article.config.seeder;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Data
@NoArgsConstructor
@Component
public class SeederConfig {

    @Value("${seeder.debug:true}")
    private boolean inDebugMode;

    @Value("${seeder.insert-data:true}")
    private boolean inInsetDataMode;
}