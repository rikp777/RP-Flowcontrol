package flowcontrol.farmer.model.entity.seeder;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import flowcontrol.farmer.config.seeder.SeederConfig;
import flowcontrol.farmer.model.entity.Cell;
import flowcontrol.farmer.model.entity.Certificate;
import flowcontrol.farmer.repository.CertificateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Configuration
@Slf4j
public class CertificateSeeder {
    private final CertificateRepository certificateRepo;
    private SeederConfig seederConfig;

    private int id = 1;

    public CertificateSeeder(CertificateRepository certificateRepo, SeederConfig seederConfig) {
        this.certificateRepo = certificateRepo;
        this.seederConfig = seederConfig;
    }

    private void message(Certificate certificate){
        if(this.seederConfig.isInDebugMode())
            UtilSeeder.sendMessage("Certificate seeder", this.id, certificate.getName(), certificate.getId());
        this.id++;
    }

    public Set<Certificate> run() {
        if(this.seederConfig.isInInsetDataMode()) {
            if(certificateRepo.findById(UUID.fromString("ea1b56bb-eedf-45c9-8be0-1f2a5d418d31")).isEmpty()){
                Certificate certificate = new Certificate(UUID.fromString("ea1b56bb-eedf-45c9-8be0-1f2a5d418d31"));
                certificate.setName("Fair Produce");
                certificateRepo.save(certificate);

                message(certificate);
            }

            if(certificateRepo.findById(UUID.fromString("c918de8f-51c8-46fe-b212-13884e2ce14a")).isEmpty()){
                Certificate certificate = new Certificate(UUID.fromString("c918de8f-51c8-46fe-b212-13884e2ce14a"));
                certificate.setName("GGN/GLN");
                certificateRepo.save(certificate);

                message(certificate);
            }

            if(certificateRepo.findById(UUID.fromString("116ab82d-9390-45e9-b9c1-c858bc858f6c")).isEmpty()){
                Certificate certificate = new Certificate(UUID.fromString("116ab82d-9390-45e9-b9c1-c858bc858f6c"));
                certificate.setName("SKAL");
                certificateRepo.save(certificate);

                message(certificate);
            }

            if(certificateRepo.findById(UUID.fromString("6f45ef90-f63c-4c6b-8123-066c624a2267")).isEmpty()){
                Certificate certificate = new Certificate(UUID.fromString("6f45ef90-f63c-4c6b-8123-066c624a2267"));
                certificate.setName("Planet Proof");
                certificateRepo.save(certificate);

                message(certificate);
            }

            log.info("Certificate seeding done, seeded: " +  (this.id - 1) + " certificates.");
        } else {
            log.info("Certificate seeding not required");
        }
        return Sets.newHashSet(certificateRepo.findAll());
    }
}
