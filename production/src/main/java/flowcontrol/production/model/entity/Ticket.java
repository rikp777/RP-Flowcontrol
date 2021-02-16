package flowcontrol.production.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Ticket")
@Table(name = "ticket")
public class Ticket {

    @Id
    @SequenceGenerator(
            name = "ticket_sequence",
            sequenceName = "ticket_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "ticket_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    private String palletLabelId;

    @Column(
            name = "started_at",
            nullable = false
    )
    private LocalDateTime startAt;

    @Column(
            name = "ended_at",
            nullable = false
    )
    private LocalDateTime  endAt;

    @OneToMany(
            mappedBy = "ticket",
            orphanRemoval = true,
            cascade = { CascadeType.PERSIST, CascadeType.REMOVE },
            fetch = FetchType.LAZY
    )
    private List<Interruption> interruptions;

    @ManyToOne
    @JoinColumn(
            name = "lineId",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "line_ticket_fk"
            )
    )
    private Line line;
}
