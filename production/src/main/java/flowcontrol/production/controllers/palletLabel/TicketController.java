package flowcontrol.production.controllers.palletLabel;


import flowcontrol.production.controllers.assembler.TicketAssembler;
import flowcontrol.production.model.meta.BasicMetaData;
import flowcontrol.production.exception.TicketException;
import flowcontrol.production.model.entity.Ticket;
import flowcontrol.production.model.response.TicketResponse;
import flowcontrol.production.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/farmers/{farmerId}/palletlabels/{palletLabelId}/tickets")
@AllArgsConstructor
public class TicketController {

    @Autowired
    private final TicketService ticketService;
    private final TicketAssembler ticketAssembler;


    /**
     * Find all ticket that belong to farmer and pallet label
     * @param farmerId
     * @param palletLabelId
     */
    @GetMapping(
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaTypes.HAL_JSON_VALUE
            }
    )
    public ResponseEntity<CollectionModel<TicketResponse>> findAll(
            @PathVariable Long farmerId,
            @PathVariable Long palletLabelId
    ){
        BasicMetaData metaData = BasicMetaData.builder()
                .farmerId(farmerId)
                .palletLabelId(palletLabelId)
                .build();

        return ResponseEntity.ok(
                ticketAssembler.toCollectionModel(
                        ticketService.findAll(metaData)
                )
        );
    }


    /**
     * Find one by id that belong to farmer and pallet label
     * @param farmerId
     * @param palletLabelId
     * @param ticketId
     */
    @GetMapping(
            path = "/{ticketId}",
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaTypes.HAL_JSON_VALUE
            }
    )
    public ResponseEntity<EntityModel<TicketResponse>> findOne(
            @PathVariable Long farmerId,
            @PathVariable Long palletLabelId,
            @PathVariable Long ticketId
    ) {
        BasicMetaData metaData = BasicMetaData.builder()
                .farmerId(farmerId)
                .palletLabelId(palletLabelId)
                .build();

        return ticketService.findById(metaData, ticketId)
                .map(ticket -> ticketAssembler(ticket))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Create new ticket
     * @param farmerId
     * @param palletLabelId
     * @param lineId
     */
    @PostMapping(
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaTypes.HAL_JSON_VALUE
            }
    )
    public ResponseEntity<EntityModel<TicketResponse>> createTicket(
            @PathVariable Long farmerId,
            @PathVariable Long palletLabelId,
            @RequestParam Long lineId
    ){
        BasicMetaData metaData = BasicMetaData.builder()
                .farmerId(farmerId)
                .palletLabelId(palletLabelId)
                .build();

        return ticketService.create(metaData, lineId)
                .map(ticket -> ticketAssembler(ticket))
                .orElseThrow(() -> new TicketException("Couldn't create new ticket"));
    }


    /**
     * Close existing ticket
     * @param palletLabelId
     * @param ticketId
     */
    @PostMapping(
            path = "/{ticketId}/close",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaTypes.HAL_JSON_VALUE
            }
    )
    public ResponseEntity<EntityModel<TicketResponse>> closeTicket(
            @PathVariable Long farmerId,
            @PathVariable Long palletLabelId,
            @PathVariable Long ticketId
    ){
        BasicMetaData metaData = BasicMetaData.builder()
                .farmerId(farmerId)
                .palletLabelId(palletLabelId)
                .build();

        return ticketService.close(metaData, ticketId)
                .map(ticket -> ticketAssembler(ticket))
                .orElseThrow(() -> new TicketException("Couldn't close ticket: [" + ticketId + "]"));
    }



    private ResponseEntity<EntityModel<TicketResponse>> ticketAssembler(Ticket ticket){
        return ResponseEntity.ok(EntityModel.of(ticketAssembler.toModel(ticket)));
    }
}
