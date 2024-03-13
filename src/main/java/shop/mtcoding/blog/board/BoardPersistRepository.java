package shop.mtcoding.blog.board;


import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardPersistRepository {
    private final EntityManager em;

    @Transactional
    public Board save(Board board) {
        // 비영속 객체
        em.persist(board);
        // board -> 영속 객체가 된다.
        return board;
    }


    public List<Board> findAll() {
        Query query = em.createQuery("SELECT b FROM Board b ORDER BY b.id desc", Board.class);
        return query.getResultList();
    }

    public Board findById(Integer id) {
        Query query = em.createNativeQuery("SELECT * FROM board_tb WHERE id = ?", Board.class);
        query.setParameter(1, id);
        return (Board) query.getSingleResult();
    }


    @Transactional
    public void deleteById(Integer id) {
        Query query =
                em.createNativeQuery("DELETE FROM board_tb WHERE id = ?");
        query.setParameter(1, id);
        query.executeUpdate();
    }

    @Transactional
    public void updateById(Integer id, String title, String content, String username) {
        Query query =
                em.createNativeQuery("UPDATE board_tb SET title = ? , content = ? , username = ? WHERE id = ?");
        query.setParameter(1, title);
        query.setParameter(2, content);
        query.setParameter(3, username);
        query.setParameter(4, id);
        query.executeUpdate();
    }
}
