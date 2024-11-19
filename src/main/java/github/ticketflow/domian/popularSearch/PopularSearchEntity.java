package github.ticketflow.domian.popularSearch;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PopularSearch")
public class PopularSearchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "popular_search_id")
    private Long PopularSearchId;

    @Column(name = "keyword", nullable = false, unique = true)
    private String keyword;

    @Column(name = "search_count")
    private int searchCount;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDate createdAt;

    public PopularSearchEntity(String keyword, int searchCount) {
        this.keyword = keyword;
        this.searchCount = searchCount;
    }

    void update (int addCount) {
        this.searchCount = this.searchCount + addCount;
    }

}
