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
    public Albums getByName(String albumName) {
        Query selectquerry = entityManager.createQuery("SELECT a FROM Albums a WHERE a.albumName = :album", Albums.class);
        selectquerry.setParameter("album", albumName);
        return (Albums) selectquerry.getSingleResult();
    }

    @Override
    public List<User> getUsersChoisedAlbums(Albums album) {
        Query selectquerry =  entityManager.createQuery("SELECT a FROM Albums a WHERE a.albumName = :album", Albums.class);
        Albums albums = (Albums) selectquerry.setParameter("album", album.getAlbumName()).getSingleResult();
        return albums.getUsers();
    }

    @Override
    public List<Songs> getSongsByAlbum(String album) {
        Query selectquerry =  entityManager.createQuery("SELECT a FROM Albums a WHERE a.albumName = :album", Albums.class);
        Albums albums = (Albums) selectquerry.setParameter("album", album).getSingleResult();
        return albums.getSongs();
    }

    @Override
    public void insertAlbum(Albums album) {
        entityManager.merge(album);
    }

    @Override
    public void updateAlbum(Albums album) {
        Albums updatableAlbum = getByName(album.getAlbumName());
        updatableAlbum.setAlbumName(album.getAlbumName());
        entityManager.merge(updatableAlbum);
    }

    @Override
    public void delete(Albums albums) {
        Query selectquerry = entityManager.createQuery("SELECT a FROM Albums a WHERE a.albumName = :albumName", Albums.class);
        selectquerry.setParameter("albumName", albums.getAlbumName());
        Albums entity = (Albums) selectquerry.getSingleResult();
            entity.getUsers().clear();
            entityManager.remove(entity);
    }
}
