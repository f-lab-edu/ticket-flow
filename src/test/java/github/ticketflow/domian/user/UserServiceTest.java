package github.ticketflow.domian.user;

import github.ticketflow.domian.user.dto.UserResponseDTO;
import github.ticketflow.domian.user.dto.UserUpdateRequestDTO;
import org.assertj.core.api.Assertions;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

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

}