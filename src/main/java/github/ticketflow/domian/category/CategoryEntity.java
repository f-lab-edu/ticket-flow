package github.ticketflow.domian.category;

import github.ticketflow.domian.category.dto.CategoryRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "Category")
@NoArgsConstructor
@AllArgsConstructor
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

    public CategoryEntity(CategoryRequestDTO dto) {
        this.categoryName = dto.getCategoryName();
        this.categoryLevel = dto.getCategoryLevel();
        this.parentCategoryId = dto.getParentCategoryId();
    }
}
