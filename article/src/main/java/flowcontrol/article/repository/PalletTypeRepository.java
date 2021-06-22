package flowcontrol.article.repository;

import flowcontrol.article.model.entity.Inset;
import flowcontrol.article.model.entity.PalletType;
import flowcontrol.article.repository.generic.AbstractBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PalletTypeRepository extends AbstractBaseRepository<PalletType, Long> {
    Optional<PalletType> findByName(String name);
}
