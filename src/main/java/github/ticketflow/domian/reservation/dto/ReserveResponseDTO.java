package github.ticketflow.domian.reservation.dto;

import github.ticketflow.domian.gradeTicketInfo.dto.GradeTicketInfoResponseDTO;
import github.ticketflow.domian.ticket.dto.TicketResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReserveResponseDTO {

    private TicketResponseDTO ticketResponse;
    private GradeTicketInfoResponseDTO gradeTicketInfoResponse;
}
