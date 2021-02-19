package flowcontrol.gateway.model.request;

import flowcontrol.gateway.model.entity.enumeration.DeviceType;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DeviceInfoRequest {

    @NotBlank
    private String deviceId;

    @NotNull
    private DeviceType deviceType;

    @NotNull
    @NotBlank
    private String notificationToken;
}
