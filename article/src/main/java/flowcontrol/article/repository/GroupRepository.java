package flowcontrol.article.repository;

import flowcontrol.article.model.entity.Color;
import flowcontrol.article.model.entity.Group;
import flowcontrol.article.repository.generic.AbstractBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GroupRepository extends AbstractBaseRepository<Group, UUID> {
    Optional<Group> findByName(String name);
}
