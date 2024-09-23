package github.ticketflow.domian.seat;

import github.ticketflow.domian.seatGrade.SeatGradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<SeatEntity, Long> {

    List<SeatEntity> findAllBySeatGradeEntity(SeatGradeEntity seatGradeEntity);

}
