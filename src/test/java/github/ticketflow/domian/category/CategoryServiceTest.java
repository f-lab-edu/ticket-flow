package github.ticketflow.domian.category;

import github.ticketflow.domian.category.dto.CategoryRequestDTO;
import github.ticketflow.domian.category.dto.CategoryResponseDTO;
import github.ticketflow.domian.category.dto.CategoryUpdateRequestDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @DisplayName("카테고리의 level을 넣으면, level에 해당하는 데이터들의 리스트가 나온다.")
    @Test
    void getCategoriesByCategoryLevel() {
        // given
        CategoryEntity categoryEntity1 = CategoryEntity.builder()
                .categoryName("스포츠")
                .categoryLevel(1)
                .build();

        CategoryEntity categoryEntity2 = CategoryEntity.builder()
                .categoryName("뮤지컬")
                .categoryLevel(1)
                .build();

        CategoryEntity categoryEntity3 = CategoryEntity.builder()
                .categoryName("연극")
                .categoryLevel(1)
                .build();

        BDDMockito.given(categoryRepository.findAllByCategoryLevel(1))
                .willReturn(List.of(categoryEntity1, categoryEntity2, categoryEntity3));

        // when
        List<CategoryResponseDTO> result = categoryService.getCategoryByCategoryLevel(1);

        // then
        assertThat(result).hasSize(3)
                .extracting("categoryName", "categoryLevel")
                .containsExactlyInAnyOrder(
                        tuple("스포츠", 1),
                        tuple("뮤지컬", 1),
                        tuple("연극", 1)
                );

    }

    @DisplayName("카테고리의 level을 넣으면, level에 해당하는 데이터들의 리스트가 나온다.")
    @Test
    void getCategoriesByParentCategoryId() {
        // given
        CategoryEntity categoryEntity2 = CategoryEntity.builder()
                .categoryName("축구")
                .categoryLevel(2)
                .parentCategoryId(1L)
                .build();

        CategoryEntity categoryEntity3 = CategoryEntity.builder()
                .categoryName("야구")
                .categoryLevel(2)
                .parentCategoryId(1L)
                .build();

        BDDMockito.given(categoryRepository.findAllByParentCategoryId(1L))
                .willReturn(List.of(categoryEntity2, categoryEntity3));

        // when
        List<CategoryResponseDTO> result = categoryService.getCategoryByParentCategoryId(1L);

        // then
        assertThat(result).hasSize(2)
                .extracting("categoryName", "categoryLevel", "parentCategoryId")
                .containsExactlyInAnyOrder(
                        tuple("축구", 2, 1L),
                        tuple("야구", 2, 1L)
                );

    }

    @DisplayName("카테고리의 id로 특정한 카테고리를 가지고 온다.")
    @Test
    void getCategoryByIdTest() {
        // given
        CategoryEntity categoryEntity = CategoryEntity.builder()
                .categoryId(1L)
                .categoryName("스포츠")
                .categoryLevel(1)
                .build();

        BDDMockito.given(categoryRepository.findById(categoryEntity.getCategoryId()))
                .willReturn(Optional.of(categoryEntity));

        // when
        CategoryEntity result = categoryService.getCategory(categoryEntity.getCategoryId());

        // then
        assertThat(result)
                .extracting("categoryId","categoryName", "categoryLevel", "parentCategoryId")
                .contains(categoryEntity.getCategoryId(), categoryEntity.getCategoryName(), categoryEntity.getCategoryLevel(), categoryEntity.getParentCategoryId());
    }

    @DisplayName("카테고리를 추가하면, 추가한 카테고리의 정보가 나온다.")
    @Test
    void createCategoryTest() {
        // given
        CategoryRequestDTO dto = CategoryRequestDTO.builder()
                .categoryName("스포츠")
                .categoryLevel(1)
                .build();

        CategoryEntity categoryEntity = new CategoryEntity(dto);
        BDDMockito.given(categoryRepository.save(any(CategoryEntity.class)))
                .willReturn(categoryEntity);

        // when
        CategoryEntity result = categoryService.createCategory(dto);

        // then
        assertThat(result)
                .extracting("categoryName", "categoryLevel", "parentCategoryId")
                .contains("스포츠", 1, null);
    }

    @DisplayName("카테고리의 수정하고자 하는 필드의 값을 넣어서 보면, 수정하고자하는 필드만 값이 변경이 된다.")
    @Test
    void updateCategoryTest() {
        // give
        CategoryEntity categoryEntity = CategoryEntity.builder()
                .categoryId(1L)
                .categoryName("스포츠")
                .categoryLevel(1)
                .build();

        CategoryUpdateRequestDTO dto = CategoryUpdateRequestDTO.builder()
                .categoryName("뮤지컬")
                .build();

        BDDMockito.given(categoryRepository.findById(categoryEntity.getCategoryId()))
                .willReturn(Optional.of(categoryEntity));
        BDDMockito.given(categoryRepository.save(any(CategoryEntity.class))).willReturn(categoryEntity);

        // when
        CategoryEntity result = categoryService.updateCategory(1L, dto);

        // then
        assertThat(result)
                .extracting("categoryName", "categoryLevel", "parentCategoryId")
                .contains("뮤지컬", 1, null);
    }

    @DisplayName("카테고리의 특정 id로 카테고리를 삭제하면, 삭제한 카테고리의 정보가 나온다.")
    @Test
    void deleteCategoryTest() {
        // given
        CategoryEntity categoryEntity = CategoryEntity.builder()
                .categoryId(1L)
                .categoryName("스포츠")
                .categoryLevel(1)
                .build();

        BDDMockito.given(categoryRepository.findById(categoryEntity.getCategoryId()))
                .willReturn(Optional.of(categoryEntity));
        BDDMockito.willDoNothing().given(categoryRepository).deleteById(categoryEntity.getCategoryId());

        // when
        CategoryEntity result = categoryService.deletedCategory(categoryEntity.getCategoryId());

        // then
        assertThat(result)
                .extracting("categoryName", "categoryLevel", "parentCategoryId")
                .contains("스포츠", 1, null);
    }

}