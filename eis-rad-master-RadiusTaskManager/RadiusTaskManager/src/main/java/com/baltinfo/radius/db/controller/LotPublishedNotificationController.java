package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.db.constants.TypeEventConstant;
import com.baltinfo.radius.db.model.VAuctionLot;
import com.baltinfo.radius.db.model.bankruptcy.VAuctionLotAll;
import com.baltinfo.radius.utils.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

public class LotPublishedNotificationController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(LotPublishedNotificationController.class);

    public List<VAuctionLot> getLotsForPublishNotification() {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return em.createQuery("select l from VAuctionLot l " +
                            " where cast(l.auctionRecepDateB as date) = cast(:date as date) " +
                            " and l.lotEtpCode is not null and length(trim(l.lotEtpCode)) > 0 " +
                            " and l.startCostRub >= 300000000 " +
                            " and not exists " +
                            " (select e from Event e where e.indActual = 1 and e.lotUnid = l.lotUnid and e.tevUnid = :tevUnid)", VAuctionLot.class)
                    .setParameter("date", new Date())
                    .setParameter("tevUnid", TypeEventConstant.LOT_PUBLISHED.getUnid())
                    .getResultList();
        } catch (Exception ex) {
            logger.error("Error getAuctionsForEndReceiptApplications", ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }
}
