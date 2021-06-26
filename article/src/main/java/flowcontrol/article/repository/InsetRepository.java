package flowcontrol.article.repository;

import flowcontrol.article.model.entity.Cask;
import flowcontrol.article.model.entity.Group;
import flowcontrol.article.model.entity.Inset;
import flowcontrol.article.repository.generic.AbstractBaseRepository;

import java.util.Optional;
import java.util.UUID;

public interface InsetRepository extends AbstractBaseRepository<Inset, UUID> {
    Optional<Inset> findByName(String name);
    Optional<Inset> findByExcelCode(String excelCode);
}
