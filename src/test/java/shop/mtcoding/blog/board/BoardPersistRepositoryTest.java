package shop.mtcoding.blog.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@Import(BoardNativeRepository.class)
@DataJpaTest
public class BoardPersistRepositoryTest {
    @Autowired //DI
    private BoardNativeRepository boardNativeRepository;

    @Test
    public void save_test () {
        //given
       Board board = new Board("제목5", "내용5", "ssar");
        //when
        boardNativeRepository.save(board);
        System.out.println("save_test : " + board);
        //then
    }

    @Test
    public void findAllTest (){
        //given
        //when
        List<Board> boardList = boardNativeRepository.findAll();
        //then
        System.out.println("findAll_test/size : " + boardList.size());
        System.out.println("findAll_test/username :" + boardList.get(2).getUsername());

        //org.assertj.core.api
        assertThat(boardList.size()).isEqualTo(3);
        assertThat(boardList.get(2).getUsername()).isEqualTo("ssar");
    }

    @Test
    public void findById (){
        //given
        int id = 1;
        //when
        Board board = boardNativeRepository.findById(id);
        //then
        assertThat(board.getTitle()).isEqualTo("제목1");
        assertThat(board.getContent()).isEqualTo("내용1");
    }

    @Test
    public void deleteById_test () {
        //given
        int id = 1;
        //when
        boardNativeRepository.deleteById(id);
        //then
        List<Board> boardList = boardNativeRepository.findAll();
        assertThat(boardList.size()).isEqualTo(3);
    }
    @Test
    public void updateById_test () {
        //given
        int id = 1;
        String title = "제목수정 1";
        String content = "내용수정 1";
        String username = "bori";
        //when
        boardNativeRepository.updateById(id, title, content, username);
        //then
        Board board = boardNativeRepository.findById(id);
        System.out.println("updateById_test/board :" + board);
        assertThat(board.getTitle()).isEqualTo("제목수정 1");
        assertThat(board.getContent()).isEqualTo("내용수정 1");
        assertThat(board.getUsername()).isEqualTo("bori");

    }
}
