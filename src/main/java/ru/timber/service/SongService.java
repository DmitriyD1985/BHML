package ru.timber.service;

import ru.timber.model.Albums;
import ru.timber.model.Songs;
import java.util.List;


public interface SongService {
    List<Songs> listOfSongs();
}
