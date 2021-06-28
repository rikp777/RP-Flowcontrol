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
                palletLabel.setPalletLabelFarmerId(UUID.fromString(""));//todo add farmerid
                palletLabel.setArticle(UUID.fromString("56e6b597-929e-4aaf-97bf-4f2506d2cacc"));
                palletLabel.setArticleAmount(180);
                palletLabel.setCropDate("2021-03-02");
                palletLabel.setNote("Test one");
                palletLabel.setHarvestCycle(1);
                palletLabel.setHarvestCycleDay(1);
                palletLabel.setCell(UUID.fromString(""));
                palletLabel.setFarmer(UUID.fromString(""));
                palletLabel.setPalletType(UUID.fromString(""));
                palletLabel.setShippingLabel(util.findShippingLabelInSet(UUID.fromString("")));
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

