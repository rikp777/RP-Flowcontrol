package flowcontrol.article.controller;

import flowcontrol.article.controller.assembler.ArticleAssembler;
import flowcontrol.article.exception.ResourceNotFoundException;
import flowcontrol.article.model.entity.Article;
import flowcontrol.article.model.mapper.ArticleMapper;
import flowcontrol.article.model.request.article.CreateArticleRequest;
import flowcontrol.article.model.request.article.UpdateArticleRequest;
import flowcontrol.article.model.response.ArticleResponse;
import flowcontrol.article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    @Autowired
    public ArticleController(ArticleService articleService, ArticleAssembler articleAssembler, ArticleMapper articleMapper) {
        super(articleService, articleAssembler, articleMapper);
        this.articleAssembler = articleAssembler;
        this.articleMapper = articleMapper;
        this.articleService = articleService;
    }



    //region CRUD
//    @PostMapping(
//            consumes = {
//                    MediaType.APPLICATION_JSON_VALUE,
//                    MediaType.APPLICATION_XML_VALUE,
//                    "multipart/form-data"
//            }
//    )//CREATE
//    public ResponseEntity<ArticleResponse> create(@Valid @ModelAttribute("article") CreateArticleRequest articleRequest){
//        Article mappedArticle = articleMapper.toEntity(articleRequest);
//        return articleService.createOrUpdate(mappedArticle)
//                .map(article -> ResponseEntity.ok(articleAssembler.toModel(article)))
//                .orElseThrow(() ->
//                        new IllegalArgumentException("Something went wrong")
//                );
//    }

//    @PutMapping(
//            path = "/{articleId}",
//            consumes = {
//                    MediaType.APPLICATION_JSON_VALUE,
//                    MediaType.APPLICATION_XML_VALUE,
//                    "multipart/form-data"
//            }
//    ) //UPDATE
//    public ResponseEntity<ArticleResponse> update(@PathVariable String articleId, @Valid @ModelAttribute("article") UpdateArticleRequest articleRequest){
//        return articleService.getById(Long.parseLong(articleId))
//                .map(cask -> {
//                    Article mappedArticle = articleMapper.mapUpdatesToOriginal(articleRequest, cask);
//                    return articleService.createOrUpdate(mappedArticle)
//                            .map(updatedArticle -> ResponseEntity.ok(articleAssembler.toModel(updatedArticle)))
//                            .orElseThrow(() ->
//                                    new IllegalArgumentException("Something went wrong")
//                            );
//                })
//                .orElseThrow(() ->
//                        new ResourceNotFoundException("Article", "Id", articleId)
//                );
//    }
//    @DeleteMapping(
//            path = "/{articleId}",
//            consumes = {
//                    MediaType.APPLICATION_JSON_VALUE,
//                    MediaType.APPLICATION_XML_VALUE,
//            }
//    )//DELETE
//    public ResponseEntity<String> delete(@PathVariable String articleId){
//        return articleService.getById(Long.parseLong(articleId))
//                .map(article -> {
//                    articleService.delete(article);
//                    return ResponseEntity.ok("Deleted article [" + articleId + "]");
//                })
//                .orElseThrow(() ->
//                        new ResourceNotFoundException("Article", "Id", articleId)
//                );
//    }
    //endregion

    //region Relations
        //none
    //endregion
}
