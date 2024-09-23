package github.ticketflow.domian.seat.dto;

import github.ticketflow.domian.eventLocation.dto.EventLocationResponseDTO;
import github.ticketflow.domian.seat.SeatEntity;
import github.ticketflow.domian.seatGrade.dto.SeatGradeResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SeatResponseDTO {

    private Long seatId;
    private SeatGradeResponseDTO seatGradeResponseDTO;
    private EventLocationResponseDTO eventLocationResponseDTO;
    private String seatZone;
    private int seatRow;
    private int seatNumber;

    public SeatResponseDTO(SeatEntity seatEntity) {
        this.seatId = seatEntity.getSeatId();
        this.seatGradeResponseDTO = new SeatGradeResponseDTO(seatEntity.getSeatGradeEntity());
        this.seatZone = seatEntity.getSeatZone();
        this.seatRow = seatEntity.getSeatRow();
        this.seatNumber = seatEntity.getSeatNumber();
    }
}
