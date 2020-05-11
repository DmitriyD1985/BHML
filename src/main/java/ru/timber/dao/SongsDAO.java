package ru.timber.dao;

import ru.timber.model.Songs;
import java.util.List;



public interface SongsDAO {
    List<Songs> listOfSongs();
    public Songs getSongsByName(String songsNmae);
    public void insertSongs(Songs songs);
    public void updateSongs(Songs songs);
    public void removeSongs(Songs songs);
}
