package github.ticketflow.domian.popularSearch;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.List;

public interface PopularSearchRepository extends JpaRepository<PopularSearchEntity, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    PopularSearchEntity findByKeyword(String keyword);

    List<PopularSearchEntity> findTop10ByOrderBySearchCountDesc();

}
