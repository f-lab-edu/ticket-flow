package github.ticketflow.domian.popularSearch;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PopularSearchRepository extends JpaRepository<PopularSearchEntity, Long> {

    PopularSearchEntity findByKeyword(String keyword);

    List<PopularSearchEntity> findTop10ByOrderBySearchCountDesc();

}
