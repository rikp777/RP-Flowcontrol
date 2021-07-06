package flowcontrol.gateway.model.entity;

import flowcontrol.gateway.model.entity.base.DateAudit;
import flowcontrol.gateway.model.entity.enumeration.DeviceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "UserDevice")
@Table(name = "user_device")

@Builder

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDevice extends BaseEntity {

    public UserDevice(UUID id){
        super(id);
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "device_type")
    @Enumerated(value = EnumType.STRING)
    private DeviceType deviceType;

    @Column(name = "notification_token")
    private String notificationToken;

    @Column(name = "device_id", nullable = false)
    private String deviceId;

    @OneToOne(optional = false, mappedBy = "userDevice")
    private RefreshToken refreshToken;

    @Column(name = "refresh_active")
    private Boolean isRefreshActive;
}
