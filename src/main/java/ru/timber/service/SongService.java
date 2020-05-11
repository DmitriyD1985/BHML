package ru.timber.service;

import ru.timber.model.Songs;
import java.util.List;


public interface SongService {
    List<Songs> listOfSongs();
    public Songs getSongsByName(String songsNmae);
    public void insertSongs(Songs songs);
    public void updateSongs(Songs songs);
    public void removeSongs(Songs songs);
}
