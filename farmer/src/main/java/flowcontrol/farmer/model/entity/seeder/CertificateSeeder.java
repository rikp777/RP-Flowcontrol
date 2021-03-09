package flowcontrol.farmer.model.entity.seeder;

import flowcontrol.farmer.model.entity.Certificate;
import flowcontrol.farmer.model.entity.Farmer;
import flowcontrol.farmer.repository.CertificateRepository;
import flowcontrol.farmer.repository.FarmerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
@Slf4j
public class CertificateSeeder {
    @Autowired
    private final CertificateRepository certificateRepo;


    public CertificateSeeder(CertificateRepository certificateRepo) {
        this.certificateRepo = certificateRepo;
    }

    public Set<Certificate> run() {
        List<Certificate> certificates = Arrays.asList(
                Certificate.builder()
                        .name("Fair Produce")
                        .build(),
                Certificate.builder()
                        .name("GGN/GLN")
                        .build(),
                Certificate.builder()
                        .name("SKAL")
                        .build(),
                Certificate.builder()
                        .name("Planet Proof")
                        .build()
        );

        if(certificateRepo.findAll().size() == 0){
            log.info("Certificate done seeding");
            certificateRepo.saveAll(certificates);
        }

        return new HashSet<>(certificateRepo.findAll());
    }
}
