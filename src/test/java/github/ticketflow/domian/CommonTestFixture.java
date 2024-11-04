package github.ticketflow.domian;

import github.ticketflow.domian.event.EventEntity;
import github.ticketflow.domian.eventLocation.EventLocationEntity;
import github.ticketflow.domian.gradeTicketInfo.GradeTicketInfoEntity;
import github.ticketflow.domian.payment.PaymentEntity;
import github.ticketflow.domian.paymentTicket.PaymentTicketEntity;
import github.ticketflow.domian.seat.SeatEntity;
import github.ticketflow.domian.seatGrade.SeatGradeEntity;
import github.ticketflow.domian.ticket.TicketEntity;
import github.ticketflow.domian.ticket.TicketStatus;
import github.ticketflow.domian.user.entity.UserEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class CommonTestFixture {

    public static EventEntity getEventEntity(EventLocationEntity eventLocationEntity, String eventName) {
        return EventEntity.builder()
                .eventId(1L)
                .eventLocationEntity(eventLocationEntity)
                .eventName(eventName)
                .eventDescription("축구 경기")
                .date(LocalDate.of(2024, 10, 15))
                .startTime(LocalTime.of(20, 30))
                .build();
    }

    public static EventLocationEntity getEventLocationEntity(Long eventLocationId, String eventLocationName) {
        return new EventLocationEntity(eventLocationId, eventLocationName, 50000);
    }

    public static SeatGradeEntity getSeatGradeEntity(Long seatGradeId, EventLocationEntity eventLocationEntity) {
        return SeatGradeEntity.builder()
                .seatGradeId(seatGradeId)
                .eventLocation(eventLocationEntity)
                .seatGradeName("1등급")
                .seatGradePrice(BigDecimal.valueOf(10000))
                .seatGradeTotalSeats(120)
                .build();
    }

    public static GradeTicketInfoEntity getGradeTicketInfoEntity(Long gradeTicketId, EventEntity eventEntity, SeatGradeEntity seatGradeEntity) {
        return GradeTicketInfoEntity.builder()
                .gradeTicketInfoId(gradeTicketId)
                .eventEntity(eventEntity)
                .eventLocationEntity(eventEntity.getEventLocationEntity())
                .seatGradeEntity(seatGradeEntity)
                .numberTotalTicket(120)
                .numberOfReservedTickets(0)
                .build();
    }

    public static SeatEntity getSeatEntity(Long seatId, SeatGradeEntity seatGradeEntity) {
        return SeatEntity.builder()
                .seatId(seatId)
                .seatGradeEntity(seatGradeEntity)
                .seatZone("1구역")
                .seatRow(1)
                .seatNumber(1)
                .build();
    }

    public static TicketEntity getTicketEntity(Long ticketId, EventEntity eventEntity, SeatEntity seatEntity, BigDecimal price) {
        return TicketEntity.builder()
                .ticketId(1L)
                .eventEntity(eventEntity)
                .seatEntity(seatEntity)
                .ticketPrice(price)
                .ticketStatus(TicketStatus.NO_PAYMENT)
                .build();
    }

    public static TicketEntity getTicketEntity(Long ticketId, EventEntity eventEntity, BigDecimal price) {
        return TicketEntity.builder()
                .ticketId(1L)
                .eventEntity(eventEntity)
                .ticketPrice(price)
                .ticketStatus(TicketStatus.NO_PAYMENT)
                .build();
    }

    public static UserEntity getUserEntity(Long userId) {
        return UserEntity.builder()
                .id(userId)
                .email("test@test.com")
                .password("dltkdgur12")
                .phoneNumber("01011223344")
                .build();
    }

    public static PaymentEntity getPaymentEntity(Long paymentId, UserEntity userEntity) {
        return PaymentEntity.builder()
                .paymentId(paymentId)
                .userEntity(userEntity)
                .numberOfTicket(1)
                .paymentPrice(BigDecimal.valueOf(10000))
                .build();
    }

    public static PaymentTicketEntity getPaymentTicketEntity(PaymentEntity paymentEntity, TicketEntity ticketEntity) {
        return PaymentTicketEntity.builder()
                .paymentEntity(paymentEntity)
                .ticketEntity(ticketEntity)
                .build();
    }

}
