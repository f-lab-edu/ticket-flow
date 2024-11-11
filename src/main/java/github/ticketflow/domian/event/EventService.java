package github.ticketflow.domian.event;

import github.ticketflow.config.exception.GlobalCommonException;
import github.ticketflow.config.exception.event.EventErrorResponsive;
import github.ticketflow.domian.categoryEvent.CategoryEventEntity;
import github.ticketflow.domian.event.dto.EventRequestDTO;
import github.ticketflow.domian.event.dto.EventResponseDTO;
import github.ticketflow.domian.event.dto.EventUpdateRequestDTO;
import github.ticketflow.domian.eventLocation.EventLocationEntity;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public EventEntity getEventById(Long eventId) {
        return eventRepository.findById(eventId).orElseThrow(() ->
                new GlobalCommonException(EventErrorResponsive.NOT_FOUND_EVENT)
        );
    }

    @Cacheable(value = "events", key = "#categoryId")
    public List<EventResponseDTO> getEventByCategoryId(Page<CategoryEventEntity> categoryEventEntities) {
        List<EventResponseDTO> eventEntities = new ArrayList<>();

        categoryEventEntities.forEach(categoryEventEntity -> {
            eventEntities.add(new EventResponseDTO(categoryEventEntity.getEventEntity()));
        });

        return eventEntities;
    }

    public List<EventResponseDTO> getEventByEventName(String eventName) {
        List<EventResponseDTO> eventEntities = new ArrayList<>();
        eventRepository.findAllByEventNameContainingIgnoreCase(eventName).forEach(
                eventEntity -> eventEntities.add(new EventResponseDTO(eventEntity))
        );

        return eventEntities;
    }

    public EventEntity createEvent(EventRequestDTO dto,
                                   EventLocationEntity eventLocationEntity) {
        EventEntity newEventEntity = new EventEntity(dto, eventLocationEntity);
        return eventRepository.save(newEventEntity);
    }

    public EventEntity updateEvent(Long eventId,
                                   EventUpdateRequestDTO dto) {
        EventEntity eventEntity = getEventById(eventId);
        EventEntity updateEventEntity = eventEntity.update(dto);
        return eventRepository.save(updateEventEntity);
    }

    public EventEntity updateEvent(Long eventId,
                                   EventUpdateRequestDTO dto,
                                   EventLocationEntity eventLocationEntity) {
        EventEntity eventEntity = getEventById(eventId);
        EventEntity updateEventEntity = eventEntity.update(dto, eventLocationEntity);
        return eventRepository.save(updateEventEntity);

    }

    public EventEntity deletedEvent(Long eventId) {
        EventEntity eventEntity = getEventById(eventId);
        eventRepository.delete(eventEntity);

        return eventEntity;
    }
}