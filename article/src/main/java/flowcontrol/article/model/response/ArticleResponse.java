package flowcontrol.article.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Builder
@Data
@Relation(itemRelation = "article", collectionRelation = "articles")
public class ArticleResponse extends RepresentationModel<ArticleResponse> {
    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("excel_code")
    private String excelCode;

    @JsonProperty("inset_gram")
    private Integer insetGram;

    @JsonProperty("inset_limit")
    private Integer insetLimit;

    @JsonProperty("pallet_limit")
    private Integer palletLimit;

    @JsonProperty("origin")
    private String origin;

    @JsonProperty("biologic")
    private boolean biologic;

    @JsonProperty("inactive")
    private boolean inactive;

    @JsonProperty("additional_info")
    private String additionalInfo;

}
