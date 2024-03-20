package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.db.model.LotonlineProfile;
import com.baltinfo.radius.utils.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;

public class LotonlineProfileController extends AbstractController {
    private static final Logger logger = LoggerFactory.getLogger(LotonlineProfileController.class);


    public LotonlineProfile getLotonlineProfile(long unid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return em.find(LotonlineProfile.class, unid);
        } catch (Throwable ex) {
            logger.error("Error getLotonlineProfile by lpUnid = {}", unid, ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

}
