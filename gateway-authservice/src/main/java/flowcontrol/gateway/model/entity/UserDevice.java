package flowcontrol.gateway.model.entity;

import flowcontrol.gateway.model.entity.base.DateAudit;
import flowcontrol.gateway.model.entity.enumeration.DeviceType;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "user_device")
public class UserDevice extends DateAudit {

    @Id
    @Column(name = "user_device_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_device_sequence")
    @SequenceGenerator(name = "user_device_sequence", allocationSize = 1)
    private Long id;

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