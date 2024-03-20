package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.db.model.lotonline.LotInfo;
import com.baltinfo.radius.utils.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;

/**
 * @author Suvorina Aleksandra
 * @since 24.03.2021
 */
public class LotInfoController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(LotInfoController.class);

    public LotInfo getLotInfo(Long id) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManagerLOLS3();
            return em.find(LotInfo.class, id);
        }catch (Exception ex) {
            logger.error("Error getLotInfo by id = {}", id, ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

}
