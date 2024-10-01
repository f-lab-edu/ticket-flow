package github.ticketflow.domian.event;

import github.ticketflow.domian.CategoryEvent.CategoryEventEntity;
import github.ticketflow.domian.CategoryEvent.CategoryEventService;
import github.ticketflow.domian.category.CategoryEntity;
import github.ticketflow.domian.category.CategoryService;
import github.ticketflow.domian.category.dto.CategoryResponseDTO;
import github.ticketflow.domian.deletedEvent.DeletedEventService;
import github.ticketflow.domian.event.dto.EventRequestDTO;
import github.ticketflow.domian.event.dto.EventResponseDTO;
import github.ticketflow.domian.event.dto.EventUpdateRequestDTO;
import github.ticketflow.domian.eventLocation.EventLocationEntity;
import github.ticketflow.domian.eventLocation.EventLocationRepository;
import github.ticketflow.domian.eventLocation.EventLocationService;
import github.ticketflow.domian.eventLocation.dto.EventLocationResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EventFacade {

    private final EventService eventService;
    private final CategoryService categoryService;
    private final EventLocationService eventLocationService;
    private final CategoryEventService categoryEventService;
    private final DeletedEventService deletedEventService;

    EventEntity getEventById(Long eventId) {
        return  eventService.getEventById(eventId);
    }

    @Transactional
    List<EventResponseDTO> getEventByCategoryId (Long categoryId, int page) {
        CategoryResponseDTO categoryResponseDTO =  categoryService.getCategory(categoryId);
        CategoryEntity categoryEntity = new CategoryEntity(categoryResponseDTO);

        Page<CategoryEventEntity> categoryEventEntities = categoryEventService.getCategoryEventsByCategory(categoryEntity, page);
        return eventService.getEventByCategoryId(categoryEventEntities);
    }

    @Transactional
    EventEntity createEvent(EventRequestDTO dto) {
        EventLocationResponseDTO eventLocationResponseDTO = eventLocationService.getEventLocation(dto.getEventLocationId());
        EventLocationEntity eventLocationEntity = new EventLocationEntity(eventLocationResponseDTO);

        CategoryResponseDTO categoryResponseDTO =  categoryService.getCategory(dto.getCategoryId());
        CategoryEntity categoryEntity = new CategoryEntity(categoryResponseDTO);

        EventEntity eventEntity = eventService.createEvent(dto, eventLocationEntity);

        categoryEventService.createCategoryEvent(categoryEntity, eventEntity);
        return eventEntity;
    }

    @Transactional
    EventEntity updateEvent(Long eventId, EventUpdateRequestDTO dto) {
        if (dto.getEventLocationId() == null) {
            return eventService.updateEvent(eventId, dto);
        }

        EventLocationResponseDTO eventLocationResponseDTO = eventLocationService.getEventLocation(dto.getEventLocationId());
        EventLocationEntity eventLocationEntity = new EventLocationEntity(eventLocationResponseDTO);

        return eventService.updateEvent(eventId, dto, eventLocationEntity);
    }

    @Transactional
    EventEntity deletedEvent(Long eventId) {
        EventEntity eventEntity = eventService.deletedEvent(eventId);
        deletedEventService.createDeletedEvent(eventEntity);

        return eventEntity;
    }

}
