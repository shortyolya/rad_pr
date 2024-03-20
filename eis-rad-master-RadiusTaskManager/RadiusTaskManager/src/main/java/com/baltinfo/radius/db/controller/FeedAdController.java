package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.db.constants.FadStatus;
import com.baltinfo.radius.db.dao.FeedAdDao;
import com.baltinfo.radius.db.dao.FeedAdObjDao;
import com.baltinfo.radius.db.model.FeedAd;
import com.baltinfo.radius.db.model.VFeedObject;
import com.baltinfo.radius.utils.HibernateUtil;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Set;


public class FeedAdController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(FeedAdController.class);

    private final FeedAdDao feedAdDao;
    private final FeedAdObjDao feedAdObjDao;

    public FeedAdController(FeedAdDao feedAdDao, FeedAdObjDao feedAdObjDao) {
        this.feedAdDao = Objects.requireNonNull(feedAdDao, "Can't get FeedAd DAO");
        this.feedAdObjDao = Objects.requireNonNull(feedAdObjDao, "Can't get FeedAdObj DAO");
    }

    public Result createGoodAd(String xml,
                               String error,
                               Long mevUnid,
                               Long fcUnid,
                               Long fcgUnid,
                               Long subUnid,
                               HashMap<Long, String> objUnidsWithErrors,
                               List<Long> badObjUnids) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            em.getTransaction().begin();
            feedAdDao.changeAdStatus(em, fcUnid, mevUnid, fcgUnid, subUnid);
            error = (error.length() > 2000 ? error.substring(0, 2000) : error);
            FeedAd feedAd = feedAdDao.createGoodAd(em, xml, error, mevUnid, fcUnid, fcgUnid, subUnid);
            feedAdObjDao.createAdObj(em, feedAd.getFadUnid(), objUnidsWithErrors, badObjUnids);
            em.getTransaction().commit();
            return Result.ok();
        } catch (Exception ex) {
            logger.error("Error when trying to create new good ad. mevUnid = {}, fcUnid = {}, fcgUnid = {}",
                    mevUnid, fcUnid, fcgUnid, ex);
            rollbackTransaction(em);
            return Result.error(ex);
        } finally {
            closeEntityManager(em);
        }
    }

    public Result createBadAd(String fatalError, Long mevUnid, Long fcUnid, Long fcgUnid, Long subUnid, Set<Long> objUnidsSet) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            em.getTransaction().begin();
            fatalError = (fatalError.length() > 2000 ? fatalError.substring(0, 2000) : fatalError);
            FeedAd feedAd = feedAdDao.createBadAd(em, fatalError, mevUnid, fcUnid, fcgUnid, subUnid);
            feedAdObjDao.createBadAdObj(em, feedAd.getFadUnid(), objUnidsSet, fatalError);
            em.getTransaction().commit();
            return Result.ok();
        } catch (Exception ex) {
            logger.error("Error when trying to create new bad ad. mevUnid = {}, fcUnid = {}, fcgUnid = {} ",
                    mevUnid, fcUnid, fcgUnid, ex);
            rollbackTransaction(em);
            return Result.error(ex);
        } finally {
            closeEntityManager(em);
        }
    }

    public Result createBadAd(String fatalError, Long mevUnid, Long fcUnid, Long subUnid, Set<Long> objUnidsList) {
        return createBadAd(fatalError, mevUnid, fcUnid, null, subUnid, objUnidsList);
    }

    public Result createGoodAd(String xml,
                               String error,
                               Long mevUnid,
                               Long fcUnid,
                               Long subUnid,
                               HashMap<Long, String> objUnidsWithErrors,
                               List<Long> badObjUnids) {
        return createGoodAd(xml, error, mevUnid, fcUnid, null, subUnid, objUnidsWithErrors, badObjUnids);
    }

    public void createFeedAdWithCategoryError(Long mevUnid, Long subUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            List<VFeedObject> objectsWithoutCategory = em.createNamedQuery("VFeedObject.findAllWithoutCategories")
                    .setParameter("mevUnid", mevUnid)
                    .setParameter("subUnid", subUnid)
                    .getResultList();
            em.getTransaction().begin();
            em.createQuery("update FeedAd f " +
                            "set f.fadStatus = :newFadStatus " +
                            "where f.fadStatus = :oldFadStatus " +
                            "and f.indActual = 1 " +
                            "and f.mevUnid = :mevUnid " +
                            "and f.subUnid = :subUnid")
                    .setParameter("newFadStatus", FadStatus.ARCHIVE.getCode())
                    .setParameter("oldFadStatus", FadStatus.CATEGORY_ERROR.getCode())
                    .setParameter("mevUnid", mevUnid)
                    .setParameter("subUnid", subUnid)
                    .executeUpdate();
            if (!objectsWithoutCategory.isEmpty()) {
                FeedAd fad = feedAdDao.createAd(em, FadStatus.CATEGORY_ERROR.getCode(), mevUnid, subUnid);
                EntityManager finalEm = em;
                objectsWithoutCategory.forEach(vFeedObject -> feedAdObjDao
                        .createAdObjWithCategoryError(finalEm, fad.getFadUnid(), vFeedObject));
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            logger.error("Error in createFeedAdWithCategoryError method. mevUnid = {} ", mevUnid, e);
            rollbackTransaction(em);
        } finally {
            closeEntityManager(em);
        }
    }

    public Result<Void, String> deleteArchiveFeedAd(Date date) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            em.getTransaction().begin();

            List<Long> feedAds = em.createQuery("select fa.fadUnid from FeedAd fa where fa.indActual = 1 and fa.fadStatus = :fadStatus and fa.fadDate < :fadDate", Long.class)
                    .setParameter("fadStatus", FadStatus.ARCHIVE.getCode())
                    .setParameter("fadDate", date)
                    .getResultList();

            logger.info("deleteArchiveFeedAd by date = {}, count = {}", date, feedAds.size());

            for (Long fadUnid : feedAds) {
                em.createQuery("delete from FeedAdObj where fadUnid = :fadUnid")
                        .setParameter("fadUnid", fadUnid)
                        .executeUpdate();
                em.createQuery("delete from FeedAd where fadUnid = :fadUnid")
                        .setParameter("fadUnid", fadUnid)
                        .executeUpdate();
            }

            em.getTransaction().commit();
            return Result.ok();
        } catch (Exception e) {
            logger.error("Error deleteArchiveFeedAd. date = {} ", date, e);
            rollbackTransaction(em);
            return Result.error(e.getMessage());
        } finally {
            closeEntityManager(em);
        }
    }
}
