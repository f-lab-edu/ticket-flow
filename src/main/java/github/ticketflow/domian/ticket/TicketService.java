package github.ticketflow.domian.ticket;

import github.ticketflow.aop.DistributedLock;
import github.ticketflow.config.exception.GlobalCommonException;
import github.ticketflow.config.exception.ticket.TicketErrorResponsive;
import github.ticketflow.domian.event.EventEntity;
import github.ticketflow.domian.seat.SeatEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketEntity getTicketById(Long ticketId) {
        return ticketRepository.findById(ticketId).orElseThrow(() ->
                new GlobalCommonException(TicketErrorResponsive.NOT_FOUND_TICKET));
    }

    public TicketEntity getTicketBySeatEntity(SeatEntity seatEntity) {
        return  ticketRepository.findBySeatEntity(seatEntity).orElseThrow(() ->
                new GlobalCommonException(TicketErrorResponsive.NOT_FOUND_TICKET));
    }

    public TicketEntity createTicket(EventEntity eventEntity, BigDecimal ticketPrice) {
        TicketEntity ticketEntity = new TicketEntity(eventEntity, ticketPrice);
        return ticketRepository.save(ticketEntity);
    }

    public TicketEntity updateTicket(Long ticketId, TicketUpdateVO ticketUpdateVO) {
        TicketEntity ticketEntity = getTicketById(ticketId);
        TicketEntity updateTicketEntity = ticketEntity.update(ticketUpdateVO);
        return ticketRepository.save(updateTicketEntity);
    }

    public TicketEntity deleteTicket(Long ticketId) {
        TicketEntity ticketEntity = getTicketById(ticketId);
        ticketRepository.delete(ticketEntity);
        return ticketEntity;
    }

}
