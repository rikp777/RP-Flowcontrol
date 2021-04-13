package flowcontrol.gateway.service;

import flowcontrol.gateway.model.entity.Role;
import flowcontrol.gateway.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;


    public Collection<Role> findAll(){
        return roleRepository.findAll();
    }
}
