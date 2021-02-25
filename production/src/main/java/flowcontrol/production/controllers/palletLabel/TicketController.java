package flowcontrol.production.controllers.palletLabel;

import flowcontrol.production.exception.TicketException;
import flowcontrol.production.model.entity.Ticket;
import flowcontrol.production.model.request.FillRefillTrayRequest;
import flowcontrol.production.model.response.TicketResponse;
import flowcontrol.production.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.validation.Valid;

@RestController
@RequestMapping("/v1/palletlabels/{palletLabelId}/tickets")
@AllArgsConstructor
public class TicketController {

    @Autowired
    private final TicketService ticketService;


    /**
     * Get all tickets
     * @param palletLabelId
     * @return
     */
    @GetMapping
    public List<Ticket> getAllTickets(@PathVariable Long palletLabelId){
        return ticketService.getAll(palletLabelId);
    }


    /**
     * Create new ticket
     * @param palletLabelId
     * @param lineId
     * @return
     */
    @PostMapping()
    public ResponseEntity createTicket(@PathVariable("palletLabelId") Long palletLabelId, @RequestParam Long lineId){

        return ticketService.create(palletLabelId, lineId)
                .map(ticket -> ResponseEntity.ok(ticket))
                .orElseThrow(() -> new TicketException("Couldn't create new ticket"));
    }


    /**
     * Close existing ticket
     * @param palletLabelId
     * @param ticketId
     * @return
     */
    @PostMapping("/{ticketId}")
    public ResponseEntity closeTicket(@PathVariable String palletLabelId, @PathVariable("ticketId") Long ticketId){

        return ticketService.close(ticketId)
                .map(ticket -> ResponseEntity.ok(ticket))
                .orElseThrow(() -> new TicketException("Couldn't close ticket: [" + ticketId + "]"));
    }


    /**
     * Fill ticket refill tray amount
     * @param palletLabelId
     * @param ticketId
     * @param fillRefillTrayRequest
     * @return
     */
    @PutMapping("/{ticketId}/refillTray")
    public ResponseEntity fillRefillTray(
            @PathVariable("palletLabelId") Long palletLabelId,
            @PathVariable("ticketId") Long ticketId,
            @Valid @RequestBody FillRefillTrayRequest fillRefillTrayRequest
            ){

            return ticketService.fillRefillTray(ticketId, fillRefillTrayRequest)
                    .map(ticket -> ResponseEntity.ok(ticket))
                    .orElseThrow(() -> new TicketException("Couldn't fill refill tray amount for ticket [" + ticketId + "]"));
    }
}
