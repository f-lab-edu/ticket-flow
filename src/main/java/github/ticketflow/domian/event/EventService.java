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

    private final EventRepositoryLayer eventRepositoryLayer;

    private static final int PAGE_SIZE = 10;

    public EventEntity getEventById(Long eventId) {
        return eventRepositoryLayer.getEventById(eventId);
    }

    @Transactional
    public List<EventEntity> getEventByCategoryId(Long categoryId, int pageNo) {
        List<EventEntity> eventEntities = new ArrayList<>();

        CategoryEntity categoryEntity = eventRepositoryLayer.getCategoryEntity(categoryId);
        Pageable pageable = PageRequest.of(pageNo, PAGE_SIZE);

        Page<CategoryEventEntity> categoryEventEntities = eventRepositoryLayer.getCategoryEventEntity(categoryEntity, pageable);

        categoryEventEntities.forEach(categoryEventEntity -> {
            eventEntities.add(categoryEventEntity.getEventEntity());
        });

        return eventEntities;
    }

    @Transactional
    public EventEntity createEvent(EventRequestDTO dto) {
        EventLocationEntity eventLocationEntity = eventRepositoryLayer.getEventLocationEntity(dto.getEventLocationId());
        CategoryEntity categoryEntity = eventRepositoryLayer.getCategoryEntity(dto.getCategoryId());
        EventEntity newEventEntity = new EventEntity(dto, eventLocationEntity);

        CategoryEventEntity categoryEventEntity = new CategoryEventEntity(categoryEntity, newEventEntity);

        eventRepositoryLayer.saveCategoryEvent(categoryEventEntity);
        return eventRepositoryLayer.saveEvent(newEventEntity);
    }


    @Transactional
    public EventEntity updateEvent(Long eventId, EventUpdateRequestDTO dto) {
        EventEntity eventEntity = getEventById(eventId);

        if(dto.getEventLocationId() != null) {
            EventLocationEntity eventLocationEntity = eventRepositoryLayer.getEventLocationEntity(dto.getEventLocationId());
            EventEntity updateEventEntity = eventEntity.update(dto, eventLocationEntity);
            return eventRepositoryLayer.saveEvent(updateEventEntity);
        }

        EventEntity updateEventEntity = eventEntity.update(dto);
        return eventRepositoryLayer.saveEvent(updateEventEntity);
    }

    @Transactional
    public EventEntity deletedEvent(Long eventId) {
        EventEntity eventEntity = getEventById(eventId);
        DeletedEventEntity deletedEventEntity = new DeletedEventEntity(eventEntity);

        eventRepositoryLayer.saveDeletedEvent(deletedEventEntity);
        eventRepositoryLayer.deletedEvent(eventEntity);

        return eventEntity;
    }
}