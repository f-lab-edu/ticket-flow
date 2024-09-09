package github.ticketflow.domian.category.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CategoryUpdateRequestDTO {

    private String categoryName;
    private Integer categoryLevel;
    private Long parentCategoryId;

}
