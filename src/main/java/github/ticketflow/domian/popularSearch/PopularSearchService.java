package github.ticketflow.domian.popularSearch;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class PopularSearchService {

    private final Map<String, Long> keywordCount = new HashMap<>();

    public void incrementKeywordCount(String keyword) {
        keywordCount.merge(keyword, 1L, Long::sum);
    }

    @Cacheable(value = "popularKeywords", key = "#limit")
    public List<String> getPopularKeywords(int limit) {
        return keywordCount.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .limit(limit)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    @CacheEvict(value = "popularKeyword", allEntries = true)
    public void resetKeywords() {
        keywordCount.clear();
    }
}
