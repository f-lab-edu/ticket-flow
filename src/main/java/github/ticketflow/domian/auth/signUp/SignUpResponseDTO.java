package github.ticketflow.domian.auth.signUp;

import github.ticketflow.domian.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class SignUpResponseDTO {

    private String email;
    private String username;
    private String phoneNumber;
    private HttpStatus status;

    static public SignUpResponseDTO toSignUpResponseDTO(UserEntity userEntity) {
        return new SignUpResponseDTO(userEntity.getEmail(), userEntity.getUsername(), userEntity.getPhoneNumber(), HttpStatus.CREATED);
    }

}
