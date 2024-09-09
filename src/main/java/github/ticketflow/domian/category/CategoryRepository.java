package github.ticketflow.domian.category;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {

    List<CategoryEntity> findAllByCategoryLevel(int categoryLevel);

    List<CategoryEntity> findAllByParentCategoryId(Long parentId);

}
