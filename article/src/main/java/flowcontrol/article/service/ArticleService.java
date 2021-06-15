package flowcontrol.article.service;

import flowcontrol.article.model.entity.Article;
import flowcontrol.article.model.mapper.ArticleMapper;
import flowcontrol.article.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Slf4j
public class ArticleService extends BaseService<Article> {

    @Autowired
    ArticleMapper articleMapper;

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        super(articleRepository);
        this.articleRepository = articleRepository;
    }

    @Async
    @Override
    public Optional<Article> createOrUpdate(Article article){
        return Optional.of(articleRepository.save(article));
        //https://stackoverflow.com/questions/41125384/copy-non-null-properties-from-one-object-to-another-using-beanutils-or-similar
    }

    @Transactional
    public boolean isAlreadyPresentByExcelCode(String excelCode){
        return articleRepository.findByExcelCode(excelCode).isPresent();
    }

    @Async
    public Optional<Article> getById(String id){
        return articleRepository.findById(Long.parseLong(id));
    }
}
