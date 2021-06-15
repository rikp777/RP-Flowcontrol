package flowcontrol.article.controller.assembler;

import flowcontrol.article.controller.SortTypeController;
import flowcontrol.article.model.entity.SortType;
import flowcontrol.article.model.response.SortTypeResponse;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SortTypeAssembler extends BaseAssembler<SortType, SortTypeResponse> {

    @Override
    public SortTypeResponse toModel(SortType sortType) {
        SortTypeResponse response = SortTypeResponse.builder()
                .name(sortType.getName())
                .description(sortType.getDescription())
                .build();

        response
                .add(linkTo(methodOn(SortTypeController.class)
                        .getById(sortType.getId()))
                        .withSelfRel());

        return response;
    }
}
