package flowcontrol.production.model.response;

import flowcontrol.production.model.entity.Interruption;
import flowcontrol.production.model.entity.Line;
import flowcontrol.production.model.general.Farmer;
import flowcontrol.production.model.general.PalletLabel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
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

    private PalletLabel palletLabel;
    private Farmer farmer;
}
