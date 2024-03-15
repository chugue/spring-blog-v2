package shop.mtcoding.blog.user;


import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final EntityManager em;

    public User findByUsernameAndPassword (UserRequest.LoginDTO reqDTO){
        Query query = em.createQuery("select u from User u where u.username = :username and u.password = :password", User.class);
        query.setParameter("username", reqDTO.getUsername());
        query.setParameter("password", reqDTO.getPassword());
        return (User) query.getSingleResult();
    }

    @Transactional
    public User save (User user){
        em.persist(user); //DB에 담긴 user
        return user;
    }
//
//    @Transactional
//    public User updateById (int id, String password, String email){
//        User user = findById(id);
//    }
//
//    private User findById(int id) {
//
//    }
}
