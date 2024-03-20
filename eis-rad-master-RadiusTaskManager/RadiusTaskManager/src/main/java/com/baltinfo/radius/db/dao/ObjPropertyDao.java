package com.baltinfo.radius.db.dao;

import com.baltinfo.radius.db.model.ObjProperty;
import java.util.List;
import javax.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 16.11.2020
 */
public class ObjPropertyDao extends AbstractDao {

    private static final Logger logger = LoggerFactory.getLogger(ObjPropertyDao.class);

    public List<ObjProperty> getObjPropertyListForExportByPgUnid(EntityManager em, Long pgUnid) {
        return em.createQuery("SELECT op FROM ObjProperty AS op " +
                "WHERE op.indActual = 1 " +
                "AND op.pgUnid.pgUnid = :pgUnid " +
                "AND op.opIndExportEtp = true " +
                "ORDER BY op.opNum ASC", ObjProperty.class)
                .setParameter("pgUnid", pgUnid)
                .getResultList();
    }

    public ObjProperty getObjPropertyByPgUnidAndAopCode(EntityManager em, Long pgUnid, String aopCode) {
        return em.createQuery("SELECT a FROM ObjProperty AS a " +
                "WHERE a.indActual = 1 " +
                "AND a.pgUnid.pgUnid = :pgUnid " +
                "AND a.aopUnid.indActual = 1 " +
                "AND trim(lower(a.aopUnid.aopCode)) = trim(lower(:aopCode))", ObjProperty.class)
                .setParameter("pgUnid", pgUnid)
                .setParameter("aopCode", aopCode)
                .getSingleResult();
    }
}
