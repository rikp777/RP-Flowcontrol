package flowcontrol.transport.controllers.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

public class ShippingLabelAssembler implements RepresentationModelAssembler {
    @Override
    public RepresentationModel<?> toModel(Object entity) {
        return null;
    }

    @Override
    public CollectionModel toCollectionModel(Iterable entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
