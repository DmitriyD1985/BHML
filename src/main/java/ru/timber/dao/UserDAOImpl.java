package ru.timber.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.timber.model.Albums;
import ru.timber.model.User;
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
        Query selectquerry = entityManager.createQuery("SELECT u FROM User u WHERE u.login = :login", User.class);
        selectquerry.setParameter("login", login);
        return (User) selectquerry.getSingleResult();
    }

    @Override
    public List<Albums> findAlbumsByUser(User user) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u = :user", User.class).getSingleResult().getUserA();
    }

    @Override
    public void insertUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        User updatableUser = getByLogin(user.getLogin());
        updatableUser.setLogin(user.getLogin());
        updatableUser.setPassword(user.getPassword());
        updatableUser.setUserProfile(user.getUserProfile());
        entityManager.merge(updatableUser);
    }

    @Override
    public void delete(User user) {
        Query selectquerry = entityManager.createQuery("SELECT u FROM User u WHERE u.login = :login", User.class);
        selectquerry.setParameter("login", user.getLogin());
        User entity = (User) selectquerry.getSingleResult();
            entity.getUserA().clear();
            entityManager.remove(entity);
    }
}
