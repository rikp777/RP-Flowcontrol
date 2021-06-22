package flowcontrol.article.repository;

import flowcontrol.article.model.entity.SortType;
import flowcontrol.article.model.entity.Type;
import flowcontrol.article.repository.generic.AbstractBaseRepository;

import java.util.Optional;

public interface TypeRepository extends AbstractBaseRepository<Type,  Long> {
    Optional<Type> findByName(String name);
}
