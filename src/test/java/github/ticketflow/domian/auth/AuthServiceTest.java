package github.ticketflow.domian.auth;

import github.ticketflow.domian.auth.signUp.SignUpRequestDTO;
import github.ticketflow.domian.auth.signUp.SignUpResponseDTO;

import github.ticketflow.domian.user.entity.UserEntity;
import github.ticketflow.domian.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;

    @DisplayName("사용자 입력한 정보가 문제가 없으면 회원가입이 된다.")
    @Test
    void signup() {
        SignUpRequestDTO dto = new SignUpRequestDTO("test1234@naver.com", "dltkdgur12", "이상혁", "01044825308");
        BDDMockito.given(userRepository.existsByEmail(dto.getEmail())).willReturn(false);
        BDDMockito.given(passwordEncoder.encode(dto.getPassword())).willReturn("encodePassword");

        UserEntity userEntity = new UserEntity(dto.getEmail(), "encodePassword", dto.getUsername(), dto.getPhoneNumber());
        BDDMockito.given(userRepository.save(BDDMockito.any(UserEntity.class))).willReturn(userEntity);

        // when
        UserEntity result = authService.signUp(dto);

        // then
        assertThat(result)
                .extracting("email", "username", "phoneNumber")
                .contains("test1234@naver.com", "이상혁", "01044825308");
    }
}