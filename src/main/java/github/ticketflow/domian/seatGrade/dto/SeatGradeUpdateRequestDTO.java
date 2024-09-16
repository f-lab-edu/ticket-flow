package github.ticketflow.domian.seatGrade.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatGradeUpdateRequestDTO {

    private Long eventLocationId;
    private String seatGradeName;
    private int seatGradePrice;
    private int seatGradeTotalSeats;

}
