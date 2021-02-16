package flowcontrol.production.controllers;

import flowcontrol.production.model.entity.Ticket;
import flowcontrol.production.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
@RequestMapping("/v1/palletlabels")
public class TicketController {

    @Autowired
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/{palletLabelId}/tickets")
    public Ticket createTicket(@PathVariable("palletLabelId") Long palletLabelId, @RequestParam Long lineId){
        Ticket ticket = ticketService.create(palletLabelId, lineId);

        return ticket;
    }

//    @PostMapping("/{palletLabelId}/ticketss")
//    public Ticket createTicketsssss(@PathVariable("palletLabelId") String palletLabelId){
////        Ticket ticket = ticketService.create(palletLabelId, lineId);
//        System.out.println(palletLabelId);
//
//        return new Ticket();
//    }

//    @GetMapping()
//    public String test(){
//        return "test";
//    }
//    @PutMapping("/{palletLabelId}/tickets")
//    public Ticket updateTicket(@PathVariable("palletLabelId") String palletLabelId,){
//        Ticket ticket = ticketService.create(palletLabelId, productionLine);
//
//        return ticket;
//    }
//
//    @PostMapping("/{palletLabelId}/tickets/{ticketId}")
//    public Ticket createTicket
}
