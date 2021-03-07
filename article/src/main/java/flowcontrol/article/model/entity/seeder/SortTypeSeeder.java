package flowcontrol.article.model.entity.seeder;

import com.google.common.collect.Sets;
import flowcontrol.article.model.entity.SortType;
import flowcontrol.article.repository.SortTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
@Slf4j
public class SortTypeSeeder {

    @Autowired
    private final SortTypeRepository sortTypeRepo;

    public SortTypeSeeder(SortTypeRepository sortTypeRepo) {
        this.sortTypeRepo = sortTypeRepo;
    }



    public Set<SortType> run() {
        if(!sortTypeRepo.findById(1L).isPresent()) {
            SortType mini = new SortType();
            mini.setName("Mini");
            mini.setDescription("Mini");
            sortTypeRepo.save(mini);

            SortType fijn = new SortType();
            fijn.setName("Fijn");
            fijn.setDescription("Fijn");
            sortTypeRepo.save(fijn);

            SortType small = new SortType();
            small.setName("Small");
            small.setDescription("Small");
            sortTypeRepo.save(small);

            SortType middel = new SortType();
            middel.setName("Middel");
            middel.setDescription("Middel");
            sortTypeRepo.save(middel);

            SortType medium = new SortType();
            medium.setName("Medium");
            medium.setDescription("Medium");
            sortTypeRepo.save(medium);

            SortType reuze = new SortType();
            reuze.setName("Reuzen");
            reuze.setDescription("Reuzen");
            sortTypeRepo.save(reuze);

            SortType industrie = new SortType();
            industrie.setName("Industrie");
            industrie.setDescription("Industrie");
            sortTypeRepo.save(industrie);

            SortType industrie3 = new SortType();
            industrie3.setName("Industrie (3.3.100)");
            industrie3.setDescription("Industrie (3.3.100)");
            sortTypeRepo.save(industrie3);

            SortType industrie2 = new SortType();
            industrie2.setName("Industrie (2.3.80)");
            industrie2.setDescription("Industrie (2.3.80)");
            sortTypeRepo.save(industrie2);

            SortType extraFijn = new SortType();
            extraFijn.setName("Extra fijn");
            extraFijn.setDescription("Extra fijn");
            sortTypeRepo.save(extraFijn);

            SortType gesneden = new SortType();
            gesneden.setName("Gesneden");
            gesneden.setDescription("Gesneden");
            sortTypeRepo.save(gesneden);

            SortType gesneden35 = new SortType();
            gesneden35.setName("Gesneden (35-45 mm)");
            gesneden35.setDescription("Gesneden (35-45 mm)");
            sortTypeRepo.save(gesneden35);

            SortType gesneden45 = new SortType();
            gesneden45.setName("Gesneden (45-55 mm)");
            gesneden45.setDescription("Gesneden (45-55 mm)");
            sortTypeRepo.save(gesneden45);


            SortType flats = new SortType();
            flats.setName("Flats");
            flats.setDescription("Flats");
            sortTypeRepo.save(flats);

            log.info("Sort types done seeding");
        }

        return Sets.newHashSet(sortTypeRepo.findAll());
    }
}
