package github.ticketflow.domian.user;

import github.ticketflow.domian.user.entity.UserEntity;
import github.ticketflow.domian.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@Import(UserService.class)
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @DisplayName("회원 가입을 할 때 입력한 이메일이 데이터 베이스에 존재하면 true를 반환한다.")
    @Test
    void existsByEmail() {
        // given
        UserEntity userEntity = new UserEntity("test1234@naver.com", "dltkdgur12!", "dltkdgur", "123124124");
        userRepository.save(userEntity);

        // when
        Boolean b = userRepository.existsByEmail("test1234@naver.com");

        // then
        assertThat(b).isTrue();
    }

    @DisplayName("회원 탈퇴 후, 탙퇴한 회원을 찾을 때 false를 반환을 한다.")
    @Test
    void findDeletedUserTest() {
        // given
        UserEntity userEntity = new UserEntity("test1234@naver.com", "dltkdgur12!", "dltkdgur", "123124124");
        userRepository.save(userEntity);

        userService.deletedUser(userEntity.getId());

        // when
        Boolean b = userRepository.existsByEmail("test1234@naver.com");

        // then
        assertThat(b).isFalse();
    }


}