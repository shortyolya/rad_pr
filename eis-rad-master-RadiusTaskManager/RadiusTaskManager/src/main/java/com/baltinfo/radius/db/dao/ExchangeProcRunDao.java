package com.baltinfo.radius.db.dao;

import com.baltinfo.radius.db.constants.ExchangeProcStatus;
import com.baltinfo.radius.db.constants.ExchangeProcs;
import com.baltinfo.radius.db.constants.LotStatus;
import com.baltinfo.radius.db.model.ExchangeProcRun;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * DAO для работы с журналом запуска процедур информационного обмена
 *
 * @author Suvorina Aleksandra
 * @since 23.08.2018
 */
public class ExchangeProcRunDao extends AbstractDao {

    private static Logger logger = LoggerFactory.getLogger(ExchangeProcRunDao.class);

    /**
     * Получение первой записи журнала процедур информационного обмена со статусом "Идет"
     *
     * @param em           экземпляр {@link EntityManager}
     * @param exchangeProc процедура информационного обмена
     * @return список журнала процедур информационного обмена {@link ExchangeProcRun}
     */
    public Optional<ExchangeProcRun> getFirstRunningExchangeProcRun(EntityManager em, ExchangeProcs exchangeProc) {
        logger.debug("start method: getRunningExchangeProcRun() at {}", Instant.now());
        try {
            ExchangeProcRun exchangeProcRun = (ExchangeProcRun) em.createNamedQuery("ExchangeProcRun.findByStatus")
                    .setParameter("eprLoadStatus", ExchangeProcStatus.RUNNING.getCode())
                    .setParameter("epUnid", exchangeProc.getUnid())
                    .setMaxResults(1)
                    .getSingleResult();
            return Optional.of(exchangeProcRun);
        } catch (NoResultException ex) {
            return Optional.empty();
        } catch (Exception ex) {
            logger.error("Can't get first running ExchangeProcRun by ExchangeProc = {}", exchangeProc, ex);
            return Optional.empty();
        } finally {
            logger.debug("finish method: getRunningExchangeProcRun() at {}", Instant.now());
        }
    }

    /**
     * Получение первой записи журнала процедур информационного обмена со статусом "Не запускалась"
     *
     * @param em           экземпляр {@link EntityManager}
     * @param exchangeProc процедура информационного обмена
     * @return список журнала процедур информационного обмена {@link ExchangeProcRun}
     */
    public Optional<ExchangeProcRun> getFirstNotStartedExchangeProcRun(EntityManager em, ExchangeProcs exchangeProc) {
        logger.debug("start method: getRunningExchangeProcRun() at {}", Instant.now());
        try {
            ExchangeProcRun exchangeProcRun = (ExchangeProcRun) em.createNamedQuery("ExchangeProcRun.findByStatus")
                    .setParameter("eprLoadStatus", ExchangeProcStatus.NOT_STARTED.getCode())
                    .setParameter("epUnid", exchangeProc.getUnid())
                    .setMaxResults(1)
                    .getSingleResult();
            return Optional.of(exchangeProcRun);
        } catch (NoResultException ex) {
            return Optional.empty();
        } catch (Exception ex) {
            logger.error("Can't get first running ExchangeProcRun by ExchangeProc = {}", exchangeProc, ex);
            return Optional.empty();
        } finally {
            logger.debug("finish method: getRunningExchangeProcRun() at {}", Instant.now());
        }
    }

    /**
     * Получение записей журнала процедур информационного обмена со статусом "Идет"
     *
     * @param em           экземпляр {@link EntityManager}
     * @param exchangeProc процедура информационного обмена
     * @return список журнала процедур информационного обмена {@link ExchangeProcRun}
     */
    public Optional<List<ExchangeProcRun>> getRunningExchangeProcRun(EntityManager em, ExchangeProcs exchangeProc) {
        logger.debug("start method: getRunningExchangeProcRun() at {}", Instant.now());
        try {
            List<ExchangeProcRun> exchangeProcRun = (List<ExchangeProcRun>) em.createNamedQuery("ExchangeProcRun.findByStatus")
                    .setParameter("eprLoadStatus", ExchangeProcStatus.RUNNING.getCode())
                    .setParameter("epUnid", exchangeProc.getUnid())
                    .getResultList();
            return Optional.ofNullable(exchangeProcRun);
        } catch (Exception ex) {
            logger.error("Can't get running ExchangeProcRun by ExchangeProc = {}", exchangeProc, ex);
            return Optional.empty();
        } finally {
            logger.debug("finish method: getRunningExchangeProcRun() at {}", Instant.now());
        }
    }

    public ExchangeProcRun getExchangeProcRun(EntityManager em, long eprUnid) {
        return em.find(ExchangeProcRun.class, eprUnid);
    }

    public ExchangeProcRun saveExchangeProcRun(EntityManager em, ExchangeProcRun exchangeProcRun) {
        if (exchangeProcRun.getEprUnid() == null) {
            exchangeProcRun.setDateB(new Date());
            exchangeProcRun.setPersCodeB(1L);
            exchangeProcRun.setIndActual(1);
            em.persist(exchangeProcRun);
        } else {
            exchangeProcRun.setDateBRec(new Date());
            exchangeProcRun.setPersCodeBRec(1L);
            em.merge(exchangeProcRun);
        }
        return exchangeProcRun;
    }

