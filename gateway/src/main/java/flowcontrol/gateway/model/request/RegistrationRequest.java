package flowcontrol.gateway.model.request;

import flowcontrol.gateway.validation.NullOrNotBlank;
import io.micrometer.core.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegistrationRequest {

    @NullOrNotBlank(message = "Registration username can be null but not blank")
    private String username;

    @NullOrNotBlank(message = "Registration email can be null but not blank")
    private String email;

    @NotNull(message = "Registration password cannot be null")
    private String password;

    @NotNull(message = "Specify whether the user has to be registered as and admin or not ")
    private Boolean registerAsAdmin;

}
