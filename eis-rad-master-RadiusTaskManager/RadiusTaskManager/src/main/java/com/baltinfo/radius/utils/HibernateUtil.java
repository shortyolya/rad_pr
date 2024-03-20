package com.baltinfo.radius.utils;

import org.hibernate.Session;
import org.hibernate.internal.SessionFactoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;

/**
 * <p>
 * Обеспечяивает соединение с БД
 * </p>
 *
 * @author Lapenok Igor
 * @since 20.08.2018
 */
public class HibernateUtil {

    public static final Logger logger = LoggerFactory.getLogger(HibernateUtil.class);
    private static HibernateUtil instance = new HibernateUtil();

    /*
     * Конструктор
     */
    private HibernateUtil() {
        try {
            emf = Persistence.createEntityManagerFactory("radiusPU");
        } catch (Throwable ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    /**
     * Возвращает экземпляр объекта
     */
    public static HibernateUtil getInstance() {
        return instance;
    }

    //Фабрика для получения экземпляра EntityManager
    private EntityManagerFactory emf;
    private EntityManagerFactory emfBKR;
    private EntityManagerFactory emfLOLS1;
    private EntityManagerFactory emfLOLS2;
    private EntityManagerFactory emfLOLS3;
    private EntityManagerFactory emfLOLS4;
    private EntityManagerFactory emfLOLS5;
    private EntityManagerFactory emfLO;
    private EntityManagerFactory emfLOUS1;
    private EntityManagerFactory emfFias;
    private EntityManagerFactory emfVitrina;
    private EntityManagerFactory emfDep;
    private EntityManagerFactory emfBkrDep;
    private EntityManagerFactory emfAH;
    private EntityManagerFactory emfRadHolding;

    /*
     * Получение экземпляра EntityManager
     */
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    public EntityManager getEntityManagerBKR() {
        if (emfBKR == null) {
            emfBKR = Persistence.createEntityManagerFactory("bankruptcyPU");
        }
        return emfBKR.createEntityManager();
    }

    public EntityManager getEntityManagerLOLS1() {
        if (emfLOLS1 == null) {
            emfLOLS1 = Persistence.createEntityManagerFactory("lotOnlinePULS1");
        }
        return emfLOLS1.createEntityManager();
    }

    public EntityManager getEntityManagerLOLS2() {
        if (emfLOLS2 == null) {
            emfLOLS2 = Persistence.createEntityManagerFactory("lotOnlinePULS2");
        }
        return emfLOLS2.createEntityManager();
    }

    public EntityManager getEntityManagerLOLS3() {
        if (emfLOLS3 == null) {
            emfLOLS3 = Persistence.createEntityManagerFactory("lotOnlinePULS3");
        }
        return emfLOLS3.createEntityManager();
    }

    public EntityManager getEntityManagerLOLS4() {
        if (emfLOLS4 == null) {
            emfLOLS4 = Persistence.createEntityManagerFactory("lotOnlinePULS4");
        }
        return emfLOLS4.createEntityManager();
    }

    public EntityManager getEntityManagerLOLS5() {
        if (emfLOLS5 == null) {
            emfLOLS5 = Persistence.createEntityManagerFactory("lotOnlinePULS5");
        }
        return emfLOLS5.createEntityManager();
    }

    public EntityManager getEntityManagerLOUS1() {
        if (emfLOUS1 == null) {
            emfLOUS1 = Persistence.createEntityManagerFactory("lotOnlineUserShard1PU");
        }
        return emfLOUS1.createEntityManager();
    }

    public EntityManager getEntityManagerFias() {
        if (emfFias == null) {
            emfFias = Persistence.createEntityManagerFactory("fiasPU");
        }
        return emfFias.createEntityManager();
    }

    public EntityManager getEntityManagerVitrina() {
        if (emfVitrina == null) {
            emfVitrina = Persistence.createEntityManagerFactory("vitrinaPU");
        }
        return emfVitrina.createEntityManager();
    }

    public EntityManager getEntityManagerDep() {
        if (emfDep == null) {
            emfDep = Persistence.createEntityManagerFactory("depPU");
        }
        return emfDep.createEntityManager();
    }

    public EntityManager getEntityManagerBkrDep() {
        if (emfBkrDep == null) {
            emfBkrDep = Persistence.createEntityManagerFactory("bkrDepPU");
        }
        return emfBkrDep.createEntityManager();
    }

    public EntityManager getEntityManagerAH() {
        if (emfAH == null) {
            emfAH = Persistence.createEntityManagerFactory("auctionHousePU");
        }
        return emfAH.createEntityManager();
    }

    public EntityManager getEntityManagerRadHolding() {
        if (emfRadHolding == null) {
            emfRadHolding = Persistence.createEntityManagerFactory("radHoldingPU");
        }
        return emfRadHolding.createEntityManager();
    }

    /*
     * Возвращает jdbc Connection на основе настроек Hibernate
     */
    public Connection getConnection() {
        try {
            return getConnectionProvider().getConnection();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return null;
    }

    public Connection getConnectionDep() {
        try {
            return getConnectionProviderDep().getConnection();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return null;
    }


    public HikariConnectionProvider getConnectionProvider() {
        try {
            Session ses = (Session) emf.createEntityManager().getDelegate();
            SessionFactoryImpl sessionFactory = (SessionFactoryImpl) ses.getSessionFactory();
            return (HikariConnectionProvider) sessionFactory.getConnectionProvider();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return null;
    }

    public HikariConnectionProvider getConnectionProviderBKR() {
        try {
            if (emfBKR == null) {
                emfBKR = Persistence.createEntityManagerFactory("bankruptcyPU");
            }
            Session ses = (Session) emfBKR.createEntityManager().getDelegate();
            SessionFactoryImpl sessionFactory = (SessionFactoryImpl) ses.getSessionFactory();
            return (HikariConnectionProvider) sessionFactory.getConnectionProvider();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return null;
    }

    public HikariConnectionProvider getConnectionProviderBkrDep() {
        try {
            if (emfBkrDep == null) {
                emfBkrDep = Persistence.createEntityManagerFactory("bkrDepPU");
            }
            Session ses = (Session) emfBkrDep.createEntityManager().getDelegate();
            SessionFactoryImpl sessionFactory = (SessionFactoryImpl) ses.getSessionFactory();
            return (HikariConnectionProvider) sessionFactory.getConnectionProvider();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return null;
    }

    public HikariConnectionProvider getConnectionProviderLO() {
        try {
            if (emfLO == null) {
                emfLO = Persistence.createEntityManagerFactory("lotOnlinePU");
            }
            Session ses = (Session) emfLO.createEntityManager().getDelegate();
            SessionFactoryImpl sessionFactory = (SessionFactoryImpl) ses.getSessionFactory();
            return (HikariConnectionProvider) sessionFactory.getConnectionProvider();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return null;
    }

    public HikariConnectionProvider getConnectionProviderFias() {
        try {
            if (emfFias == null) {
                emfFias = Persistence.createEntityManagerFactory("fiasPU");
            }
            Session ses = (Session) emfFias.createEntityManager().getDelegate();
            SessionFactoryImpl sessionFactory = (SessionFactoryImpl) ses.getSessionFactory();
            return (HikariConnectionProvider) sessionFactory.getConnectionProvider();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return null;
    }

    public HikariConnectionProvider getConnectionProviderDep() {
        try {
            if (emfDep == null) {
                emfDep = Persistence.createEntityManagerFactory("depPU");
            }
            Session ses = (Session) emfDep.createEntityManager().getDelegate();
            SessionFactoryImpl sessionFactory = (SessionFactoryImpl) ses.getSessionFactory();
            return (HikariConnectionProvider) sessionFactory.getConnectionProvider();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return null;
    }

    public void closeEmf() {
        if (emf == null) {
            return;
        }
        emf.close();
    }

    public void closeEmfBKR() {
        if (emfBKR == null) {
            return;
        }
        emfBKR.close();
    }

    public void closeEmfFias() {
        if (emfFias == null) {
            return;
        }
        emfFias.close();
    }
}
