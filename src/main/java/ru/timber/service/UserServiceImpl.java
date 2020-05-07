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

    @Autowired
    UserDAO userDAO;

    @Override
    public List<User> getAll() {
        return userDAO.getAll();
    }

    @Override
    public User getByLogin(String login) {
        return userDAO.getByLogin(login);
    }

    @Override
    public void add(User user) {
        userDAO.add(user);
    }

    @Override
    public List<Albums> findAlbumsByUser(User user) {
        return userDAO.findAlbumsByUser(user);
    }
}
