package flowcontrol.transport.model.entity.seeder;

import flowcontrol.transport.model.entity.PalletLabel;
import flowcontrol.transport.repository.PalletLabelRepository;
import flowcontrol.transport.repository.ShippingLabelRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
@Slf4j
public class PalletLabelSeeder {
    @Autowired
    private final PalletLabelRepository palletLabelRepo;

    public PalletLabelSeeder(PalletLabelRepository palletLabelRepo) {
        this.palletLabelRepo = palletLabelRepo;
    }

    public Set<PalletLabel> run(UtilSeeder util) {
        List<PalletLabel> palletLabels = Arrays.asList(
                PalletLabel.builder()
                        .generalId(1L)
                        .palletLabelFarmerId(1L)
                        .article(2579l)
                        .articleAmount(180)
                        .cropDate("2021-03-02")
                        .note("Test one")
                        .harvestCycle(1)
                        .harvestCycleDay(1)
                        .cell(1L)
                        .farmer(1L)
                        .palletType(1L)
                        .shippingLabel(util.findShippingLabelInSet(1L))
                        .build(),
                PalletLabel.builder()
                        .generalId(2L)
                        .palletLabelFarmerId(2L)
                        .article(2580l)
                        .articleAmount(180)
                        .cropDate("2021-03-02")
                        .note("Test two")
                        .harvestCycle(1)
                        .harvestCycleDay(1)
                        .cell(1L)
                        .farmer(1L)
                        .palletType(1L)
                        .shippingLabel(util.findShippingLabelInSet(1L))
                        .build(),
                PalletLabel.builder()
                        .generalId(3L)
                        .palletLabelFarmerId(3L)
                        .article(2581l)
                        .articleAmount(180)
                        .cropDate("2021-03-02")
                        .note("Test three")
                        .harvestCycle(1)
                        .harvestCycleDay(1)
                        .cell(1L)
                        .farmer(1L)
                        .palletType(1L)
                        .shippingLabel(util.findShippingLabelInSet(1L))
                        .build()
        );

        if(palletLabelRepo.findAll().size() == 0){
            log.info("PalletLabel done seeding");
            palletLabelRepo.saveAll(palletLabels);
        }

        List<PalletLabel> palletLabelss = palletLabelRepo.findAll();
        return new HashSet<>(palletLabelss);
    }
}

