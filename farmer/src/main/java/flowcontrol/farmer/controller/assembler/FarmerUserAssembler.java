package flowcontrol.farmer.controller.assembler;

import flowcontrol.farmer.controller.CellController;
import flowcontrol.farmer.model.entity.Cell;
import flowcontrol.farmer.model.entity.FarmerUser;
import flowcontrol.farmer.model.response.CellResponse;
import flowcontrol.farmer.model.response.FarmerUserResponse;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class FarmerUserAssembler extends BaseAssembler<FarmerUser, FarmerUserResponse>{

    private final FarmerAssembler farmerAssembler;
    public FarmerUserAssembler(FarmerAssembler farmerAssembler) {
        this.farmerAssembler = farmerAssembler;
    }

    @Override
    public FarmerUserResponse toModel(FarmerUser farmerUser) {
        FarmerUserResponse response = FarmerUserResponse.builder()
                .userId(farmerUser.getUserId())
                .email(farmerUser.getEmail())
                .farmer(farmerAssembler.toModel(farmerUser.getFarmer()))
                .build();

//        response
//                .add(linkTo(methodOn(FarmerController.class)
//                        .getById(cell.getFarmer().getId(), cell.getId()))
//                        .withSelfRel());

        return response;
    }
}