package flowcontrol.article.repository.Generic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@JsonIgnoreProperties(
        {"hibernateLazyInitializer", "handler", "createAt", "updatedAt"}
)
public class AbstractBaseEntity implements Serializable {

    @Id @GeneratedValue
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Version
    private int version;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public AbstractBaseEntity(){
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
