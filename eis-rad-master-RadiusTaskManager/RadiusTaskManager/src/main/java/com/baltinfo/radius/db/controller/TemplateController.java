package com.baltinfo.radius.db.controller;


import com.baltinfo.radius.db.model.DocTemplate;
import com.baltinfo.radius.db.model.DocTemplatePart;
import com.baltinfo.radius.db.model.HtmlCorrection;
import com.baltinfo.radius.utils.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * @author kya
 */
public class TemplateController extends AbstractController {
    private static final Logger logger = LoggerFactory.getLogger(TemplateController.class);

    public TemplateController() {
    }

    public DocTemplate getDocTemplate(Long dtUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return em.find(DocTemplate.class, dtUnid);
        } catch (Throwable ex) {
            logger.error("Error getDocTemplate by dtUnid = {}", dtUnid, ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    public List<DocTemplatePart> getDocTemplatePartList(long dtUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            Query query = em.createQuery("select v from DocTemplatePart v where v.indActual = 1 and v.dtUnid = :dtUnid order by v.dtpUnid");
            query.setParameter("dtUnid", dtUnid);
            return query.getResultList();
        } catch (Throwable ex) {
            logger.error("Error getDocTemplatePartList by dtUnid = {}", dtUnid, ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    public List<HtmlCorrection> getHtmlCorrectionList() {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            Query query = em.createQuery("select v from HtmlCorrection v order by v.hcUnid");
            return query.getResultList();
        } catch (Throwable ex) {
            logger.error("Error getHtmlCorrectionList", ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }
}
