package flowcontrol.production.repository;

import flowcontrol.production.model.entity.Interruption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InterruptionRepository extends JpaRepository<Interruption, UUID> {
    List<Interruption> getInterruptionsByTicketId(UUID ticketId);

    Optional<Interruption> getInterruptionByTicketIdAndId(
            UUID ticketId,
            UUID interruptionId
    );
}
