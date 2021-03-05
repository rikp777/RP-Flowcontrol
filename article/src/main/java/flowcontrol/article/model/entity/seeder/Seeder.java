package flowcontrol.article.model.entity.seeder;

import flowcontrol.article.model.entity.Color;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class Seeder {
    @Bean
    CommandLineRunner commandLineRunner(
            CaskSeeder caskSeeder,
            GroupSeeder groupSeeder,
            TypeSeeder typeSeeder,
            ColorSeeder colorSeeder
    ) {
        return arts -> {
            Set<Color> colors = colorSeeder.run();
            caskSeeder.run(colors);
            groupSeeder.run();
            typeSeeder.run();

        };
    }
}
