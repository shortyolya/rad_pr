package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.db.constants.ExchangeProcStatus;
import com.baltinfo.radius.db.dao.ObjectJpaDao;
import com.baltinfo.radius.db.dto.FeedObjectDto;
import com.baltinfo.radius.db.model.Lot;
import com.baltinfo.radius.db.model.ObjSaleCategory;
import com.baltinfo.radius.db.model.ObjectJPA;
import com.baltinfo.radius.db.model.SaleCategory;
import com.baltinfo.radius.loadauction.model.assets.Asset;
import com.baltinfo.radius.utils.HibernateUtil;
import com.baltinfo.radius.utils.Result;
import com.baltinfo.radius.yandex.dto.ObjAndDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * <p>
 * Будущий контроллер для создания объектов по Asset'ам
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 30.01.2020
 */
public class ObjectController extends AbstractController {

    private static Logger logger = LoggerFactory.getLogger(ObjectController.class);

    private final ObjectJpaDao objectJpaDao;

    public ObjectController(ObjectJpaDao objectJpaDao) {
        this.objectJpaDao = Objects.requireNonNull(objectJpaDao, "Can't get objectJpaDao");
    }

    public ObjectJPA createObjectsFromAsset(List<Asset> assetList) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            em.getTransaction().begin();
            Result<ObjectJPA, String> resObject = objectJpaDao.createObjectsFromAsset(em, assetList);
            if (resObject.isError()) {
                logger.error("Can't create object");
                rollbackTransaction(em);
                return null;
            }
            em.getTransaction().commit();
            return resObject.getResult();
        } catch (Exception e) {
            logger.error("Can't create object", e);
            rollbackTransaction(em);
            return null;
        } finally {
            closeEntityManager(em);
        }
    }

    public ObjSaleCategory createObjectSaleCategory(EntityManager em, ObjectJPA obj, SaleCategory saleCategory, boolean indMain) {
        try {
            Result<ObjSaleCategory, String> categoryResult = objectJpaDao.createObjSaleCategory(em, obj, saleCategory, indMain);
            if (categoryResult.isError()) {
                logger.info(categoryResult.getError());
                return null;
            }
            return categoryResult.getResult();
        } catch (Exception e) {
            logger.info("Can't create objSaleCategory");
            return null;
        }
    }

    public SaleCategory getSaleCategory(EntityManager em, Long scUnid) {
        try {
            return objectJpaDao.getSaleCategoryByUnid(em, scUnid);
        } catch (Exception e) {
            logger.error("Can't get SaleCategory by unid = {}", scUnid, e);
            return null;
        }
    }

    public List<ObjSaleCategory> getObjSaleCategories(EntityManager em, Long objUnid) {
        try {
            return objectJpaDao.getObjSaleCategories(em, objUnid);
        } catch (Exception e) {
            logger.error("Error getObjSaleCategories by objUnid = {}", objUnid, e);
            return null;
        }
    }

    public List<FeedObjectDto> getObjectsForFeed() {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return objectJpaDao.getObjectsForFeed(em);
        } catch (Exception e) {
            logger.error("Can't getObjectsForFeed", e);
            return null;
        } finally {
            closeEntityManager(em);
        }
    }

    public FeedObjectDto getObjectForFeed(Long objUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return objectJpaDao.getObjectForFeed(em, objUnid);
        } catch (Exception e) {
            logger.error("Can't getObjectForFeed", e);
            return null;
        } finally {
            closeEntityManager(em);
        }
    }

    public Optional<ObjectJPA> getObjectByCode(String objCode) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            ObjectJPA object = em.createNamedQuery("ObjectJPA.findByObjCode", ObjectJPA.class)
                    .setParameter("objCode", objCode)
                    .getSingleResult();
            return Optional.of(object);
        } catch (Exception e) {
            logger.error("Can't getObjectByCode by objCode = {}", objCode, e);
            return Optional.empty();
        } finally {
            closeEntityManager(em);
        }
    }

    public List<ObjAndDate> getObjectsFromAuctionSentOnEtp(Long tsUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            List<Object[]> objects = em.createQuery("SELECT o, epr.eprRunDate " +
                            "FROM Auction a, ObjectJPA o, Lot l " +
                            "WHERE a.auctionEtpId is not null " +
                            "AND a.mpUnid is not null " +
                            "AND a.tsUnid = :tsUnid " +
                            "AND a.indActual = 1 " +
                            "AND o.objUnid = l.objUnid.objUnid and l.auctionUnid = a.auctionUnid and l.indActual = 1 and l.lotIndCurrent = 1 " +
                            "AND o.indActual = 1")
                    .setParameter("tsUnid", tsUnid)
                    .getResultList();
            List<ObjAndDate> rez = new ArrayList<>();
            for (Object[] object : objects) {
                rez.add(new ObjAndDate((ObjectJPA) object[0], new Date(((Timestamp) object[1]).getTime())));
            }
            return rez;
        } catch (Exception e) {
            logger.error("Can't getObjectsFromAuctionSentOnEtp by tsUnid = {} ", tsUnid, e);
            return new ArrayList<>();
        } finally {
            closeEntityManager(em);
        }
    }

    public List<ObjAndDate> getObjectsFromAuctionByEpr(Long epUnid, Long tsUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            List<Object[]> objects = em.createQuery("SELECT o, epr.eprRunDate " +
                    "FROM Auction a, ExchangeProcRun epr, ObjectJPA o, Lot l " +
                            "WHERE epr.epUnid = :epUnid " +
                            "AND epr.eprLoadStatus = :eprLoadStatus " +
                            "AND a.auctionUnid = epr.eprSourceId " +
                            "AND a.tsUnid = :tsUnid " +
                            "AND a.indActual = 1 " +
                            "AND o.objUnid = l.objUnid.objUnid and l.auctionUnid = a.auctionUnid and l.indActual = 1 and l.lotIndCurrent = 1 " +
                            "AND o.indActual = 1")
                    .setParameter("epUnid", epUnid)
                    .setParameter("tsUnid", tsUnid)
                    .setParameter("eprLoadStatus", ExchangeProcStatus.FINISHED.getCode())
                    .getResultList();
            List<ObjAndDate> rez = new ArrayList<>();
            for (Object[] object : objects) {
                rez.add(new ObjAndDate((ObjectJPA) object[0], new Date(((Timestamp) object[1]).getTime())));
            }
            return rez;
        } catch (Exception e) {
            logger.error("Can't getObjectsFromAuctionByEpr by epUnid = {}, tsUnid = {} ", epUnid, tsUnid, e);
            return new ArrayList<>();
        } finally {
            closeEntityManager(em);
        }
    }

    public List<ObjAndDate> getObjectForUpdateStatistic(Long epUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            List<Object[]> objects = em.createQuery("SELECT o, epr.eprRunDate " +
                            "FROM ObjectJPA o, ExchangeProcRun epr " +
                            "WHERE epr.epUnid = :epUnid " +
                            "AND epr.eprLoadStatus = :eprLoadStatus " +
                            "AND o.objUnid = epr.eprSourceId " +
                            "AND o.indActual = 1 " +
                            "AND (o.objIndSaled = 0 OR o.objIndSaled is null) " +
                            "ORDER BY o.objUnid DESC")
                    .setParameter("eprLoadStatus", ExchangeProcStatus.FINISHED.getCode())
                    .setParameter("epUnid", epUnid)
                    .getResultList();
            List<ObjAndDate> rez = new ArrayList<>();
            for (Object[] object : objects) {
                rez.add(new ObjAndDate((ObjectJPA) object[0], new Date(((Timestamp) object[1]).getTime())));
            }
            return rez;
        } catch (Exception e) {
            logger.error("Can't getObjectForUpdateStatistic by epUnid = {}", epUnid, e);
            return new ArrayList<>();
        } finally {
            closeEntityManager(em);
        }
    }

    /**
     * Получение текущего лота по идентификатору объекта
     *
     * @param objUnid - идентификатор объекта
     * @return - торги
     */
    public Lot getCurrentLotByObjUnid(long objUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return (Lot) em.createNamedQuery("Lot.findCurrentByObjUnid")
                    .setParameter("objUnid", objUnid)
                    .getSingleResult();
        } catch (NoResultException ex) {
        } catch (Throwable ex) {
            logger.error(ex.getMessage(), ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }
}
