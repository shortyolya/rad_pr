package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.bankruptcy.BkrAppIndPermit;
import com.baltinfo.radius.bankruptcy.BkrNonExecReason;
import com.baltinfo.radius.bankruptcy.BkrTypeAdditionalData;
import com.baltinfo.radius.bankruptcy.BkrTypeDoc;
import com.baltinfo.radius.bankruptcy.BkrTypePublicationBody;
import com.baltinfo.radius.bankruptcy.BkrTypeStates;
import com.baltinfo.radius.bankruptcy.export.AdditionalPropertiesService;
import com.baltinfo.radius.bankruptcy.export.BkrFileService;
import com.baltinfo.radius.dadata.dto.AddressDto;
import com.baltinfo.radius.dadata.services.DadataService;
import com.baltinfo.radius.db.constants.ApplicationForm;
import com.baltinfo.radius.db.constants.ApplicationReviewResult;
import com.baltinfo.radius.db.constants.AuctionDealTypeCost;
import com.baltinfo.radius.db.constants.ConvertDist;
import com.baltinfo.radius.db.constants.ConvertSource;
import com.baltinfo.radius.db.constants.CostIndTax;
import com.baltinfo.radius.db.constants.EisCurrencyUnid;
import com.baltinfo.radius.db.constants.EntityConstant;
import com.baltinfo.radius.db.constants.LotStatus;
import com.baltinfo.radius.db.constants.TypeAuctionCode;
import com.baltinfo.radius.db.constants.TypeCostConstant;
import com.baltinfo.radius.db.constants.TypeDocConstant;
import com.baltinfo.radius.db.constants.TypeStateConstant;
import com.baltinfo.radius.db.constants.TypeSubject;
import com.baltinfo.radius.db.dao.LotDao;
import com.baltinfo.radius.db.dao.ObjRoleDao;
import com.baltinfo.radius.db.dao.SubjectDao;
import com.baltinfo.radius.db.dto.VitrinaAddressDto;
import com.baltinfo.radius.db.model.Application;
import com.baltinfo.radius.db.model.Auction;
import com.baltinfo.radius.db.model.Deal;
import com.baltinfo.radius.db.model.Declarant;
import com.baltinfo.radius.db.model.DocFile;
import com.baltinfo.radius.db.model.Document;
import com.baltinfo.radius.db.model.Lot;
import com.baltinfo.radius.db.model.ObjCost;
import com.baltinfo.radius.db.model.ObjRole;
import com.baltinfo.radius.db.model.ObjectJPA;
import com.baltinfo.radius.db.model.ReductionSchedule;
import com.baltinfo.radius.db.model.Subject;
import com.baltinfo.radius.db.model.SubjectHistory;
import com.baltinfo.radius.db.model.TypeDoc;
import com.baltinfo.radius.db.model.bankruptcy.VAdditionalDataBkr;
import com.baltinfo.radius.db.model.bankruptcy.VApplicationAll;
import com.baltinfo.radius.db.model.bankruptcy.VAuctionBkr;
import com.baltinfo.radius.db.model.bankruptcy.VAuctionLotAll;
import com.baltinfo.radius.db.model.bankruptcy.VDocLinkBkr;
import com.baltinfo.radius.db.model.bankruptcy.VNonExecReason;
import com.baltinfo.radius.db.model.bankruptcy.VReductionScheduleBkr;
import com.baltinfo.radius.db.model.bankruptcy.VStepBkr;
import com.baltinfo.radius.db.model.bankruptcy.VWebBidderAll;
import com.baltinfo.radius.exchange.ExchangeResult;
import com.baltinfo.radius.notification.impl.AccountantNotificationService;
import com.baltinfo.radius.rest.response.common.FileResponse;
import com.baltinfo.radius.subject.SubjectUtils;
import com.baltinfo.radius.utils.DateUtils;
import com.baltinfo.radius.utils.HibernateUtil;
import com.baltinfo.radius.utils.Result;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Suvorina Aleksandra
 * @since 10.10.2018
 */
