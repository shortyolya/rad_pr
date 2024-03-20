package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.db.constants.AccOperIndReturn;
import com.baltinfo.radius.db.constants.AccOperStatuses;
import com.baltinfo.radius.db.constants.BkrTypeAccOper;
import com.baltinfo.radius.db.constants.ParticipantAgentConstant;
import com.baltinfo.radius.db.constants.TradeLists;
import com.baltinfo.radius.db.constants.TypeAccountOperations;
import com.baltinfo.radius.db.model.ExchangeProcRun;
import com.baltinfo.radius.db.model.bankruptcy.VApplicationAll;
import com.baltinfo.radius.db.model.bankruptcy.VAuctionLotAll;
import com.baltinfo.radius.db.model.bankruptcy.dep.AccOper;
import com.baltinfo.radius.db.model.bankruptcy.dep.WbAccount;
import com.baltinfo.radius.db.model.dep.AccountDeposit;
import com.baltinfo.radius.db.model.dep.AccountOperation;
import com.baltinfo.radius.db.model.dep.BankBook;
import com.baltinfo.radius.db.model.dep.PayDoc;
import com.baltinfo.radius.db.model.dep.TradeBankBook;
import com.baltinfo.radius.db.model.dep.TypeAccountOperation;
import com.baltinfo.radius.db.model.lotonline.PaymentTransaction;
import com.baltinfo.radius.utils.HibernateUtil;
import com.baltinfo.radius.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class BkrDepController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(BkrDepController.class);

    private final AccountDepositController accountDepositController;
    private final ClDateController clDateController;
    // Форматировние суммы документа в назначении платежа
    private final DecimalFormat df;

    public BkrDepController(AccountDepositController accountDepositController, ClDateController clDateController) {
        this.accountDepositController = accountDepositController;
        this.clDateController = clDateController;

        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator('-');
        String pattern = "###############.##";
        df = new DecimalFormat(pattern, dfs);
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);
        df.setGroupingUsed(false);
    }

    /**
     * Получение сведений о лицевых счетах
     */
    public Result<String, String> getAccountInfo(ExchangeProcRun epr) {
        EntityManager emBKR = null;
        EntityManager emDep = null;
        EntityManager emEis = null;
        Connection conn = null;
        CallableStatement cs = null;

        Date now = new Date();
        long paUnid = epr.getEprPaUnid();
        try {
            emBKR = HibernateUtil.getInstance().getEntityManagerBkrDep();
            emDep = HibernateUtil.getInstance().getEntityManagerDep();
            emEis = HibernateUtil.getInstance().getEntityManager();

            List<WbAccount> accList = emBKR.createNamedQuery("WbAccount.findNew", WbAccount.class)
                    .getResultList();

            Date lastUpdate = (Date) emEis.createNamedQuery("ExchangeProcRun.findLastSuccessDateByEpUnid")
                    .setParameter("epUnid", epr.getEpUnid())
                    .getSingleResult();

            List<WbAccount> accListUpdate = emBKR.createNamedQuery("WbAccount.findUpdateByDateFrom", WbAccount.class)
                    .setParameter("dateFrom", lastUpdate)
                    .getResultList();

            emDep.getTransaction().begin();
            conn = HibernateUtil.getInstance().getConnectionProviderBkrDep().getConnection();
            conn.setAutoCommit(false);
            List<String> errors = new ArrayList<>();
            for (WbAccount wa : accList) {
                BankBook bb = new BankBook();
                bb.setDateB(now);
                bb.setIndActual(1);
                bb.setPersCodeB(paUnid);

                updateBankBookFields(bb, wa, now, paUnid);

                emDep.persist(bb);

                TradeBankBook tbb = new TradeBankBook();
                tbb.setDateB(now);
                tbb.setIndActual(1);
                tbb.setPersCodeB(paUnid);

                updateTradeBankBookFields(tbb, bb, wa, now, paUnid);

                emDep.persist(tbb);

                cs = conn.prepareCall("{call DEP.PKG_DEP.webbider_account_sended(?,?,?,?)}");
                cs.setLong(1, wa.getWbUnid());
                cs.setLong(2, bb.getBbUnid());
                cs.registerOutParameter(3, Types.INTEGER);
                cs.registerOutParameter(4, Types.VARCHAR);
                cs.executeUpdate();
                long code = cs.getLong(3);
                String ret = cs.getString(4);
                if (!ret.equals("SUCCESS")) {
                    errors.add("Ошибка при запуске процедуры DEP.PKG_DEP.webbider_account_sended: " + code + ", message: " + ret);
                    logger.error("Stored procedure DEP.PKG_DEP.webbider_account_sended call error " + code + ", message: " + ret);
                    rollbackTransaction(emDep);
                    rollbackConnection(conn);
                    return Result.error(StringUtils.join(errors, "\n"));
                }
                cs.close();
            }
            for (WbAccount wa : accListUpdate) {
                BankBook bb = emDep.find(BankBook.class, wa.getWbEisAccountUnid());
                if (bb != null) {
                    updateBankBookFields(bb, wa, now, paUnid);
                    emDep.merge(bb);
                } else {
                    errors.add("Лицевой счет не найден в БД ЕИС: Идентификатор счета = " + wa.getWbEisAccountUnid() +
                            ", идентификатор участника на ЭТП Банкротство = " + wa.getWbUnid() +
                            ", номер счета " + wa.getAccountCode());
                    logger.error("BankBook for update not found: bbUnid = {}, wbUnid = {}", wa.getWbEisAccountUnid(), wa.getWbUnid());
                    continue;
                }

                TradeBankBook tbb = accountDepositController.getTradeBankBookByBankBook(wa.getWbEisAccountUnid());
                if (tbb != null) {
                    updateTradeBankBookFields(tbb, bb, wa, now, paUnid);
                    emDep.merge(tbb);
                } else {
                    errors.add("Лицевой счет площадки не найден в БД ЕИС: bbUnid = " + wa.getWbEisAccountUnid() + ", wbUnid = " + wa.getWbUnid());
                    logger.error("TradeBankBook for update not found: bbUnid = {}, wbUnid = {}", wa.getWbEisAccountUnid(), wa.getWbUnid());
                }
            }
            conn.commit();
            emDep.getTransaction().commit();
            return Result.ok(StringUtils.join(errors, "\n"));
        } catch (Throwable ex) {
            rollbackTransaction(emDep);
            rollbackConnection(conn);
            logger.error("Error in getAccountInfo:", ex);
            return Result.error(getStackTrace(ex));
        } finally {
            closeEntityManager(emBKR);
            closeEntityManager(emDep);
            closeEntityManager(emEis);
            closeCallableStatement(cs);
            closeConnection(conn);
        }
    }

    /**
     * Получение информации о возврате денежных средств по заявлению пользователя
     */
    public Result<String, String> getReturnInfo(ExchangeProcRun epr) {
        EntityManager emBKR = null;
        EntityManager emDep = null;
        Connection conn = null;

        Date now = new Date();
        long paUnid = epr.getEprPaUnid();

        AccountDeposit accountDeposit = accountDepositController.getAccountDepositByEpUnid(epr.getEpUnid());
        if (accountDeposit == null) {
            return Result.error("Не указан расчетный счет для процедуры");
        }
        try {
            emBKR = HibernateUtil.getInstance().getEntityManagerBkrDep();
            emDep = HibernateUtil.getInstance().getEntityManagerDep();
            List<String> errors = new ArrayList<>();
            List<AccOper> acoListTaoReturn = emBKR.createQuery("select a from AccOper a " +
                            "where a.taoUnid = :taoUnid and a.acoOperEisId is null", AccOper.class)
                    .setParameter("taoUnid", BkrTypeAccOper.TAO_RETURN.getBkrUnid())
                    .getResultList();

            emDep.getTransaction().begin();
            conn = HibernateUtil.getInstance().getConnectionProviderBkrDep().getConnection();
            conn.setAutoCommit(false);

            for (AccOper bkrAccOper : acoListTaoReturn) {
                Result<AccountOperation, String> accOperResult = buildAccountOperation(emDep, bkrAccOper, now, paUnid);
                if (accOperResult.isError()) {
                    errors.add(accOperResult.getError());
                    continue;
                }
                AccountOperation eisAccOPer = accOperResult.getResult();
                eisAccOPer.setTaoUnid(TypeAccountOperations.RETURN.getTaoUnid());
                eisAccOPer.setAccOperSum(bkrAccOper.getAcoSumm());
                emDep.persist(eisAccOPer);

                PayDoc payDoc = buildPayDoc(emDep, bkrAccOper, eisAccOPer, accountDeposit, now);
                emDep.persist(payDoc);

                Result<Void, String> updateBkrResult = updateBkrAccOper(conn, bkrAccOper, eisAccOPer);
                if (updateBkrResult.isError()) {
                    errors.add(updateBkrResult.getError());
                    rollbackTransaction(emDep);
                    rollbackConnection(conn);
                    return Result.error(StringUtils.join(errors, "\n"));
                }
            }

            conn.commit();
            emDep.getTransaction().commit();
            return Result.ok(StringUtils.join(errors, "\n"));
        } catch (Throwable ex) {
            rollbackTransaction(emDep);
            rollbackConnection(conn);
            logger.error("Error in getReturnInfo:", ex);
            return Result.error(getStackTrace(ex));
        } finally {
            closeEntityManager(emBKR);
            closeEntityManager(emDep);
            closeConnection(conn);
        }
    }

    /**
     * Передача информации о поступлении денежных средств на лицевой счет
     *
     * @return
     */
    public Result<String, String> sendPaymentInfo(ExchangeProcRun epr) {
        EntityManager emDep = null;
        Connection conn = null;
        CallableStatement cs = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

        AccountDeposit accountDeposit = accountDepositController.getAccountDepositByEpUnid(epr.getEpUnid());
        if (accountDeposit == null) {
            return Result.error("Не указан расчетный счет для процедуры");
        }

        try {
            emDep = HibernateUtil.getInstance().getEntityManagerDep();
            List<String> errors = new ArrayList<>();

            List<AccountOperation> accountOperations = emDep.createQuery("select a from AccountOperation a " +
                            "where a.indActual = 1 and a.tradeListUnid = :tradeListUnid " +
                            "and a.taoUnid.taoUnid = :taoUnid and a.accOperOperSiteId is null", AccountOperation.class)
                    .setParameter("tradeListUnid", TradeLists.BKR)
                    .setParameter("taoUnid", TypeAccountOperations.ADMISSION_TO_ACCOONT.getTaoUnid())
                    .getResultList();

            List<String> accountNums = new ArrayList<>();

            emDep.getTransaction().begin();
            conn = HibernateUtil.getInstance().getConnectionProviderBkrDep().getConnection();
            conn.setAutoCommit(false);
            for (AccountOperation eisAccOper : accountOperations) {
                BankBook bb = eisAccOper.getBbUnid();
                accountNums.add(bb.getBbAccount());
                TradeBankBook tbb;
                PayDoc payDoc;
                try {
                    tbb = (TradeBankBook) emDep.createQuery("select a from TradeBankBook a where a.bbUnid = :bbUnid and a.indActual = 1")
                            .setParameter("bbUnid", bb.getBbUnid())
                            .getSingleResult();
                } catch (NoResultException ex) {
                    errors.add("Не удалось получить лицевой счет для bbUnid = " + bb.getBbUnid());
                    logger.error("TradeBankBook for update not found: bbUnid = {}", bb.getBbUnid());
                    continue;
                }
                try {
                    payDoc = emDep.createQuery("select a from PayDoc a where a.accOperUnid.accOperUnid = :accOperUnid  and a.indActual = 1 and a.payDocVersionIndCurrent = true", PayDoc.class)
                            .setParameter("accOperUnid", eisAccOper.getAccOperUnid())
                            .getSingleResult();
                } catch (NoResultException ex) {
                    errors.add("Не удалось получить платежный документ для accOperUnid = " + eisAccOper.getAccOperUnid());
                    logger.error("PayDoc for update not found: accOperUnid = {}", eisAccOper.getAccOperUnid());
                    continue;
                }
                cs = conn.prepareCall("{call DEP.PKG_DEP.PROC_CREATE_ACO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

                cs.setLong(1, BkrTypeAccOper.TAO_INCOME.getBkrUnid());
                cs.setBigDecimal(2, null);
                cs.setLong(3, new Long(tbb.getTbbTradeUnid()));
                cs.setBigDecimal(4, eisAccOper.getAccOperSum());
                cs.setString(5, eisAccOper.getAccOperDescr());
                cs.setLong(6, 0);
                cs.setString(7, payDoc.getPayDocRecipient());
                cs.setString(8, payDoc.getPayDocRecInn());
                cs.setString(9, payDoc.getPayDocRecKpp());
                cs.setString(10, payDoc.getPayDocRecAccount());
                cs.setString(11, payDoc.getPayDocRecBank());
                cs.setString(12, payDoc.getPayDocRecBankBik());
                cs.setString(13, payDoc.getPayDocRecCorrAccount());
                cs.setString(14, payDoc.getPayDocNumber());
                cs.setString(15, payDoc.getPayDocDate() == null ? null : sdf.format(payDoc.getPayDocDate()));
                cs.setBigDecimal(16, null);

                cs.registerOutParameter(17, Types.INTEGER);
                cs.registerOutParameter(18, Types.VARCHAR);
                cs.executeUpdate();
                Long retCode = cs.getLong(17);
                String ret = cs.getString(18);

                if (!ret.equals("SUCCESS")) {
                    errors.add("Ошибка при запуске процедуры DEP.PKG_DEP.PROC_CREATE_ACO: " + retCode + " message: " + ret);
                    logger.error("Stored procedure DEP.PKG_DEP.PROC_CREATE_ACO call error " + retCode + " message: " + ret);
                    rollbackTransaction(emDep);
                    rollbackConnection(conn);
                    return Result.error(StringUtils.join(errors, "\n"));
                }
                cs.close();

                eisAccOper.setAccOperOperSiteId(retCode);
                emDep.merge(eisAccOper);
            }

            conn.commit();
            emDep.getTransaction().commit();

            if (!accountNums.isEmpty()) {
                Result<Void, String> result = blockApplMoney(conn, accountNums);
                if (result.isError()) {
                    errors.add("Ошибка при автоматическом блокировании д/с: " + result.getError());
                    rollbackConnection(conn);
                    return Result.error(StringUtils.join(errors, "\n"));
                }
            }
            return Result.ok(StringUtils.join(errors, "\n"));
        } catch (Throwable ex) {
            rollbackTransaction(emDep);
            rollbackConnection(conn);
            logger.error("Error in sendPaymentInfo:", ex);
            return Result.error(getStackTrace(ex));
        } finally {
            closeEntityManager(emDep);
            closeConnection(conn);
        }
    }

    /**
     * Передача подтверждения факта возврата денежных средств
     */
    public Result<String, String> sendReturnInfo(ExchangeProcRun epr) {

        EntityManager emDep = null;
        Connection conn = null;
        CallableStatement cs = null;

        AccountDeposit accountDeposit = accountDepositController.getAccountDepositByEpUnid(epr.getEpUnid());
        if (accountDeposit == null) {
            return Result.error("Не указан расчетный счет для процедуры");
        }

        try {
            emDep = HibernateUtil.getInstance().getEntityManagerDep();
            List<String> errors = new ArrayList<>();

            List<AccountOperation> aoList = emDep.createQuery("select a from AccountOperation a " +
                            "where a.indActual = 1 and a.tradeListUnid = :tradeListUnid and a.taoUnid = :taoUnid" +
                            " and a.accOperIndReturn = :accOperIndReturn", AccountOperation.class)
                    .setParameter("tradeListUnid", TradeLists.BKR)
                    .setParameter("taoUnid", TypeAccountOperations.RETURN.getTaoUnid())
                    .setParameter("accOperIndReturn", AccOperIndReturn.PAYDOC_SENT.getCode())
                    .getResultList();

            emDep.getTransaction().begin();
            conn = HibernateUtil.getInstance().getConnectionProviderBkrDep().getConnection();
            conn.setAutoCommit(false);

            for (AccountOperation eisAccOper : aoList) {
                PayDoc payDoc = emDep.createQuery("select a from PayDoc a where a.accAccOperUnid.accOperUnid = :accAccOperUnid " +
                                "and a.indActual = 1 and a.payDocVersionIndCurrent = true", PayDoc.class)
                        .setParameter("accAccOperUnid", eisAccOper.getAccOperUnid())
                        .getSingleResult();

                cs = conn.prepareCall("{call DEP.PKG_DEP.PROC_UPDATE_ACO(?,?,?,?,?)}");
                cs.setLong(1, eisAccOper.getAccOperOperSiteId());
                cs.setString(2, payDoc.getPayDocNumber());
                cs.setDate(3, new java.sql.Date(payDoc.getPayDocDate().getTime()));

                cs.registerOutParameter(4, Types.INTEGER);
                cs.registerOutParameter(5, Types.VARCHAR);
                cs.executeUpdate();
                long retCode = cs.getLong(4);
                String ret = cs.getString(5);

                if (!ret.equals("SUCCESS")) {
                    errors.add("Ошибка при запуске процедуры DEP.PKG_DEP.PROC_UPDATE_ACO: " + retCode + " message: " + ret);
                    logger.error("Stored procedure DEP.PKG_DEP.PROC_UPDATE_ACO call error " + retCode + " message: " + ret);
                    rollbackTransaction(emDep);
                    rollbackConnection(conn);
                    return Result.error(StringUtils.join(errors, "\n"));
                }
                cs.close();

                eisAccOper.setAccOperIndReturn(AccOperIndReturn.RESULT_TRANSMITTED.getCode());
                emDep.merge(eisAccOper);
            }

            conn.commit();
            emDep.getTransaction().commit();
            return Result.ok(StringUtils.join(errors, "\n"));
        } catch (Throwable ex) {
            rollbackTransaction(emDep);
            rollbackConnection(conn);
            logger.error("Error in sendReturnInfo:", ex);
            return Result.error(getStackTrace(ex));
        } finally {
            closeEntityManager(emDep);
            closeConnection(conn);
        }
    }

    /**
     * Получение сведений о перечислении средств Организатору торгов
     */
    public Result<String, String> getReturnToOrgInfo(ExchangeProcRun epr) {
        EntityManager emBKRDep = null;
        EntityManager emBKR = null;
        EntityManager emDep = null;
        Connection conn = null;

        AccountDeposit accountDeposit = accountDepositController.getAccountDepositByEpUnid(epr.getEpUnid());
        if (accountDeposit == null) {
            return Result.error("Не указан расчетный счет для процедуры");
        }

        Date now = new Date();
        long paUnid = epr.getEprPaUnid();

        try {
            emBKRDep = HibernateUtil.getInstance().getEntityManagerBkrDep();
            emBKR = HibernateUtil.getInstance().getEntityManagerBKR();
            emDep = HibernateUtil.getInstance().getEntityManagerDep();

            List<String> errors = new ArrayList<>();

            List<AccOper> acoListTaoDebit = emBKRDep.createQuery("select a from AccOper a " +
                            "where a.taoUnid = :taoUnid and a.acoOperEisId is null", AccOper.class)
                    .setParameter("taoUnid", BkrTypeAccOper.TAO_DEBIT.getBkrUnid())
                    .getResultList();

            emDep.getTransaction().begin();
            conn = HibernateUtil.getInstance().getConnectionProviderBkrDep().getConnection();
            conn.setAutoCommit(false);

            for (AccOper bkrAccOper : acoListTaoDebit) {
                Result<AccountOperation, String> accOperResult = buildAccountOperation(emDep, bkrAccOper, now, paUnid);
                if (accOperResult.isError()) {
                    errors.add(accOperResult.getError());
                    continue;
                }
                AccountOperation eisAccOper = accOperResult.getResult();
                eisAccOper.setTaoUnid(TypeAccountOperations.TRANSFER_TO_ORG.getTaoUnid());
                eisAccOper.setAccOperSum(bkrAccOper.getAcoSumm());
                emDep.persist(eisAccOper);

                PayDoc payDoc = buildPayDoc(emDep, bkrAccOper, eisAccOper, accountDeposit, now);
                if (bkrAccOper.getApplicatUnid() != null) {
                    VApplicationAll bkrAppl = emBKR.find(VApplicationAll.class, bkrAccOper.getApplicatUnid());
                    if (bkrAppl != null) {
                        VAuctionLotAll bkrLot = emBKR.find(VAuctionLotAll.class, bkrAppl.getLotUnid());
                        payDoc.setPayDocLotNumber(bkrLot.getLotNoticeNum());
                        payDoc.setPayDocWbUnid(bkrLot.getWbUnid());
                    }
                }

                emDep.persist(payDoc);

                Result<Void, String> updateBkrResult = updateBkrAccOper(conn, bkrAccOper, eisAccOper);
                if (updateBkrResult.isError()) {
                    errors.add(updateBkrResult.getError());
                    rollbackTransaction(emDep);
                    rollbackConnection(conn);
                    return Result.error(StringUtils.join(errors, "\n"));
                }
            }

            conn.commit();
            emDep.getTransaction().commit();

            return Result.ok(StringUtils.join(errors, "\n"));
        } catch (Throwable ex) {
            rollbackTransaction(emDep);
            rollbackConnection(conn);
            logger.error("Error in getReturnToOrgInfo:", ex);
            return Result.error(getStackTrace(ex));
        } finally {
            closeEntityManager(emBKRDep);
            closeEntityManager(emBKR);
            closeEntityManager(emDep);
            closeConnection(conn);
        }
    }

    /**
     * Получение сведений о перечислении средств Оператору Банкротство
     */
    public Result<String, String> getTransferToOperatorInfo(ExchangeProcRun epr) {

        EntityManager emBKR = null;
        EntityManager emDep = null;
        Connection conn = null;

        AccountDeposit accountDeposit = accountDepositController.getAccountDepositByEpUnid(epr.getEpUnid());
        if (accountDeposit == null) {
            return Result.error("Не указан расчетный счет для процедуры");
        }

        Date now = new Date();
        long paUnid = epr.getEprPaUnid();

        try {
            emBKR = HibernateUtil.getInstance().getEntityManagerBkrDep();
            emDep = HibernateUtil.getInstance().getEntityManagerDep();

            List<AccOper> acoList = emBKR.createQuery("select a from AccOper a " +
                            "where a.taoUnid = :taoUnid and a.acoOperEisId is null", AccOper.class)
                    .setParameter("taoUnid", BkrTypeAccOper.TAO_TRANSFER_TO_OPERATOR.getBkrUnid())
                    .getResultList();

            emDep.getTransaction().begin();
            conn = HibernateUtil.getInstance().getConnectionProviderBkrDep().getConnection();
            conn.setAutoCommit(false);

            List<String> errors = new ArrayList<>();
            for (AccOper bkrAccOper : acoList) {
                Result<AccountOperation, String> accOperResult = buildAccountOperation(emDep, bkrAccOper, now, paUnid);
                if (accOperResult.isError()) {
                    errors.add(accOperResult.getError());
                    continue;
                }
                AccountOperation eisAccOper = accOperResult.getResult();
                eisAccOper.setTaoUnid(TypeAccountOperations.REALIZATION.getTaoUnid());
                eisAccOper.setAccOperStatus(AccOperStatuses.NOT_PROCESSED.getCode());
                eisAccOper.setAdepUnid(accountDeposit.getAdepUnid());
                eisAccOper.setAccOperSum(bkrAccOper.getAcoSumm().abs());
                emDep.persist(eisAccOper);

                Result<Void, String> updateBkrResult = updateBkrAccOper(conn, bkrAccOper, eisAccOper);
                if (updateBkrResult.isError()) {
                    errors.add(updateBkrResult.getError());
                    rollbackTransaction(emDep);
                    rollbackConnection(conn);
                    return Result.error(StringUtils.join(errors, "\n"));
                }
            }

            conn.commit();
            emDep.getTransaction().commit();

            return Result.ok(StringUtils.join(errors, "\n"));
        } catch (Throwable ex) {
            rollbackTransaction(emDep);
            rollbackConnection(conn);
            logger.error("Error in getTransferToOperatorInfo:", ex);
            return Result.error(getStackTrace(ex));
        } finally {
            closeEntityManager(emBKR);
            closeEntityManager(emDep);
            closeConnection(conn);
        }
    }

    private Result<AccountOperation, String> buildAccountOperation(EntityManager emDep, AccOper ao, Date now, Long paUnid) {
        TradeBankBook tbb;
        BankBook bb;
        try {
            tbb = (TradeBankBook) emDep.createQuery("select tbb from TradeBankBook tbb where tbb.indActual = 1 and tbb.tbbTradeUnid = :tbbTradeUnid and tbb.tradeListUnid = :tradeListUnid")
                    .setParameter("tbbTradeUnid", ao.getWbUnid() + "")
                    .setParameter("tradeListUnid", TradeLists.BKR)
                    .getSingleResult();
            bb = emDep.find(BankBook.class, tbb.getBbUnid());
        } catch (NoResultException ex) {
            logger.error("Error in buildAccountOperation by tbbTradeUnid = {}:", ao.getWbUnid(), ex);
            return Result.error("Не удалось получить лицевой счет для tbbTradeUnid = " + ao.getWbUnid());
        }

        AccountOperation oper = new AccountOperation();
        oper.setDateB(now);
        oper.setIndActual(1);
        oper.setDateBRec(now);
        oper.setPersCodeB(paUnid);
        oper.setPersCodeBRec(paUnid);
        oper.setAccOperDescr(ao.getAcoNote());
        oper.setAccOperIndReturn(0);
        oper.setAccOperOperSiteId(ao.getAcoUnid());
        oper.setAccOperTime(ao.getAcoTime());
        oper.setBbUnid(bb);
        oper.setTradeListUnid(TradeLists.BKR);

        return Result.ok(oper);
    }

    private PayDoc buildPayDoc(EntityManager emDep, AccOper bkrAccOper, AccountOperation eisAccOper, AccountDeposit accountDeposit, Date now) {
        PayDoc pd = new PayDoc();
        pd.setAccOperUnid(eisAccOper);
        pd.setDateB(now);
        pd.setIndActual(1);
        pd.setDateBRec(now);
        pd.setPersCodeB(ParticipantAgentConstant.SYSTEM.getPaUnid());
        pd.setPersCodeBRec(ParticipantAgentConstant.SYSTEM.getPaUnid());
        pd.setAdepUnid(accountDeposit.getAdepUnid());
        pd.setBbUnid(eisAccOper.getBbUnid());
        pd.setPayDocVersionNum("1");
        pd.setPayDocVersionDate(now);
        pd.setPayDocVersionIndCurrent(true);

        pd.setPayDocName("Платежное поручение");

        pd.setPayDocNumber((String) emDep.createNativeQuery("SELECT dep.f_get_next_out_pay_doc_number(:adepUnid)")
                .setParameter("adepUnid", accountDeposit.getAdepUnid())
                .getSingleResult());

        TypeAccountOperation tao = emDep.find(TypeAccountOperation.class, eisAccOper.getTaoUnid());
        Date dpDate = getDpDate(bkrAccOper, tao);
        pd.setPayDocDate(dpDate);
        pd.setPayDocIndOut(true);
        pd.setPayDocLinking(0);
        pd.setPayDocIndDownload(0);

        pd.setPayDocPayer(accountDeposit.getAdepOrgName());
        pd.setPayDocPayerAccount(accountDeposit.getAdepAccount());
        pd.setPayDocPayerBank(accountDeposit.getAdepBank());
        pd.setPayDocPayerBankBik(accountDeposit.getAdepBankBik());
        pd.setPayDocPayerCorrAccount(accountDeposit.getAdepCorrAccount());
        pd.setPayDocPayerInn(accountDeposit.getAdepOrgInn());
        pd.setPayDocPayerKpp(accountDeposit.getAdepOrgKpp());

        if (bkrAccOper.getTaoUnid().equals(BkrTypeAccOper.TAO_RETURN.getBkrUnid())) {
            pd.setPayDocPurpose("Возврат денежных средств по заявлению пользователя. Сумма " + df.format(bkrAccOper.getAcoSumm().abs()) + ". Без налога (НДС)");
        } else {
            if (Objects.isNull(bkrAccOper.getAcoPurpose()) || bkrAccOper.getAcoPurpose().isEmpty()) {
                pd.setPayDocPurpose(bkrAccOper.getAcoNote());
            } else {
                pd.setPayDocPurpose(bkrAccOper.getAcoPurpose());
            }
        }

        pd.setPayDocRecAccount(bkrAccOper.getAcoRecipAccount());
        pd.setPayDocRecBank(bkrAccOper.getAcoRecipBankName());
        pd.setPayDocRecBankBik(bkrAccOper.getAcoRecipBankBik());
        pd.setPayDocRecCorrAccount(bkrAccOper.getAcoRecipBankAccount());
        pd.setPayDocRecInn(bkrAccOper.getAcoRecipInn());
        pd.setPayDocRecKpp(bkrAccOper.getAcoRecipKpp());
        pd.setPayDocRecipient(bkrAccOper.getAcoRecipName());
        // Сумма документа
        pd.setPayDocSum(bkrAccOper.getAcoSumm().abs());
        return pd;
    }

    private Result<Void, String> updateBkrAccOper(Connection conn, AccOper bkrAccOper, AccountOperation eisAccOper) {

        CallableStatement cs = null;
        try {
            cs = conn.prepareCall("{call DEP.PKG_DEP.acc_oper_sended(?,?,?,?)}");
            cs.setLong(1, bkrAccOper.getAcoUnid());
            cs.setLong(2, eisAccOper.getAccOperUnid());
            cs.registerOutParameter(3, Types.INTEGER);
            cs.registerOutParameter(4, Types.VARCHAR);
            cs.executeUpdate();
            long code = cs.getLong(3);
            String ret = cs.getString(4);
            if (!ret.equals("SUCCESS")) {
                logger.error("Stored procedure DEP.PKG_DEP.acc_oper_sended call error:" + code + ", message: " + ret);
                return Result.error("Ошибка при запуске процедуры DEP.PKG_DEP.acc_oper_sended: " + code + ", message: " + ret);
            }
            cs.close();
            return Result.ok();
        } catch (Exception ex) {
            logger.error("Error in processAccOper by acoUnid = {}", bkrAccOper.getAcoUnid(), ex);
            return Result.error("Не удалось выполнить передачу операции в ЕИС РАД для acoUnid = " + bkrAccOper.getAcoUnid());
        } finally {
            closeCallableStatement(cs);
        }
    }

    /**
     * Блокирование денег на лицевых сетах, при наличии неоплаченных заявок
     */
    private Result<Void, String> blockApplMoney(Connection conn, List<String> accountNums) {
        try {
            for (String accountNum : accountNums) {
                try (CallableStatement cs = conn.prepareCall("{call DEP.PKG_DEP.PROC_BLOCK_BY_WB_ACCOUNT_NUM(?,?,?)}")) {
                    cs.setString(1, accountNum);
                    cs.registerOutParameter(2, Types.INTEGER);
                    cs.registerOutParameter(3, Types.VARCHAR);
                    cs.executeUpdate();
                    Long retCode = cs.getLong(2);
                    String ret = cs.getString(3);
                    if (!ret.equals("SUCCESS")) {
                        logger.info("Stored procedure call DEP.PKG_DEP.PROC_BLOCK_BY_WB_ACCOUNT_NUM retCode: {}, message: {}", retCode, ret);
                    }
                    if (!retCode.equals(0L)) {
                        return Result.error("Ошибка при запуске процедуры DEP.PKG_DEP.PROC_BLOCK_BY_WB_ACCOUNT_NUM: " + retCode + ", message: " + ret);
                    }
                }
            }
            conn.commit();
            return Result.ok();
        } catch (Throwable ex) {
            logger.error("Can't block appl money", ex);
            return Result.error("Can't block appl money. Error message: " + ex.getMessage());
        }
    }

    private Date getDpDate(AccOper bkrAccOper, TypeAccountOperation eisTao) {
        Date dpDate = bkrAccOper.getAcoTime();
        boolean indAsv = bkrAccOper.getAcoPayDocNum() != null && bkrAccOper.getAcoPayDocNum().equals("ASV");
        boolean indBkrRad = bkrAccOper.getAcoPayDocNum() != null && bkrAccOper.getAcoPayDocNum().equals("BKR_RAD");
        boolean indPrivOwnRad = bkrAccOper.getAcoPayDocNum() != null && bkrAccOper.getAcoPayDocNum().equals("PRIV_OWN_RAD");
        if (indAsv && bkrAccOper.getTaoUnid().equals(BkrTypeAccOper.TAO_DEBIT.getBkrUnid())) {
            dpDate = clDateController.getDatePlusWorkDays(dpDate, 2);
        } else if ((indBkrRad || indPrivOwnRad) && bkrAccOper.getTaoUnid().equals(BkrTypeAccOper.TAO_DEBIT.getBkrUnid())) {
            // Банкротство и ЧС, где организатор РАД
            dpDate = clDateController.getDatePlusWorkDays(dpDate, 1);
        } else {
            if (eisTao.getTaoBias() != null) {
                if (eisTao.getTaoIndWorkDay() != null && eisTao.getTaoIndWorkDay() == 1) {
                    dpDate = clDateController.getDatePlusWorkDays(dpDate, eisTao.getTaoBias());
                } else {
                    dpDate = new Date(dpDate.getTime() + eisTao.getTaoBias() * 86400000);
                }
            }
        }
        return dpDate;
    }

    private void updateBankBookFields(BankBook bb, WbAccount wa, Date now, long paUnid) {
        bb.setDateBRec(now);
        bb.setPersCodeBRec(paUnid);
        bb.setBbAccount(wa.getWbAccountNum() + "");
        // bbOwner field is restricted to 200
        bb.setBbOwner(wa.getPartyName() != null ? (wa.getPartyName().length() > 200 ? wa.getPartyName().substring(0, 200) : wa.getPartyName()) : "");
        bb.setBbOwnerInn(wa.getPartyInn());
        bb.setBbOwnerKpp(wa.getPartyCodeKpp());
        bb.setBbOwnerSnils(wa.getPartySnils());
        bb.setBbOwnerType(wa.getTypesUnid());
        bb.setBbAddress(wa.getPartyAddrLegal());
        bb.setBbPhone(wa.getPartyPhone());
        bb.setBbEmail(wa.getPartyEmail());
        bb.setBbStatus(wa.getIndHasAccreditation());
    }

    private void updateTradeBankBookFields(TradeBankBook tbb, BankBook bb, WbAccount wa, Date now, long paUnid) {
        tbb.setDateBRec(now);
        tbb.setPersCodeBRec(paUnid);
        tbb.setBbUnid(bb.getBbUnid());
        tbb.setTbbAccount(wa.getAccountCode());
        // tbbBank field is restricted to 150
        tbb.setTbbBank(wa.getBankName() != null ? (wa.getBankName().length() > 150 ? wa.getBankName().substring(0, 150) : wa.getBankName()) : "");
        tbb.setTbbBankBik(wa.getBankBic());
        tbb.setTbbCorrAccount(wa.getBankCAccount());
        tbb.setTbbTradeUnid(wa.getWbUnid() + "");
        tbb.setTradeListUnid(TradeLists.BKR);
    }

    private String getStackTrace(Throwable ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        return sw.toString();
    }

}
