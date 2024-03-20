package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.application.configuration.PayDocProperties;
import com.baltinfo.radius.db.constants.AccOperStatuses;
import com.baltinfo.radius.db.constants.LotOnlineProfileType;
import com.baltinfo.radius.db.constants.LotOnlineProvisionType;
import com.baltinfo.radius.db.constants.LotOnlineTransactionType;
import com.baltinfo.radius.db.constants.ParticipantAgentConstant;
import com.baltinfo.radius.db.constants.TradeLists;
import com.baltinfo.radius.db.constants.TypeAccountOperations;
import com.baltinfo.radius.db.constants.TypeSubject;
import com.baltinfo.radius.db.model.ExchangeProcRun;
import com.baltinfo.radius.db.model.dep.AccountDeposit;
import com.baltinfo.radius.db.model.dep.AccountOperation;
import com.baltinfo.radius.db.model.dep.BankBook;
import com.baltinfo.radius.db.model.dep.PayDoc;
import com.baltinfo.radius.db.model.dep.TradeBankBook;
import com.baltinfo.radius.db.model.dep.TypeAccountOperation;
import com.baltinfo.radius.db.model.lotonline.LotInfo;
import com.baltinfo.radius.db.model.lotonline.PaymentAccount;
import com.baltinfo.radius.db.model.lotonline.PaymentTransaction;
import com.baltinfo.radius.db.model.lotonline.Tender;
import com.baltinfo.radius.db.model.lotonline.UserAccountDetails;
import com.baltinfo.radius.utils.HibernateUtil;
import com.baltinfo.radius.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoDepController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(LoDepController.class);

    private final AccountDepositController accountDepositController;
    private final ClDateController clDateController;
    private final PayDocProperties payDocProperties;
    // Форматировние суммы документа в назначении платежа
    private final DecimalFormat df;

    private final String DATEFORMAT = "dd.MM.yyyy HH:mm:ss";
    private final SimpleDateFormat sdfCur = new SimpleDateFormat(DATEFORMAT);
    //Форматируем дату со сдвигом в UTC
    private final SimpleDateFormat sdfUTC = new SimpleDateFormat(DATEFORMAT);

    // Пороговое значение для даты process_date в PaymentTransaction, чтобы в getReturnInfo не тянуть исторические записи
    private final Date thresholdGetReturnInfo;
    // Пороговое значение для даты process_date в PaymentTransaction, чтобы в getRealizationInfo не тянуть исторические записи
    private final Date thresholdGetRealizationInfo;
    public static final Long SYSTEM_ID = 3001L;

    public LoDepController(AccountDepositController accountDepositController, ClDateController clDateController, PayDocProperties payDocProperties) {
        this.accountDepositController = accountDepositController;
        this.clDateController = clDateController;
        this.payDocProperties = payDocProperties;

        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator('-');
        String pattern = "###############.##";
        df = new DecimalFormat(pattern, dfs);
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);
        df.setGroupingUsed(false);

        //Форматируем дату со сдвигом в UTC
        sdfUTC.setTimeZone(TimeZone.getTimeZone("UTC"));

        Calendar cal = Calendar.getInstance();
        // выставляем 2017-03-01 -- накатка версии, содержащей функционал getReturnInfo
        cal.set(2017, 2, 1);
        thresholdGetReturnInfo = cal.getTime();
        // выставляем 2017-04-03 -- накатка версии, содержащей функционал getRealizationInfo
        cal.set(2017, 3, 3);
        thresholdGetRealizationInfo = cal.getTime();
    }

    /**
     * Получение сведений о лицевых счетах
     */
    public Result<String, String> getAccountInfo(ExchangeProcRun epr) {
        EntityManager emLO1 = null;
        EntityManager emDep = null;
        EntityManager emEis = null;

        Date now = new Date();
        long paUnid = epr.getEprPaUnid();

        try {
            emLO1 = HibernateUtil.getInstance().getEntityManagerLOUS1();
            emDep = HibernateUtil.getInstance().getEntityManagerDep();
            emEis = HibernateUtil.getInstance().getEntityManager();

            List<PaymentAccount> pacList = emLO1.createQuery("select a from PaymentAccount a where a.accountNumber is not null and a.eisAccountUnid is null", PaymentAccount.class)
                    .setHint("javax.persistence.fetchgraph", emLO1.getEntityGraph("graph.PaymentAccount.profileFk"))
                    .getResultList();

            Date dateFrom = (Date) emEis.createNamedQuery("ExchangeProcRun.findLastSuccessDateByEpUnid")
                    .setParameter("epUnid", epr.getEpUnid())
                    .getSingleResult();
            dateFrom = convertToUTC(dateFrom);

            List<PaymentAccount> pacUpdateList1 = emLO1.createQuery("select a from PaymentAccount a " +
                            "where a.eisAccountUnid is not null and a.eisAccountUnid > 0 and a.profileFk.lastUpdate > :dateFrom", PaymentAccount.class)
                    .setHint("javax.persistence.fetchgraph", emLO1.getEntityGraph("graph.PaymentAccount.profileFk"))
                    .setParameter("dateFrom", dateFrom)
                    .getResultList();

            emDep.getTransaction().begin();
            emLO1.getTransaction().begin();
            for (PaymentAccount pac : pacList) {
                BankBook bb = new BankBook();
                bb.setDateB(now);
                bb.setIndActual(1);
                bb.setPersCodeB(paUnid);

                Integer status = getBankBookStatusFromLo(emLO1, pac.getProfileFk().getId());

                updateBankBookFields(bb, pac, now, paUnid, status);

                emDep.persist(bb);

                TradeBankBook tbb = new TradeBankBook();
                tbb.setDateB(now);
                tbb.setIndActual(1);
                tbb.setPersCodeB(paUnid);

                updateTradeBankBookFields(tbb, bb, pac, now, paUnid);

                emDep.persist(tbb);

                pac.setEisAccountUnid(bb.getBbUnid());
                emLO1.merge(pac);
            }
            List<String> errors = saveUpdateList(emDep, emLO1, pacUpdateList1, epr, now, paUnid);

            emLO1.getTransaction().commit();
            emDep.getTransaction().commit();
            return Result.ok(StringUtils.join(errors, "\n"));
        } catch (Throwable ex) {
            rollbackTransaction(emDep);
            rollbackTransaction(emLO1);
            logger.error("Error in getAccountInfo:", ex);
            return Result.error(getStackTrace(ex));
        } finally {
            closeEntityManager(emLO1);
            closeEntityManager(emDep);
            closeEntityManager(emEis);
        }
    }

    /**
     * Получение информации о возврате денежных средств по заявлению пользователя
     */
    public Result<String, String> getReturnInfo(ExchangeProcRun epr) {
        EntityManager emLOUserShard = null;
        EntityManager emLOLotShard = null;
        EntityManager emDep = null;

        Date now = new Date();
        long paUnid = epr.getEprPaUnid();

        AccountDeposit accountDeposit = accountDepositController.getAccountDepositByEpUnid(epr.getEpUnid());
        if (accountDeposit == null) {
            return Result.error("Не указан расчетный счет для процедуры");
        }
        try {
            emLOUserShard = HibernateUtil.getInstance().getEntityManagerLOUS1();
            emLOLotShard = HibernateUtil.getInstance().getEntityManagerLOLS3();
            emDep = HibernateUtil.getInstance().getEntityManagerDep();

            emDep.getTransaction().begin();
            emLOUserShard.getTransaction().begin();

            List<PaymentTransaction> ptList1GetReturnInfo = emLOUserShard.createQuery("select a from PaymentTransaction a " +
                            "where a.processDate > :thresholdGetReturnInfo and a.transactionType = :transactionType and a.eisOperId is null", PaymentTransaction.class)
                    .setParameter("thresholdGetReturnInfo", thresholdGetReturnInfo)
                    .setParameter("transactionType", LotOnlineTransactionType.CREDIT.name())
                    .setHint("javax.persistence.fetchgraph", emLOUserShard.getEntityGraph("graph.PaymentTransaction.paymentAccount"))
                    .getResultList();

            List<String> errors = new ArrayList<>();
            for (PaymentTransaction pt : ptList1GetReturnInfo) {
                Result<AccountOperation, String> accOperResult = buildAccountOperation(emDep, pt, now, paUnid);
                if (accOperResult.isError()) {
                    errors.add(accOperResult.getError());
                    continue;
                }
                AccountOperation eisAccOper = accOperResult.getResult();
                eisAccOper.setTaoUnid(TypeAccountOperations.RETURN.getTaoUnid());
                emDep.persist(eisAccOper);

                PayDoc payDoc = buildPayDoc(emDep, emLOLotShard, emLOUserShard, pt, eisAccOper, accountDeposit, now);
                emDep.persist(payDoc);

                pt.setEisOperId(eisAccOper.getAccOperUnid());
                emLOUserShard.merge(pt);
            }

            emLOUserShard.getTransaction().commit();
            emDep.getTransaction().commit();
            return Result.ok(StringUtils.join(errors, "\n"));
        } catch (Throwable ex) {
            rollbackTransaction(emDep);
            rollbackTransaction(emLOUserShard);
            logger.error("Error in getReturnInfo:", ex);
            return Result.error(getStackTrace(ex));
        } finally {
            closeEntityManager(emDep);
            closeEntityManager(emLOUserShard);
            closeEntityManager(emLOLotShard);
        }
    }

    /**
     * Передача информации о поступлении денежных средств на лицевой счет
     */
    public Result<String, String> sendPaymentInfo(ExchangeProcRun epr) {
        EntityManager emLO1 = null;
        EntityManager emDep = null;

        Date now = new Date();
        long paUnid = epr.getEprPaUnid();

        AccountDeposit accountDeposit = accountDepositController.getAccountDepositByEpUnid(epr.getEpUnid());
        if (accountDeposit == null) {
            return Result.error("Не указан расчетный счет для процедуры");
        }
        try {
            emLO1 = HibernateUtil.getInstance().getEntityManagerLOUS1();
            emDep = HibernateUtil.getInstance().getEntityManagerDep();
            List<String> errors = new ArrayList<>();

            List<AccountOperation> aoList = emDep.createQuery("select a from AccountOperation a " +
                            "where a.indActual = 1 and a.tradeListUnid = :tradeListUnid and a.taoUnid.taoUnid =:taoUnid and a.accOperOperSiteId is null", AccountOperation.class)
                    .setParameter("tradeListUnid", TradeLists.LO)
                    .setParameter("taoUnid", TypeAccountOperations.ADMISSION_TO_ACCOONT.getTaoUnid())
                    .setHint("javax.persistence.fetchgraph", emDep.getEntityGraph("graph.AccountOperation.all"))
                    .getResultList();

            emDep.getTransaction().begin();
            emLO1.getTransaction().begin();
            for (AccountOperation eisAccOper : aoList) {
                BankBook bb = eisAccOper.getBbUnid();
                TradeBankBook tbb = emDep.createQuery("select a from TradeBankBook a where a.bbUnid = :bbUnid", TradeBankBook.class)
                        .setParameter("bbUnid", bb.getBbUnid())
                        .getSingleResult();
                PayDoc pp = null;
                try {
                    pp = emDep.createQuery("select a from PayDoc a where a.accOperUnid.accOperUnid = :accOperUnid and a.payDocVersionIndCurrent = true", PayDoc.class)
                            .setParameter("accOperUnid", eisAccOper.getAccOperUnid())
                            .getSingleResult();
                } catch (NoResultException ex) {
                    errors.add("Не удалось получить платежный документ для accOperUnid = " + eisAccOper.getAccOperUnid());
                    logger.error("PayDoc for update not found: accOperUnid = {}", eisAccOper.getAccOperUnid());
                    continue;
                }
                long profileFk = new Long(tbb.getTbbTradeUnid());
                long shardId = profileFk % 1000;
                PaymentAccount pa = emLO1.createQuery("select a from PaymentAccount a where a.profileFk.id = :profileFk", PaymentAccount.class)
                        .setParameter("profileFk", profileFk)
                        .getSingleResult();
                PaymentTransaction trans = new PaymentTransaction();
                trans.setId((new Long(emLO1.createNativeQuery("select nextval('seq_payment_transaction_id')")
                        .getSingleResult() + "")) * 1000 + shardId);
                trans.setPaymentAccount(pa);
                trans.setActorId(SYSTEM_ID);
                trans.setAmount(eisAccOper.getAccOperSum());
                trans.setBlockedAmount(pa.getBlockedAmount() != null ? pa.getBlockedAmount() : BigDecimal.ZERO);
                trans.setCurrentAmount((pa.getCurrentAmount() != null ? pa.getCurrentAmount() : BigDecimal.ZERO).add(eisAccOper.getAccOperSum()));

                pa.setBlockedAmount(trans.getBlockedAmount());
                pa.setCurrentAmount(trans.getCurrentAmount());

                trans.setComment(eisAccOper.getAccOperDescr());
                trans.setDepositId(null);
                trans.setOrderNumber(pp.getPayDocNumber());
                trans.setPaymentDate(convertToUTC(pp.getPayDocDate()));
                trans.setProcessDate(convertToUTC(now));
                trans.setTransactionType(LotOnlineTransactionType.DEBIT.name());

                emLO1.persist(trans);
                emLO1.merge(pa);

                eisAccOper.setAccOperOperSiteId(trans.getId());
                emDep.merge(eisAccOper);
            }

            emLO1.getTransaction().commit();
            emDep.getTransaction().commit();

            return Result.ok(StringUtils.join(errors, "\n"));
        } catch (Throwable ex) {
            rollbackTransaction(emDep);
            rollbackTransaction(emLO1);
            logger.error("Error in sendPaymentInfo:", ex);
            return Result.error(getStackTrace(ex));
        } finally {
            closeEntityManager(emLO1);
            closeEntityManager(emDep);
        }
    }

    /**
     * Получение информации о перечислении средств Организатору торгов
     */
    public Result<String, String> getReturnToOrgInfo(ExchangeProcRun epr) {
        EntityManager emLOUserShard = null;
        EntityManager emLOLotShard = null;
        EntityManager emDep = null;

        Date now = new Date();
        long paUnid = epr.getEprPaUnid();

        AccountDeposit accountDeposit = accountDepositController.getAccountDepositByEpUnid(epr.getEpUnid());
        if (accountDeposit == null) {
            return Result.error("Не указан расчетный счет для процедуры");
        }

        try {
            emLOUserShard = HibernateUtil.getInstance().getEntityManagerLOUS1();
            emLOLotShard = HibernateUtil.getInstance().getEntityManagerLOLS3();
            emDep = HibernateUtil.getInstance().getEntityManagerDep();

            emDep.getTransaction().begin();
            emLOUserShard.getTransaction().begin();

            List<PaymentTransaction> ptList1GetReturnToOrgInfo = emLOUserShard.createQuery("select a from PaymentTransaction a " +
                            " where a.transactionType = :transactionType " +
                            " and a.depositId is not null " +
                            " and (a.depositId.depositType = :depositType or a.depositId.depositType = :depositTypeManual) " +
                            " and a.eisOperId is null", PaymentTransaction.class)
                    .setParameter("transactionType", LotOnlineTransactionType.CREDIT_BLOCKED.name())
                    .setParameter("depositType", LotOnlineProvisionType.LOT_PROVISION.name())
                    .setParameter("depositTypeManual", LotOnlineProvisionType.MANUAL.name())
                    .setHint("javax.persistence.fetchgraph", emLOUserShard.getEntityGraph("graph.PaymentTransaction.paymentAccountAndPaymentGuaranty"))
                    .getResultList();

            List<String> errors = new ArrayList<>();
            for (PaymentTransaction pt : ptList1GetReturnToOrgInfo) {
                Result<AccountOperation, String> accOperResult = buildAccountOperation(emDep, pt, now, paUnid);
                if (accOperResult.isError()) {
                    errors.add(accOperResult.getError());
                    continue;
                }
                AccountOperation eisAccOper = accOperResult.getResult();
                eisAccOper.setTaoUnid(TypeAccountOperations.TRANSFER_TO_ORG.getTaoUnid());
                emDep.persist(eisAccOper);

                PayDoc payDoc = buildPayDoc(emDep, emLOLotShard, emLOUserShard, pt, eisAccOper, accountDeposit, now);


                emDep.persist(payDoc);

                pt.setEisOperId(eisAccOper.getAccOperUnid());
                emLOUserShard.merge(pt);
            }

            emLOUserShard.getTransaction().commit();
            emDep.getTransaction().commit();

            return Result.ok(StringUtils.join(errors, "\n"));
        } catch (Throwable ex) {
            rollbackTransaction(emDep);
            rollbackTransaction(emLOUserShard);
            logger.error("Error in getReturnToOrgInfo:", ex);
            return Result.error(getStackTrace(ex));
        } finally {
            closeEntityManager(emLOUserShard);
            closeEntityManager(emLOLotShard);
            closeEntityManager(emDep);
        }
    }

    /**
     * Получение сведений о реализации
     */
    public Result<String, String> getRealizationInfo(ExchangeProcRun epr) {
        EntityManager emLOUserShard = null;
        EntityManager emDep = null;

        AccountDeposit accountDeposit = accountDepositController.getAccountDepositByEpUnid(epr.getEpUnid());
        if (accountDeposit == null) {
            return Result.error("Не указан расчетный счет для процедуры");
        }

        Date now = new Date();
        long paUnid = epr.getEprPaUnid();

        try {
            emLOUserShard = HibernateUtil.getInstance().getEntityManagerLOUS1();
            emDep = HibernateUtil.getInstance().getEntityManagerDep();

            emDep.getTransaction().begin();
            emLOUserShard.getTransaction().begin();

            List<PaymentTransaction> ptList1GetRealizationInfo = emLOUserShard.createQuery("select a from PaymentTransaction a " +
                            " where a.processDate > :thresholdGetRealizationInfo and a.transactionType = :transactionType " +
                            " and a.depositId is not null " +
                            " and a.depositId.depositType = :depositType " +
                            " and a.eisOperId is null", PaymentTransaction.class)
                    .setParameter("thresholdGetRealizationInfo", thresholdGetRealizationInfo)
                    .setParameter("transactionType", LotOnlineTransactionType.CREDIT_BLOCKED.name())
                    .setParameter("depositType", LotOnlineProvisionType.LOT.name())
                    .setHint("javax.persistence.fetchgraph", emLOUserShard.getEntityGraph("graph.PaymentTransaction.paymentAccountAndPaymentGuaranty"))
                    .getResultList();

            List<String> errors = new ArrayList<>();
            for (PaymentTransaction pt : ptList1GetRealizationInfo) {
                Result<AccountOperation, String> accOperResult = buildAccountOperation(emDep, pt, now, paUnid);
                if (accOperResult.isError()) {
                    errors.add(accOperResult.getError());
                    continue;
                }
                AccountOperation eisAccOper = accOperResult.getResult();
                eisAccOper.setTaoUnid(TypeAccountOperations.REALIZATION.getTaoUnid());
                // статус операции при создании "Не обработано"
                eisAccOper.setAccOperStatus(AccOperStatuses.NOT_PROCESSED.getCode());
                eisAccOper.setAdepUnid(accountDeposit.getAdepUnid());
                emDep.persist(eisAccOper);

                PaymentAccount pac = pt.getPaymentAccount();
                // обновляем BankBook
                BankBook bb = eisAccOper.getBbUnid();
                TradeBankBook tbb = emDep.createQuery("select a from TradeBankBook a where a.bbUnid = :bbUnid", TradeBankBook.class)
                        .setParameter("bbUnid", bb.getBbUnid())
                        .getSingleResult();
                Integer status = getBankBookStatusFromLo(emLOUserShard, pac.getProfileFk().getId());
                updateBankBookFields(bb, pac, now, paUnid, status);
                emDep.merge(bb);

                // обновляем TradeBankBook
                updateTradeBankBookFields(tbb, bb, pac, now, paUnid);
                emDep.merge(tbb);

                pt.setEisOperId(eisAccOper.getAccOperUnid());
                emLOUserShard.merge(pt);
            }

            emLOUserShard.getTransaction().commit();
            emDep.getTransaction().commit();

            return Result.ok(StringUtils.join(errors, "\n"));
        } catch (Throwable ex) {
            rollbackTransaction(emDep);
            rollbackTransaction(emLOUserShard);
            logger.error("Error in getTransferToOperatorInfo:", ex);
            return Result.error(getStackTrace(ex));
        } finally {
            closeEntityManager(emLOUserShard);
            closeEntityManager(emDep);
        }
    }


    private Integer getBankBookStatusFromLo(EntityManager emLo, Long profileFk) {

        BigInteger serviceCount = (BigInteger) emLo.createNativeQuery("select count(s.id) from site_service s where s.state = 'ACTIVE' and s.profile_fk = :prifileFk")
                .setParameter("prifileFk", profileFk)
                .getSingleResult();

        return serviceCount != null && serviceCount.compareTo(BigInteger.ZERO) > 0 ? 1 : 0;
    }

    private void updateTradeBankBookFields(TradeBankBook tbb, BankBook bb, PaymentAccount pac, Date now, long paUnid) {
        tbb.setDateBRec(now);
        tbb.setPersCodeBRec(paUnid);
        tbb.setBbUnid(bb.getBbUnid());
        tbb.setTbbAccount(pac.getProfileFk().getTransactionalAccount());
        // tbbBank field is restricted to 150
        tbb.setTbbBank(pac.getProfileFk().getBankFullName() != null
                ? (pac.getProfileFk().getBankFullName().length() > 150 ? pac.getProfileFk().getBankFullName().substring(0, 150) : pac.getProfileFk().getBankFullName())
                : "");
        tbb.setTbbBankBik(pac.getProfileFk().getBankIdentificationCode());
        tbb.setTbbCorrAccount(pac.getProfileFk().getCorrespondentAccount());
        tbb.setTbbTradeUnid(pac.getProfileFk().getId() + "");
        tbb.setTradeListUnid(TradeLists.LO);
    }

    private void updateBankBookFields(BankBook bb, PaymentAccount pac, Date now, long paUnid, Integer status) {
        bb.setDateBRec(now);
        bb.setPersCodeBRec(paUnid);
        bb.setBbAccount(pac.getAccountNumber() + "");
        // bbOwner field is restricted to 200
        bb.setBbOwner(pac.getProfileFk().getCompanyFullName() != null ? (pac.getProfileFk().getCompanyFullName().length() > 200 ? pac.getProfileFk().getCompanyFullName().substring(0, 200) : pac.getProfileFk().getCompanyFullName()) : pac.getProfileFk().getPersonFio());
        bb.setBbOwnerInn(pac.getProfileFk().getInn());
        bb.setBbOwnerKpp(pac.getProfileFk().getKpp());
        bb.setBbOwnerSnils(null);//???
        bb.setBbOwnerType(getTypesUnid(pac.getProfileFk().getProfileType()));
        // bbAddress field is restricted to 2000
        bb.setBbAddress(pac.getProfileFk().getCompanyLegalAddress() != null ? (pac.getProfileFk().getCompanyLegalAddress().length() > 2000 ? pac.getProfileFk().getCompanyLegalAddress().substring(0, 2000) : pac.getProfileFk().getCompanyLegalAddress()) : "");
        // bbPhone field is restricted to 200; из UserProfile приходит "person_phone", length = 50 -- оставляем как есть
        bb.setBbPhone(pac.getProfileFk().getPersonPhone() != null ? pac.getProfileFk().getPersonPhone() : "");
        // bbEmail field is restricted to 200; из UserProfile приходит "person_email", length = 255 -- нужно отсекать
        bb.setBbEmail(pac.getProfileFk().getPersonEmail() != null ? (pac.getProfileFk().getPersonEmail().length() > 200 ? pac.getProfileFk().getPersonEmail().substring(0, 200) : pac.getProfileFk().getPersonEmail()) : "");
        // bbOwnerSname field is restricted to 2000
        bb.setBbOwnerSname(pac.getProfileFk().getCompanyShortName() != null ? (pac.getProfileFk().getCompanyShortName().length() > 2000 ? pac.getProfileFk().getCompanyShortName().substring(0, 2000) : pac.getProfileFk().getCompanyShortName()) : "");
        // bbOwnerSite field is restricted to 2000
        bb.setBbOwnerSite(pac.getProfileFk().getCompanyWebsite() != null ? (pac.getProfileFk().getCompanyWebsite().length() > 2000 ? pac.getProfileFk().getCompanyWebsite().substring(0, 2000) : pac.getProfileFk().getCompanyWebsite()) : "");
        bb.setBbStatus(status);
    }

    private List<String> saveUpdateList(EntityManager emEis, EntityManager emLo, List<PaymentAccount> pacList, ExchangeProcRun epr, Date now, Long paUnid) {
        List<String> errors = new ArrayList<>();
        for (PaymentAccount pac : pacList) {
            BankBook bb = emEis.find(BankBook.class, pac.getEisAccountUnid());
            if (bb != null) {

                Integer status = getBankBookStatusFromLo(emLo, pac.getProfileFk().getId());
                updateBankBookFields(bb, pac, now, paUnid, status);
                emEis.merge(bb);
            } else {
                errors.add("Лицевой счет не найден в БД ЕИС: bbUnid = " + pac.getEisAccountUnid() + ", PaymentAccount [id = " + pac.getId() + "]");
                logger.error("BankBook for update not found: bbUnid = {}, PaymentAccount [id = {}]", pac.getEisAccountUnid(), pac.getId());
                continue;
            }

            TradeBankBook tbb = accountDepositController.getTradeBankBookByBankBook(pac.getEisAccountUnid());
            if (tbb != null) {
                updateTradeBankBookFields(tbb, bb, pac, now, paUnid);
                emEis.merge(tbb);
            } else {
                errors.add("Лицевой счет площадки не найден в БД ЕИС: bbUnid = " + pac.getEisAccountUnid() + ", PaymentAccount [id = " + pac.getId() + "]");
                logger.error("TradeBankBook for update not found: bbUnid = {}, PaymentAccount [id = {}]", pac.getEisAccountUnid(), pac.getId());
            }
        }
        return errors;
    }


    private Result<AccountOperation, String> buildAccountOperation(EntityManager emDep, PaymentTransaction pt, Date now, Long paUnid) {
        TradeBankBook tbb;
        BankBook bb;

        try {
            tbb = (TradeBankBook) emDep.createQuery("select tbb from TradeBankBook tbb where tbb.indActual = 1 and tbb.tbbTradeUnid = :tbbTradeUnid and tbb.tradeListUnid = :tradeListUnid")
                    .setParameter("tbbTradeUnid", pt.getPaymentAccount().getProfileFk().getId() + "")
                    .setParameter("tradeListUnid", TradeLists.LO)
                    .getSingleResult();
            bb = emDep.find(BankBook.class, tbb.getBbUnid());
        } catch (NoResultException ex) {
            logger.error("Error get TradeBankBook in buildAccountOperation by tbbTradeUnid = {}:", pt.getPaymentAccount().getProfileFk().getId(), ex);
            return Result.error("Не удалось получить лицевой счет для tbbTradeUnid = " + pt.getPaymentAccount().getProfileFk().getId());
        }

        Date ptProcessDate = null;
        try {
            // Получаем необходимые даты из PaymentTransaction со сдвигом в серверное время
            ptProcessDate = convertFromUTC(pt.getProcessDate());
        } catch (ParseException ex) {
            logger.error("Error convertFromUTC in buildAccountOperation by PaymentTransaction[id = {}]", pt.getId(), ex);
            return Result.error("Не удалось сконвертировать время для поля processDate при обработке PaymentTransaction с id = " + pt.getId());
        }

        // ptProcessDate должен быть не null, в противном случае логируем и переходим к следующей PaymentTransaction
        if (ptProcessDate == null) {
            logger.error("Field ProcessDate is empty by PaymentTransaction[id = {}]", pt.getId());
            return Result.error("Пустое поле processDate после convertFromUTC при обработке PaymentTransaction с id = " + pt.getId());
        }

        AccountOperation oper = new AccountOperation();
        oper.setDateB(now);
        oper.setIndActual(1);
        oper.setDateBRec(now);
        oper.setPersCodeB(paUnid);
        oper.setPersCodeBRec(paUnid);
        oper.setAccOperDescr(formatPayDocPurpose(pt.getComment()));
        oper.setAccOperIndReturn(0);
        oper.setAccOperOperSiteId(pt.getId());
        oper.setAccOperSum(pt.getAmount());
        oper.setAccOperTime(ptProcessDate);
        oper.setBbUnid(bb);
        oper.setTradeListUnid(TradeLists.LO);

        return Result.ok(oper);
    }

    private PayDoc buildPayDoc(EntityManager emDep, EntityManager emLOLotShard, EntityManager emLOUserShard,
                               PaymentTransaction loPaymentTransaction, AccountOperation eisAccOper, AccountDeposit accountDeposit, Date now) {
        PayDoc pd = new PayDoc();
        pd.setAccOperUnid(eisAccOper);
        pd.setDateB(now);
        pd.setIndActual(1);
        pd.setPersCodeB(ParticipantAgentConstant.SYSTEM.getPaUnid());
        pd.setPersCodeBRec(ParticipantAgentConstant.SYSTEM.getPaUnid());
        pd.setAdepUnid(accountDeposit.getAdepUnid());
        pd.setBbUnid(eisAccOper.getBbUnid());
        pd.setPayDocVersionNum("1");
        pd.setPayDocVersionDate(now);
        pd.setPayDocVersionIndCurrent(true);
        pd.setPayDocName("Платежное поручение");
        pd.setPayDocNumber((String) emDep.createNativeQuery("select dep.f_get_next_out_pay_doc_number(:adepUnid)")
                .setParameter("adepUnid", accountDeposit.getAdepUnid())
                .getSingleResult());


        Date dpDate = null;
        try {
            // Получаем необходимые даты из PaymentTransaction со сдвигом в серверное время
            dpDate = convertFromUTC(loPaymentTransaction.getPaymentDate());
        } catch (ParseException ex) {
            logger.error("Error convertFromUTC in buildAccountOperation by PaymentTransaction[id = {}]", loPaymentTransaction.getId(), ex);
        }
        if (dpDate == null) {
            dpDate = eisAccOper.getAccOperTime();
            TypeAccountOperation tao = emDep.find(TypeAccountOperation.class, eisAccOper.getTaoUnid());
            if (tao.getTaoBias() != null) {
                if (tao.getTaoIndWorkDay() != null && tao.getTaoIndWorkDay() == 1) {
                    dpDate = clDateController.getDatePlusWorkDays(dpDate, tao.getTaoBias());
                } else {
                    dpDate = new Date(dpDate.getTime() + tao.getTaoBias() * 86400000);
                }
            }
        }
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

        if (eisAccOper.getTaoUnid().equals(TypeAccountOperations.RETURN.getTaoUnid())) {
            pd.setPayDocPurpose("Возврат денежных средств по заявлению пользователя. Сумма " + df.format(loPaymentTransaction.getAmount().abs()) + ". Без налога (НДС)");
        } else {
            if (StringUtils.isNotEmpty(loPaymentTransaction.getPaymentPurpose())) {
                pd.setPayDocPurpose(loPaymentTransaction.getPaymentPurpose());
            } else {
                pd.setPayDocPurpose(formatPayDocPurpose(loPaymentTransaction.getComment()));
            }
        }

        if (eisAccOper.getTaoUnid().equals(TypeAccountOperations.TRANSFER_TO_ORG.getTaoUnid())) {

            LotInfo lotInfo = getLotInfoById(emLOLotShard, loPaymentTransaction.getDepositId().getLotId());
            UserAccountDetails userAccountDetails = null;
            if (lotInfo != null && lotInfo.getAccountDetailsId() != null) {
                userAccountDetails = emLOUserShard.find(UserAccountDetails.class, lotInfo.getAccountDetailsId());
            }
            if (userAccountDetails != null) {
                pd.setPayDocRecAccount(userAccountDetails.getRecipientAccount());
                pd.setPayDocRecBank(userAccountDetails.getRecipientBankName());
                pd.setPayDocRecBankBik(userAccountDetails.getRecipientBankBic());
                pd.setPayDocRecCorrAccount(userAccountDetails.getRecipientBankCorrAccount());
                pd.setPayDocRecInn(userAccountDetails.getRecipientInn());
                pd.setPayDocRecKpp(userAccountDetails.getRecipientKpp());
                pd.setPayDocRecipient(userAccountDetails.getRecipientName());
                pd.setPayDocRecOktmo(userAccountDetails.getRecipientOktmo());
                pd.setPayDocRecKbk(userAccountDetails.getRecipientKbk());

                pd.setPayDocPayerStatus(userAccountDetails.getStatusDocumentSettlement());
                pd.setPayDocCode(userAccountDetails.getNpaCode());
                pd.setPayDocTaxablePeriod(userAccountDetails.getTaxCode());
                pd.setPayDocBaseIndicator(userAccountDetails.getValueReasonPayment());
                pd.setPayDocDocNumIndicator(userAccountDetails.getIndicatorDocumentNumber());
                pd.setPayDocDocDateIndicator(userAccountDetails.getIndicatorDocumentDate());
            } else {
                pd.setPayDocRecAccount(loPaymentTransaction.getRecipAccount());
                pd.setPayDocRecBank(loPaymentTransaction.getRecipBankName());
                pd.setPayDocRecBankBik(loPaymentTransaction.getRecipBankBik());
                pd.setPayDocRecCorrAccount(loPaymentTransaction.getRecipBankAccount());
                pd.setPayDocRecInn(loPaymentTransaction.getRecipInn());
                pd.setPayDocRecKpp(loPaymentTransaction.getRecipKpp());
                pd.setPayDocRecipient(loPaymentTransaction.getRecipName());
                pd.setPayDocRecOktmo(loPaymentTransaction.getRecipOktmo());
                pd.setPayDocRecKbk(loPaymentTransaction.getRecipKbk());

                pd.setPayDocPayerStatus(payDocProperties.getPayerStatus());
                pd.setPayDocCode(payDocProperties.getCode());
                pd.setPayDocTaxablePeriod(payDocProperties.getTaxablePeriod());
                pd.setPayDocBaseIndicator(payDocProperties.getBaseIndicator());
                pd.setPayDocDocNumIndicator(payDocProperties.getDocNumIndicator());
                pd.setPayDocDocDateIndicator(payDocProperties.getDocDateIndicator());
            }
            if (lotInfo != null) {
                pd.setPayDocLotNumber(lotInfo.getLotCode());
                pd.setPayDocProfileId(lotInfo.getTenderFk().getProfileId());
            }
        } else {
            pd.setPayDocRecAccount(loPaymentTransaction.getRecipAccount());
            pd.setPayDocRecBank(loPaymentTransaction.getRecipBankName());
            pd.setPayDocRecBankBik(loPaymentTransaction.getRecipBankBik());
            pd.setPayDocRecCorrAccount(loPaymentTransaction.getRecipBankAccount());
            pd.setPayDocRecInn(loPaymentTransaction.getRecipInn());
            pd.setPayDocRecKpp(loPaymentTransaction.getRecipKpp());
            pd.setPayDocRecipient(loPaymentTransaction.getRecipName());
            pd.setPayDocRecOktmo(loPaymentTransaction.getRecipOktmo());
            pd.setPayDocRecKbk(loPaymentTransaction.getRecipKbk());
        }

        // Сумма документа
        pd.setPayDocSum(loPaymentTransaction.getAmount());

        return pd;
    }

    private LotInfo getLotInfoById(EntityManager em, long lotId) {
        try {
            return (LotInfo) em.createQuery("SELECT l from LotInfo l WHERE l.id = :lotId")
                    .setParameter("lotId", lotId)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        } catch (Throwable ex) {
            logger.error("Error getLotInfoById by lotId = {}", lotId, ex);
        }
        return null;
    }

    private String formatPayDocPurpose(String purpose) {
        // Вырезаем из назначения ссылку и оставляем вместо нее только текст ссылки
        String urlRegex = "^.*(<a href=\\\".*\\\">(.*)<\\/a>).*$";
        Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
        Matcher urlMatcher = pattern.matcher(purpose);

        if (urlMatcher.matches()) {
            try {
                String link = urlMatcher.group(1);
                String linkContent = urlMatcher.group(2);
                String newPurpose = purpose.replace(link, linkContent);
                return newPurpose;
            } catch (Exception ex) {
                logger.error("Error extract url from purpose = {}", purpose, ex);
            }
        }
        return purpose;
    }

    private Short getTypesUnid(String profileType) {
        Long typesUnid = null;
        if (LotOnlineProfileType.PHYSICAL.name().equals(profileType)) {
            typesUnid = TypeSubject.FL.getUnid();
        } else if (LotOnlineProfileType.LEGAL.name().equals(profileType)) {
            typesUnid = TypeSubject.YL.getUnid();
        } else if (LotOnlineProfileType.INDIVIDUAL.name().equals(profileType)) {
            typesUnid = TypeSubject.IP.getUnid();
        }
        return typesUnid.shortValue();
    }

    private Date convertToUTC(Date dateIn) throws ParseException {
        if (dateIn == null) {
            return null;
        }
        return sdfCur.parse(sdfUTC.format(dateIn));
    }

    private Date convertFromUTC(Date dateIn) throws ParseException {
        if (dateIn == null) {
            return null;
        }
        return sdfUTC.parse(sdfCur.format(dateIn));
    }

    private String getStackTrace(Throwable ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        return sw.toString();
    }

}
