package flowcontrol.article.controller;

import flowcontrol.article.controller.assembler.ArticleAssembler;
import flowcontrol.article.model.entity.Article;
import flowcontrol.article.model.mapper.ArticleMapper;
import flowcontrol.article.model.request.article.CreateArticleRequest;
import flowcontrol.article.model.request.article.UpdateArticleRequest;
import flowcontrol.article.model.response.ArticleResponse;
import flowcontrol.article.service.ArticleService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/articles")
@Validated
public class ArticleController extends BaseController<ArticleResponse, Article, CreateArticleRequest, UpdateArticleRequest, ArticleMapper>{

    //Assemblers
    private final ArticleAssembler articleAssembler;

    //Mappers
    private final ArticleMapper articleMapper;

    //Services
    private final ArticleService articleService;

    //Constructor
    public ArticleController(ArticleService articleService, ArticleAssembler articleAssembler, ArticleMapper articleMapper) {
        super(articleService, articleAssembler, articleMapper);
        this.articleAssembler = articleAssembler;
        this.articleMapper = articleMapper;
        this.articleService = articleService;
    }

    //region API endpoints
    //endregion

    //region Relations
        //none
    //endregion
}
