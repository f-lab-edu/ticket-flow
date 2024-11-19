package github.ticketflow.domian.gradeTicketInfo;

import github.ticketflow.domian.event.EventEntity;
import github.ticketflow.domian.seat.SeatEntity;
import github.ticketflow.domian.seatGrade.SeatGradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GradeTicketInfoRepository extends JpaRepository<GradeTicketInfoEntity, Long> {

    List<GradeTicketInfoEntity> findAllByEventEntity(EventEntity eventEntity);

    Optional<GradeTicketInfoEntity> findByEventEntityAndSeatGradeEntity(EventEntity eventEntity, SeatGradeEntity seatGradeEntity);

}