    public List<ExchangeProcRun> getEPRSendWithoutFinishedReceive(EntityManager em, Long sendEpUnid, Long receiveEpUnid) {
        List<Integer> eprReceiveStatuses = Arrays.asList(ExchangeProcStatus.FINISHED.getCode(),
                ExchangeProcStatus.RUNNING.getCode(),
                ExchangeProcStatus.CANCELED.getCode());
        Query q = em.createQuery("select eprSend from ExchangeProcRun eprSend " +
                        " where" +
                        " eprSend.indActual = 1 " +
                        " and eprSend.epUnid = :sendEpUnid " +
                        " and eprSend.eprLoadStatus = :eprLoadStatus " +
                        " and eprSend.eprSourceId not in " +
                        " (select eprReceive.eprSourceId " +
                        " from ExchangeProcRun eprReceive " +
                        " where " +
                        " eprReceive.indActual = 1 " +
                        " and eprReceive.eprLoadStatus in (:eprLoadStatuses) " +
                        " and eprReceive.epUnid = :receiveEpUnid) ")
                .setParameter("sendEpUnid", sendEpUnid)
                .setParameter("receiveEpUnid", receiveEpUnid)
                .setParameter("eprLoadStatus", ExchangeProcStatus.FINISHED.getCode())
                .setParameter("eprLoadStatuses", eprReceiveStatuses);
        return q.getResultList();
    }

    public ExchangeProcRun getNotStartErrorExchangeProcRun(EntityManager em, long epUnid, long eprReceiverId) {
        List<Integer> eprStatuses = new ArrayList<Integer>() {{
            add(ExchangeProcStatus.ERROR_START.getCode());
            add(ExchangeProcStatus.CANCELED.getCode());
        }};
        return (ExchangeProcRun) em.createQuery(
                        "select a "
                                + "from ExchangeProcRun a "
                                + "where a.epUnid = :epUnid "
                                + "and a.eprReceiverId = :eprReceiverId "
                                + "and a.indActual = 1 "
                                + "and a.eprLoadStatus not in :eprStatuses "
                                + " order by a.eprRunDate desc")
                .setParameter("epUnid", epUnid)
                .setParameter("eprReceiverId", eprReceiverId)
                .setParameter("eprStatuses", eprStatuses)
                .setMaxResults(1)
                .getSingleResult();
    }


    public Optional<List<ExchangeProcRun>> getFinishedExchangeProcRun(EntityManager em, long epUnid) {
        try {
            List<ExchangeProcRun> exchangeProcRun = (List<ExchangeProcRun>) em.createQuery(
                            "select e from ExchangeProcRun e "
                                    + "where e.epUnid = :epUnid "
                                    + "and e.eprLoadStatus = :eprLoadStatus "
                                    + "and e.indActual = 1 "
                                    + "order by e.eprRunDate desc")
                    .setParameter("epUnid", epUnid)
                    .setParameter("eprLoadStatus", ExchangeProcStatus.FINISHED.getCode())
                    .getResultList();
            return Optional.ofNullable(exchangeProcRun);
        } catch (Exception ex) {
            logger.error("Can't get running getFinishedExchangeProcRun by epUnid = {}", epUnid, ex);
            return Optional.empty();
        }
    }


    public List<ExchangeProcRun> getSuspendedBkrAuctionExchangeProcRuns(EntityManager em) {
        try {
            List<ExchangeProcRun> exchangeProcRun = em.createQuery(
                            "select e from ExchangeProcRun e "
                                    + "where e.epUnid = :epUnid "
                                    + "and e.eprLoadStatus = :eprLoadStatus "
                                    + "and e.indActual = 1 and "
                                     + " exists (select l.lotUnid from Lot l where l.auctionUnid = e.eprSourceId and l.indActual = 1 and l.lotStatus = :lotSuspendedStatus ) "
                                    + "order by e.eprRunDate desc", ExchangeProcRun.class)
                    .setParameter("epUnid", ExchangeProcs.CHECK_SUSPENDED_AUCTION.getUnid())
                    .setParameter("eprLoadStatus", ExchangeProcStatus.FINISHED.getCode())
                    .setParameter("lotSuspendedStatus", LotStatus.TRADE_SUSPENDED.getCode())
                    .getResultList();
            return exchangeProcRun;
        } catch (Exception ex) {
            logger.error("Can't get running getSuspendedBkrAuctionExchangeProcRuns", ex);
            return new ArrayList<>();
        }
    }

    public ExchangeProcRun getFinishedExchangeProcRun(EntityManager em, long epUnid, long eprSourceId) {

        try {
            return (ExchangeProcRun) em.createQuery(
                            "select a "
                                    + "from ExchangeProcRun a "
                                    + "where a.epUnid = :epUnid "
                                    + "and a.eprSourceId = :eprSourceId "
                                    + "and a.indActual = 1 "
                                    + "and a.eprLoadStatus = :eprStatus "
                                    + " order by a.eprRunDate desc")
                    .setParameter("epUnid", epUnid)
                    .setParameter("eprSourceId", eprSourceId)
                    .setParameter("eprStatus", ExchangeProcStatus.FINISHED.getCode())
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (javax.persistence.NoResultException ignored) {
        } catch (Throwable ex) {
            logger.error("Error getFinishedExchangeProcRun by epUnid = {}, eprSourceId = {}", epUnid, eprSourceId, ex);
        }
        return null;
    }
}
