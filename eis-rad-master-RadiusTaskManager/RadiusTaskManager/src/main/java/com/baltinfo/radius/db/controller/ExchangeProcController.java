package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.db.constants.ExchangeProcStatus;
import com.baltinfo.radius.db.constants.ExchangeProcs;
import com.baltinfo.radius.db.constants.RunningDetailsStatus;
import com.baltinfo.radius.db.dao.ExchangeProcDao;
import com.baltinfo.radius.db.dao.ExchangeProcRunDao;
import com.baltinfo.radius.db.dao.RunningDetailsDao;
import com.baltinfo.radius.db.model.ExchangeProc;
import com.baltinfo.radius.db.model.ExchangeProcRun;
import com.baltinfo.radius.db.model.RunningDetails;
import com.baltinfo.radius.utils.HibernateUtil;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Suvorina Aleksandra
 * @since 23.08.2018
 */
public class ExchangeProcController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(ExchangeProcController.class);

    private final ExchangeProcRunDao exchangeProcRunDao;
    private final RunningDetailsDao runningDetailsDao;
    private final ExchangeProcDao exchangeProcDao;

    public ExchangeProcController(ExchangeProcRunDao exchangeProcRunDao, RunningDetailsDao runningDetailsDao,
                                  ExchangeProcDao exchangeProcDao) {
        this.exchangeProcRunDao = exchangeProcRunDao;
        this.runningDetailsDao = runningDetailsDao;
        this.exchangeProcDao = exchangeProcDao;
    }

    public Optional<ExchangeProcRun> getFirstRunningExchangeProcRun(ExchangeProcs exchangeProc) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return exchangeProcRunDao.getFirstRunningExchangeProcRun(em, exchangeProc);
        } catch (Exception ex) {
            logger.error("Error when get first running ExchangeProcRun by exchangeProc = {}", exchangeProc, ex);
        } finally {
            closeEntityManager(em);
        }
        return Optional.empty();
    }

    public Optional<ExchangeProcRun> getFirstNotStartedExchangeProcRun(ExchangeProcs exchangeProc) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return exchangeProcRunDao.getFirstNotStartedExchangeProcRun(em, exchangeProc);
        } catch (Exception ex) {
            logger.error("Error when get first not started ExchangeProcRun by exchangeProc = {}", exchangeProc, ex);
        } finally {
            closeEntityManager(em);
        }
        return Optional.empty();
    }

    public Optional<List<ExchangeProcRun>> getRunningExchangeProcRun(ExchangeProcs exchangeProc) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return exchangeProcRunDao.getRunningExchangeProcRun(em, exchangeProc);
        } catch (Exception ex) {
            logger.error("Error when get running ExchangeProcRun by exchangeProc = {}", exchangeProc, ex);
        } finally {
            closeEntityManager(em);
        }
        return Optional.empty();
    }

    public ExchangeProcRun getExchangeProcRun(long eprUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return exchangeProcRunDao.getExchangeProcRun(em, eprUnid);
        } catch (Exception ex) {
            logger.error("Error when get ExchangeProcRun by eprUnid = {}", eprUnid, ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    public List<RunningDetails> getNotSentRunningDetails(Long eprUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return runningDetailsDao.getRunningDetailsByStatus(em, eprUnid, RunningDetailsStatus.NOT_SENT);
        } catch (Exception ex) {
            logger.error("Error when get ExchangeProcRun by eprUnid = {}", eprUnid, ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    public List<RunningDetails> getRunningDetails(Long eprUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return runningDetailsDao.getRunningDetails(em, eprUnid);
        } catch (Exception ex) {
            logger.error("Error when get ExchangeProcRun by eprUnid = {}", eprUnid, ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    public RunningDetails updateRunningDetails(RunningDetails runningDetails) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            em.getTransaction().begin();
            RunningDetails rd = runningDetailsDao.saveRunningDetails(em, runningDetails);
            em.getTransaction().commit();
            return rd;
        } catch (Exception ex) {
            rollbackTransaction(em);
            logger.error("Error when update RunningDetails. rdUnid = {}", runningDetails.getRdUnid(), ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    public ExchangeProc getExchangeProc(Long epUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return exchangeProcDao.getExchangeProc(em, epUnid);
        } catch (Exception ex) {
            logger.error("Error when get start ExchangeProc by epUnid = {}", epUnid, ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    public ExchangeProcRun updateExchangeProcRun(ExchangeProcRun epr) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            em.getTransaction().begin();
            ExchangeProcRun updatedEpr = exchangeProcRunDao.saveExchangeProcRun(em, epr);
            em.getTransaction().commit();
            return updatedEpr;
        } catch (Exception ex) {
            rollbackTransaction(em);
            logger.error("Error when update ExchangeProcRun. eprUnid = {}", epr.getEprUnid(), ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    public List<ExchangeProcRun> getEPRSendWithoutFinishedReceive(Long sendEpUnid, Long receiveEpUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return exchangeProcRunDao.getEPRSendWithoutFinishedReceive(em, sendEpUnid, receiveEpUnid);
        } catch (Exception ex) {
            logger.error("Error getBkrEPRWithNotFinishedAuctions", ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    public Result<Void, String> saveExchangeProcRunWithDetails(ExchangeProcRun epr, List<RunningDetails> details) {
        EntityManager emMain = null;
        try {
            emMain = HibernateUtil.getInstance().getEntityManager();
            emMain.getTransaction().begin();
            epr = exchangeProcRunDao.saveExchangeProcRun(emMain, epr);
            for (RunningDetails rd : details) {
                rd.setEprUnid(epr.getEprUnid());
                runningDetailsDao.saveRunningDetails(emMain, rd);
            }
            emMain.getTransaction().commit();
            return Result.ok();
        } catch (Exception e) {
            logger.error("Error saveExchangeProcRunWithDetails", e);
            rollbackTransaction(emMain);
            return Result.error(e.getMessage());
        } finally {
            closeEntityManager(emMain);
        }
    }

    public boolean saveExchangeProcRun(ExchangeProcRun epr) {
        EntityManager emMain = null;
        try {
            emMain = HibernateUtil.getInstance().getEntityManager();
            emMain.getTransaction().begin();
            exchangeProcRunDao.saveExchangeProcRun(emMain, epr);
            emMain.getTransaction().commit();
            return true;
        } catch (Exception e) {
            logger.error("Error saveExchangeProcRun", e);
            rollbackTransaction(emMain);
        } finally {
            closeEntityManager(emMain);
        }
        return false;
    }

    public ExchangeProcRun getNotStartErrorExchangeProcRun(long epUnid, long eprReceiverId) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return exchangeProcRunDao.getNotStartErrorExchangeProcRun(em, epUnid, eprReceiverId);
        } catch (javax.persistence.NoResultException ex) {
        } catch (Throwable ex) {
            logger.error("Error getNotStartErrorExchangeProcRun by epUnid = {}, eprReceiverId = {}", epUnid, eprReceiverId, ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    public boolean resumeExchange(ExchangeProcRun epr) {
        if (epr == null) {
            return false;
        }

        EntityManager em = null;
        Long eprUnid = epr.getEprUnid();
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            em.getTransaction().begin();

            epr.setEprLoadStatus(ExchangeProcStatus.RUNNING.getCode());
            exchangeProcRunDao.saveExchangeProcRun(em, epr);

            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            logger.error("Error resumeExchange by eprUnid = {}", eprUnid, e);
            rollbackTransaction(em);
        } finally {
            closeEntityManager(em);
        }
        return false;
    }

    public Result<ExchangeProcRun, String> createExchangeProcRun(ExchangeProcs procedure,
                                                                 ExchangeProcStatus status,
                                                                 String error) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            em.getTransaction().begin();

            ExchangeProcRun eprToAdd = new ExchangeProcRun();
            eprToAdd.setEpUnid(procedure.getUnid());
            eprToAdd.setEprPaUnid(1L);
            eprToAdd.setEprRunDate(new Date());
            eprToAdd.setEprLoadStatus(status.getCode());
            if (error != null && !error.isEmpty()) {
                eprToAdd.setEprErrorText(error);
            }
            ExchangeProcRun exchangeProcRun = exchangeProcRunDao.saveExchangeProcRun(em, eprToAdd);

            em.getTransaction().commit();
            return Result.ok(exchangeProcRun);
        } catch (Exception e) {
            logger.error("Can't create exchange proc run", e);
            rollbackTransaction(em);
            return Result.error("Can't create exchange proc run. Text:" + e.getMessage());
        } finally {
            closeEntityManager(em);
        }
    }

    public Result<ExchangeProcRun, String> updateExchangeProcRun(Long eprUnid, ExchangeProcStatus status, String error) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            em.getTransaction().begin();

            ExchangeProcRun eprToAdd = em.find(ExchangeProcRun.class, eprUnid);
            eprToAdd.setEprLoadStatus(status.getCode());
            if (error != null && !error.isEmpty()) {
                eprToAdd.setEprErrorText(error);
            }
            ExchangeProcRun exchangeProcRun = exchangeProcRunDao.saveExchangeProcRun(em, eprToAdd);

            em.getTransaction().commit();
            return Result.ok(exchangeProcRun);
        } catch (Exception e) {
            logger.error("Can't update exchange proc run, id = {}", eprUnid, e);
            rollbackTransaction(em);
            return Result.error("Can't update exchange proc run with id = " + eprUnid + ". Text:" + e.getMessage());
        } finally {
            closeEntityManager(em);
        }
    }

    public Optional<List<ExchangeProcRun>> getFinishedExchangeProcRun(long epUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return exchangeProcRunDao.getFinishedExchangeProcRun(em, epUnid);
        } catch (Exception ex) {
            logger.error("Error when getFinishedExchangeProcRun by exchangeProcUnid = {}", epUnid, ex);
        } finally {
            closeEntityManager(em);
        }
        return Optional.empty();
    }

    public List<ExchangeProcRun> getSuspendedBkrAuctionExchangeProcRuns() {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return exchangeProcRunDao.getSuspendedBkrAuctionExchangeProcRuns(em);
        } catch (Exception ex) {
            logger.error("Error when getSuspendedBkrAuctionExchangeProcRuns", ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    public ExchangeProcRun getFinishedExchangeProcRunByAuction(long epUnid, long eprSourceId) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return exchangeProcRunDao.getFinishedExchangeProcRun(em, epUnid, eprSourceId);
        } catch (Exception ex) {
            logger.error("Error when getFinishedExchangeProcRunByAuction", ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }
}
