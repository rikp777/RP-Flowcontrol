package flowcontrol.production.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity( name = "InterruptionReason" )
@Table( name = "interruption_reason" )
public class InterruptionReason extends BaseEntity {

    private Long id;

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
