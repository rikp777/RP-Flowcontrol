package flowcontrol.production.repository;

import flowcontrol.production.model.entity.Interruption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InterruptionRepository extends JpaRepository<Interruption, Long> {
    List<Interruption> getInterruptionsByTicketId(Long ticketId);

    Optional<Interruption> getInterruptionByTicketIdAndId(
            Long ticketId,
            Long interruptionId
    );
}
