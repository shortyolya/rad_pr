package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.utils.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

public class ReportController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

    public ReportController() {
    }

    public List<Object[]> getTableValues(String sql) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return em.createNativeQuery(sql).getResultList();
        } catch (Exception ex) {
            logger.error("Error getTableValues", ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    public Object[] getFooterValues(String sql) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return (Object[]) em.createNativeQuery(sql).getSingleResult();
        } catch (Exception ex) {
            logger.error("Error getFooterValues", ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    public List<Object[]> getTableValues(String sql, Map<String, Object> params) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            Query q = em.createNativeQuery(sql);
            for (String paramName : params.keySet()) {
                q.setParameter(paramName, params.get(paramName));
            }
            return q.getResultList();
        } catch (Exception ex) {
            logger.error("Error getTableValues", ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    public Object[] getFooterValues(String sql, Map<String, Object> params) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            Query q = em.createNativeQuery(sql);
            for (String paramName : params.keySet()) {
                q.setParameter(paramName, params.get(paramName));
            }
            return (Object[]) q.getSingleResult();
        } catch (Exception ex) {
            logger.error("Error getFooterValues", ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

}
