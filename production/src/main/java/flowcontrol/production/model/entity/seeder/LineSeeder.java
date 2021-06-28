package flowcontrol.production.model.entity.seeder;

import flowcontrol.production.config.seeder.SeederConfig;
import flowcontrol.production.model.entity.Line;
import flowcontrol.production.repository.LineRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
@Slf4j
public class LineSeeder {

    private final LineRepository lineRepository;
    private SeederConfig seederConfig;

    private int id = 1;

    public LineSeeder(LineRepository lineRepository, SeederConfig seederConfig) {
        this.lineRepository = lineRepository;
        this.seederConfig = seederConfig;
    }

    private void message(Line line){
        if(this.seederConfig.isInDebugMode())
            UtilSeeder.sendMessage("Line seeder", this.id, line.getName(), line.getId());
        this.id++;
    }

    public void run() {
        if (this.seederConfig.isInInsetDataMode()) {

            if(lineRepository.findByName("Line One").isEmpty()){
                Line line = new Line(UUID.fromString("55258c47-c7d9-4872-ac5a-c9592439037a"));
                line.setName("Line One");
                line.setDescription("Line one next to line three");
                lineRepository.save(line);
            }

            if(lineRepository.findByName("Line Two").isEmpty()) {
                Line line = new Line(UUID.fromString("ab217358-ca7d-41bc-9385-db2effc51844"));
                line.setName("Line Two");
                line.setDescription("Line two next to line three");
                lineRepository.save(line);
            }

            if(lineRepository.findByName("Line Three").isEmpty()) {
                Line line = new Line(UUID.fromString("e2966341-c0f5-4bd3-8efa-1a36fe1a3e7c"));
                line.setName("Line Three");
                line.setDescription("Line three next to line one");
                lineRepository.save(line);
            }

            log.info("Line seeding done, seeded: " + (this.id - 1) + " lines.");
        } else {
            log.info("Line seeding not required");
        }
    }
}
