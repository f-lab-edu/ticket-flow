package github.ticketflow.domian.user;

import github.ticketflow.domian.user.dto.UserResponseDTO;
import github.ticketflow.domian.user.dto.UserUpdateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PatchMapping("/user/{userId}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long userId, @RequestBody UserUpdateRequestDTO dto) {
        return  ResponseEntity.ok(userService.updateUser(userId, dto));
    }

}
