package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog.user.User;

import java.sql.Timestamp;

@NoArgsConstructor
@Table(name="board_tb")
@Data
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
//    @JoinColumn(name = "user_id") 칼럼이름을 내가 정할 수 있다.
    @ManyToOne (fetch = FetchType.LAZY)
    private User user; // db -> user_id 변수명
    @CreationTimestamp //pc -> db (날짜주입)
    private Timestamp createdAt;

    @Builder
    public Board(Integer id, String title, String content, User user, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.createdAt = createdAt;
    }
}
