package flowcontrol.gateway.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("device_id")
    private String deviceId;

    @NotNull
    @JsonProperty("device_type")
    private DeviceType deviceType;

    @NotNull
    @NotBlank
    @JsonProperty("notification_token")
    private String notificationToken;
}
