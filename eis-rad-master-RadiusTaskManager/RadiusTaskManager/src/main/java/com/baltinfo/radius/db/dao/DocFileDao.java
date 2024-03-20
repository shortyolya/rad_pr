package com.baltinfo.radius.db.dao;

import com.baltinfo.radius.db.model.DocFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.Date;

/**
 * <p>
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 29.06.2020
 */
public class DocFileDao extends AbstractDao {
    private static Logger logger = LoggerFactory.getLogger(DocFileDao.class);

    public DocFile getDocFileByUnid(EntityManager em, Long dfUnid) {
        return em.find(DocFile.class, dfUnid);
    }

    public DocFile getDocFileByDfFtpPath(EntityManager em, String ftpPath) {
        return (DocFile) em.createQuery("select df from DocFile df " +
                "where df.indActual = 1 " +
                "and df.dfFtpPath = :ftpPath")
                .setParameter("ftpPath", ftpPath)
                .getSingleResult();
    }

    public DocFile getDocFileByDfFilePath(EntityManager em, String filePath) {
        return (DocFile) em.createQuery("select df from DocFile df " +
                "where df.indActual = 1 " +
                "and df.dfFilePath = :filePath")
                .setParameter("filePath", filePath)
                .getSingleResult();
    }

    public DocFile updateDocFile(EntityManager em, DocFile docFile) {
        try {
            setRecHisory(new Date(), docFile);
            em.merge(docFile);
            return docFile;
        } catch (Exception e) {
            logger.error("Can't update DocFile with unid = {}", docFile.getDfUnid(), e);
            return null;
        }
    }

    public DocFile createDocFile(EntityManager em, DocFile docFile) {
        setNewHisory(new Date(), docFile);
        em.persist(docFile);
        return docFile;
    }
}
