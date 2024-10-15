package github.ticketflow.domian.popularSearch;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class PopularSearchServiceTest {

    @Autowired
    private PopularSearchService popularSearchService;

    @DisplayName("검색어가 저장이 되고 인기 검색어를 불러오면 리스트로 나온다.")
    @Test
    void getPopularKeywordsTest() {
        // give
        popularSearchService.saveOrUpdatePopularSearch("서울");
        popularSearchService.saveOrUpdatePopularSearch("수원");
        popularSearchService.saveOrUpdatePopularSearch("수원");
        popularSearchService.saveOrUpdatePopularSearch("서울");
        popularSearchService.saveOrUpdatePopularSearch("수원");

        // when
        List<PopularSearchEntity> result = popularSearchService.getPopularKeywords();

        // then
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getKeyword()).isEqualTo("수원");
        assertThat(result.get(1).getKeyword()).isEqualTo("서울");
    }


}