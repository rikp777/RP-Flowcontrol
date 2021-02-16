package flowcontrol.production.controllers;

import flowcontrol.production.model.entity.Ticket;
import flowcontrol.production.model.general.PalletLabel;
import flowcontrol.production.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
public class TicketController {

    @Autowired
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/palletlabels/{palletLabelId}/tickets")
    public Ticket createTickets(@PathVariable("palletLabelId") String palletLabelId, @RequestParam String productionLine){
        Ticket ticket = ticketService.create(palletLabelId, productionLine);

        return ticket;
    }
}
