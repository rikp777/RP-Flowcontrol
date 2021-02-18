package flowcontrol.production.model.entity.seeder;

import flowcontrol.production.repository.LineRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Seeder {
    @Bean
    CommandLineRunner commandLineRunner(
            LineSeeder lineSeeder,
            InterruptionReasonSeeder interruptionReasonSeeder
    ) {
        return arts -> {
            lineSeeder.run();
            interruptionReasonSeeder.run();
        };
    }
}
