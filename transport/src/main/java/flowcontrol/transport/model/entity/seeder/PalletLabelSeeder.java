package flowcontrol.transport.model.entity.seeder;

import flowcontrol.transport.config.seeder.SeederConfig;
import flowcontrol.transport.model.entity.PalletLabel;
import flowcontrol.transport.repository.PalletLabelRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
@Slf4j
public class PalletLabelSeeder {

    private final PalletLabelRepository palletLabelRepo;
    private SeederConfig seederConfig;

    private int id = 1;

    public PalletLabelSeeder(PalletLabelRepository palletLabelRepo, SeederConfig seederConfig) {
        this.palletLabelRepo = palletLabelRepo;
        this.seederConfig = seederConfig;
    }

    private void message(PalletLabel palletLabel){
        if(this.seederConfig.isInDebugMode())
            UtilSeeder.sendMessage("PalletLabel seeder", this.id, palletLabel.getGeneralId().toString(), palletLabel.getId());
        this.id++;
    }

    public Set<PalletLabel> run(UtilSeeder util) {
        if (this.seederConfig.isInInsetDataMode()) {
            if (this.palletLabelRepo.findById(UUID.fromString("3d443c4a-9930-4e31-a33f-59f515b4b94e")).isEmpty()) {
                PalletLabel palletLabel = new PalletLabel(UUID.fromString("3d443c4a-9930-4e31-a33f-59f515b4b94e"));
                palletLabel.setGeneralId(1L);
                palletLabel.setPalletLabelFarmerId(1l);//todo add farmerid
                palletLabel.setArticle(UUID.fromString("56e6b597-929e-4aaf-97bf-4f2506d2cacc"));
                palletLabel.setArticleAmount(180);
                palletLabel.setCropDate("2021-03-02");
                palletLabel.setNote("These mushrooms have a slight discoloration ");
                palletLabel.setHarvestCycle(1);
                palletLabel.setHarvestCycleDay(1);
                palletLabel.setCell(UUID.fromString("2950cbec-b8b3-4a9e-8847-b2c92418fc42"));
                palletLabel.setFarmer(UUID.fromString("e6c9b529-b44f-4809-9c25-fb15f4e32795"));
                palletLabel.setPalletType(UUID.fromString("43f8d9ba-d66b-11eb-b8bc-0242ac130003"));
                palletLabelRepo.save(palletLabel);

                message(palletLabel);
            }

            if (this.palletLabelRepo.findById(UUID.fromString("3c80957b-ac78-46f0-abbe-d54f1025a4dd")).isEmpty()) {
                PalletLabel palletLabel = new PalletLabel(UUID.fromString("3c80957b-ac78-46f0-abbe-d54f1025a4dd"));
                palletLabel.setGeneralId(2L);
                palletLabel.setPalletLabelFarmerId(2l);//todo add farmerid
                palletLabel.setArticle(UUID.fromString("29ef480b-2a00-4bc6-b97f-63b63e321c57"));
                palletLabel.setArticleAmount(180);
                palletLabel.setCropDate("2021-03-03");
                palletLabel.setNote("These mushrooms are in a different box than discussed ");
                palletLabel.setHarvestCycle(1);
                palletLabel.setHarvestCycleDay(1);
                palletLabel.setCell(UUID.fromString("2950cbec-b8b3-4a9e-8847-b2c92418fc42"));
                palletLabel.setFarmer(UUID.fromString("29ef480b-2a00-4bc6-b97f-63b63e321c57"));
                palletLabel.setPalletType(UUID.fromString("43f8d9ba-d66b-11eb-b8bc-0242ac130003"));
                palletLabelRepo.save(palletLabel);

                message(palletLabel);
            }

            if (this.palletLabelRepo.findById(UUID.fromString("bc4824f9-aa93-4480-978e-219a2012e961")).isEmpty()) {
                PalletLabel palletLabel = new PalletLabel(UUID.fromString("bc4824f9-aa93-4480-978e-219a2012e961"));
                palletLabel.setGeneralId(3L);
                palletLabel.setPalletLabelFarmerId(3l);//todo add farmerid
                palletLabel.setArticle(UUID.fromString("595ab897-75d5-476b-9ed0-534db67bdf19"));
                palletLabel.setArticleAmount(170);
                palletLabel.setCropDate("2021-03-03");
                palletLabel.setNote("Test Two");
                palletLabel.setHarvestCycle(1);
                palletLabel.setHarvestCycleDay(1);
                palletLabel.setCell(UUID.fromString("2950cbec-b8b3-4a9e-8847-b2c92418fc42"));
                palletLabel.setFarmer(UUID.fromString("29ef480b-2a00-4bc6-b97f-63b63e321c57"));
                palletLabel.setPalletType(UUID.fromString("43f8d9ba-d66b-11eb-b8bc-0242ac130003"));
                palletLabelRepo.save(palletLabel);

                message(palletLabel);
            }

            log.info("PalletLabel seeding done, seeded: " + (this.id - 1) + " pallet labels.");
        } else {
            log.info("PalletLabel seeding not required");
        }
        return new HashSet<>(palletLabelRepo.findAll());
    }
}

