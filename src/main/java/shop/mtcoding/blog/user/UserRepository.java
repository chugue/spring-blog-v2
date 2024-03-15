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

    @Transactional
    public User save (User user){
        em.persist(user); //DB에 담긴 user
        return user;
    }


    public User findById(int id) {
        // id, title, user_id (이질감), created_at
        User user = em.find(User.class, id);
        return user;
    }

    @Transactional
    public User updateById(int id,UserRequest.UpdateDTO reqDTO){
        User user = findById(id);
        user.setPassword(reqDTO.getPassword());
        user.setEmail(reqDTO.getEmail());
        return user;

    }
    public User findByUsernameAndPassword (UserRequest.LoginDTO reqDTO){
        Query query = em.createQuery("select u from User u where u.username = :username", User.class);
        query.setParameter("username", reqDTO.getUsername());
        return (User) query.getSingleResult();
    }


}
