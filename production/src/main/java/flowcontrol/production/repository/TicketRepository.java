package flowcontrol.production.repository;

import flowcontrol.production.model.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

//    @Query("SELECT * FROM ticket t WHERE t.pallet_label_id = ?1")
//    Optional<Ticket> getByPalletLabelIdAndWhereEnded_atIsNull(String palletLabelId);

    List<Ticket> getTicketByPalletLabelId(Long palletLabelId);
}
