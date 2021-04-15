package flowcontrol.gateway.model.entity.seeder;


import flowcontrol.gateway.model.entity.Role;
import flowcontrol.gateway.model.entity.enumeration.RoleType;
import flowcontrol.gateway.repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
@Slf4j
@AllArgsConstructor
public class RoleSeeder {

    private final RoleRepository roleRepository;


    public void run() {
        Role user = new Role();
        user.setRoleType(RoleType.ROLE_USER);
        roleRepository.save(user);
        

        Role admin = new Role();
        admin.setRoleType(RoleType.ROLE_ADMIN);
        roleRepository.save(admin);


        List<Role> roles = roleRepository.findAll();
    }
}

