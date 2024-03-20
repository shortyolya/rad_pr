package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.db.dao.ObjPropertyDao;
import com.baltinfo.radius.db.model.ObjProperty;
import com.baltinfo.radius.utils.HibernateUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Maxim Kuznetsov
 * @since 16.11.2020
 */
public class ObjPropertyController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(ObjPropertyController.class);

    private ObjPropertyDao objPropertyDao;

    public ObjPropertyController(ObjPropertyDao objPropertyDao) {
        this.objPropertyDao = Objects.requireNonNull(objPropertyDao, "Can't get objPropertyDao");
    }

    public List<ObjProperty> getObjPropertyListForExportByPgUnid(Long pgUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return objPropertyDao.getObjPropertyListForExportByPgUnid(em, pgUnid);
        } catch (Exception e) {
            logger.error("Can't get ObjProperty list by pgUnid = {}", pgUnid, e);
            return new ArrayList<>();
        } finally {
            closeEntityManager(em);
        }
    }

    public ObjProperty getObjPropertyByPgUnidAndAopCode(Long pgUnid, String aopCode) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return objPropertyDao.getObjPropertyByPgUnidAndAopCode(em, pgUnid, aopCode);
        } catch (NoResultException e) {
            logger.error("ObjProperty not found by pgUnid = {} and aopCode = {}", pgUnid, aopCode);
            return null;
        } catch (Exception e) {
            logger.error("Can't get ObjProperty by pgUnid = {} and aopCode = {}", pgUnid, aopCode, e);
            return null;
        } finally {
            closeEntityManager(em);
        }
    }
}
