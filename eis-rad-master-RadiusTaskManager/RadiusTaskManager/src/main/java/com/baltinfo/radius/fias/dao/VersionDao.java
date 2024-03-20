package com.baltinfo.radius.fias.dao;

import com.baltinfo.radius.fias.model.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 * DAO for {@link Version}
 *
 * @author Andrei Shymko
 * @since 11.10.2020
 */
public class VersionDao {

    private static final Logger logger = LoggerFactory.getLogger(VersionDao.class);

    /**
     * Update entity
     *
     * @param entityManager
     * @param version
     * @return updated entity
     */
    public void saveOrUpdate(EntityManager entityManager, Version version) {

        entityManager.getTransaction().begin();
        entityManager.merge(version);
        entityManager.getTransaction().commit();
    }

    /**
     * Look for entity with the latest successful database update
     *
     * @param entityManager
     * @param loadSign
     * @return Version
     */
    public Version findVersionByLatestDateAndLoadSign(EntityManager entityManager, Integer loadSign) {

        TypedQuery<Version> query = entityManager.createQuery(
                "SELECT v FROM Version v WHERE v.date = (SELECT MAX(v.date) FROM Version v WHERE v.loadSign = :loadsign)", Version.class);

        try {
            return query.setParameter("loadsign", loadSign).getSingleResult();
        } catch (NoResultException exception) {
            return null;
        }
    }

    public Version findVersionByStatus(EntityManager entityManager, Integer loadSign) {
        TypedQuery<Version> query = entityManager.createQuery(
                "SELECT v FROM Version v WHERE v.loadSign = :loadsign", Version.class);

        try {
            return query.setParameter("loadsign", loadSign).getSingleResult();
        } catch (NoResultException exception) {
            return null;
        }
    }
}