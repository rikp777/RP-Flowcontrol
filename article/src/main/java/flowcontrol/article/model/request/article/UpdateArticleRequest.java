package flowcontrol.article.model.request.article;

import com.fasterxml.jackson.annotation.JsonProperty;
import flowcontrol.article.model.request.SequenceFirstOrder;
import flowcontrol.article.model.request.SequenceSecondOrder;
import flowcontrol.article.model.validation.annotation.*;
import lombok.Data;

import javax.validation.GroupSequence;
import javax.validation.constraints.*;

@Data
@GroupSequence({UpdateArticleRequest.class, SequenceFirstOrder.class, SequenceSecondOrder.class})
public class UpdateArticleRequest {
    @Min(value = 20 , message = "'${validatedValue}' should be greater then equal to {value}")
    @Max(value = 180 , message = "'${validatedValue}' should be less then equal to {value}")
    @JsonProperty("inset_gram")
    private Integer insetGram;

    @JsonProperty("inset_limit")
    private Integer insetLimit;

    @JsonProperty("pallet_limit")
    private Integer palletLimit;

    @NullOrNotBlank(message = "'${validatedValue}' is mandatory")
    @Pattern(regexp = "[A-Z]+", message = "The '${validatedValue}' has not the correct syntax")
    @JsonProperty("origin")
    private String origin;

    @JsonProperty("biologic")
    private boolean biologic;

    @JsonProperty("inactive")
    private boolean inactive;

    @NullOrNotBlank(message = "is mandatory")
    @JsonProperty("additional_info")
    private String additionalInfo;


    @NullOrNotBlank(message = "ExcelCode is mandatory", groups = SequenceFirstOrder.class)
    @UniqueExcelCode(groups = SequenceSecondOrder.class)
    @Size(
            min = 3,
            max = 10,
            message = "The excelCode '${validatedValue}' must be between {min} and {max} characters long"
    )
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "The excelCode has not the correct syntax")
    @JsonProperty("excel_code")
    private String excelCode;


    @NullOrNotBlank(message = "is mandatory", groups = SequenceFirstOrder.class)
    @TypeExists(groups = SequenceSecondOrder.class)
    @JsonProperty("type_id")
    private String typeId;


    @NullOrNotBlank(message = "is mandatory", groups = SequenceFirstOrder.class)
    @PalletTypeExists(groups = SequenceSecondOrder.class)
    @JsonProperty("pallet_type_id")
    private String palletTypeId;

    @NullOrNotBlank(message = "is mandatory", groups = SequenceFirstOrder.class)
    @CaskExists(groups = SequenceSecondOrder.class)
    @JsonProperty("cask_id")
    private String caskId;

    @NullOrNotBlank(message = "is mandatory", groups = SequenceFirstOrder.class)
    @InsetExists(groups = SequenceSecondOrder.class)
    @JsonProperty("inset_id")
    private String insetId;


    @NullOrNotBlank(message = "is mandatory", groups = SequenceFirstOrder.class)
    @GroupExists(groups = SequenceSecondOrder.class)
    @JsonProperty("group_id")
    private String groupId;


    @NullOrNotBlank(message = "is mandatory", groups = SequenceFirstOrder.class)
    @SortTypeExists(groups = SequenceSecondOrder.class)
    @JsonProperty("sort_type_id")
    private String sortTypeId;


    @Pattern(regexp = "[0-9]|NULL+", message = "The '${validatedValue}' has not the correct syntax", groups = SequenceFirstOrder.class)
    @NullOrNotBlank(message = "is mandatory", groups = SequenceFirstOrder.class)
    @ColorExists(groups = SequenceSecondOrder.class)
    @JsonProperty("color_id")
    private String colorId;
}
