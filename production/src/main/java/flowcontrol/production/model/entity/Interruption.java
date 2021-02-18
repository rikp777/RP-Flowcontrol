package flowcontrol.production.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.Optional;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@Entity( name = "Interruption" )
@Table( name = "interruption" )
public class Interruption {

    @Id
    @SequenceGenerator(
            name = "interruption_sequence",
            sequenceName = "interruption_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "interruption_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    private LocalDateTime startAt;
    private LocalDateTime endAt;

    @ManyToOne(
            targetEntity = Ticket.class,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "ticketId", nullable = false)
    @JsonIgnore
    private Ticket ticket;

    @ManyToOne(
            targetEntity = InterruptionReason.class
    )
    @JoinColumn(name = "interruptionReasonId", nullable = false)
    private InterruptionReason interruptionReason;
}
