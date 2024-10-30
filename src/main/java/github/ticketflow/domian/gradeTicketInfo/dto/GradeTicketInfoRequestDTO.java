package github.ticketflow.domian.gradeTicketInfo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GradeTicketInfoRequestDTO {

    @NotNull
    private Long eventId;
    @NotNull
    private Long seatGradeId;
    @Positive
    private int numberTotalTicket;
    @Positive
    private int numberOfReservedTickets;

}
