package github.ticketflow.domian.popularSearch;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PopularSearchService {

    private final PopularSearchRepository popularSearchRepository;

    public void saveOrUpdatePopularSearch(String keyword) {

        PopularSearchEntity popularSearchEntity = popularSearchRepository.findByKeyword(keyword);

        if (popularSearchEntity != null) {
            popularSearchEntity.update(1);
            popularSearchRepository.save(popularSearchEntity);
        } else {
            PopularSearchEntity newPopularSearchEntity = new PopularSearchEntity(keyword, 1);
            popularSearchRepository.save(newPopularSearchEntity);
        }

    }

    public List<PopularSearchEntity> getPopularKeywords() {
        return popularSearchRepository.findTop10ByOrderBySearchCountDesc();
    }

}
