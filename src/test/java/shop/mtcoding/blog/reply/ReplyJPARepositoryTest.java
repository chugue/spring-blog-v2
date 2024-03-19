package shop.mtcoding.blog.reply;


// 1. One관계는 조인하고, Many 관계는 조회를 한 번더 한다. -> DTO 담기
// 2. Many 관계를 양방향 매핑하기

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import shop.mtcoding.blog.board.Board;

@DataJpaTest
public class ReplyJPARepositoryTest {
    @Autowired
    private ReplyJPARepository replyJPARepository;

    @Test
    public void save_test(){
        // given
        Reply reply = Reply.builder()
                .board(Board.builder().id(1).build()).build();
        // when

        // then

    }
}
