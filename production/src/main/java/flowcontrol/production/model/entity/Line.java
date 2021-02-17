package flowcontrol.production.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@Entity( name = "Line" )
@Table( name = "line" )
@ToString
public class Line {

    @Id
    @SequenceGenerator(
            name = "line_sequence",
            sequenceName = "line_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "line_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    private String name;
    private String description;

    @OneToMany(
            mappedBy = "line",
            orphanRemoval = true,
            cascade = { CascadeType.PERSIST, CascadeType.REMOVE },
            fetch = FetchType.LAZY
    )
    private List<Ticket> tickets;
}
