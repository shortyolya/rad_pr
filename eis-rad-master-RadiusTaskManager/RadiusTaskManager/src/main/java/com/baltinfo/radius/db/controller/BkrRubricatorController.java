package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.db.model.bankruptcy.Rubricator;
import com.baltinfo.radius.utils.HibernateUtil;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.util.List;

/**
 * @author Suvorina Aleksandra
 * @since 27.01.2021
 */
public class BkrRubricatorController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(BkrRubricatorController.class);

    private static final String SUCCESS_RETURN_CODE = "SUCCESS";

    public List<Rubricator> getAllRubricator() {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManagerBKR();
            return em.createNamedQuery("Rubricator.findAll", Rubricator.class)
                    .getResultList();
        } catch (Exception ex) {
            logger.error("Error getAllRubricator", ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    public Result<Rubricator, String> createRubricator(Rubricator rubricator) {
        EntityManager em = null;
        Connection conn = null;
        try {
            em = HibernateUtil.getInstance().getEntityManagerBKR();
            conn = HibernateUtil.getInstance().getConnectionProviderBKR().getConnection();
            em.getTransaction().begin();
            CallableStatement cs = conn.prepareCall("{call WEB.CREATE_ENTITIES.PROC_CREATE_RUBRICATOR(?,?,?,?,?,?,?,?,?,?)}");

            cs.setBigDecimal(1, rubricator.getRubRubrUnid() == null ? null : new BigDecimal(rubricator.getRubRubrUnid()));
            cs.setLong(2, rubricator.getOcUnid());
            cs.setString(3, rubricator.getRubrName());
            cs.setLong(4, rubricator.getRubrLevel());
            cs.setString(5, rubricator.getRubrSingleClassifCode());
            cs.setString(6, rubricator.getRubrFullName());
            cs.setString(7, rubricator.getRubrCode());
            cs.setString(8, rubricator.getRubrAsvCode());

            cs.registerOutParameter(9, Types.DECIMAL);
            cs.registerOutParameter(10, Types.VARCHAR);

            cs.executeUpdate();
            Long rubrUnid = cs.getLong(9);
            String ret = cs.getString(10);

            if (!SUCCESS_RETURN_CODE.equals(ret)) {
                rollbackTransaction(em);
                logger.error("Error execute procedure WEB.CREATE_ENTITIES.PROC_CREATE_RUBRICATOR. Error = {}", ret);
                return Result.error(ret);
            }

            conn.commit();
            em.getTransaction().commit();

            Rubricator newRubricator = em.find(Rubricator.class, rubrUnid);
            return Result.ok(newRubricator);
        } catch (Exception ex) {
            logger.error("Error createRubricator.", ex);
            rollbackConnection(conn);
            rollbackTransaction(em);
            return Result.error(ex.getMessage());
        } finally {
            closeConnection(conn);
            closeEntityManager(em);
        }
    }

    public Result<Rubricator, String> updateRubricator(Rubricator rubricator) {
        EntityManager em = null;
        Connection conn = null;
        try {
            em = HibernateUtil.getInstance().getEntityManagerBKR();
            conn = HibernateUtil.getInstance().getConnectionProviderBKR().getConnection();
            em.getTransaction().begin();
            CallableStatement cs = conn.prepareCall("{call WEB.CREATE_ENTITIES.PROC_UPDATE_RUBRICATOR(?,?,?,?,?,?,?,?)}");

            cs.setLong(1, rubricator.getRubrUnid());
            cs.setBigDecimal(2, rubricator.getRubRubrUnid() == null ? null : new BigDecimal(rubricator.getRubRubrUnid()));
            cs.setLong(3, rubricator.getRubrLevel());
            cs.setString(4, rubricator.getRubrName());
            cs.setString(5, rubricator.getRubrFullName());
            cs.setString(6, rubricator.getRubrCode());
            cs.setString(7, rubricator.getRubrAsvCode());
            cs.registerOutParameter(8, Types.VARCHAR);
            cs.executeUpdate();

            String ret = cs.getString(8);

            if (!SUCCESS_RETURN_CODE.equals(ret)) {
                rollbackTransaction(em);
                logger.error("Error execute procedure WEB.CREATE_ENTITIES.PROC_UPDATE_RUBRICATOR. Error = {}", ret);
                return Result.error(ret);
            }

            conn.commit();
            em.getTransaction().commit();

            Rubricator updatedRubricator = em.find(Rubricator.class, rubricator.getRubrUnid());
            return Result.ok(updatedRubricator);
        } catch (Exception ex) {
            logger.error("Error updateRubricator.", ex);
            rollbackConnection(conn);
            rollbackTransaction(em);
            return Result.error(ex.getMessage());
        } finally {
            closeConnection(conn);
            closeEntityManager(em);
        }
    }

    public Result<Void, String> deleteRubricator(Rubricator rubrForDelete, String newRubricatorCode) {
        EntityManager em = null;
        Connection conn = null;
        try {
            Rubricator newRubricator = getRubricatorByCode(newRubricatorCode);
            if (newRubricator == null) {
                return Result.error("По коду " + newRubricatorCode + " не найдена рубрика для замены ");
            }

            em = HibernateUtil.getInstance().getEntityManagerBKR();
            conn = HibernateUtil.getInstance().getConnectionProviderBKR().getConnection();
            em.getTransaction().begin();
            CallableStatement cs = conn.prepareCall("{call WEB.CREATE_ENTITIES.PROC_DELETE_RUBRICATOR(?,?,?)}");

            cs.setLong(1, rubrForDelete.getRubrUnid());
            cs.setLong(2, newRubricator.getRubrUnid());
            cs.registerOutParameter(3, Types.VARCHAR);
            cs.executeUpdate();

            String ret = cs.getString(3);

            if (!SUCCESS_RETURN_CODE.equals(ret)) {
                rollbackTransaction(em);
                logger.error("Error execute procedure WEB.CREATE_ENTITIES.PROC_DELETE_RUBRICATOR. Error = {}", ret);
                return Result.error(ret);
            }

            conn.commit();
            em.getTransaction().commit();

            return Result.ok();
        } catch (Exception ex) {
            logger.error("Error deleteRubricator.", ex);
            rollbackConnection(conn);
            rollbackTransaction(em);
            return Result.error(ex.getMessage());
        } finally {
            closeConnection(conn);
            closeEntityManager(em);
        }
    }

    private Rubricator getRubricatorByCode(String singleClassifCode) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManagerBKR();

            return em.createNamedQuery("Rubricator.findBySingleClassifCode", Rubricator.class)
                    .setParameter("rubrSingleClassifCode", singleClassifCode)
                    .getSingleResult();
        } catch(NoResultException ex) {
            logger.warn("No Rubricator found by singleClassifCode = {}", singleClassifCode);
        } catch (Exception ex) {
            logger.error("Error getRubricatorByCodeby singleClassifCode = {}", singleClassifCode, ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }


}
