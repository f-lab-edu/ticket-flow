package github.ticketflow.config.exception.paymemtTicket;

import github.ticketflow.config.exception.ErrorResponsive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum PaymentTicketErrorResponsive implements ErrorResponsive {
    NOT_FOUND_PAYMENT_TICKET(HttpStatus.NOT_FOUND, "결제한 티켓의 맵핑된 엔티티를 찾을 수 없습니다.");

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
