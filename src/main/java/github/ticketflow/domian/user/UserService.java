package github.ticketflow.domian.user;

import github.ticketflow.config.exception.GlobalCommonException;
import github.ticketflow.config.exception.auth.AuthErrorResponsive;
import github.ticketflow.domian.user.dto.UserResponseDTO;
import github.ticketflow.domian.user.dto.UserUpdateRequestDTO;
import github.ticketflow.domian.user.entity.LeaveUserEntity;
import github.ticketflow.domian.user.entity.UserEntity;
import github.ticketflow.domian.user.repository.LeaveUserRepository;
import github.ticketflow.domian.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final LeaveUserRepository leaveUserRepository;

    public UserEntity getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new GlobalCommonException(AuthErrorResponsive.NOT_FOUND_USER));
    }

    public UserEntity updateUser(Long userId, UserUpdateRequestDTO dto) {
        UserEntity userEntity = userRepository
                .findById(userId)
                .orElseThrow(() -> new GlobalCommonException(AuthErrorResponsive.FAIL_UPDATE));

        userEntity.update(dto);
        return userRepository.save(userEntity);
    }

    public UserEntity deletedUser(Long userId) {
        UserEntity userEntity = userRepository
                .findById(userId)
                .orElseThrow(() -> new GlobalCommonException(AuthErrorResponsive.NOT_FOUND_USER));

        LeaveUserEntity saveLeaveUserEntity = leaveUserRepository.save(new LeaveUserEntity(userEntity));
        userRepository.deleteById(userId);

        return userEntity;
    }
}
