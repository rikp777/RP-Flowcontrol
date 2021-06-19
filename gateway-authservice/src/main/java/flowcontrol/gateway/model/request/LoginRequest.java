package flowcontrol.gateway.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("username")
    private String username;

    @NotNull
    @NotBlank
    @JsonProperty("email")
    private String email;

    @NotNull
    @JsonProperty("password")
    private String password;

    @Valid
    @NotNull
    @JsonProperty("device_info")
    private DeviceInfoRequest deviceInfo;
}
