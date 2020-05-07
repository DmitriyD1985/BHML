package ru.timber.dao;

import ru.timber.model.Albums;
import ru.timber.model.User;
import java.util.List;

public interface UserDAO {
    List<User> getAll();
    User getByLogin(String login);
    void add(User user);
    List<Albums> findAlbumsByUser(User user);
}
