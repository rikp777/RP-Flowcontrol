package flowcontrol.article.model.request.article;

import com.fasterxml.jackson.annotation.JsonProperty;
import flowcontrol.article.model.request.SequenceFirstOrder;
import flowcontrol.article.model.request.SequenceSecondOrder;
import flowcontrol.article.model.validation.annotation.NullOrNotBlank;
import lombok.Data;

import javax.validation.GroupSequence;
import javax.validation.constraints.*;

@Data
@GroupSequence({BaseArticleRequest.class, SequenceFirstOrder.class, SequenceSecondOrder.class})
public class BaseArticleRequest {

    @NotNull(message = "'${validatedValue}' is mandatory")
    @Min(value = 20 , message = "'${validatedValue}' should be greater then equal to {value}")
    @Max(value = 180 , message = "'${validatedValue}' should be less then equal to {value}")
    @JsonProperty("inset_gram")
    private Integer insetGram;

    @NotNull(message = "'${validatedValue}' is mandatory")
    @JsonProperty("inset_limit")
    private Integer insetLimit;

    @NotNull(message = "'${validatedValue}' is mandatory")
    @JsonProperty("pallet_limit")
    private Integer palletLimit;

    @NotBlank(message = "'${validatedValue}' is mandatory")
    @Pattern(regexp = "[A-Z]+", message = "The '${validatedValue}' has not the correct syntax")
    @JsonProperty("origin")
    private String origin;

    @NotNull(message = "biologic is mandatory")
    @JsonProperty("biologic")
    private boolean biologic;

    @JsonProperty("inactive")
    private boolean inactive;

    @NullOrNotBlank(message = "is mandatory")
    @JsonProperty("additional_info")
    private String additionalInfo;


}
