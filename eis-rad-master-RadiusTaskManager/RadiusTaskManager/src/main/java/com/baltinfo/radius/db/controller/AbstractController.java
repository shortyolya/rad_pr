package com.baltinfo.radius.db.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.sql.CallableStatement;
import java.sql.Connection;

/**
 * <p>
 * Базовый контроллер БД
 * </p>
 *
 * @author Lapenok Igor
 * @since 20.08.2018
 */
public class AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(AbstractController.class);

    protected void rollbackTransaction(EntityManager em) {
        try {
            if (em != null) {
                em.getTransaction().rollback();
            }
        } catch (Exception ex) {
            logger.debug("Error when rollback transaction", ex);
        }
    }

    protected void closeEntityManager(EntityManager em) {
        try {
            if (em != null) {
                em.close();
            }
        } catch (Exception ex) {
            logger.debug("Error when close EntityManager", ex);
        }
    }

    protected void closeCallableStatement(CallableStatement cs) {
        try {
            if (cs != null) {
                cs.close();
            }
        } catch (Exception ex) {
            logger.debug("Error when close CallableStatement", ex);
        }
    }

    protected void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception ex) {
            logger.debug("Error when close Connection", ex);
        }
    }

    protected void rollbackConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.rollback();
            }
        } catch (Exception ex) {
            logger.debug("Error when rollback Connection", ex);
        }
    }
}
