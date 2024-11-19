package github.ticketflow.config.exception.event;

import github.ticketflow.config.exception.ErrorResponsive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum EventErrorResponsive implements ErrorResponsive {
    NOT_FOUND_EVENT(HttpStatus.NOT_FOUND, "이벤트를 찾을 수 없습니다.");


    private final HttpStatus status;
    private final String message;

    @Override
    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
