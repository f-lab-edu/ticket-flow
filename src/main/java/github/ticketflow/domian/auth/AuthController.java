package github.ticketflow.domian.auth;

import github.ticketflow.domian.auth.signUp.SignUpRequestDTO;
import github.ticketflow.domian.auth.signUp.SignUpResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public SignUpResponseDTO signUp(@Valid @RequestBody SignUpRequestDTO dto) {
        return authService.signUp(dto);
    }
}
