package com.baltinfo.radius.db.dao;

import com.baltinfo.radius.db.model.AllowObjProp;
import com.baltinfo.radius.db.model.PropertyValue;
import java.util.ArrayList;
import java.util.Date;
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
public class PropertyValueDao extends AbstractDao {

    private static final Logger logger = LoggerFactory.getLogger(PropertyValueDao.class);

    public List<PropertyValue> getPropertyValuesByObject(EntityManager em, Long objUnid) {
        return (List<PropertyValue>) em.createQuery("SELECT p FROM PropertyValue AS p " +
                "WHERE p.indActual = 1 " +
                "AND p.objUnid = :objUnid")
                .setParameter("objUnid", objUnid)
                .getResultList();
    }

    public PropertyValue getPropertyValue(EntityManager em, Long pvUnid) {
        return em.find(PropertyValue.class, pvUnid);
    }

    public void updatePropertyValues(EntityManager em, List<PropertyValue> propertyValues) {
        propertyValues.forEach(val -> updatePropertyValue(em, val));
    }

    public void updatePropertyValue(EntityManager em, PropertyValue propertyValue) {
        Date now = new Date();
        Long paUnid = 1L;
        propertyValue.setDateBRec(now);
        propertyValue.setPersCodeBRec(paUnid);
        em.merge(propertyValue);
    }

    private boolean isUpdatedValue(EntityManager em, PropertyValue propertyValue) {
        PropertyValue oldPropertyValue = getPropertyValue(em, propertyValue.getPvUnid());
        boolean newValueNotEmpty = propertyValue.getPvValue() != null && !propertyValue.getPvValue().isEmpty();
        boolean oldValueNotEmpty = oldPropertyValue != null && oldPropertyValue.getPvValue() != null && !oldPropertyValue.getPvValue().isEmpty();
        return newValueNotEmpty && !oldValueNotEmpty || oldValueNotEmpty && !newValueNotEmpty || newValueNotEmpty && !oldPropertyValue.getPvValue().equals(propertyValue.getPvValue());
    }

    public void createPropertyValue(EntityManager em, PropertyValue propertyValue) {
        Date now = new Date();
        Long paUnid = 1L;
        propertyValue.setIndActual(1);
        propertyValue.setDateB(now);
        propertyValue.setPersCodeB(paUnid);
        em.persist(propertyValue);
    }

    public PropertyValue getPropertyValueByOpUnidAndObjUnid(EntityManager em, Long opUnid, Long objUnid) {
        return (PropertyValue) em.createQuery("SELECT p FROM PropertyValue AS p " +
                "WHERE p.indActual = 1 " +
                "AND p.opUnid.opUnid = :opUnid " +
                "AND p.objUnid = :objUnid")
                .setParameter("opUnid", opUnid)
                .setParameter("objUnid", objUnid)
                .getSingleResult();
    }

    public PropertyValue getPropertyValueByAopCodeAndObjUnid(EntityManager em, String aopCode, Long objUnid) {
        return (PropertyValue) em.createQuery("SELECT p FROM PropertyValue AS p " +
                "WHERE p.indActual = 1 " +
                "AND p.opUnid.indActual = 1 " +
                "AND p.opUnid.aopUnid.indActual = 1 " +
                "AND p.opUnid.aopUnid.aopCode = :aopCode " +
                "AND p.objUnid = :objUnid")
                .setParameter("aopCode", aopCode)
                .setParameter("objUnid", objUnid)
                .getSingleResult();
    }
}
