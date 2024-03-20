package com.baltinfo.radius.db.dao;

import com.baltinfo.radius.db.model.AllowPropVal;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Igor Lapenok
 * @since 11.09.2020
 */
public class AllowPropValueDao extends AbstractDao{
    public Optional<AllowPropVal> findAllowPropVal(EntityManager em, Long aopUnid, String code) {
        List<AllowPropVal> list = em.createQuery("SELECT a " +
                "FROM AllowPropVal a " +
                "WHERE a.apvCode = :code " +
                "AND a.aopUnid = :aopUnid " +
                "AND a.indActual = 1")
                .setParameter("aopUnid", aopUnid)
                .setParameter("code", code)
                .getResultList();
        if (!list.isEmpty()) {
            return Optional.of(list.get(0));
        }
        return Optional.empty();
    }

    public Optional<AllowPropVal>  findAllowPropVal(EntityManager em, String value, Long aopUnid) {
        List<AllowPropVal> list = em.createQuery("SELECT a " +
                "FROM AllowPropVal a " +
                "WHERE a.aopUnid = :aopUnid " +
                "AND a.apvValue = :value " +
                "AND a.indActual = 1")
                .setParameter("aopUnid", aopUnid)
                .setParameter("value", value)
                .getResultList();
        if (!list.isEmpty()) {
            return Optional.of(list.get(0));
        }
        return Optional.empty();
    }

    public Optional<AllowPropVal>  findAllowPropVal(EntityManager em, String value, Long aopUnid, Long parentAopUnid) {
        List<AllowPropVal> list = em.createQuery("SELECT a " +
                "FROM AllowPropVal a " +
                "WHERE a.aopUnid = :aopUnid " +
                "AND a.apvValue = :value " +
                "AND a.apvParentUnid = :parentUnid " +
                "AND a.indActual = 1")
                .setParameter("aopUnid", aopUnid)
                .setParameter("parentUnid", parentAopUnid)
                .setParameter("value", value)
                .getResultList();
        if (!list.isEmpty()) {
            return Optional.of(list.get(0));
        }
        return Optional.empty();
    }

    public List<AllowPropVal> findAllowPropVal(EntityManager em, Long aopUnid) {
        return em.createQuery("SELECT a " +
                "FROM AllowPropVal a " +
                "WHERE a.aopUnid = :aopUnid " +
                "AND a.indActual = 1")
                .setParameter("aopUnid", aopUnid)
                .getResultList();
    }

    public AllowPropVal create(EntityManager em, Long aopUnid, String code, String value, Long parentUnid, String jsonProperty) {
        AllowPropVal allowPropVal = new AllowPropVal();
        allowPropVal.setAopUnid(aopUnid);
        allowPropVal.setApvCode(code);
        allowPropVal.setApvValue(value);
        allowPropVal.setApvJsonProperties(jsonProperty);
        allowPropVal.setApvParentUnid(parentUnid);
        this.setNewHisory(new Date(), allowPropVal);
        em.persist(allowPropVal);
        return allowPropVal;
    }

    public AllowPropVal create(EntityManager em, Long aopUnid, String value, Long parentUnid, String jsonProperty) {
        AllowPropVal allowPropVal = new AllowPropVal();
        allowPropVal.setAopUnid(aopUnid);
        allowPropVal.setApvValue(value);
        allowPropVal.setApvCode(value);
        allowPropVal.setApvJsonProperties(jsonProperty);
        allowPropVal.setApvParentUnid(parentUnid);
        this.setNewHisory(new Date(), allowPropVal);
        em.persist(allowPropVal);
        return allowPropVal;
    }

    public AllowPropVal update(EntityManager em, AllowPropVal allowPropVal,  Long aopUnid, String code, String value, Long parentUnid, String jsonProperty) {
        allowPropVal.setAopUnid(aopUnid);
        allowPropVal.setApvCode(code);
        allowPropVal.setApvValue(value);
        allowPropVal.setApvJsonProperties(jsonProperty);
        allowPropVal.setApvParentUnid(parentUnid);
        this.setRecHisory(new Date(), allowPropVal);
        return em.merge(allowPropVal);
    }

    public AllowPropVal update(EntityManager em, AllowPropVal allowPropVal,  Long aopUnid, String value, Long parentUnid, String jsonProperty) {
        allowPropVal.setAopUnid(aopUnid);
        allowPropVal.setApvValue(value);
        allowPropVal.setApvCode(value);
        allowPropVal.setApvJsonProperties(jsonProperty);
        allowPropVal.setApvParentUnid(parentUnid);
        this.setRecHisory(new Date(), allowPropVal);
        return em.merge(allowPropVal);
    }

    public AllowPropVal deactualize(EntityManager em, AllowPropVal allowPropVal) {
        allowPropVal.setIndActual(0);
        this.setRecHisory(new Date(), allowPropVal);
        return em.merge(allowPropVal);
    }
}
