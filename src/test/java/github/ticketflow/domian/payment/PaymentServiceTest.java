package github.ticketflow.domian.payment;

import github.ticketflow.domian.CommonTestFixture;
import github.ticketflow.domian.event.EventEntity;
import github.ticketflow.domian.eventLocation.EventLocationEntity;
import github.ticketflow.domian.payment.dto.PaymentRequestDTO;
import github.ticketflow.domian.payment.dto.PaymentResponseDTO;
import github.ticketflow.domian.paymentTicket.PaymentTicketEntity;
import github.ticketflow.domian.seat.SeatEntity;
import github.ticketflow.domian.seatGrade.SeatGradeEntity;
import github.ticketflow.domian.ticket.TicketEntity;
import github.ticketflow.domian.user.entity.UserEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentService paymentService;

    @DisplayName("결제의 아이디를 가지고 조회를 하면, 티켓의 정보가 나온다.")
    @Test
    void getPaymentById() {
        // given
        UserEntity userEntity = CommonTestFixture.getUserEntity(1L);
        EventLocationEntity eventLocationEntity = CommonTestFixture.getEventLocationEntity(1L, "사울 월드컵 경기장");
        EventEntity eventEntity = CommonTestFixture.getEventEntity(eventLocationEntity, "FC서울 VS 수원 삼성");
        SeatGradeEntity seatGradeEntity = CommonTestFixture.getSeatGradeEntity(1L, eventLocationEntity);
        SeatEntity seatEntity = CommonTestFixture.getSeatEntity(1L, seatGradeEntity);
        TicketEntity ticketEntity = CommonTestFixture.getTicketEntity(1L, eventEntity, seatEntity, BigDecimal.valueOf(10000));
        PaymentEntity paymentEntity = CommonTestFixture.getPaymentEntity(1L, userEntity);
        PaymentTicketEntity paymentTicketEntity = CommonTestFixture.getPaymentTicketEntity(paymentEntity, ticketEntity);

        BDDMockito.given(paymentRepository.findById(any(Long.class)))
                .willReturn(Optional.of(paymentEntity));


        // when
        PaymentEntity result = paymentService.getPaymentById(paymentEntity.getPaymentId());

        // then
        assertThat(result).extracting("numberOfTicket", "paymentPrice")
                .contains(paymentEntity.getNumberOfTicket(), paymentEntity.getPaymentPrice());
    }

    @DisplayName("유저의 엔티티로 티켓의 정보를 가지고 온다.")
    @Test
    void getPaymentByUserEntity() {
        // given
        UserEntity userEntity = CommonTestFixture.getUserEntity(1L);
        EventLocationEntity eventLocationEntity = CommonTestFixture.getEventLocationEntity(1L, "사울 월드컵 경기장");
        EventEntity eventEntity = CommonTestFixture.getEventEntity(eventLocationEntity, "FC서울 VS 수원 삼성");
        SeatGradeEntity seatGradeEntity = CommonTestFixture.getSeatGradeEntity(1L, eventLocationEntity);
        SeatEntity seatEntity = CommonTestFixture.getSeatEntity(1L, seatGradeEntity);
        TicketEntity ticketEntity = CommonTestFixture.getTicketEntity(1L, eventEntity, seatEntity, BigDecimal.valueOf(10000));
        PaymentEntity paymentEntity = CommonTestFixture.getPaymentEntity(1L, userEntity);
        PaymentTicketEntity paymentTicketEntity = CommonTestFixture.getPaymentTicketEntity(paymentEntity, ticketEntity);

        BDDMockito.given(paymentRepository.findByUserEntity(any(UserEntity.class)))
                .willReturn(List.of(paymentEntity));

        // when
        List<PaymentResponseDTO> result = paymentService.getPaymentByUserEntity(userEntity);

        // then
        assertThat(result).hasSize(1);
        assertThat(result.get(0)).extracting("numberOfTicket", "paymentPrice")
                .contains(paymentEntity.getNumberOfTicket(), paymentEntity.getPaymentPrice());
    }

    @DisplayName("결제를 생성하면 생성된 정보가 나온다.")
    @Test
    void createPayment() {
        // given
        UserEntity userEntity = CommonTestFixture.getUserEntity(1L);
        EventLocationEntity eventLocationEntity = CommonTestFixture.getEventLocationEntity(1L, "사울 월드컵 경기장");
        EventEntity eventEntity = CommonTestFixture.getEventEntity(eventLocationEntity, "FC서울 VS 수원 삼성");
        SeatGradeEntity seatGradeEntity = CommonTestFixture.getSeatGradeEntity(1L, eventLocationEntity);
        SeatEntity seatEntity = CommonTestFixture.getSeatEntity(1L, seatGradeEntity);
        TicketEntity ticketEntity = CommonTestFixture.getTicketEntity(1L, eventEntity, seatEntity, BigDecimal.valueOf(10000));
        PaymentEntity paymentEntity = CommonTestFixture.getPaymentEntity(1L, userEntity);
        PaymentTicketEntity paymentTicketEntity = CommonTestFixture.getPaymentTicketEntity(paymentEntity, ticketEntity);

        PaymentRequestDTO dto = new PaymentRequestDTO(1L, 1, List.of(1L), BigDecimal.valueOf(10000));
        PaymentVO paymentVO = new PaymentVO(userEntity, List.of(ticketEntity), dto);

        BDDMockito.given(paymentRepository.save(any(PaymentEntity.class)))
                .willReturn(paymentEntity);

        // when
        PaymentEntity result = paymentService.createPayment(paymentVO);

        // then
        assertThat(result).extracting("numberOfTicket", "paymentPrice")
                .contains(paymentEntity.getNumberOfTicket(), paymentEntity.getPaymentPrice());
    }

    @DisplayName("수정하기를 했을 떄, deletedAt에 변경된 값이 나온다.")
    @Test
    void updatePaymentTest() {
        // given
        UserEntity userEntity = CommonTestFixture.getUserEntity(1L);
        EventLocationEntity eventLocationEntity = CommonTestFixture.getEventLocationEntity(1L, "사울 월드컵 경기장");
        EventEntity eventEntity = CommonTestFixture.getEventEntity(eventLocationEntity, "FC서울 VS 수원 삼성");
        SeatGradeEntity seatGradeEntity = CommonTestFixture.getSeatGradeEntity(1L, eventLocationEntity);
        SeatEntity seatEntity = CommonTestFixture.getSeatEntity(1L, seatGradeEntity);
        TicketEntity ticketEntity = CommonTestFixture.getTicketEntity(1L, eventEntity, seatEntity, BigDecimal.valueOf(10000));
        PaymentEntity paymentEntity = CommonTestFixture.getPaymentEntity(1L, userEntity);
        PaymentTicketEntity paymentTicketEntity = CommonTestFixture.getPaymentTicketEntity(paymentEntity, ticketEntity);

        LocalDate deletedAt = LocalDate.of(2024, 10, 4);

        PaymentEntity updatePaymentEntity = paymentEntity.update(deletedAt);

        BDDMockito.given(paymentRepository.findById(any(Long.class)))
                .willReturn(Optional.of(paymentEntity));

        BDDMockito.given(paymentRepository.save(any(PaymentEntity.class)))
                .willReturn(updatePaymentEntity);

        // when
        PaymentEntity result = paymentService.updatePayment(paymentEntity.getPaymentId());

        // then
        assertThat(result).extracting("deletedAt").isEqualTo(updatePaymentEntity.getDeletedAt());
    }

    @DisplayName("결제 정보를 삭제를 하면, 삭제된 정보가 나온다.")
    @Test
    void deletePaymentTest() {
        // given
        UserEntity userEntity = CommonTestFixture.getUserEntity(1L);
        EventLocationEntity eventLocationEntity = CommonTestFixture.getEventLocationEntity(1L, "사울 월드컵 경기장");
        EventEntity eventEntity = CommonTestFixture.getEventEntity(eventLocationEntity, "FC서울 VS 수원 삼성");
        SeatGradeEntity seatGradeEntity = CommonTestFixture.getSeatGradeEntity(1L, eventLocationEntity);
        SeatEntity seatEntity = CommonTestFixture.getSeatEntity(1L, seatGradeEntity);
        TicketEntity ticketEntity = CommonTestFixture.getTicketEntity(1L, eventEntity, seatEntity, BigDecimal.valueOf(10000));
        PaymentEntity paymentEntity = CommonTestFixture.getPaymentEntity(1L, userEntity);
        PaymentTicketEntity paymentTicketEntity = CommonTestFixture.getPaymentTicketEntity(paymentEntity, ticketEntity);

        BDDMockito.given(paymentRepository.findById(any(Long.class)))
                .willReturn(Optional.of(paymentEntity));

        BDDMockito.willDoNothing().given(paymentRepository).delete(any(PaymentEntity.class));

        // when
        PaymentEntity result = paymentService.deletedPayment(paymentEntity.getPaymentId());

        // then
        assertThat(result).extracting("numberOfTicket", "paymentPrice")
                .contains(paymentEntity.getNumberOfTicket(), paymentEntity.getPaymentPrice());
    }

}