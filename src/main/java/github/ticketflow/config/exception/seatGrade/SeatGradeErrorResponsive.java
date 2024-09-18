package github.ticketflow.config.exception.seatGrade;

import github.ticketflow.config.exception.ErrorResponsive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum SeatGradeErrorResponsive implements ErrorResponsive {
    NOT_FOUND_SEAT_GRADE(HttpStatus.NOT_FOUND, "좌석 등급을 찾을 수 없습니다.");

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
