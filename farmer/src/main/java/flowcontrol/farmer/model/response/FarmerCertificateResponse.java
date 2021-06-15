package flowcontrol.farmer.model.response;

import flowcontrol.farmer.model.entity.Certificate;
import flowcontrol.farmer.model.entity.Farmer;
import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Builder
@Data
@Relation(itemRelation = "Farmer_certificate_response", collectionRelation = "Farmer_certificate_responses")
public class FarmerCertificateResponse extends RepresentationModel<FarmerCertificateResponse> {

    private String code;
    private Certificate certificate;
}