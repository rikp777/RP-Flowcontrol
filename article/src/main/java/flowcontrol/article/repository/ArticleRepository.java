package flowcontrol.article.repository;

import flowcontrol.article.model.entity.Article;
import flowcontrol.article.model.entity.Cask;
import flowcontrol.article.repository.Generic.AbstractBaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends AbstractBaseRepository<Article, Long> {

    Optional<Article> findArticleByExcelCode(String excelCode);
}
