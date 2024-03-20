package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.db.model.CategoryMatcher;
import com.baltinfo.radius.db.model.FeedCategory;
import com.baltinfo.radius.db.model.FeedCategoryGroup;
import com.baltinfo.radius.utils.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kulikov Semyon
 * @since 30.01.2020
 */

public class CategoryMatcherController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryMatcherController.class);

    public CategoryMatcher findByCategoryUnidAndMevUnid(Long scUnid, Long mevUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return (CategoryMatcher) em.createNamedQuery("CategoryMatcher.findByCategoryUnidAndMevUnid")
                    .setParameter("scUnid", scUnid)
                    .setParameter("mevUnid", mevUnid)
                    .getSingleResult();
        } catch (NoResultException e) {
            logger.warn("Nothing was found when getting Category by scUnid = {}, mevUnid = {}", scUnid, mevUnid, e);
        } catch (Throwable ex) {
            logger.error("Error when getting Category by scUnid = {}, mevUnid = {}", scUnid, mevUnid, ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    public List<FeedCategory> findByMevUnid(Long mevUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return em.createQuery("select f from FeedCategory f where f.indActual = 1 " +
                    "and f.mevUnid = :mevUnid " +
                    "and f.fcIndExport = true")
                    .setParameter("mevUnid", mevUnid)
                    .getResultList();
        } catch (NoResultException e) {
            logger.warn("Nothing was found when getting Category by mevUnid = {}", mevUnid, e);
        } catch (Throwable ex) {
            logger.error("Error when getting Category by mevUnid = {}", mevUnid, ex);
        } finally {
            closeEntityManager(em);
        }
        return new ArrayList<>();
    }

    public List<FeedCategoryGroup> getActualGroups() {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return em.createQuery("select fcg from FeedCategoryGroup fcg " +
                    "where fcg.indActual = 1")
                    .getResultList();
        } catch (NoResultException e) {
            logger.warn("Nothing was found when getting category groups");
        } catch (Throwable ex) {
            logger.error("Error when getting category groups", ex);
        } finally {
            closeEntityManager(em);
        }
        return new ArrayList<>();
    }
}
