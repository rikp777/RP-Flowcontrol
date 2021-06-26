package flowcontrol.article.repository;

import flowcontrol.article.model.entity.Article;
import flowcontrol.article.model.entity.Cask;
import flowcontrol.article.repository.generic.AbstractBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CaskRepository extends AbstractBaseRepository<Cask, UUID> {

    Optional<Cask> findByExcelCode(String excelCode);
}
