package flowcontrol.farmer.model.entity.seeder;

import flowcontrol.farmer.model.entity.Cell;
import flowcontrol.farmer.model.entity.Certificate;
import flowcontrol.farmer.model.entity.Farmer;
import flowcontrol.farmer.model.entity.FarmerUser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class Seeder {
    @Bean
    CommandLineRunner commandLineRunner(
           FarmerSeeder farmerSeeder,
           CertificateSeeder certificateSeeder,
           CellSeeder cellSeeder,
           FarmerUserSeeder farmerUserSeeder
    ) {
        return arts -> {
            UtilSeeder util = new UtilSeeder();
            // Certificate
            Set<Certificate> certificates = certificateSeeder.run();
            util.setCertificates(certificates);

            // Farmer
            Set<Farmer> farmers = farmerSeeder.run(util);
            util.setFarmers(farmers);

            // Cell
            Set<Cell> cells = cellSeeder.run(util);
            util.setCells(cells);

            // Cell
            Set<FarmerUser> farmerUsers = farmerUserSeeder.run(util);

        };
    }
}
