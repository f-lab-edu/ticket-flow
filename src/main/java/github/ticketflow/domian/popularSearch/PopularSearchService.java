package github.ticketflow.domian.popularSearch;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PopularSearchService {

    private final StringRedisTemplate redisTemplate;

    private static final String LOCK_PREFIX = "lock:search:";
    private static final String SEARCH_RANKING_KEY = "popular:search:ranking";



    public void incrementSearchCount(String keyword) {
        String lockKey = LOCK_PREFIX + keyword;

        Boolean lockAcquired = redisTemplate.opsForValue().setIfAbsent(lockKey, "LOCKED", 5, TimeUnit.SECONDS);

        if (lockAcquired) {
            try {
                updateSearchCount(keyword);
            } finally {
                releaseLock(lockKey);
            }
        } else {
            System.out.println("다른 프로세스에서 이미 락을 획득했습니다. 요청을 무시합니다.");
        }

    }

    private void releaseLock(String lockKey) {
        redisTemplate.delete(lockKey);
    }

    private void updateSearchCount(String keyword) {
        redisTemplate.opsForZSet().incrementScore(SEARCH_RANKING_KEY, keyword, 1);
    }

    public Set<String> getTop10SearchKeywords() {
        return redisTemplate.opsForZSet().reverseRange(SEARCH_RANKING_KEY, 0, 9);
    }

//    private final PopularSearchRepository popularSearchRepository;
//
//    public void saveOrUpdatePopularSearch(String keyword) {
//
//        PopularSearchEntity popularSearchEntity = popularSearchRepository.findByKeyword(keyword);
//
//        if (popularSearchEntity != null) {
//            popularSearchEntity.update(1);
//            popularSearchRepository.save(popularSearchEntity);
//        } else {
//            PopularSearchEntity newPopularSearchEntity = new PopularSearchEntity(keyword, 1);
//            popularSearchRepository.save(newPopularSearchEntity);
//        }
//
//    }
//
//    public List<PopularSearchEntity> getPopularKeywords() {
//        return popularSearchRepository.findTop10ByOrderBySearchCountDesc();
//    }

}
