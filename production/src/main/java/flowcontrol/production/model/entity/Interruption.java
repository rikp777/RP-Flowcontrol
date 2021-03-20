package flowcontrol.production.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity( name = "Interruption" )
@Table( name = "interruption" )
public class Interruption extends BaseEntity {

    private LocalDateTime startAt;
    private LocalDateTime endAt;

    @ManyToOne(
            targetEntity = Ticket.class,
            fetch = FetchType.EAGER
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
