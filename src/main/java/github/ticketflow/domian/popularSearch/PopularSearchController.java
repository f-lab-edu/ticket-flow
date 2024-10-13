package github.ticketflow.domian.popularSearch;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/popularSearch")
public class PopularSearchController {

    private final PopularSearchService popularSearchService;

    @GetMapping
    public List<String> getPopularSearch() {
        return popularSearchService.getPopularKeywords(10);
    }

    @Scheduled(fixedRate = 3600000)
    public void updatePopularKeywords() {
        List<String> popularKeywords = popularSearchService.getPopularKeywords(10);
    }

}
