package flowcontrol.production.service;

import flowcontrol.production.exception.AppException;
import flowcontrol.production.exception.ResourceNotFoundException;
import flowcontrol.production.exception.TicketException;
import flowcontrol.production.model.entity.Line;
import flowcontrol.production.model.entity.Ticket;
import flowcontrol.production.model.general.PalletLabel;
import flowcontrol.production.model.request.FillRefillTrayRequest;
import flowcontrol.production.repository.TicketRepository;
import flowcontrol.production.repository.impl.PalletLabelRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class TicketService {

    @Autowired
    private final TicketRepository ticketRepository;

    @Autowired
    private final LineService lineService;

    @Autowired
    private final PalletLabelRepository palletLabelRepository;

    public List<Ticket> getAll(Long palletLabelId){
        return this.ticketRepository.getTicketByPalletLabelId(palletLabelId);
    }

    public Optional<Ticket> getById(Long ticketId){
        // Get ticket
        Ticket ticket = this.ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket", "Ticket not found", ticketId));

        return Optional.of(ticket);
    }

    public Optional<Ticket> close(Long ticketId){
        // Get ticket
        Ticket ticket =this.ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket", "Ticket not found", ticketId));

        // Close ticket by filling setEndAt
        ticket.setEndAt(LocalDateTime.now());

        // Save ticket
        ticketRepository.save(ticket);

        // Return ticket
        return Optional.of(ticket);
    }

    public Optional<Ticket> closeTicketWithRestAmount(Long ticketId, Integer usedArticleAmount){
        // Get ticket
        Ticket ticket = this.ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket", "Ticket not found", ticketId));

        // Close ticket and fill rest amount
        ticket.setEndAt(LocalDateTime.now());
        ticket.setArticleAmountUsed(usedArticleAmount);

        // Save ticket
        ticketRepository.save(ticket);

        // Return ticket
        return Optional.of(ticket);
    }

    public Optional<Ticket> create(Long palletLabelId, Long lineId){
        // Get pallet label ?
        PalletLabel palletLabel = palletLabelRepository.findById(palletLabelId)
                .orElseThrow(() -> new ResourceNotFoundException("Pallet label", "Pallet label not found", palletLabelId));

        // Get production line ?
        Line line = this.lineService.getById(lineId)
                .orElseThrow(() -> new AppException("Line is needed for the creation of a ticket"));

        // Instantiate new Ticket
        Ticket ticket = new Ticket();

        // Get List of tickets that belong to pallet label by id
        List<Ticket> ticketList = ticketRepository.getTicketByPalletLabelId(palletLabel.getId());

        log.info("================================================");
        log.info("Begin check [Create Ticket]");
        if(ticketList.isEmpty()) {
            log.info("Pallet label: [" + palletLabel.getId() + "] has no tickets yet");

            ticket.setArticleAmountUsed(palletLabel.getArticleAmount());
            ticket.setStartAt(LocalDateTime.now());
            ticket.setPalletLabelId(palletLabel.getId());
            ticket.setLine(line);
            ticketRepository.save(ticket);
        }else{
            log.info("Pallet label: [" + palletLabel.getId() + "] has [" + ticketList.stream().count() + "] tickets");
            Integer totalArticleAmountUsed = this.getTotalArticleAmountUsed(ticketList);

            if(totalArticleAmountUsed >= palletLabel.getArticleAmount()){
                log.info("Pallet label: [" + palletLabel.getId() + "] exceeds max amount throw exception");
                log.info("Pallet label: [" + palletLabel.getId() + "] the pallet has been completely used and can no longer be scanned for processing");
                this.closeOpenTickets(ticketList);

                throw new TicketException("Pallet label: [" + palletLabel.getId() + "] has been completely used and can no longer be scanned for processing");
            }else{
                log.info("Exceed with the checks ");

                // Close ticket before
                this.closeOpenTickets(ticketList);

                // Create new ticket with rest amount
                log.info("Create new ticket with rest amount ");
                ticket.setArticleAmountUsed(palletLabel.getArticleAmount() - totalArticleAmountUsed);
                ticket.setStartAt(LocalDateTime.now());
                ticket.setPalletLabelId(palletLabel.getId());
                ticket.setLine(line);
                ticketRepository.save(ticket);
            }
        }
        log.info("End check");
        log.info("================================================");

        return Optional.of(ticket);
    }

    private Integer getTotalArticleAmountUsed(@NotNull List<Ticket> tickets){
        Integer totalArticleAmountUsed = 0;
        for (Ticket ticket : tickets){
            totalArticleAmountUsed = totalArticleAmountUsed + ticket.getArticleAmountUsed();
        }
        return totalArticleAmountUsed;
    }

    private void closeOpenTickets(@NotNull List<Ticket> tickets){
        for (Ticket ticket : tickets){
            if(ticket.getEndAt() == null){
                System.out.println("Ticket: [" + ticket.getId() + "] closed");
                ticket.setEndAt(LocalDateTime.now());
                ticketRepository.save(ticket);
            }
        }
    }

    public Optional<Ticket> fillRefillTray(Long ticketId, FillRefillTrayRequest fillRefillTrayRequest){
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket", "Ticket not found", ticketId));

        ticket.setRefillTrays(fillRefillTrayRequest.getAmount());

        ticketRepository.save(ticket);

        return Optional.of(ticket);
    }
}
