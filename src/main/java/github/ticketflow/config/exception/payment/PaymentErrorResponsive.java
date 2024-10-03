package github.ticketflow.config.exception.payment;

import github.ticketflow.config.exception.ErrorResponsive;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum PaymentErrorResponsive implements ErrorResponsive {
    NOT_FOUND_PAYMENT(HttpStatus.NOT_FOUND, "결제를 한 내역을 찾을 수 없습니다.");

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
