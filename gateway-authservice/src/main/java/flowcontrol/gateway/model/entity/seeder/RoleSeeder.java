package flowcontrol.gateway.model.entity.seeder;


import com.google.common.collect.Sets;
import flowcontrol.gateway.config.seeder.SeederConfig;
import flowcontrol.gateway.model.entity.Role;
import flowcontrol.gateway.model.entity.enumeration.RoleType;
import flowcontrol.gateway.repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.UUID;


@Configuration
@Slf4j
public class RoleSeeder {
    private SeederConfig seederConfig;
    private final RoleRepository roleRepository;
    private int id = 1;

    public RoleSeeder(RoleRepository roleRepository, SeederConfig seederConfig){
        this.roleRepository = roleRepository;
        this.seederConfig = seederConfig;
    }

    public void run() {
        if(this.seederConfig.isInInsetDataMode()){
            if(roleRepository.findById(UUID.fromString("2950cbec-b8b3-4a9e-8847-b2c92418fc42")).isEmpty()) {
                Role user = new Role(UUID.fromString("2950cbec-b8b3-4a9e-8847-b2c92418fc42"));
                user.setRoleType(RoleType.ROLE_USER);
                roleRepository.save(user);
            }

            if(roleRepository.findById(UUID.fromString("dc7d739a-2bc6-426b-9afc-7f54cc788d19")).isEmpty()) {
                Role admin = new Role(UUID.fromString("dc7d739a-2bc6-426b-9afc-7f54cc788d19"));
                admin.setRoleType(RoleType.ROLE_ADMIN);
                roleRepository.save(admin);
            }

            log.info("Role seeding done, seeded: " +  (this.id - 1) + " roles.");

        } else {
            log.info("Role seeding not required");
        }
    }
}

