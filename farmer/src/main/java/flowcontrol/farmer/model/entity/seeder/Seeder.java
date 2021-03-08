package flowcontrol.farmer.model.entity.seeder;

import flowcontrol.farmer.model.entity.Farmer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class Seeder {
    @Bean
    CommandLineRunner commandLineRunner(
           FarmerSeeder farmerSeeder
    ) {
        return arts -> {
            // Farmer
            Set<Farmer> farmers = farmerSeeder.run();
        };
    }
}
