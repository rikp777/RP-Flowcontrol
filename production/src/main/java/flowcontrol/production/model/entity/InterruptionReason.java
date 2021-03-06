package flowcontrol.production.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "InterruptionReason")
@Table(name = "interruption_reason")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class InterruptionReason extends BaseEntity {

    public InterruptionReason(UUID id){
        super(id);
    }

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
