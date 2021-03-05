package flowcontrol.article.model.entity.seeder;

import flowcontrol.article.exception.ResourceNotFoundException;
import flowcontrol.article.model.entity.Cask;
import flowcontrol.article.model.entity.Color;
import flowcontrol.article.repository.CaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class CaskSeeder {

    @Autowired
    private final CaskRepository caskRepo;

    public CaskSeeder(CaskRepository caskRepo) {
        this.caskRepo = caskRepo;
    }

    private Color findColorInSet(Set<Color> colors, String color){
        return colors.stream()
                .filter(colorItem ->
                        colorItem.getName().equals(color)).findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Color", "Name", color));
    }

    public void run(Set<Color> colors){




        // Multi
        Cask emballageMulti = new Cask();
        emballageMulti.setExcelCode("EM0001XX");
        emballageMulti.setName("Emballage multi");
        emballageMulti.setDescription("Emballage multi");
        emballageMulti.setWeight(null);
        emballageMulti.setMaxFillingQuantity(null);
        emballageMulti.setMaterial("Hout");

        emballageMulti.setColor(findColorInSet(colors, "Brown"));
        caskRepo.save(emballageMulti);

        Cask emballageIndustrie = new Cask();
        emballageIndustrie.setExcelCode("EM0002XX");
        emballageIndustrie.setName("Emballage industrie");
        emballageIndustrie.setDescription("Emballage industrie");
        emballageIndustrie.setWeight(10000);
        emballageIndustrie.setMaxFillingQuantity(null);
        emballageIndustrie.setMaterial("Hout");

        emballageMulti.setColor(findColorInSet(colors, "Brown"));
        caskRepo.save(emballageIndustrie);

        Cask emballageSemiMulti = new Cask();
        emballageSemiMulti.setExcelCode("EM0004XX");
        emballageSemiMulti.setName("Emballage semi-multi");
        emballageSemiMulti.setDescription("Emballage semi-multi");
        emballageSemiMulti.setWeight(null);
        emballageSemiMulti.setMaxFillingQuantity(null);
        emballageSemiMulti.setMaterial("Hout");

        emballageMulti.setColor(findColorInSet(colors, "Brown"));
        caskRepo.save(emballageSemiMulti);

        Cask EmballageDoos = new Cask();
        EmballageDoos.setExcelCode("EM0009XX");
        EmballageDoos.setName("Emballage doos CHEF");
        EmballageDoos.setDescription("Emballage doos CHEF");
        EmballageDoos.setWeight(null);
        EmballageDoos.setMaxFillingQuantity(null);
        EmballageDoos.setMaterial("Karton");

        emballageMulti.setColor(findColorInSet(colors, "Brown"));
        caskRepo.save(EmballageDoos);


        // Actie
        Cask doos = new Cask();
        doos.setExcelCode("EM0009XX");
        doos.setName("Doos");
        doos.setDescription("Doos");
        doos.setWeight(null);
        doos.setMaxFillingQuantity(null);
        doos.setMaterial("Karton");
        caskRepo.save(doos);


        // CH
        Cask CH705 = new Cask();
        CH705.setExcelCode("EM0003XX");
        CH705.setName("CH705");
        CH705.setDescription("CH705");
        CH705.setWeight(null);
        CH705.setMaxFillingQuantity(640);
        CH705.setMaterial("Plastic");
        caskRepo.save(CH705);

        Cask CH052 = new Cask();
        CH052.setExcelCode("EM0004XX");
        CH052.setName("CH052");
        CH052.setDescription("CH052");
        CH052.setWeight(null);
        CH052.setMaxFillingQuantity(770);
        CH052.setMaterial("Plastic");
        caskRepo.save(CH052);

        Cask CH050 = new Cask();
        CH050.setExcelCode("EM0005XX");
        CH050.setName("CH050");
        CH050.setDescription("CH050");
        CH050.setWeight(null);
        CH050.setMaxFillingQuantity(756);
        CH050.setMaterial("Pet");
        caskRepo.save(CH050);

        Cask CH050B = new Cask();
        CH050B.setExcelCode("EM0008XX");
        CH050B.setName("CH050");
        CH050B.setDescription("CH050");
        CH050B.setWeight(null);
        CH050B.setMaxFillingQuantity(770);
        CH050B.setMaterial("Plastic");
        caskRepo.save(CH050B);

        Cask CH051G = new Cask();
        CH051G.setExcelCode("EM0006XX");
        CH051G.setName("CH051");
        CH051G.setDescription("CH051");
        CH051G.setWeight(null);
        CH051G.setMaxFillingQuantity(770);
        CH051G.setMaterial("Plastic");
        caskRepo.save(CH051G);

        Cask CH051Z = new Cask();
        CH051Z.setExcelCode("EM0007XX");
        CH051Z.setName("CH051");
        CH051Z.setDescription("CH051");
        CH051Z.setWeight(null);
        CH051Z.setMaxFillingQuantity(770);
        CH051Z.setMaterial("Plastic");
        caskRepo.save(CH051Z);

        Cask CH190 = new Cask();
        CH190.setExcelCode("EM0008XX");
        CH190.setName("CH190");
        CH190.setDescription("CH190");
        CH190.setWeight(null);
        CH190.setMaxFillingQuantity(640);
        CH190.setMaterial("Pet");
        caskRepo.save(CH190);

        Cask CH193 = new Cask();
        CH193.setExcelCode("EM0008XX");
        CH193.setName("CH193");
        CH193.setDescription("CH193");
        CH193.setWeight(null);
        CH193.setMaxFillingQuantity(700);
        CH193.setMaterial("Pet");
        caskRepo.save(CH193);
    }
}

