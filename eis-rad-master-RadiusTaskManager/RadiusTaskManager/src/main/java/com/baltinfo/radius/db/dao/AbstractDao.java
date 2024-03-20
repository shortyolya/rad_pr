package com.baltinfo.radius.db.dao;

import com.baltinfo.radius.db.model.IHistory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Date;

/**
 * <p>
 * Базовый DAO
 * </p>
 *
 * @author Lapenok Igor
 * @since 16.08.2018
 */
public abstract class AbstractDao {

    private static Logger logger = LoggerFactory.getLogger(AbstractDao.class);

    public static final Long DEFAULT_PERS_CODE = 1L;

    public Long getSeq(String seqName, EntityManager em) {
        Query q = em.createNativeQuery("select nextval('" + seqName + "')");
        return new Long(q.getSingleResult() + "");
    }

    protected void setNewHisory(Long paUnid, Date now, IHistory object) {
        object.setPersCodeB(paUnid);
        object.setIndActual(1);
        object.setDateB(now);
        setRecHisory(paUnid, now, object);
    }

    protected void setNewHisory(Date now, IHistory object) {
        setNewHisory(DEFAULT_PERS_CODE, now, object);
    }

    protected void setRecHisory(Long paUnid, Date now, IHistory object) {
        object.setPersCodeBRec(paUnid);
        object.setDateBRec(now);
    }

    protected void setRecHisory(Date now, IHistory object) {
        setRecHisory(DEFAULT_PERS_CODE, now, object);
    }

    protected void setRecHisoryForDelete(Long paUnid, Date now, IHistory object) {
        object.setIndActual(0);
        object.setPersCodeBRec(paUnid);
        object.setDateBRec(now);
    }

    protected void setRecHisoryForDelete(Date now, IHistory object) {
        setRecHisoryForDelete(DEFAULT_PERS_CODE, now, object);
    }
}