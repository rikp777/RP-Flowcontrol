package flowcontrol.gateway.repository;

import flowcontrol.gateway.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
