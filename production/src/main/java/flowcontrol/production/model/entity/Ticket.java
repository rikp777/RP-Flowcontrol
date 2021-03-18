package flowcontrol.production.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Ticket")
@Table(name = "ticket")
@Getter
@Setter
@ToString
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

    private Long palletLabelId;

    @Column(
            name = "started_at",
            nullable = false
    )
    private LocalDateTime startAt;

    @Column(
            name = "ended_at",
            nullable = true
    )
    private LocalDateTime  endAt;

    @Column(
            name = "article_amount_used",
            nullable = false
    )
    private Integer articleAmountUsed;

    private Integer refillTrays;

    // Relations
    @OneToMany(
            targetEntity = Interruption.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    @JoinColumn(name = "ticketId", referencedColumnName = "id")
    private List<Interruption> interruptions = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "line_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "line_ticket_fk"
            )
    )
    @JsonBackReference
    private Line line;

}
