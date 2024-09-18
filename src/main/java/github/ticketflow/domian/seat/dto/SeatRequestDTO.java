package github.ticketflow.domian.seat.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private int seatRow;
    @NotNull
    private int seatNumber;

}
