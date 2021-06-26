package flowcontrol.article.repository;

import flowcontrol.article.model.entity.Inset;
import flowcontrol.article.model.entity.PalletType;
import flowcontrol.article.repository.generic.AbstractBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PalletTypeRepository extends AbstractBaseRepository<PalletType, UUID> {
    Optional<PalletType> findByName(String name);
}
