package github.ticketflow.domian.gradeTicketInfo;

import github.ticketflow.domian.event.EventEntity;
import github.ticketflow.domian.event.dto.EventResponseDTO;
import github.ticketflow.domian.eventLocation.EventLocationEntity;
import github.ticketflow.domian.eventLocation.dto.EventLocationResponseDTO;
import github.ticketflow.domian.gradeTicketInfo.dto.GradeTicketInfoRequestDTO;
import github.ticketflow.domian.gradeTicketInfo.dto.GradeTicketInfoResponseDTO;
import github.ticketflow.domian.gradeTicketInfo.dto.GradeTicketInfoUpdateRequestDTO;
import github.ticketflow.domian.seatGrade.SeatGradeEntity;
import github.ticketflow.domian.seatGrade.dto.SeatGradeResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import static github.ticketflow.domian.CommonTestFixture.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class GradeTicketInfoServiceTest {

    @Mock
    private GradeTicketInfoRepository gradeTicketInfoRepository;

    @InjectMocks
    private GradeTicketInfoService gradeTicketInfoService;

    @DisplayName("등급별 좌석의 티켓의 정보의 id로 등급별 좌석의 티켓의 정보의 정보가 니온다.")
    @Test
    void getGradeTicketInfoByIdTest() {
        // given
        EventLocationEntity eventLocationEntity = getEventLocationEntity(1L, "서울 월드컵 경기장");
        EventEntity eventEntity =  getEventEntity(eventLocationEntity, "FC서울 VS 수원 삼성");
        SeatGradeEntity seatGradeEntity = getSeatGradeEntity(1L, eventLocationEntity);
        GradeTicketInfoEntity gradeTicketInfoEntity = gradeTicketInfoEntity(1L, eventEntity, seatGradeEntity);

        BDDMockito.given(gradeTicketInfoRepository.findById(any(Long.class)))
                .willReturn(Optional.of(gradeTicketInfoEntity));

        // when
        GradeTicketInfoEntity result = gradeTicketInfoService.getGradeTicketInfoById(gradeTicketInfoEntity.getGradeTicketInfoId());

        // then
        assertThat(result).extracting("numberTotalTicket", "numberOfRemainingTickets")
                .contains(gradeTicketInfoEntity.getNumberTotalTicket(), gradeTicketInfoEntity.getNumberOfRemainingTickets());

        assertThat(result.getEventEntity()).isEqualTo(eventEntity);
        assertThat(result.getEventLocationEntity()).isEqualTo(eventLocationEntity);
        assertThat(result.getSeatGradeEntity()).isEqualTo(seatGradeEntity);

    }

    @DisplayName("이벤트 엔티티로 등급별 티켓 정보를 검색하면, 리스트로 등급별 티켓 정보들이 나온다.")
    @Test
    void getGradeTicketInfoByEventEntityTest() {
        EventLocationEntity eventLocationEntity = getEventLocationEntity(1L, "서울 월드컵 경기장");
        EventEntity eventEntity = getEventEntity(eventLocationEntity, "FC서울 VS 수원 삼성");
        SeatGradeEntity seatGradeEntity1 = getSeatGradeEntity(1L, eventLocationEntity);
        SeatGradeEntity seatGradeEntity2 = getSeatGradeEntity(2L, eventLocationEntity);
        SeatGradeEntity seatGradeEntity3 = getSeatGradeEntity(3L, eventLocationEntity);

        GradeTicketInfoEntity gradeTicketInfoEntity1 = gradeTicketInfoEntity(1L, eventEntity, seatGradeEntity1);
        GradeTicketInfoEntity gradeTicketInfoEntity2 = gradeTicketInfoEntity(1L, eventEntity, seatGradeEntity2);
        GradeTicketInfoEntity gradeTicketInfoEntity3 = gradeTicketInfoEntity(1L, eventEntity, seatGradeEntity3);

        List<GradeTicketInfoEntity> gradeTicketInfoEntities = List.of(gradeTicketInfoEntity1, gradeTicketInfoEntity2, gradeTicketInfoEntity3);

        BDDMockito.given(gradeTicketInfoRepository.findAllByEventEntity(any(EventEntity.class)))
                .willAnswer(invocationOnMock -> {
                    Random random = new Random();
                    int randomSize = random.nextInt(gradeTicketInfoEntities.size()) + 1;
                    return gradeTicketInfoEntities.subList(0, randomSize);
                });

        // when
        List<GradeTicketInfoResponseDTO> result = gradeTicketInfoService.getGradeTicketInfoByEventEntity(eventEntity);

        // then
        assertThat(result.size()).isBetween(1, 3);
        assertThat(result).extracting("gradeTicketInfoId", "numberTotalTicket", "numberOfRemainingTickets")
                .containsAnyOf(
                        tuple(1L, gradeTicketInfoEntity1.getNumberTotalTicket(), gradeTicketInfoEntity1.getNumberOfRemainingTickets()),
                        tuple(2L, gradeTicketInfoEntity2.getNumberTotalTicket(), gradeTicketInfoEntity2.getNumberOfRemainingTickets()),
                        tuple(3L, gradeTicketInfoEntity3.getNumberTotalTicket(), gradeTicketInfoEntity3.getNumberOfRemainingTickets())
                );
    }

    @DisplayName("이벤트 엔티티와 좌석 등급 엔티리로 등급별 좌석 정보를 가지고 오면 등급별 좌석 정보가 나온다.")
    @Test
    void getGradeTicketInfoByEventEntityAndSeatGradeEntityTest() {
        // given
        EventLocationEntity eventLocationEntity = getEventLocationEntity(1L, "서울 월드컵 경기장");
        EventEntity eventEntity = getEventEntity(eventLocationEntity, "FC서울 VS 수원 삼성");
        SeatGradeEntity seatGradeEntity = getSeatGradeEntity(1L, eventLocationEntity);
        GradeTicketInfoEntity gradeTicketInfoEntity = gradeTicketInfoEntity(1L, eventEntity, seatGradeEntity);

        BDDMockito.given(gradeTicketInfoRepository.findByEventEntityAndSeatGradeEntity(any(EventEntity.class), any(SeatGradeEntity.class)))
                .willReturn(Optional.of(gradeTicketInfoEntity));

        // when
        GradeTicketInfoEntity result = gradeTicketInfoService.getGradeTicketInfoByEventEntityAndSeatGradeEntity(eventEntity, seatGradeEntity);

        // then
        assertThat(result).extracting("numberTotalTicket", "numberOfRemainingTickets")
                .contains(gradeTicketInfoEntity.getNumberTotalTicket(), gradeTicketInfoEntity.getNumberOfRemainingTickets());

        assertThat(result.getEventEntity()).isEqualTo(eventEntity);
        assertThat(result.getEventLocationEntity()).isEqualTo(eventLocationEntity);
        assertThat(result.getSeatGradeEntity()).isEqualTo(seatGradeEntity);
    }

    @DisplayName("등급별 좌석 정보를 생성을 하면, 등급별 좌석 정보가 나온다.")
    @Test
    void createGradeTicketInfoTest() {
        // given
        EventLocationEntity eventLocationEntity = getEventLocationEntity(1L, "서울 월드컵 경기장");
        EventEntity eventEntity = getEventEntity(eventLocationEntity, "FC서울 VS 수원 삼성");
        SeatGradeEntity seatGradeEntity = getSeatGradeEntity(1L, eventLocationEntity);
        GradeTicketInfoEntity gradeTicketInfoEntity = gradeTicketInfoEntity(1L, eventEntity, seatGradeEntity);

        GradeTicketInfoRequestDTO dto = new GradeTicketInfoRequestDTO(eventEntity.getEventId(), seatGradeEntity.getSeatGradeId(), 120, 120);


        BDDMockito.given(gradeTicketInfoRepository.save(any(GradeTicketInfoEntity.class)))
                .willReturn(gradeTicketInfoEntity);

        // when
        GradeTicketInfoEntity result = gradeTicketInfoService.createGradeTicketInfo(dto, eventEntity, eventEntity.getEventLocationEntity(), seatGradeEntity);

        // then
        assertThat(result).extracting("numberTotalTicket", "numberOfRemainingTickets")
                .contains(gradeTicketInfoEntity.getNumberTotalTicket(), gradeTicketInfoEntity.getNumberOfRemainingTickets());
        assertThat(result.getEventEntity()).isEqualTo(eventEntity);
        assertThat(result.getEventLocationEntity()).isEqualTo(eventLocationEntity);
        assertThat(result.getSeatGradeEntity()).isEqualTo(seatGradeEntity);
    }

    @DisplayName("등급별 좌석 정보에 수정이 필요한 부분을 수정을 하면, 수정된 정보가 나온다.")
    @Test
    void updateGradeTicketInfoTest() {
        EventLocationEntity eventLocationEntity = getEventLocationEntity(1L, "서울 월드컵 경기장");
        EventEntity eventEntity = getEventEntity(eventLocationEntity, "FC서울 VS 수원 삼성");
        SeatGradeEntity seatGradeEntity = getSeatGradeEntity(1L, eventLocationEntity);
        GradeTicketInfoEntity gradeTicketInfoEntity = gradeTicketInfoEntity(1L, eventEntity, seatGradeEntity);

        EventLocationEntity eventLocationEntitySuwon = getEventLocationEntity(2L, "수원 빅버드 경기장");
        EventEntity eventEntity2 = getEventEntity(eventLocationEntitySuwon, "수원 삼성 VS 포항 스틸러스");
        GradeTicketInfoUpdateRequestDTO dto = GradeTicketInfoUpdateRequestDTO.builder()
                .eventId(2L)
                .numberTotalTicket(150)
                .numberOfRemainingTickets(150)
                .build();

        GradeTicketInfoUpdate update = GradeTicketInfoUpdate.builder()
                .eventEntity(eventEntity2)
                .build();

        GradeTicketInfoEntity updateGradeTicketInfoEntity = gradeTicketInfoEntity.update(dto, update);

        BDDMockito.given(gradeTicketInfoRepository.findById(any(Long.class)))
                .willReturn(Optional.of(gradeTicketInfoEntity));

        BDDMockito.given(gradeTicketInfoRepository.save(any(GradeTicketInfoEntity.class)))
                .willReturn(updateGradeTicketInfoEntity);

        // then
        GradeTicketInfoEntity result = gradeTicketInfoService.updateGradeTicketInfo(gradeTicketInfoEntity.getGradeTicketInfoId(), dto, update);

        // then
        assertThat(result).extracting("numberTotalTicket", "numberOfRemainingTickets")
                .contains(updateGradeTicketInfoEntity.getNumberTotalTicket(), updateGradeTicketInfoEntity.getNumberOfRemainingTickets());
        assertThat(result.getEventEntity()).isEqualTo(eventEntity2);
        assertThat(result.getEventLocationEntity()).isEqualTo(eventLocationEntitySuwon);
    }

    @DisplayName("등급별 좌석 정보를 삭제를 하면, 삭제된 등급별 좌석 정보가 나온다.")
    @Test
    void deleteGradeTicketInfoTest() {
        // given
        EventLocationEntity eventLocationEntity = getEventLocationEntity(1L, "서울 월드컵 경기장");
        EventEntity eventEntity = getEventEntity(eventLocationEntity, "FC서울 VS 수원 삼성");
        SeatGradeEntity seatGradeEntity = getSeatGradeEntity(1L, eventLocationEntity);
        GradeTicketInfoEntity gradeTicketInfoEntity = gradeTicketInfoEntity(1L, eventEntity, seatGradeEntity);

        BDDMockito.given(gradeTicketInfoRepository.findById(any(Long.class)))
                .willReturn(Optional.of(gradeTicketInfoEntity));

        BDDMockito.willDoNothing().given(gradeTicketInfoRepository).delete(any(GradeTicketInfoEntity.class));

        // when
        GradeTicketInfoEntity result = gradeTicketInfoService.deleteGradeTicketInfo(gradeTicketInfoEntity.getGradeTicketInfoId());

        // then
        assertThat(result).extracting("numberTotalTicket", "numberOfRemainingTickets")
                .contains(gradeTicketInfoEntity.getNumberTotalTicket(), gradeTicketInfoEntity.getNumberOfRemainingTickets());
        assertThat(result.getEventEntity()).isEqualTo(eventEntity);
        assertThat(result.getEventLocationEntity()).isEqualTo(eventLocationEntity);
        assertThat(result.getSeatGradeEntity()).isEqualTo(seatGradeEntity);
    }
}