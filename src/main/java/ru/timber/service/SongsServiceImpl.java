package ru.timber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.timber.dao.SongsDAO;
import ru.timber.model.Albums;
import ru.timber.model.Songs;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SongsServiceImpl implements SongService {

    @Autowired
    SongsDAO songsDAO;

    @Override
    public List<Songs> listOfSongs() {
        return songsDAO.listOfSongs();
    }
}

