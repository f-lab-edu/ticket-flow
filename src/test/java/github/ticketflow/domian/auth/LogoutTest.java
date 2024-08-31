package github.ticketflow.domian.auth;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class LogoutTest {

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("로그아웃 시, 로그아웃 되었습니다. 라는 메세지가 나온다.")
    @Test
    public void logout() throws Exception {

        mockMvc.perform(post("/logout"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("로그아웃 되었습니다."));

    }

}
