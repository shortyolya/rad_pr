package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.db.model.DocTemplate;
import com.baltinfo.radius.utils.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;

public class DocTemplateController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(DocTemplateController.class);

    public DocTemplate getDocTemplateByDtUnid(Long dtUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return em.find(DocTemplate.class, dtUnid);
        } catch (Exception ex) {
            logger.error("Error getDocTemplateByDtUnid by dtUnid = {}", dtUnid, ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }
}
