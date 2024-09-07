package github.ticketflow.domian.category.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoryUpdateRequestDTO {

    private String categoryName;
    private Integer categoryLevel;
    private Long parentCategoryId;

}
