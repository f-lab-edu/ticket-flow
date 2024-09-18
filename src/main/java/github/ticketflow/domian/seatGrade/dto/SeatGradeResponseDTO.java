package github.ticketflow.domian.seatGrade.dto;

import github.ticketflow.domian.eventLocation.EventLocationEntity;
import github.ticketflow.domian.eventLocation.dto.EventLocationRequestDTO;
import github.ticketflow.domian.eventLocation.dto.EventLocationResponseDTO;
import github.ticketflow.domian.seatGrade.SeatGradeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SeatGradeResponseDTO {

    private Long seatGradeId;
    private EventLocationResponseDTO eventLocation;
    private String seatGradeName;
    private BigDecimal seatGradePrice;
    private int seatGradeTotalSeats;

    public SeatGradeResponseDTO(SeatGradeEntity seatGradeEntity) {
        this.seatGradeId = seatGradeEntity.getSeatGradeId();
        this.eventLocation = new EventLocationResponseDTO(seatGradeEntity.getEventLocation());
        this.seatGradeName = seatGradeEntity.getSeatGradeName();
        this.seatGradePrice = seatGradeEntity.getSeatGradePrice();
        this.seatGradeTotalSeats = seatGradeEntity.getSeatGradeTotalSeats();
    }
}
