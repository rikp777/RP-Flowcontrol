package flowcontrol.article.repository;

import flowcontrol.article.model.entity.SortType;
import flowcontrol.article.model.entity.Type;
import flowcontrol.article.repository.generic.AbstractBaseRepository;

import java.util.Optional;
import java.util.UUID;

public interface TypeRepository extends AbstractBaseRepository<Type, UUID> {
    Optional<Type> findByName(String name);
}
