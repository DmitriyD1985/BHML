package ru.timber.service;

import ru.timber.model.Albums;
import ru.timber.model.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User getByLogin(String login);
    void add(User user);
    List<Albums> findAlbumsByUser(User user);
}
