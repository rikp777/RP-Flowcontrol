package flowcontrol.article.model.entity.seeder;

import com.google.common.collect.Sets;
import flowcontrol.article.config.seeder.SeederConfig;
import flowcontrol.article.model.entity.Color;
import flowcontrol.article.model.entity.Group;
import flowcontrol.article.repository.GroupRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Set;
import java.util.UUID;

@Configuration
@Slf4j
public class GroupSeeder {
    private final GroupRepository groupRepo;
    private final SeederConfig seederConfig;

    private int id = 1;

    public GroupSeeder(GroupRepository groupRepo, SeederConfig seederConfig) {
        this.groupRepo = groupRepo;
        this.seederConfig = seederConfig;
    }

    private void message(Group group){
        if(this.seederConfig.isInDebugMode())
            UtilSeeder.sendMessage("Group seeder", this.id, group.getName(), group.getId());
        this.id++;
    }

    public Set<Group> run() {
        if(this.seederConfig.isInInsetDataMode()) {
            log.info("Group seeding starting...");

            if (groupRepo.findByName("Afk(2-2-60)").isEmpty()) {
                var group = new Group(UUID.fromString("1f8adaf6-00aa-4309-bdf7-135e2e12b912"));
                group.setName("Afk(2-2-60)");
                group.setDescription("Afk(2-2-60)");
                groupRepo.save(group);

                this.message(group);
            }

            if (groupRepo.findByName("Afk(2-3-80)").isEmpty()) {
                var group = new Group(UUID.fromString("2d87cbb3-4a43-4732-80d5-8ec1a51a616f"));
                group.setName("Afk(2-3-80)");
                group.setDescription("Afk(2-3-80)");
                groupRepo.save(group);

                this.message(group);
            }

            if (groupRepo.findByName("Kastanje").isEmpty()) {
                var group = new Group(UUID.fromString("337fc50a-3554-48e3-859f-3b6cb38f764c"));
                group.setName("Kastanje");
                group.setDescription("Kastanje");
                groupRepo.save(group);

                this.message(group);
            }

            if (groupRepo.findByName("Wit").isEmpty()) {
                var group = new Group(UUID.fromString("5e5e7a49-d2a7-45cd-9b94-23a8085d8598"));
                group.setName("Wit");
                group.setDescription("Wit");
                groupRepo.save(group);
                this.message(group);
            }

            if (groupRepo.findByName("Shiitake").isEmpty()) {
                var group = new Group(UUID.fromString("6651ab26-a922-46ad-80fb-8bfc7109167a"));
                group.setName("Shiitake");
                group.setDescription("Shiitake");
                groupRepo.save(group);

                this.message(group);
            }

            if (groupRepo.findByName("Oesterzwam").isEmpty()) {
                var group = new Group(UUID.fromString("67085311-3bf0-4506-9d86-c6e2d0601ccc"));
                group.setName("Oesterzwam");
                group.setDescription("Oesterzwam");
                groupRepo.save(group);

                this.message(group);
            }

            if (groupRepo.findByName("Portabella").isEmpty()) {
                var group = new Group(UUID.fromString("75732429-3ccf-4197-a329-91fa13d018cb"));
                group.setName("Portabella");
                group.setDescription("Portabella");
                groupRepo.save(group);

                this.message(group);
            }

            if (groupRepo.findByName("Beukenzwam").isEmpty()) {
                var group = new Group(UUID.fromString("9a3007a6-4ffb-4826-b8d3-83f16e20de88"));
                group.setName("Beukenzwam");
                group.setDescription("Beukenzwam");
                groupRepo.save(group);

                this.message(group);
            }

            if (groupRepo.findByName("Oyster Mushroom").isEmpty()) {
                var group = new Group(UUID.fromString("a2da6ae5-1c92-4a43-9b01-9ec4f4751815"));
                group.setName("Oyster Mushroom");
                group.setDescription("Oyster Mushroom");
                groupRepo.save(group);

                this.message(group);
            }

            if (groupRepo.findByName("Pioppino").isEmpty()) {
                var group = new Group(UUID.fromString("a3d23eef-97c9-4ce4-b706-288f1c999614"));
                group.setName("Pioppino");
                group.setDescription("Pioppino");
                groupRepo.save(group);

                this.message(group);
            }

            if (groupRepo.findByName("Nameco").isEmpty()) {
                var group = new Group(UUID.fromString("cef638f5-cc4d-485f-817a-0a6d5f80a770"));
                group.setName("Nameco");
                group.setDescription("Nameco");
                groupRepo.save(group);

                this.message(group);
            }

            if (groupRepo.findByName("Eryngii").isEmpty()) {
                var group = new Group(UUID.fromString("d09d5f60-3a2d-48e9-b3ac-64e90f102779"));
                group.setName("Eryngii");
                group.setDescription("Eryngii");
                groupRepo.save(group);

                this.message(group);
            }

            if (groupRepo.findByName("Koraalzwam").isEmpty()) {
                var group = new Group(UUID.fromString("d5f226b8-945d-452e-9930-91de69166b1c"));
                group.setName("Koraalzwam");
                group.setDescription("Koraalzwam");
                groupRepo.save(group);

                this.message(group);
            }

            if (groupRepo.findByName("Pom Pom Blanc").isEmpty()) {
                var group = new Group(UUID.fromString("d8e0a3cc-e687-4702-86eb-a4a907cc3d2d"));
                group.setName("Pom Pom Blanc");
                group.setDescription("Pom Pom Blanc");
                groupRepo.save(group);

                this.message(group);
            }

            if (groupRepo.findByName("Akkerpaddenstoelen").isEmpty()) {
                var group = new Group(UUID.fromString("e7ae1157-53b9-469b-a428-7e082e63f723"));
                group.setName("Akkerpaddenstoelen");
                group.setDescription("Akkerpaddenstoelen");
                groupRepo.save(group);

                this.message(group);
            }

            if (groupRepo.findByName("Shii-meji").isEmpty()) {
                var group = new Group(UUID.fromString("f634a488-a216-4aae-aaf7-e65549772092"));
                group.setName("Shii-meji");
                group.setDescription("Shii-meji ");
                groupRepo.save(group);

                this.message(group);
            }

            log.info("Group seeding done, seeded: " +  (this.id - 1) + " groups.");
        }else {
            log.info("Group seeding not required");
        }

        return Sets.newHashSet(groupRepo.findAll());
    }

}
