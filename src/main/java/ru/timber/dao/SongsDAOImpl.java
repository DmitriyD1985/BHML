package ru.timber.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.timber.model.Songs;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Component
@Transactional
public class SongsDAOImpl implements SongsDAO {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    @Override
    public List<Songs> listOfSongs() {
        return  entityManager.createNamedQuery("getAllSongs").getResultList();
    }

    @Override
    public Songs getSongsByName(String songsNmae) {
        Query selectquerry = entityManager.createQuery("SELECT s FROM Songs s WHERE s.songName = :songs", Songs.class);
        selectquerry.setParameter("songs", songsNmae);
        return (Songs) selectquerry.getSingleResult();
    }

    @Override
    public void insertSongs(Songs songs) {
        entityManager.persist(songs);
    }

    @Override
    public void updateSongs(Songs songs) {
        Songs updatableSong = getSongsByName(songs.getSongName());
        updatableSong.setSongName(songs.getSongName());
        entityManager.merge(updatableSong);
    }

    @Override
    public void removeSongs(Songs songs) {
        entityManager.remove(songs);
    }

}
