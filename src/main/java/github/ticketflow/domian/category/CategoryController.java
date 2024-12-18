package github.ticketflow.domian.category;

import github.ticketflow.domian.category.dto.CategoryRequestDTO;
import github.ticketflow.domian.category.dto.CategoryResponseDTO;
import github.ticketflow.domian.category.dto.CategoryUpdateRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/category-level/{categoryLevel}")
    public ResponseEntity<List<CategoryResponseDTO>> getCategoryByCategoryLevel(@PathVariable int categoryLevel)  {
        return ResponseEntity.ok(categoryService.getCategoryByCategoryLevel(categoryLevel)) ;
    }

    @GetMapping("parent-category/{parentCategoryId}")
    public ResponseEntity<List<CategoryResponseDTO>> getCategoryByParentCategoryId(@PathVariable Long parentCategoryId)  {
        return ResponseEntity.ok(categoryService.getCategoryByParentCategoryId(parentCategoryId));
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable Long categoryId) {
        return ResponseEntity.ok(new CategoryResponseDTO(categoryService.getCategory(categoryId)));
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(@Valid  @RequestBody CategoryRequestDTO dto) {
         return ResponseEntity.ok(new CategoryResponseDTO(categoryService.createCategory(dto)));
    }

    @PatchMapping("/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable Long categoryId,
                                                              @Valid @RequestBody CategoryUpdateRequestDTO dto) {
        return ResponseEntity.ok(new CategoryResponseDTO(categoryService.updateCategory(categoryId, dto)));
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> deleteCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(new CategoryResponseDTO(categoryService.deletedCategory(categoryId)));
    }
}
