package flowcontrol.production.model.entity.seeder;

import flowcontrol.production.model.entity.Line;
import flowcontrol.production.model.entity.Ticket;
import flowcontrol.production.repository.LineRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class LineSeeder {

    @Bean
    CommandLineRunner commandLineRunner(LineRepository repository){
        return arts -> {
            Line lineOne = new Line();
            lineOne.setName("Line One");
            lineOne.setDescription("Line one next to line three");
            repository.save(lineOne);

            Line lineTwo = new Line();
            lineTwo.setName("Line Two");
            lineTwo.setDescription("Line two next to line three");
            repository.save(lineTwo);


            Line lineThree = new Line();
            lineThree.setName("Line Three");
            lineThree.setDescription("Line three next to line one");
            repository.save(lineThree);
        };
    }
}
