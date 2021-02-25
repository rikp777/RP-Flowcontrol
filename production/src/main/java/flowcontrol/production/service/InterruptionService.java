package flowcontrol.production.service;

import flowcontrol.production.exception.AppException;
import flowcontrol.production.exception.InterruptionNotFoundException;
import flowcontrol.production.exception.ResourceNotFoundException;
import flowcontrol.production.model.entity.Interruption;
import flowcontrol.production.model.entity.InterruptionReason;
import flowcontrol.production.model.entity.Ticket;
import flowcontrol.production.repository.InterruptionReasonRepository;
import flowcontrol.production.repository.InterruptionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    public List<Interruption> getAll(){
        return interruptionRepository.findAll();
    }
    public Optional<Interruption> getById(Long id){
        Interruption interruption=  interruptionRepository.findById(id)
                .orElseThrow(() -> new InterruptionNotFoundException(id));

        return Optional.of(interruption);
    }

    public Optional<Interruption> create(Long ticketId, Long interruptionReasonId, Integer usedArticleAmount){
        log.info("================================================");
        log.info("Begin check [Create Interruption]");
        // Get interruption Reason
        InterruptionReason interruptionReason = interruptionReasonRepository.findById(interruptionReasonId)
                .orElseThrow(() -> new ResourceNotFoundException("Interruption reason", "Interruption reason not found", interruptionReasonId));

        // Get ticket
        Ticket ticket = ticketService.getById(ticketId)
                .orElseThrow(() -> new AppException("Ticket is need for creation of an interruption"));

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
            ticketService.closeTicketWithRestAmount(ticketId, usedArticleAmount);

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

    public Optional<Interruption> close(Long interruptionId){
        // Get interruption
        Interruption interruption = this.getById(interruptionId).get();

        // Close interruption by filling setEndAt
        interruption.setEndAt(LocalDateTime.now());

        // Update interruption
        Interruption interruptionSaved = interruptionRepository.save(interruption);

        // Return interruption
        return Optional.of(interruptionSaved);
    }


    public Interruption update(Long interruptionId, Interruption interruption){
        // Get interruption
        Interruption interruptionToUpdate = this.getById(interruptionId).get();

        // Update properties
        interruptionToUpdate.setStartAt(interruption.getStartAt());
        interruptionToUpdate.setEndAt(interruption.getEndAt());

        // Update interruption
        Interruption updatedInterruption = interruptionRepository.save(interruption);

        // Return updated interruption
        return updatedInterruption;
    }


    public void deleteById(Long interruptionId){
        // Check if exists
        if(interruptionRepository.existsById(interruptionId)){
            throw new InterruptionNotFoundException(interruptionId);
        }
        interruptionRepository.deleteById(interruptionId);
    }
}
