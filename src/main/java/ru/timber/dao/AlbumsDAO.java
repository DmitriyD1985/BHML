package ru.timber.dao;

import ru.timber.model.Albums;
import ru.timber.model.Songs;
import ru.timber.model.User;
import java.util.List;


public interface AlbumsDAO {
    Albums getByName(String albumName);
    List<User> getUsersChoisedAlbums(Albums album);
    List<Songs> getSongsByAlbum(String album);
    public void insertAlbum(Albums album);
    public void updateAlbum(Albums album);
    public void delete(Albums album);
}
