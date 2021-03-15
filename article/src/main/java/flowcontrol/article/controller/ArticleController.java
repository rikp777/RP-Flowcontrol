package flowcontrol.article.controller;

import flowcontrol.article.exception.ResourceNotFoundException;
import flowcontrol.article.model.entity.Article;
import flowcontrol.article.model.response.ApiResponse;
import flowcontrol.article.model.response.ArticleResponse;
import flowcontrol.article.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/articles")
@AllArgsConstructor
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
}
