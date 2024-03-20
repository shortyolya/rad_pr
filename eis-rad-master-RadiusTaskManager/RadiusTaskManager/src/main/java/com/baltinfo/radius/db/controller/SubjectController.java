package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.db.constants.TypeSubject;
import com.baltinfo.radius.db.model.Subject;
import com.baltinfo.radius.utils.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Suvorina Aleksandra
 * @since 11.10.2018
 */
public class SubjectController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(SubjectController.class);

    public List<Subject> findFlByPassportOrSnils(String series, String number, String snils) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            Query query = em.createQuery("SELECT s FROM Subject s " +
                    "WHERE s.indActual = 1 " +
                    "and (s.subSnils like :snils or ( s.subDocumSeries like :series and s.subDocumNumber like :number)) " +
                    "and s.typesUnid = :typesUnid")
                    .setParameter("snils", snils)
                    .setParameter("series", series)
                    .setParameter("number", number)
                    .setParameter("typesUnid", TypeSubject.FL.getUnid());
            return query.getResultList();
        } catch (Throwable ex) {
            logger.error("Error findFlByPassportOrSnils. series = {}, number = {}, snils = {}",
                    series, number, snils, ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    public List<Subject> findFlByPassport(String series, String number) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            Query query = em.createQuery("SELECT s FROM Subject s " +
                    "WHERE s.indActual = 1 " +
                    "and s.subDocumSeries like :series and s.subDocumNumber like :number and s.typesUnid = :typesUnid")
                    .setParameter("series", series)
                    .setParameter("number", number)
                    .setParameter("typesUnid", TypeSubject.FL.getUnid());
            return query.getResultList();
        } catch (Throwable ex) {
            logger.error("Error findFlByPassport. series = {}, number = {}",
                    series, number, ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    public List<Subject> findFlBySnils(String snils) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            Query query = em.createQuery("SELECT s FROM Subject s " +
                    "WHERE s.indActual = 1 and s.subSnils like :snils and s.typesUnid = :typesUnid")
                    .setParameter("snils", snils)
                    .setParameter("typesUnid", TypeSubject.FL.getUnid());
            return query.getResultList();
        } catch (Throwable ex) {
            logger.error("Error findFlBySnils. snils = {}", snils, ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    public List<Subject> findIpByInn(String inn) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            Query query = em.createQuery("SELECT s FROM Subject s " +
                    "WHERE s.indActual = 1 and s.subInn like :inn and s.typesUnid = :typesUnid")
                    .setParameter("inn", inn)
                    .setParameter("typesUnid", TypeSubject.IP.getUnid());
            return query.getResultList();
        } catch (Throwable ex) {
            logger.error("Error findIpByInn. inn = {}", inn, ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }


    public List<Subject> findYlByInnAndKpp(String inn, String kpp) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            Query query = em.createQuery("SELECT s FROM Subject s " +
                    "WHERE s.indActual = 1 and s.subInn like :inn and s.subCodeKpp like :kpp and s.typesUnid = :typesUnid")
                    .setParameter("inn", inn)
                    .setParameter("kpp", kpp)
                    .setParameter("typesUnid", TypeSubject.YL.getUnid());
            return query.getResultList();
        } catch (Throwable ex) {
            logger.error("Error findYlByInnAndKpp. inn = {}, kpp = {}", inn, kpp, ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    public List<Subject> findYlByInnAndFIO(String inn, String subName) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            Query query = em.createQuery("SELECT s FROM Subject s " +
                    "WHERE s.indActual = 1 and s.subInn like :inn and s.typesUnid = :typesUnid" +
                    " and LOWER(TRIM(s.subName)) like :subName")
                    .setParameter("inn", inn)
                    .setParameter("subName", subName.trim().toLowerCase())
                    .setParameter("typesUnid", TypeSubject.FL.getUnid());
            return query.getResultList();
        } catch (Throwable ex) {
            logger.error("Error findYlByInnAndFIO. inn = {}, FIO = {}", inn, subName, ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    public List<Subject> findByOgrn(String ogrn, Long typesUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            Query query = em.createQuery("SELECT s FROM Subject s " +
                    "WHERE s.indActual = 1 and s.subOgrn like :ogrn and s.typesUnid = :typesUnid")
                    .setParameter("ogrn", ogrn)
                    .setParameter("typesUnid", typesUnid);
            return query.getResultList();
        } catch (Throwable ex) {
            logger.error("Error findIpByOgrn. ogrn = {},", ogrn, ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }
}
