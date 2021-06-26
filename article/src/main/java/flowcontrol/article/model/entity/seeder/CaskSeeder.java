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

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Configuration
@Slf4j
public class CaskSeeder {

    @Autowired
    private final CaskRepository caskRepo;
    private int id = 1;

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
            log.info("Cask seeder insert: " + this.id++ + " - " + cask.getExcelCode() + " | " +
                    "UUID: " + cask.getId()
            );
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
                Cask cask = new Cask(UUID.fromString("7e4efd4-3c0b-4258-8537-41e17263e94c"));
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
                Cask cask = new Cask(UUID.fromString("259d0ecd-40d0-4611-bec7-5bef50de471c"));
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
                Cask cask = new Cask(UUID.fromString("3b74a379-2b0f-4ccb-82e6-73263bca2ead"));
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
                Cask cask = new Cask(UUID.fromString("46fb38a5-32b6-4dc2-9ed5-cc78ab8ab182"));
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
            if (caskRepo.findByExcelCode("EM0010XX").isEmpty()) {
                Cask cask = new Cask(UUID.fromString("6e35c2b1-66f5-4466-a6d1-eee8c31bc28c"));
                cask.setExcelCode("EM0010XX");
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
                Cask cask = new Cask(UUID.fromString("90a17803-51e3-494c-aff7-49fe784dbeb0"));
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

            if (caskRepo.findByExcelCode("EM0088XX").isEmpty()) {
                Cask cask = new Cask(UUID.fromString("96bbb69b-3a99-433d-b0a8-bcab8c136f47"));
                cask.setExcelCode("EM0088XX");
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
                Cask cask = new Cask(UUID.fromString("9df81159-d1f7-4e23-b141-9e2227aba73d"));
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
                Cask cask = new Cask(UUID.fromString("a8e5c7de-0f80-4cd0-b762-cdc24663b682"));
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
                Cask cask = new Cask(UUID.fromString("ad6ad449-ba4e-4bd0-8a5d-dce054623c77"));
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
                Cask cask = new Cask(UUID.fromString("d70be467-641e-4350-a8fb-e4c494ab1ef6"));
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

            if (caskRepo.findByExcelCode("EM0098XX").isEmpty()) {
                Cask cask = new Cask(UUID.fromString("e29b1150-6bec-42e2-b630-2dffca4bdef0"));
                cask.setExcelCode("EM0098XX");
                cask.setName("CH190");
                cask.setDescription("CH190");
                cask.setWeight(null);
                cask.setMaxFillingQuantity(640);
                cask.setMaterial("Pet");
                cask.setColor(transparent);
                caskRepo.save(cask);

                this.message(cask);
            }

            if (caskRepo.findByExcelCode("EM0045XX").isEmpty()) {
                Cask cask = new Cask(UUID.fromString("fa81817d-ebe8-432b-8c53-e9c42f2d8a23"));
                cask.setExcelCode("EM0045XX");
                cask.setName("CH093");
                cask.setDescription("CH093");
                cask.setWeight(null);
                cask.setMaxFillingQuantity(700);
                cask.setMaterial("Pet");
                cask.setColor(transparent);
                caskRepo.save(cask);

                this.message(cask);
            }

            if (caskRepo.findByExcelCode("EM0047XX").isEmpty()) {
                Cask cask = new Cask(UUID.fromString("ff3f39a4-c5d9-45f1-906d-743fb4122adb"));
                cask.setExcelCode("EM0047XX");
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
        Iterable<Cask> caskss = caskRepo.findAll();
        return Sets.newHashSet(caskRepo.findAll());
    }
}

