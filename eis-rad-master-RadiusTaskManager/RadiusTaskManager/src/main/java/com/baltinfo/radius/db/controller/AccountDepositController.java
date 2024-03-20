package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.db.model.dep.AccountDeposit;
import com.baltinfo.radius.db.model.dep.TradeBankBook;
import com.baltinfo.radius.utils.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class AccountDepositController  extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(AccountDepositController.class);

    /**
     * Получение расчетного счета по идентификатору процедуры информационного обмена
     *
     * @param epUnid идентификатор процедуры информационного обмена
     * @return расчетный счет
     */
    public AccountDeposit getAccountDepositByEpUnid(Long epUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManagerDep();
            return em.createQuery("SELECT a FROM AccountDeposit a, TradeAccount t " +
                            "WHERE a.adepUnid = t.adepUnid " +
                            "AND t.tacEpUnid = :epUnid " +
                            "AND t.indActual = 1", AccountDeposit.class)
                    .setParameter("epUnid", epUnid)
                    .getSingleResult();
        } catch (NoResultException ex) {
            logger.warn("AccountDeposit not found by epUnid = {}", epUnid);
        } catch (Throwable ex) {
            logger.error("Error when getting AccountDeposit by epUnid = {}", epUnid, ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    public TradeBankBook getTradeBankBookByBankBook(Long bbUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManagerDep();
            List<TradeBankBook> res = em.createQuery("select tbb from TradeBankBook tbb where tbb.bbUnid = :bbUnid and tbb.indActual = 1")
                    .setParameter("bbUnid", bbUnid)
                    .getResultList();

            // лицевых счетов по площадкам может быть несколько, берём первый
            if (res != null && !res.isEmpty()) {
                return res.get(0);
            }
        } catch (Throwable ex) {
            logger.error("Error when getting TradeBankBook by bbUnid = {}", bbUnid, ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }


}
