package flowcontrol.article.service;

import flowcontrol.article.model.entity.Article;
import flowcontrol.article.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ArticleService extends BaseService<Article> {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        super(articleRepository);
        this.articleRepository = articleRepository;
    }

    @Transactional
    public boolean isAlreadyPresentByExcelCode(String excelCode){
        return articleRepository.findByExcelCode(excelCode).isPresent();
    }
}
