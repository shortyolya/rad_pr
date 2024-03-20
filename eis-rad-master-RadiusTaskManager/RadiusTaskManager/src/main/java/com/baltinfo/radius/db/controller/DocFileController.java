package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.db.dao.DocFileDao;
import com.baltinfo.radius.db.model.DocFile;
import com.baltinfo.radius.utils.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.Objects;

/**
 * <p>
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 26.06.2020
 */
public class DocFileController extends AbstractController {
    private static Logger logger = LoggerFactory.getLogger(DocFileController.class);

    private final DocFileDao docFileDao;

    public DocFileController(DocFileDao docFileDao) {
        this.docFileDao = Objects.requireNonNull(docFileDao, "Can't get docFileDao");
    }

    public DocFile getDocFileByUnid(Long dfUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return docFileDao.getDocFileByUnid(em, dfUnid);
        } catch (Exception ex) {
            logger.error("Can't get docFile by unid = {}", dfUnid, ex);
            return null;
        } finally {
            closeEntityManager(em);
        }
    }

    public DocFile getDocFileByFtpPath(String ftpPath) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return docFileDao.getDocFileByDfFtpPath(em, ftpPath);
        } catch (Exception ex) {
            logger.error("Can't get docFile by ftpPath = {}", ftpPath, ex);
            return null;
        } finally {
            closeEntityManager(em);
        }
    }

    public DocFile getDocFileByDfFilePath(String filePath) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return docFileDao.getDocFileByDfFilePath(em, filePath);
        } catch (Exception ex) {
            logger.error("Can't get docFile by dbFilePath = {}", filePath, ex);
            return null;
        } finally {
            closeEntityManager(em);
        }
    }

    public DocFile updateDocFile(DocFile docFile) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            em.getTransaction().begin();
            docFile = docFileDao.updateDocFile(em, docFile);
            em.getTransaction().commit();
            return docFile;
        } catch (Exception ex) {
            rollbackTransaction(em);
            logger.error("Can't update docFile with unid = {}", docFile.getDfUnid(), ex);
            return null;
        } finally {
            closeEntityManager(em);
        }
    }

    public DocFile createDocFile(DocFile docFile) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            em.getTransaction().begin();
            docFile = docFileDao.createDocFile(em, docFile);
            em.getTransaction().commit();
            return docFile;
        } catch (Exception ex) {
            rollbackTransaction(em);
            logger.error("Can't create DocFile", ex);
            return null;
        } finally {
            closeEntityManager(em);
        }
    }
}
