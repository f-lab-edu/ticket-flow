package github.ticketflow.config.exception.gradeTicket;

import github.ticketflow.config.exception.ErrorResponsive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GradeTicketErrorResponsive implements ErrorResponsive {
    NOT_FOUND_GRADE_TICKET(HttpStatus.NOT_FOUND, "등급별 좌석의 정보를 찾을 수 없습니다.");

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
