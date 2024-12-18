package github.ticketflow.domian.seatGrade;

import github.ticketflow.domian.CommonTestFixture;
import github.ticketflow.domian.eventLocation.EventLocationEntity;
import github.ticketflow.domian.eventLocation.EventLocationRepository;
import github.ticketflow.domian.eventLocation.dto.EventLocationResponseDTO;
import github.ticketflow.domian.seatGrade.dto.SeatGradeRequestDTO;
import github.ticketflow.domian.seatGrade.dto.SeatGradeResponseDTO;
import github.ticketflow.domian.seatGrade.dto.SeatGradeUpdateRequestDTO;
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

import static github.ticketflow.domian.CommonTestFixture.*;
import static github.ticketflow.domian.CommonTestFixture.getSeatGradeEntity;
import static org.mockito.ArgumentMatchers.any;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class SeatGradeServiceTest {

    @Mock
    private SeatGradeRepository seatGradeRepository;

    @Mock
    private EventLocationRepository eventLocationRepository;

    @InjectMocks
    private SeatGradeService seatGradeService;

    @DisplayName("좌석 등급의 id로 좌석 등급을 가지고 오면, 좌석 등급의 정보가 나온다.")
    @Test
    void getSeatGradeByIdTest() {
        // given
        EventLocationEntity eventLocationEntity = getEventLocationEntity(1L, "서울 월드컵 경기장");
        SeatGradeEntity seatGradeEntity = getSeatGradeEntity(1L, eventLocationEntity);
        BDDMockito.given(seatGradeRepository.findById(seatGradeEntity.getSeatGradeId()))
                .willReturn(Optional.of(seatGradeEntity));

        // when
        SeatGradeEntity result = seatGradeService.getSeatGradeById(seatGradeEntity.getSeatGradeId());

        // then
        assertThat(result)
                .extracting("seatGradeId", "seatGradeName", "seatGradePrice", "seatGradeTotalSeats")
                .contains(1L, seatGradeEntity.getSeatGradeName(), seatGradeEntity.getSeatGradePrice(), seatGradeEntity.getSeatGradeTotalSeats());

        assertThat(result.getEventLocation()).isEqualTo(eventLocationEntity);
    }



    @DisplayName("이벤트 장소 id로 이벤트 장소에 있는 좌석 등급들을 가지고 올 수 있다.")
    @Test
    void getSeatGradeByEventLocationIdTest() {
        // give
        EventLocationEntity eventLocationEntitySeoul = getEventLocationEntity(1L, "서울 월드컵 경기장");

        EventLocationResponseDTO dto = new EventLocationResponseDTO(eventLocationEntitySeoul);
        SeatGradeEntity seatGradeEntity1st = getSeatGradeEntity(1L, eventLocationEntitySeoul);
        SeatGradeEntity seatGradeEntity2nd = getSeatGradeEntity(2L, eventLocationEntitySeoul);
        SeatGradeEntity seatGradeEntity3rd = getSeatGradeEntity(3L, eventLocationEntitySeoul);
        List<SeatGradeEntity> seatGradeEntities = new ArrayList<>(List.of(seatGradeEntity1st, seatGradeEntity2nd, seatGradeEntity3rd));

        BDDMockito.given(eventLocationRepository.findById(any(Long.class)))
                .willReturn(Optional.of(eventLocationEntitySeoul));

        BDDMockito.given(seatGradeRepository.findAllByEventLocation(any(EventLocationEntity.class)))
                .willAnswer(invocation -> {
                    Random random = new Random();
                    int randomSize = random.nextInt(seatGradeEntities.size()) + 1;
                    return seatGradeEntities.subList(0, randomSize);
                });

        // when
        List<SeatGradeResponseDTO> result = seatGradeService.getSeatGradeByEventLocationId(eventLocationEntitySeoul.getEventLocationId());

        // then


        assertThat(result.size()).isBetween(1, 3);
        assertThat(result).extracting("seatGradeId", "seatGradeName", "seatGradePrice", "seatGradeTotalSeats")
                .containsAnyOf(
                        tuple(seatGradeEntity1st.getSeatGradeId(), seatGradeEntity1st.getSeatGradeName(), seatGradeEntity1st.getSeatGradePrice(), seatGradeEntity1st.getSeatGradeTotalSeats()),
                        tuple(seatGradeEntity2nd.getSeatGradeId(), seatGradeEntity2nd.getSeatGradeName(), seatGradeEntity2nd.getSeatGradePrice(), seatGradeEntity2nd.getSeatGradeTotalSeats()),
                        tuple(seatGradeEntity3rd.getSeatGradeId(), seatGradeEntity3rd.getSeatGradeName(), seatGradeEntity3rd.getSeatGradePrice(), seatGradeEntity3rd.getSeatGradeTotalSeats())
                );

        assertThat(result.get(0).getEventLocation()).isEqualTo(dto);
    }

    @DisplayName("새로운 좌석 등급을 생성을 하면, 새롭게 만들어진 좌석 등급이 생긴다.")
    @Test
    void createSeatGradeTest() {
        // give
        EventLocationEntity eventLocationEntity = getEventLocationEntity(1L, "서울 월드컵 경기장");
        SeatGradeRequestDTO seatGradeRequestDTO = new SeatGradeRequestDTO(1L, "1등급", setBigDecimal(150000), 5000);
        SeatGradeEntity seatGradeEntity = new SeatGradeEntity(seatGradeRequestDTO, eventLocationEntity);

        BDDMockito.given(eventLocationRepository.findById(any(Long.class)))
                        .willReturn(Optional.of(eventLocationEntity));

        BDDMockito.given(seatGradeRepository.save(any(SeatGradeEntity.class)))
                .willReturn(seatGradeEntity);

        // when
        SeatGradeEntity result = seatGradeService.createSeatGrade(seatGradeRequestDTO);

        // then
        assertThat(result)
                .extracting("seatGradeName", "seatGradePrice", "seatGradeTotalSeats")
                .contains(seatGradeEntity.getSeatGradeName(), seatGradeEntity.getSeatGradePrice(), seatGradeEntity.getSeatGradeTotalSeats());

        assertThat(result.getEventLocation()).isEqualTo(eventLocationEntity);

    }

    @DisplayName("원하는 정보를 수정하면, 수정된 정보가 나온다.")
    @Test
    void updateSeatGradeTest() {
        // given
        EventLocationEntity eventLocationEntity = getEventLocationEntity(1L, "서울 월드컵 경기장");
        SeatGradeEntity seatGradeEntity = getSeatGradeEntity(1L, eventLocationEntity);

        SeatGradeUpdateRequestDTO seatGradeUpdateRequestDTO = SeatGradeUpdateRequestDTO.builder()
                .seatGradePrice(setBigDecimal(200000))
                .seatGradeTotalSeats(100000)
                .build();

        seatGradeEntity.update(seatGradeUpdateRequestDTO, eventLocationEntity);

        BDDMockito.given(seatGradeRepository.findById(any(Long.class)))
                .willReturn(Optional.of(seatGradeEntity));

        BDDMockito.given(seatGradeRepository.save(any(SeatGradeEntity.class)))
                .willReturn(seatGradeEntity);

        // when
        SeatGradeEntity result = seatGradeService.updateSeatGrade(seatGradeEntity.getSeatGradeId(), seatGradeUpdateRequestDTO);

        // then
        assertThat(result)
                .extracting("seatGradeName", "seatGradePrice", "seatGradeTotalSeats")
                .contains(seatGradeEntity.getSeatGradeName(), seatGradeEntity.getSeatGradePrice(), seatGradeEntity.getSeatGradeTotalSeats());
    }

    @DisplayName("좌석 등급을 삭제를 하면, 식제된 좌석 등급의 정보가 나온다.")
    @Test
    void deleteSeatGradeTest() {
        // given
        EventLocationEntity eventLocationEntity = getEventLocationEntity(1L, "서울 월드컵 경기장");
        SeatGradeEntity seatGradeEntity = getSeatGradeEntity(1L, eventLocationEntity);
        BDDMockito.given(seatGradeRepository.findById(any(Long.class)))
                .willReturn(Optional.of(seatGradeEntity));

        BDDMockito.willDoNothing().given(seatGradeRepository).delete(any(SeatGradeEntity.class));

        // when
        SeatGradeEntity result = seatGradeService.deletedSeatGrade(seatGradeEntity.getSeatGradeId());

        // then
        assertThat(result)
                .extracting("seatGradeId", "seatGradeName", "seatGradePrice", "seatGradeTotalSeats")
                .contains(seatGradeEntity.getSeatGradeId(), seatGradeEntity.getSeatGradeName(), seatGradeEntity.getSeatGradePrice(), seatGradeEntity.getSeatGradeTotalSeats());

        assertThat(result.getEventLocation()).isEqualTo(eventLocationEntity);

    }

    private static BigDecimal setBigDecimal(int price) {
        return new BigDecimal(price);
    }
}