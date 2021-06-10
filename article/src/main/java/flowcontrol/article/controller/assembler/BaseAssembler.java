package flowcontrol.article.controller.assembler;

import flowcontrol.article.model.entity.Article;
import flowcontrol.article.model.response.ArticleResponse;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

public abstract class BaseAssembler<O, R extends RepresentationModel<?>> implements RepresentationModelAssembler<O, R> {

    public CollectionModel<R> collection(Iterable<? extends O> entities){
        CollectionModel<R> collection = RepresentationModelAssembler.super.toCollectionModel(entities);
        return collection;
    }
}
