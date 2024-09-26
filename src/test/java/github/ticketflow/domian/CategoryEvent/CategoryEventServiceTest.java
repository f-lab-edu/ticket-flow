package github.ticketflow.domian.CategoryEvent;

import github.ticketflow.domian.category.CategoryEntity;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CategoryEventServiceTest {

    @Mock
    private CategoryEventRepository categoryEventRepository;

    @InjectMocks
    private CategoryEventService categoryEventService;

    private static final int PAGE_SIZE = 10;

    @DisplayName("카테고리의 엔티티로 CategoryEventEntity를 찾으면 정보가 나온다.")
    @Test
    void getCategoryEventsByCategory() {
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

        Pageable pageable = PageRequest.of(0, PAGE_SIZE);

        BDDMockito.given(categoryEventRepository.findByCategoryEntity(categoryEntity, pageable))
                .willReturn(categoryEventEntityPage);

        // when
        Page<CategoryEventEntity> result = categoryEventRepository.findByCategoryEntity(categoryEntity, pageable);

        // then
        assertThat(result.getContent()).hasSize(3);  // Page의 사이즈가 3이어야 함
        assertThat(result.getContent())
                .extracting("eventEntity.eventName")
                .containsExactly("FC서울 vs 대전하나시티즌", "수원 삼성 vs 전남 드래곤즈", "울산현대 vs 전북현대");

        assertThat(result.getContent())
                .extracting("eventEntity.eventLocation.eventLocationName")
                .containsExactly("서울 월드컵 경기징", "수원 빅버드 경기징", "울산 문수 경기징");

    }

    @DisplayName("이벤트를 생성을 했을 때, 카테고리와 맵핑이 되기 위해서 CategoryEvent를 저장하면 CategoryEvent 정보가 나온다.")
    @Test
    void createCategoryEventTest() {
        // given
        CategoryEntity categoryEntity = CategoryEntity.builder()
                .categoryId(1L)
                .categoryName("스포츠")
                .categoryLevel(1)
                .build();

        EventLocationEntity eventLocationEntity = new EventLocationEntity(1L, "서울 월듴컵 경기장", 50000);

        EventEntity eventEntity = EventEntity.builder()
                .eventId(1L)
                .eventLocation(eventLocationEntity)
                .eventName("FC 서울 vs 수원 삼성")
                .eventDescription("축구 경기")
                .date(LocalDate.of(2024, 10, 15))
                .startTime(LocalTime.of(20, 30))
                .build();

        CategoryEventEntity categoryEventEntity = CategoryEventEntity.builder()
                .categoryEventId(1L)
                .categoryEntity(categoryEntity)
                .eventEntity(eventEntity)
                .build();

        BDDMockito.given(categoryEventRepository.save(BDDMockito.any())).willReturn(categoryEventEntity);

        // when
        CategoryEventEntity result = categoryEventService.createCategoryEvent(categoryEntity, eventEntity);

        // then
        assertThat(result.getCategoryEntity()).extracting("categoryName", "categoryLevel")
                .contains(categoryEntity.getCategoryName(), categoryEntity.getCategoryLevel());
        assertThat(result.getEventEntity()).extracting("eventName", "eventDescription", "date", "startTime")
                .contains(eventEntity.getEventName(), eventEntity.getEventDescription(), eventEntity.getDate(), eventEntity.getStartTime());
    }

    private static EventEntity getEventEntity(EventLocationEntity eventLocationEntity, String eventName) {
        return EventEntity.builder()
                .eventId(1L)
                .eventLocation(eventLocationEntity)
                .eventName(eventName)
                .eventDescription("축구 경기")
                .date(LocalDate.of(2024, 10, 15))
                .startTime(LocalTime.of(20, 30))
                .build();
    }

    private static EventLocationEntity getEventLocationEntity(Long eventLocationId, String eventLocationName) {
        EventLocationEntity eventLocationEntity = new EventLocationEntity(eventLocationId, eventLocationName, 50000);
        return eventLocationEntity;
    }

}