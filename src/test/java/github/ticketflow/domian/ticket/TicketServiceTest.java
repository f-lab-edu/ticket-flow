package github.ticketflow.domian.ticket;

import github.ticketflow.domian.CommonTestFixture;
import github.ticketflow.domian.event.EventEntity;
import github.ticketflow.domian.eventLocation.EventLocationEntity;
import github.ticketflow.domian.seat.SeatEntity;
import github.ticketflow.domian.seatGrade.SeatGradeEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketService ticketService;

    @DisplayName("티켓의 id로 티켓을 조회하면, 티켓의 정보가 나온다.")
    @Test
    void getTicketByIdTest() {
        // given
        EventLocationEntity eventLocationEntity = CommonTestFixture.getEventLocationEntity(1L, "서울 월드컵 경기장");
        EventEntity eventEntity = CommonTestFixture.getEventEntity(eventLocationEntity, "FC서울 vs 수원 삼성");
        SeatGradeEntity seatGradeEntity = CommonTestFixture.getSeatGradeEntity(1L, eventLocationEntity);
        SeatEntity seatEntity = CommonTestFixture.getSeatEntity(1L, seatGradeEntity);
        TicketEntity ticketEntity = CommonTestFixture.getTicketEntity(1L, eventEntity, seatEntity, intToBigDecimal(1000000));

        BDDMockito.given(ticketRepository.findById(any(Long.class)))
                .willReturn(Optional.ofNullable(ticketEntity));

        // when
        TicketEntity result = ticketService.getTicketById(ticketEntity.getTicketId());

        // then
        assertThat(result).isEqualTo(ticketEntity);
        assertThat(result).extracting("ticketPrice", "ticketStatus")
                .contains(ticketEntity.getTicketPrice(), ticketEntity.getTicketStatus());

    }

    @DisplayName("좌석 엔티티로 조회를 하면, 티켓의 정보가 나온다.")
    @Test
    void getTicketBySeatEntityTest() {
        // given
        EventLocationEntity eventLocationEntity = CommonTestFixture.getEventLocationEntity(1L, "서울 월드컵 경기장");
        EventEntity eventEntity = CommonTestFixture.getEventEntity(eventLocationEntity, "FC서울 vs 수원 삼성");
        SeatGradeEntity seatGradeEntity = CommonTestFixture.getSeatGradeEntity(1L, eventLocationEntity);
        SeatEntity seatEntity = CommonTestFixture.getSeatEntity(1L, seatGradeEntity);
        TicketEntity ticketEntity = CommonTestFixture.getTicketEntity(1L, eventEntity, seatEntity, intToBigDecimal(1000000));

        BDDMockito.given(ticketRepository.findBySeatEntity(any(SeatEntity.class)))
                .willReturn(Optional.ofNullable(ticketEntity));

        // when
        TicketEntity result = ticketService.getTicketBySeatEntity(seatEntity);

        // then
        assertThat(result).isEqualTo(ticketEntity);
        assertThat(result).extracting("ticketPrice", "ticketStatus")
                .contains(ticketEntity.getTicketPrice(), ticketEntity.getTicketStatus());
    }

    @DisplayName("티켓을 생성을 하면, 티켓의 정보가 나온다.")
    @Test
    void createTicketTest() {
        // given
        EventLocationEntity eventLocationEntity = CommonTestFixture.getEventLocationEntity(1L, "서울 월드컵 경기장");
        EventEntity eventEntity = CommonTestFixture.getEventEntity(eventLocationEntity, "FC서울 vs 수원 삼성");
        SeatGradeEntity seatGradeEntity = CommonTestFixture.getSeatGradeEntity(1L, eventLocationEntity);
        SeatEntity seatEntity = CommonTestFixture.getSeatEntity(1L, seatGradeEntity);
        TicketEntity ticketEntity = CommonTestFixture.getTicketEntity(1L, eventEntity, seatEntity, intToBigDecimal(1000000));

        BDDMockito.given(ticketRepository.save(any(TicketEntity.class)))
                .willReturn(ticketEntity);

        // when
        TicketEntity result = ticketService.createTicket(eventEntity, seatEntity, ticketEntity.getTicketPrice());

        // then
        assertThat(result).isEqualTo(ticketEntity);
        assertThat(result).extracting("ticketPrice", "ticketStatus")
                .contains(ticketEntity.getTicketPrice(), ticketEntity.getTicketStatus());
    }

    @DisplayName("티켓을 수정을 하면, 수정된 정보가 나온다.")
    @Test
    void updateTicketTest() {
        // given
        EventLocationEntity eventLocationEntity = CommonTestFixture.getEventLocationEntity(1L, "서울 월드컵 경기장");
        EventEntity eventEntity = CommonTestFixture.getEventEntity(eventLocationEntity, "FC서울 vs 수원 삼성");
        SeatGradeEntity seatGradeEntity = CommonTestFixture.getSeatGradeEntity(1L, eventLocationEntity);
        SeatEntity seatEntity = CommonTestFixture.getSeatEntity(1L, seatGradeEntity);
        TicketEntity ticketEntity = CommonTestFixture.getTicketEntity(1L, eventEntity, seatEntity, intToBigDecimal(1000000));

        TicketUpdateVO updateClass = TicketUpdateVO.builder()
                .ticketPrice(intToBigDecimal(300000))
                .build();

        BDDMockito.given(ticketRepository.findById(any(Long.class)))
                .willReturn(Optional.ofNullable(ticketEntity));

        BDDMockito.given(ticketRepository.save(any(TicketEntity.class)))
                .willReturn(ticketEntity);

        // then
        TicketEntity result = ticketService.updateTicket(ticketEntity.ticketId, updateClass);

        // then
        assertThat(result).isEqualTo(ticketEntity);
        assertThat(result.getTicketPrice()).isEqualTo(updateClass.getTicketPrice());
    }

    @DisplayName("티켓을 삭제하면, 삭제된 정보가 나온다.")
    @Test
    void deleteTicketTest() {
        // given
        EventLocationEntity eventLocationEntity = CommonTestFixture.getEventLocationEntity(1L, "서울 월드컵 경기장");
        EventEntity eventEntity = CommonTestFixture.getEventEntity(eventLocationEntity, "FC서울 vs 수원 삼성");
        SeatGradeEntity seatGradeEntity = CommonTestFixture.getSeatGradeEntity(1L, eventLocationEntity);
        SeatEntity seatEntity = CommonTestFixture.getSeatEntity(1L, seatGradeEntity);
        TicketEntity ticketEntity = CommonTestFixture.getTicketEntity(1L, eventEntity, seatEntity, intToBigDecimal(1000000));

        BDDMockito.given(ticketRepository.findById(any(Long.class)))
                .willReturn(Optional.ofNullable(ticketEntity));

        BDDMockito.willDoNothing().given(ticketRepository).delete(any(TicketEntity.class));

        // when
        TicketEntity result = ticketService.deleteTicket(ticketEntity.ticketId);

        // then
        assertThat(result).isEqualTo(ticketEntity);
        assertThat(result).extracting("ticketPrice", "ticketStatus")
                .contains(ticketEntity.getTicketPrice(), ticketEntity.getTicketStatus());
    }

    private BigDecimal intToBigDecimal(int price) {
        return BigDecimal.valueOf(price);
    }

}