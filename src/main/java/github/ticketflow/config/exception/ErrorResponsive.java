package github.ticketflow.config.exception;

import org.springframework.http.HttpStatus;

public interface ErrorResponsive {

    HttpStatus getStatus();
    String getMessage();

}
