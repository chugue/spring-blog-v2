package shop.mtcoding.blog.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;


@Import(UserRepository.class)
@DataJpaTest // DataSource(connection pool), EntityManger
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void login_test(){
        // given
        UserRequest.LoginDTO reqDTO = new UserRequest.LoginDTO();
        reqDTO.setUsername("ssar");
        reqDTO.setPassword("1234");
        // when
        User user = userRepository.findByUsernameAndPassword(reqDTO);
        Assertions.assertThat(user.getUsername().equals(reqDTO.getUsername()));
        Assertions.assertThat(user.getPassword().equals(reqDTO.getPassword()));

    }
}