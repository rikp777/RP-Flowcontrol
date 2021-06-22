package flowcontrol.article.repository;

import flowcontrol.article.model.entity.PalletType;
import flowcontrol.article.model.entity.SortType;
import flowcontrol.article.repository.generic.AbstractBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SortTypeRepository extends AbstractBaseRepository<SortType, Long> {
    Optional<SortType> findByName(String name);
}
