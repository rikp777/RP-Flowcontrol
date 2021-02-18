package flowcontrol.production.controllers.palletLabel;

import flowcontrol.production.model.entity.Ticket;
import flowcontrol.production.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
@RequestMapping("/v1/palletlabels/{palletLabelId}/tickets")
public class TicketController {

    @Autowired
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }


    @GetMapping
    public List<Ticket> getAllTickets(@PathVariable Long palletLabelId){
        return ticketService.getAll(palletLabelId);
    }

    @PostMapping()
    public Ticket createTicket(@PathVariable("palletLabelId") Long palletLabelId, @RequestParam Long lineId){
        Ticket ticket = ticketService.create(palletLabelId, lineId);

        return ticket;
    }

    @PostMapping("/{ticketId}")
    public Ticket closeTicket(@PathVariable String palletLabelId, @PathVariable("ticketId") Long ticketId){

        Ticket ticket = ticketService.close(ticketId);
        return ticket;
    }
}
