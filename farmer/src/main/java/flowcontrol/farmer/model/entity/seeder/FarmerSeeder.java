package flowcontrol.farmer.model.entity.seeder;

import flowcontrol.farmer.model.entity.Farmer;
import flowcontrol.farmer.repository.FarmerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;


import java.util.*;

@Configuration
@Slf4j
public class FarmerSeeder {
    @Autowired
    private final FarmerRepository farmerRepo;


    public FarmerSeeder(FarmerRepository farmerRepo) {
        this.farmerRepo = farmerRepo;
    }

    public Set<Farmer> run() {
        List<Farmer> farmers = Arrays.asList(
            Farmer.builder()
                    .name("Test farmer")
                    .street("Test street")
                    .place("Panningen")
                    .zipCode("5728XL")
                    .country("Netherlands")
                    .province("Limburg")
                    .email("test@flowcontrol.com")
                    .phone("+31623456789")
                    .fax("+1234567890")
                    .kvk("ASDFGHJKL")
                    .build()
        );

        if(farmerRepo.findAll().size() == 0){
            farmerRepo.saveAll(farmers);
        }

        return new HashSet<>(farmerRepo.findAll());
    }
}
