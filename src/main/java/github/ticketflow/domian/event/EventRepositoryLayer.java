package github.ticketflow.domian.event;

import github.ticketflow.config.exception.GlobalCommonException;
import github.ticketflow.config.exception.category.CategoryErrorResponsive;
import github.ticketflow.config.exception.event.EventErrorResponsive;
import github.ticketflow.config.exception.eventLocation.EventLocationErrorResponsive;
import github.ticketflow.domian.CategoryEvent.CategoryEventEntity;
import github.ticketflow.domian.CategoryEvent.CategoryEventRepository;
import github.ticketflow.domian.category.CategoryEntity;
import github.ticketflow.domian.category.CategoryRepository;
import github.ticketflow.domian.event.entity.DeletedEventEntity;
import github.ticketflow.domian.event.entity.EventEntity;
import github.ticketflow.domian.event.repository.DeletedEventRepository;
import github.ticketflow.domian.event.repository.EventRepository;
import github.ticketflow.domian.eventLocation.EventLocationEntity;
import github.ticketflow.domian.eventLocation.EventLocationRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Getter
@Component
@RequiredArgsConstructor
public class EventRepositoryLayer {

    private final EventRepository eventRepository;
    private final EventLocationRepository eventLocationRepository;
    private final DeletedEventRepository deletedEventRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryEventRepository categoryEventRepository;

    EventEntity getEventById(Long eventId) {
        return eventRepository.findById(eventId).orElseThrow(() ->
                new GlobalCommonException(EventErrorResponsive.NOT_FOUND_EVENT)
        );
    }

    CategoryEntity getCategoryEntity(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() ->
                new GlobalCommonException(CategoryErrorResponsive.NOT_FOUND_CATEGORY)
        );
    }

    Page<CategoryEventEntity> getCategoryEventEntity(CategoryEntity categoryEntity, Pageable pageable) {
        return categoryEventRepository.findByCategoryEntity(categoryEntity, pageable);
    }

    EventLocationEntity getEventLocationEntity(Long eventLocationId) {
        return eventLocationRepository.findById(eventLocationId).orElseThrow(() ->
                new GlobalCommonException(EventLocationErrorResponsive.NOT_FOUND_EVENT_LOCATION)
        );
    }

    CategoryEventEntity saveCategoryEvent(CategoryEventEntity categoryEventEntity) {
        return categoryEventRepository.save(categoryEventEntity);
    }

    EventEntity saveEvent(EventEntity eventEntity) {
        return eventRepository.save(eventEntity);
    }

    DeletedEventEntity saveDeletedEvent(DeletedEventEntity deletedEventEntity) {
        return deletedEventRepository.save(deletedEventEntity);
    }

    void deletedEvent(EventEntity eventEntity) {
        eventRepository.delete(eventEntity);
    }

}
