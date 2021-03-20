package flowcontrol.production.model.response;


import flowcontrol.production.model.entity.InterruptionReason;
import flowcontrol.production.model.entity.Ticket;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Relation(itemRelation = "interruption", collectionRelation = "interruptions")
public class InterruptionResponse extends RepresentationModel<InterruptionResponse> {

    private Long id;

    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private Ticket ticket;

    private InterruptionReason interruptionReason;
}
