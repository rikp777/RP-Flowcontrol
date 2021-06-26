package flowcontrol.article.repository;

import flowcontrol.article.model.entity.PalletType;
import flowcontrol.article.model.entity.SortType;
import flowcontrol.article.repository.generic.AbstractBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SortTypeRepository extends AbstractBaseRepository<SortType, UUID> {
    Optional<SortType> findByName(String name);
}
