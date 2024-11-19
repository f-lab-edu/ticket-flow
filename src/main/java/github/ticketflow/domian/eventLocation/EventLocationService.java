package github.ticketflow.domian.eventLocation;

import github.ticketflow.config.exception.GlobalCommonException;
import github.ticketflow.config.exception.eventLocation.EventLocationErrorResponsive;
import github.ticketflow.domian.event.EventEntity;
import github.ticketflow.domian.eventLocation.dto.EventLocationRequestDTO;
import github.ticketflow.domian.eventLocation.dto.EventLocationResponseDTO;
import github.ticketflow.domian.eventLocation.dto.EventLocationUpdateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventLocationService {

    private final EventLocationRepository eventLocationRepository;

    public EventLocationEntity getEventLocation(Long eventLocationId) {
       return eventLocationRepository.findById(eventLocationId).orElseThrow(
               () -> new GlobalCommonException(EventLocationErrorResponsive.NOT_FOUND_EVENT_LOCATION)
       );
    }

    public EventLocationEntity createEventLocation(EventLocationRequestDTO dto) {
       return eventLocationRepository.save(new EventLocationEntity(dto));
    }

    @Transactional
    public EventLocationEntity updateEventLocation(Long eventLocationId, EventLocationUpdateRequestDTO dto) {
        EventLocationEntity eventLocationEntity = getEventLocation(eventLocationId);

        EventLocationEntity updateEventLocationEntity = eventLocationEntity.update(dto);
        return eventLocationRepository.save(updateEventLocationEntity);
    }

    @Transactional
    public EventLocationEntity deletedEventLocation(Long eventLocationId) {
        EventLocationEntity eventLocationEntity = getEventLocation(eventLocationId);
        eventLocationRepository.delete(eventLocationEntity);
        return eventLocationEntity;
    }
}
