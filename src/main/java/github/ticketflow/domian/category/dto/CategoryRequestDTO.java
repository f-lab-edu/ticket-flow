package github.ticketflow.domian.category.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoryRequestDTO {

    @NotBlank
    private String categoryName;

    @NotBlank
    private int categoryLevel;

    private Long parentCategoryId;

}
