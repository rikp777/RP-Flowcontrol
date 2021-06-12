package flowcontrol.article.controller.assembler;

import flowcontrol.article.controller.*;
import flowcontrol.article.model.entity.Article;
import flowcontrol.article.model.response.ArticleResponse;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ArticleAssembler extends BaseAssembler<Article, ArticleResponse> {


    @Override
    public ArticleResponse toModel(Article article) {
        ArticleResponse response = ArticleResponse.builder()
                .fullName(article.getFullName())
                .excelCode(article.getExcelCode())
                .additionalInfo(article.getAdditionalInfo())
                .biologic(article.isBiologic())
                .origin(article.getOrigin())
                .palletLimit(article.getPalletLimit())
                .insetGram(article.getInsetGram())
                .insetLimit(article.getInsetLimit())
                .build();

        response
                .add(linkTo(methodOn(ArticleController.class)
                        .getById(article.getId()))
                        .withSelfRel());


        //region relationships
        if(article.getPalletType() != null){
            Long id = article.getPalletType().getId();
            response
                    .add(linkTo(methodOn(PalletTypeController.class)
                            .getById(id))
                            .withRel("pallet_type"));
        }
        if(article.getCask() != null){
            Long id = article.getCask().getId();
            response
                    .add(linkTo(methodOn(CaskController.class)
                            .getById(id))
                            .withRel("cask"));
        }

        if(article.getGroup() != null){
            Long id = article.getGroup().getId();
            response
                    .add(linkTo(methodOn(GroupController.class)
                            .getById(id))
                            .withRel("group"));
        }

        if(article.getColor() != null){
            Long id = article.getColor().getId();
            response
                    .add(linkTo(methodOn(ColorController.class)
                            .getById(id))
                            .withRel("color"));
        }

        if(article.getInset() != null){
            Long id = article.getInset().getId();
            response
                    .add(linkTo(methodOn(InsetController.class)
                            .getById(id))
                            .withRel("inset"));
        }
        if(article.getType() != null){
            Long id = article.getType().getId();
            response
                    .add(linkTo(methodOn(TypeController.class)
                            .getById(id))
                            .withRel("type"));
        }

        if(article.getSortType() != null){
            Long id = article.getSortType().getId();
            response
                    .add(linkTo(methodOn(SortTypeController.class)
                            .getById(id))
                            .withRel("sortType"));
        }

        //endregion


        return response;
    }

    @Override
    public CollectionModel<ArticleResponse> toCollectionModel(Iterable<? extends Article> entities) {
        CollectionModel<ArticleResponse> articleResponse = collection(entities);
        if(articleResponse.getContent() != null && articleResponse.getContent().size() > 0){
            articleResponse.add(linkTo(methodOn(ArticleController.class).getAll()).withSelfRel());
        }

        return articleResponse;
    }
}
