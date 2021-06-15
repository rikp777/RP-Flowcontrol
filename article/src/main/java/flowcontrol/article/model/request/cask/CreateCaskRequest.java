package flowcontrol.article.model.request.cask;

import com.fasterxml.jackson.annotation.JsonProperty;
import flowcontrol.article.model.request.SequenceFirstOrder;
import flowcontrol.article.model.request.SequenceSecondOrder;
import flowcontrol.article.model.validation.annotation.ColorExists;
import flowcontrol.article.model.validation.annotation.NullOrNotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.GroupSequence;
import javax.validation.constraints.*;


@Data
@Accessors(chain = true)
@GroupSequence({CreateCaskRequest.class, SequenceFirstOrder.class, SequenceSecondOrder.class})
public class CreateCaskRequest {

    @NotBlank(message = "ExcelCode is mandatory", groups = SequenceFirstOrder.class)
    @Size(
            min = 3,
            max = 10,
            message = "The excelCode '${validatedValue}' must be between {min} and {max} characters long"
    )
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "The excelCode has not the correct syntax")
    @JsonProperty("excel_code")
    private String excelCode;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    //@NotBlank(message = "'${validatedValue}' is mandatory", groups = SequenceFirstOrder.class)
    //@Pattern(regexp = "[0-9]", message = "The '${validatedValue}' has not the correct syntax", groups = SequenceFirstOrder.class)
    @Min(value = 20 , message = "'${validatedValue}' should be greater then equal to {value}", groups = SequenceSecondOrder.class)
    @Max(value = 180 , message = "'${validatedValue}' should be less then equal to {value}", groups = SequenceSecondOrder.class)
    @JsonProperty("weight")
    private Integer weight;

    //@NotBlank(message = "'${validatedValue}' is mandatory", groups = SequenceFirstOrder.class)
    //@Pattern(regexp = "[0-9]", message = "The '${validatedValue}' has not the correct syntax", groups = SequenceFirstOrder.class)
    @Min(value = 20 , message = "'${validatedValue}' should be greater then equal to {value}", groups = SequenceSecondOrder.class)
    @Max(value = 180 , message = "'${validatedValue}' should be less then equal to {value}", groups = SequenceSecondOrder.class)
    @JsonProperty("max_filling_quantity")
    private Integer maxFillingQuantity;

    @JsonProperty("material")
    private String material;

    @Pattern(regexp = "[0-9]+", message = "The '${validatedValue}' has not the correct syntax", groups = SequenceFirstOrder.class)
    @NullOrNotBlank(message = "is mandatory", groups = SequenceFirstOrder.class)
    @ColorExists(groups = SequenceSecondOrder.class)
    @JsonProperty("color_id")
    private String colorId;
}
