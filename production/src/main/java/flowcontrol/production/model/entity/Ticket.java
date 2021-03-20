package flowcontrol.production.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Ticket")
@Table(name = "ticket")
@Getter
@Setter
@ToString
public class Ticket extends BaseEntity {

    private Long palletLabelId;
    private Long farmerId;

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
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    @JoinColumn(name = "ticketId", referencedColumnName = "id")
    @JsonIgnore
    private List<Interruption> interruptions = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
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
