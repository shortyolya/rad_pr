package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.avito.model.development.City;
import com.baltinfo.radius.avito.model.development.Housing;
import com.baltinfo.radius.avito.model.development.Object;
import com.baltinfo.radius.avito.model.development.Region;
import com.baltinfo.radius.db.dao.AllowPropValueDao;
import com.baltinfo.radius.db.model.AllowPropVal;
import com.baltinfo.radius.utils.HibernateUtil;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class AllowPropValueController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(AllowPropValueController.class);

    private final AllowPropValueDao allowPropValueDao;

    public AllowPropValueController(AllowPropValueDao allowPropValueDao) {
        this.allowPropValueDao = allowPropValueDao;
    }

    public Result<List<AllowPropVal>, Void> findAllowPropVal(Long aopCode) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            List<AllowPropVal> apvList = allowPropValueDao.findAllowPropVal(em, aopCode);
            return Result.ok(apvList);
        } catch (Exception ex) {
            logger.error("Error in findAllowPropVal method. aopCode = {}", aopCode, ex);
            return Result.error();
        } finally {
            closeEntityManager(em);
        }
    }

    public void saveRegion(Region xmlRegion, Long regionAopUnid, Long cityAopUnid, Long objectAopUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            em.getTransaction().begin();
            AllowPropVal regionApv = allowPropValueDao
                    .create(em, regionAopUnid, xmlRegion.getName(), null, null);
            for (City city : xmlRegion.getCityList()) {
                AllowPropVal cityApv = allowPropValueDao
                        .create(em, cityAopUnid, city.getName(), regionApv.getApvUnid(), null);
                for (Object object : city.getObjectList()) {
                    if (object.getHouseList() != null && !object.getHouseList().isEmpty()) {
                        for (Housing house : object.getHouseList()) {
                            allowPropValueDao.create(
                                    em,
                                    objectAopUnid,
                                    house.getId().toString(),
                                    house.getAddress(),
                                    cityApv.getApvUnid(),
                                    null);
                        }
                    } else {
                        allowPropValueDao.create(
                                em,
                                objectAopUnid,
                                object.getId().toString(),
                                object.getAddress(),
                                cityApv.getApvUnid(),
                                null);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            rollbackTransaction(em);
            logger.error("Error in saveRegion method. ", ex);
        } finally {
            closeEntityManager(em);
        }
    }

    public void saveCity(City xmlCity, Long cityAopUnid, Long objectAopUnid, Long regionApvUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            em.getTransaction().begin();
            AllowPropVal cityApv = allowPropValueDao
                    .create(em, cityAopUnid, xmlCity.getName(), regionApvUnid, null);
            for (Object object : xmlCity.getObjectList()) {
                if (object.getHouseList() != null && !object.getHouseList().isEmpty()) {
                    for (Housing house : object.getHouseList()) {
                        allowPropValueDao.create(
                                em,
                                objectAopUnid,
                                house.getId().toString(),
                                house.getAddress(),
                                cityApv.getApvUnid(),
                                null);
                    }
                } else {
                    allowPropValueDao.create(
                            em,
                            objectAopUnid,
                            object.getId().toString(),
                            object.getAddress(),
                            cityApv.getApvUnid(),
                            null);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            rollbackTransaction(em);
            logger.error("Error in saveCity method. ", ex);
        } finally {
            closeEntityManager(em);
        }
    }

    public void saveHouse(Housing house, Long objectAopUnid, Long cityApvUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            em.getTransaction().begin();
            allowPropValueDao.create(em, objectAopUnid, house.getId().toString(), house.getAddress(), cityApvUnid, null);
            em.getTransaction().commit();
        } catch (Exception ex) {
            rollbackTransaction(em);
            logger.error("Error in saveHouse method. ", ex);
        } finally {
            closeEntityManager(em);
        }
    }

    public void deactualizeApvList(List<AllowPropVal> apvList) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            em.getTransaction().begin();
            for (AllowPropVal apv : apvList) {
                allowPropValueDao.deactualize(em, apv);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            rollbackTransaction(em);
            logger.error("Error in deactualizeApvList method", ex);
        } finally {
            closeEntityManager(em);
        }
    }
}
