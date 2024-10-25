package github.ticketflow.domian.popularSearch;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/popularSearch")
public class PopularSearchController {

    private final PopularSearchService popularSearchService;

    @PostMapping("/{keyword}")
    public void save(@PathVariable String keyword) {
        popularSearchService.incrementSearchCount(keyword);
    }

    @GetMapping
    public Set<String> getPopularSearch() {
        return popularSearchService.getTop10SearchKeywords();
    }
}
