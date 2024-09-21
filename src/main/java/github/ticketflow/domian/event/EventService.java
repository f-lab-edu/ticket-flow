package github.ticketflow.domian.event;

import github.ticketflow.config.exception.GlobalCommonException;
import github.ticketflow.config.exception.category.CategoryErrorResponsive;
import github.ticketflow.config.exception.event.EventErrorResponsive;
import github.ticketflow.config.exception.eventLocation.EventLocationErrorResponsive;
import github.ticketflow.domian.CategoryEvent.CategoryEventEntity;
import github.ticketflow.domian.CategoryEvent.CategoryEventRepository;
import github.ticketflow.domian.category.CategoryEntity;
import github.ticketflow.domian.category.CategoryRepository;
import github.ticketflow.domian.event.dto.EventRequestDTO;
import github.ticketflow.domian.event.dto.EventUpdateRequestDTO;
import github.ticketflow.domian.event.entity.DeletedEventEntity;
import github.ticketflow.domian.event.entity.EventEntity;
import github.ticketflow.domian.event.repository.DeletedEventRepository;
import github.ticketflow.domian.event.repository.EventRepository;
import github.ticketflow.domian.eventLocation.EventLocationEntity;
import github.ticketflow.domian.eventLocation.EventLocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final EventLocationRepository eventLocationRepository;
    private final DeletedEventRepository deletedEventRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryEventRepository categoryEventRepository;


    public EventEntity getEventById(Long eventId) {
        return getEventEntity(eventId);
    }

    @Transactional
    public List<EventEntity> getEventByCategoryId(Long categoryId, int pageNo) {
        List<EventEntity> eventEntities = new ArrayList<>();

        CategoryEntity categoryEntity = getCategoryEntity(categoryId);

        Pageable pageable = PageRequest.of(pageNo, 10);

        Page<CategoryEventEntity> categoryEventEntities = categoryEventRepository.findByCategoryEntity(categoryEntity, pageable);

        categoryEventEntities.forEach(categoryEventEntity -> {
            eventEntities.add(categoryEventEntity.getEventEntity());
        });

        return eventEntities;
    }

    @Transactional
    public EventEntity createEvent(EventRequestDTO dto) {
        EventLocationEntity eventLocationEntity = getEventLocationEntity(dto.getEventLocationId());
        CategoryEntity categoryEntity = getCategoryEntity(dto.getCategoryId());
        EventEntity newEventEntity = new EventEntity(dto, eventLocationEntity);

        CategoryEventEntity categoryEventEntity = new CategoryEventEntity(categoryEntity, newEventEntity);

        categoryEventRepository.save(categoryEventEntity);
        return eventRepository.save(newEventEntity);
    }


    @Transactional
    public EventEntity updateEvent(Long eventId ,EventUpdateRequestDTO dto) {
        EventLocationEntity eventLocationEntity = null;
        if(dto.getEventLocationId() != null) {
            eventLocationEntity = getEventLocationEntity(dto.getEventLocationId());
        }

        EventEntity eventEntity = getEventEntity(eventId);
        EventEntity updateEventEntity = eventEntity.update(dto, eventLocationEntity);

        return eventRepository.save(updateEventEntity);
    }

    @Transactional
    public EventEntity deletedEvent(Long eventId) {
        EventEntity eventEntity = getEventEntity(eventId);
        DeletedEventEntity deletedEventEntity = new DeletedEventEntity(eventEntity);

        deletedEventRepository.save(deletedEventEntity);
        eventRepository.delete(eventEntity);

        return eventEntity;
    }

    private EventEntity getEventEntity(Long eventId) {
        return eventRepository.findById(eventId).orElseThrow(() ->
                new GlobalCommonException(EventErrorResponsive.NOT_FOUND_EVENT)
        );
    }

    private EventLocationEntity getEventLocationEntity(Long eventLocationId) {
        return eventLocationRepository.findById(eventLocationId).orElseThrow(() ->
                new GlobalCommonException(EventLocationErrorResponsive.NOT_FOUND_EVENT_LOCATION)
        );
    }

    private CategoryEntity getCategoryEntity(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() ->
                new GlobalCommonException(CategoryErrorResponsive.NOT_FOUND_CATEGORY)
        );
    }
}
