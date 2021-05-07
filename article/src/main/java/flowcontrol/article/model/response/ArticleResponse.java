package flowcontrol.article.model.response;

import flowcontrol.article.model.entity.Article;
import flowcontrol.article.model.entity.Inset;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleResponse {
    private String name;

    public void setName(Article article) {
        String name = "";

        if(article.getColor() != null){
            name = name + article.getColor().getName() + " ";
        }
        if(article.getGroup() != null){
            if(!article.getGroup().getName().isEmpty()){
                name = name + article.getGroup().getName() + " ";
            }
        }
        if(article.getType() != null){
            name = name + article.getType().getName() + " ";
        }
        if(article.getSortType() != null){
            name = name + article.getSortType().getName() + " ";
        }
        if(article.getInsetLimit() != null){
            name = name + article.getInsetLimit() + " x ";
        }
        if(article.getInsetGram() != null){
            name = name + article.getInsetGram() + " ";
        }
        if(article.getInset() != null){
            Inset inset = article.getInset();
            name = name + inset.getColor().getName() + " ";
        }
        if(article.getExcelCode() != null){
            name = name + "(" + article.getExcelCode() + ")";
        }

        this.name = name;
    }
}
