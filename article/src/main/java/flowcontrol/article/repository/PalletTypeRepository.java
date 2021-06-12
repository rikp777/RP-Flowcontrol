package flowcontrol.article.repository;

import flowcontrol.article.model.entity.PalletType;
import flowcontrol.article.repository.generic.AbstractBaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PalletTypeRepository extends AbstractBaseRepository<PalletType, Long> {
}
