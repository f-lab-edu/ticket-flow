package github.ticketflow.domian.auth.signUp;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SignUpRequestDTO {

    private String email;
    private String password;
    private String username;
    private String phoneNumber;
}
