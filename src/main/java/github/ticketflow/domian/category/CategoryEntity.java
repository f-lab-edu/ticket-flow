package github.ticketflow.domian.category;

import github.ticketflow.domian.category.dto.CategoryRequestDTO;
import github.ticketflow.domian.category.dto.CategoryUpdateRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "Category")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "category_level")
    private int categoryLevel;

    @Column(name = "parent_category_id")
    private Long parentCategoryId;

    public CategoryEntity(String categoryName, int categoryLevel, Long parentCategoryId) {
        this.categoryName = categoryName;
        this.categoryLevel = categoryLevel;
        this.parentCategoryId = parentCategoryId;
    }

    public CategoryEntity(CategoryRequestDTO dto) {
        this.categoryName = dto.getCategoryName();
        this.categoryLevel = dto.getCategoryLevel();
        this.parentCategoryId = dto.getParentCategoryId();
    }

    public CategoryEntity update(CategoryUpdateRequestDTO dto) {
        if (dto.getCategoryName() != null) {
            this.categoryName = dto.getCategoryName();
        }

        if (dto.getCategoryLevel() != null) {
            this.categoryName = dto.getCategoryName();
        }

        if (dto.getParentCategoryId() != null) {
            this.parentCategoryId = dto.getParentCategoryId();
        }

        return this;
    }
}
