package flowcontrol.farmer.controller.assembler;

import flowcontrol.farmer.controller.CellController;
import flowcontrol.farmer.controller.FarmerController;
import flowcontrol.farmer.model.entity.Cell;
import flowcontrol.farmer.model.entity.Farmer;
import flowcontrol.farmer.model.response.CellResponse;
import flowcontrol.farmer.model.response.FarmerResponse;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CellAssembler extends BaseAssembler<Cell, CellResponse>{

    @Override
    public CellResponse toModel(Cell cell) {
        CellResponse response = CellResponse.builder()
                .name(cell.getName())
                .description(cell.getDescription())
                .build();

        response
                .add(linkTo(methodOn(CellController.class)
                        .getById(cell.getFarmer().getId(), cell.getId()))
                        .withSelfRel());

        return response;
    }
}
