package github.ticketflow.domian.seatGrade.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SeatGradeRequestDTO {

    @NotBlank
    private Long eventLocationId;
    @NotBlank
    private String seatGradeName;
    @NotNull
    private int seatGradePrice;
    @NotNull
    private int seatGradeTotalSeats;

}
