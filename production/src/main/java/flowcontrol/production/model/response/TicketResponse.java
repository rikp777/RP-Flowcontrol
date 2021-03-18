package flowcontrol.production.model.response;

import com.fasterxml.jackson.annotation.JsonBackReference;
import flowcontrol.production.model.entity.Interruption;
import flowcontrol.production.model.entity.Line;
import flowcontrol.production.model.entity.Ticket;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@Relation(itemRelation = "ticket", collectionRelation = "tickets")
public class TicketResponse extends RepresentationModel<TicketResponse> {
    private Long id;

    private LocalDateTime startAt;
    private LocalDateTime  endAt;
    private Integer articleAmountUsed;
    private Integer refillTrays;
    private List<Interruption> interruptions = new ArrayList<>();
    private Line line;

    private Long palletLabelId;
    private Long farmerId;
}
