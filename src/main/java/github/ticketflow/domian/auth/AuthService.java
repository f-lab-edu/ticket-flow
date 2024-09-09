package github.ticketflow.domian.auth;

import github.ticketflow.config.exception.GlobalCommonException;
import github.ticketflow.config.exception.auth.AuthErrorResponsive;
import github.ticketflow.domian.auth.signUp.SignUpRequestDTO;
import github.ticketflow.domian.auth.signUp.SignUpResponseDTO;
import github.ticketflow.domian.user.entity.UserEntity;
import github.ticketflow.domian.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SignUpResponseDTO signUp(SignUpRequestDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new GlobalCommonException(AuthErrorResponsive.DUPLICATED_EMAIL);
        }

        UserEntity newUser = new UserEntity(
                dto.getEmail(),
                passwordEncoder.encode(dto.getPassword()),
                dto.getUsername(),
                dto.getPhoneNumber()
        );

        UserEntity saveUser = userRepository.save(newUser);
        return SignUpResponseDTO.toSignUpResponseDTO(saveUser);
    }

}
