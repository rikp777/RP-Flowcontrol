package flowcontrol.article.model.entity.seeder;

import com.google.common.collect.Sets;
import flowcontrol.article.exception.ResourceNotFoundException;
import flowcontrol.article.model.entity.Color;
import flowcontrol.article.model.entity.Group;
import flowcontrol.article.repository.CaskRepository;
import flowcontrol.article.repository.GroupRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
@Slf4j
public class GroupSeeder {

    @Autowired
    private final GroupRepository groupRepo;


    public GroupSeeder(GroupRepository groupRepo) {
        this.groupRepo = groupRepo;
    }

    public Set<Group> run() {
        if(!groupRepo.findById(1L).isPresent()) {
            Group AFK60 = new Group();
            AFK60.setName("Afk(2-2-60)");
            AFK60.setDescription("Afk(2-2-60)");
            groupRepo.save(AFK60);
            Group AFK80 = new Group();
            AFK80.setName("Afk(2-3-80)");
            AFK80.setDescription("Afk(2-3-80)");
            groupRepo.save(AFK80);


            Group kastanje = new Group();
            kastanje.setName("Kastanje");
            kastanje.setDescription("Kastanje");
            groupRepo.save(kastanje);

            Group wit = new Group();
            wit.setName("Wit");
            wit.setDescription("Wit");
            groupRepo.save(wit);

            Group shiitake = new Group();
            shiitake.setName("Shiitake");
            shiitake.setDescription("Shiitake");
            groupRepo.save(shiitake);

            Group oesterzwam = new Group();
            oesterzwam.setName("Oesterzwam");
            oesterzwam.setDescription("Oesterzwam");
            groupRepo.save(oesterzwam);

            Group portabella = new Group();
            portabella.setName("Portabella");
            portabella.setDescription("Portabella");
            groupRepo.save(portabella);

            Group beukenzwam = new Group();
            beukenzwam.setName("Beukenzwam");
            beukenzwam.setDescription("Beukenzwam");
            groupRepo.save(beukenzwam);

            Group oysterMushroomBunches = new Group();
            oysterMushroomBunches.setName("Oyster Mushroom");
            oysterMushroomBunches.setDescription("Oyster Mushroom");
            groupRepo.save(oysterMushroomBunches);

            Group pioppino = new Group();
            pioppino.setName("Pioppino");
            pioppino.setDescription("Pioppino");
            groupRepo.save(pioppino);

            Group nameco = new Group();
            nameco.setName("Nameco");
            nameco.setDescription("Nameco");
            groupRepo.save(nameco);

            Group eryngii = new Group();
            eryngii.setName("Eryngii");
            eryngii.setDescription("Eryngii");
            groupRepo.save(eryngii);

            Group koraalzwam = new Group();
            koraalzwam.setName("Koraalzwam");
            koraalzwam.setDescription("Koraalzwam");
            groupRepo.save(koraalzwam);

            Group pomPom = new Group();
            pomPom.setName("Pom Pom Blanc");
            pomPom.setDescription("Pom Pom Blanc");
            groupRepo.save(pomPom);

            Group akkerpaddenstoelen = new Group();
            akkerpaddenstoelen.setName("Akkerpaddenstoelen");
            akkerpaddenstoelen.setDescription("Akkerpaddenstoelen");
            groupRepo.save(akkerpaddenstoelen);

            Group shiiMeji = new Group();
            shiiMeji.setName("Shii-meji ");
            shiiMeji.setDescription("Shii-meji ");
            groupRepo.save(shiiMeji);

            log.info("Groups done seeding");
        }

        return Sets.newHashSet(groupRepo.findAll());
    }

}