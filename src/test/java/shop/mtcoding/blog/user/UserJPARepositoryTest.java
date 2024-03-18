package shop.mtcoding.blog.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;


//임포트 필요없음
@DataJpaTest
public class UserJPARepositoryTest {
    @Autowired
    private UserJPARepository userJPARepository;

    @Test
    public void findByUsernameAndPassword_test(){
        // given
        String username = "ssar";
        String password = "1234";
        // when
        userJPARepository.findByUsernameAndPassword(username, password);
        // then

    }

    @Test
    public void page_test(){
        // given
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(1,2,sort);
        // when
        Page<User> userPG = userJPARepository.findAll(pageable);
        // then
        ObjectMapper om = new ObjectMapper();
        String json = null;
        try {
            json = om.writeValueAsString(userPG);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(json);
    }

    @Test
    public void findAll_test(){
        // given
        // when
        userJPARepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

        // then
    }

    @Test
    public void findById_test(){
        // given
        int id = 5;
        // when
        Optional<User> userOP = userJPARepository.findById(id);

        if (userOP.isPresent()){
            User user = userOP.get();
            System.out.println("findById_test : " + user.getUsername() );
            System.out.println("findById_test : 1"  );
        }
        // then

    }

    @Test
    public void save_test(){
        // given
        User user = User.builder()
                .username("happy")
                .password("1234")
                .email("happy@nate.com")
                .build();
        // when
        userJPARepository.save(user);

        // then

    }



}
