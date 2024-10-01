package github.ticketflow.domian.ticket.dto;

import github.ticketflow.domian.ticket.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TicketUpdateRequestDTO {

    private Long eventId;
    private Long seatId;
    private BigDecimal ticketPrice;
    private TicketStatus ticketStatus;

}
