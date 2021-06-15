package flowcontrol.farmer.controller.assembler;

import flowcontrol.farmer.controller.CellController;
import flowcontrol.farmer.controller.FarmerCertificateController;
import flowcontrol.farmer.controller.FarmerController;
import flowcontrol.farmer.model.entity.Farmer;
import flowcontrol.farmer.model.response.FarmerResponse;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class FarmerAssembler extends BaseAssembler<Farmer, FarmerResponse>{

    @Override
    public FarmerResponse toModel(Farmer farmer) {
        FarmerResponse response = FarmerResponse.builder()
                .name(farmer.getName())
        .street(farmer.getStreet())
        .street_number(farmer.getStreet_number())
        .place(farmer.getPlace())
        .zipCode(farmer.getZipCode())
        .country(farmer.getCountry())
        .province(farmer.getProvince())
        .email(farmer.getEmail())
        .phone(farmer.getPhone())
        .fax(farmer.getFax())
        .kvk(farmer.getKvk())
                .build();

        response
                .add(linkTo(methodOn(FarmerController.class)
                        .getById(farmer.getId()))
                        .withSelfRel());


        //region relationships
        if(farmer.getFarmerCertificates() != null){
            response
                    .add(linkTo(methodOn(FarmerCertificateController.class)
                            .getAll(farmer.getId()))
                            .withRel("farmer_certificates"));
        }

        if(farmer.getCells() != null){
            response
                    .add(linkTo(methodOn(CellController.class)
                            .getAll(farmer.getId()))
                            .withRel("cells"));
        }


        return response;
    }
}
