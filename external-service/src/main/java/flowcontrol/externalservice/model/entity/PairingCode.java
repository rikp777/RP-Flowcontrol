package flowcontrol.externalservice.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

@Entity(name = "pairing_code")
@DynamicUpdate(true)
@Table(name = "article")
@Getter
@Setter
@Accessors(chain = true)
@ToString
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class PairingCode extends BaseEntity{
    private ExternalType externalType;
    private String externalCode;
    private String identifier;
    private String internalCode;
}
