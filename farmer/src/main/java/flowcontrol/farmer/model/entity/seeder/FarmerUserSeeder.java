package flowcontrol.farmer.model.entity.seeder;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import flowcontrol.farmer.model.entity.Farmer;
import flowcontrol.farmer.model.entity.FarmerCertificate;
import flowcontrol.farmer.model.entity.FarmerUser;
import flowcontrol.farmer.repository.FarmerCertificateRepository;
import flowcontrol.farmer.repository.FarmerRepository;
import flowcontrol.farmer.repository.FarmerUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Configuration
@Slf4j
public class FarmerUserSeeder {
    @Autowired
    private final FarmerUserRepository farmerUserRepo;


    public FarmerUserSeeder(FarmerUserRepository farmerUserRepo) {
        this.farmerUserRepo = farmerUserRepo;
    }


    public Set<FarmerUser> run(UtilSeeder util) {
        Farmer farmerOne = util.findFarmerInSet("Test farmer one");
        Farmer farmerTwo = util.findFarmerInSet("Test farmer two");

        List<FarmerUser> farmerUsers = Arrays.asList(
                FarmerUser.builder()
                        .email("test@flowcontrol.com")
                        .userId(1l)
                        .farmer(farmerOne)
                        .build(),
                FarmerUser.builder()
                        .email("test@flowcontrol.com")
                        .userId(1l)
                        .farmer(farmerTwo)
                        .build()
        );


        if (Iterables.size(farmerUserRepo.findAll()) == 0) {
            log.info("Farmer done seeding");
            farmerUserRepo.saveAll(farmerUsers);
        }

        return Sets.newHashSet(farmerUserRepo.findAll());
    }
}
