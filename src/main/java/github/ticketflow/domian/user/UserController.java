package github.ticketflow.domian.user;

import github.ticketflow.domian.user.dto.UserResponseDTO;
import github.ticketflow.domian.user.dto.UserUpdateRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PatchMapping("/user/{userId}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long userId,
                                                      @Valid @RequestBody UserUpdateRequestDTO dto) {
        return  ResponseEntity.ok(new UserResponseDTO(userService.updateUser(userId, dto)));
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<UserResponseDTO> deleteUser(@PathVariable Long userId) {
        return ResponseEntity.ok(new UserResponseDTO(userService.deletedUser(userId)));
    }

}
