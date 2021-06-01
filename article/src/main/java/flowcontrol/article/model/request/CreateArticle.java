package flowcontrol.article.model.request;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class CreateArticle {

    @NotBlank(message = "ExcelCode is mandatory")
    @Size(
            min = 3,
            max = 10,
            message = "The excelCode '${validatedValue}' must be between {min} and {max} characters long"
    )
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "The excelCode has not the correct syntax")
    private String excelCode;


    @NotNull(message = "insetGram is mandatory")
    @Min(value = 20 , message = "insetGram should be greater then equal to {value}")
    @Max(value = 180 , message = "insetGram should be less then equal to {value}")
    private Integer insetGram;

    @NotNull(message = "insetLimit is mandatory")
    private Integer insetLimit;

    @NotNull(message = "palletLimit is mandatory")
    private Integer palletLimit;

    @NotBlank(message = "origin is mandatory")
    private String origin;

    @NotNull(message = "biologic is mandatory")
    private boolean biologic;

    @NotNull(message = "inactive is mandatory")
    private boolean inactive;

    @NotBlank(message = "additionalInfo is mandatory")
    private String additionalInfo;

}
