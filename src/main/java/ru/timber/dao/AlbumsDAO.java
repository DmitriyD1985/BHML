package ru.timber.dao;

import ru.timber.model.Albums;
import ru.timber.model.Songs;
import ru.timber.model.User;

import java.util.List;

//NativeQuery
public interface AlbumsDAO {
    List<Albums> getallAlbums();

    Albums getByName(String albumName);

    List<User> getUsersChoisedAlbums(Albums album);

    public void add(Albums albums);

    List<Songs> getSongsByAlbum(String album);
}
