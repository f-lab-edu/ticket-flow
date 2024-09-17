package github.ticketflow.domian.seatGrade.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatGradeUpdateRequestDTO {

    private Long eventLocationId;
    private String seatGradeName;
    private BigDecimal seatGradePrice;
    private int seatGradeTotalSeats;

}
