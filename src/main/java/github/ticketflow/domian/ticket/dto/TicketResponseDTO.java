package github.ticketflow.domian.ticket.dto;

import github.ticketflow.domian.event.EventEntity;
import github.ticketflow.domian.seat.SeatEntity;
import github.ticketflow.domian.ticket.TicketEntity;
import github.ticketflow.domian.ticket.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponseDTO {

    private Long ticketId;
    private EventEntity eventEntity;
    private SeatEntity seatEntity;
    private BigDecimal ticketPrice;
    private TicketStatus ticketStatus;
    private LocalDate createdAt;

    public TicketResponseDTO(TicketEntity ticketEntity) {
        this.ticketId = ticketEntity.getTicketId();
        this.eventEntity = ticketEntity.getEventEntity();
        this.seatEntity = ticketEntity.getSeatEntity();
        this.ticketPrice = ticketEntity.getTicketPrice();
        this.ticketStatus = ticketEntity.getTicketStatus();
        this.createdAt = ticketEntity.getCreatedAt();
    }
}
