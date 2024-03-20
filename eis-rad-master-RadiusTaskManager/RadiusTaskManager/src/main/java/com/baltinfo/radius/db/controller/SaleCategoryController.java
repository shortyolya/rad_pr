package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.db.model.ObjSaleCategory;
import com.baltinfo.radius.db.model.SaleCategory;
import com.baltinfo.radius.utils.HibernateUtil;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Date;
import java.util.List;

/**
 * @author Suvorina Aleksandra
 * @since 27.01.2021
 */
public class SaleCategoryController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(SaleCategoryController.class);

    public SaleCategoryController() {

    }

    public List<SaleCategory> getFirstLevelCategories() {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return em.createNamedQuery("SaleCategory.findFirstLevelCategories", SaleCategory.class)
                    .getResultList();
        } catch (Exception ex) {
            logger.error("Error getFirstLevelCategories.", ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    public List<SaleCategory> getChildCategories(Long parentScUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return em.createNamedQuery("SaleCategory.findChild", SaleCategory.class)
                    .setParameter("scUnid", parentScUnid)
                    .getResultList();
        } catch (Exception ex) {
            logger.error("Error getChildCategories. parentScUnid = {}", parentScUnid, ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    public SaleCategory getSaleCategoryByScCode(String scCode) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return em.createNamedQuery("SaleCategory.findByScCode", SaleCategory.class)
                    .setParameter("scCode", scCode)
                    .getSingleResult();
        } catch (Exception ex) {
            logger.error("Error getSaleCategoryByScCode. scCode = {}", scCode, ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    public List<SaleCategory> getSaleCategoriesForDelete() {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return em.createNamedQuery("SaleCategory.findByScIndDelete", SaleCategory.class)
                    .getResultList();
        } catch (Exception ex) {
            logger.error("Error getSaleCategoriesForDelete", ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    public Result<Void, String> deleteCategories() {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            em.getTransaction().begin();
            Date now = new Date();
            List<SaleCategory> saleCategories = em.createNamedQuery("SaleCategory.findByScIndDelete", SaleCategory.class)
                    .getResultList();
            for (SaleCategory categoryForDelete : saleCategories) {
                if (categoryForDelete.getScNewCode() == null || categoryForDelete.getScNewCode().isEmpty()) {
                    rollbackTransaction(em);
                    return Result.error("Не указан код категории для замены для категории " + categoryForDelete.getScCode() + " " + categoryForDelete.getScName());
                }
                SaleCategory newCategory;
                try {
                    newCategory = em.createNamedQuery("SaleCategory.findByScCode", SaleCategory.class)
                            .setParameter("scCode", categoryForDelete.getScNewCode())
                            .getSingleResult();
                } catch (NoResultException ex) {
                    logger.error("No SaleCategory found by scCode = {}", categoryForDelete.getScNewCode(), ex);
                    rollbackTransaction(em);
                    return Result.error("Не найдена категория для замены с кодом " + categoryForDelete.getScNewCode() + " для категории " + categoryForDelete.getScCode() + " " + categoryForDelete.getScName());
                }

                List<ObjSaleCategory> objSaleCategories = em.createNamedQuery("ObjSaleCategory.findByScUnid", ObjSaleCategory.class)
                        .setParameter("scUnid", categoryForDelete.getScUnid())
                        .getResultList();

                for (ObjSaleCategory objSaleCategory : objSaleCategories) {
                    objSaleCategory.setIndActual(0);
                    objSaleCategory.setDateBRec(now);
                    objSaleCategory.setPersCodeBRec(1L);
                    em.merge(objSaleCategory);

                    ObjSaleCategory newOsc = new ObjSaleCategory();
                    newOsc.setObjUnid(objSaleCategory.getObjUnid());
                    newOsc.setScUnid(newCategory);
                    newOsc.setIndActual(1);
                    objSaleCategory.setDateB(now);
                    objSaleCategory.setPersCodeB(1L);
                    em.persist(newCategory);
                }

                categoryForDelete.setIndActual(0);
                categoryForDelete.setDateBRec(now);
                categoryForDelete.setPersCodeBRec(1L);
                em.merge(categoryForDelete);
            }
            em.getTransaction().commit();
            return Result.ok();
        } catch (Exception ex) {
            logger.error("Error deleteCategories", ex);
            rollbackTransaction(em);
            return Result.error(ex.getMessage());

        } finally {
            closeEntityManager(em);
        }
    }

}
