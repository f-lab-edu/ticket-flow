package github.ticketflow.domian.deletedEvent;

import github.ticketflow.domian.event.EventEntity;
import github.ticketflow.domian.eventLocation.EventLocationEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class DeletedEventServiceTest {

    @Mock
    private DeletedEventRepository deletedEventRepository;

    @InjectMocks
    private DeletedEventService deletedEventService;

    @DisplayName("이벤트 엔티티를 받아서 DeletedEvent 엔티티로 바꿔서 저장을 하면, DeletedEvent 정보가 나온다.")
    @Test
    void createDeletedEvent() {
        // given
        EventLocationEntity eventLocationEntity = new EventLocationEntity(1L, "서울 월듴컵 경기장", 50000);

        EventEntity eventEntity = EventEntity.builder()
                .eventId(1L)
                .eventLocation(eventLocationEntity)
                .eventName("FC 서울 vs 수원 삼성")
                .eventDescription("축구 경기")
                .date(LocalDate.of(2024, 10, 15))
                .startTime(LocalTime.of(20, 30))
                .build();

        DeletedEventEntity deletedEventEntity = new DeletedEventEntity(eventEntity);

        BDDMockito.given(deletedEventRepository.save(any(DeletedEventEntity.class)))
                .willReturn(deletedEventEntity);

        // when
        DeletedEventEntity result = deletedEventService.createDeletedEvent(eventEntity);

        // then
        assertThat(result).extracting("eventId", "eventName", "eventDescription", "date", "startTime")
                .contains(eventEntity.getEventId(), eventEntity.getEventName(), eventEntity.getEventDescription(), eventEntity.getDate(), eventEntity.getStartTime());
        assertThat(result.getEventLocation()).isEqualTo(eventLocationEntity);
    }

}