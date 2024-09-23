package github.ticketflow.domian.seat.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SeatRequestDTO {

    @NotNull
    private Long seatGradeId;
    @NotBlank
    private String seatZone;
    @Positive
    private int seatRow;
    @Positive
    private int seatNumber;

}
