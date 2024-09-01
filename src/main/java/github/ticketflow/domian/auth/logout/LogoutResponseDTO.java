package github.ticketflow.domian.auth.logout;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LogoutResponseDTO {

    private HttpStatus status;
    private String message;

}
