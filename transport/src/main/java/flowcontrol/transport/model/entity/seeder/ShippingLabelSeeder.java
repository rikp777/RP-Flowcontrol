package flowcontrol.transport.model.entity.seeder;

import flowcontrol.transport.model.entity.PalletLabel;
import flowcontrol.transport.model.entity.ShippingLabel;
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
public class ShippingLabelSeeder {
    @Autowired
    private final ShippingLabelRepository shippingLabelRepo;

    public ShippingLabelSeeder(ShippingLabelRepository shippingLabelRepo) {
        this.shippingLabelRepo = shippingLabelRepo;
    }

    public Set<ShippingLabel> run(UtilSeeder util) {
        List<ShippingLabel> shippingLabels = Arrays.asList(
                ShippingLabel.builder()
                        .generalId(1L)
                        .transportDate("2020-02-02")
                        .transportDeliveryDate("2020-02-02")
                        .farmerId(1L)
                        .truckId(1L)
                        .transportDriverId(1L)
                        .build()

        );

        if(shippingLabelRepo.findAll().size() == 0){
            log.info("Shippinglabel done seeding");
            shippingLabelRepo.saveAll(shippingLabels);
        }

        List<ShippingLabel> shippingLabelss = shippingLabelRepo.findAll();
        return new HashSet<>();
    }
}
