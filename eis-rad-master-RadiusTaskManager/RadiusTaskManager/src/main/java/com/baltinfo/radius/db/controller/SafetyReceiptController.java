package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.utils.HibernateUtil;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;

public class SafetyReceiptController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(SafetyReceiptController.class);


    public Result<Void, String> resetSequence() {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            em.createNativeQuery("SELECT setval('web.seq_safety_receipt_num', 1, FALSE);")
                    .getSingleResult();
            return Result.ok();
        } catch (Exception ex) {
            logger.error("Error when trying reset sequence", ex);
            return Result.error(ex.getMessage());
        } finally {
            closeEntityManager(em);
        }
    }

}
