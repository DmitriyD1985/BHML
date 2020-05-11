package ru.timber.service;

import ru.timber.model.Albums;
import ru.timber.model.Songs;
import ru.timber.model.User;

import java.util.List;

public interface AlbumService {
    Albums getByName(String albumName);
    List<User> getUsersChoisedAlbums(Albums album);
    public void add(Albums albums);
    List<Songs> getSongsByAlbum(String album);
    public void updateAlbum(Albums album);
    public void removeAlbum(Albums album);
}
