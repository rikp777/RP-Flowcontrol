package flowcontrol.production.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity( name = "InterruptionReason" )
@Table( name = "interruption_reason" )
@NoArgsConstructor
public class InterruptionReason extends BaseEntity {

    public InterruptionReason(UUID id){
        super(id);
    }

    private UUID id;

    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;

    @Column(
            name = "description",
            nullable = true,
            columnDefinition = "TEXT"
    )
    private String description;

    @Column(
            name = "stop_process",
            nullable = false
    )
    private Boolean stopProcess;

    @OneToMany(
            targetEntity = Interruption.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    @JoinColumn(name = "interruptionReasonId", referencedColumnName = "id")
    @JsonIgnore
    private List<Interruption> interruptions = new ArrayList<>();
}
