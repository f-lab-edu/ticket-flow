package github.ticketflow.domian.seat.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatUpdateRequestDTO {

    private Long seatGradeId;
    private String seatZone;
    private int seatRow;
    private int seatNumber;

}
