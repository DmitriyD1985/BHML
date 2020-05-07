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
public class SongsDAOImpl implements SongsDAO {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    @Override
    public List<Songs> listOfSongs() {
        return  entityManager.createNamedQuery("getAllSongs").getResultList();
    }

}
