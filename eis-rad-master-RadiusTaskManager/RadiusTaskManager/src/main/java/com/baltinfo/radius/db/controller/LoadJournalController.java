package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.db.dao.LoadJournalDao;
import com.baltinfo.radius.db.dto.AuctionDto;
import com.baltinfo.radius.db.model.LoadJournal;
import com.baltinfo.radius.db.model.LoadStatus;
import com.baltinfo.radius.utils.HibernateUtil;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.Objects;

/**
 * <p>
 *     Контроллер для создания записей в таблицу LoadJournal
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 20.02.2020
 */
public class LoadJournalController extends AbstractController{
    private static Logger logger = LoggerFactory.getLogger(LoadJournalController.class);

    private final LoadJournalDao loadJournalDao;

    public LoadJournalController(LoadJournalDao loadJournalDao) {
        this.loadJournalDao = Objects.requireNonNull(loadJournalDao, "Can't get LoadJournal DAO");
    }

    public Result<LoadJournal, String> saveLoadJournal(LoadJournal loadJournal) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            em.getTransaction().begin();
            loadJournal = loadJournalDao.saveLoadJournal(em, loadJournal);
            em.getTransaction().commit();
            return Result.ok(loadJournal);
        } catch (Exception e) {
            logger.error("Can't create loadJournal", e);
            rollbackTransaction(em);
            return Result.error("Can't create loadJournal");
        } finally {
            closeEntityManager(em);
        }
    }

    public Result<LoadJournal, String> updateLoadStatus(Long baUnid, String errorInfo, Long loadStatus) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            em.getTransaction().begin();
            LoadJournal loadJournal = getLoadJournalByBaUnid(em, baUnid);
            loadJournal = loadJournalDao.updateLoadJournalStatus(em, loadJournal, errorInfo, loadStatus);
            em.getTransaction().commit();
            return Result.ok(loadJournal);
        } catch (Exception e) {
            rollbackTransaction(em);
            logger.error("Can't update loadJournal status. baUnid = {}, errorInfo = {}, loadStatus = {}",
                    baUnid, errorInfo, loadStatus, e);
            return Result.error("Can't update loadJournal status");
        } finally {
            closeEntityManager(em);
        }
    }

    private LoadJournal getLoadJournalByBaUnid(EntityManager em, Long baUnid) {
        return (LoadJournal) em.createQuery("SELECT lj FROM LoadJournal lj " +
                        "where lj.baUnid = :baUnid " +
                        "and lj.indActual = 1")
                        .setParameter("baUnid", baUnid)
                        .getSingleResult();
    }

    public boolean loadingIsComplete(Long baUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            LoadJournal loadJournal = getLoadJournalByBaUnid(em, baUnid);
            if (loadJournal != null) {
                return loadJournal.getLstUnid().equals(LoadStatus.END_LOAD_STATUS);
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error("Can't define that loading is complete", e);
        } finally {
            closeEntityManager(em);
        }
        return false;
    }
}
