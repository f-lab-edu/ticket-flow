package github.ticketflow.domian.categoryEvent;

import github.ticketflow.domian.category.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryEventRepository extends JpaRepository<CategoryEventEntity, Long> {

    Page<CategoryEventEntity> findByCategoryEntity(CategoryEntity categoryEntity, Pageable pageable);

}
