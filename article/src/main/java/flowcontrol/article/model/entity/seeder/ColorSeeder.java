package flowcontrol.article.model.entity.seeder;

import com.google.common.collect.Sets;
import flowcontrol.article.model.entity.Color;
import flowcontrol.article.repository.CaskRepository;
import flowcontrol.article.repository.ColorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
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
            Color brown = new Color();
            brown.setName("Brown");
            colorRepo.save(brown);

            Color white = new Color();
            white.setName("White");
            colorRepo.save(white);

            Color pink = new Color();
            pink.setName("Pink");
            colorRepo.save(pink);

            Color yellow = new Color();
            yellow.setName("Yellow");
            colorRepo.save(yellow);

            Color grey = new Color();
            grey.setName("Grey");
            colorRepo.save(grey);

            Color styro = new Color();
            styro.setName("Styro");
            colorRepo.save(styro);

            Color blue = new Color();
            blue.setName("Blue");
            colorRepo.save(blue);

            Color green = new Color();
            green.setName("Green");
            colorRepo.save(green);

            Color black = new Color();
            black.setName("Black");
            colorRepo.save(black);

            Color transparent = new Color();
            transparent.setName("Transparent");
            colorRepo.save(transparent);

            log.info("Colors done seeding");
        }
        return Sets.newHashSet(colorRepo.findAll());
    }
}
