package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.db.model.VLoadJournal;
import com.baltinfo.radius.utils.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 * @author Kulikov Semyon
 * @since 02.03.2020
 */

public class VLoadJournalController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(VLoadJournalController.class);

    public VLoadJournal getLoadJournalByLaUnid(Long laUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return (VLoadJournal) em.createNamedQuery("VLoadJournal.findByLaUnid")
                    .setParameter("laUnid", laUnid)
                    .getSingleResult();
        } catch (Exception ex) {
            logger.error("Error when getAuctionByLaUnid by laUnid = {}", laUnid, ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    public VLoadJournal getLoadJournalByBaUnid(Long baUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return (VLoadJournal) em.createNamedQuery("VLoadJournal.findByBaUnid")
                    .setParameter("baUnid", baUnid)
                    .getSingleResult();
        } catch (NoResultException ex) {
            logger.warn("No result found getAuctionByBaUnid by baUnid = {}", baUnid);
        } catch (Exception ex) {
            logger.error("Error when getAuctionByBaUnid by baUnid = {}", baUnid, ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }
}
