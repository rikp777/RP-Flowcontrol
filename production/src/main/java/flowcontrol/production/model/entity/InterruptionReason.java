package flowcontrol.production.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@Entity( name = "InterruptionReason" )
@Table( name = "interruption_reason" )
public class InterruptionReason {

    @Id
    @SequenceGenerator(
            name = "interruption_reason_sequence",
            sequenceName = "interruption_reason_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "interruption_reason_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
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
