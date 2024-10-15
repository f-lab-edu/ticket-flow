package github.ticketflow.domian.seatGrade.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SeatGradeRequestDTO {

    @NotNull
    private Long eventLocationId;
    @NotBlank
    private String seatGradeName;
    @NotNull
    private BigDecimal seatGradePrice;
    @Positive
    private int seatGradeTotalSeats;

}
