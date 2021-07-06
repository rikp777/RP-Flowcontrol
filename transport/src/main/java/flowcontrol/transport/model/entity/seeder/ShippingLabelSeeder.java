package flowcontrol.transport.model.entity.seeder;

import flowcontrol.transport.config.seeder.SeederConfig;
import flowcontrol.transport.model.entity.PalletLabel;
import flowcontrol.transport.model.entity.ShippingLabel;
import flowcontrol.transport.repository.ShippingLabelRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
@Slf4j
public class ShippingLabelSeeder {
    private final ShippingLabelRepository shippingLabelRepo;
    private SeederConfig seederConfig;

    private int id = 1;

    public ShippingLabelSeeder(ShippingLabelRepository shippingLabelRepo, SeederConfig seederConfig) {
        this.shippingLabelRepo = shippingLabelRepo;
        this.seederConfig = seederConfig;
    }

    private void message(ShippingLabel shippingLabel){
        if(this.seederConfig.isInDebugMode())
            UtilSeeder.sendMessage("ShippingLabel seeder", this.id, shippingLabel.getGeneralId().toString(), shippingLabel.getId());
        this.id++;
    }

    public Set<ShippingLabel> run(UtilSeeder util) {
        if(this.seederConfig.isInInsetDataMode()){
            if(shippingLabelRepo.findById(UUID.fromString("1703ad67-f28e-4b4b-b437-a534000515b0")).isEmpty()) {
                ShippingLabel shippingLabel = new ShippingLabel(UUID.fromString("1703ad67-f28e-4b4b-b437-a534000515b0"));
                shippingLabel.setGeneralId(1L);
                shippingLabel.setTransportDate("2020-02-02");
                shippingLabel.setTransportDeliveryDate("2020-02-02");
                shippingLabel.setFarmerId(UUID.fromString("e6c9b529-b44f-4809-9c25-fb15f4e32795"));
                shippingLabel.setTruckId(UUID.fromString("2239de3c-17d7-4d39-9182-54aee9c71476"));
                shippingLabel.setTransportDriverId(UUID.fromString("e8f2ac43-9cc4-40c3-9b9a-54522f5d7f0d"));
                shippingLabelRepo.save(shippingLabel);

                message(shippingLabel);
            }
            log.info("ShippingLabel seeding done, seeded: " +  (this.id - 1) + " shipping labels.");

        } else {
            log.info("ShippingLabel seeding not required");
        }

        List<ShippingLabel> shippingLabelss = shippingLabelRepo.findAll();
        return new HashSet<>();
    }
}
