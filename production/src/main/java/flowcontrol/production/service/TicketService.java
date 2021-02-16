package flowcontrol.production.service;

import flowcontrol.production.model.entity.Line;
import flowcontrol.production.model.entity.Ticket;
import flowcontrol.production.model.general.PalletLabel;
import flowcontrol.production.repository.TicketRepository;
import flowcontrol.production.repository.impl.PalletLabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    @Autowired
    private final TicketRepository ticketRepository;

    @Autowired
    private final LineService lineService;

    @Autowired
    private final PalletLabelRepository palletLabelRepository;

    public TicketService(TicketRepository ticketRepository, LineService lineService, PalletLabelRepository palletLabelRepository) {
        this.ticketRepository = ticketRepository;
        this.lineService = lineService;
        this.palletLabelRepository = palletLabelRepository;
    }


    public Ticket create(Long palletLabelId, Long lineId){
        // Get pallet label ?

        PalletLabel palletLabel = palletLabelRepository.findById(palletLabelId);
        // Get production line ?
        Line line = this.lineService.get(lineId);

        line.getDescription();
//        this.ticketRepository.findById();
        // If endtime not set yet
            //if endtime not set yet check for open interruptions
                // if open innterruption close this interruption by filling the end




        // Create ticket with logic

        //return ticket
        return null;
    }
}
