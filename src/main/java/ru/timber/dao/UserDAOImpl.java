package ru.timber.dao;


import ru.timber.model.Albums;
import ru.timber.model.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
@Transactional(readOnly = true)
public class UserDAOImpl implements UserDAO {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    @Override
    public List<User> getAll() {
        return entityManager.createQuery(
                "select u from User u", User.class
        ).getResultList();
    }

    @Override
    public User getByLogin(String login) {
        Query q = entityManager.createQuery("SELECT u FROM User u WHERE u.login = :login", User.class);
        q.setParameter("login", login);
        return (User) q.getSingleResult();
    }

    @Override
    @Transactional
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<Albums> findAlbumsByUser(User user) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u = :user", User.class).getSingleResult().getUserA();
    }


}
