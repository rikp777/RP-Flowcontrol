package flowcontrol.production.service;

import flowcontrol.production.model.entity.Line;
import flowcontrol.production.model.entity.Ticket;
import flowcontrol.production.model.general.PalletLabel;
import flowcontrol.production.repository.TicketRepository;
import flowcontrol.production.repository.impl.PalletLabelRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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

    public Ticket close(Long ticketId){
        // Get ticket
        Ticket ticket =this.ticketRepository.findById(ticketId).orElse(null);

        // Close ticket by filling setEndAt
        ticket.setEndAt(LocalDateTime.now());

        // Save ticket
        ticketRepository.save(ticket);

        // Return ticket 
        return ticket;
    }

    public Ticket create(Long palletLabelId, Long lineId){
        // Get pallet label ?
        PalletLabel palletLabel = palletLabelRepository.findById(palletLabelId);

        // Get production line ?
        Line line = this.lineService.get(lineId);

        line.getDescription();

        Ticket ticket = new Ticket();


        List<Ticket> ticketList = ticketRepository.getTicketByPalletLabelId(palletLabel.getId());

        System.out.println("================================================");
        System.out.println("Begin check");
        // Check if pallet label has tickets
        if(ticketList.isEmpty()) {
            System.out.println("Pallet label: [" + palletLabel.getId() + "] has no tickets yet");
            ticket.setArticleAmountUsed(60);
            ticket.setStartAt(LocalDateTime.now());
            ticket.setPalletLabelId(palletLabel.getId());
            ticket.setLine(line);
            ticketRepository.save(ticket);
//            System.out.println(ticket.toString());
        }else{
            System.out.println("Pallet label: [" + palletLabel.getId() + "] has [" + ticketList.stream().count() + "] tickets");
            Integer totalArticleAmountUsed = 0;
            for (Ticket ticketForPalletLabel : ticketList){
                totalArticleAmountUsed = totalArticleAmountUsed + ticketForPalletLabel.getArticleAmountUsed();
            }

            if(totalArticleAmountUsed >= palletLabel.getArticleAmount()){
                System.out.println("Pallet label: [" + palletLabel.getId() + "] exceeds max amount throw exception");
                System.out.println("Pallet label: [" + palletLabel.getId() + "] the pallet has been completely used and can no longer be scanned for processing");
                for (Ticket ticketForPalletLabel : ticketList){
                    if(ticketForPalletLabel.getEndAt() == null){
                        System.out.println("Ticket: [" + ticketForPalletLabel.getId() + "] closed");
                        ticketForPalletLabel.setEndAt(LocalDateTime.now());
                        ticketRepository.save(ticketForPalletLabel);
                    }
                }
            }else{
                System.out.println("Exceed with the checks ");

                // Close ticket before
                for (Ticket ticketForPalletLabel : ticketList){
                    if(ticketForPalletLabel.getEndAt() == null){
                        System.out.println("Ticket: [" + ticketForPalletLabel.getId() + "] closed");
                        ticketForPalletLabel.setEndAt(LocalDateTime.now());
                        ticketRepository.save(ticketForPalletLabel);
                    }
                }

                // Create new ticket with rest amount
                System.out.println("Create new ticket with rest amount ");
                ticket.setArticleAmountUsed(palletLabel.getArticleAmount() - totalArticleAmountUsed);
                ticket.setStartAt(LocalDateTime.now());
                ticket.setPalletLabelId(palletLabel.getId());
                ticket.setLine(line);
                ticketRepository.save(ticket);
//                System.out.println(ticket);
            }

            // Check if total amount form pallet label is not exceeded
            // If no open tickets for pallet label
        }
        System.out.println("End check");



        // this.ticketRepository.findById();
        // If endtime not set yet
            //if endtime not set yet check for open interruptions
                // if open innterruption close this interruption by filling the end




        // Create ticket with logic

        //return ticket
        return null;
    }
}
