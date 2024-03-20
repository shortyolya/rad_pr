package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.db.dao.ExplanationResponseDao;
import com.baltinfo.radius.db.model.ExplanationResponse;
import com.baltinfo.radius.utils.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;

/**
 * @author Suvorina Aleksandra
 * @since 23.01.2022
 * @since 23.01.2022
 */
public class ExplanationResponseController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(ExplanationResponseController.class);

    private final ExplanationResponseDao explanationResponseDao;

    public ExplanationResponseController(ExplanationResponseDao explanationResponseDao) {
        this.explanationResponseDao = explanationResponseDao;
    }

    public Boolean checkEprExistsByEtpId(Long exrEtpId) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            Long count = em.createNamedQuery("ExplanationResponse.findCountByExrEtpId", Long.class)
                    .setParameter("exrEtpId", exrEtpId)
                    .getSingleResult();
            return count != 0;
        } catch (Exception ex) {
            logger.error("Error checkEprByEtpUnid by exrEtpId = {}", exrEtpId, ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    public ExplanationResponse createExplanationResponse(ExplanationResponse newExr) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            em.getTransaction().begin();
            ExplanationResponse explanationResponse = explanationResponseDao.createExplanationResponse(em, newExr);
            em.getTransaction().commit();
            return explanationResponse;
        } catch (Exception ex) {
            logger.error("Error createExplanationResponse ", ex);
            rollbackTransaction(em);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }
}
