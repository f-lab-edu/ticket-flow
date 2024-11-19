package github.ticketflow.domian.gradeTicketInfo.dto;


import github.ticketflow.domian.event.dto.EventResponseDTO;
import github.ticketflow.domian.eventLocation.dto.EventLocationResponseDTO;
import github.ticketflow.domian.gradeTicketInfo.GradeTicketInfoEntity;
import github.ticketflow.domian.seatGrade.dto.SeatGradeResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GradeTicketInfoResponseDTO {

    private Long gradeTicketInfoId;
    private EventResponseDTO eventResponseDTO;
    private EventLocationResponseDTO eventLocationResponseDTO;
    private SeatGradeResponseDTO seatGradeResponseDTO;
    private int numberTotalTicket;
    private int numberOfRemainingTickets;

    public GradeTicketInfoResponseDTO(GradeTicketInfoEntity gradeTicketEntity) {
        this.gradeTicketInfoId = gradeTicketEntity.getGradeTicketInfoId();
        this.eventResponseDTO = new EventResponseDTO(gradeTicketEntity.getEventEntity());
        this.eventLocationResponseDTO = new EventLocationResponseDTO(gradeTicketEntity.getEventLocationEntity());
        this.seatGradeResponseDTO = new SeatGradeResponseDTO(gradeTicketEntity.getSeatGradeEntity());
        this.numberTotalTicket = gradeTicketEntity.getNumberTotalTicket();
        this.numberOfRemainingTickets = gradeTicketEntity.getNumberOfRemainingTickets();
    }
}
