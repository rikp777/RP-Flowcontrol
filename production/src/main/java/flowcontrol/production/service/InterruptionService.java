package flowcontrol.production.service;

import flowcontrol.production.exception.AppException;
import flowcontrol.production.exception.InterruptionNotFoundException;
import flowcontrol.production.exception.ResourceNotFoundException;
import flowcontrol.production.model.entity.Interruption;
import flowcontrol.production.model.entity.InterruptionReason;
import flowcontrol.production.model.entity.Ticket;
import flowcontrol.production.model.meta.BasicMetaData;
import flowcontrol.production.repository.InterruptionReasonRepository;
import flowcontrol.production.repository.InterruptionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class InterruptionService {

    @Autowired
    private final InterruptionRepository interruptionRepository;

    @Autowired
    private final InterruptionReasonRepository interruptionReasonRepository;

    @Autowired
    private final TicketService ticketService;

    /**
     * @param ticketId
     * @return
     * Returns error message when data has been found for ticketId but the belonging ids are not right else return
     * empty list
     */
    public List<Interruption> findAll(BasicMetaData meta, UUID ticketId){
        return interruptionRepository.getInterruptionsByTicketId(ticketId)
                .stream()
                .map(interruptionItem -> {
                    if (interruptionItem.getTicket().getFarmerId() == meta.getFarmerId() && interruptionItem.getTicket().getPalletLabelId() == meta.getPalletLabelId() && interruptionItem.getTicket().getId() == ticketId)
                        return interruptionItem;
//                        //  Returns error message when data has been found for ticketId but the belonging ids are not right else return
//                        //  empty list
//                    throw new InterruptionNotFoundException(
//                            "given id's",
//                            "FarmerId + [" + meta.getFarmerId() + "] " +
//                            "PalletLabelId + [" + meta.getPalletLabelId() + "] " +
//                            "TicketId + [" + ticketId + "] "
//                            ) ;
                    return null;
                }).collect(Collectors.toList());
    }


    /**
     * @param meta
     * @param ticketId
     * @param interruptionId
     * @return
     */
    public Optional<Interruption> findById(BasicMetaData meta, UUID ticketId, UUID interruptionId){
        Interruption interruption=  interruptionRepository
                .getInterruptionByTicketIdAndId(
                        ticketId,
                        interruptionId
                )
                .map(interruptionItem -> {
//                            log.info(interruptionItem.getTicket().getFarmerId() + " == " + meta.getFarmerId());
//                            log.info(interruptionItem.getTicket().getPalletLabelId() + " == " + meta.getPalletLabelId());
//                            log.info(String.valueOf(interruptionItem.getTicket().getFarmerId() == meta.getFarmerId()));
//                            log.info(String.valueOf(interruptionItem.getTicket().getPalletLabelId() == meta.getPalletLabelId()));

                            if (interruptionItem.getTicket().getFarmerId().equals(meta.getFarmerId()) && interruptionItem.getTicket().getPalletLabelId().equals(meta.getPalletLabelId()))
                                return interruptionItem;
                            return null;
                        }
                )
                .orElseThrow(() -> new InterruptionNotFoundException("Id", interruptionId));

        return Optional.of(interruption);
    }


    /**
     * @param meta
     * @param ticketId
     * @param interruptionReasonId
     * @param usedArticleAmount
     * @return
     */
    public Optional<Interruption> create(
            BasicMetaData meta,
            UUID ticketId,
            UUID interruptionReasonId,
            Integer usedArticleAmount
    ){
        log.info("================================================");
        log.info("Begin check [Create Interruption]");
        // Get interruption Reason
        InterruptionReason interruptionReason = interruptionReasonRepository.findById(interruptionReasonId)
                .orElseThrow(() -> new ResourceNotFoundException("Interruption reason", "Interruption reason not found", interruptionReasonId));

        // Get ticket
        Ticket ticket = ticketService.findById(meta, ticketId)
                .orElseThrow(() -> new AppException(
                        "Ticket is needed for creation of an interruption. " +
                        "You have not passed on the correct ids " +
                        "FarmerId: [" + meta.getFarmerId() + "] " +
                        "PalletLabelId: [" + meta.getPalletLabelId() + "] " +
                        "TicketId: [" + ticketId + "] " +
                        "One of the above is false " +
                        "give the correct combination of ids"
                        ));

        log.info("Interruption will be created for ticket: [" + ticket.getId() + "]");

        // Create new interruption instance
        Interruption interruption = new Interruption();
        interruption.setStartAt(LocalDateTime.now());
        interruption.setTicket(ticket);
        interruption.setInterruptionReason(interruptionReason);

        // Check if to close process and fill end time of interruption
        if(interruptionReason.getStopProcess()){
            log.info("Interruption will stop the current ticket: [" + ticket.getId() + "] because stop process is [true]");
            log.info("Interruption has been created and will be closed immediately");

            // Stop ticket
            ticketService.closeTicketWithRestAmount(meta, ticketId, usedArticleAmount);

            // Set end time for interruption
            interruption.setEndAt(LocalDateTime.now());
        }

        // Save new interruption
        Interruption interruptionSaved = interruptionRepository.save(interruption);

        log.info("End check");
        log.info("================================================");

        // Return new interruption with id
        return Optional.of(interruptionSaved);
    }


    /**
     * @param meta
     * @param ticketId
     * @param interruptionId
     * @return
     */
    public Optional<Interruption> close(BasicMetaData meta, UUID ticketId, UUID interruptionId){
        // Get interruption
        Interruption interruption = this.findById(meta, ticketId, interruptionId).get();

        // Close interruption by filling setEndAt
        interruption.setEndAt(LocalDateTime.now());

        // Update interruption
        Interruption interruptionSaved = interruptionRepository.save(interruption);

        // Return interruption
        return Optional.of(interruptionSaved);
    }


    /**
     * @param meta
     * @param ticketId
     * @param interruptionId
     * @param interruption
     * @return
     */
    public Interruption update(
            BasicMetaData meta,
            UUID ticketId,
            UUID interruptionId,
            Interruption interruption
    ){
        // Get interruption
        Interruption interruptionToUpdate = this.findById(meta, ticketId, interruptionId).get();

        // Update properties
        interruptionToUpdate.setStartAt(interruption.getStartAt());
        interruptionToUpdate.setEndAt(interruption.getEndAt());

        // Update interruption
        Interruption updatedInterruption = interruptionRepository.save(interruption);

        // Return updated interruption
        return updatedInterruption;
    }


    /**
     * @param meta
     * @param ticketId
     * @param interruptionId
     */
    public void deleteById(
            BasicMetaData meta,
            UUID ticketId,
            UUID interruptionId
    ){
        // Check if exists
        if(interruptionRepository.existsById(interruptionId)){
            throw new InterruptionNotFoundException("Id", interruptionId);
        }
        interruptionRepository.deleteById(interruptionId);
    }
}
