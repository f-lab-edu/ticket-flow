package github.ticketflow.domian.reservation;

import github.ticketflow.aop.DistributedLock;
import github.ticketflow.config.exception.GlobalCommonException;
import github.ticketflow.config.exception.ticket.TicketErrorResponsive;
import github.ticketflow.domian.event.EventEntity;
import github.ticketflow.domian.event.EventService;
import github.ticketflow.domian.gradeTicketInfo.GradeTicketInfoEntity;
import github.ticketflow.domian.gradeTicketInfo.GradeTicketInfoService;
import github.ticketflow.domian.gradeTicketInfo.dto.GradeTicketInfoResponseDTO;
import github.ticketflow.domian.reservation.dto.ReserveRequestDTO;
import github.ticketflow.domian.reservation.dto.ReserveResponseDTO;
import github.ticketflow.domian.seat.SeatRepository;
import github.ticketflow.domian.seatGrade.SeatGradeEntity;
import github.ticketflow.domian.seatGrade.SeatGradeRepository;
import github.ticketflow.domian.seatGrade.SeatGradeService;
import github.ticketflow.domian.ticket.TicketEntity;
import github.ticketflow.domian.ticket.TicketRepository;
import github.ticketflow.domian.ticket.TicketService;
import github.ticketflow.domian.ticket.dto.TicketResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ReserveService {

    private final GradeTicketInfoService gradeTicketInfoService;
    private final TicketService ticketService;
    private final EventService eventService;
    private final SeatGradeService seatGradeService;

    @Transactional
    @DistributedLock(key = "reservation-key")
    public ReserveResponseDTO reserveTicket(ReserveRequestDTO dto) {
        EventEntity event = eventService.getEventById(dto.getEventId());
        SeatGradeEntity seatGrade = seatGradeService.getSeatGradeById(dto.getSeatGradeId());

        GradeTicketInfoEntity gradeTicketInfo = gradeTicketInfoService.getGradeTicketInfoByEventEntityAndSeatGradeEntity(event, seatGrade);

        int totalTicket = gradeTicketInfo.getNumberTotalTicket();
        int reservedTicket = gradeTicketInfo.getNumberOfReservedTickets();

        int remainingTicket = totalTicket - reservedTicket;

        if (remainingTicket > 0) {
            reservedTicket++;
            GradeTicketInfoEntity newGradeTicketInfo = gradeTicketInfoService.updateNumberOfReservedTickets(gradeTicketInfo, reservedTicket);
            TicketEntity ticket = ticketService.createTicket(event, new BigDecimal(100000));
            return new ReserveResponseDTO(new TicketResponseDTO(ticket), new GradeTicketInfoResponseDTO(newGradeTicketInfo));
        } else {
            throw new GlobalCommonException(TicketErrorResponsive.SOLD_OUT_TICKET);
        }
    }

}
