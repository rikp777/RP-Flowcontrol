package flowcontrol.article.model.entity.seeder;

import com.google.common.collect.Sets;
import flowcontrol.article.model.entity.Color;
import flowcontrol.article.repository.ColorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
@Slf4j
public class ColorSeeder {

    @Autowired
    private final ColorRepository colorRepo;

    public ColorSeeder(ColorRepository colorRepo) {
        this.colorRepo = colorRepo;
    }

    public Set<Color> run() {
        if(!colorRepo.findById(1L).isPresent()) {
            var brown = new Color();
            brown.setName("Brown");
            colorRepo.save(brown);

            var white = new Color();
            white.setName("White");
            colorRepo.save(white);

            var pink = new Color();
            pink.setName("Pink");
            colorRepo.save(pink);

            var yellow = new Color();
            yellow.setName("Yellow");
            colorRepo.save(yellow);

            var grey = new Color();
            grey.setName("Grey");
            colorRepo.save(grey);

            var styro = new Color();
            styro.setName("Styro");
            colorRepo.save(styro);

            var blue = new Color();
            blue.setName("Blue");
            colorRepo.save(blue);

            var green = new Color();
            green.setName("Green");
            colorRepo.save(green);

            var black = new Color();
            black.setName("Black");
            colorRepo.save(black);

            var transparent = new Color();
            transparent.setName("Transparent");
            colorRepo.save(transparent);

            log.info("Colors done seeding");
        }
        return Sets.newHashSet(colorRepo.findAll());
    }
}
