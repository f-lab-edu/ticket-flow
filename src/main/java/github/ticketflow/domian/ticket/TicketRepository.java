package github.ticketflow.domian.ticket;

import github.ticketflow.domian.seat.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<TicketEntity, Long> {

    Optional<TicketEntity> findBySeatEntity(SeatEntity seatEntity);

}
