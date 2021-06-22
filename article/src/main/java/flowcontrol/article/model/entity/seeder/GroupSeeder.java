package flowcontrol.article.model.entity.seeder;

import com.google.common.collect.Sets;
import flowcontrol.article.model.entity.Color;
import flowcontrol.article.model.entity.Group;
import flowcontrol.article.repository.GroupRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
@Slf4j
public class GroupSeeder {
    private int id = 0;

    @Autowired
    private final GroupRepository groupRepo;

    public GroupSeeder(GroupRepository groupRepo) {
        this.groupRepo = groupRepo;
    }

    private void message(Group group){
        boolean debug = true;
        if(debug)
            log.info("Group seeder insert: " + this.id++ + " - " + group.getName());
    }

    public Set<Group> run() {

        boolean seed = true;
        if(seed) {
            log.info("Group seeding starting...");

            if (groupRepo.findByName("Afk(2-2-60)").isEmpty()) {
                var group = new Group();
                group.setName("Afk(2-2-60)");
                group.setDescription("Afk(2-2-60)");
                groupRepo.save(group);

                this.message(group);
            }

            if (groupRepo.findByName("Afk(2-3-80)").isEmpty()) {
                var group = new Group();
                group.setName("Afk(2-3-80)");
                group.setDescription("Afk(2-3-80)");
                groupRepo.save(group);

                this.message(group);
            }

            if (groupRepo.findByName("Kastanje").isEmpty()) {
                var group = new Group();
                group.setName("Kastanje");
                group.setDescription("Kastanje");
                groupRepo.save(group);

                this.message(group);
            }

            if (groupRepo.findByName("Wit").isEmpty()) {
                var group = new Group();
                group.setName("Wit");
                group.setDescription("Wit");
                groupRepo.save(group);
                this.message(group);
            }

            if (groupRepo.findByName("Shiitake").isEmpty()) {
                var group = new Group();
                group.setName("Shiitake");
                group.setDescription("Shiitake");
                groupRepo.save(group);

                this.message(group);
            }

            if (groupRepo.findByName("Oesterzwam").isEmpty()) {
                var group = new Group();
                group.setName("Oesterzwam");
                group.setDescription("Oesterzwam");
                groupRepo.save(group);

                this.message(group);
            }

            if (groupRepo.findByName("Portabella").isEmpty()) {
                var group = new Group();
                group.setName("Portabella");
                group.setDescription("Portabella");
                groupRepo.save(group);

                this.message(group);
            }

            if (groupRepo.findByName("Beukenzwam").isEmpty()) {
                var group = new Group();
                group.setName("Beukenzwam");
                group.setDescription("Beukenzwam");
                groupRepo.save(group);

                this.message(group);
            }

            if (groupRepo.findByName("Oyster Mushroom").isEmpty()) {
                var group = new Group();
                group.setName("Oyster Mushroom");
                group.setDescription("Oyster Mushroom");
                groupRepo.save(group);

                this.message(group);
            }

            if (groupRepo.findByName("Pioppino").isEmpty()) {
                var group = new Group();
                group.setName("Pioppino");
                group.setDescription("Pioppino");
                groupRepo.save(group);

                this.message(group);
            }

            if (groupRepo.findByName("Nameco").isEmpty()) {
                var group = new Group();
                group.setName("Nameco");
                group.setDescription("Nameco");
                groupRepo.save(group);

                this.message(group);
            }

            if (groupRepo.findByName("Eryngii").isEmpty()) {
                var group = new Group();
                group.setName("Eryngii");
                group.setDescription("Eryngii");
                groupRepo.save(group);

                this.message(group);
            }

            if (groupRepo.findByName("Koraalzwam").isEmpty()) {
                var group = new Group();
                group.setName("Koraalzwam");
                group.setDescription("Koraalzwam");
                groupRepo.save(group);

                this.message(group);
            }

            if (groupRepo.findByName("Pom Pom Blanc").isEmpty()) {
                var group = new Group();
                group.setName("Pom Pom Blanc");
                group.setDescription("Pom Pom Blanc");
                groupRepo.save(group);

                this.message(group);
            }

            if (groupRepo.findByName("Akkerpaddenstoelen").isEmpty()) {
                var group = new Group();
                group.setName("Akkerpaddenstoelen");
                group.setDescription("Akkerpaddenstoelen");
                groupRepo.save(group);

                this.message(group);
            }

            if (groupRepo.findByName("Shii-meji").isEmpty()) {
                var group = new Group();
                group.setName("Shii-meji");
                group.setDescription("Shii-meji ");
                groupRepo.save(group);

                this.message(group);
            }

            log.info("Group seeding done, seeded: " +  this.id + " groups.");
        }else {
            log.info("Group seeding not required");
        }

        return Sets.newHashSet(groupRepo.findAll());
    }

}
