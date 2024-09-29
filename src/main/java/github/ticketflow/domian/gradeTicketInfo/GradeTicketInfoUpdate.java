package github.ticketflow.domian.gradeTicketInfo;

import github.ticketflow.domian.event.EventEntity;
import github.ticketflow.domian.eventLocation.EventLocationEntity;
import github.ticketflow.domian.seatGrade.SeatGradeEntity;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GradeTicketInfoUpdate {

    @Nullable
    private EventEntity eventEntity;
    @Nullable
    private EventLocationEntity eventLocationEntity;
    @Nullable
    private SeatGradeEntity seatGradeEntity;

}
