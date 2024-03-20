package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.db.constants.TypeDocConstant;
import com.baltinfo.radius.db.model.DocFile;
import com.baltinfo.radius.db.model.Document;

import com.baltinfo.radius.db.model.TypeDoc;
import com.baltinfo.radius.utils.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;


public class DocumentController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(DocumentController.class);
    private static final Long persCode = 1L;

    public List<Document> getGeneratedDocuments(Long documStatus, Long dgtUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return em.createNamedQuery("Document.getByDocumStatusAndDgtUnid", Document.class)
                    .setParameter("documStatus", documStatus)
                    .setParameter("dgtUnid", dgtUnid)
                    .getResultList();
        } catch (Exception ex) {
            logger.error("Error getGeneratedDocuments by documStatus = {} and dgtUnid = {}", documStatus, dgtUnid, ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    public void saveDocument(Document document, DocFile docFile) {
        EntityManager em = null;
        Date now = new Date();
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            em.getTransaction().begin();

            docFile.setIndActual(1);
            docFile.setDateB(now);
            docFile.setPersCodeB(persCode);
            em.persist(docFile);

            document.setDfUnid(docFile);
            document.setDateBRec(now);
            document.setPersCodeBRec(persCode);
            em.merge(document);
            em.getTransaction().commit();
        } catch (Exception ex) {
            logger.error("Error saveDocument by documUnid = {}", document.getDocumUnid(), ex);
            rollbackTransaction(em);
        } finally {
            closeEntityManager(em);
        }
    }

    public void saveDocument(Document document) {
        EntityManager em = null;
        Date now = new Date();
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            em.getTransaction().begin();
            document.setDateBRec(now);
            document.setPersCodeBRec(persCode);
            em.merge(document);
            em.getTransaction().commit();
        } catch (Exception ex) {
            logger.error("Error setDocumStatusAndResult by documUnid = {}", document.getDocumUnid(), ex);
            rollbackTransaction(em);
        } finally {
            closeEntityManager(em);
        }
    }

    public Document createDocument(Document document) {
        EntityManager em = null;
        Date now = new Date();
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            em.getTransaction().begin();
            document.setIndActual(1);
            document.setDateB(now);
            document.setPersCodeB(persCode);
            em.persist(document);
            em.getTransaction().commit();
            return document;
        } catch (Exception ex) {
            logger.error("Error createDocument", ex);
            rollbackTransaction(em);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    public TypeDoc getTypeDocById(Long tdUnid){
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return em.find(TypeDoc.class, tdUnid);
        } catch (Exception e) {
            logger.error("Can't get getPaidPayDoc list ", e);
            return null;
        } finally {
            closeEntityManager(em);
        }
    }
}
