package flowcontrol.gateway.model.entity.seeder;

import flowcontrol.gateway.model.entity.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class Seeder {
    @Bean
    CommandLineRunner commandLineRunner(
            RoleSeeder roleSeeder
    ) {
        return arts -> {

            // Role
            roleSeeder.run();


        };
    }
}
