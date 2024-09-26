package github.ticketflow.domian.CategoryEvent;

import github.ticketflow.domian.category.CategoryEntity;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryEventRepository extends JpaRepository<CategoryEventEntity, Long> {

    Page<CategoryEventEntity> findByCategoryEntity(CategoryEntity categoryEntity, Pageable pageable);

}
