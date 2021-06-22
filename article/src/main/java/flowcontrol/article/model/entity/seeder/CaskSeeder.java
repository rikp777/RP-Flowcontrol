package flowcontrol.article.model.entity.seeder;

import com.google.common.collect.Sets;
import flowcontrol.article.exception.ResourceNotFoundException;
import flowcontrol.article.model.entity.Article;
import flowcontrol.article.model.entity.Cask;
import flowcontrol.article.model.entity.Color;
import flowcontrol.article.repository.CaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
@Slf4j
public class CaskSeeder {

    @Autowired
    private final CaskRepository caskRepo;
    private int id = 0;

    public CaskSeeder(CaskRepository caskRepo) {
        this.caskRepo = caskRepo;
    }

    private Color findColorInSet(Set<Color> colors, String color){
        return colors.stream()
                .filter(colorItem ->
                        colorItem.getName().equals(color)).findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Color", "Name", color));
    }

    private void message(Cask cask){
        boolean debug = true;
        if(debug)
            log.info("Cask seeder insert: " + this.id++ + " - " + cask.getExcelCode());
    }

    public Set<Cask> run(UtilSeeder util) {
        boolean seed = true;
        if(seed) {

            Color brown = util.findColorInSet("Brown");
            Color transparent = util.findColorInSet("Transparent");
            Color styro = util.findColorInSet("Styro");
            Color blue = util.findColorInSet("Blue");
            Color green = util.findColorInSet("Green");
            Color black = util.findColorInSet("Black");

            log.info("Cask seeding starting...");

            //region Multi
            if (caskRepo.findByExcelCode("EM0001XX").isEmpty()) {
                Cask cask = new Cask();
                cask.setExcelCode("EM0001XX");
                cask.setName("Emballage multi");
                cask.setDescription("Emballage multi");
                cask.setWeight(null);
                cask.setMaxFillingQuantity(null);
                cask.setMaterial("Wood");
                cask.setColor(brown);
                caskRepo.save(cask);

                this.message(cask);
            }

            if (caskRepo.findByExcelCode("EM0002XX").isEmpty()) {
                Cask cask = new Cask();
                cask.setExcelCode("EM0002XX");
                cask.setName("Emballage industrie");
                cask.setDescription("Emballage industrie 10KG");
                cask.setWeight(10000);
                cask.setMaxFillingQuantity(null);
                cask.setMaterial("Wood");
                cask.setColor(brown);
                caskRepo.save(cask);

                this.message(cask);
            }

            if (caskRepo.findByExcelCode("EM0004XX").isEmpty()) {
                Cask cask = new Cask();
                cask.setExcelCode("EM0004XX");
                cask.setName("Emballage semi-multi");
                cask.setDescription("Emballage semi-multi");
                cask.setWeight(null);
                cask.setMaxFillingQuantity(null);
                cask.setMaterial("Wood");
                cask.setColor(brown);
                caskRepo.save(cask);

                this.message(cask);
            }
            
            if (caskRepo.findByExcelCode("EM0009XX").isEmpty()) {
                Cask cask = new Cask();
                cask.setExcelCode("EM0009XX");
                cask.setName("Emballage doos CHEF");
                cask.setDescription("Emballage doos CHEF");
                cask.setWeight(null);
                cask.setMaxFillingQuantity(null);
                cask.setMaterial("Karton");
                cask.setColor(brown);
                caskRepo.save(cask);

                this.message(cask);
            }
            //endregion

            //region Actie
            if (caskRepo.findByExcelCode("EM0009XX").isEmpty()) {
                Cask cask = new Cask();
                cask.setExcelCode("EM0009XX");
                cask.setName("Doos");
                cask.setDescription("Doos");
                cask.setWeight(null);
                cask.setMaxFillingQuantity(null);
                cask.setMaterial("Karton");
                cask.setColor(brown);
                caskRepo.save(cask);

                this.message(cask);
            }
            //endregion

            //region CH
            if (caskRepo.findByExcelCode("EM0003XX").isEmpty()) {
                Cask cask = new Cask();
                cask.setExcelCode("EM0003XX");
                cask.setName("CH705");
                cask.setDescription("CH705");
                cask.setWeight(null);
                cask.setMaxFillingQuantity(640);
                cask.setMaterial("Plastic");
                cask.setColor(styro);
                caskRepo.save(cask);

                this.message(cask);
            }

            if (caskRepo.findByExcelCode("EM0004XX").isEmpty()) {
                Cask cask = new Cask();
                cask.setExcelCode("EM0004XX");
                cask.setName("CH052");
                cask.setDescription("CH052");
                cask.setWeight(null);
                cask.setMaxFillingQuantity(770);
                cask.setMaterial("Plastic");
                cask.setColor(styro);
                caskRepo.save(cask);

                this.message(cask);
            }

            if (caskRepo.findByExcelCode("EM0005XX").isEmpty()) {
                Cask cask = new Cask();
                cask.setExcelCode("EM0005XX");
                cask.setName("CH050");
                cask.setDescription("CH050");
                cask.setWeight(null);
                cask.setMaxFillingQuantity(756);
                cask.setMaterial("Pet");
                cask.setColor(blue);
                caskRepo.save(cask);

                this.message(cask);
            }

            if (caskRepo.findByExcelCode("EM0008XX").isEmpty()) {
                Cask cask = new Cask();
                cask.setExcelCode("EM0008XX");
                cask.setName("CH050");
                cask.setDescription("CH050");
                cask.setWeight(null);
                cask.setMaxFillingQuantity(770);
                cask.setMaterial("Plastic");
                cask.setColor(blue);
                caskRepo.save(cask);

                this.message(cask);
            }

            if (caskRepo.findByExcelCode("EM0006XX").isEmpty()) {
                Cask cask = new Cask();
                cask.setExcelCode("EM0006XX");
                cask.setName("CH051");
                cask.setDescription("CH051");
                cask.setWeight(null);
                cask.setMaxFillingQuantity(770);
                cask.setMaterial("Plastic");
                cask.setColor(green);
                caskRepo.save(cask);

                this.message(cask);
            }

            if (caskRepo.findByExcelCode("EM0007XX").isEmpty()) {
                Cask cask = new Cask();
                cask.setExcelCode("EM0007XX");
                cask.setName("CH051");
                cask.setDescription("CH051");
                cask.setWeight(null);
                cask.setMaxFillingQuantity(770);
                cask.setMaterial("Plastic");
                cask.setColor(black);
                caskRepo.save(cask);

                this.message(cask);
            }

            if (caskRepo.findByExcelCode("EM0008XX").isEmpty()) {
                Cask cask = new Cask();
                cask.setExcelCode("EM0008XX");
                cask.setName("CH190");
                cask.setDescription("CH190");
                cask.setWeight(null);
                cask.setMaxFillingQuantity(640);
                cask.setMaterial("Pet");
                cask.setColor(transparent);
                caskRepo.save(cask);

                this.message(cask);
            }

            if (caskRepo.findByExcelCode("EM0009XX").isEmpty()) {
                Cask cask = new Cask();
                cask.setExcelCode("EM0009XX");
                cask.setName("CH093");
                cask.setDescription("CH093");
                cask.setWeight(null);
                cask.setMaxFillingQuantity(700);
                cask.setMaterial("Pet");
                cask.setColor(transparent);
                caskRepo.save(cask);

                this.message(cask);
            }

            if (caskRepo.findByExcelCode("EM0010XX").isEmpty()) {
                Cask cask = new Cask();
                cask.setExcelCode("EM0010XX");
                cask.setName("M6 Holland Crates");
                cask.setDescription("M6 Holland Crates");
                cask.setWeight(null);
                cask.setMaxFillingQuantity(700);
                cask.setMaterial("Wood");
                cask.setColor(brown);
                caskRepo.save(cask);

                this.message(cask);
            }

            log.info("Cask seeding done, seeded: " +  this.id + " casks.");
        }else {
            log.info("Cask seeding not required");
        }
        return Sets.newHashSet(caskRepo.findAll());
    }
}

