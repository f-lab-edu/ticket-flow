package github.ticketflow.domian.event;

import github.ticketflow.domian.CommonTestFixture;
import github.ticketflow.domian.categoryEvent.CategoryEventEntity;
import github.ticketflow.domian.category.CategoryEntity;
import github.ticketflow.domian.event.dto.EventRequestDTO;
import github.ticketflow.domian.event.dto.EventResponseDTO;
import github.ticketflow.domian.event.dto.EventUpdateRequestDTO;
import github.ticketflow.domian.eventLocation.EventLocationEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static github.ticketflow.domian.CommonTestFixture.*;
import static github.ticketflow.domian.CommonTestFixture.getEventEntity;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService eventService;

    @DisplayName("이벤트 id를 통해서 이벤트를 가지고 오면, 이벤트의 정보가 나온다.")
    @Test
    void getEventByIdTest() {
        // given
        EventLocationEntity eventLocationEntity = getEventLocationEntity(1L, "서울월드컵 경기장");
        EventEntity eventEntity = getEventEntity(eventLocationEntity, "대한민축 vs 일본 축구 친선경기");

        BDDMockito.given(eventRepository.findById(any(Long.class)))
                .willReturn(Optional.of(eventEntity));

        // when
        EventEntity result = eventService.getEventById(any(Long.class));

        // then
        assertThat(result).extracting("eventName", "eventDescription", "date", "startTime")
                .contains(eventEntity.getEventName(), eventEntity.getEventDescription(), eventEntity.getDate(), eventEntity.getStartTime());
        assertThat(result).extracting("EventLocationEntity")
                .isEqualTo(eventLocationEntity);
    }

    @DisplayName("카테고리 id로 이벤트 목록을 가지고 오면, 리스트에 정보가 담긴다.")
    @Test
    void getEventByCategory() {
        // given
        CategoryEntity categoryEntity = CategoryEntity.builder()
                .categoryId(1L)
                .categoryName("스포츠")
                .categoryLevel(1)
                .build();

        EventLocationEntity eventLocationEntitySeoul = getEventLocationEntity(1L, "서울 월드컵 경기징");
        EventLocationEntity eventLocationEntitySuwon = getEventLocationEntity(1L, "수원 빅버드 경기징");
        EventLocationEntity eventLocationEntityUlsan = getEventLocationEntity(1L, "울산 문수 경기징");

        EventEntity eventEntitySeoul = getEventEntity(eventLocationEntitySeoul, "FC서울 vs 대전하나시티즌");
        EventEntity eventEntitySuwon = getEventEntity(eventLocationEntitySuwon, "수원 삼성 vs 전남 드래곤즈");
        EventEntity eventEntityUlsan = getEventEntity(eventLocationEntityUlsan, "울산현대 vs 전북현대");

        CategoryEventEntity categoryEventEntitySeoul = CategoryEventEntity.builder()
                .categoryEventId(1L)
                .categoryEntity(categoryEntity)
                .eventEntity(eventEntitySeoul)
                .build();

        CategoryEventEntity categoryEventEntitySuwon = CategoryEventEntity.builder()
                .categoryEventId(2L)
                .categoryEntity(categoryEntity)
                .eventEntity(eventEntitySuwon)
                .build();

        CategoryEventEntity categoryEventEntityUlsan = CategoryEventEntity.builder()
                .categoryEventId(3L)
                .categoryEntity(categoryEntity)
                .eventEntity(eventEntityUlsan)
                .build();

        List<CategoryEventEntity> categoryEventEntities = List.of(categoryEventEntitySeoul, categoryEventEntitySuwon, categoryEventEntityUlsan);
        Page<CategoryEventEntity> categoryEventEntityPage = new PageImpl<>(categoryEventEntities);


        // when
        List<EventResponseDTO> result = eventService.getEventByCategoryId(categoryEventEntityPage);

        // then
        assertThat(result).extracting("eventName", "eventDescription", "date", "startTime")
                .containsAnyOf(
                        tuple(eventEntitySeoul.getEventName(), eventEntitySeoul.getEventDescription(), eventEntitySeoul.getDate(), eventEntitySeoul.getStartTime()),
                        tuple(eventEntitySuwon.getEventName(), eventEntitySuwon.getEventDescription(), eventEntitySuwon.getDate(), eventEntitySuwon.getStartTime()),
                        tuple(eventEntityUlsan.getEventName(), eventEntityUlsan.getEventDescription(), eventEntityUlsan.getDate(), eventEntityUlsan.getStartTime())
                );

    }

    @DisplayName("이벤트를 생성하면, 생성이 된 이벤트의 정보가 나온다.")
    @Test
    void createEventTest() {
        // given
        EventRequestDTO dto = EventRequestDTO.builder()
                .eventLocationId(1L)
                .categoryId(1L)
                .eventName("대한민축 vs 일본 축구 친선경기")
                .eventDescription("축구 경가")
                .date(LocalDate.of(2024, 10, 15))
                .startTime(LocalTime.of(8, 0))
                .build();

        EventLocationEntity eventLocationEntity = getEventLocationEntity(1L, "서울 월드컵 경기장");
        EventEntity eventEntity = getEventEntity(eventLocationEntity, dto.getEventName());

        BDDMockito.given(eventRepository.save(any(EventEntity.class)))
                .willReturn(eventEntity);

        // when
        EventEntity result = eventService.createEvent(dto, eventLocationEntity);

        // then
        assertThat(result).extracting("eventName", "eventDescription", "date", "startTime")
                .contains(eventEntity.getEventName(), eventEntity.getEventDescription(), eventEntity.getDate(),eventEntity.getStartTime());
        assertThat(result).extracting("eventLocationEntity")
                .isEqualTo(eventLocationEntity);
    }

    @DisplayName("수정하고 싶은 이벤트 정보를 수정하면, 수정된 정보가 나온다.")
    @Test
    void updateEventTest() {
        // given
        EventLocationEntity eventLocationEntitySeoul = getEventLocationEntity(1L, "서울 월드컵 경기장");
        EventLocationEntity eventLocationEntitySuwon = getEventLocationEntity(2L, "수원 빅버드 경기장");

        EventEntity eventEntity = getEventEntity(eventLocationEntitySeoul, "FC서울 vs 수원삼섬");

        EventUpdateRequestDTO dto = EventUpdateRequestDTO.builder()
                .eventLocationId(2L)
                .eventName("수원 삼성 vs FC서울")
                .build();

        eventEntity.update(dto, eventLocationEntitySuwon);

        BDDMockito.given(eventRepository.findById(any(Long.class)))
                .willReturn(Optional.of(eventEntity));

        BDDMockito.given(eventRepository.save(any(EventEntity.class)))
                .willReturn(eventEntity);

        // when
        EventEntity result = eventService.updateEvent(1L, dto);

        // then
        assertThat(result).extracting("eventName", "eventDescription", "date", "startTime")
                .contains(eventEntity.getEventName(), eventEntity.getEventDescription(), eventEntity.getDate(), eventEntity.getStartTime());
    }

    @DisplayName("삭제하고 싶은 이벤트를 삭제하고, 삭제된 이벤트 정보를 반환읋 한다.")
    @Test
    void deleteEventTest() {
        // given
        EventLocationEntity eventLocationEntitySeoul = getEventLocationEntity(1L, "서울 월드컵 경기장");
        EventEntity eventEntity = getEventEntity(eventLocationEntitySeoul, "FC서울 vs 수원삼섬");

        BDDMockito.given(eventRepository.findById(any(Long.class)))
                .willReturn(Optional.of(eventEntity));
        BDDMockito.willDoNothing().given(eventRepository).delete(eventEntity);

        // when
        EventEntity result = eventService.deletedEvent(any(Long.class));

        // then
        assertThat(result).extracting("eventName", "eventDescription", "date", "startTime")
                .contains(eventEntity.getEventName(), eventEntity.getEventDescription(), eventEntity.getDate(), eventEntity.getStartTime());

    }
}