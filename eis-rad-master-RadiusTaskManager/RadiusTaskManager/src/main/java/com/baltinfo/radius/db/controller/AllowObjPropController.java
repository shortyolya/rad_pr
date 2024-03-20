package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.avito.dto.AutoProperty;
import com.baltinfo.radius.avito.dto.ObjectPropertyDto;
import com.baltinfo.radius.db.dao.AllowObjPropDao;
import com.baltinfo.radius.db.dao.AllowPropValueDao;
import com.baltinfo.radius.db.model.AllowObjProp;
import com.baltinfo.radius.db.model.AllowPropVal;
import com.baltinfo.radius.utils.HibernateUtil;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Igor Lapenok
 * @since 11.09.2020
 */
public class AllowObjPropController extends AbstractController {

    private final Logger logger = LoggerFactory.getLogger(AllowObjPropController.class);

    private AllowObjPropDao allowObjPropDao;
    private AllowPropValueDao allowPropValueDao;

    public AllowObjPropController(AllowObjPropDao allowObjPropDao, AllowPropValueDao allowPropValueDao) {
        this.allowObjPropDao = allowObjPropDao;
        this.allowPropValueDao = allowPropValueDao;
    }

    public boolean fillValues(AutoProperty autoProperty, Map<String, ObjectPropertyDto> propertyValues) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            Optional<AllowObjProp> allowObjProp = this.allowObjPropDao.findAllowObjProp(em, autoProperty.getType());
            if (allowObjProp.isPresent()) {
                em.getTransaction().begin();
                for (String code : propertyValues.keySet()) {
                    ObjectPropertyDto propertyDto = propertyValues.get(code);
                    Optional<AllowPropVal> allowPropVal =
                            allowPropValueDao.findAllowPropVal(em, allowObjProp.get().getAopUnid(), propertyDto.getId());
                    if (allowPropVal.isPresent()) {
                        allowPropValueDao.update(em, allowPropVal.get(), allowObjProp.get().getAopUnid(),
                                propertyDto.getId(), propertyDto.getValue(), null, null);
                    } else {
                        allowPropValueDao.create(em, allowObjProp.get().getAopUnid(), propertyDto.getId(),
                                propertyDto.getValue(), null, null);
                    }
                }
                em.getTransaction().commit();
                return true;
            }

        } catch (Exception e) {
            logger.error("Error getAuctionsForEndReceiptApplications", e);
            rollbackTransaction(em);
        } finally {
            closeEntityManager(em);
        }
        return false;
    }

    public Result<Void, String> fillMakeValues(List<ObjectPropertyDto> objectPropertyDtoList) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            int count = 1;
            for (ObjectPropertyDto objectProperty : objectPropertyDtoList) {
                em.getTransaction().begin();
                logger.info("Start load Make = {}", objectProperty.getValue());
                Optional<AllowObjProp> allowObjProp = this.allowObjPropDao.findAllowObjProp(em, objectProperty.getCode().getType());
                if (allowObjProp.isPresent()) {
                    Optional<AllowPropVal> allowPropVal =
                            allowPropValueDao.findAllowPropVal(em, allowObjProp.get().getAopUnid(), objectProperty.getId());
                    AllowPropVal value = allowPropVal.isPresent()
                            ? allowPropValueDao.update(em, allowPropVal.get(), allowObjProp.get().getAopUnid(),
                            objectProperty.getId(), objectProperty.getValue(), null, null)
                            : allowPropValueDao.create(em, allowObjProp.get().getAopUnid(), objectProperty.getId(),
                            objectProperty.getValue(), null, null);
                    if (value != null) {
                        Result<Void, String> result = fillChildValues(em, value, objectProperty.getChildList());
                        if (result.isError()) {
                            rollbackTransaction(em);
                            return result;
                        }
                    }
                }
                logger.info("End load Make = {}, count = {}", objectProperty.getValue(), count);
                em.getTransaction().commit();
                count++;
            }
            return Result.ok();
        } catch (Exception e) {
            logger.error("Can't fill Make values", e);
            rollbackTransaction(em);
            return Result.error("Ошибка при обновлении марки, модели, поколения или модификации автомобиля");
        } finally {
            closeEntityManager(em);
        }
    }

    private Result<Void, String> fillChildValues(EntityManager em, AllowPropVal parentValue, List<ObjectPropertyDto> childList) {
        for (ObjectPropertyDto objectProperty : childList) {
            Optional<AllowObjProp> allowObjProp = this.allowObjPropDao.findAllowObjProp(em, objectProperty.getCode().getType());
            if (allowObjProp.isPresent()) {
                Optional<AllowPropVal> allowPropVal =
                        allowPropValueDao.findAllowPropVal(em, allowObjProp.get().getAopUnid(), objectProperty.getId());
                AllowPropVal value = allowPropVal.isPresent()
                        ? allowPropValueDao.update(em, allowPropVal.get(), allowObjProp.get().getAopUnid(),
                        objectProperty.getId(), objectProperty.getValue(), parentValue.getApvUnid(), objectProperty.getJsonText())
                        : allowPropValueDao.create(em, allowObjProp.get().getAopUnid(), objectProperty.getId(),
                        objectProperty.getValue(), parentValue.getApvUnid(), objectProperty.getJsonText());
                if (value != null) {
                    Result<Void, String> result = fillChildValues(em, value, objectProperty.getChildList());
                    if (result.isError()) {
                        return result;
                    }
                }
            }
        }
        return Result.ok();
    }

    public Result<AllowObjProp, Void> findAllowObjProp(String aopCode) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            Optional<AllowObjProp> aop = allowObjPropDao.findAllowObjProp(em, aopCode);
            if (aop.isPresent()) {
                return Result.ok(aop.get());
            }
        } catch (Exception ex) {
            logger.error("Error in findAllowObjProp method. aopCode = {}", aopCode, ex);
            return Result.error();
        }
        return Result.error();
    }
}
