package ru.timber.service;

import ru.timber.model.Albums;
import ru.timber.model.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User getByLogin(String login);
    List<Albums> findAlbumsByUser(User user);
    public void insertUser(User user);
    public void updateUser(User user);
    public void removeUser(User user);
}
