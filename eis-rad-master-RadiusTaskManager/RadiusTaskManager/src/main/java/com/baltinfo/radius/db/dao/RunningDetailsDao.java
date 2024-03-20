package com.baltinfo.radius.db.dao;

import com.baltinfo.radius.db.constants.RunningDetailsStatus;
import com.baltinfo.radius.db.model.RunningDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * DAO для работы с ходом обмена
 *
 * @author Suvorina Aleksandra
 * @since 23.08.2018
 */
public class RunningDetailsDao extends AbstractDao {

    private static Logger logger = LoggerFactory.getLogger(RunningDetailsDao.class);

    /**
     * Получение хода обмена по статусу
     *
     * @param em                   экземпляр {@link EntityManager}
     * @param exchangeProcRunUnid  идентификатор журнала процедуры информационного обмена
     * @param runningDetailsStatus статус хода обмена
     * @return список записей хода обмена
     */
    public List<RunningDetails> getRunningDetailsByStatus(EntityManager em, Long exchangeProcRunUnid,
                                                          RunningDetailsStatus runningDetailsStatus) {
        logger.debug("start method: getRunningDetailsByStatus() at {}", Instant.now());
        try {
            return em.createNamedQuery("RunningDetails.findByResultStatus")
                    .setParameter("eprUnid", exchangeProcRunUnid)
                    .setParameter("result", runningDetailsStatus.getCode())
                    .getResultList();
        } catch (Exception ex) {
            logger.error("Can't get RunningDetails by eprUnid = {} and status = {}",
                    exchangeProcRunUnid, runningDetailsStatus, ex);
        } finally {
            logger.debug("finish method: getRunningDetailsByStatus() at {}", Instant.now());
        }
        return null;
    }

    public List<RunningDetails> getRunningDetails(EntityManager em, Long exchangeProcRunUnid) {
        logger.debug("start method: getRunningDetailsByStatus() at {}", Instant.now());
        try {
            return em.createNamedQuery("RunningDetails.findByEprUnid")
                    .setParameter("eprUnid", exchangeProcRunUnid)
                    .getResultList();
        } catch (Exception ex) {
            logger.error("Can't get RunningDetails by eprUnid = {}",
                    exchangeProcRunUnid, ex);
        } finally {
            logger.debug("finish method: getRunningDetailsByStatus() at {}", Instant.now());
        }
        return null;
    }

    public RunningDetails saveRunningDetails(EntityManager em, RunningDetails runningDetails) {
        if (runningDetails.getRdUnid() == null) {
            runningDetails.setIndActual(1);
            runningDetails.setDateB(new Date());
            runningDetails.setPersCodeB(1L);
            em.persist(runningDetails);
        } else {
            runningDetails.setDateBRec(new Date());
            runningDetails.setPersCodeBRec(1L);
            em.merge(runningDetails);
        }
        return runningDetails;
    }


    public List<RunningDetails> getRunningDetailsByEpr(EntityManager em, Long eprUnid) {
        return em.createNamedQuery("RunningDetails.findByEprUnid")
                .setParameter("eprUnid", eprUnid)
                .getResultList();
    }

}
