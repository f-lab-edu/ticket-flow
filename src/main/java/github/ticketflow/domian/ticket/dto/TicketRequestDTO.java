package github.ticketflow.domian.ticket.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TicketRequestDTO {

    @NotNull
    private Long eventId;
    @NotNull
    private Long seatGradeId;
    @NotNull
    @Positive
    private BigDecimal ticketPrice;

}
