package flowcontrol.article.model.entity.seeder;

import com.google.common.collect.Sets;
import flowcontrol.article.model.entity.SortType;
import flowcontrol.article.model.entity.Type;
import flowcontrol.article.repository.TypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
@Slf4j
public class TypeSeeder {
    private int id = 0;

    @Autowired
    private final TypeRepository typeRepo;

    public TypeSeeder(TypeRepository typeRepo) {
        this.typeRepo = typeRepo;
    }

    private void message(Type type){
        boolean debug = true;
        if(debug)
            log.info("Type seeder insert: " + this.id++ + " - " + type.getName());
    }

    public Set<Type> run() {
        boolean seed = true;
        if(seed) {
            log.info("Type seeding starting...");

            if (typeRepo.findByName("Bunches").isEmpty()) {
                var type = new Type();
                type.setName("Bunches");
                typeRepo.save(type);

                this.message(type);
            }

            if (typeRepo.findByName("Leaves").isEmpty()) {
                var type = new Type();
                type.setName("Leaves");
                typeRepo.save(type);

                this.message(type);
            }

            if (typeRepo.findByName("Stem").isEmpty()) {
                var type = new Type();
                type.setName("Stem");
                typeRepo.save(type);

                this.message(type);
            }

            if (typeRepo.findByName("Stemless").isEmpty()) {
                var type = new Type();
                type.setName("Stemless");
                typeRepo.save(type);

                this.message(type);
            }

            if (typeRepo.findByName("Tros").isEmpty()) {
                var type = new Type();
                type.setName("Tros");
                typeRepo.save(type);

                this.message(type);
            }

            if (typeRepo.findByName("Air").isEmpty()) {
                var type = new Type();
                type.setName("Air");
                typeRepo.save(type);

                this.message(type);
            }

            log.info("Sort type seeding done, seeded: " +  this.id + " sort types.");
        }else {
            log.info("Sort type seeding not required");
        }

        return Sets.newHashSet(typeRepo.findAll());
    }
}
