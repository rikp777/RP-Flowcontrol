package flowcontrol.article.model.entity.seeder;

import flowcontrol.article.model.entity.Color;
import flowcontrol.article.repository.CaskRepository;
import flowcontrol.article.repository.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class ColorSeeder {

    @Autowired
    private final ColorRepository colorRepo;

    public ColorSeeder(ColorRepository colorRepo) {
        this.colorRepo = colorRepo;
    }

    public Set<Color> run() {
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

        Color gray = new Color();
        gray.setName("Gray");
        colorRepo.save(gray);

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

        return new HashSet<>(colorRepo.findAll());
    }
}
