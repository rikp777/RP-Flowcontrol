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
@Relation(itemRelation = "cask", collectionRelation = "casks")
public class CaskResponse extends RepresentationModel<CaskResponse> {
    @JsonProperty("excel_code")
    private String excelCode;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("weight")
    private Integer weight;

    @JsonProperty("max_filling_quantity")
    private Integer maxFillingQuantity;

    @JsonProperty("material")
    private String material;

    @JsonProperty("color")
    private String color;
}
