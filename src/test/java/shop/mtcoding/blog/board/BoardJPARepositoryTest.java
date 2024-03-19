package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import shop.mtcoding.blog.user.User;

import java.util.Optional;

@DataJpaTest
public class BoardJPARepositoryTest {
    @Autowired
    private BoardJPARepository boardJPARepository;
    @Autowired
    private EntityManager em;


    @Test
    public void findByIdJoinUserAndReplies_test(){
        // given
        int id = 4;
        // when
        Board board = boardJPARepository.findByIdJoinUserAndReplies(id).get();

        // then

    }

    @Test
    public void findById_test(){
        // given
        int id = 1;
        // when
        Optional<Board> boardOP = boardJPARepository.findById(id);

        if (boardOP.isPresent()){
            Board board = boardOP.get();
            System.out.println("findById_test : " + board.getTitle());
        }
        // then

    }

    //save
    @Test
    public void save_test(){
        // given
        User sessionUser = User.builder().id(1).build();
        Board board = Board.builder().title("제목5").content("내용5").user(sessionUser).build();
        // when
        boardJPARepository.save(board);
        // then

    }

    //findById

    //findByIdJoinUser

    //findAll

    //findAll
    @Test
    public void findAll_test(){
        // given
        Sort sort = Sort.by(Sort.Direction.DESC);
        // when

        // then

    }

    //deleteById
    @Test
    public void deleteById_test(){
        // given
        int id = 1;
        // when
        boardJPARepository.deleteById(id);
        em.flush();
        // then
    }
}
