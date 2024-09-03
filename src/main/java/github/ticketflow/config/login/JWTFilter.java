package github.ticketflow.config.login;

import github.ticketflow.config.exception.auth.AuthErrorCode;
import github.ticketflow.config.exception.GlobalCommonException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new GlobalCommonException(AuthErrorCode.ABNORMAL_TOKEN);
        }

        String token = authorizationHeader.replace("Bearer ", "");

        if(!jwtUtil.isExpired(token)) {
            Authentication authentication = jwtUtil.getAuthentication(jwtUtil.getEmail(token));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }



        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return StringUtils.startsWithAny(request.getRequestURI(), "/login");
    }

}
