package flowcontrol.production.model.entity.seeder;

import flowcontrol.production.model.entity.Line;
import flowcontrol.production.repository.LineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LineSeeder {

    @Autowired
    private final LineRepository lineRepository;

    public LineSeeder(LineRepository lineRepository) {
        this.lineRepository = lineRepository;
    }

    public void run(){
        Line lineOne = new Line();
        lineOne.setName("Line One");
        lineOne.setDescription("Line one next to line three");
        lineRepository.save(lineOne);

        Line lineTwo = new Line();
        lineTwo.setName("Line Two");
        lineTwo.setDescription("Line two next to line three");
        lineRepository.save(lineTwo);


        Line lineThree = new Line();
        lineThree.setName("Line Three");
        lineThree.setDescription("Line three next to line one");
        lineRepository.save(lineThree);
    }
}
