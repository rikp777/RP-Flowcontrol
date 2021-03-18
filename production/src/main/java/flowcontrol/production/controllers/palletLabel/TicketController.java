package flowcontrol.production.controllers.palletLabel;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import flowcontrol.production.controllers.assembler.TicketAssembler;
import flowcontrol.production.exception.TicketException;
import flowcontrol.production.model.entity.Ticket;
import flowcontrol.production.model.response.TicketResponse;
import flowcontrol.production.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/farmers/{farmerId}/palletlabels/{palletLabelId}/tickets")
@AllArgsConstructor
public class TicketController {

    @Autowired
    private final TicketService ticketService;
    private final TicketAssembler ticketAssembler;


    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Ticket>>> findAll(
            @PathVariable Long farmerId,
            @PathVariable Long palletLabelId
    ){

        List<EntityModel<Ticket>> tickets = StreamSupport
                .stream(ticketService.findAll(palletLabelId).spliterator(), false)
                .map(ticket -> EntityModel.of(ticket,
                        linkTo(methodOn(TicketController.class).findOne(farmerId,palletLabelId, ticket.getId())).withSelfRel(),
                        linkTo(methodOn(TicketController.class).findAll(farmerId, palletLabelId)).withRel("tickets")))
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                CollectionModel.of(tickets,
                        linkTo(methodOn(TicketController.class).findAll(farmerId, palletLabelId)).withSelfRel()));
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<EntityModel<TicketResponse>> findOne(
            @PathVariable Long farmerId,
            @PathVariable Long palletLabelId,
            @PathVariable Long ticketId
    ) {

//        return ticketService.findById(ticketId) //
//                .map(ticket -> EntityModel.of(ticket, //
//                        linkTo(methodOn(TicketController.class).findOne(farmerId, palletLabelId, ticket.getId())).withSelfRel(), //
//                        linkTo(methodOn(TicketController.class).findAll(farmerId, palletLabelId)).withRel("tickets"))) //
//                .map(ResponseEntity::ok) //
//                .orElse(ResponseEntity.notFound().build());

        return ticketService.findById(ticketId)
                .map(ticket -> {
                    TicketResponse ticketResponse = ticketAssembler.toModel(ticket)
                            .add(linkTo(methodOn(TicketController.class).findAll(1L, 1L)).withRel("tickets"));

                    return ResponseEntity.ok(EntityModel.of(ticketResponse));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Create new ticket
     * @param palletLabelId
     * @param lineId
     * @return
     */
    @PostMapping()
    public ResponseEntity<EntityModel<Ticket>> createTicket(
            @PathVariable Long farmerId,
            @PathVariable("palletLabelId") Long palletLabelId,
            @RequestParam Long lineId
    ){
        return ticketService.create(farmerId, palletLabelId, lineId)
                .map(ticket -> ResponseEntity.ok(EntityModel.of(ticket)))
                .orElseThrow(() -> new TicketException("Couldn't create new ticket"));


//        return ticketService.create(farmerId, palletLabelId, lineId)
//                .map(ticket -> ResponseEntity.ok(ticket))
//                .orElseThrow(() -> new TicketException("Couldn't create new ticket"));
    }
//
//
//    /**
//     * Close existing ticket
//     * @param palletLabelId
//     * @param ticketId
//     * @return
//     */
//    @PostMapping("/{ticketId}")
//    public ResponseEntity closeTicket(@PathVariable Long farmerId, @PathVariable String palletLabelId,
//                                      @PathVariable("ticketId") Long ticketId){
//
//        return ticketService.close(ticketId)
//                .map(ticket -> ResponseEntity.ok(ticket))
//                .orElseThrow(() -> new TicketException("Couldn't close ticket: [" + ticketId + "]"));
//    }
//
//
//    /**
//     * Fill ticket refill tray amount
//     * @param palletLabelId
//     * @param ticketId
//     * @param fillRefillTrayRequest
//     * @return
//     */
//    @PutMapping("/{ticketId}/refillTray")
//    public ResponseEntity fillRefillTray(
//            @PathVariable Long farmerId,
//            @PathVariable("palletLabelId") Long palletLabelId,
//            @PathVariable("ticketId") Long ticketId,
//            @Valid @RequestBody FillRefillTrayRequest fillRefillTrayRequest
//            ){
//
//            return ticketService.fillRefillTray(ticketId, fillRefillTrayRequest)
//                    .map(ticket -> ResponseEntity.ok(ticket))
//                    .orElseThrow(() -> new TicketException("Couldn't fill refill tray amount for ticket [" + ticketId + "]"));
//    }
}
