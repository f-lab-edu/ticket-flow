package github.ticketflow.domian.ticket;

import github.ticketflow.domian.event.EventEntity;
import github.ticketflow.domian.seat.SeatEntity;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketUpdateVO {

    @Nullable
    private EventEntity eventEntity;
    @Nullable
    private SeatEntity seatEntity;
    @Nullable
    private BigDecimal ticketPrice;
    @Nullable
    private TicketStatus ticketStatus;


}
