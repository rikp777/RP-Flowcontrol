package flowcontrol.production.repository;

import flowcontrol.production.model.entity.Interruption;
import flowcontrol.production.model.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterruptionRepository extends JpaRepository<Interruption, Long> {
    List<Interruption> getInterruptionsByTicketId(Long ticketId);
}
