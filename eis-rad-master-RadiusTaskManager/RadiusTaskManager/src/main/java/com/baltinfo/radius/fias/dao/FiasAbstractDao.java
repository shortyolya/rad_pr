package com.baltinfo.radius.fias.dao;

import com.baltinfo.radius.fias.model.FiasModel;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.baltinfo.radius.fias.utils.FiasConstants.BATCH_SIZE;
import static com.baltinfo.radius.fias.utils.FiasConstants.FIELD_ID;

/**
 * Abstract DAO class for saving and updating FIAS GAR
 *
 * @author Andrei Shymko
 * @since 13.10.2020
 */
public abstract class FiasAbstractDao<E extends FiasModel> {

    private static final Logger logger = LoggerFactory.getLogger(FiasAbstractDao.class);

    private final Class<E> clazz;

    protected FiasAbstractDao(Class<E> clazz) {
        this.clazz = clazz;
    }

    /**
     * Save new entities to database using Hibernate Batch
     *
     * @param entityManager
     * @param entityList    for saving to database
     */
    public void saveAll(EntityManager entityManager, List<E> entityList) {

        int i = 1;
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        for (E entity : entityList) {

            if (i == BATCH_SIZE) {
                entityManager.flush();
                entityManager.clear();
            }
            entityManager.persist(entity);
            i++;
        }
        transaction.commit();
    }

    /**
     * Save or update entities to database using Hibernate Batch
     *
     * @param entityManager
     * @param entityList    for saving to database
     */
    public void saveOrUpdateAll(EntityManager entityManager, List<E> entityList) {

        Session session = entityManager.unwrap(Session.class);
        session.getTransaction().begin();

        List<Number> idList = entityList.stream()
                .map(FiasModel::getId)
                .collect(Collectors.toList());

        List<E> existList = new ArrayList<>();
        if (!idList.isEmpty()) {
            existList = session.createCriteria
                    (clazz).add(
                    Restrictions.in(FIELD_ID, idList)
            ).list();
        }

        session.clear();

        List<E> updateList = entityList;
        List<E> saveList = new ArrayList<>(entityList);

        updateList.retainAll(existList);
        saveList.removeAll(existList);

        int i = 1;
        for (E entity : updateList) {

            if (i == BATCH_SIZE) {
                session.flush();
                session.clear();
            }
            session.update(entity);
            i++;
        }

        for (E entity : saveList) {

            if (i == BATCH_SIZE) {
                session.flush();
                session.clear();
            }
            session.persist(entity);
            i++;
        }
        session.getTransaction().commit();
    }
}