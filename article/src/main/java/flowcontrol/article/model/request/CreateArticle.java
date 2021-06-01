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


    @NotNull(message = "'${validatedValue}' is mandatory")
    @Min(value = 20 , message = "'${validatedValue}' should be greater then equal to {value}")
    @Max(value = 180 , message = "'${validatedValue}' should be less then equal to {value}")
    private Integer insetGram;

    @NotNull(message = "'${validatedValue}' is mandatory")
    private Integer insetLimit;

    @NotNull(message = "'${validatedValue}' is mandatory")
    private Integer palletLimit;

    @NotBlank(message = "'${validatedValue}' is mandatory")
    @Pattern(regexp = "[A-Z]+", message = "The '${validatedValue}' has not the correct syntax")
    private String origin;

    @NotNull(message = "biologic is mandatory")
    private boolean biologic;

    private boolean inactive;

    @NotBlank(message = "'${validatedValue}' is mandatory")
    private String additionalInfo;

}
