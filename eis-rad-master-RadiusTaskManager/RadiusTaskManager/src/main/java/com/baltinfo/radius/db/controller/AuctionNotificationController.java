package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.db.constants.TypeEventConstant;
import com.baltinfo.radius.db.dao.EventDao;
import com.baltinfo.radius.db.dao.MessageEmailDao;
import com.baltinfo.radius.db.dao.NotificationDao;
import com.baltinfo.radius.db.model.Event;
import com.baltinfo.radius.db.model.MessageEmail;
import com.baltinfo.radius.db.model.NotifSettings;
import com.baltinfo.radius.db.model.Notification;
import com.baltinfo.radius.db.model.ParticipantAgent;
import com.baltinfo.radius.db.model.VAuctionNotification;
import com.baltinfo.radius.db.model.VRsNotification;
import com.baltinfo.radius.utils.DateUtils;
import com.baltinfo.radius.utils.HibernateUtil;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Suvorina Aleksandra
 * @since 22.11.2018
 */
public class AuctionNotificationController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(AuctionNotificationController.class);

    private final EventDao eventDao;
    private final NotificationDao notificationDao;
    private final MessageEmailDao messageEmailDao;

    public AuctionNotificationController(EventDao eventDao, NotificationDao notificationDao, MessageEmailDao messageEmailDao) {
        this.eventDao = eventDao;
        this.notificationDao = notificationDao;
        this.messageEmailDao = messageEmailDao;
    }

    public List<VAuctionNotification> getAuctionsForEndReceiptApplications() {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return em.createQuery("select a from VAuctionNotification a " +
                            " where a.auctionRecepDateE <= :date " +
                            " and a.typeAuctionCode <> 4 " +
                            " and a.lsUnid in (1, 2, 3, 5, 6, 7, 8) " +
                            " and a.tsUnid = 12 " +
                            " and not exists " +
                            " (select e from Event e where e.indActual = 1 and e.auctionUnid = a.auctionUnid and e.tevUnid = :tevUnid)")
                    .setParameter("date", new Date())
                    .setParameter("tevUnid", TypeEventConstant.END_RECEIPT_APPLICATIONS.getUnid())
                    .getResultList();
        } catch (Exception ex) {
            logger.error("Error getAuctionsForEndReceiptApplications", ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    public List<VAuctionNotification> getAuctionsBeforeStartAuctionDate() {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return em.createQuery("select a from VAuctionNotification a " +
                            " where a.auctionDateB <= :date " +
                            " and a.typeAuctionCode <> 4 " +
                            " and a.lsUnid in (1, 2, 3, 5, 6, 7, 8) " +
                            " and a.tsUnid = 12 " +
                            " and not exists " +
                            " (select e from Event e where e.indActual = 1 and e.auctionUnid = a.auctionUnid and e.tevUnid = :tevUnid)")
                    .setParameter("date", DateUtils.plusDay(new Date(), 1))
                    .setParameter("tevUnid", TypeEventConstant.START_AUCTION.getUnid())
                    .getResultList();
        } catch (Exception ex) {
            logger.error("Error getAuctionsBeforeStartAuctionDate", ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    public List<VRsNotification> getRedSchedForEndPeriod() {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return em.createQuery("select rs from VRsNotification rs " +
                            " where rs.redSchedApplDateE <= :date " +
                            " and rs.typeAuctionCode = 4 " +
                            " and rs.lsUnid in (1, 2, 3, 5, 6, 7, 8) " +
                            " and rs.tsUnid = 12 " +
                            " and not exists " +
                            " (select e " +
                            "    from Event e, ReductionSchedule rs1 " +
                            "   where e.indActual = 1 and e.redSchedUnid = rs1.redSchedUnid " +
                            "     and rs1.redSchedDateB = rs.redSchedDateB " +
                            "     and rs1.lotUnid.auctionUnid = rs.auctionUnid " +
                            "     and e.tevUnid = :tevUnid)", VRsNotification.class)
                    .setParameter("date", new Date())
                    .setParameter("tevUnid", TypeEventConstant.END_APPL_REDUCTION_PERIOD.getUnid())
                    .getResultList();
        } catch (Exception ex) {
            logger.error("Error getRedSchedForEndPeriod", ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    public List<ParticipantAgent> getCuratorsByAuction(Long auctionUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return em.createQuery("select distinct pa from ParticipantAgent pa, Auction a, Lot l, ObjectJPA obj " +
                            " where a.auctionUnid = :auctionUnid " +
                            " and a.auctionUnid = l.auctionUnid " +
                            " and l.objUnid = obj.objUnid " +
                            " and obj.parPaUnid = pa.paUnid " +
                            " and l.indActual = 1 " +
                            " and obj.indActual = 1")
                    .setParameter("auctionUnid", auctionUnid)
                    .getResultList();
        } catch (Exception ex) {
            logger.error("Error getCuratorsByAuction auctionUnid = {}", auctionUnid, ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    public Result<Void, String> createEventWithNotifications(Event event, List<Notification> notifications, List<MessageEmail> emails) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            em.getTransaction().begin();
            eventDao.createEvent(em, event);
            for (Notification notification : notifications) {
                notification.setEventUnid(event);
                notificationDao.createNotification(em, notification);
            }

            for (MessageEmail email : emails) {
                messageEmailDao.createMessageEmail(em, email);
            }
            em.getTransaction().commit();
            return Result.ok();
        } catch (Exception ex) {
            logger.error("Error createEventWithNotifications", ex);
            rollbackTransaction(em);
            return Result.error("Error create event with notifications. Error: " + ex.getMessage());
        } finally {
            closeEntityManager(em);
        }
    }
}
