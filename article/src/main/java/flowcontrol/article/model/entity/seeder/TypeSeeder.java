package flowcontrol.article.model.entity.seeder;

import flowcontrol.article.model.entity.Type;
import flowcontrol.article.repository.GroupRepository;
import flowcontrol.article.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class TypeSeeder {

    @Autowired
    private final TypeRepository typeRepo;

    public TypeSeeder(TypeRepository typeRepo) {
        this.typeRepo = typeRepo;
    }

    public Set<Type> run() {

        Type bunches = new Type();
        bunches.setName("Bunches");
        typeRepo.save(bunches);

        Type leaves = new Type();
        leaves.setName("Leaves");
        typeRepo.save(leaves);

        Type stem = new Type();
        stem.setName("Stem");
        typeRepo.save(stem);

        Type Stemless = new Type();
        Stemless.setName("Stemless");
        typeRepo.save(Stemless);

        Type tros = new Type();
        tros.setName("Tros");
        typeRepo.save(tros);

        Type air = new Type();
        air.setName("Air");
        typeRepo.save(air);

        return new HashSet<>(typeRepo.findAll());
    }
}
