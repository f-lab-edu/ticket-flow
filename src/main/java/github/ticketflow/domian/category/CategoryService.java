package github.ticketflow.domian.category;

import github.ticketflow.config.exception.GlobalCommonException;
import github.ticketflow.config.exception.category.CategoryErrorCode;
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

    public List<CategoryResponseDTO> getAllCategory() {
        List<CategoryResponseDTO> categories = new ArrayList<>();
        categoryRepository.findAll().forEach((categoryEntity) -> {
            CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO(categoryEntity);
            categories.add(categoryResponseDTO);
        });
        return categories;
    }

    public CategoryResponseDTO getCategory(Long categoryId) {
        CategoryEntity categoryEntity = categoryRepository.findById(categoryId).orElseThrow(
                () -> new GlobalCommonException(CategoryErrorCode.NOT_FOUND_CATEGORY)
        );

        return new CategoryResponseDTO(categoryEntity);
    }

    public CategoryResponseDTO createCategory(CategoryRequestDTO dto) {
        CategoryEntity categoryEntity = new CategoryEntity(dto);
        CategoryEntity saveCategoryEntity = categoryRepository.save(categoryEntity);
        return new CategoryResponseDTO(saveCategoryEntity);
    }

    public CategoryResponseDTO updateCategory(Long categoryId, CategoryUpdateRequestDTO dto) {
        CategoryEntity categoryEntity = categoryRepository.findById(categoryId).orElseThrow(
                () -> new GlobalCommonException(CategoryErrorCode.NOT_FOUND_CATEGORY)
        );

        categoryEntity.update(dto);
        CategoryEntity updateCategoryEntity = categoryRepository.save(categoryEntity);

        return new CategoryResponseDTO(updateCategoryEntity);
    }

    public CategoryResponseDTO deletedCategory(Long categoryId) {
        CategoryEntity categoryEntity = categoryRepository.findById(categoryId).orElseThrow(
                () -> new GlobalCommonException(CategoryErrorCode.NOT_FOUND_CATEGORY)
        );
        categoryRepository.deleteById(categoryId);
        return new CategoryResponseDTO(categoryEntity);
    }
}
