package flowcontrol.article.controller.assembler;

import flowcontrol.article.controller.GroupController;
import flowcontrol.article.model.entity.Cask;
import flowcontrol.article.model.entity.Color;
import flowcontrol.article.model.entity.Group;
import flowcontrol.article.model.response.CaskResponse;
import flowcontrol.article.model.response.ColorResponse;
import flowcontrol.article.model.response.GroupResponse;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ColorAssembler extends BaseAssembler<Color, ColorResponse> {

    @Override
    public ColorResponse toModel(Color color) {
        ColorResponse response = ColorResponse.builder()
                .name(color.getName())
                .build();

        response
                .add(linkTo(methodOn(GroupController.class)
                        .getById(color.getId()))
                        .withSelfRel());

        return response;
    }
}
