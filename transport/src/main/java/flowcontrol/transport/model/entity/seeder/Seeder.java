package flowcontrol.transport.model.entity.seeder;

import flowcontrol.transport.model.entity.PalletLabel;
import flowcontrol.transport.model.entity.ShippingLabel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class Seeder {
    @Bean
    CommandLineRunner commandLineRunner(
            PalletLabelSeeder palletLabelSeeder,
            ShippingLabelSeeder shippingLabelSeeder
    ) {
        return arts -> {
            UtilSeeder util = new UtilSeeder();

            // Shipping labels
            Set<ShippingLabel> shippingLabels = shippingLabelSeeder.run(util);
            util.setShippingLabels(shippingLabels);

            // Pallet labels
            Set<PalletLabel> palletLabels = palletLabelSeeder.run(util);
            util.setPalletLabels(palletLabels);
        };
    }
}

