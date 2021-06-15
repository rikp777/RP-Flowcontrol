package flowcontrol.farmer.controller.assembler;

import flowcontrol.farmer.controller.FarmerCertificateController;
import flowcontrol.farmer.controller.FarmerController;
import flowcontrol.farmer.model.entity.Farmer;
import flowcontrol.farmer.model.entity.FarmerCertificate;
import flowcontrol.farmer.model.response.FarmerCertificateResponse;
import flowcontrol.farmer.model.response.FarmerResponse;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class FarmerCertificateAssembler extends BaseAssembler<FarmerCertificate, FarmerCertificateResponse>{

    @Override
    public FarmerCertificateResponse toModel(FarmerCertificate farmerCertificate) {
        FarmerCertificateResponse response = FarmerCertificateResponse.builder()
                .code(farmerCertificate.getCode())
//                .farmer(farmerCertificate.getFarmer())
                .certificate(farmerCertificate.getCertificate())
                .build();

        response
                .add(linkTo(methodOn(FarmerCertificateController.class)
                        .getById(farmerCertificate.getFarmer().getId(), farmerCertificate.getId()))
                        .withSelfRel());

        return response;
    }
}
