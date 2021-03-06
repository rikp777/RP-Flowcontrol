package flowcontrol.article.service;

import flowcontrol.article.model.entity.Article;
import flowcontrol.article.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;


    public List<Article> getAll(){
        return articleRepository.findAll();
    }
}
