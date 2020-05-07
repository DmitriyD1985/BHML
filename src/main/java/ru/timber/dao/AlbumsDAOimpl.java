package ru.timber.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.timber.model.Albums;
import ru.timber.model.Songs;
import ru.timber.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
@Transactional
public class AlbumsDAOimpl implements AlbumsDAO {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    @Override
    public List<Albums> getallAlbums() {
        return entityManager.createNativeQuery("SELECT * FROM musicstore.albums").getResultList();
    }

    @Override
    public Albums getByName(String albumName) {
        Query q = entityManager.createQuery("SELECT a FROM Albums a WHERE a.albumName = :album", Albums.class);
        q.setParameter("album", albumName);
        return (Albums) q.getSingleResult();
    }

    @Override
    public List<User> getUsersChoisedAlbums(Albums album) {
        Query q =  entityManager.createQuery("SELECT a FROM Albums a WHERE a.albumName = :album", Albums.class);
        Albums a = (Albums) q.setParameter("album", album.getAlbumName()).getSingleResult();
        return a.getUsers();
    }

    @Override
    @Transactional
    public void add(Albums albums) {
        entityManager.persist(albums);
    }

    @Override
    public List<Songs> getSongsByAlbum(String album) {
        Query q =  entityManager.createQuery("SELECT a FROM Albums a WHERE a.albumName = :album", Albums.class);
        Albums a = (Albums) q.setParameter("album", album).getSingleResult();
        return a.getSongs();
    }
}
