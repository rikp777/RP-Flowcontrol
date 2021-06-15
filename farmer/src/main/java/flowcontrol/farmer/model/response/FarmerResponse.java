package flowcontrol.farmer.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Builder
@Data
@Relation(itemRelation = "farmer", collectionRelation = "farmers")
public class FarmerResponse extends RepresentationModel<FarmerResponse> {
    @JsonProperty("name")
    private String name;

    @JsonProperty("street")
    private String street;

    @JsonProperty("number")
    private String street_number;

    @JsonProperty("place")
    private String place;

    @JsonProperty("zip_code")
    private String zipCode;

    @JsonProperty("country")
    private String country;

    @JsonProperty("province")
    private String province;

    @JsonProperty("email")
    private String email;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("fax")
    private String fax;

    @JsonProperty("kvk")
    private String kvk;
}
