package github.ticketflow.domian.auth;

import github.ticketflow.domian.auth.signUp.SignUpRequestDTO;
import github.ticketflow.domian.user.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.PostMapping;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class loginTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAllInBatch();
    }

    @DisplayName("로그인을 성공적으로 했을 때, token을 넘겨준다.")
    @Test
    void loginSuccess() throws Exception {
        SignUpRequestDTO dto = new SignUpRequestDTO("test1@naver.com", "dltkdgur12", "이상혁", "01044554455");
        authService.signUp(dto);

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("email", "test1@naver.com")
                        .param("password", "dltkdgur12"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists());
    }

    @DisplayName("로그인을 실패했을 때, Unauthorized이 발생한다.")
    @Test
    void loginFail() throws Exception {

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("email", "test2@naver.com")
                        .param("password", "dltkdgur12"))
                .andExpect(status().isUnauthorized());
    }

}
