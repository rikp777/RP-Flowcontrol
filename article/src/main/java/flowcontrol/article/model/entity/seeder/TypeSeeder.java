package flowcontrol.article.model.entity.seeder;

import com.google.common.collect.Sets;
import flowcontrol.article.model.entity.Type;
import flowcontrol.article.repository.TypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
@Slf4j
public class TypeSeeder {

    @Autowired
    private final TypeRepository typeRepo;

    public TypeSeeder(TypeRepository typeRepo) {
        this.typeRepo = typeRepo;
    }

    public Set<Type> run() {
        if(!typeRepo.findById(1L).isPresent()) {
            var bunches = new Type();
            bunches.setName("Bunches");
            typeRepo.save(bunches);

            var leaves = new Type();
            leaves.setName("Leaves");
            typeRepo.save(leaves);

            var stem = new Type();
            stem.setName("Stem");
            typeRepo.save(stem);

            var stemless = new Type();
            stemless.setName("Stemless");
            typeRepo.save(stemless);

            var tros = new Type();
            tros.setName("Tros");
            typeRepo.save(tros);

            var air = new Type();
            air.setName("Air");
            typeRepo.save(air);

            log.info("Types done seeding");
        }

        return Sets.newHashSet(typeRepo.findAll());
    }
}
