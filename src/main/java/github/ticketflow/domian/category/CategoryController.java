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
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/category")
    public ResponseEntity<List<CategoryResponseDTO>> getCategory() {
        return ResponseEntity.ok(categoryService.getAllCategory()) ;
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable Long categoryId) {
        return ResponseEntity.ok(categoryService.getCategory(categoryId));
    }

    @PostMapping("/category")
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody CategoryRequestDTO dto) {
         return ResponseEntity.ok(categoryService.createCategory(dto));
    }

    @PatchMapping("/category/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable Long categoryId,
                                                              @RequestBody CategoryUpdateRequestDTO dto) {
        return ResponseEntity.ok(categoryService.updateCategory(categoryId, dto));
    }

    @DeleteMapping("/category/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> deleteCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(categoryService.deletedCategory(categoryId));
    }
}
