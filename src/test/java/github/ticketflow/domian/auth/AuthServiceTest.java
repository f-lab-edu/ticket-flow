package github.ticketflow.domian.auth;

import github.ticketflow.domian.auth.signUp.SignUpRequestDTO;
import github.ticketflow.domian.auth.signUp.SignUpResponseDTO;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class AuthServiceTest {

    @Autowired
    private AuthService authService;


    @DisplayName("회원 가입을 한다.")
    @Test
    void signup () {
        // given
        SignUpRequestDTO signUpRequestDTO = new SignUpRequestDTO("test1234@naver.com", "dltkdgur12", "이상혁", "01044825308");
        // when
        SignUpResponseDTO saveUser = authService.signUp(signUpRequestDTO);
        // then
        assertThat(saveUser)
                .extracting("email", "username", "phoneNumber", "message")
                .contains("test1234@naver.com", "이상혁", "01044825308", "회원가입에 성공했습니다");


    }

}