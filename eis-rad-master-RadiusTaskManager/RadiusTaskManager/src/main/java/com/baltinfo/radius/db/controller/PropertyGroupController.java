package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.db.dao.PropertyGroupDao;
import com.baltinfo.radius.db.model.PropertyGroup;
import com.baltinfo.radius.utils.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Maxim Kuznetsov
 * @since 16.11.2020
 */
public class PropertyGroupController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(PropertyGroupController.class);

    private PropertyGroupDao propertyGroupDao;

    public PropertyGroupController(PropertyGroupDao propertyGroupDao) {
        this.propertyGroupDao = Objects.requireNonNull(propertyGroupDao, "Can't get propertyGroupDao");
    }

    public List<PropertyGroup> getPropertyGroupsBySaleCategory(Long scUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return propertyGroupDao.getPropertyGroupsBySaleCategory(em, scUnid);
        } catch (Exception e) {
            logger.error("Can't get PropertyGroup list by scUnid={}", scUnid, e);
            return new ArrayList<>();
        } finally {
            closeEntityManager(em);
        }
    }

    public PropertyGroup getPropertyGroupByUnid(Long pgUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return propertyGroupDao.getPropertyGroupByUnid(em, pgUnid);
        } catch (Exception e) {
            logger.error("Can't get PropertyGroup by pgUnid = {}", pgUnid, e);
            return null;
        } finally {
            closeEntityManager(em);
        }
    }
}
