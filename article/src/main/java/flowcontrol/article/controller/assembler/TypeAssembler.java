package flowcontrol.article.controller.assembler;

import flowcontrol.article.controller.TypeController;
import flowcontrol.article.model.entity.Type;
import flowcontrol.article.model.response.TypeResponse;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TypeAssembler extends BaseAssembler<Type, TypeResponse> {

    @Override
    public TypeResponse toModel(Type type) {
        TypeResponse response = TypeResponse.builder()
                .name(type.getName())
                .build();

        response
                .add(linkTo(methodOn(TypeController.class)
                        .getById(type.getId()))
                        .withSelfRel());

        return response;
    }
}
