package flowcontrol.transport.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
@JsonIgnoreProperties(
        {"hibernateLazyInitializer", "handler", "createAt", "updatedAt"}
)
public class BaseEntity implements Serializable {

    @Id
    @GenericGenerator(name = "UseExistingIdOtherwiseGenerateUsingIdentity", strategy = "flowcontrol.transport.model.entity.UseExistingIdOtherwiseGenerateUsingIdentity")
    @GeneratedValue(generator = "UseExistingIdOtherwiseGenerateUsingIdentity")
    @Column(unique = true, nullable = false)
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID id;

    @Version
    private int version;

    @Basic
    @Column(
            name = "created_at",
            nullable = false,
            insertable = true,
            updatable = false,
            columnDefinition = "TIMESTAMP NOT NULL"
    )
    @CreatedDate
    private LocalDateTime createdAt;

    @Basic
    @Column(
            name = "updated_at",
            nullable = false,
            insertable = true,
            updatable = false,
            columnDefinition = "TIMESTAMP NOT NULL"
    )
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public BaseEntity(){
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    public BaseEntity(UUID id){
        this.id = id;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
