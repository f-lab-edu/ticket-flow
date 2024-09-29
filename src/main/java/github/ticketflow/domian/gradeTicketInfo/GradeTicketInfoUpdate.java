package github.ticketflow.domian.gradeTicketInfo;

import github.ticketflow.domian.event.EventEntity;
import github.ticketflow.domian.eventLocation.EventLocationEntity;
import github.ticketflow.domian.seatGrade.SeatGradeEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GradeTicketInfoUpdate {

    private EventEntity eventEntity;
    private EventLocationEntity eventLocationEntity;
    private SeatGradeEntity seatGradeEntity;

}
