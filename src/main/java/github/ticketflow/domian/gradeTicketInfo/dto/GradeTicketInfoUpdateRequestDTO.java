package github.ticketflow.domian.gradeTicketInfo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GradeTicketInfoUpdateRequestDTO {

    private Long eventId;
    private Long eventLocationId;
    private Long seatGradeId;
    private Integer numberTotalTicket;
    private Integer numberOfRemainingTickets;

}
