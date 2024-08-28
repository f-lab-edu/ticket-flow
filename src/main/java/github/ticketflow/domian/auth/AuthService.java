package github.ticketflow.domian.auth;

import github.ticketflow.domian.auth.signUp.SignUpRequestDTO;
import github.ticketflow.domian.auth.signUp.SignUpResponseDTO;
import github.ticketflow.domian.user.UserEntity;
import github.ticketflow.domian.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SignUpResponseDTO signUp(SignUpRequestDTO dto) {
        userRepository.existsByEmail(dto.getEmail()).orElseThrow(IllegalArgumentException::new);

        UserEntity newUser = new UserEntity(
                dto.getEmail(),
                passwordEncoder.encode(dto.getPassword()),
                dto.getUsername(),
                dto.getPhoneNumber()
        );

        UserEntity saveUser = userRepository.save(newUser);
        String message = "회원가입에 성공했습니다";
        return SignUpResponseDTO.toSignUpResponseDTO(saveUser, message);
    }

}
