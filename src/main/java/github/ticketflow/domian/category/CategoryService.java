package github.ticketflow.domian.category;

import github.ticketflow.config.exception.GlobalCommonException;
import github.ticketflow.config.exception.category.CategoryErrorResponsive;
import github.ticketflow.domian.category.dto.CategoryRequestDTO;
import github.ticketflow.domian.category.dto.CategoryResponseDTO;
import github.ticketflow.domian.category.dto.CategoryUpdateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryResponseDTO> getCategoryByCategoryLevel(int categoryLevel) {
        List<CategoryResponseDTO> categories = new ArrayList<>();
        categoryRepository.findAllByCategoryLevel(categoryLevel).forEach(categoryEntity -> {
            categories.add(new CategoryResponseDTO(categoryEntity));
        });
        return categories;
    }

    public List<CategoryResponseDTO> getCategoryByParentCategoryId(Long parentCategoryId) {
        List<CategoryResponseDTO> categories = new ArrayList<>();
        categoryRepository.findAllByParentCategoryId(parentCategoryId).forEach(categoryEntity -> {
            categories.add(new CategoryResponseDTO(categoryEntity));
        });
        return categories;
    }

    public CategoryEntity getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(
                () -> new GlobalCommonException(CategoryErrorResponsive.NOT_FOUND_CATEGORY)
        );
    }

    public CategoryEntity createCategory(CategoryRequestDTO dto) {
        CategoryEntity categoryEntity = new CategoryEntity(dto);
        return categoryRepository.save(categoryEntity);
    }

    public CategoryEntity updateCategory(Long categoryId, CategoryUpdateRequestDTO dto) {
        CategoryEntity categoryEntity = getCategory(categoryId);
        categoryEntity.update(dto);
        return categoryRepository.save(categoryEntity);
    }

    public CategoryEntity deletedCategory(Long categoryId) {
        CategoryEntity categoryEntity = getCategory(categoryId);
        categoryRepository.deleteById(categoryId);
        return categoryEntity;
    }
}
