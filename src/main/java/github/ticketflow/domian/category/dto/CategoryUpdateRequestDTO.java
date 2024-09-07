package github.ticketflow.domian.category.dto;

import lombok.Getter;

@Getter
public class CategoryUpdateRequestDTO {

    private String categoryName;
    private Integer categoryLevel;
    private Long parentCategoryId;

}
