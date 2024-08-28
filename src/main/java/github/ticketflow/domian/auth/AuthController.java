package github.ticketflow.domian.auth;

import github.ticketflow.domian.auth.signUp.SignUpRequestDTO;
import github.ticketflow.domian.auth.signUp.SignUpResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signUp")
    public SignUpResponseDTO signUp(@RequestBody SignUpRequestDTO dto) {
        return authService.signUp(dto);
    }
}
