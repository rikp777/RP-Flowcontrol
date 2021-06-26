package flowcontrol.article.repository;

import flowcontrol.article.model.entity.Article;
import flowcontrol.article.repository.generic.AbstractBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ArticleRepository extends AbstractBaseRepository<Article, UUID> {

    Optional<Article> findByExcelCode(String excelCode);
}
