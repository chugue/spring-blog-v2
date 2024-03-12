package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import lombok.Data;
import shop.mtcoding.util.MyDateUtil;

import java.sql.Timestamp;


@Table(name="board_tb")
@Data
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
    private String username;
    private Timestamp createdAt;
}
