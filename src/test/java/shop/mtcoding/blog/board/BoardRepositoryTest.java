package shop.mtcoding.blog.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(BoardRepository.class)
@DataJpaTest
public class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void findById_test () {
        int id = 1;
        Board board = boardRepository.findById(id);
    }
    @Test
    public void findByIdJoinUser_test () {
        int id = 1;
        System.out.println("start -1");
        Board board = boardRepository.findByIdJoinUser(id);
        System.out.println("start -2");
        System.out.println(board.getId());
        System.out.println("start -3");
        System.out.println(board.getUser().getUsername());
    }
}
