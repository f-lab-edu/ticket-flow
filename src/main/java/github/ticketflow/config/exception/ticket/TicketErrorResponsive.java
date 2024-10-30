package github.ticketflow.config.exception.ticket;

import github.ticketflow.config.exception.ErrorResponsive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum TicketErrorResponsive implements ErrorResponsive {
    NOT_FOUND_TICKET(HttpStatus.NOT_FOUND, "티켓을 찾을 수 없습니다."),
    SOLD_OUT_TICKET(HttpStatus.BAD_REQUEST, "티켓이 매진되었습니다.");

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
