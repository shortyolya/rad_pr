package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.db.model.bankruptcy.VAuctionLotAll;
import com.baltinfo.radius.utils.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Suvorina Aleksandra
 * @since 26.10.2020
 */
public class BkrCounterController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(BkrCounterController.class);

    private static final Long REVOKED_APPLICATION_STATE_ID = 594L;

    public List<VAuctionLotAll> getBkrLotsByAuctionUnid(Long auctionUnid) {
        EntityManager emBkr = null;
        try {
            emBkr = HibernateUtil.getInstance().getEntityManagerBKR();
            return (List<VAuctionLotAll>) emBkr.createQuery("SELECT l FROM VAuctionLotAll l " +
                            "WHERE l.auctionUnid = :auctionUnid")
                    .setParameter("auctionUnid", auctionUnid)
                    .getResultList();
        } catch (Exception ex) {
            logger.error("Error when trying to getBkrLotsByAuctionUnid = {}", auctionUnid, ex);
            return new ArrayList<>();
        } finally {
            closeEntityManager(emBkr);
        }
    }

    public Long getViewCountByLotUnid(Long bkrLotUnid) {
        EntityManager emBkr = null;
        try {
            emBkr = HibernateUtil.getInstance().getEntityManagerBKR();
            BigDecimal count = (BigDecimal) emBkr.createNativeQuery("select COUNT_VALUE from WEB.COUNTER " +
                    "where TCOUNT_UNID = 1 and COUNT_OBJ_UNID = :lotUnid ")
                    .setParameter("lotUnid", bkrLotUnid)
                    .getSingleResult();
            return count.longValue();
        } catch (NoResultException ex) {
            logger.warn("No lot view count found for lotUnid = {}", bkrLotUnid);
            return 0L;
        } catch (Exception ex) {
            logger.error("Error getViewCountByLotUnid by bkrLotUnid = {}", bkrLotUnid, ex);
            return null;
        } finally {
            closeEntityManager(emBkr);
        }
    }

    public Long getApplCountByLotUnid(Long bkrLotUnid) {
        EntityManager emBkr = null;
        try {
            emBkr = HibernateUtil.getInstance().getEntityManagerBKR();
            BigDecimal count = (BigDecimal) emBkr.createNativeQuery("select count(a.applicat_unid) " +
                    "from web.application_list a where a.TYPE_STATE_UNID <> :stateRevoked and a.lot_unid =  :lotUnid ")
                    .setParameter("stateRevoked", REVOKED_APPLICATION_STATE_ID)
                    .setParameter("lotUnid", bkrLotUnid)
                    .getSingleResult();
            return count.longValue();
        } catch (Exception ex) {
            logger.error("Error getApplCountByLotUnid by bkrLotUnid = {}", bkrLotUnid, ex);
            return null;
        } finally {
            closeEntityManager(emBkr);
        }
    }

}