public class BkrExchangeResultsController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(BkrExchangeResultsController.class);

    private static final String APPL_ETP_PREFIX = "ЭТП-";
    private static final Long BKR_OPF_IP = 188L;
    private static final String DOCUMENT_PASSPORT_NAME = "Паспорт";
    private static final BigDecimal DEFAULT_TAX_PERCENT = new BigDecimal("20.0");

    private final SubjectUtils subjectUtils;
    private final SubjectDao subjectDao;
    private final LotDao lotDao;
    private final OperJournalController operJournalController;
    private final String pathToFiles;
    private final DealController dealController;
    private final ObjRoleDao objRoleDao;
    private final AdditionalPropertiesService additionalPropertiesService;
    private final RewardController rewardController;
    private final VitrinaController vitrinaController;
    private final DadataService dadataService;
    private final BkrFileService bkrFileService;
    private final RateController rateController;
    private final AccountantNotificationService accountantNotificationService;


    public BkrExchangeResultsController(SubjectUtils subjectUtils, SubjectDao subjectDao, LotDao lotDao,
                                        OperJournalController operJournalController, String pathToFiles,
                                        DealController dealController, ObjRoleDao objRoleDao,
                                        AdditionalPropertiesService additionalPropertiesService,
                                        RewardController rewardController,
                                        VitrinaController vitrinaController,
                                        DadataService dadataService,
                                        BkrFileService bkrFileService,
                                        RateController rateController,
                                        AccountantNotificationService accountantNotificationService) {
        this.subjectUtils = subjectUtils;
        this.subjectDao = subjectDao;
        this.lotDao = lotDao;
        this.operJournalController = operJournalController;
        this.pathToFiles = pathToFiles;
        this.dealController = dealController;
        this.objRoleDao = objRoleDao;
        this.additionalPropertiesService = additionalPropertiesService;
        this.rewardController = rewardController;
        this.vitrinaController = vitrinaController;
        this.dadataService = dadataService;
        this.bkrFileService = bkrFileService;
        this.rateController = rateController;
        this.accountantNotificationService = accountantNotificationService;
    }

    public Result<Boolean, String> checkLotIsFinished(Long bkrAuctionUnid, Long lotNumber) {
        EntityManager emBkr = null;
        try {
            emBkr = HibernateUtil.getInstance().getEntityManagerBKR();
            VAuctionLotAll bkrLot = (VAuctionLotAll) emBkr.createQuery("select l from VAuctionLotAll l " +
                            "where l.auctionUnid = :auctionUnid and l.lotNumber = :lotNumber")
                    .setParameter("auctionUnid", bkrAuctionUnid)
                    .setParameter("lotNumber", lotNumber + "")
                    .getSingleResult();
            return Result.ok(bkrLot.getDoTypeStateUnid() == BkrTypeStates.DO_TYPE_STATE_NOT_DONE.getUnid()
                    || bkrLot.getDoTypeStateUnid() == BkrTypeStates.DO_TYPE_STATE_DONE.getUnid()
                    || bkrLot.getDoTypeStateUnid() == BkrTypeStates.DO_TYPE_STATE_ANNULED.getUnid()
                    || bkrLot.getDoTypeStateUnid() == BkrTypeStates.DO_TYPE_STATE_CANCELED.getUnid()
                    || bkrLot.getDoTypeStateUnid() == BkrTypeStates.DO_TYPE_STATE_REPEATED.getUnid());
        } catch (NoResultException ex) {
            logger.error("NoResultException checkLotIsFinished. bkrAuctionUnid = {}, lotNumber = {}",
                    bkrAuctionUnid, lotNumber, ex);
            return Result.error("Лот №" + lotNumber + " не найден на площадке Банкротство.");
        } catch (Exception ex) {
            logger.error("Error checkLotIsFinished. bkrAuctionUnid = {}, lotNumber = {}",
                    bkrAuctionUnid, lotNumber, ex);
            return Result.error(ex.getMessage());
        } finally {
            closeEntityManager(emBkr);
        }
    }

    public ExchangeResult receiveResultsByLot(Auction eisAuction, Lot eisLot, Long bkrAuctionUnid, Long paUnid) {
        EntityManager emBkr = null;
        EntityManager emEis = null;
        try {
            List<String> errors = new ArrayList<>();
            emBkr = HibernateUtil.getInstance().getEntityManagerBKR();
            emEis = HibernateUtil.getInstance().getEntityManager();
            emEis.getTransaction().begin();

            VAuctionLotAll bkrLot = (VAuctionLotAll) emBkr.createQuery("select l from VAuctionLotAll l where l.auctionUnid = :auctionUnid and l.lotNumber = :lotNumber")
                    .setParameter("auctionUnid", bkrAuctionUnid)
                    .setParameter("lotNumber", eisLot.getLotNumber() + "")
                    .getSingleResult();

            VAuctionBkr bkrAuction = emBkr.find(VAuctionBkr.class, bkrAuctionUnid);

            // Заявки
            List<VApplicationAll> bkrApplications = emBkr.createQuery("select a from VApplicationAll a where a.lotUnid = :lotUnid")
                    .setParameter("lotUnid", bkrLot.getLotUnid())
                    .getResultList();
            List<VStepBkr> steps = emBkr.createNamedQuery("Step.findByLotUnid", VStepBkr.class)
                    .setParameter("lotUnid", bkrLot.getLotUnid())
                    .getResultList();

            boolean endCostExist = false;
            Map<Long, SubjectHistory> bkrEisBidders = new HashMap<>();
            List<Declarant> eisApplDeclarants = new ArrayList<>();
            List<Application> approvedApplications = new ArrayList<>();
            for (VApplicationAll bkrApplication : bkrApplications) {
                VStepBkr step = steps.stream()
                        .filter(s -> s.getStepCardNumber() != null && s.getStepCardNumber().equals(bkrApplication.getApplicatCardNumber()))
                        .max(Comparator.comparing(VStepBkr::getStepNumber))
                        .orElse(null);
                BigDecimal applicatAmmount = null;
                if (step != null) {
                    applicatAmmount = step.getStepValueRub();
                }
                Result<Application, String> applResult = receiveApplication(emBkr, emEis, bkrApplication, applicatAmmount,
                        eisAuction, eisLot, paUnid);
                if (applResult.isError()) {
                    rollbackTransaction(emEis);
                    return ExchangeResult.notLoaded(applResult.getError());
                }
                Application eisApplication = applResult.getResult();

                if (eisApplication.getTsUnid().equals(TypeStateConstant.APP_ALLOWED.getId())) {
                    approvedApplications.add(eisApplication);
                }

                Result<Void, String> appDocumResult = receiveApplicationDocuments(emBkr, emEis, bkrApplication.getApplicatUnid(), eisApplication, paUnid);
                if (appDocumResult.isError()) {
                    rollbackTransaction(emEis);
                    return ExchangeResult.notLoaded(appDocumResult.getError());
                }

                VWebBidderAll wb = emBkr.find(VWebBidderAll.class, bkrApplication.getPersonUnid());
                SubjectHistory subjectHistory = null;
                if (bkrEisBidders.containsKey(wb.getWbUnid())) {
                    subjectHistory = bkrEisBidders.get(wb.getWbUnid());
                } else {
                    Result<SubjectHistory, String> shResult =
                            receiveSubjectHistory(emBkr, emEis, wb, paUnid,
                                    subjectDao.isOwnerKio(emEis, eisLot.getObjUnid().getObjUnid()));
                    if (shResult.isError()) {
                        rollbackTransaction(emEis);
                        return ExchangeResult.notLoaded(shResult.getError());
                    }
                    subjectHistory = shResult.getResult();
                    bkrEisBidders.put(wb.getWbUnid(), subjectHistory);
                }

                Result<Declarant, String> declarantResult = buildDeclarant(emEis, subjectHistory, eisApplication, paUnid);
                if (declarantResult.isError()) {
                    rollbackTransaction(emEis);
                    return ExchangeResult.notLoaded(declarantResult.getError());
                }
                eisApplDeclarants.add(declarantResult.getResult());
            }

            if (bkrLot.getCostEValue() != null && bkrLot.getCostEValue().compareTo(BigDecimal.ZERO) > 0) {
                Result<Void, String> endCostResult = receiveEndCost(emEis, bkrLot.getCostEValue(), eisLot, paUnid, eisAuction);
                if (endCostResult.isError()) {
                    rollbackTransaction(emEis);
                    return ExchangeResult.notLoaded(endCostResult.getError());
                }
                endCostExist = true;
            }

            Optional<List<VAdditionalDataBkr>> addDataProtAppl = getBkrProtocols(emBkr, bkrLot.getDoUnid(),
                    BkrTypeAdditionalData.PROTOCOL_APPL.getUnid());
            if (addDataProtAppl.isPresent()) {
                for (VAdditionalDataBkr protocol : addDataProtAppl.get()) {
                    Result<Document, String> protocolResult = loadProtocol(emEis, protocol, eisLot.getLotUnid(), TypeDocConstant.PROTOCOL_APPL.getUnid(), paUnid);
                    if (protocolResult.isError()) {
                        errors.add(protocolResult.getError());
                    }
                }
            }

            Optional<List<VAdditionalDataBkr>> addDataProtResult = getBkrProtocols(emBkr, bkrLot.getDoUnid(),
                    BkrTypeAdditionalData.PROTOCOL_RESULT.getUnid());
            Document eisResultProtocol = null;
            if (addDataProtResult.isPresent()) {
                for (VAdditionalDataBkr protocol : addDataProtResult.get()) {
                    Result<Document, String> eisResultProtocolRes = loadProtocol(emEis, protocol, eisLot.getLotUnid(), TypeDocConstant.PROTOCOL_RESULT.getUnid(), paUnid);
                    if (eisResultProtocolRes.isError()) {
                        errors.add(eisResultProtocolRes.getError());
                    } else {
                        eisResultProtocol = eisResultProtocolRes.getResult();
                    }
                }
            }

            VNonExecReason nonExecReasonBkr = null;
            if ((bkrLot.getDoTypeStateUnid() == BkrTypeStates.DO_TYPE_STATE_CANCELED.getUnid()
                    || bkrLot.getDoTypeStateUnid() == BkrTypeStates.DO_TYPE_STATE_NOT_DONE.getUnid()
                    || bkrLot.getDoTypeStateUnid() == BkrTypeStates.DO_TYPE_STATE_REPEATED.getUnid())
                    && bkrLot.getNonExecReasonUnid() != null) {
                nonExecReasonBkr = emBkr.find(VNonExecReason.class, bkrLot.getNonExecReasonUnid());
            }

            if (bkrLot.getDoTypeStateUnid() == BkrTypeStates.DO_TYPE_STATE_CANCELED.getUnid()) {
                eisLot.setLotAuctionCancelTime(bkrLot.getLotAuctionCancelTime());
                if (nonExecReasonBkr != null) {
                    eisLot.setLotAuctionCancelCause(nonExecReasonBkr.getNonExecReasonName());
                }
            }
            if (bkrLot.getDoTypeStateUnid() == BkrTypeStates.DO_TYPE_STATE_ANNULED.getUnid()) {
                eisLot.setLotAuctionCancelCause(bkrAuction.getAuctionCancellationCause());
            }
            if (bkrLot.getDoTypeStateUnid() == BkrTypeStates.DO_TYPE_STATE_REPEATED.getUnid()
                    || bkrLot.getDoTypeStateUnid() == BkrTypeStates.DO_TYPE_STATE_NOT_DONE.getUnid()) {
                if (nonExecReasonBkr != null) {
                    Result<Long, String> eisNonExchangeReasonUnid = getEisNonExecReasonUnid(emEis, nonExecReasonBkr.getNonExecReasonUnid());
                    if (eisNonExchangeReasonUnid.isSuccess()) {
                        eisLot.setNonExecReasonUnid(eisNonExchangeReasonUnid.getResult());
                    } else {
                        rollbackTransaction(emEis);
                        return ExchangeResult.notLoaded(eisNonExchangeReasonUnid.getError());
                    }
                }
            }

            eisLot.setLotApplCount(new Long(approvedApplications.size()));

            // Статус лота
            eisLot.setTsUnid(getLotTsUnid(bkrLot.getDoTypeStateUnid()));
            eisLot.setLotStatus(getLotStatus(bkrLot.getDoTypeStateUnid()));
            eisLot.setDateBRec(new Date());
            eisLot.setPersCodeBRec(paUnid);
            emEis.merge(eisLot);

            // Если это блочные торги,
            // то для состоявшихся и отмененных лотов выставляем статус соответствующих лотов в последующих торгах в "Торги уже проведены"
            if (eisAuction.getBaUnid() != null && (eisLot.getLotStatus().equals(LotStatus.TRADE_DONE.getCode()) ||
                    eisLot.getLotStatus().equals(LotStatus.TRADE_CANCEL.getCode()))) {
                List<Lot> nextLots = lotDao.getNextStageLots(emEis, eisAuction.getBaUnid(),
                        eisAuction.getAuctionStageNum(), eisLot.getLotNumber());

                for (Lot lot : nextLots) {
                    lot.setTsUnid(TypeStateConstant.LOT_ALREADY_DONE.getId());
                    lot.setDateBRec(new Date());
                    lot.setPersCodeBRec(paUnid);
                    emEis.merge(lot);
                }
            }

            ObjectJPA obj = eisLot.getObjUnid();
            obj.setTsUnid(getObjTsUnid(eisLot.getTsUnid()));

            obj.setDateBRec(new Date());
            obj.setPersCodeB(paUnid);
            boolean isNeedCreateRewards = false;
            if (eisLot.getLotStatus().equals(LotStatus.TRADE_DONE.getCode()) && endCostExist) {
                obj.setObjIndSaled(1);
                obj.setTsUnid(TypeStateConstant.OBJ_SALED.getId());
                if (eisResultProtocol != null) {
                    obj.setObjSaleDate(DateUtils.truncate(eisResultProtocol.getDocumDate()));
                } else {
                    obj.setObjSaleDate(DateUtils.truncate(bkrLot.getLotAuctionDateEFact()));
                }

                isNeedCreateRewards = true;
            }

            if (bkrLot.getDoTypeStateUnid() == BkrTypeStates.DO_TYPE_STATE_NOT_DONE.getUnid()
                    && (bkrLot.getNonExecReasonUnid() != null && bkrLot.getNonExecReasonUnid().equals(BkrNonExecReason.ONE_APPLICATION.getUnid()))) {
                obj.setObjIndSaled(1);
                obj.setTsUnid(TypeStateConstant.OBJ_SALED.getId());
                if (eisResultProtocol != null) {
                    obj.setObjSaleDate(DateUtils.truncate(eisResultProtocol.getDocumDate()));
                } else {
                    obj.setObjSaleDate(DateUtils.truncate(bkrLot.getAuctionDateE()));
                }
                // Создаем договор купли-продажи
                Long tpaUnid = null;
                Optional<ObjRole> saleObjRole = objRoleDao.getSellerObjRole(emEis, eisLot.getObjUnid().getObjUnid());
                if (saleObjRole.isPresent()) {
                    tpaUnid = saleObjRole.get().getTpaUnid();
                } else {
                    ObjRole objRole = objRoleDao.getCreatorObjRole(emEis, eisLot.getObjUnid().getObjUnid());
                    tpaUnid = objRole.getTpaUnid();
                }
                ObjCost futureSaleCost = null;
                if (eisAuction.getTypeAuctionUnid().getTypeAuctionCode().equals(TypeAuctionCode.HOLLAND.getCode())) {
                    if (eisAuction.getAuctionDealTypeCost() == null) {
                        futureSaleCost = getMinCost(emEis, eisLot);
                    } else {
                        futureSaleCost = eisAuction.getAuctionDealTypeCost() == AuctionDealTypeCost.START_COST.getCode()
                                ? getStartCost(emEis, eisLot)
                                : getMinCost(emEis, eisLot);
                    }
                } else {
                    futureSaleCost = getStartCost(emEis, eisLot);
                }

                Optional<Declarant> singleParticipant = eisApplDeclarants.stream()
                        .filter(decl -> decl.getApplicatUnid().getTsUnid().equals(TypeStateConstant.APP_ALLOWED.getId()))
                        .findFirst();
                SubjectHistory participantSh = null;

                isNeedCreateRewards = true;
                if (singleParticipant.isPresent()) {
                    participantSh = singleParticipant.get().getShUnid();
                }
                if (futureSaleCost != null || participantSh != null) {
                    boolean createNewDeal = true;
                    Optional<Deal> dealOptional = dealController.getSaleDeal(emEis, eisLot.getObjUnid().getObjUnid());
                    if (dealOptional.isPresent()) {
                        Result<Void, String> deleteDealResult = dealController.deleteSaleDeal(emEis, dealOptional.get(), paUnid);
                        if (deleteDealResult.isError()) {
                            errors.add(deleteDealResult.getError());
                            createNewDeal = false;
                        } else {
                            operJournalController.createOperJournalForDealDelete(emEis, dealOptional.get().getDealUnid(), paUnid);
                        }
                    }

                    if (createNewDeal) {
                        Result<Deal, String> saleDealResult = dealController.createSaleDeal(emEis, tpaUnid, paUnid,
                                eisLot.getObjUnid().getObjUnid(), futureSaleCost, participantSh);
                        if (saleDealResult.isError()) {
                            errors.add(saleDealResult.getError());
                        } else {
                            operJournalController.createOperJournalForDealCreate(emEis, saleDealResult.getResult().getDealUnid(), paUnid);
                        }
                    }
                }
            }

            // Обновление дополнительных свойств
            Long wpUnid = bkrLot.getWpUnid();
            Result<FileResponse, String> result = getAdditionalPropertiesBody(wpUnid);
            if (result.isSuccess()) {
                additionalPropertiesService
                        .updateAdditionalPropertiesByInputStream(new ByteArrayInputStream(result.getResult().getBytes()),
                                obj.getObjUnid());
            }

            emEis.merge(obj);

            if (isNeedCreateRewards) {
                // Создаем плановый расчет вознаграждения
                Result<Void, String> rewardResult = rewardController.createPlanReward(emEis, obj);
                if (rewardResult.isError()) {
                    errors.add("Не удалось создать плановый расчет вознаграждения. " + rewardResult.getError());
                }

                ObjRole objRole = objRoleDao.getCreatorObjRole(emEis, eisLot.getObjUnid().getObjUnid());
                Long tpaCreator = objRole.getTpaUnid();

                Long tpaSeller = null;
                Optional<ObjRole> saleObjRole = objRoleDao.getSellerObjRole(emEis, eisLot.getObjUnid().getObjUnid());
                if (saleObjRole.isPresent()) {
                    tpaSeller = saleObjRole.get().getTpaUnid();
                }

                List<Declarant> allowedApplicationDeclarants = eisApplDeclarants.stream()
                        .filter(decl -> decl.getApplicatUnid().getTsUnid().equals(TypeStateConstant.APP_ALLOWED.getId()))
                        .collect(Collectors.toList());

                Result<Void, String> winnerRewardResult = rewardController.createWinnerReward(emEis, eisLot, obj, eisAuction,
                        obj.getSgtUnid(), tpaCreator, tpaSeller, allowedApplicationDeclarants);
                if (winnerRewardResult.isError()) {
                    errors.add("Не удалось создать акт по победителю. " + winnerRewardResult.getError());
                }

                // Создание уведомления бухгалтеру
                createMessageForAccountant(emEis, obj, eisLot);

            }
            operJournalController.createOperJournalForLotEdit(emEis, eisLot.getLotUnid(), "Перенесены результаты торгов по лоту с ЭТП Банкротство", paUnid);
            operJournalController.createOperJournalForObjectEdit(emEis, obj.getObjUnid(), "Перенесены результаты торгов по лоту с ЭТП Банкротство", paUnid);

            emEis.getTransaction().commit();
            if (errors.isEmpty()) {
                return ExchangeResult.loaded(bkrLot.getLotUnid());
            } else {
                return ExchangeResult.loadedWithErrors(bkrLot.getLotUnid(), String.join("\n", errors));
            }
        } catch (Exception ex) {
            logger.error("Error receiveResultsByLot. eisAuctionUnid = {}, eisLotUnid = {}, bkrAuctionUnid = {}, paUnid = {}",
                    eisAuction.getAuctionUnid(), eisLot.getLotUnid(), bkrAuctionUnid, paUnid, ex);
            rollbackTransaction(emEis);
            return ExchangeResult.notLoaded(ex.getMessage());
        } finally {
            closeEntityManager(emBkr);
            closeEntityManager(emEis);
        }
    }

    private Result<FileResponse, String> getAdditionalPropertiesBody(Long wpUnid) {
        Result<Long, String> dfUnidResult = getAdditionalPropertiesDfUnid(wpUnid);
        if (dfUnidResult.isSuccess()) {
            FileResponse file = bkrFileService.getFileFromStorage(dfUnidResult.getResult());
            if (file == null || file.getBytes().length == 0) {
                return Result.error("Can't get additional properties file body.");
            }
            return Result.ok(file);
        }
        return Result.error(dfUnidResult.getError());
    }

    private Result<Long, String> getAdditionalPropertiesDfUnid(Long wpUnid) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            conn = HibernateUtil.getInstance().getConnectionProviderBKR().getConnection();
            preparedStatement = conn.prepareStatement("SELECT PB_DF_UNID FROM WEB.PUBLICATION_BODY WHERE TPB_UNID = ? AND PB_WP_UNID = ?");
            preparedStatement.setLong(1, BkrTypePublicationBody.ADDITIONAL_PROPERTIES.getUnid());
            preparedStatement.setLong(2, wpUnid);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Long pbDfUnid = rs.getLong("PB_DF_UNID");
                return Result.ok(pbDfUnid);
            } else {
                return Result.error("No entity PUBLICATION_BODY with wpUnid = " + wpUnid + " and tpbUnid = " + BkrTypePublicationBody.ADDITIONAL_PROPERTIES.getUnid());
            }
        } catch (Exception e) {
            rollbackConnection(conn);
            logger.error("Can't get AdditionalProperties pbDfUnid from BKR with wpUnid = {} and tpbUnid = {}", wpUnid, BkrTypePublicationBody.ADDITIONAL_PROPERTIES.getUnid(), e);
            return Result.error("Can't get AdditionalProperties pbDfUnid from BKR with wpUnid = " + wpUnid + " and tpbUnid = " + BkrTypePublicationBody.ADDITIONAL_PROPERTIES.getUnid());
        } finally {
            closeConnection(conn);
            closePreparedStatement(preparedStatement);
            closeResultSet(rs);
        }
    }

    private void closeResultSet(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (Exception ex) {
            logger.error("Can't close ResultSet", ex);
        }
    }

    private void closePreparedStatement(PreparedStatement preparedStatement) {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (Exception ex) {
            logger.error("Can't close PreparedStatement", ex);
        }
    }

    public Result<Void, String> setAuctionStateAfterExchange(Long eisAuctionUnid, Long paUnid) {
        EntityManager emEis = null;
        try {
            emEis = HibernateUtil.getInstance().getEntityManager();
            emEis.getTransaction().begin();
            Auction eisAuction = emEis.find(Auction.class, eisAuctionUnid);

            eisAuction.setTsUnid(TypeStateConstant.TRADE_DONE.getId());
            eisAuction.setDateBRec(new Date());
            eisAuction.setPersCodeBRec(paUnid);
            emEis.merge(eisAuction);
            operJournalController.createOperJournalForAuctionEdit(emEis, eisAuction.getAuctionUnid(),
                    "Перенесены результаты торгов с ЭТП Банкротство", paUnid);
            emEis.getTransaction().commit();
            return Result.ok();
        } catch (Exception ex) {
            logger.error("Error setAuctionStateAfterExchange. eisAuctionUnid = {}",
                    eisAuctionUnid, ex);
            rollbackTransaction(emEis);
            return Result.error(ex.getMessage());
        } finally {
            closeEntityManager(emEis);
        }
    }

    public List<VAuctionLotAll> getFinishedBkrLots(List<Long> bkrAuctionUnids) {
        EntityManager emBkr = null;
        try {
            emBkr = HibernateUtil.getInstance().getEntityManagerBKR();
            List<Long> bkrLotFinishedStates = Arrays.asList(BkrTypeStates.DO_TYPE_STATE_NOT_DONE.getUnid(),
                    BkrTypeStates.DO_TYPE_STATE_DONE.getUnid(),
                    BkrTypeStates.DO_TYPE_STATE_ANNULED.getUnid(),
                    BkrTypeStates.DO_TYPE_STATE_CANCELED.getUnid(),
                    BkrTypeStates.DO_TYPE_STATE_REPEATED.getUnid());

            List<VAuctionLotAll> bkrFinishedLots = emBkr.createQuery("select l from VAuctionLotAll l " +
                            "where l.auctionUnid in (:auctionUnids) and l.doTypeStateUnid in (:typeStates)")
                    .setParameter("auctionUnids", bkrAuctionUnids)
                    .setParameter("typeStates", bkrLotFinishedStates)
                    .getResultList();
            return bkrFinishedLots;
        } catch (Exception ex) {
            logger.error("Error getFinishedBkrLots.", ex);
        } finally {
            closeEntityManager(emBkr);
        }
        return null;
    }

    private Result<Application, String> receiveApplication(EntityManager emBkr, EntityManager emEis,
                                                           VApplicationAll bkrApplication, BigDecimal applicatAmmount,
                                                           Auction eisAuction, Lot eisLot, Long paUnid) {
        try {
            ReductionSchedule reductionSchedule = null;
            if (bkrApplication.getRedSchedUnid() != null) {
                VReductionScheduleBkr bkrRedSched = emBkr.find(VReductionScheduleBkr.class, bkrApplication.getRedSchedUnid());
                try {
                    reductionSchedule = (ReductionSchedule) emEis.createNamedQuery("ReductionSchedule.findByLotUnidAndDateB")
                            .setParameter("lotUnid", eisLot.getLotUnid())
                            .setParameter("redSchedDateB", bkrRedSched.getRedSchedDateB())
                            .getSingleResult();
                } catch (NoResultException ex) {
                    logger.error("Error get ReductionSchedule by lotUnid = {}, redSchedDateB = {}", eisLot.getLotUnid(),
                            bkrRedSched.getRedSchedDateB(), ex);
                }
            }

            Application application = buildApplication(bkrApplication, applicatAmmount, eisAuction, reductionSchedule);
            application.setIndActual(1L);
            application.setDateB(new Date());
            application.setPersCodeB(paUnid);
            application.setLotUnid(eisLot.getLotUnid());
            emEis.persist(application);

            return Result.ok(application);
        } catch (Exception ex) {
            logger.error("Error receive application", ex);
            return Result.error("Ошибка при переносе заявки №" + bkrApplication.getApplicatNumber() + ": " + ex.getMessage());
        }
    }

    private Result<SubjectHistory, String> receiveSubjectHistory(EntityManager emBkr, EntityManager emEis,
                                                                 VWebBidderAll bkrWebBidder, Long paUnid,
                                                                 boolean ownerKio) {
        try {
            VDocLinkBkr passport = getPersonDocument(emBkr, BkrTypeDoc.PASSPORT.getUnid(), bkrWebBidder.getPersonUnid());

            VDocLinkBkr paPassport = getPersonDocument(emBkr, BkrTypeDoc.PASSPORT.getUnid(), bkrWebBidder.getPersonPaUnid());
            VDocLinkBkr staffPassport = getPersonDocument(emBkr, BkrTypeDoc.PASSPORT.getUnid(), bkrWebBidder.getStaffSubPersonUnid());
            VDocLinkBkr paTrusty = getPersonDocument(emBkr, BkrTypeDoc.TRUSTY.getUnid(), bkrWebBidder.getPersonPaUnid());

            SubjectHistory sh = buildSubjectHistory(bkrWebBidder);

            if (ownerKio) {
                // Для КИО всегда берем из дадаты, т.к. нужен fias_id с учетом квартиры
                if (bkrWebBidder.getPartyAddrFact() != null && !bkrWebBidder.getPartyAddrFact().isEmpty()) {
                    AddressDto address = dadataService.createAddressDtoByAddress(bkrWebBidder.getPartyAddrFact());
                    if (address != null) {
                        sh.setShSubAddrFactFiasId(address.getFiasId());
                    }
                }
                if (bkrWebBidder.getPartyAddrLegal() != null && !bkrWebBidder.getPartyAddrLegal().isEmpty()) {
                    AddressDto address = dadataService.createAddressDtoByAddress(bkrWebBidder.getPartyAddrLegal());
                    if (address != null) {
                        sh.setShSubAddrLegalFiasId(address.getFiasId());
                    }
                }
            } else {
                // Для остальных берем с витрины
                Optional<VitrinaAddressDto> addressDtoResult = vitrinaController.getAddressByBkrWbUnid(bkrWebBidder.getWbUnid());
                if (addressDtoResult.isPresent()) {
                    VitrinaAddressDto addressDto = addressDtoResult.get();
                    sh.setShSubAddrLegalFiasId(addressDto.getLegalAddressFiasId());
                    sh.setShSubAddrFactFiasId(addressDto.getPostAddressFiasId());
                }

                // Если с витрины fias_id не пришел, берем из дадаты, в приоритете юридический адрес
                if ((sh.getShSubAddrLegalFiasId() == null || sh.getShSubAddrLegalFiasId().isEmpty()) &&
                        (sh.getShSubAddrFactFiasId() == null || sh.getShSubAddrFactFiasId().isEmpty())) {
                    if (bkrWebBidder.getPartyAddrLegal() != null && !bkrWebBidder.getPartyAddrLegal().isEmpty()) {
                        AddressDto address = dadataService.createAddressDtoByAddress(bkrWebBidder.getPartyAddrLegal());
                        if (address != null) {
                            sh.setShSubAddrLegalFiasId(address.getFiasId());
                        }
                    }
                }

                // Если юридического нет, пробуем фактический
                if ((sh.getShSubAddrLegalFiasId() == null || sh.getShSubAddrLegalFiasId().isEmpty()) &&
                        (sh.getShSubAddrFactFiasId() == null || sh.getShSubAddrFactFiasId().isEmpty())) {
                    if (bkrWebBidder.getPartyAddrFact() != null && !bkrWebBidder.getPartyAddrFact().isEmpty()) {
                        AddressDto address = dadataService.createAddressDtoByAddress(bkrWebBidder.getPartyAddrFact());
                        if (address != null) {
                            sh.setShSubAddrFactFiasId(address.getFiasId());
                        }
                    }
                }
            }

            if (sh.getTypesUnid().equals(TypeSubject.FL.getUnid()) && passport != null) {
                addSubjectHistoryPassportFields(sh, passport);
            }
            if (sh.getTypesUnid().equals(TypeSubject.IP.getUnid()) && staffPassport != null) {
                addSubjectHistoryPassportFields(sh, staffPassport);
            }
            if ((bkrWebBidder.getPersonUnid() == null || !bkrWebBidder.getPersonUnid().equals(bkrWebBidder.getPersonPaUnid()))
                    && paPassport != null) {
                addSubjectHistoryPaPassportFields(sh, paPassport);
            }
            if (paTrusty != null) {
                addSubjectHistoryPaTrustyFields(sh, paTrusty);
            }
            List<Subject> subjects = subjectUtils.findSubject(sh);
            if (subjects.isEmpty()) {
                sh = subjectDao.createSubjectFromSubjectHistory(emEis, sh, paUnid);
            } else {
                Subject subject = subjects.get(0);
                if ((sh.getShSubAddrLegalFiasId() != null || sh.getShSubAddrFactFiasId() != null) &&
                        (subject.getSubAddrLegalFiasId() == null || subject.getSubAddrLegalFiasId().isEmpty()
                                || subject.getSubAddrFactFiasId() == null || subject.getSubAddrFactFiasId().isEmpty())) {
                    subject.setSubAddrLegalFiasId(sh.getShSubAddrLegalFiasId());
                    subject.setSubAddrFactFiasId(sh.getShSubAddrFactFiasId());
                    subjectDao.updateSubject(emEis, subject, paUnid);
                }
                sh.setSubUnid(subject.getSubUnid());
                sh = subjectDao.createSubjectHistory(emEis, sh, paUnid);
            }
            return Result.ok(sh);
        } catch (Exception ex) {
            logger.error("Error receiveSubjectHistory", ex);
            return Result.error("Ошибка при переносе заявителя " + bkrWebBidder.getPartyName() + ": " + ex.getMessage());
        }
    }

    private VDocLinkBkr getPersonDocument(EntityManager emBkr, Long typeDocUnid, Long personUnid) {
        VDocLinkBkr document = null;
        if (personUnid != null) {
            List<VDocLinkBkr> docs = emBkr.createQuery("select d from VDocLinkBkr d " +
                            "where d.typeDocUnid = :typeDoc and d.docLinkCodeEntity = 'PERSON' and d.docLinkEntityUnid = :entityUnid ", VDocLinkBkr.class)
                    .setParameter("typeDoc", typeDocUnid)
                    .setParameter("entityUnid", personUnid)
                    .setMaxResults(1)
                    .getResultList();
            if (CollectionUtils.isNotEmpty(docs)) {
                document = docs.get(0);
            }
        }
        return document;
    }

    private Result<Void, String> receiveEndCost(EntityManager emEis, BigDecimal endCostValue, Lot eisLot, Long paUnid, Auction auction) {
        try {
            emEis.createNativeQuery("update web.obj_cost set cost_ind_current = 0, date_b_rec = :now, pers_code_b_rec = :paUnid"
                            + " where ind_actual = 1 and cost_ind_current = 1 and obj_unid = :objUnid")
                    .setParameter("objUnid", eisLot.getObjUnid().getObjUnid())
                    .setParameter("paUnid", paUnid)
                    .setParameter("now", new Date())
                    .executeUpdate();

            List<ObjCost> oldEndCosts = emEis.createQuery("select oc from ObjCost oc " +
                            "where oc.indActual = 1 and oc.lotUnid.lotUnid = :lotUnid and oc.typeCosUnid = :typeCosUnid")
                    .setParameter("lotUnid", eisLot.getLotUnid())
                    .setParameter("typeCosUnid", TypeCostConstant.RESULT.getId())
                    .getResultList();
            for (ObjCost oldEndCost : oldEndCosts) {
                oldEndCost.setIndActual(0);
                oldEndCost.setDateBRec(new Date());
                oldEndCost.setPersCodeBRec(paUnid);
                emEis.persist(oldEndCost);
            }

            ObjCost endCost = new ObjCost();
            endCost.setIndActual(1);
            endCost.setDateB(new Date());
            endCost.setPersCodeB(paUnid);
            endCost.setObjUnid(eisLot.getObjUnid());
            endCost.setLotUnid(eisLot);
            endCost.setCostValue(endCostValue);
            endCost.setCurUnid(eisLot.getCurUnid() != null ? eisLot.getCurUnid() : EisCurrencyUnid.RUB.getUnid());
            if (endCost.getCurUnid().equals(EisCurrencyUnid.RUB.getUnid())) {
                endCost.setCostValueRub(endCostValue);
            } else {
                Date date;
                if (auction.getTypeAuctionUnid().getTypeAuctionCode() == TypeAuctionCode.PUBLIC_SALE.getCode()) {
                    date = eisLot.getObjUnid().getObjSaleDate();
                } else {
                    date = auction.getAuctionDateB();
                }
                Result<BigDecimal, String> rezCost = rateController.calcObjCostValueRub(emEis, date, endCostValue, endCost.getCurUnid());
                if (rezCost.isError()) throw new Exception(rezCost.getError());
                endCost.setCostValueRub(rezCost.getResult());
            }
            endCost.setTypeCosUnid(TypeCostConstant.RESULT.getId());
            endCost.setCostIndCurrent(1);
            ObjCost startCost = getStartCost(emEis, eisLot);
            if (startCost != null) {
                endCost.setCostIndTax(startCost.getCostIndTax());
                endCost.setCostTaxProc(startCost.getCostTaxProc());
            }
            if (endCost.getCostIndTax() == null) {
                endCost.setCostIndTax(CostIndTax.TAX_NOT_INCLUDED.getIndTax());
            }
            BigDecimal costValue = endCost.getCostValue();
            if (costValue != null) {
                BigDecimal taxCoeff;
                if (endCost.getCostTaxProc() != null && endCost.getCostTaxProc() > 0) {
                    taxCoeff = new BigDecimal(endCost.getCostTaxProc())
                            .divide(new BigDecimal("100.0"));
                } else {
                    taxCoeff = DEFAULT_TAX_PERCENT
                            .divide(new BigDecimal("100.0"));
                }
                BigDecimal costTax = null;
                if (endCost.getCostIndTax().equals(CostIndTax.TAX_NOT_INCLUDED.getIndTax())) {
                    costTax = costValue.multiply(taxCoeff);
                } else if (endCost.getCostIndTax().equals(CostIndTax.TAX_INCLUDED.getIndTax())) {
                    costTax = costValue.divide(taxCoeff.add(BigDecimal.ONE), 4, RoundingMode.HALF_UP)
                            .multiply(taxCoeff);
                }
                if (costTax != null) {
                    endCost.setCostTax(costTax.setScale(2, RoundingMode.HALF_UP));
                }
            }
            emEis.persist(endCost);
            return Result.ok();
        } catch (Exception ex) {
            logger.error("Error receiveEndCost", ex);
            return Result.error("Ошибка при переносе конечной стоимости по лоту №" + eisLot.getLotNumber() + ": " + ex.getMessage());
        }
    }

    private ObjCost getStartCost(EntityManager emEis, Lot eisLot) {
        try {
            ObjCost startCost = (ObjCost) emEis.createNamedQuery("ObjCost.findStartCostByLotUnid")
                    .setParameter("lotUnid", eisLot.getLotUnid())
                    .getSingleResult();
            return startCost;
        } catch (NoResultException e) {
            logger.warn("Can't find obj cost for lot_id = {}", eisLot);
            return null;
        }
    }

    private ObjCost getMinCost(EntityManager emEis, Lot eisLot) {
        try {
            ObjCost minCost = (ObjCost) emEis.createNamedQuery("ObjCost.findMinCostByLotUnid")
                    .setParameter("lotUnid", eisLot.getLotUnid())
                    .getSingleResult();
            return minCost;
        } catch (NoResultException e) {
            logger.warn("Can't find obj min for lot_id = {}", eisLot);
            return null;
        }
    }

    private Result<Declarant, String> buildDeclarant(EntityManager emEis, SubjectHistory subjectHistory,
                                                     Application eisApplication, Long paUnid) {
        try {
            Declarant declarant = new Declarant();
            declarant.setSubUnid(subjectHistory.getSubUnid());
            declarant.setApplicatUnid(eisApplication);
            declarant.setShUnid(subjectHistory);

            declarant.setIndActual(1);
            declarant.setDateB(new Date());
            declarant.setPersCodeB(paUnid);
            emEis.persist(declarant);
            return Result.ok(declarant);
        } catch (Exception ex) {
            logger.error("Error receiveDeclarant", ex);
            return Result.error("Ошибка при переносе заявителя " + subjectHistory.getShSubName() + ": " + ex.getMessage());
        }
    }


    private Application buildApplication(VApplicationAll applicationBkr, BigDecimal applicatAmmount, Auction eisAuction,
                                         ReductionSchedule reductionSchedule) throws ParseException {
        Application application = new Application();
        application.setEntityUnid(EntityConstant.APPLICATION.getId());
        application.setTpaUnid(eisAuction.getTpaUnid());
        application.setTsUnid(getEisApplicationState(applicationBkr.getApplicatIndPermit()));
        application.setApplicatAppTime(applicationBkr.getApplicatAppTime());
        application.setApplicatNumber(APPL_ETP_PREFIX + applicationBkr.getApplicatNumber());
        application.setApplicatDate(null);

        application.setApplicatAmount(applicatAmmount == null ? applicationBkr.getApplicatAmountRub() : applicatAmmount);

        application.setApplicatIndWinner(applicationBkr.getApplicatIndWinner() == 1);
        application.setApplicatReviewResults(getApplicatReviewResult(applicationBkr.getApplicatIndPermit()));
        application.setApplicatCardNumber(applicationBkr.getApplicatCardNumber());
        application.setApplicatIndPayDep(applicationBkr.getApplicatIndPayDep() == 1);
        application.setApplicatDepositNumber(applicationBkr.getApplicatDepositNumber());
        application.setApplicatDepositDate(applicationBkr.getApplicatDepositDate());
        application.setApplicatApplForm(ApplicationForm.ELECTRONIC.getCode());
        application.setApplicatPayDocNum(applicationBkr.getApplicatPayDocNum());
        application.setApplicatPayDocDate(applicationBkr.getApplicatPayDocDate());
        if (application.getApplicatReviewResults().equals(ApplicationReviewResult.CANCELED.getCode())) {
            application.setApplicatWithdrawTime(applicationBkr.getApplicatDateCancel());
            application.setApplicatWithdrawReason(applicationBkr.getApplicatRefusalFound());
        }
        if (application.getApplicatReviewResults().equals(ApplicationReviewResult.REJECTED.getCode())) {
            application.setApplicatDateRefuse(applicationBkr.getApplicatDateCancel());
            application.setApplicatRefusalFound(applicationBkr.getApplicatRefusalFound());
        }
        application.setApplicatIndTransfer(false);
        application.setApplicatTransferDate(null);
        application.setApplicatIndDocCompliance(applicationBkr.getApplicatDocCompliance() != null && applicationBkr.getApplicatDocCompliance() == 1);

        if (reductionSchedule != null) {
            application.setRedSchedUnid(reductionSchedule.getRedSchedUnid());
        }

        return application;
    }

    private Long getEisApplicationState(Integer indPermit) {
        Long tsUnid = null;
        if (indPermit == null) {
            indPermit = 0;
        }
        if (BkrAppIndPermit.REJECTED.getCode().equals(indPermit)) {
            tsUnid = TypeStateConstant.APP_REJECTED.getId();
        } else if (BkrAppIndPermit.ALLOWED.getCode().equals(indPermit)) {
            tsUnid = TypeStateConstant.APP_ALLOWED.getId();
        } else if (BkrAppIndPermit.CANCELED.getCode().equals(indPermit)) {
            tsUnid = TypeStateConstant.APP_RECALLED.getId();
        } else if (BkrAppIndPermit.REVIEW.getCode().equals(indPermit)) {
            tsUnid = TypeStateConstant.APP_CREATED.getId();
        }
        return tsUnid;
    }

    private Integer getApplicatReviewResult(Integer indPermit) {
        Integer result = null;
        if (indPermit == null) {
            indPermit = 0;
        }
        if (BkrAppIndPermit.REJECTED.getCode().equals(indPermit)) {
            result = ApplicationReviewResult.REJECTED.getCode();
        } else if (BkrAppIndPermit.ALLOWED.getCode().equals(indPermit)) {
            result = ApplicationReviewResult.ALLOWED.getCode();
        } else if (BkrAppIndPermit.CANCELED.getCode().equals(indPermit)) {
            result = ApplicationReviewResult.CANCELED.getCode();
        } else if (BkrAppIndPermit.REVIEW.getCode().equals(indPermit)) {
            result = ApplicationReviewResult.PENDING.getCode();
        }
        return result;
    }

    private SubjectHistory buildSubjectHistory(VWebBidderAll wb) {
        SubjectHistory sh = new SubjectHistory();
        sh.setTypesUnid(getTypesUnid(wb));
        if (sh.getTypesUnid().equals(TypeSubject.FL.getUnid())) {
            addSubjectHistoryFlFields(sh, wb);
        } else if (sh.getTypesUnid().equals(TypeSubject.YL.getUnid())) {
            addSubjectHistoryYlFields(sh, wb);
        } else if (sh.getTypesUnid().equals(TypeSubject.IP.getUnid())) {
            addSubjectHistoryIpFields(sh, wb);
        }
        if (wb.getPersonUnid() == null || !wb.getPersonUnid().equals(wb.getPersonPaUnid())) {
            addSubjectHistoryPaFields(sh, wb);
        }

        sh.setShSubInn(wb.getPartyInn());
        sh.setShSubAddrLegal(wb.getPartyAddrLegal());
        sh.setShSubAddrFact(wb.getPartyAddrFact());
        sh.setShSubPhone(wb.getPartyPhone());
        sh.setShSubFax(wb.getSubFax());
        if (wb.getPartyEmail() != null && wb.getPartyEmail().length() <= 50) {
            sh.setShSubEMail(wb.getPartyEmail());
        }
        sh.setShSubCodeKpp(wb.getSubCodeKpp());
        sh.setShSubAccountName(wb.getAccountName());
        sh.setShSubAccountCode(wb.getAccountCode());
        sh.setShSubBankName(wb.getBankName());
        sh.setShSubBankBic(wb.getBankBic());
        sh.setShSubBankAccount(wb.getBankCAccount());

        return sh;
    }

    private Long getTypesUnid(VWebBidderAll wb) {
        Long typesUnid = null;
        if (wb.getPersonUnid() != null) {
            typesUnid = TypeSubject.FL.getUnid();
        } else if (wb.getOpfUnid() != null && wb.getOpfUnid().equals(BKR_OPF_IP)) {
            typesUnid = TypeSubject.IP.getUnid();
        } else if (wb.getSubUnid() != null) {
            typesUnid = TypeSubject.YL.getUnid();
        }
        return typesUnid;
    }

    private void addSubjectHistoryFlFields(SubjectHistory sh, VWebBidderAll wb) {
        sh.setShSubName(wb.getPartyName());
        sh.setShSubSname(wb.getPartyName());
        sh.setShSubNameF(wb.getPersonNameF());
        sh.setShSubNameI(wb.getPersonNameI());
        sh.setShSubNameO(wb.getPersonNameO());
        sh.setShSubBirthday(wb.getPersonBirthday());
        if (wb.getPersonUnid().equals(wb.getPersonPaUnid())) {
            sh.setShSubSnils(wb.getPersonPaSnils());
        }
    }

    private void addSubjectHistoryPaFields(SubjectHistory sh, VWebBidderAll wb) {
        sh.setShPaSubNameF(wb.getPersonPaF());
        sh.setShPaSubNameI(wb.getPersonPaI());
        sh.setShPaSubNameO(wb.getPersonPaO());
        sh.setShPaSubBirthday(wb.getPersonPaBirthday());
        sh.setShPaSnils(wb.getPersonPaSnils());
    }

    private void addSubjectHistoryYlFields(SubjectHistory sh, VWebBidderAll wb) {
        sh.setShSubName(wb.getSubName());
        sh.setShSubSname(wb.getSubSname());
        sh.setShSubOgrn(wb.getSubOgrn());
        if (wb.getStaffSubPersonUnid() != null) {
            sh.setShSubManF(wb.getStaffSubPersonNameF());
            sh.setShSubManI(wb.getStaffSubPersonNameI());
            sh.setShSubManO(wb.getStaffSubPersonNameO());
            sh.setShSubManPost(wb.getDlaName());
            sh.setShSubManBase(wb.getSstaffFoundation());
        }
    }

    private void addSubjectHistoryIpFields(SubjectHistory sh, VWebBidderAll wb) {
        sh.setShSubName(wb.getSubName());
        sh.setShSubSname(wb.getSubSname());
        sh.setShSubNameF(wb.getStaffSubPersonNameF());
        sh.setShSubNameI(wb.getStaffSubPersonNameI());
        sh.setShSubNameO(wb.getStaffSubPersonNameO());

        sh.setShSubOgrn(wb.getSubOgrn());
    }

    private void addSubjectHistoryPassportFields(SubjectHistory sh, VDocLinkBkr passport) {
        sh.setShSubDocumName(DOCUMENT_PASSPORT_NAME);
        sh.setShSubDocumNumber(passport.getDocumNumber());
        sh.setShSubDocumSeries(passport.getDocumSeries());
        sh.setShSubDocumIssueAgency(passport.getDocumIssueAgency());
        sh.setShSubDocumIssueDate(passport.getDocumIssueDate());
    }

    private void addSubjectHistoryPaPassportFields(SubjectHistory sh, VDocLinkBkr passport) {
        sh.setShPaSubDocumName(DOCUMENT_PASSPORT_NAME);
        sh.setShPaSubDocumNumber(passport.getDocumNumber());
        sh.setShPaSubDocumSeries(passport.getDocumSeries());
        sh.setShPaSubDocumIssueAgency(passport.getDocumIssueAgency());
        sh.setShPaSubDocumIssueDate(passport.getDocumIssueDate());
    }

    private void addSubjectHistoryPaTrustyFields(SubjectHistory sh, VDocLinkBkr document) {
        sh.setShPaDocName(document.getTypeDocName());
        sh.setShPaDocNumber(document.getDocumNumber());
        sh.setShPaDocDate(document.getDocumIssueDate());
    }

    private Result<DocFile, String> createDocFile(byte[] buf, String extention) {
        File dir = new File(pathToFiles + File.separator + new SimpleDateFormat("yyyyMMdd").format(new Date()));
        if (!dir.exists()) {
            dir.mkdir();
        }
        try {
            File file = File.createTempFile("tmp-", "." + extention, dir);

            BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(file));
            output.write(buf);
            DocFile docFile = new DocFile();
            docFile.setDfSize(buf.length);
            docFile.setDfFilePath(dir.getName() + File.separator + file.getName());
            docFile.setDfNameDb(file.getName());
            return Result.ok(docFile);
        } catch (Exception e) {
            logger.info("Error create doc file", e);
            return Result.error(e.getMessage());
        }
    }

    private Optional<List<VAdditionalDataBkr>> getBkrProtocols(EntityManager emBkr, Long doUnid, Long tadUnid) {
        try {
            List<VAdditionalDataBkr> addDataProtocols = emBkr.createNamedQuery("VAdditionalDataBkr.findByDoAndTadUnid")
                    .setParameter("doUnid", doUnid)
                    .setParameter("tadUnid", tadUnid)
                    .getResultList();
            return Optional.of(addDataProtocols);
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    private Result<Document, String> loadProtocol(EntityManager emEis, VAdditionalDataBkr protocol, Long lotUnid, Long tdUnid, Long paUnid) {
        try {
            FileResponse file = bkrFileService.getFileFromStorage(protocol.getAddDfUnid());
            if (file == null || file.getBytes().length == 0) {
                return Result.error("Не удалось загрузить документ.");
            }

            String fileName = protocol.getAddFileName();
            String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
            Result<DocFile, String> docFileRes = createDocFile(file.getBytes(), ext);
            if (docFileRes.isError()) {
                return Result.error("Не удалось загрузить документ. Ошибка: " + docFileRes.getError());
            }

            DocFile docFile = docFileRes.getResult();

            docFile.setDateB(new Date());
            docFile.setPersCodeB(paUnid);
            docFile.setIndActual(1);
            docFile.setDfExt(ext);
            docFile.setDfName(fileName);
            emEis.persist(docFile);

            Document document = new Document();
            TypeDoc typeDoc = emEis.find(TypeDoc.class, tdUnid);
            document.setDocumIndExport(typeDoc.getTdIndLoadSite());
            document.setDocumIndExportEtp(typeDoc.getTdIndLoadEtp());
            document.setDocumIndExportFeed(typeDoc.getTdIndLoadFeed());

            document.setDateB(new Date());
            document.setPersCodeB(paUnid);
            document.setIndActual(1);
            document.setTdUnid(typeDoc);
            document.setLotUnid(lotUnid);
            document.setDfUnid(docFile);
            document.setDocumName(protocol.getAddNote());
            document.setDocumDate(protocol.getDateBRec());
            emEis.persist(document);
            return Result.ok(document);
        } catch (Exception ex) {
            logger.error("Error loadProtocol", ex);
            return Result.error("Не удалось загрузить документ. Ошибка: " + ex.getMessage());
        }
    }

    private Result<Long, String> getEisNonExecReasonUnid(EntityManager eisEm, Long bkrNonExecReasonUnid) {
        try {
            Long nonExecReasonUnid = ((BigDecimal) eisEm.createNativeQuery("select t.cc_source_unid from web.convert_classif t " +
                            "where t.cs_unid = :csUnid and t.cdict_unid = :cdistUnid and t.cc_rad_unid = :ccRadUnid ")
                    .setParameter("csUnid", ConvertSource.BANKRUPTCY.getUnid())
                    .setParameter("cdistUnid", ConvertDist.NON_EXEC_REASON.getUnid())
                    .setParameter("ccRadUnid", bkrNonExecReasonUnid)
                    .getSingleResult()).longValue();
            return Result.ok(nonExecReasonUnid);
        } catch (NoResultException ex) {
            logger.info("getCCValue not found csUnid = " + ConvertSource.BANKRUPTCY + " cdistUnid = "
                    + ConvertDist.NON_EXEC_REASON + " ccRadUnid = " + bkrNonExecReasonUnid);
            return Result.error("converter for NonExecReason not found by unid = " + bkrNonExecReasonUnid);
        }
    }

    private Result<Long, String> getEisTypeDocUnid(EntityManager eisEm, Long bkrTypeDocUnid) {
        try {
            Long nonExecReasonUnid = ((BigDecimal) eisEm.createNativeQuery("select t.cc_source_unid from web.convert_classif t " +
                            "where t.cs_unid = :csUnid and t.cdict_unid = :cdistUnid and t.cc_rad_unid = :ccRadUnid ")
                    .setParameter("csUnid", ConvertSource.BANKRUPTCY.getUnid())
                    .setParameter("cdistUnid", ConvertDist.TYPE_DOC.getUnid())
                    .setParameter("ccRadUnid", bkrTypeDocUnid)
                    .getSingleResult()).longValue();
            return Result.ok(nonExecReasonUnid);
        } catch (NoResultException ex) {
            logger.info("getCCValue not found csUnid = " + ConvertSource.BANKRUPTCY + " cdistUnid = "
                    + ConvertDist.TYPE_DOC + " ccRadUnid = " + bkrTypeDocUnid);
            return Result.error("converter for type_doc not found by unid = " + bkrTypeDocUnid);
        }
    }

    private Long getLotTsUnid(Long tsUnidBkr) {
        Long tsUnid;
        if (tsUnidBkr.equals(BkrTypeStates.DO_TYPE_STATE_NOT_DONE.getUnid())
                || tsUnidBkr.equals(BkrTypeStates.DO_TYPE_STATE_REPEATED.getUnid())) {
            tsUnid = TypeStateConstant.LOT_NOT_DONE.getId();
        } else if (tsUnidBkr.equals(BkrTypeStates.DO_TYPE_STATE_DONE.getUnid())) {
            tsUnid = TypeStateConstant.LOT_DONE.getId();
        } else if (tsUnidBkr.equals(BkrTypeStates.DO_TYPE_STATE_CANCELED.getUnid())
                || tsUnidBkr.equals(BkrTypeStates.DO_TYPE_STATE_ANNULED.getUnid())) {
            tsUnid = TypeStateConstant.LOT_CANCEL.getId();
        } else {
            tsUnid = TypeStateConstant.NOT_TAKE_PLACE.getId();
        }
        return tsUnid;
    }

    private Long getObjTsUnid(Long tsUnidLot) {
        Long tsUnid;
        if (tsUnidLot.equals(TypeStateConstant.LOT_NOT_DONE.getId())) {
            tsUnid = TypeStateConstant.TRADE_DONE.getId();
        } else if (tsUnidLot.equals(TypeStateConstant.LOT_DONE.getId())) {
            tsUnid = TypeStateConstant.TRADE_DONE.getId();
        } else if (tsUnidLot.equals(TypeStateConstant.LOT_CANCEL.getId())) {
            tsUnid = TypeStateConstant.TRADE_CANCELED.getId();
        } else {
            tsUnid = TypeStateConstant.IN_WORK.getId();
        }
        return tsUnid;
    }

    private Integer getLotStatus(Long tsUnidBkr) {
        Integer lotStatus;
        if (tsUnidBkr.equals(BkrTypeStates.DO_TYPE_STATE_NOT_DONE.getUnid())
                || tsUnidBkr.equals(BkrTypeStates.DO_TYPE_STATE_REPEATED.getUnid())) {
            lotStatus = LotStatus.TRADE_NOT_DONE.getCode();
        } else if (tsUnidBkr.equals(BkrTypeStates.DO_TYPE_STATE_DONE.getUnid())) {
            lotStatus = LotStatus.TRADE_DONE.getCode();
        } else if (tsUnidBkr.equals(BkrTypeStates.DO_TYPE_STATE_CANCELED.getUnid())
                || tsUnidBkr.equals(BkrTypeStates.DO_TYPE_STATE_ANNULED.getUnid())) {
            lotStatus = LotStatus.TRADE_CANCEL.getCode();
        } else {
            lotStatus = LotStatus.TRADE_NO.getCode();
        }
        return lotStatus;
    }


    public Result<Void, String> deleteAuctionBkrNewConnection(long bkrAuctionUnid) throws Exception {
        EntityManager emBkr = null;
        Connection conn = null;
        try {
            emBkr = HibernateUtil.getInstance().getEntityManagerBKR();
            conn = HibernateUtil.getInstance().getConnectionProviderBKR().getConnection();
            emBkr.getTransaction().begin();
            deleteAuctionBkr(conn, bkrAuctionUnid);
            conn.commit();
            emBkr.getTransaction().commit();
            return Result.ok();
        } catch (Exception ex) {
            rollbackConnection(conn);
            rollbackTransaction(emBkr);
            return Result.error(ex.getMessage());
        } finally {
            closeConnection(conn);
            closeEntityManager(emBkr);
        }
    }

    private void deleteAuctionBkr(Connection conn, long bkrAuctionUnid) throws Exception {
        CallableStatement cs = null;
        try {
            cs = conn.prepareCall("{call WEB.CREATE_ENTITIES.PROC_DROP_AUCTION_DP(?,?)}");
            cs.setLong(1, bkrAuctionUnid);
            cs.registerOutParameter(2, Types.VARCHAR);
            cs.executeUpdate();
        } finally {
            closeCallableStatement(cs);
        }
    }

    private Result<Void, String> receiveApplicationDocuments(EntityManager emBkr, EntityManager emEis,
                                                             Long bkrApplicationUnid, Application eisApplication,
                                                             Long paUnid) {
        List<VDocLinkBkr> docs = emBkr.createQuery("select d from VDocLinkBkr d " +
                        "where d.docLinkCodeEntity = 'APPLICATION' and d.docLinkEntityUnid = :entityUnid ", VDocLinkBkr.class)
                .setParameter("entityUnid", bkrApplicationUnid)
                .getResultList();
        for (VDocLinkBkr doc : docs) {
            FileResponse file = bkrFileService.getFileFromStorage(doc.getDfUnid());
            if (file == null || file.getBytes().length == 0) {
                return Result.error("Не удалось загрузить документ.");
            }

            String fileName = doc.getDocBodyFile();
            String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
            Result<DocFile, String> docFileRes = createDocFile(file.getBytes(), ext);
            if (docFileRes.isError()) {
                return Result.error("Не удалось загрузить документ. Ошибка: " + docFileRes.getError());
            }

            DocFile docFile = docFileRes.getResult();

            docFile.setDateB(new Date());
            docFile.setPersCodeB(paUnid);
            docFile.setIndActual(1);
            docFile.setDfExt(ext);
            docFile.setDfName(doc.getDocumNote());
            emEis.persist(docFile);

            Document document = new Document();
            Result<Long, String> typeDocResult = getEisTypeDocUnid(emEis, doc.getTypeDocUnid());
            Long tdUnid;
            if (typeDocResult.isSuccess()) {
                tdUnid = typeDocResult.getResult();
            } else {
                tdUnid = TypeDocConstant.OTHER.getUnid();
            }
            TypeDoc typeDoc = emEis.find(TypeDoc.class, tdUnid);
            document.setDocumIndExport(typeDoc.getTdIndLoadSite());
            document.setDocumIndExportEtp(typeDoc.getTdIndLoadEtp());
            document.setDocumIndExportFeed(typeDoc.getTdIndLoadFeed());

            document.setDateB(new Date());
            document.setPersCodeB(paUnid);
            document.setIndActual(1);
            document.setTdUnid(typeDoc);
            document.setApplicatUnid(eisApplication);
            document.setDfUnid(docFile);
            document.setDocumName(doc.getDocumNote());
            emEis.persist(document);
        }
        return Result.ok();
    }

    private void createMessageForAccountant(EntityManager emEis, ObjectJPA object, Lot lot) {
        try {
            Optional<Deal> dealResult = dealController.getCommissionDeal(emEis, object.getObjUnid());
            if (!dealResult.isPresent()) {
                return;
            }
            Deal deal = dealResult.get();
            Long paBk = deal.getPaUnid();

            if (paBk == null && deal.getDeaDealUnid() != null) {
                paBk = deal.getDeaDealUnid().getPaUnid();
            }
            if (paBk == null) {
                return;
            }
            accountantNotificationService.createTradeResultNotification(paBk, lot);
        } catch (Exception ex) {
            logger.error("Error createMessageForAccountant for objUnid = {}", object.getObjUnid(), ex);
        }
    }

}
