package github.ticketflow.domian.eventLocation;

import github.ticketflow.config.exception.GlobalCommonException;
import github.ticketflow.config.exception.eventLocation.EventLocationErrorResponsive;
import github.ticketflow.domian.eventLocation.dto.EventLocationRequestDTO;
import github.ticketflow.domian.eventLocation.dto.EventLocationResponseDTO;
import github.ticketflow.domian.eventLocation.dto.EventLocationUpdateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventLocationService {

    private final EventLocationRepository eventLocationRepository;

    public EventLocationResponseDTO getEventLocation(Long eventLocationId) {
        EventLocationEntity eventLocationEntity = getEventLocationEntityById(eventLocationId);
        return new EventLocationResponseDTO(eventLocationEntity);
    }

    public EventLocationResponseDTO createEventLocation(EventLocationRequestDTO dto) {
        EventLocationEntity saveEventLocationEntity = eventLocationRepository.save(new EventLocationEntity(dto));
        return new EventLocationResponseDTO(saveEventLocationEntity);
    }


    public EventLocationResponseDTO updateEventLocation(Long eventLocationId, EventLocationUpdateRequestDTO dto) {
        EventLocationEntity eventLocationEntity = getEventLocationEntityById(eventLocationId);

        EventLocationEntity updateEventLocationEntity = eventLocationEntity.update(dto);
        EventLocationEntity saveEventLocationEntity = eventLocationRepository.save(updateEventLocationEntity);

        return new EventLocationResponseDTO(saveEventLocationEntity);
    }


    public EventLocationResponseDTO deletedEventLocation(Long eventLocationId) {
        EventLocationEntity eventLocationEntity = getEventLocationEntityById(eventLocationId);
        eventLocationRepository.delete(eventLocationEntity);
        return new EventLocationResponseDTO(eventLocationEntity);
    }

    private EventLocationEntity getEventLocationEntityById(Long eventLocationId) {
        EventLocationEntity eventLocationEntity = eventLocationRepository.findById(eventLocationId).orElseThrow(
                () -> new GlobalCommonException(EventLocationErrorResponsive.NOT_FOUND_EVENT_LOCATION)
        );
        return eventLocationEntity;
    }
}
