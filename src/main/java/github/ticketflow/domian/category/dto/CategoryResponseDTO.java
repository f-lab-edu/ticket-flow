package github.ticketflow.domian.category.dto;

import github.ticketflow.domian.category.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDTO {

    private Long categoryId;
    private String categoryName;
    private int categoryLevel;
    private Long parentCategoryId;

    public CategoryResponseDTO(CategoryEntity categoryEntity) {
        this.categoryId = categoryEntity.getCategoryId();
        this.categoryName = categoryEntity.getCategoryName();
        this.categoryLevel = categoryEntity.getCategoryLevel();
        this.parentCategoryId = categoryEntity.getParentCategoryId();
    }
}
