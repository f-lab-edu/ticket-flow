package github.ticketflow.domian.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class signUpDTO {

    private String email;
    private String password;
    private String username;
    private String phoneNumber;
}
