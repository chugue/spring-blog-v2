package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import shop.mtcoding.blog.user.User;

import java.util.List;

@Import(BoardRepository.class)
@DataJpaTest
public class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private EntityManager em;


    @Test
    public void stream_test(){
        // given
        String q = "SELECT b FROM Board b ORDER BY b.id DESC";
        List<Board> boardList = em.createQuery(q, Board.class).getResultList();
        // when

        // then

    }

    @Test
    public void radomQuery_test() {
        String q1 = "SELECT b FROM Board b ORDER BY b.id DESC";
        List<Board> boardList = em.createQuery(q1, Board.class).getResultList();

        int[] ids = boardList.stream().mapToInt(board -> board.getUser().getId()).distinct().toArray();
        //select u from User u where b.id in (?, ?);
        String q2 = "SELECT u FROM User u WHERE u.id in (";
        for (int i = 0; i < ids.length; i++) {
            if (i == ids.length - 1) {
                q2 = q2 + ids[i] + ");";
            } else {
                q2 = q2 + ids[i] + ",";
            }
        }
        List<User> userList = em.createQuery(q2, User.class).getResultList();
        for (Board board : boardList) {
            for (User user : userList) {
                if (user.getId() == board.getUser().getId())
                    board.setUser(user);
            }
        }
    }

    @Test
    public void randomquery_test() {
        int[] ids = {1, 2};
        //select u from User u where b.id in (?, ?);
        String q = "SELECT u FROM User u WHERE u.id = (";
        for (int i = 0; i < ids.length; i++) {
            if (i == ids.length - 1) {
                q = q + ids[i] + ")";
            } else {
                q = q + ids[i] + ",";
            }
        }
        System.out.println(q);
    }

    @Test
    public void findAll_custom_inquery_test() {
        List<Board> boardList = boardRepository.findAll();

        int[] ids = boardList.stream().mapToInt(board -> board.getUser().getId()).distinct().toArray();
        for (int id : ids) {
            System.out.println(id);
        }
    }


    @Test
    public void findAll_lazyloading_test() {
        //given
        List<Board> boardList = boardRepository.findAll();
        boardList.forEach(board -> {
            System.out.println(board.getUser().getUsername()); // lazyloading
        });
        //when
        //then
    }

    @Test
    public void findById_test() {
        int id = 1;
        Board board = boardRepository.findById(id);
    }

    @Test
    public void findByIdJoinUser_test() {
        int id = 1;
        System.out.println("start -1");
        Board board = boardRepository.findByIdJoinUser(id);
        System.out.println("start -2");
        System.out.println(board.getId());
        System.out.println("start -3");
        System.out.println(board.getUser().getUsername());
    }
}
