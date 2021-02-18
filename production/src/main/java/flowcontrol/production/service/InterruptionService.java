package flowcontrol.production.service;

import flowcontrol.production.model.entity.Interruption;
import flowcontrol.production.model.entity.InterruptionReason;
import flowcontrol.production.model.entity.Ticket;
import flowcontrol.production.repository.InterruptionReasonRepository;
import flowcontrol.production.repository.InterruptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InterruptionService {

    @Autowired
    private final InterruptionRepository interruptionRepository;

    @Autowired
    private final InterruptionReasonRepository interruptionReasonRepository;

    @Autowired
    private final TicketService ticketService;

    public InterruptionService(InterruptionRepository interruptionRepository, InterruptionReasonRepository interruptionReasonRepository, TicketService ticketService) {
        this.interruptionRepository = interruptionRepository;
        this.interruptionReasonRepository = interruptionReasonRepository;
        this.ticketService = ticketService;
    }


    public List<Interruption> getAll(){
        return interruptionRepository.findAll();
    }
    public Interruption getById(Long id){
        return interruptionRepository.findById(id).orElse(null);
    }

    public Interruption create(Long ticketId, Long interruptionReasonId, Integer usedArticleAmount){
        // Get interruption Reason
        InterruptionReason interruptionReason = interruptionReasonRepository.findById(interruptionReasonId).orElse(null);

        // Get ticket entity
        Ticket ticket = ticketService.getById(ticketId);

        System.out.println("Interruption will be created for ticket: [" + ticket.getId() + "]");

        // Check if ticket has open interruptions
        // Create new interruption
        Interruption interruption = new Interruption();
        interruption.setStartAt(LocalDateTime.now());
        interruption.setTicket(ticket);
        interruption.setInterruptionReason(interruptionReason);

        // Check if to close process and fill end time of interruption
        if(interruptionReason.getStopProcess()){
            System.out.println("Interruption will stop the current ticket: [" + ticket.getId() + "] ");
            System.out.println("Interruption has been created and also will be closed");
            // Stop ticket
            ticketService.closeTicketWithRestAmount(ticketId, usedArticleAmount);
            interruption.setEndAt(LocalDateTime.now());
        }

        // Save new interruption
        Interruption interruptionSaved = interruptionRepository.save(interruption);

        // Return new interruption with id
        return interruptionSaved;
    }

    public Interruption close(Long interruptionId){
        Interruption interruption = interruptionRepository.getOne(interruptionId);

        interruption.setEndAt(LocalDateTime.now());

        interruptionRepository.save(interruption);

        return interruption;
    }


    public Interruption update(Long interruptionId, Interruption interruption){
        // Gets only a reference does not fetch it from the database only updates properties that are updated
        Interruption interruptionToUpdate = interruptionRepository.getOne(interruptionId);

        // Update properties
        interruptionToUpdate.setStartAt(interruption.getStartAt());
        interruptionToUpdate.setEndAt(interruption.getEndAt());

        // Update interruption
        Interruption updatedInterruption = interruptionRepository.save(interruption);

        // Return updated interruption
        return updatedInterruption;
    }


    public boolean delete(Long id){
        interruptionRepository.deleteById(id);
        return false;
    }
}
