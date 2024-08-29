package github.ticketflow.domian.auth;

import github.ticketflow.config.exception.BusinessException;
import github.ticketflow.config.exception.ErrorCode;
import github.ticketflow.domian.auth.signUp.SignUpRequestDTO;
import github.ticketflow.domian.auth.signUp.SignUpResponseDTO;
import github.ticketflow.domian.user.UserEntity;
import github.ticketflow.domian.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SignUpResponseDTO signUp(SignUpRequestDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new BusinessException(ErrorCode.DUPLICATED_EMAIL);
        }

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
