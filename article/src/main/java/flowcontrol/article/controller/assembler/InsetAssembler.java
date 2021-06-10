package flowcontrol.article.controller.assembler;

import flowcontrol.article.controller.ColorController;
import flowcontrol.article.controller.GroupController;
import flowcontrol.article.controller.InsetController;
import flowcontrol.article.model.entity.Group;
import flowcontrol.article.model.entity.Inset;
import flowcontrol.article.model.response.GroupResponse;
import flowcontrol.article.model.response.InsetResponse;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class InsetAssembler extends BaseAssembler<Inset, InsetResponse> {

    @Override
    public InsetResponse toModel(Inset inset) {
        InsetResponse response = InsetResponse.builder()
                .excelCode(inset.getExcelCode())
                .name(inset.getName())
                .description(inset.getDescription())
                .maxInsetAmount(inset.getMaxInsetAmount())
                .color(inset.getColor().getName())
                .build();

        response
                .add(linkTo(methodOn(InsetController.class)
                        .getById(inset.getId()))
                        .withSelfRel());

        if(inset.getColor() != null){
            Long id = inset.getColor().getId();
            response
                    .add(linkTo(methodOn(ColorController.class)
                            .getById(id))
                            .withRel("color"));
        }

        return response;
    }
}
