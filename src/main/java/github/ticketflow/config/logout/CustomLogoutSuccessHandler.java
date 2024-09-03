package github.ticketflow.config.logout;

import com.fasterxml.jackson.databind.ObjectMapper;
import github.ticketflow.domian.auth.logout.LogoutResponseDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.IOException;

public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        ObjectMapper objectMapper = new ObjectMapper();

        LogoutResponseDTO dto = new LogoutResponseDTO(HttpStatus.OK, "로그아웃 되었습니다.");
        String result = objectMapper.writeValueAsString(dto);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write(result);
    }
}
