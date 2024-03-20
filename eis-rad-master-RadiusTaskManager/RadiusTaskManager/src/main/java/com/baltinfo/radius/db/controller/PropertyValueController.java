package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.db.dao.PropertyValueDao;
import com.baltinfo.radius.db.model.AllowPropVal;
import com.baltinfo.radius.db.model.ObjProperty;
import com.baltinfo.radius.db.model.PropertyGroup;
import com.baltinfo.radius.db.model.PropertyValue;
import com.baltinfo.radius.utils.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author Maxim Kuznetsov
 * @since 16.11.2020
 */
public class PropertyValueController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(PropertyValueController.class);

    private PropertyValueDao propertyValueDao;

    public PropertyValueController(PropertyValueDao propertyValueDao) {
        this.propertyValueDao = Objects.requireNonNull(propertyValueDao, "Can't get propertyValueDao");
    }

    public List<PropertyValue> getPropertyValuesByObject(Long objUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return propertyValueDao.getPropertyValuesByObject(em, objUnid);
        } catch (Exception e) {
            logger.error("Can't get PropertyValue list by objUnid={}", objUnid, e);
            return new ArrayList<>();
        } finally {
            closeEntityManager(em);
        }
    }

    public Boolean updatePropertyValues(List<PropertyValue> propertyValues) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            em.getTransaction().begin();
            propertyValueDao.updatePropertyValues(em, propertyValues);
            em.getTransaction().commit();
            return true;
        } catch (Throwable ex) {
            logger.error("Can't update PropertyValue list", ex);
            rollbackTransaction(em);
            return false;
        } finally {
            closeEntityManager(em);
        }
    }

    public Boolean updatePropertyValue(PropertyValue propertyValue) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            em.getTransaction().begin();
            propertyValueDao.updatePropertyValue(em, propertyValue);
            em.getTransaction().commit();
            return true;
        } catch (Throwable ex) {
            logger.error("Can't update PropertyValue with unid = {}", propertyValue.getPvUnid(), ex);
            rollbackTransaction(em);
            return false;
        } finally {
            closeEntityManager(em);
        }
    }

    private boolean isPropertyValueExist(ObjProperty objProperty, List<PropertyValue> propertyValuesList) {
        for (PropertyValue propertyValue : propertyValuesList) {
            if (objProperty.getOpUnid().equals(propertyValue.getOpUnid().getOpUnid())) {
                return true;
            }
        }
        return false;
    }

    public Boolean createPropertyValue(PropertyValue propertyValue) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            em.getTransaction().begin();
            propertyValueDao.createPropertyValue(em, propertyValue);
            em.getTransaction().commit();
            return true;
        } catch (Throwable ex) {
            logger.error("Can't create PropertyValue", ex);
            rollbackTransaction(em);
            return false;
        } finally {
            closeEntityManager(em);
        }
    }

    public PropertyValue getPropertyValueByUnid(Long pvUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return propertyValueDao.getPropertyValue(em, pvUnid);
        } catch (Exception e) {
            logger.error("Can't get PropertyValue by pvUnid = {}", pvUnid, e);
            return null;
        } finally {
            closeEntityManager(em);
        }
    }

    public PropertyValue getPropertyValueByOpUnidAndObjUnid(Long opUnid, Long objUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return propertyValueDao.getPropertyValueByOpUnidAndObjUnid(em, opUnid, objUnid);
        } catch (Exception e) {
            logger.error("Can't get PropertyValue by opUnid = {}, objUnid = {}", opUnid, objUnid, e);
            return null;
        } finally {
            closeEntityManager(em);
        }
    }

    public PropertyValue getPropertyValueByAopCodeAndObjUnid(String aopCode, Long objUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return propertyValueDao.getPropertyValueByAopCodeAndObjUnid(em, aopCode, objUnid);
        } catch (Exception e) {
            logger.error("Can't get PropertyValue by aopCode = {}, objUnid = {}", aopCode, objUnid, e);
            return null;
        } finally {
            closeEntityManager(em);
        }
    }

    public List<PropertyGroup> getPropertyGroupsForSaleCategory(Long scUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return em.createNamedQuery("PropertyGroup.findByPgUnid", PropertyGroup.class)
                    .setParameter("scUnid", scUnid)
                    .getResultList();
        } catch (Exception e) {
            logger.error("Error PropertyGroupController.getPropertyGroupsForSaleCategory with scUnid={}", scUnid, e);
        } finally {
            closeEntityManager(em);
        }
        return Collections.emptyList();
    }

    public List<ObjProperty> getObjPropertiesByPgUnid(Long pgUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return em.createNamedQuery("ObjProperty.findByPgUnid", ObjProperty.class)
                    .setParameter("pgUnid", pgUnid)
                    .getResultList();
        } catch (Exception e) {
            logger.error("Error ObjPropertyController.getObjPropertiesByPgUnid() with pgUnid={}", pgUnid, e);
        } finally {
            closeEntityManager(em);
        }
        return Collections.emptyList();
    }

    public AllowPropVal getAllowPropertyValueByValue(Long aopUnid, String apvValue) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return em.createNamedQuery("AllowPropVal.findByAopUnidAndApvValue", AllowPropVal.class)
                    .setParameter("aopUnid", aopUnid)
                    .setParameter("apvValue", apvValue.trim())
                    .getSingleResult();
        } catch (Exception e) {
            logger.error("Error getAllowPropertyValueByValue by aopUnid = {}, apvValue = {}", aopUnid, apvValue, e);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }
}
