package ru.timber.service;

import ru.timber.dao.UserDAO;
import ru.timber.model.Albums;
import ru.timber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;



@Service
@Transactional
public class UserServiceImpl implements UserService {

    UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> getAll() {
        return userDAO.getAll();
    }

    @Override
    public User getByLogin(String login) {
        return userDAO.getByLogin(login);
    }

    @Override
    public List<Albums> findAlbumsByUser(User user) {
        return userDAO.findAlbumsByUser(user);
    }

    @Override
    public void insertUser(User user) {
        userDAO.insertUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    @Override
    public void removeUser(User user) {
        userDAO.delete(user);
    }
}
