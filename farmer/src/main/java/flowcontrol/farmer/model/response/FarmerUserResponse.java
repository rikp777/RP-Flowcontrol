package flowcontrol.farmer.model.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import flowcontrol.farmer.model.entity.Farmer;
import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Builder
@Data
@Relation(itemRelation = "farmer_user", collectionRelation = "farmer_users")
public class FarmerUserResponse extends RepresentationModel<FarmerResponse> {

    private FarmerResponse farmer;

    @JsonProperty("user_id")
    private Long userId;

    private String email;
}
