package flowcontrol.farmer.controller.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

public abstract class BaseAssembler<O, R extends RepresentationModel<?>> implements RepresentationModelAssembler<O, R> {

    public CollectionModel<R> collection(Iterable<? extends O> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}

