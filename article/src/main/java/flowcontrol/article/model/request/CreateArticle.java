package flowcontrol.article.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import flowcontrol.article.model.validation.annotation.*;
import lombok.Data;

import javax.validation.GroupSequence;
import javax.validation.constraints.*;

@Data
@GroupSequence({CreateArticle.class, SequenceFirstOrder.class,SequenceSecondOrder.class})
public class CreateArticle {

    @UniqueExcelCode()
    @NotBlank(message = "ExcelCode is mandatory")
    @Size(
            min = 3,
            max = 10,
            message = "The excelCode '${validatedValue}' must be between {min} and {max} characters long"
    )
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "The excelCode has not the correct syntax")
    @JsonProperty("excel_code")
    private String excelCode;


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

    @NotBlank(message = "is mandatory")
    @JsonProperty("additional_info")
    private String additionalInfo;

    @TypeExists()
    @NotNull(message = "is mandatory")
    @JsonProperty("type_id")
    private String typeId;

    @PalletTypeExists()
    @NotNull(message = "is mandatory")
    @JsonProperty("pallet_type_id")
    private String palletTypeId;

    @CaskExists()
    @NotNull(message = "is mandatory")
    @JsonProperty("cask_id")
    private String caskId;

    @InsetExists()
    @NotNull(message = "is mandatory")
    @JsonProperty("inset_id")
    private String insetId;

    @GroupExists()
    @NotNull(message = "is mandatory")
    @JsonProperty("group_id")
    private String groupId;

    @SortTypeExists()
    @NotNull(message = "is mandatory")
    @JsonProperty("sort_type_id")
    private String sortTypeId;


    @Pattern(regexp = "[0-9]+", message = "The '${validatedValue}' has not the correct syntax", groups = SequenceFirstOrder.class)
    @NotNull(message = "is mandatory", groups = SequenceFirstOrder.class)
    @ColorExists(groups = SequenceSecondOrder.class)
    @JsonProperty("color_id")
    private String colorId;
}
