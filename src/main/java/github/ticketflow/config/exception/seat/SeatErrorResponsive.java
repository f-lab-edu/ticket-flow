package github.ticketflow.config.exception.seat;

import github.ticketflow.config.exception.ErrorResponsive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum SeatErrorResponsive implements ErrorResponsive {
    NOT_FOUND_SEAT(HttpStatus.NOT_FOUND, "좌석을 찾을 수 없습니다.");

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
