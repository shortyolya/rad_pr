package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.avito.dto.truck.TruckObjectPropertyDto;
import com.baltinfo.radius.avito.dto.truck.TruckProperty;
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
import java.util.Optional;

/**
 * @author Andrei Shymko
 * @since 09.11.2020
 */
public class TruckAllowObjPropController extends AbstractController {
    private final Logger logger = LoggerFactory.getLogger(TruckAllowObjPropController.class);

    private final AllowObjPropDao allowObjPropDao;
    private final AllowPropValueDao allowPropValueDao;

    public TruckAllowObjPropController(AllowObjPropDao allowObjPropDao, AllowPropValueDao allowPropValueDao) {
        this.allowObjPropDao = allowObjPropDao;
        this.allowPropValueDao = allowPropValueDao;
    }

    public boolean fillValues(TruckProperty truckProperty, List<TruckObjectPropertyDto> propertyValues) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            Optional<AllowObjProp> allowObjProp = this.allowObjPropDao.findAllowObjProp(em, truckProperty.getType());
            if (allowObjProp.isPresent()) {
                em.getTransaction().begin();
                for (TruckObjectPropertyDto propertyDto : propertyValues) {
                    Optional<AllowPropVal> allowPropVal =
                            allowPropValueDao.findAllowPropVal(em, propertyDto.getValue(), allowObjProp.get().getAopUnid());
                    if (allowPropVal.isPresent()) {
                        allowPropValueDao.update(em, allowPropVal.get(), allowObjProp.get().getAopUnid(),
                                propertyDto.getValue(), null, null);
                    } else {
                        allowPropValueDao.create(em, allowObjProp.get().getAopUnid(),
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

    public Result<Void, String> fillMakeValues(List<TruckObjectPropertyDto> objectPropertyDtoList) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            int count = 1;
            for (TruckObjectPropertyDto objectProperty : objectPropertyDtoList) {
                em.getTransaction().begin();
                logger.info("Start load Make = {}", objectProperty.getValue());
                Optional<AllowObjProp> allowObjProp = this.allowObjPropDao.findAllowObjProp(em, objectProperty.getCode().getType(), objectProperty.getCode().getName());
                if (allowObjProp.isPresent()) {
                    Optional<AllowPropVal> allowPropVal =
                            allowPropValueDao.findAllowPropVal(em, objectProperty.getValue(), allowObjProp.get().getAopUnid());
                    AllowPropVal value = allowPropVal.isPresent()
                            ? allowPropValueDao.update(em, allowPropVal.get(), allowObjProp.get().getAopUnid(),
                            objectProperty.getValue(), null, null)
                            : allowPropValueDao.create(em, allowObjProp.get().getAopUnid(),
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
            return Result.error("Ошибка при обновлении марки, модели, типа кузова грузовика");
        } finally {
            closeEntityManager(em);
        }
    }

    private Result<Void, String> fillChildValues(EntityManager em, AllowPropVal parentValue, List<TruckObjectPropertyDto> childList) {
        for (TruckObjectPropertyDto objectProperty : childList) {
            Optional<AllowObjProp> allowObjProp = this.allowObjPropDao.findAllowObjProp(em, objectProperty.getCode().getType(), objectProperty.getCode().getName());
            if (allowObjProp.isPresent()) {
                Optional<AllowPropVal> allowPropVal =
                        allowPropValueDao.findAllowPropVal(em, objectProperty.getValue(), allowObjProp.get().getAopUnid(), parentValue.getApvUnid());
                AllowPropVal value = allowPropVal.isPresent()
                        ? allowPropValueDao.update(em, allowPropVal.get(), allowObjProp.get().getAopUnid(),
                        objectProperty.getValue(), parentValue.getApvUnid(), objectProperty.getJsonText())
                        : allowPropValueDao.create(em, allowObjProp.get().getAopUnid(),
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
}