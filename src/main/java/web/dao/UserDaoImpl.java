package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public void createUser(User user) {
        System.out.println("Создаю юзера");
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        System.out.println("Апдейт");
        entityManager.merge(user);
    }

    @Override
    public User readUser(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void deleteUser(long id) {
        System.out.println("Удаляю");
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }
}
