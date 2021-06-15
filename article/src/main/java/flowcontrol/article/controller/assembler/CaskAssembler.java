package flowcontrol.article.controller.assembler;


import flowcontrol.article.controller.CaskController;
import flowcontrol.article.controller.ColorController;
import flowcontrol.article.model.entity.Cask;
import flowcontrol.article.model.response.CaskResponse;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CaskAssembler extends BaseAssembler<Cask, CaskResponse> {

    @Override
    public CaskResponse toModel(Cask cask) {
        CaskResponse response = CaskResponse.builder()
                .excelCode(cask.getExcelCode())
                .name(cask.getName())
                .description(cask.getDescription())
                .material(cask.getMaterial())
                .maxFillingQuantity(cask.getMaxFillingQuantity())
                .weight(cask.getWeight())
                .color(cask.getColor().getName())
                .build();

        response
                .add(linkTo(methodOn(CaskController.class)
                        .getById(cask.getId()))
                        .withSelfRel());

        if(cask.getColor() != null){
            Long id = cask.getColor().getId();
            response
                    .add(linkTo(methodOn(ColorController.class)
                            .getById(id))
                            .withRel("color"));
        }

        if(cask.getArticles() != null){
            response
                    .add(linkTo(methodOn(CaskController.class)
                            .getAllBelongingArticles(cask.getId()))
                            .withRel("articles"));
        }

        return response;
    }
}
