package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.db.model.DocFile;
import com.baltinfo.radius.db.model.MapRegion;
import com.baltinfo.radius.utils.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;

public class MapRegionController extends AbstractController {
    private static Logger logger = LoggerFactory.getLogger(DocFileController.class);

    public MapRegion getMapRegionByMrCode(String mrCode) {

        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return (MapRegion) em.createQuery("select mr from MapRegion mr " +
                    "where mr.mrCode = :mrCode")
                    .setParameter("mrCode", mrCode)
                    .getSingleResult();
        } catch (Exception ex) {
            logger.error("Can't get MapRegion by mrCode = {}", mrCode, ex);
            return null;
        } finally {
            closeEntityManager(em);
        }
    }
}
