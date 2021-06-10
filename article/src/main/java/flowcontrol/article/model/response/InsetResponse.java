package flowcontrol.article.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Relation(itemRelation = "inset", collectionRelation = "insets")
public class InsetResponse extends RepresentationModel<InsetResponse> {
    @JsonProperty("excel_code")
    private String excelCode;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("max_inset_amount")
    private Integer maxInsetAmount;

    @JsonProperty("color")
    private String color;
}
