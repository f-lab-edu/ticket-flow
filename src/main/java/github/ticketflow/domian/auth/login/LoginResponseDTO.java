package github.ticketflow.domian.auth.login;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginResponseDTO {

    private Long userId;
    private String email;
    private String token;

    public LoginResponseDTO(Long userId, String email, String token) {
        this.userId = userId;
        this.email = email;
        this.token = token;
    }
}
