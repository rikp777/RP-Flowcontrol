package flowcontrol.production.model.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FillRefillTrayRequest {

    @NotNull(message = "Amount cannot be null")
    @NotBlank(message = "Amount cannot be blank")
    private Integer amount;
}
