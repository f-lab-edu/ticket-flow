package github.ticketflow.domian.auth.signUp;

import github.ticketflow.domian.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpResponseDTO {

    private String email;
    private String username;
    private String phoneNumber;
    private String message;

    static public SignUpResponseDTO toSignUpResponseDTO(UserEntity userEntity, String message) {
        return new SignUpResponseDTO(userEntity.getEmail(), userEntity.getUsername(), userEntity.getPhoneNumber(), message);
    }

}
