package github.ticketflow.domian.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CategoryRequestDTO {

    @NotBlank
    private String categoryName;

    @Positive
    private int categoryLevel;

    private Long parentCategoryId;



}
