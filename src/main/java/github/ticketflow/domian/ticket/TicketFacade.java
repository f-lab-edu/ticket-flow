package github.ticketflow.domian.ticket;

import github.ticketflow.domian.event.EventEntity;
import github.ticketflow.domian.event.EventService;
import github.ticketflow.domian.seat.SeatEntity;
import github.ticketflow.domian.seat.SeatService;
import github.ticketflow.domian.ticket.dto.TicketRequestDTO;
import github.ticketflow.domian.ticket.dto.TicketUpdateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TicketFacade {

    private final TicketService ticketService;
    private final EventService eventService;
    private final SeatService seatService;

    public TicketEntity getTicketById(Long ticketId) {
        return ticketService.getTicketById(ticketId);
    }

    public TicketEntity getTicketBySeatEntity(Long seatId) {
        SeatEntity seatEntity = seatService.getSeatById(seatId);
        return ticketService.getTicketBySeatEntity(seatEntity);
    }

    public TicketEntity createTicket(TicketRequestDTO dto) {
        EventEntity eventEntity = eventService.getEventById(dto.getEventId());
        SeatEntity seatEntity = seatService.getSeatById(dto.getSeatId());
        return ticketService.createTicket(eventEntity, seatEntity, dto.getTicketPrice());
    }

    public TicketEntity updateTicket(Long ticketId, TicketUpdateRequestDTO dto) {
        TicketUpdateVO.TicketUpdateVOBuilder builder = TicketUpdateVO.builder();
        if (dto.getEventId() != null) {
            EventEntity eventEntity = eventService.getEventById(dto.getEventId());
            builder.eventEntity(eventEntity);
        }
        if (dto.getSeatId() != null) {
            SeatEntity seatEntity = seatService.getSeatById(dto.getSeatId());
            builder.seatEntity(seatEntity);
        }
        if (dto.getTicketPrice() != null) {
            builder.ticketPrice(dto.getTicketPrice());
        }
        if (dto.getTicketStatus() != null) {
            builder.ticketStatus(dto.getTicketStatus());
        }

        TicketUpdateVO ticketUpdateVO = builder.build();
        return ticketService.updateTicket(ticketId, ticketUpdateVO);
    }

    public TicketEntity deleteTicket(Long ticketId) {
        return ticketService.deleteTicket(ticketId);
    }
}
