package github.ticketflow.domian.user;

import github.ticketflow.domian.user.dto.UserResponseDTO;
import github.ticketflow.domian.user.dto.UserUpdateRequestDTO;
import github.ticketflow.domian.user.entity.LeaveUserEntity;
import github.ticketflow.domian.user.entity.UserEntity;
import github.ticketflow.domian.user.repository.LeaveUserRepository;
import github.ticketflow.domian.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private LeaveUserRepository leaveUserRepository;

    @InjectMocks
    private UserService userService;

    @DisplayName("이름과 전화번호를 수정을 하면 수정된 정보가 나온다.")
    @Test
    void updateUserTest() {
        // given
        UserEntity userEntity = UserEntity.builder()
                                    .id(1L)
                                    .email("test1@gmail.com")
                                    .password("dltkdgur12")
                                    .username("이상혁")
                                    .phoneNumber("01044554455")
                                    .build();

        BDDMockito.given(userRepository.save(any(UserEntity.class))).willReturn(userEntity);

        UserUpdateRequestDTO dto = new UserUpdateRequestDTO("권예정", "01011111111");
        BDDMockito.given(userRepository.findById(userEntity.getId())).willReturn(Optional.of(userEntity));


        // when
        UserResponseDTO updateUser = userService.updateUser(userEntity.getId(), dto);

        // then
        assertThat(updateUser)
                .extracting("username", "phoneNumber")
                .containsExactly("권예정", "01011111111");
    }

    @DisplayName("회원탈퇴시, 탈퇴한 회원의 정보가 나온다.")
    @Test
    void deletedUserTest() {
        // given
        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .email("test1@gmail.com")
                .password("dltkdgur12")
                .username("이상혁")
                .phoneNumber("01044554455")
                .build();

        LeaveUserEntity leaveUserEntity = new LeaveUserEntity(userEntity);

        BDDMockito.given(userRepository.findById(userEntity.getId())).willReturn(Optional.of(userEntity));
        BDDMockito.given(leaveUserRepository.save(any(LeaveUserEntity.class))).willReturn(leaveUserEntity);
        BDDMockito.willDoNothing().given(userRepository).deleteById(userEntity.getId());

        // when
        UserResponseDTO dto = userService.deletedUser(userEntity.getId());

        // then
        assertThat(dto)
                .extracting("userId", "email", "username", "phoneNumber")
                .containsExactly(1L, "test1@gmail.com", "이상혁", "01044554455");
    }

}