package github.ticketflow.domian.user;

import github.ticketflow.config.exception.GlobalCommonException;
import github.ticketflow.config.exception.auth.AuthErrorCode;
import github.ticketflow.domian.user.dto.UserResponseDTO;
import github.ticketflow.domian.user.dto.UserUpdateRequestDTO;
import github.ticketflow.domian.user.entity.UserEntity;
import github.ticketflow.domian.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDTO updateUser(Long userId, UserUpdateRequestDTO dto) {
        UserEntity userEntity = userRepository
                .findById(userId)
                .orElseThrow(() -> new GlobalCommonException(AuthErrorCode.NOT_FOUND_USER));

        userEntity.update(dto);
        UserEntity updateUserEntity = userRepository.save(userEntity);

        return new UserResponseDTO(updateUserEntity);
    }
}
