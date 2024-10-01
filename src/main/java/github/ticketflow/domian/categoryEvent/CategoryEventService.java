package github.ticketflow.domian.categoryEvent;

import github.ticketflow.domian.category.CategoryEntity;
import github.ticketflow.domian.event.EventEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryEventService {

    private final CategoryEventRepository categoryEventRepository;

    private static final int PAGE_SIZE = 10;

    public Page<CategoryEventEntity> getCategoryEventsByCategory(CategoryEntity categoryEntity, int pageNo) {
        Pageable pageable = PageRequest.of(pageNo, PAGE_SIZE);

        return categoryEventRepository.findByCategoryEntity(categoryEntity, pageable);
    }

    public CategoryEventEntity createCategoryEvent(CategoryEntity categoryEntity, EventEntity eventEntity) {
        CategoryEventEntity categoryEventEntity = new CategoryEventEntity(categoryEntity, eventEntity);
        return categoryEventRepository.save(categoryEventEntity);
    }

}
