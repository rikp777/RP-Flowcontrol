package flowcontrol.production.service;

import flowcontrol.production.model.entity.Ticket;
import flowcontrol.production.model.general.PalletLabel;
import flowcontrol.production.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class TicketService {

    @Autowired
    private final TicketRepository ticketRepository;

    @Autowired
    private final LineService lineService;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public TicketService(TicketRepository ticketRepository, LineService lineService) {
        this.ticketRepository = ticketRepository;
        this.lineService = lineService;
    }


    public Ticket create(String PalletLabelId, String productionLine){
        // Get pallet label ?
        PalletLabel palletLabel = webClientBuilder.build() //Gives you a client
                .get() // Method for the request
                .uri("http://localhost:7072/palletlabels/" + PalletLabelId) // Url that you need to access
                .retrieve() // Go do the fetch
                .bodyToMono(PalletLabel.class) // Whatever body go get back map it to the class - Mono means you will get a object back but not right away "async" //empty page but you will need to wait but you know it will come and than you can do stuff "promise"
                .block();

        // Get production line ?
        this.lineService.get(productionLine);

        // Create ticket with logic

        //return ticket
        return null;
    }
}
