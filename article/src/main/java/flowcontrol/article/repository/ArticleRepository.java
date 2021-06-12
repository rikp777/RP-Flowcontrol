package flowcontrol.article.repository;

import flowcontrol.article.model.entity.Article;
import flowcontrol.article.repository.generic.AbstractBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends AbstractBaseRepository<Article, Long> {

    Optional<Article> findByExcelCode(String excelCode);
}
