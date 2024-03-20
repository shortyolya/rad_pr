package com.baltinfo.radius.db.dao;

import com.baltinfo.radius.db.model.AdStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.Date;

/**
 * @author Suvorina Aleksandra
 * @since 12.03.2021
 */
public class AdStatisticsDao extends AbstractDao {

    private static Logger logger = LoggerFactory.getLogger(AdStatisticsDao.class);

    private static final Long persCode = 1L;


    public void deleteAdStatistics(EntityManager em, AdStatistics adStatistic) {
        Date now = new Date();
        adStatistic.setDateBRec(now);
        adStatistic.setPersCodeBRec(persCode);
        adStatistic.setIndActual(0);
        em.merge(adStatistic);
    }


    public void saveAdStatistics(EntityManager em, AdStatistics adStatistic) {
        Date now = new Date();
        if (adStatistic.getAdsUnid() == null) {
            adStatistic.setDateB(now);
            adStatistic.setPersCodeB(persCode);
            adStatistic.setIndActual(1);
            em.persist(adStatistic);
        } else {
            adStatistic.setDateBRec(now);
            adStatistic.setPersCodeBRec(persCode);
            em.merge(adStatistic);
        }
    }
}
