package github.ticketflow.domian.seatGrade;

import github.ticketflow.domian.eventLocation.EventLocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatGradeRepository extends JpaRepository<SeatGradeEntity, Long> {

    List<SeatGradeEntity> findAllByEventLocation(EventLocationEntity eventLocation);
}
