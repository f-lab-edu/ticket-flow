package github.ticketflow.domian.seat;

import github.ticketflow.domian.eventLocation.EventLocationEntity;
import github.ticketflow.domian.seat.dto.SeatRequestDTO;
import github.ticketflow.domian.seat.dto.SeatResponseDTO;
import github.ticketflow.domian.seat.dto.SeatUpdateRequestDTO;
import github.ticketflow.domian.seatGrade.SeatGradeEntity;
import github.ticketflow.domian.seatGrade.SeatGradeRepository;
import github.ticketflow.domian.seatGrade.dto.SeatGradeResponseDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class SeatServiceTest {

    @Mock
    private SeatRepository seatRepository;

    @Mock
    private SeatGradeRepository seatGradeRepository;

    @InjectMocks
    private SeatService seatService;

    @DisplayName("좌석의 id로 좌석을 가지고 오면, 정보를 반한을 한다.")
    @Test
    public void getSeatByIdTest() {
        // given
        EventLocationEntity eventLocationEntity = getEventLocationEntity(1L, "서울 월드컵 경기장", 50000);
        SeatGradeEntity seatGradeEntity = getSeatGradeEntity(1L, eventLocationEntity, "1등급", 150000, 5000);
        SeatEntity seatEntity = getSeatEntity(1L, seatGradeEntity, "1구역", 2, 25);

        BDDMockito.given(seatRepository.findById(any(Long.class)))
                .willReturn(Optional.of(seatEntity));

        // when
        SeatEntity result = seatService.getSeatById(seatEntity.getSeatId());

        // then
        assertThat(result)
                .extracting("seatId", "seatZone", "seatRow", "seatNumber")
                .contains(seatEntity.getSeatId(), seatEntity.getSeatZone(), seatEntity.getSeatNumber());

        assertThat(result.getSeatGradeEntity()).isEqualTo(seatGradeEntity);
    }

    @DisplayName("좌석 등급의 id로 좌석을 가지고 오면 리스트에 담에서 정보를 반환을 한다.")
    @Test
    void getSeatBySeatGradeTest() {
        // given
        EventLocationEntity eventLocationEntity = getEventLocationEntity(1L, "서울 월드컵 경기장", 50000);
        SeatGradeEntity seatGradeEntity = getSeatGradeEntity(1L, eventLocationEntity, "1등급", 150000, 5000);
        SeatGradeResponseDTO dto = new SeatGradeResponseDTO(seatGradeEntity);
        SeatEntity seatEntity1 = getSeatEntity(1L, seatGradeEntity, "1구역", 2, 25);
        SeatEntity seatEntity2 = getSeatEntity(2L, seatGradeEntity, "1구역", 2, 26);
        SeatEntity seatEntity3 = getSeatEntity(3L, seatGradeEntity, "1구역", 2, 27);
        List<SeatEntity> seatEntities = new ArrayList<>(List.of(seatEntity1, seatEntity2, seatEntity3));


        BDDMockito.given(seatGradeRepository.findById(any(Long.class)))
                        .willReturn(Optional.of(seatGradeEntity));


        BDDMockito.given(seatRepository.findAllBySeatGradeEntity(any(SeatGradeEntity.class)))
                .willAnswer(invocationOnMock -> {
                    Random random = new Random();
                    int randomSize = random.nextInt(seatEntities.size()) + 1;
                    return seatEntities.subList(0, randomSize);
                });

        // when
        List<SeatResponseDTO> result = seatService.getSeatByGradeId(seatGradeEntity.getSeatGradeId());

        // then
        assertThat(result.size()).isBetween(1, 3);
        assertThat(result)
                .extracting("seatId", "seatZone", "seatRow", "seatNumber")
                .containsAnyOf(
                        tuple(seatEntity1.getSeatId(), seatEntity1.getSeatZone(), seatEntity1.getSeatRow(), seatEntity1.getSeatNumber()),
                        tuple(seatEntity2.getSeatId(), seatEntity2.getSeatZone(), seatEntity2.getSeatRow(), seatEntity2.getSeatNumber()),
                        tuple(seatEntity3.getSeatId(), seatEntity3.getSeatZone(), seatEntity3.getSeatRow(), seatEntity3.getSeatNumber())
                );

        assertThat(result.get(0).getSeatGradeResponseDTO()).isEqualTo(dto);
    }

    @DisplayName("좌석을 생성을 하면, 생성된 좌석의 정보가 반환이 된다.")
    @Test
    void createSeatTest() {
        // given
        EventLocationEntity eventLocationEntity = getEventLocationEntity(1L, "서울 월드컵 경기장", 50000);
        SeatGradeEntity seatGradeEntity = getSeatGradeEntity(1L, eventLocationEntity, "1등급", 150000, 5000);
        SeatRequestDTO seatRequestDTO = new SeatRequestDTO(seatGradeEntity.getSeatGradeId(),"1구역", 2, 25);
        SeatEntity seatEntity = new SeatEntity(seatRequestDTO, seatGradeEntity);

        BDDMockito.given(seatGradeRepository.findById(any(Long.class)))
                .willReturn(Optional.of(seatGradeEntity));

        BDDMockito.given(seatRepository.save(any(SeatEntity.class)))
                .willReturn(seatEntity);

        // when
        SeatEntity result = seatService.createSeat(seatRequestDTO);

        // then
        assertThat(result)
                .extracting("seatId", "seatZone", "seatRow", "seatNumber")
                .contains(seatEntity.getSeatId(), seatEntity.getSeatZone(), seatEntity.getSeatNumber());

        assertThat(result.getSeatGradeEntity()).isEqualTo(seatGradeEntity);
    }

    @DisplayName("좌석의 정보를 수정하면, 수정된 좌석의 정보가 반환이 된다.")
    @Test
    void updateSeatTest() {
        // given
        EventLocationEntity eventLocationEntity = getEventLocationEntity(1L, "서울 월드컵 경기장", 50000);
        SeatGradeEntity seatGradeEntity = getSeatGradeEntity(1L, eventLocationEntity, "1등급", 150000, 5000);
        SeatUpdateRequestDTO updateDTO = SeatUpdateRequestDTO.builder()
                .seatRow(3)
                .seatNumber(36)
                .build();
        SeatEntity seatEntity = getSeatEntity(1L, seatGradeEntity, "1구역", 2, 25);

        BDDMockito.given(seatRepository.findById(any(Long.class)))
                .willReturn(Optional.of(seatEntity));

        BDDMockito.given(seatRepository.save(any(SeatEntity.class)))
                .willReturn(seatEntity);

        // when
        SeatEntity result = seatService.updateSeat(seatEntity.getSeatId(), updateDTO);

        // then
        assertThat(result)
                .extracting("seatId", "seatZone", "seatRow", "seatNumber")
                .contains(seatEntity.getSeatId(), seatEntity.getSeatZone(), seatEntity.getSeatNumber());

        assertThat(result.getSeatGradeEntity()).isEqualTo(seatGradeEntity);
    }

    @DisplayName("좌석을 삭제를 하면, 삭제된 좌석의 정보가 나온다.")
    @Test
    void deleteSeatTest() {
        // given
        EventLocationEntity eventLocationEntity = getEventLocationEntity(1L, "서울 월드컵 경기장", 50000);
        SeatGradeEntity seatGradeEntity = getSeatGradeEntity(1L, eventLocationEntity, "1등급", 150000, 5000);
        SeatEntity seatEntity = getSeatEntity(1L, seatGradeEntity, "1구역", 2, 25);
        BDDMockito.given(seatRepository.findById(any(Long.class)))
                .willReturn(Optional.of(seatEntity));

        BDDMockito.willDoNothing().given(seatRepository).delete(any(SeatEntity.class));

        // when
        SeatEntity result = seatService.deletedSeat(seatEntity.getSeatId());

        // then
        assertThat(result)
                .extracting("seatId", "seatZone", "seatRow", "seatNumber")
                .contains(seatEntity.getSeatId(), seatEntity.getSeatZone(), seatEntity.getSeatNumber());

        assertThat(result.getSeatGradeEntity()).isEqualTo(seatGradeEntity);
    }


    private static SeatEntity getSeatEntity(Long id, SeatGradeEntity seatGradeEntity, String seatZone, int seatRow ,int seatNumber) {
        return new SeatEntity(id, seatGradeEntity, seatZone, seatRow, seatNumber);
    }

    private static EventLocationEntity getEventLocationEntity(Long id, String name, int totalSeats) {
        return new EventLocationEntity(id, name, totalSeats);
    }

    private static SeatGradeEntity getSeatGradeEntity(Long id, EventLocationEntity eventLocationEntity, String seatGrade, int price, int seatGradeTotalSeats) {
        return new SeatGradeEntity(id, eventLocationEntity, seatGrade, BigDecimal.valueOf(price), seatGradeTotalSeats);
    }

}