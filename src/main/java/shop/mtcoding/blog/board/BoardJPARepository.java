package shop.mtcoding.blog.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardJPARepository extends JpaRepository<Board, Integer> {

    // JPA레파지토리에서 정의되지 않은 메소드는 직접 이렇게 조인쿼리를 만들어 구현해준다.
    // 이렇게라도 해야지 같은 의존 일관성이 유지가 된다.
    @Query("select b from Board b join fetch b.user u where b.id = :id")
    Optional<Board> findByIdJoinUser(@Param("id") int id);
}
