package flowcontrol.article.controller.assembler;

import flowcontrol.article.controller.PalletTypeController;
import flowcontrol.article.model.entity.PalletType;
import flowcontrol.article.model.response.PalletTypeResponse;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PalletTypeAssembler extends BaseAssembler<PalletType, PalletTypeResponse> {

    @Override
    public PalletTypeResponse toModel(PalletType palletType) {
        PalletTypeResponse response = PalletTypeResponse.builder()
                .name(palletType.getName())
                .weight(palletType.getWeight())
                .price(palletType.getPrice())
                .build();

        response
                .add(linkTo(methodOn(PalletTypeController.class)
                        .getById(palletType.getId()))
                        .withSelfRel());

        return response;
    }
}
