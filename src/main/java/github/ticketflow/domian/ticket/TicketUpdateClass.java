package github.ticketflow.domian.ticket;

import github.ticketflow.domian.event.EventEntity;
import github.ticketflow.domian.seat.SeatEntity;
import github.ticketflow.domian.ticket.dto.TicketUpdateRequestDTO;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketUpdateClass {

    @Nullable
    private EventEntity eventEntity;
    @Nullable
    private SeatEntity seatEntity;
    @Nullable
    private BigDecimal ticketPrice;
    @Nullable
    private TicketStatus ticketStatus;


}
