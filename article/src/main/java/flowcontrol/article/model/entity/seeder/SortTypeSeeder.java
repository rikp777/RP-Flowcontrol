package flowcontrol.article.model.entity.seeder;

import com.google.common.collect.Sets;
import flowcontrol.article.config.seeder.SeederConfig;
import flowcontrol.article.model.entity.PalletType;
import flowcontrol.article.model.entity.SortType;
import flowcontrol.article.repository.SortTypeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Set;
import java.util.UUID;

@Configuration
@Slf4j
public class SortTypeSeeder {
    private final SortTypeRepository sortTypeRepo;
    private final SeederConfig seederConfig;

    private int id = 1;

    public SortTypeSeeder(SortTypeRepository sortTypeRepo, SeederConfig seederConfig) {
        this.sortTypeRepo = sortTypeRepo;
        this.seederConfig = seederConfig;
    }

    private void message(SortType sortType){
        if(this.seederConfig.isInDebugMode())
            UtilSeeder.sendMessage("Sort seeder", this.id, sortType.getName(), sortType.getId());
        this.id++;
    }

    public Set<SortType> run() {
        if(this.seederConfig.isInInsetDataMode()) {

            log.info("Sort type seeding starting...");

            if (sortTypeRepo.findByName("Mini").isEmpty()) {
                var sortType = new SortType(UUID.fromString("980a6546-d66b-11eb-b8bc-0242ac130003"));
                sortType.setName("Mini");
                sortType.setDescription("Mini");
                sortTypeRepo.save(sortType);

                this.message(sortType);
            }

            if (sortTypeRepo.findByName("Fijn").isEmpty()) {
                var sortType = new SortType(UUID.fromString("95f0dcf4-d66b-11eb-b8bc-0242ac130003"));
                sortType.setName("Fijn");
                sortType.setDescription("Fijn");
                sortTypeRepo.save(sortType);

                this.message(sortType);
            }

            if (sortTypeRepo.findByName("Small").isEmpty()) {
                var sortType = new SortType(UUID.fromString("93b4b7da-d66b-11eb-b8bc-0242ac130003"));
                sortType.setName("Small");
                sortType.setDescription("Small");
                sortTypeRepo.save(sortType);

                this.message(sortType);
            }

            if (sortTypeRepo.findByName("Middel").isEmpty()) {
                var sortType = new SortType(UUID.fromString("91ce4986-d66b-11eb-b8bc-0242ac130003"));
                sortType.setName("Middel");
                sortType.setDescription("Middel");
                sortTypeRepo.save(sortType);

                this.message(sortType);
            }

            if (sortTypeRepo.findByName("Medium").isEmpty()) {
                var sortType = new SortType(UUID.fromString("8fca470c-d66b-11eb-b8bc-0242ac130003"));
                sortType.setName("Medium");
                sortType.setDescription("Medium");
                sortTypeRepo.save(sortType);

                this.message(sortType);
            }

            if (sortTypeRepo.findByName("Reuzen").isEmpty()) {
                var sortType = new SortType(UUID.fromString("8c8b8736-d66b-11eb-b8bc-0242ac130003"));
                sortType.setName("Reuzen");
                sortType.setDescription("Reuzen");
                sortTypeRepo.save(sortType);

                this.message(sortType);
            }

            if (sortTypeRepo.findByName("Industrie").isEmpty()) {
                var sortType = new SortType(UUID.fromString("8ab14fe0-d66b-11eb-b8bc-0242ac130003"));
                sortType.setName("Industrie");
                sortType.setDescription("Industrie");
                sortTypeRepo.save(sortType);

                this.message(sortType);
            }

            if (sortTypeRepo.findByName("Industrie (3.3.100)").isEmpty()) {
                var sortType = new SortType(UUID.fromString("88564868-d66b-11eb-b8bc-0242ac130003"));
                sortType.setName("Industrie (3.3.100)");
                sortType.setDescription("Industrie (3.3.100)");
                sortTypeRepo.save(sortType);

                this.message(sortType);
            }

            if (sortTypeRepo.findByName("Industrie (2.3.80)").isEmpty()) {
                var sortType = new SortType(UUID.fromString("85886a6c-d66b-11eb-b8bc-0242ac130003"));
                sortType.setName("Industrie (2.3.80)");
                sortType.setDescription("Industrie (2.3.80)");
                sortTypeRepo.save(sortType);

                this.message(sortType);
            }

            if (sortTypeRepo.findByName("Extra fijn").isEmpty()) {
                var sortType = new SortType(UUID.fromString("80f7e950-d66b-11eb-b8bc-0242ac130003"));
                sortType.setName("Extra fijn");
                sortType.setDescription("Extra fijn");
                sortTypeRepo.save(sortType);

                this.message(sortType);
            }

            if (sortTypeRepo.findByName("Gesneden").isEmpty()) {
                var sortType = new SortType(UUID.fromString("8337dd4c-d66b-11eb-b8bc-0242ac130003"));
                sortType.setName("Gesneden");
                sortType.setDescription("Gesneden");
                sortTypeRepo.save(sortType);

                this.message(sortType);
            }

            if (sortTypeRepo.findByName("Gesneden (35-45 mm)").isEmpty()) {
                var sortType = new SortType(UUID.fromString("7e7126a6-d66b-11eb-b8bc-0242ac130003"));
                sortType.setName("Gesneden (35-45 mm)");
                sortType.setDescription("Gesneden (35-45 mm)");
                sortTypeRepo.save(sortType);

                this.message(sortType);
            }

            if (sortTypeRepo.findByName("Gesneden (45-55 mm)").isEmpty()) {
                var sortType = new SortType(UUID.fromString("7b96a550-d66b-11eb-b8bc-0242ac130003"));
                sortType.setName("Gesneden (45-55 mm)");
                sortType.setDescription("Gesneden (45-55 mm)");
                sortTypeRepo.save(sortType);

                this.message(sortType);
            }

            if (sortTypeRepo.findByName("Flats").isEmpty()) {
                var sortType = new SortType(UUID.fromString("79825f66-d66b-11eb-b8bc-0242ac130003"));
                sortType.setName("Flats");
                sortType.setDescription("Flats");
                sortTypeRepo.save(sortType);

                this.message(sortType);
            }

            log.info("Sort type seeding done, seeded: " +  (this.id - 1) + " sort types.");
        }else {
            log.info("Sort type seeding not required");
        }

        return Sets.newHashSet(sortTypeRepo.findAll());
    }
}
