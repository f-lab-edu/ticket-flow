package github.ticketflow.domian.category.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CategoryRequestDTO {

    @NotBlank
    private String categoryName;

    @NotBlank
    private int categoryLevel;

    private Long parentCategoryId;

}
