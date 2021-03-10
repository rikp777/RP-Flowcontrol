package flowcontrol.article.model.entity.seeder;

import com.google.common.collect.Sets;
import flowcontrol.article.exception.ResourceNotFoundException;
import flowcontrol.article.model.entity.Article;
import flowcontrol.article.model.entity.Color;
import flowcontrol.article.model.entity.PalletType;
import flowcontrol.article.repository.ArticleRepository;
import flowcontrol.article.repository.PalletTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
@Slf4j
public class PalletTypeSeeder {
    @Autowired
    private final PalletTypeRepository palletTypeRepo;

    public PalletTypeSeeder(PalletTypeRepository palletTypeRepo) {
        this.palletTypeRepo = palletTypeRepo;
    }



    public Set<PalletType> run() {
        if(!palletTypeRepo.findById(1L).isPresent()) {
            PalletType ww = new PalletType();
            ww.setName("WW");
            ww.setWeight(0);
            ww.setPrice(0d);
            palletTypeRepo.save(ww);

            PalletType euro = new PalletType();
            euro.setName("Euro");
            euro.setWeight(25000);
            euro.setPrice(8.6d);
            palletTypeRepo.save(euro);

            PalletType dpa = new PalletType();
            dpa.setName("DPA");
            dpa.setWeight(15500);
            dpa.setPrice(9d);
            palletTypeRepo.save(dpa);

            PalletType plastic = new PalletType();
            plastic.setName("Plastic");
            plastic.setWeight(23000);
            plastic.setPrice(70d);
            palletTypeRepo.save(plastic);

            log.info("Pallet types done seeding");
        }

        return Sets.newHashSet(palletTypeRepo.findAll());
    }
}