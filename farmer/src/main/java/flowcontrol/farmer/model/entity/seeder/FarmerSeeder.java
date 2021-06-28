package flowcontrol.farmer.model.entity.seeder;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import flowcontrol.farmer.config.seeder.SeederConfig;
import flowcontrol.farmer.model.entity.Certificate;
import flowcontrol.farmer.model.entity.Farmer;
import flowcontrol.farmer.model.entity.FarmerCertificate;
import flowcontrol.farmer.model.entity.FarmerUser;
import flowcontrol.farmer.repository.FarmerCertificateRepository;
import flowcontrol.farmer.repository.FarmerRepository;
import flowcontrol.farmer.repository.FarmerUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;


import java.util.*;

@Configuration
@Slf4j
public class FarmerSeeder {

    private final FarmerRepository farmerRepo;
    private final FarmerUserRepository farmerUserRepo;
    private final FarmerCertificateRepository farmerCertificateRepository;

    private SeederConfig seederConfig;

    private int id = 1;

    public FarmerSeeder(FarmerRepository farmerRepo, FarmerCertificateRepository farmerCertificateRepository, FarmerUserRepository farmerUserRepo, SeederConfig seederConfig) {
        this.farmerRepo = farmerRepo;
        this.farmerUserRepo = farmerUserRepo;
        this.farmerCertificateRepository = farmerCertificateRepository;
        this.seederConfig = seederConfig;
    }

    private void message(Farmer farmer){
        if(this.seederConfig.isInDebugMode())
            UtilSeeder.sendMessage("Farmer seeder", this.id, farmer.getName(), farmer.getId());
        this.id++;
    }

    public Set<Farmer> run(UtilSeeder util) {
        if(this.seederConfig.isInInsetDataMode()){

            if(farmerRepo.findById(UUID.fromString("e6c9b529-b44f-4809-9c25-fb15f4e32795")).isEmpty()){
                Farmer farmer = new Farmer(UUID.fromString("e6c9b529-b44f-4809-9c25-fb15f4e32795"));
                farmer.setName("Test farmer one");
                farmer.setStreet("Test street one");
                farmer.setPlace("Horst");
                farmer.setZipCode("5728XL");
                farmer.setCountry("Netherlands");
                farmer.setProvince("Limburg");
                farmer.setEmail("test@flowcontrol.com");
                farmer.setPhone("+31623456789");
                farmer.setFax("+1234567890");
                farmer.setKvk("+ASDFGHJKL");

                farmerRepo.save(farmer);

                FarmerCertificate farmerCertificate = FarmerCertificate.builder()
                        .code("test")
                        .certificate(util.findCertificateInSet("Fair Produce"))
                        .farmer(farmer)
                        .build();

                farmerCertificateRepository.save(farmerCertificate);

                FarmerUser farmerUser =  FarmerUser.builder()
                        .email("test@flowcontrol.com")
                        .userId(1l)
                        .farmer(farmer)
                        .build();

                farmerUserRepo.save(farmerUser);

                message(farmer);
            }

            if(farmerRepo.findById(UUID.fromString("5c883f5b-35c0-4b4f-b68a-06536da12e5c")).isEmpty()){
                Farmer farmer = new Farmer(UUID.fromString("5c883f5b-35c0-4b4f-b68a-06536da12e5c"));
                farmer.setName("Test farmer two");
                farmer.setStreet("Test street two");
                farmer.setPlace("Venray");
                farmer.setZipCode("5788XL");
                farmer.setCountry("Netherlands");
                farmer.setProvince("Limburg");
                farmer.setEmail("test2@flowcontrol.com");
                farmer.setPhone("+31623786789");
                farmer.setFax("+1234569990");
                farmer.setKvk("+ASDFGSDKL");

                farmer = farmerRepo.save(farmer);

                FarmerCertificate farmerCertificate = FarmerCertificate.builder()
                        .code("test")
                        .certificate(util.findCertificateInSet("Fair Produce"))
                        .farmer(farmer)
                        .build();

                farmerCertificateRepository.save(farmerCertificate);

                FarmerUser farmerUser =  FarmerUser.builder()
                        .email("test@flowcontrol.com")
                        .userId(1l)
                        .farmer(farmer)
                        .build();

                farmerUserRepo.save(farmerUser);

                message(farmer);
            }

            log.info("Farmer seeding done, seeded: " +  (this.id - 1) + " farmers.");
        } else {
            log.info("Farmer seeding not required");
        }

        return Sets.newHashSet(farmerRepo.findAll());
    }
}
