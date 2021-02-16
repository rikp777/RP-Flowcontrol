package flowcontrol.production.model.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

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

    private String startAt;
    private String endAt;

    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket;
}
