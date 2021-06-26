package flowcontrol.article.model.entity.seeder;

import com.google.common.collect.Sets;
import flowcontrol.article.model.entity.Cask;
import flowcontrol.article.model.entity.Color;
import flowcontrol.article.repository.ColorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Configuration
@Slf4j
public class ColorSeeder {
    private int id = 1;

    @Autowired
    private final ColorRepository colorRepo;

    public ColorSeeder(ColorRepository colorRepo) {
        this.colorRepo = colorRepo;
    }

    private void message(Color color){
        boolean debug = true;
        if(debug)
            log.info("Color seeder insert: " + this.id++ + " - " + color.getName()+ " | " +
                    "UUID: " + color.getId()
            );
    }

    public Set<Color> run() {
        boolean seed = true;
        if(seed) {
            log.info("Color seeding starting...");

            if (colorRepo.findByName("Brown").isEmpty()) {
                var color = new Color(UUID.fromString("1d37bb39-0254-45fe-ad7d-b1559095a064"));
                color.setName("Brown");
                color = colorRepo.save(color);

                this.message(color);
            }

            if (colorRepo.findByName("White").isEmpty()) {
                var color = new Color(UUID.fromString("1d56e76f-262f-452d-b08a-63ac71ee733e"));
                color.setName("White");
                colorRepo.save(color);

                this.message(color);
            }

            if (colorRepo.findByName("Pink").isEmpty()) {
                var color = new Color(UUID.fromString("22e2e806-dbdd-4194-846d-d2bf92082798"));
                color.setName("Pink");
                colorRepo.save(color);

                this.message(color);
            }

            if (colorRepo.findByName("Yellow").isEmpty()) {
                var color = new Color(UUID.fromString("46ca6a81-d937-45e1-aefa-f2166dada50d"));
                color.setName("Yellow");
                colorRepo.save(color);

                this.message(color);
            }

            if (colorRepo.findByName("Green").isEmpty()) {
                var color = new Color(UUID.fromString("affc2627-eb68-4bf5-8de9-6e1cad1ceae7"));
                color.setName("Green");
                colorRepo.save(color);

                this.message(color);
            }

            if (colorRepo.findByName("Grey").isEmpty()) {
                var color = new Color(UUID.fromString("c18288e9-5966-4327-a87d-7ce55051055b"));
                color.setName("Grey");
                colorRepo.save(color);

                this.message(color);
            }

            if (colorRepo.findByName("Styro").isEmpty()) {
                var color = new Color(UUID.fromString("d0248c06-50ba-492f-bcff-4dfc48689f05"));
                color.setName("Styro");
                colorRepo.save(color);

                this.message(color);
            }

            if (colorRepo.findByName("Blue").isEmpty()) {
                var color = new Color(UUID.fromString("d4e7919c-b999-43c7-9fd7-b4e56c491b1d"));
                color.setName("Blue");
                colorRepo.save(color);

                this.message(color);
            }

            if (colorRepo.findByName("Blue").isEmpty()) {
                var color = new Color(UUID.fromString("d6cc8062-349c-409d-9125-3c8291fb7efc"));
                color.setName("Blue");
                colorRepo.save(color);

                this.message(color);
            }

            if (colorRepo.findByName("Black").isEmpty()) {
                var color = new Color(UUID.fromString("fabf025b-4905-45e6-9adc-511b81f6dd4f"));
                color.setName("Black");
                colorRepo.save(color);

                this.message(color);
            }

            if (colorRepo.findByName("Transparent").isEmpty()) {
                var color = new Color(UUID.fromString("fabf025b-8766-45e6-9adc-511b84f6dd4f"));
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
