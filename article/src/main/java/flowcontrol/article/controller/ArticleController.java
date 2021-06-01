package flowcontrol.article.controller;

import flowcontrol.article.exception.ResourceNotFoundException;
import flowcontrol.article.model.entity.Article;
import flowcontrol.article.model.request.CreateArticle;
import flowcontrol.article.model.response.ArticleResponse;
import flowcontrol.article.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/articles")
@AllArgsConstructor
@Validated
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    public Iterable<Article> getAll(){
        return articleService.getAll();
    }

    @GetMapping("/{articleId}")
    public ResponseEntity getById(@PathVariable Long articleId){
        System.out.println(articleId);

        return articleService.getById(articleId)
                .map(article -> {
                    ArticleResponse articleResponse = new ArticleResponse();
                    articleResponse.setName(article);
                    return ResponseEntity.ok(articleResponse);
                })
                .orElseThrow(() ->
                        new ResourceNotFoundException("Article", "Id", articleId)
                );
    }

    @PostMapping(
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    "multipart/form-data"
            }
    )
    public ResponseEntity create(@Valid @ModelAttribute("article") CreateArticle article){
        return ResponseEntity.ok("Article is valid");
    }

}
