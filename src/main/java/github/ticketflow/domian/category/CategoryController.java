package github.ticketflow.domian.category;

import github.ticketflow.domian.category.dto.CategoryRequestDTO;
import github.ticketflow.domian.category.dto.CategoryResponseDTO;
import github.ticketflow.domian.category.dto.CategoryUpdateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/category-level")
    public ResponseEntity<List<CategoryResponseDTO>> getCategoryByCategoryLevel(@PathVariable int categoryLevel)  {
        return ResponseEntity.ok(categoryService.getCategoryByCategoryLevel(categoryLevel)) ;
    }

    @GetMapping("parent-category/{parentCategoryId}")
    public ResponseEntity<List<CategoryResponseDTO>> getCategoryByParentCategoryId(@PathVariable Long parentCategoryId)  {
        return ResponseEntity.ok(categoryService.getCategoryByParentCategoryId(parentCategoryId));
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable Long categoryId) {
        return ResponseEntity.ok(categoryService.getCategory(categoryId));
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody CategoryRequestDTO dto) {
         return ResponseEntity.ok(categoryService.createCategory(dto));
    }

    @PatchMapping("/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable Long categoryId,
                                                              @RequestBody CategoryUpdateRequestDTO dto) {
        return ResponseEntity.ok(categoryService.updateCategory(categoryId, dto));
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> deleteCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(categoryService.deletedCategory(categoryId));
    }
}
