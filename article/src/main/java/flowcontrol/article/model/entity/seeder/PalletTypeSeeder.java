package flowcontrol.article.model.entity.seeder;

import com.google.common.collect.Sets;
import flowcontrol.article.model.entity.Inset;
import flowcontrol.article.model.entity.PalletType;
import flowcontrol.article.repository.PalletTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Set;
import java.util.UUID;

@Configuration
@Slf4j
public class PalletTypeSeeder {
    private int id = 1;

    @Autowired
    private final PalletTypeRepository palletTypeRepo;

    public PalletTypeSeeder(PalletTypeRepository palletTypeRepo) {
        this.palletTypeRepo = palletTypeRepo;
    }

    private void message(PalletType palletType){
        boolean debug = true;
        if(debug)
            log.info("Pallet type seeder insert: " + this.id++ + " - " + palletType.getName() + " | " +
                    "UUID: " + palletType.getId()
            );
    }

    public Set<PalletType> run() {
        boolean seed = true;
        if(seed) {
            log.info("Pallet type seeding starting...");
            if (palletTypeRepo.findByName("WW").isEmpty()) {
                var palletType = new PalletType(UUID.fromString("43f8d9ba-d66b-11eb-b8bc-0242ac130003"));
                palletType.setName("WW");
                palletType.setWeight(0);
                palletType.setPrice(0d);
                palletTypeRepo.save(palletType);

                this.message(palletType);
            }

            if (palletTypeRepo.findByName("Euro").isEmpty()) {
                var palletType = new PalletType(UUID.fromString("414e9dc6-d66b-11eb-b8bc-0242ac130003"));
                palletType.setName("Euro");
                palletType.setWeight(25000);
                palletType.setPrice(8.6d);
                palletTypeRepo.save(palletType);

                this.message(palletType);
            }

            if (palletTypeRepo.findByName("DPA").isEmpty()) {
                var palletType = new PalletType(UUID.fromString("3e95dcfc-d66b-11eb-b8bc-0242ac130003"));
                palletType.setName("DPA");
                palletType.setWeight(15500);
                palletType.setPrice(9d);
                palletTypeRepo.save(palletType);

                this.message(palletType);
            }

            if (palletTypeRepo.findByName("Plastic").isEmpty()) {
                var palletType = new PalletType(UUID.fromString("38a80798-d66b-11eb-b8bc-0242ac130003"));
                palletType.setName("Plastic");
                palletType.setWeight(23000);
                palletType.setPrice(70d);
                palletTypeRepo.save(palletType);
            }

            log.info("Pallet type seeding done, seeded: " +  this.id + " pallet types.");
        }else {
            log.info("Pallet type seeding not required");
        }

        return Sets.newHashSet(palletTypeRepo.findAll());
    }
}
