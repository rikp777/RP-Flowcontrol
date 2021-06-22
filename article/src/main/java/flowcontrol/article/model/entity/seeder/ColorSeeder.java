package flowcontrol.article.model.entity.seeder;

import com.google.common.collect.Sets;
import flowcontrol.article.model.entity.Cask;
import flowcontrol.article.model.entity.Color;
import flowcontrol.article.repository.ColorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
@Slf4j
public class ColorSeeder {
    private int id = 0;

    @Autowired
    private final ColorRepository colorRepo;

    public ColorSeeder(ColorRepository colorRepo) {
        this.colorRepo = colorRepo;
    }

    private void message(Color color){
        boolean debug = true;
        if(debug)
            log.info("Color seeder insert: " + this.id++ + " - " + color.getName());
    }

    public Set<Color> run() {
        boolean seed = true;
        if(seed) {
            log.info("Color seeding starting...");

            if (colorRepo.findByName("Brown").isEmpty()) {
                var color = new Color();
                color.setName("Brown");
                colorRepo.save(color);

                this.message(color);
            }

            if (colorRepo.findByName("White").isEmpty()) {
                var color = new Color();
                color.setName("White");
                colorRepo.save(color);

                this.message(color);
            }

            if (colorRepo.findByName("Pink").isEmpty()) {
                var color = new Color();
                color.setName("Pink");
                colorRepo.save(color);

                this.message(color);
            }

            if (colorRepo.findByName("Yellow").isEmpty()) {
                var color = new Color();
                color.setName("Yellow");
                colorRepo.save(color);

                this.message(color);
            }

            if (colorRepo.findByName("Green").isEmpty()) {
                var color = new Color();
                color.setName("Green");
                colorRepo.save(color);

                this.message(color);
            }

            if (colorRepo.findByName("Grey").isEmpty()) {
                var color = new Color();
                color.setName("Grey");
                colorRepo.save(color);

                this.message(color);
            }

            if (colorRepo.findByName("Styro").isEmpty()) {
                var color = new Color();
                color.setName("Styro");
                colorRepo.save(color);

                this.message(color);
            }

            if (colorRepo.findByName("Blue").isEmpty()) {
                var color = new Color();
                color.setName("Blue");
                colorRepo.save(color);

                this.message(color);
            }

            if (colorRepo.findByName("Blue").isEmpty()) {
                var color = new Color();
                color.setName("Blue");
                colorRepo.save(color);

                this.message(color);
            }

            if (colorRepo.findByName("Black").isEmpty()) {
                var color = new Color();
                color.setName("Black");
                colorRepo.save(color);

                this.message(color);
            }

            if (colorRepo.findByName("Transparent").isEmpty()) {
                var color = new Color();
                color.setName("Transparent");
                colorRepo.save(color);

                this.message(color);
            }

            log.info("Color seeding done, seeded: " +  this.id + " colors.");
        }else {
            log.info("Color seeding not required");
        }
        return Sets.newHashSet(colorRepo.findAll());
    }
}
