package github.ticketflow.domian.reservation.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReserveRequestDTO {

    @NotNull
    private Long eventId;
    @NotNull
    private Long seatGradeId;
    @NotNull
    @Positive
    private BigDecimal ticketPrice;

}
