package com.baltinfo.radius.db.dao;

import com.baltinfo.radius.db.model.AllowObjProp;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * @author Igor Lapenok
 * @since 11.09.2020
 */
public class AllowObjPropDao extends AbstractDao {
    public Optional<AllowObjProp> findAllowObjProp(EntityManager em, String code) {
        List<AllowObjProp> list = em.createQuery("SELECT a " +
                "FROM AllowObjProp a " +
                "WHERE a.aopCode = :code " +
                "AND a.indActual = 1 " +
                "ORDER BY a.dateB desc")
                .setParameter("code", code)
                .getResultList();
        if (!list.isEmpty()) {
            return Optional.of(list.get(0));
        }
        return Optional.empty();
    }

    public Optional<AllowObjProp> findAllowObjProp(EntityManager em, String code, String aopName) {
        List<AllowObjProp> list = em.createQuery("SELECT a " +
                "FROM AllowObjProp a " +
                "WHERE a.aopCode = :code " +
                "AND a.aopName = :aopName " +
                "AND a.indActual = 1 " +
                "ORDER BY a.dateB desc")
                .setParameter("code", code)
                .setParameter("aopName", aopName)
                .getResultList();
        if (!list.isEmpty()) {
            return Optional.of(list.get(0));
        }
        return Optional.empty();
    }
}