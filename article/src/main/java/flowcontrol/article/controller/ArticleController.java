package flowcontrol.article.controller;

import flowcontrol.article.model.entity.Article;
import flowcontrol.article.model.response.ApiResponse;
import flowcontrol.article.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/article")
@AllArgsConstructor
public class ArticleController {

    private final ArticleService articleService;


    @GetMapping
    public List<Article> getAll(){
        return articleService.getAll();
    }
}
