package github.ticketflow.domian.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

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


}