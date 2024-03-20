package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.db.constants.ExchangeProcStatus;
import com.baltinfo.radius.db.constants.ExchangeProcs;
import com.baltinfo.radius.db.constants.TypeStateConstant;
import com.baltinfo.radius.db.dao.AuctionDao;
import com.baltinfo.radius.db.model.Auction;
import com.baltinfo.radius.db.model.Lot;
import com.baltinfo.radius.db.model.VAsvAuction;
import com.baltinfo.radius.utils.HibernateUtil;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.*;

/**
 * @author Kulikov Semyon
 * @since 16.03.2020
 */

public class AuctionController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(AuctionController.class);

    private final LotController lotController;
    private final AuctionDao auctionDao;
    private final OperJournalController operJournalController;

    public AuctionController(LotController lotController, AuctionDao auctionDao, OperJournalController operJournalController) {
        this.lotController = Objects.requireNonNull(lotController, "Can't get lot controller");
        this.auctionDao = Objects.requireNonNull(auctionDao, "Can't get auction dao");
        this.operJournalController = operJournalController;
    }

    public List<Auction> findPublishedAuctionsWithoutEtpCode(Long marketplaceUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return em.createQuery("SELECT a " +
                            "FROM Auction a " +
                            "WHERE (a.auctionEtpCode is null OR a.auctionEtpCode = '') " +
                            "AND a.auctionEtpId is not null " +
                            "AND a.indActual = 1 " +
                            "AND a.mpUnid = :mpUnid ")
                    .setParameter("mpUnid", marketplaceUnid)
                    .getResultList();
        } catch (Exception e) {
            logger.error("Error findPublishedAuctionsWithoutEtpCode", e);
            return new ArrayList<>();
        } finally {
            closeEntityManager(em);
        }
    }

    public List<Auction> getAuctionsOnEtpWithoutResults(Long marketplaceUnid, Long receiveEpUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            List<Integer> eprReceiveStatuses = Arrays.asList(ExchangeProcStatus.FINISHED.getCode(),
                    ExchangeProcStatus.RUNNING.getCode(),
                    ExchangeProcStatus.CANCELED.getCode());
            return em.createQuery("SELECT a " +
                            "FROM Auction a " +
                            "WHERE a.auctionEtpId is not null " +
                            "AND a.indActual = 1 " +
                            "AND a.mpUnid = :mpUnid " +
                            " AND a.auctionUnid not in (select eprReceive.eprSourceId " +
                            " from ExchangeProcRun eprReceive " +
                            " where " +
                            " eprReceive.indActual = 1 " +
                            " and eprReceive.eprLoadStatus in (:eprLoadStatuses) " +
                            " and eprReceive.epUnid = :receiveEpUnid) ", Auction.class)
                    .setParameter("mpUnid", marketplaceUnid)
                    .setParameter("receiveEpUnid", receiveEpUnid)
                    .setParameter("eprLoadStatuses", eprReceiveStatuses)
                    .getResultList();
        } catch (Exception e) {
            logger.error("Error getAuctionsOnEtpWithoutResults", e);
            return new ArrayList<>();
        } finally {
            closeEntityManager(em);
        }
    }

    public Auction findByAuctionUnid(Long auctionUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return (Auction) em.createNamedQuery("Auction.findByAuctionUnid")
                    .setParameter("auctionUnid", auctionUnid)
                    .getSingleResult();
        } catch (Exception ex) {
            logger.error("Error when trying to find Auction by auctionUnid = {}", auctionUnid, ex);
            return null;
        } finally {
            closeEntityManager(em);
        }
    }

    public Result<Auction, String> updateAuction(Auction auction) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            em.getTransaction().begin();
            auctionDao.updateAuction(em, auction);
            em.getTransaction().commit();
            return Result.ok();
        } catch (Exception ex) {
            rollbackTransaction(em);
            logger.error("Error when trying to update auction. auctionUnid = {}", auction.getAuctionUnid(), ex);
            return Result.error(ex.getMessage());
        } finally {
            closeEntityManager(em);
        }
    }

    public Result<Void, String> updateAuctionAndLots(Auction auction, List<Lot> lots) {
        EntityManager em = null;
        try {
            Date now = new Date();
            em = HibernateUtil.getInstance().getEntityManager();
            em.getTransaction().begin();
            auction.setDateBRec(now);
            auction.setPersCodeBRec(1L);
            em.merge(auction);
            operJournalController.createOperJournalForAuctionEdit(em, auction.getAuctionUnid(), "Обновлен номер торгов в АСВ", 1L);

            for (Lot lot : lots) {
                lot.setDateBRec(now);
                lot.setPersCodeBRec(1L);
                em.merge(lot);
                operJournalController.createOperJournalForLotEdit(em, lot.getLotUnid(), "Обновлены номера лотов АСВ", 1L);
            }

            em.getTransaction().commit();
            return Result.ok();
        } catch (Exception ex) {
            logger.error("Error when trying to update auction and lots", ex);
            rollbackTransaction(em);
            return Result.error(ex.getMessage());
        } finally {
            closeEntityManager(em);
        }
    }

    public List<VAsvAuction> getAuctionsByBaUnid(Long baUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return em.createNamedQuery("VAsvAuction.findByBaUnid", VAsvAuction.class)
                    .setParameter("baUnid", baUnid)
                    .getResultList();
        } catch (Exception ex) {
            logger.error("Error getAuctionsByBaUnid, baUnid = {}", baUnid, ex);
            return null;
        } finally {
            closeEntityManager(em);
        }
    }

    public Result<Auction, String> findAuctionByBaUnidAndStage(Long baUnid, Integer auctionStageNum, Long lotNumber) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            Auction auction = em.createQuery("SELECT a FROM Auction a, Lot l " +
                            "WHERE a.baUnid = :baUnid AND a.auctionStageNum = :auctionStageNum " +
                            "AND a.auctionUnid = l.auctionUnid AND l.lotNumber = :lotNumber " +
                            "ORDER BY a.auctionStageNum, a.auctionUnid", Auction.class)
                    .setParameter("baUnid", baUnid)
                    .setParameter("auctionStageNum", auctionStageNum)
                    .setParameter("lotNumber", lotNumber)
                    .getSingleResult();
            return Result.ok(auction);
        } catch (NoResultException ex) {
            logger.error("Auction not found by baUnid = {}, auctionStageNum = {}, lotNumber = {}",
                    baUnid, auctionStageNum, lotNumber, ex);
            return Result.error("Торги c блочным id " + baUnid + " этапа № " + auctionStageNum + " в ЕИС не найдены");
        } catch (Exception ex) {
            logger.error("Error getAuction by baUnid = {}, auctionStageNum = {}, lotNumber = {}",
                    baUnid, auctionStageNum, lotNumber, ex);
            return Result.error("Произошла ошибка при получении торгов c блочным id " + baUnid + " этапа № " + auctionStageNum);
        } finally {
            closeEntityManager(em);
        }
    }

    public Boolean isExistsLotsWithResults(Long auctionUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return em.createNamedQuery("VAsvLot.countForResults", Long.class)
                    .setParameter("auctionUnid", auctionUnid)
                    .getSingleResult() > 0;
        } catch (Exception ex) {
            logger.error("Error isExistsLotsWithResults, auctionUnid = {}", auctionUnid, ex);
            return null;
        } finally {
            closeEntityManager(em);
        }
    }

    public List<Auction> findAuctionsForSyncLinks(ExchangeProcs ep) {
        EntityManager em = null;
        List<Long> tsUnids = Arrays.asList(TypeStateConstant.PREPARE_FOR_AUCTION.getId(), TypeStateConstant.TRADE_IN_PROCESS.getId());
        String lotLinkQuery = ep.getUnid() == ExchangeProcs.SEND_AUCTION_TO_RAD_HOLDING.getUnid()
                ? " (l.lotRadHoldingSite is null or length(trim(l.lotRadHoldingSite)) = 0) "
                : " (l.lotRadSite is null or length(trim(l.lotRadSite)) = 0) ";
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return em.createQuery("SELECT a " +
                            "FROM Auction a " +
                            "WHERE a.indActual = 1 " +
                            "AND a.tsUnid in (:tsUnids) " +
                            "AND (a.auctionDateB > :now or a.auctionDateE > :now) " +
                            "AND a.auctionUnid in (select epr.eprSourceId " +
                            "from ExchangeProcRun  epr " +
                            "where epr.indActual = 1 " +
                            "and epr.eprLoadStatus = :eprLoadStatus " +
                            "and epr.epUnid = :epUnid) " +
                            "AND a.auctionUnid in (select l.auctionUnid " +
                            "from Lot l " +
                            "where l.indActual = 1 " +
                            "and " + lotLinkQuery + ")", Auction.class)
                    .setParameter("tsUnids", tsUnids)
                    .setParameter("eprLoadStatus", ExchangeProcStatus.FINISHED.getCode())
                    .setParameter("epUnid", ep.getUnid())
                    .setParameter("now", new Date())
                    .getResultList();
        } catch (Exception ex) {
            logger.error("Error when trying to find Auctions by ep = {}", ep, ex);
            return new ArrayList<>();
        } finally {
            closeEntityManager(em);
        }
    }
}
