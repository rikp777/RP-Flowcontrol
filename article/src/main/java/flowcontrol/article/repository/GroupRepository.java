package flowcontrol.article.repository;

import flowcontrol.article.model.entity.Group;
import flowcontrol.article.repository.generic.AbstractBaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends AbstractBaseRepository<Group, Long> {
}
