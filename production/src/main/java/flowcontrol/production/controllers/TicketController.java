package flowcontrol.production.controllers;

import flowcontrol.production.model.entity.Ticket;
import flowcontrol.production.model.general.PalletLabel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TicketController {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping("/ticket")
    public List<Ticket> getTickets(){

        List<Ticket> tickets = Arrays.asList(
                new Ticket("1", "1", "1");
                new Ticket("2", "1", "1");
        )

        return tickets.stream().map(ticket -> {
            PalletLabel palletLabel = webClientBuilder.build() //Gives you a client
                    .get() // Method for the request
                    .uri("http://localhost:7072/palletlabels/" + ticket.getPalletLabelId()) // Url that you need to acces
                    .retrieve() // Go do the fetch
                    .bodyToMono(PalletLabel.class) // Whatever body go get back map it to the class - Mono means you will get a object back but not right away "async" //empty page but you will need to wait but you know it will come and than you can do stuff "promise"
                    .block();
        })
                .collect(Collectors.toList());


    }
}
