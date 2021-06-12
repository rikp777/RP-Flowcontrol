package flowcontrol.article.repository;

import flowcontrol.article.model.entity.SortType;
import flowcontrol.article.repository.generic.AbstractBaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SortTypeRepository extends AbstractBaseRepository<SortType, Long> {
}
