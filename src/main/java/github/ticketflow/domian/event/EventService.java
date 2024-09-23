package github.ticketflow.domian.event;

import github.ticketflow.config.exception.GlobalCommonException;
import github.ticketflow.config.exception.event.EventErrorResponsive;
import github.ticketflow.domian.CategoryEvent.CategoryEventEntity;
import github.ticketflow.domian.category.CategoryEntity;
import github.ticketflow.domian.event.dto.EventRequestDTO;
import github.ticketflow.domian.event.dto.EventResponseDTO;
import github.ticketflow.domian.event.dto.EventUpdateRequestDTO;
import github.ticketflow.domian.eventLocation.EventLocationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public EventResponseDTO getEventById(Long eventId) {
         EventEntity eventEntity = getEventEntity(eventId);

        return new EventResponseDTO(eventEntity);
    }

    public List<EventResponseDTO> getEventByCategoryId(Page<CategoryEventEntity> categoryEventEntities) {
        List<EventResponseDTO> eventEntities = new ArrayList<>();

        categoryEventEntities.forEach(categoryEventEntity -> {
            eventEntities.add(new EventResponseDTO(categoryEventEntity.getEventEntity()));
        });

        return eventEntities;
    }

    public EventResponseDTO createEvent(EventRequestDTO dto,
                                   EventLocationEntity eventLocationEntity) {
        EventEntity newEventEntity = new EventEntity(dto, eventLocationEntity);
        EventEntity saveEventEntity = eventRepository.save(newEventEntity);
        return  new EventResponseDTO(saveEventEntity);
    }

    public EventResponseDTO updateEvent(Long eventId,
                                   EventUpdateRequestDTO dto) {
        EventEntity eventEntity = getEventEntity(eventId);
        EventEntity updateEventEntity = eventEntity.update(dto);
        return new EventResponseDTO(eventRepository.save(updateEventEntity));
    }

    public EventResponseDTO updateEvent(Long eventId,
                                   EventUpdateRequestDTO dto,
                                   EventLocationEntity eventLocationEntity) {
        EventEntity eventEntity = getEventEntity(eventId);
        EventEntity updateEventEntity = eventEntity.update(dto, eventLocationEntity);
        return new EventResponseDTO(eventRepository.save(updateEventEntity));

    }

    public EventResponseDTO deletedEvent(Long eventId) {
        EventEntity eventEntity = getEventEntity(eventId);
        eventRepository.delete(eventEntity);

        return new EventResponseDTO(eventEntity);
    }

    private EventEntity getEventEntity(Long eventId) {
        return eventRepository.findById(eventId).orElseThrow(() ->
                new GlobalCommonException(EventErrorResponsive.NOT_FOUND_EVENT)
        );
    }
}