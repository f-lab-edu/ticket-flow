package github.ticketflow.config.exception.category;

import github.ticketflow.config.exception.ErrorResponsive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CategoryErrorResponsive implements ErrorResponsive {

    NOT_FOUND_CATEGORY(HttpStatus.NOT_FOUND, "카테고리를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String message;

}
