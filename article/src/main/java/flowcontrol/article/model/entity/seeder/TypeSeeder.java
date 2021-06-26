package flowcontrol.article.model.entity.seeder;

import com.google.common.collect.Sets;
import flowcontrol.article.config.seeder.SeederConfig;
import flowcontrol.article.model.entity.SortType;
import flowcontrol.article.model.entity.Type;
import flowcontrol.article.repository.TypeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Set;
import java.util.UUID;

@Configuration
@Slf4j
public class TypeSeeder {
    private final TypeRepository typeRepo;
    private final SeederConfig seederConfig;

    private int id = 1;
    private boolean override = false;

    public TypeSeeder(TypeRepository typeRepo, SeederConfig seederConfig) {
        this.typeRepo = typeRepo;
        this.seederConfig = seederConfig;
    }

    private void message(Type type){
        if(this.seederConfig.isInDebugMode())
            UtilSeeder.sendMessage("Type seeder", this.id, type.getName(), type.getId());
        this.id++;
    }

    public Set<Type> run() {
        if(this.seederConfig.isInInsetDataMode() || override) {
            log.info("Type seeding starting...");

            if (typeRepo.findByName("Bunches").isEmpty()) {
                var type = new Type(UUID.fromString("b6391a76-d66b-11eb-b8bc-0242ac130003"));
                type.setName("Bunches");
                typeRepo.save(type);

                this.message(type);
            }

            if (typeRepo.findByName("Leaves").isEmpty()) {
                var type = new Type(UUID.fromString("b444f172-d66b-11eb-b8bc-0242ac130003"));
                type.setName("Leaves");
                typeRepo.save(type);

                this.message(type);
            }

            if (typeRepo.findByName("Stem").isEmpty()) {
                var type = new Type(UUID.fromString("b26988e0-d66b-11eb-b8bc-0242ac130003"));
                type.setName("Stem");
                typeRepo.save(type);

                this.message(type);
            }

            if (typeRepo.findByName("Stemless").isEmpty()) {
                var type = new Type(UUID.fromString("b02e1816-d66b-11eb-b8bc-0242ac130003"));
                type.setName("Stemless");
                typeRepo.save(type);

                this.message(type);
            }

            if (typeRepo.findByName("Tros").isEmpty()) {
                var type = new Type(UUID.fromString("ae0e04b0-d66b-11eb-b8bc-0242ac130003"));
                type.setName("Tros");
                typeRepo.save(type);

                this.message(type);
            }

            if (typeRepo.findByName("Air").isEmpty()) {
                var type = new Type(UUID.fromString("a4ffe974-d66b-11eb-b8bc-0242ac130003"));
                type.setName("Air");
                typeRepo.save(type);

                this.message(type);
            }

            log.info("Sort type seeding done, seeded: " +  (this.id - 1) + " sort types.");
        }else {
            log.info("Sort type seeding not required");
        }

        return Sets.newHashSet(typeRepo.findAll());
    }
}
