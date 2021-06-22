package flowcontrol.article.model.entity.seeder;

import com.google.common.collect.Sets;
import flowcontrol.article.model.entity.PalletType;
import flowcontrol.article.model.entity.SortType;
import flowcontrol.article.repository.SortTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
@Slf4j
public class SortTypeSeeder {
    private int id = 0;

    @Autowired
    private final SortTypeRepository sortTypeRepo;

    public SortTypeSeeder(SortTypeRepository sortTypeRepo) {
        this.sortTypeRepo = sortTypeRepo;
    }

    private void message(SortType sortType){
        boolean debug = true;
        if(debug)
            log.info("Sort type seeder insert: " + this.id++ + " - " + sortType.getName());
    }

    public Set<SortType> run() {
        boolean seed = true;
        if(seed) {

            log.info("Sort type seeding starting...");

            if (sortTypeRepo.findByName("Mini").isEmpty()) {
                var sortType = new SortType();
                sortType.setName("Mini");
                sortType.setDescription("Mini");
                sortTypeRepo.save(sortType);

                this.message(sortType);
            }

            if (sortTypeRepo.findByName("Fijn").isEmpty()) {
                var sortType = new SortType();
                sortType.setName("Fijn");
                sortType.setDescription("Fijn");
                sortTypeRepo.save(sortType);

                this.message(sortType);
            }

            if (sortTypeRepo.findByName("Small").isEmpty()) {
                var sortType = new SortType();
                sortType.setName("Small");
                sortType.setDescription("Small");
                sortTypeRepo.save(sortType);

                this.message(sortType);
            }

            if (sortTypeRepo.findByName("Middel").isEmpty()) {
                var sortType = new SortType();
                sortType.setName("Middel");
                sortType.setDescription("Middel");
                sortTypeRepo.save(sortType);

                this.message(sortType);
            }

            if (sortTypeRepo.findByName("Medium").isEmpty()) {
                var sortType = new SortType();
                sortType.setName("Medium");
                sortType.setDescription("Medium");
                sortTypeRepo.save(sortType);

                this.message(sortType);
            }

            if (sortTypeRepo.findByName("Reuzen").isEmpty()) {
                var sortType = new SortType();
                sortType.setName("Reuzen");
                sortType.setDescription("Reuzen");
                sortTypeRepo.save(sortType);

                this.message(sortType);
            }

            if (sortTypeRepo.findByName("Industrie").isEmpty()) {
                var sortType = new SortType();
                sortType.setName("Industrie");
                sortType.setDescription("Industrie");
                sortTypeRepo.save(sortType);

                this.message(sortType);
            }

            if (sortTypeRepo.findByName("Industrie (3.3.100)").isEmpty()) {
                var sortType = new SortType();
                sortType.setName("Industrie (3.3.100)");
                sortType.setDescription("Industrie (3.3.100)");
                sortTypeRepo.save(sortType);

                this.message(sortType);
            }

            if (sortTypeRepo.findByName("Industrie (2.3.80)").isEmpty()) {
                var sortType = new SortType();
                sortType.setName("Industrie (2.3.80)");
                sortType.setDescription("Industrie (2.3.80)");
                sortTypeRepo.save(sortType);

                this.message(sortType);
            }

            if (sortTypeRepo.findByName("Extra fijn").isEmpty()) {
                var sortType = new SortType();
                sortType.setName("Extra fijn");
                sortType.setDescription("Extra fijn");
                sortTypeRepo.save(sortType);

                this.message(sortType);
            }

            if (sortTypeRepo.findByName("Gesneden").isEmpty()) {
                var sortType = new SortType();
                sortType.setName("Gesneden");
                sortType.setDescription("Gesneden");
                sortTypeRepo.save(sortType);

                this.message(sortType);
            }

            if (sortTypeRepo.findByName("Gesneden (35-45 mm)").isEmpty()) {
                var sortType = new SortType();
                sortType.setName("Gesneden (35-45 mm)");
                sortType.setDescription("Gesneden (35-45 mm)");
                sortTypeRepo.save(sortType);

                this.message(sortType);
            }

            if (sortTypeRepo.findByName("Gesneden (45-55 mm)").isEmpty()) {
                var sortType = new SortType();
                sortType.setName("Gesneden (45-55 mm)");
                sortType.setDescription("Gesneden (45-55 mm)");
                sortTypeRepo.save(sortType);

                this.message(sortType);
            }

            if (sortTypeRepo.findByName("Flats").isEmpty()) {
                var sortType = new SortType();
                sortType.setName("Flats");
                sortType.setDescription("Flats");
                sortTypeRepo.save(sortType);

                this.message(sortType);
            }

            log.info("Sort type seeding done, seeded: " +  this.id + " sort types.");
        }else {
            log.info("Sort type seeding not required");
        }

        return Sets.newHashSet(sortTypeRepo.findAll());
    }
}
