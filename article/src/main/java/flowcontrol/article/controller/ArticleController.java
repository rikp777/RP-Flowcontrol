package flowcontrol.article.controller;

import flowcontrol.article.controller.assembler.ArticleAssembler;
import flowcontrol.article.controller.assembler.BaseAssembler;
import flowcontrol.article.exception.ResourceNotFoundException;
import flowcontrol.article.model.entity.Article;
import flowcontrol.article.model.entity.Cask;
import flowcontrol.article.model.mapper.ArticleMapper;
import flowcontrol.article.model.request.article.CreateArticleRequest;
import flowcontrol.article.model.request.article.UpdateArticleRequest;
import flowcontrol.article.model.response.ArticleResponse;
import flowcontrol.article.model.response.CaskResponse;
import flowcontrol.article.service.ArticleService;
import flowcontrol.article.service.BaseService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/articles")
@Validated
public class ArticleController extends BaseController<ArticleResponse, Article>{

    //Assemblers
    private final ArticleAssembler articleAssembler;

    //Mappers
    private final ArticleMapper articleMapper;

    //Services
    private final ArticleService articleService;



    //Constructor
    @Autowired
    public ArticleController(ArticleService articleService, ArticleAssembler articleAssembler, ArticleMapper articleMapper) {
        super(articleService, articleAssembler);
        this.articleAssembler = articleAssembler;
        this.articleMapper = articleMapper;
        this.articleService = articleService;
    }



    //region CRUD
    @PostMapping(
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    "multipart/form-data"
            }
    )//CREATE
    public ResponseEntity create(@Valid @ModelAttribute("article") CreateArticleRequest articleRequest){

        Article article = articleMapper.toEntity(articleRequest);
        article = articleService.createOrUpdate(article).get();

        return ResponseEntity.ok(article);
    }

    @PutMapping(
            path = "/{articleId}",
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    "multipart/form-data"
            }
    ) //UPDATE
    public ResponseEntity update(@PathVariable String articleId, @Valid @ModelAttribute("article") UpdateArticleRequest articleRequest){

        Article original = articleService.getById(articleId).get();
        Article article = articleMapper.mapUpdatesToOriginal(articleRequest, original);

        article = articleService.createOrUpdate(article).get();

        return ResponseEntity.ok(article);
    }
    //endregion

    //region Relations
        //none
    //endregion
}
