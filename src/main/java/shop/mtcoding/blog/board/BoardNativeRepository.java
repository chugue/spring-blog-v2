package shop.mtcoding.blog.board;


import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardNativeRepository {
    private final EntityManager em;


    public List<Board>  findAll (){
        // 쿼리 결과를 정확히 받을 엔티티가 있다면 클래스명.class로 바로 매핑해서 받을 수 있다.
        Query query = em.createNativeQuery("SELECT * FROM board_tb ORDER BY id DESC", Board.class);
        return query.getResultList();
    }

    public Board  findById (int id){
        // 쿼리 결과를 정확히 받을 엔티티가 있다면 클래스명.class로 바로 매핑해서 받을 수 있다.
        Query query = em.createNativeQuery("SELECT * FROM board_tb WHERE id = ?", Board.class);
        query.setParameter(1, id);
        return (Board) query.getSingleResult();
    }

    @Transactional
    public void save (String title, String content, String username){
        Query query =
                em.createNativeQuery("INSERT INTO board_tb (title, content, username, created_at) VALUES (?, ?, ?, NOW())");
        query.setParameter(1, title);
        query.setParameter(2, content);
        query.setParameter(3, username);

        query.executeUpdate();
    }
}
