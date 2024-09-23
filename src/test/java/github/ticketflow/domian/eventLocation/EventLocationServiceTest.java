package github.ticketflow.domian.eventLocation;

import github.ticketflow.domian.eventLocation.dto.EventLocationRequestDTO;
import github.ticketflow.domian.eventLocation.dto.EventLocationResponseDTO;
import github.ticketflow.domian.eventLocation.dto.EventLocationUpdateRequestDTO;
import jakarta.persistence.Id;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EventLocationServiceTest {

    @Mock
    private EventLocationRepository eventLocationRepository;

    @InjectMocks
    private EventLocationService eventLocationService;

    @DisplayName("eventLocationId로 eventLocation을 가지고 오면, 정보가 나온다.")
    @Test
    void getEventLocationByIdTest() {
        // given
        EventLocationEntity eventLocationEntity = new EventLocationEntity(1L, "서울 월드컵 경기장", 50000);
        BDDMockito.given(eventLocationRepository.findById(eventLocationEntity.getEventLocationId()))
                .willReturn(Optional.of(eventLocationEntity));

        // when
        EventLocationResponseDTO result = eventLocationService.getEventLocation(eventLocationEntity.getEventLocationId());

        // then
        assertThat(result).extracting("eventLocationId", "eventLocationName", "totalSeats")
                .containsExactly(eventLocationEntity.getEventLocationId(), eventLocationEntity.getEventLocationName(), 50000);
    }

    @DisplayName("eventLocationRequestDTO로 eventLocation을 생성하면, 생성된 eventLocation의 정보가 나온다.")
    @Test
    void createEventLocationTest() {
        // given
        EventLocationRequestDTO dto = new EventLocationRequestDTO("서울 월드컵 경기장", 50000);
        EventLocationEntity eventLocationEntity = new EventLocationEntity(dto);
        BDDMockito.given(eventLocationRepository.save(any(EventLocationEntity.class)))
                .willReturn(eventLocationEntity);


        // when
        EventLocationResponseDTO result = eventLocationService.createEventLocation(dto);

        // then
        assertThat(result).extracting("eventLocationName", "totalSeats")
                .containsExactly(eventLocationEntity.getEventLocationName(), 50000);
    }

    @DisplayName("eventLocationId로 eventLocation을 가지고 와서 수정을 하면, 수정된 정보가 나온다.")
    @Test
    void updateEventLocationTest() {
        // given
        EventLocationEntity eventLocationEntity = new EventLocationEntity(1L, "서울 월드컵 경기장", 50000);
        EventLocationUpdateRequestDTO dto = new EventLocationUpdateRequestDTO("빅버드 스타디움", 40000);
        EventLocationEntity updateEventLocationEntity = eventLocationEntity.update(dto);

        BDDMockito.given(eventLocationRepository
                .findById(updateEventLocationEntity.getEventLocationId()))
                .willReturn(Optional.of(updateEventLocationEntity));

        BDDMockito.given(eventLocationRepository
                .save(any(EventLocationEntity.class)))
                .willReturn(updateEventLocationEntity);

        // when
        EventLocationResponseDTO result = eventLocationService.updateEventLocation(eventLocationEntity.getEventLocationId(), dto);

        // then
        assertThat(result).extracting("eventLocationId", "eventLocationName", "totalSeats")
                .containsExactly(updateEventLocationEntity.getEventLocationId(), updateEventLocationEntity.getEventLocationName(), 40000);
    }

    @DisplayName("eventLocationId로 eventLocation을 가지고 와서 삭제를 하면, 삭제된 정보가 나온다.")
    @Test
    void deleteEventLocationTest() {
        // given
        EventLocationEntity eventLocationEntity = new EventLocationEntity(1L, "서울 월드컵 경기장", 50000);
        BDDMockito.given(eventLocationRepository.findById(eventLocationEntity.getEventLocationId()))
                .willReturn(Optional.of(eventLocationEntity));
        BDDMockito.willDoNothing().given(eventLocationRepository).delete(eventLocationEntity);

        // when
        EventLocationResponseDTO result = eventLocationService.deletedEventLocation(eventLocationEntity.getEventLocationId());

        // then
        assertThat(result).extracting("eventLocationId", "eventLocationName", "totalSeats")
                .containsExactly(eventLocationEntity.getEventLocationId(), eventLocationEntity.getEventLocationName(), 50000);
    }

}