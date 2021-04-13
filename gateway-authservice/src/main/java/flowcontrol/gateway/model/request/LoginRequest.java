package flowcontrol.gateway.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginRequest {

    @NotNull
    @NotBlank
    private String username;

    @NotNull
    @NotBlank
    private String email;

    @NotNull
    private String password;

    @Valid
    @NotNull
    private DeviceInfoRequest deviceInfo;
}
