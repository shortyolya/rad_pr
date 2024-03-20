package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.db.model.Picture;
import com.baltinfo.radius.utils.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Suvorina Aleksandra
 * @since 09.09.2020
 */
public class PictureController extends AbstractController {

    private static Logger logger = LoggerFactory.getLogger(RewardController.class);

    public List<Picture> getPicturesForExportToFtp(long objUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            Query query = em.createQuery("select a from Picture a where a.indActual = 1 and a.dfUnid is not null and a.objUnid = :objUnid and a.pictIndExportFeed = 1 order by a.pictNum");
            query.setParameter("objUnid", objUnid);
            return query.getResultList();
        } catch (Throwable ex) {
            logger.error("Error when getting Pictures by objUnid = {}", objUnid, ex);
        } finally {
            closeEntityManager(em);
        }
        return new ArrayList<>();
    }

    public List<Picture> getPicturesForExportToFtpWithLimit(long objUnid, int limit) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            Query query = em.createQuery("select a from Picture a where a.indActual = 1 and a.dfUnid is not null and a.objUnid = :objUnid and a.pictIndExportFeed = 1 order by a.pictNum");
            query.setParameter("objUnid", objUnid);
            query.setMaxResults(limit);
            return query.getResultList();
        } catch (Throwable ex) {
            logger.error("Error when getting Pictures by objUnid = {}", objUnid, ex);
        } finally {
            closeEntityManager(em);
        }
        return new ArrayList<>();
    }

    public List<Picture> getPicturesWithoutPreview() {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();

           return em.createQuery("select pic from Picture pic where pic.indActual = 1 and pic.dfPreviewUnid is null and pic.dfUnid is not null").
                    setHint("javax.persistence.fetchgraph", em.getEntityGraph("graph.Picture.all"))
                   .getResultList();
        } catch (Throwable ex) {
            logger.error(ex.getMessage(), ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }
}
