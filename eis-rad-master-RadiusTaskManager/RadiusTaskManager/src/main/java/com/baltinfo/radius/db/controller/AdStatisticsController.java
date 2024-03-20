package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.db.dao.AdStatisticsDao;
import com.baltinfo.radius.db.model.AdStatistics;
import com.baltinfo.radius.utils.HibernateUtil;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Suvorina Aleksandra
 * @since 12.03.2021
 */
public class AdStatisticsController extends AbstractController {

    private static Logger logger = LoggerFactory.getLogger(AdStatisticsController.class);

    private final AdStatisticsDao adStatisticsDao;

    public AdStatisticsController(AdStatisticsDao adStatisticsDao) {
        this.adStatisticsDao = adStatisticsDao;
    }

    public List<AdStatistics> getAdStatisticsByMevUnid(Long mevUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return em.createNamedQuery("AdStatistics.findByMevUnid", AdStatistics.class)
                    .setParameter("mevUnid", mevUnid)
                    .getResultList();
        } catch (Exception e) {
            logger.error("Can't getAdStatisticsByMevUnid by mevUnid = {}", mevUnid, e);
            return null;
        } finally {
            closeEntityManager(em);
        }
    }

    public Result<Void, String> updateAdStatistics(List<AdStatistics> adStatistics) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            em.getTransaction().begin();
            for (AdStatistics adStatistic : adStatistics) {
                if (!adStatistic.getIndActual().equals(1)) {
                    adStatisticsDao.deleteAdStatistics(em, adStatistic);
                } else {
                    adStatisticsDao.saveAdStatistics(em, adStatistic);
                }
            }
            em.getTransaction().commit();
            return Result.ok();
        } catch (Exception e) {
            logger.error("Can't updateAdStatistics", e);
            rollbackTransaction(em);
            return Result.error(e.getMessage());
        } finally {
            closeEntityManager(em);
        }
    }

    public Result<Void, String> updateAdStatisticsViews(Long objUnid, Long mevUnid, Long veiwsCount, String objIdentifier) {
        EntityManager em = null;
        AdStatistics adStatistics;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            em.getTransaction().begin();
            List<AdStatistics> adStatisticsList = em.createQuery("SELECT ad FROM AdStatistics ad " +
                            "WHERE ad.indActual = 1 " +
                            "AND ad.objUnid = :objUnid " +
                            "AND ad.mevUnid = :mevUnid ", AdStatistics.class)
                    .setParameter("objUnid", objUnid)
                    .setParameter("mevUnid", mevUnid)
                    .setMaxResults(1)
                    .getResultList();

            if (adStatisticsList.isEmpty()) {
                adStatistics = new AdStatistics();
                adStatistics.setObjUnid(objUnid);
                adStatistics.setMevUnid(mevUnid);
            } else {
                adStatistics = adStatisticsList.get(0);
            }
            adStatistics.setAdsViewsCount(veiwsCount);
            adStatisticsDao.saveAdStatistics(em, adStatistics);

            em.getTransaction().commit();
            return Result.ok();
        } catch (Exception e) {
            logger.error("Can't updateAdStatisticsViews by objUnid = {}, mevUnid = {}", objUnid, mevUnid, e);
            rollbackTransaction(em);
            return Result.error("Ошибка при сохранении статистики: " + e.getMessage()+ " по объекту " + objIdentifier);
        } finally {
            closeEntityManager(em);
        }
    }
}
