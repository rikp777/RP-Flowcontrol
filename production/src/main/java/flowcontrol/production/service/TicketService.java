package flowcontrol.production.service;

import flowcontrol.production.exception.AppException;
import flowcontrol.production.exception.ResourceNotFoundException;
import flowcontrol.production.exception.TicketException;
import flowcontrol.production.model.entity.Line;
import flowcontrol.production.model.entity.Ticket;
import flowcontrol.production.model.general.PalletLabel;
import flowcontrol.production.model.meta.BasicMetaData;
import flowcontrol.production.model.request.FillRefillTrayRequest;
import flowcontrol.production.repository.LineRepository;
import flowcontrol.production.repository.TicketRepository;
import flowcontrol.production.repository.impl.PalletLabelRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class TicketService {

    private final TicketRepository ticketRepository;

    private final LineRepository lineRepository;

    private final PalletLabelRepository palletLabelRepository;


    /**
     * @param meta
     * @return
     */
    public List<Ticket> findAll(BasicMetaData meta){
        return this.ticketRepository.getTicketByFarmerIdAndPalletLabelId(meta.getFarmerId(), meta.getPalletLabelId());
    }

    /**
     * @param meta
     * @param ticketId
     * @return
     */
    public Optional<Ticket> findById(BasicMetaData meta, Long ticketId){
        return this.ticketRepository.getTicketsByFarmerIdAndPalletLabelIdAndId(
                meta.getFarmerId(),
                meta.getPalletLabelId(),
                ticketId
        );
    }

    public Optional<Ticket> close(BasicMetaData meta, Long ticketId){
        // Get ticket
        Ticket ticket =this.ticketRepository
                .getTicketsByFarmerIdAndPalletLabelIdAndId(
                        meta.getFarmerId(),
                        meta.getPalletLabelId(),
                        ticketId
                )
                .orElseThrow(() -> new ResourceNotFoundException("Ticket", "Ticket not found", ticketId));

        // Close ticket by filling setEndAt
        ticket.setEndAt(LocalDateTime.now());

        // Save ticket
        ticketRepository.save(ticket);

        // Log ticket has been closed
        log.info("Closed ticket: [" + ticketId + "]");

        // Return ticket
        return Optional.of(ticket);
    }

    public Optional<Ticket> closeTicketWithRestAmount(
            BasicMetaData meta,
            Long ticketId,
            Integer usedArticleAmount
    ){
        // Get ticket
        Ticket ticket = this.ticketRepository
                .getTicketsByFarmerIdAndPalletLabelIdAndId(
                        meta.getFarmerId(),
                        meta.getPalletLabelId(),
                        ticketId
                )
                .orElseThrow(() -> new ResourceNotFoundException("Ticket", "Ticket not found", ticketId));

        // Close ticket and fill rest amount
        ticket.setEndAt(LocalDateTime.now());
        ticket.setArticleAmountUsed(usedArticleAmount);

        // Save ticket
        ticketRepository.save(ticket);

        log.info("Closed ticket: [" + ticketId + "] with articleAmountUsed [" +  usedArticleAmount + "]");
        // Return ticket
        return Optional.of(ticket);
    }

    public Optional<Ticket> create(BasicMetaData meta, Long lineId){
        // Get pallet label ?
        PalletLabel palletLabel = palletLabelRepository
                .findById(
                        meta.getFarmerId(),
                        meta.getPalletLabelId()
                )
                .orElseThrow(() -> new ResourceNotFoundException("Pallet label", "Pallet label not found", meta.getPalletLabelId()));

        // Get production line ?
        Line line = this.lineRepository.findById(lineId)
                .orElseThrow(() -> new AppException("Line is needed for the creation of a ticket"));

        // Instantiate new Ticket
        Ticket ticket = new Ticket();
        ticket.setFarmerId(palletLabel.getFarmer().getId());

        // Get List of tickets that belong to pallet label by id
        List<Ticket> ticketList =
                ticketRepository.getTicketByFarmerIdAndPalletLabelId(
                        palletLabel.getFarmer().getId(),
                        palletLabel.getId()
                );

        log.info("================================================");
        log.info("Begin check [Create Ticket]");
        if(ticketList.isEmpty()) {
            log.info("Pallet label: [" + palletLabel.getId() + "] has no tickets yet");

            ticket.setArticleAmountUsed(palletLabel.getArticleAmount());
            ticket.setStartAt(LocalDateTime.now());
            ticket.setPalletLabelId(palletLabel.getId());
            ticket.setLine(line);
            ticketRepository.save(ticket);

            log.info("New ticket for pallet label: [" + palletLabel.getId() + "] created");
        }else{
            Integer totalArticleAmountUsed = this.getTotalArticleAmountUsed(ticketList);
            log.info("Pallet label: [" + palletLabel.getId() + "] has [" + ticketList.stream().count() + "] tickets");
            log.info("Total article amount used [" + totalArticleAmountUsed + "] of the [" + palletLabel.getArticleAmount() + "] still need to use the rest amount [" + (palletLabel.getArticleAmount() - totalArticleAmountUsed) + "]");


            if(totalArticleAmountUsed >= palletLabel.getArticleAmount()){
                log.info("Pallet label: [" + palletLabel.getId() + "] exceeds max amount of [" + palletLabel.getArticleAmount() + "] throw exception");
                log.info("Pallet label: [" + palletLabel.getId() + "] the pallet has been completely used and can no longer be scanned for processing");
                this.closeOpenTickets(ticketList);
                log.info("End check");
                log.info("================================================");

                throw new TicketException("Pallet label: [" + palletLabel.getId() + "] has been completely used and can no longer be scanned for processing");
            }else{
                log.info("Pallet label: [" + palletLabel.getId() + "] with amount [" + palletLabel.getArticleAmount() + "] was not fully used in previous ticket");
                // Close ticket before
                this.closeOpenTickets(ticketList);

                // Create new ticket with rest amount
                ticket.setArticleAmountUsed(palletLabel.getArticleAmount() - totalArticleAmountUsed);
                ticket.setStartAt(LocalDateTime.now());
                ticket.setPalletLabelId(palletLabel.getId());
                ticket.setLine(line);
                ticketRepository.save(ticket);
                log.info("Create new ticket with rest amount [" + (palletLabel.getArticleAmount() - totalArticleAmountUsed) + "]" );
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
        log.info("Close still open ticket if they exist automatically");
        for (Ticket ticket : tickets){
            if(ticket.getEndAt() == null){
                log.info("Ticket: [" + ticket.getId() + "] closed");
                ticket.setEndAt(LocalDateTime.now());
                ticketRepository.save(ticket);
            }
        }
    }

    public Optional<Ticket> fillRefillTray(
            BasicMetaData meta,
            Long ticketId,
            FillRefillTrayRequest fillRefillTrayRequest){
        Ticket ticket = ticketRepository
                .getTicketsByFarmerIdAndPalletLabelIdAndId(
                        meta.getFarmerId(),
                        meta.getPalletLabelId(),
                        ticketId
                )
                .orElseThrow(() -> new ResourceNotFoundException("Ticket", "Ticket not found", ticketId));

        // Fill refill amount
        ticket.setRefillTrays(fillRefillTrayRequest.getAmount());

        // Save updated ticket
        ticketRepository.save(ticket);

        // Return optional ticket
        return Optional.of(ticket);
    }
}
