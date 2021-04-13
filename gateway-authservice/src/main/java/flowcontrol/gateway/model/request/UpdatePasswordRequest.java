package flowcontrol.gateway.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdatePasswordRequest {
    @NotBlank
    private String oldPassword;

    @NotBlank
    private String newPassword;
}
