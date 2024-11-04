package github.ticketflow.domian.reservation;

import github.ticketflow.config.exception.GlobalCommonException;
import github.ticketflow.config.exception.ticket.TicketErrorResponsive;
import github.ticketflow.domian.CommonTestFixture;
import github.ticketflow.domian.event.EventEntity;
import github.ticketflow.domian.event.EventService;
import github.ticketflow.domian.eventLocation.EventLocationEntity;
import github.ticketflow.domian.gradeTicketInfo.GradeTicketInfoEntity;
import github.ticketflow.domian.gradeTicketInfo.GradeTicketInfoService;
import github.ticketflow.domian.reservation.dto.ReserveRequestDTO;
import github.ticketflow.domian.reservation.dto.ReserveResponseDTO;
import github.ticketflow.domian.seatGrade.SeatGradeEntity;
import github.ticketflow.domian.seatGrade.SeatGradeService;
import github.ticketflow.domian.ticket.TicketEntity;
import github.ticketflow.domian.ticket.TicketService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.redisson.api.RedissonClient;

import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
class ReserveServiceTest {

    @Mock
    private GradeTicketInfoService gradeTicketInfoService;

    @Mock
    private TicketService ticketService;

    @Mock
    private EventService eventService;

    @Mock
    private SeatGradeService seatGradeService;

    @Mock
    private RedissonClient redissonClient;

    @InjectMocks
    private ReserveService reserveService;

    @DisplayName("동시 예매 시도 시 동시성 제어 테스트")
    @Test
    void testConcurrentTicketReservations () throws InterruptedException {
        // given
        EventLocationEntity mockEventLocation = CommonTestFixture.getEventLocationEntity(1L, "서울 월드컵 경기장");
        SeatGradeEntity mockSeatGrade = CommonTestFixture.getSeatGradeEntity(1L, mockEventLocation);
        EventEntity mockEvent = CommonTestFixture.getEventEntity(mockEventLocation, "FC서울 vs 수원삼성");
        GradeTicketInfoEntity mockGradeTicketInfo = CommonTestFixture.getGradeTicketInfoEntity(1L, mockEvent, mockSeatGrade);
        TicketEntity mockTicket = CommonTestFixture.getTicketEntity(1L, mockEvent, new BigDecimal(100000));
        ReserveRequestDTO dto = new ReserveRequestDTO(mockEvent.getEventId(), mockSeatGrade.getSeatGradeId(), new BigDecimal(100000));

        BDDMockito.given(eventService.getEventById(any(Long.class)))
                .willReturn(mockEvent);
        BDDMockito.given(seatGradeService.getSeatGradeById(any(Long.class)))
                .willReturn(mockSeatGrade);
        BDDMockito.given(gradeTicketInfoService.getGradeTicketInfoByEventEntityAndSeatGradeEntity(any(EventEntity.class), any(SeatGradeEntity.class)))
                .willReturn(mockGradeTicketInfo);
        BDDMockito.given(gradeTicketInfoService.updateNumberOfReservedTickets(any(GradeTicketInfoEntity.class), anyInt()))
                .willAnswer(invocation -> {
                    GradeTicketInfoEntity gradeTicketInfo = invocation.getArgument(0);
                    int newReservedTickets = invocation.getArgument(1);

                    gradeTicketInfo.update(newReservedTickets);

                    return gradeTicketInfo;
                });
        BDDMockito.given(ticketService.createTicket(any(EventEntity.class), any(BigDecimal.class)))
                .willReturn(mockTicket);


        int numberOfThreads = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        ReserveResponseDTO finalResult = null;

        // when
        for (int i = 0; i < numberOfThreads; i++) {
            Future<ReserveResponseDTO> future = executorService.submit(() -> {
                try {
                    return reserveService.reserveTicket(dto);
                } catch (GlobalCommonException e) {
                    assertEquals(TicketErrorResponsive.SOLD_OUT_TICKET, e.getErrorCode());
                    return null;
                } finally {
                    latch.countDown();
                }
            });

            try {
                finalResult = future.get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        latch.await();
        executorService.shutdown();



        Assertions.assertThat(finalResult.getGradeTicketInfoResponse().getNumberOfReservedTickets()).isEqualTo(100);
    }
}