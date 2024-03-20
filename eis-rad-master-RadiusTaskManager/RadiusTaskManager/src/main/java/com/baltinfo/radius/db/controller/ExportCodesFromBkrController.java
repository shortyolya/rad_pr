package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.db.model.bankruptcy.VAuctionBkr;
import com.baltinfo.radius.db.model.bankruptcy.VAuctionLotAll;
import com.baltinfo.radius.utils.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kulikov Semyon
 * @since 16.03.2020
 */

public class ExportCodesFromBkrController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(ExportCodesFromBkrController.class);

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

    public VAuctionBkr getAuctionFromBkr(Long auctionUnid) {
        EntityManager emBkr = null;
        try {
            emBkr = HibernateUtil.getInstance().getEntityManagerBKR();
            return emBkr.find(VAuctionBkr.class, auctionUnid);
        } catch (Exception ex) {
            logger.error("Error when trying to getAuctionFromBkr. auctionUnid = {}", auctionUnid, ex);
            return null;
        } finally {
            closeEntityManager(emBkr);
        }
    }
}
