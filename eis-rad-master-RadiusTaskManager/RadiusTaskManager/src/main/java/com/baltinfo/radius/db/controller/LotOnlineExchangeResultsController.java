package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.bankruptcy.export.AdditionalPropertiesService;
import com.baltinfo.radius.dadata.dto.AddressDto;
import com.baltinfo.radius.dadata.services.DadataService;
import com.baltinfo.radius.db.constants.ApplicationForm;
import com.baltinfo.radius.db.constants.ApplicationReviewResult;
import com.baltinfo.radius.db.constants.AuctionDealTypeCost;
import com.baltinfo.radius.db.constants.CostIndTax;
import com.baltinfo.radius.db.constants.EisCurrencyUnid;
import com.baltinfo.radius.db.constants.EntityConstant;
import com.baltinfo.radius.db.constants.LOParticipationRequestStatus;
import com.baltinfo.radius.db.constants.LotOnlineBiddingResult;
import com.baltinfo.radius.db.constants.LotOnlineProfileType;
import com.baltinfo.radius.db.constants.LotOnlineProtocol;
import com.baltinfo.radius.db.constants.LotOnlineSaleType;
import com.baltinfo.radius.db.constants.LotOnlineTenderStatus;
import com.baltinfo.radius.db.constants.LotStatus;
import com.baltinfo.radius.db.constants.SaleGuides;
import com.baltinfo.radius.db.constants.TypeAuctionCode;
import com.baltinfo.radius.db.constants.TypeAuctionUnid;
import com.baltinfo.radius.db.constants.TypeCostConstant;
import com.baltinfo.radius.db.constants.TypeDocConstant;
import com.baltinfo.radius.db.constants.TypeStateConstant;
import com.baltinfo.radius.db.constants.TypeSubject;
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
import com.baltinfo.radius.db.model.Step;
import com.baltinfo.radius.db.model.Subject;
import com.baltinfo.radius.db.model.SubjectHistory;
import com.baltinfo.radius.db.model.TypeDoc;
import com.baltinfo.radius.db.model.lotonline.Bids;
import com.baltinfo.radius.db.model.lotonline.CancelReason;
import com.baltinfo.radius.db.model.lotonline.LotInfo;
import com.baltinfo.radius.db.model.lotonline.LotReport;
import com.baltinfo.radius.db.model.lotonline.Offer;
import com.baltinfo.radius.db.model.lotonline.ParticipationRequest;
import com.baltinfo.radius.db.model.lotonline.Tender;
import com.baltinfo.radius.db.model.lotonline.UserProfile;
import com.baltinfo.radius.exchange.ExchangeResult;
import com.baltinfo.radius.lotonline.export.LotOnlineProtocolService;
import com.baltinfo.radius.notification.impl.AccountantNotificationService;
import com.baltinfo.radius.radapi.security.TokenService;
import com.baltinfo.radius.subject.SubjectUtils;
import com.baltinfo.radius.utils.DateUtils;
import com.baltinfo.radius.utils.HibernateUtil;
import com.baltinfo.radius.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TimeZone;
import java.util.stream.Collectors;

/**
 * @author Suvorina Aleksandra
 * @since 10.10.2018
 */
public class LotOnlineExchangeResultsController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(LotOnlineExchangeResultsController.class);

    private static final String APPL_ETP_PREFIX = "ЭТП-";
    private static final String DOCUMENT_PASSPORT_NAME = "Паспорт";
    private static final BigDecimal DEFAULT_TAX_PERCENT = new BigDecimal("20.0");

    private final SubjectUtils subjectUtils;
    private final SubjectDao subjectDao;
    private final OperJournalController operJournalController;
    private final String pathToFiles;
    private final LotOnlineProtocolService lotOnlineProtocolService;
    private final DealController dealController;
    private final ObjRoleDao objRoleDao;
    private final RewardController rewardController;
    private final AdditionalPropertiesService additionalPropertiesService;
    private final RateController rateController;
    private final AccountantNotificationService accountantNotificationService;
    private final DadataService dadataService;
    private final VitrinaController vitrinaController;
    private final TokenService tokenService;
    private final String lotonlineAuthJwtTokenSubjectJson;

    private static final String DATEFORMAT = "dd.MM.yyyy HH:mm:ss.SSS";
    private static final SimpleDateFormat sdfUTC = new SimpleDateFormat(DATEFORMAT);

    static {
        //Форматируем дату со сдвигом в UTC
        sdfUTC.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    private static final SimpleDateFormat sdfCur = new SimpleDateFormat(DATEFORMAT);

    public LotOnlineExchangeResultsController(SubjectUtils subjectUtils, SubjectDao subjectDao,
                                              OperJournalController operJournalController,
                                              String pathToFiles,
                                              LotOnlineProtocolService lotOnlineProtocolService,
                                              DealController dealController,
                                              ObjRoleDao objRoleDao,
                                              RewardController rewardController,
                                              AdditionalPropertiesService additionalPropertiesService,
                                              RateController rateController,
                                              AccountantNotificationService accountantNotificationService,
                                              DadataService dadataService, VitrinaController vitrinaController,
                                              TokenService tokenService, String lotonlineAuthJwtTokenSubjectJson) {
        this.subjectUtils = subjectUtils;
        this.subjectDao = subjectDao;
        this.operJournalController = operJournalController;
        this.pathToFiles = pathToFiles;
        this.lotOnlineProtocolService = lotOnlineProtocolService;
        this.dealController = dealController;
        this.objRoleDao = objRoleDao;
        this.rewardController = rewardController;
        this.additionalPropertiesService = additionalPropertiesService;
        this.rateController = rateController;
        this.accountantNotificationService = accountantNotificationService;
        this.dadataService = dadataService;
        this.vitrinaController = vitrinaController;
        this.tokenService = tokenService;
        this.lotonlineAuthJwtTokenSubjectJson = lotonlineAuthJwtTokenSubjectJson;
    }

    public Result<Boolean, String> checkLotIsFinished(Long loTenderId, Long lotNumber) {
        EntityManager emLo = null;
        try {
            emLo = HibernateUtil.getInstance().getEntityManagerLOLS3();
            LotInfo lotInfo = (LotInfo) emLo.createQuery("select l from LotInfo l " +
                            " where l.tenderFk.id = :tenderId and l.lotNumber = :lotNumber and l.tenderStatus <> :statusDeleted")
                    .setParameter("tenderId", loTenderId)
                    .setParameter("lotNumber", lotNumber.intValue())
                    .setParameter("statusDeleted", LotOnlineTenderStatus.DELETED.toString())
                    .getSingleResult();
            return Result.ok(lotInfo.getTenderStatus() != null && lotInfo.getTenderStatus().equals(LotOnlineTenderStatus.FINISHED.name()));
        } catch (NoResultException ex) {
            logger.error("NoResultException checkLotIsFinished. loTenderId = {}, lotNumber = {}",
                    loTenderId, lotNumber, ex);
            return Result.error("Лот №" + lotNumber + " не найден на площадке lot-online.");
        } catch (Exception ex) {
            logger.error("Error checkLotIsFinished. loTenderId = {}, lotNumber = {}",
                    loTenderId, lotNumber, ex);
            return Result.error(ex.getMessage());
        } finally {
            closeEntityManager(emLo);
        }
    }

    public ExchangeResult receiveResultsByLot(Auction eisAuction, Lot eisLot, Long loTenderId, Long paUnid) {
        EntityManager emLo = null;
        EntityManager emUserShard1 = null;
        EntityManager emEis = null;
        try {
            List<String> errors = new ArrayList<>();
            emLo = HibernateUtil.getInstance().getEntityManagerLOLS3();
            emEis = HibernateUtil.getInstance().getEntityManager();
            emUserShard1 = HibernateUtil.getInstance().getEntityManagerLOUS1();
            emEis.getTransaction().begin();

            LotInfo lotInfo = (LotInfo) emLo.createQuery("select l from LotInfo l " +
                            " where l.tenderFk.id = :tenderId and l.lotNumber = :lotNumber and l.tenderStatus <> :statusDeleted")
                    .setParameter("tenderId", loTenderId)
                    .setParameter("lotNumber", eisLot.getLotNumber().intValue())
                    .setParameter("statusDeleted", LotOnlineTenderStatus.DELETED.toString())
                    .getSingleResult();

            Tender tender = emLo.find(Tender.class, loTenderId);

            // Заявки
            List<ParticipationRequest> paRequests = emLo.createQuery("select p from ParticipationRequest p where p.lotId = :lotId")
                    .setParameter("lotId", new BigInteger(lotInfo.getId() + ""))
                    .getResultList();

            boolean endCostExist = false;
            Map<Long, SubjectHistory> paProfilesSubjectHistory = new HashMap<>();
            List<Declarant> eisApplDeclarants = new ArrayList<>();
            List<Application> approvedApplications = new ArrayList<>();
            for (ParticipationRequest paRequest : paRequests) {
                Result<Application, String> applResult = receiveApplication(emLo, emEis, paRequest,
                        lotInfo, eisAuction, eisLot, paUnid);
                if (applResult.isError()) {
                    rollbackTransaction(emEis);
                    return ExchangeResult.notLoaded(applResult.getError());
                }
                Application eisApplication = applResult.getResult();

                if (eisApplication.getTsUnid().equals(TypeStateConstant.APP_ALLOWED.getId())) {
                    approvedApplications.add(eisApplication);
                }

                UserProfile userProfile = null;
                userProfile = emUserShard1.find(UserProfile.class, paRequest.getProfileId());

                SubjectHistory subjectHistory = null;
                if (paProfilesSubjectHistory.containsKey(userProfile.getId())) {
                    subjectHistory = paProfilesSubjectHistory.get(userProfile.getId());
                } else {
                    Result<SubjectHistory, String> shResult =
                            receiveSubjectHistory(emEis, userProfile, paUnid,
                                    subjectDao.isOwnerKio(emEis, eisLot.getObjUnid().getObjUnid()));
                    if (shResult.isError()) {
                        rollbackTransaction(emEis);
                        return ExchangeResult.notLoaded(shResult.getError());
                    }
                    subjectHistory = shResult.getResult();
                    paProfilesSubjectHistory.put(userProfile.getId(), subjectHistory);
                }

                Result<Declarant, String> declarantResult = buildDeclarant(emEis, subjectHistory, eisApplication, paUnid);
                if (declarantResult.isError()) {
                    rollbackTransaction(emEis);
                    return ExchangeResult.notLoaded(declarantResult.getError());
                }

                if (eisApplication.getApplicatIndWinner()) {
                    Result<Void, String> endCostResult = receiveEndCost(emEis, eisApplication, eisLot, paUnid, eisAuction);
                    if (endCostResult.isError()) {
                        rollbackTransaction(emEis);
                        return ExchangeResult.notLoaded(endCostResult.getError());
                    }
                    endCostExist = true;
                }
                eisApplDeclarants.add(declarantResult.getResult());
            }
            // Шаги
            List<Bids> bids = emLo.createQuery("select b from Bids b, TenderFlow tf "
                            + "where b.tenderFlowFk = tf.id "
                            + "and b.bidStatus = 'SUCCESSFULL' "
                            + "and tf.lotInfoFk = :lotInfoFk "
                            + "order by b.bidPrice asc")
                    .setParameter("lotInfoFk", lotInfo.getId())
                    .getResultList();
            Integer stepNumber = 1;
            for (Bids bid : bids) {
                Step step = buildStep(bid);
                step.setDateB(new Date());
                step.setPersCodeB(paUnid);
                step.setIndActual(1);
                step.setStepNumber(stepNumber);
                step.setLotUnid(eisLot.getLotUnid());
                stepNumber++;
                emEis.persist(step);
            }

            Document resultProtocol = null;
            Result<String, String> token = tokenService.generateToken(lotonlineAuthJwtTokenSubjectJson);
            if (token.isError()) {
                errors.add("Не удалось сформировать токен для вызова сервиса получения протокола. " + token.getError());
            } else {
                // Протоколы
                List<LotReport> lotReports = emLo.createQuery("select r from LotReport r where r.lotFk = :lotInfoFk")
                        .setParameter("lotInfoFk", lotInfo.getId())
                        .getResultList();
                for (LotReport lotReport : lotReports) {
                    Long typeDocUnid = getTypeDocUnid(lotReport.getCode());
                    if (typeDocUnid != null) {

                        Result<byte[], String> bufResult = lotOnlineProtocolService.getProtocol(lotReport.getId(), lotReport.getReportFilename(), token.getResult());
                        if (bufResult.isError()) {
                            rollbackTransaction(emEis);
                            return ExchangeResult.notLoaded("Ошибка при получении файла " + lotReport.getReportFilename()
                                    + ". " + bufResult.getError());
                        } else {
                            byte[] buf = bufResult.getResult();
                            String fileName = lotReport.getReportFilename();
                            String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
                            DocFile docFile = createDocFile(buf, ext);
                            Date now = new Date();
                            if (docFile != null) {
                                docFile.setDateB(now);
                                docFile.setPersCodeB(paUnid);
                                docFile.setIndActual(1);
                                docFile.setDfExt(ext);
                                docFile.setDfName(fileName);
                                emEis.persist(docFile);

                                Document document = new Document();
                                document.setDateB(now);
                                document.setPersCodeB(paUnid);
                                document.setIndActual(1);
                                TypeDoc typeDoc = emEis.find(TypeDoc.class, typeDocUnid);
                                document.setDocumIndExport(typeDoc.getTdIndLoadSite());
                                document.setDocumIndExportEtp(typeDoc.getTdIndLoadEtp());
                                document.setDocumIndExportFeed(typeDoc.getTdIndLoadFeed());
                                document.setTdUnid(typeDoc);
                                document.setLotUnid(eisLot.getLotUnid());
                                document.setDfUnid(docFile);
                                document.setDocumName(lotReport.getReportName());
                                document.setDocumDate(lotReport.getReportTime());
                                emEis.persist(document);

                                if (document.getTdUnid().getTdUnid().equals(TypeDocConstant.PROTOCOL_RESULT.getUnid())) {
                                    resultProtocol = document;
                                }
                            }
                        }
                    }
                }
            }

            // Статус лота
            if (lotInfo.getBiddingResult() == null || lotInfo.getBiddingResult().isEmpty()) {
                rollbackTransaction(emEis);
                return ExchangeResult.notLoaded("Отсутствует код результата торгов по лоту №" + eisLot.getLotNumber());
            }
            eisLot.setLotApplCount((long) approvedApplications.size());
            eisLot.setTsUnid(getLotTsUnid(lotInfo.getBiddingResult()));
            eisLot.setLotStatus(getLotStatus(lotInfo.getBiddingResult()));
            eisLot.setDateBRec(new Date());
            eisLot.setPersCodeBRec(paUnid);
            if (eisLot.getTsUnid().equals(TypeStateConstant.LOT_CANCEL.getId())
                    && lotInfo.getCancelReasonFk() != null) {
                CancelReason cancelReason = emLo.find(CancelReason.class, lotInfo.getCancelReasonFk());
                eisLot.setLotAuctionCancelCause(cancelReason.getDescription());
            }
            emEis.merge(eisLot);

            ObjectJPA obj = emEis.find(ObjectJPA.class, eisLot.getObjUnid().getObjUnid());

            boolean dealNotSoldSingle = false;
            Optional<Deal> dealResult = dealController.getCommissionDeal(emEis, obj.getObjUnid());
            if (dealResult.isPresent()) {
                Deal deal = dealResult.get();
                if (deal.getDeaDealUnid() != null) {
                    deal = deal.getDeaDealUnid();
                }
                dealNotSoldSingle = deal.getDealNotSoldSingle() != null && deal.getDealNotSoldSingle();
            }

            boolean isNeedCreateRewards = false;
            if (eisLot.getTsUnid().equals(TypeStateConstant.LOT_NOT_DONE.getId())
                    && approvedApplications.size() == 1
                    && (!(eisAuction.getSgUnid().equals(SaleGuides.PRIVATIZATION)
                    || eisAuction.getSgUnid().equals(SaleGuides.STATE_PROPERTY_MOSCOW)
                    || eisAuction.getSgUnid().equals(SaleGuides.STATE_PROPERTY_MUNICIPAL))
                    || eisAuction.getTypeAuctionUnid().getTypeAuctionUnid().equals(TypeAuctionUnid.AUCTION))
                    && !dealNotSoldSingle) {
                obj.setObjIndSaled(1);
                if (resultProtocol != null) {
                    obj.setObjSaleDate(DateUtils.truncate(resultProtocol.getDocumDate()));
                } else {
                    obj.setObjSaleDate(DateUtils.truncate(lotInfo.getBiddingStop()));
                }
                obj.setTsUnid(TypeStateConstant.OBJ_SALED.getId());

                isNeedCreateRewards = true;
                SubjectHistory participantSh = null;
                Optional<Declarant> singleParticipant = eisApplDeclarants.stream()
                        .filter(decl -> decl.getApplicatUnid().getTsUnid().equals(TypeStateConstant.APP_ALLOWED.getId()))
                        .findFirst();
                if (singleParticipant.isPresent()) {
                    participantSh = singleParticipant.get().getShUnid();
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
                ObjCost futureSaleCost;
                if (eisAuction.getTypeAuctionUnid().getTypeAuctionCode().equals(TypeAuctionCode.HOLLAND.getCode())) {
                    if (eisAuction.getAuctionDealTypeCost() == null) {
                        futureSaleCost = getMinCost(emEis, eisLot.getLotUnid());
                    } else {
                        futureSaleCost = eisAuction.getAuctionDealTypeCost() == AuctionDealTypeCost.START_COST.getCode()
                                ? getStartCost(emEis, eisLot.getLotUnid())
                                : getMinCost(emEis, eisLot.getLotUnid());
                    }
                } else {
                    futureSaleCost = getStartCost(emEis, eisLot.getLotUnid());
                }
                if (futureSaleCost != null || participantSh != null) {
                    boolean createNewDeal = true;
                    Optional<Deal> dealOptional = dealController.getSaleDeal(emEis, eisLot.getObjUnid().getObjUnid());
                    if (dealOptional.isPresent()) {
                        Result<Void, String> deleteDealResult = dealController.deleteSaleDeal(emEis, dealOptional.get(), paUnid);
                        if (deleteDealResult.isError()) {
                            logger.error("Can't delete SaleDeal " + deleteDealResult.getError());
                            createNewDeal = false;
                        } else {
                            operJournalController.createOperJournalForDealDelete(emEis, dealOptional.get().getDealUnid(), paUnid);
                        }
                    }

                    if (createNewDeal) {
                        Result<Deal, String> saleDealResult = dealController.createSaleDeal(emEis, tpaUnid, paUnid,
                                eisLot.getObjUnid().getObjUnid(), futureSaleCost, participantSh);
                        if (saleDealResult.isError()) {
                            logger.error("Can't create SaleDeal " + saleDealResult.getError());
                        } else {
                            operJournalController.createOperJournalForDealCreate(emEis, saleDealResult.getResult().getDealUnid(), paUnid);
                        }
                    }
                }
            }

            if (eisLot.getTsUnid().equals(TypeStateConstant.LOT_DONE.getId()) && endCostExist) {
                obj.setObjIndSaled(1);
                if (resultProtocol != null) {
                    obj.setObjSaleDate(DateUtils.truncate(resultProtocol.getDocumDate()));
                } else {
                    obj.setObjSaleDate(DateUtils.truncate(lotInfo.getBiddingStop()));
                }

                isNeedCreateRewards = true;
            }
            obj.setTsUnid(getObjTsUnid(eisLot.getTsUnid()));
            obj.setDateBRec(new Date());
            obj.setPersCodeBRec(paUnid);
            if (obj.getObjIndSaled() != null && obj.getObjIndSaled() == 1) {
                obj.setTsUnid(TypeStateConstant.OBJ_SALED.getId());
            }

            if (lotInfo.getAddPropertyJson() != null && !lotInfo.getAddPropertyJson().isEmpty()) {
                additionalPropertiesService.updateAdditionalPropertiesFromJson(lotInfo.getAddPropertyJson(), obj.getObjUnid());
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

            operJournalController.createOperJournalForLotEdit(emEis, eisLot.getLotUnid(), "Перенесены результаты торгов по лоту с ЭТП lot-online", paUnid);
            operJournalController.createOperJournalForObjectEdit(emEis, obj.getObjUnid(), "Перенесены результаты торгов по лоту с ЭТП lot-online", paUnid);

            emEis.getTransaction().commit();
            if (errors.isEmpty()) {
                return ExchangeResult.loaded(lotInfo.getId());
            } else {
                return ExchangeResult.loadedWithErrors(lotInfo.getId(), String.join("\n", errors));
            }
        } catch (Exception ex) {
            logger.error("Error receiveResultsByLot. eisAuctionUnid = {}, eisLotUnid = {}, loTenderId = {}, paUnid = {}",
                    eisAuction.getAuctionUnid(), eisLot.getLotUnid(), loTenderId, paUnid, ex);
            rollbackTransaction(emEis);
            return ExchangeResult.notLoaded(ex.getMessage());
        } finally {
            closeEntityManager(emLo);
            closeEntityManager(emEis);
            closeEntityManager(emUserShard1);
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
                    "Перенесены результаты торгов с ЭТП Lot-online.ru", paUnid);
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

    public List<LotInfo> getFinishedLoLots(List<Long> loTenderFkList) {
        EntityManager emLo = null;
        try {
            emLo = HibernateUtil.getInstance().getEntityManagerLOLS3();
            List<LotInfo> loFinishedLots = emLo.createQuery("select l from LotInfo l " +
                            "where l.tenderFk.id in (:loTenderFkList) and l.tenderStatus = :finishedTenderStatus")
                    .setParameter("loTenderFkList", loTenderFkList)
                    .setParameter("finishedTenderStatus", LotOnlineTenderStatus.FINISHED.name())
                    .getResultList();
            return loFinishedLots;
        } catch (Exception ex) {
            logger.error("Error getFinishedLoLots.", ex);
        } finally {
            closeEntityManager(emLo);
        }
        return null;
    }

    private Result<Application, String> receiveApplication(EntityManager emLo, EntityManager emEis,
                                                           ParticipationRequest paRequest,
                                                           LotInfo lotInfo,
                                                           Auction eisAuction, Lot eisLot, Long paUnid) {
        try {

            BigDecimal bidPrice = null;
            Long tauCode = eisAuction.getTypeAuctionUnid().getTypeAuctionCode();
            boolean isObjectCulture = lotInfo.getTenderFk().isObjectCulture();
            boolean privatizationCompetitionCulture = tauCode.equals(TypeAuctionCode.COMPETITION.getCode())
                    && lotInfo.getTenderFk().getSaleTypeFk() == LotOnlineSaleType.PRIVATIZATION.getId()
                    && isObjectCulture;
            if (tauCode.equals(TypeAuctionCode.PRICELESS.getCode()) || (tauCode.equals(TypeAuctionCode.COMPETITION.getCode()) && !privatizationCompetitionCulture)) {
                List<Offer> offers = emLo.createQuery("select o from Offer o "
                                + "where o.lotId = :lotInfoFk "
                                + "and o.offerStatus = 'APPROVED' "
                                + "and o.profileId = :profileId order by o.price desc")
                        .setParameter("lotInfoFk", lotInfo.getId())
                        .setParameter("profileId", paRequest.getProfileId())
                        .setMaxResults(1)
                        .getResultList();

                if (!offers.isEmpty()) {
                    bidPrice = offers.get(0).getPrice();
                }
            } else {
                List<Bids> bids = emLo.createQuery("select b from Bids b, TenderFlow tf "
                                + "where b.tenderFlowFk = tf.id "
                                + "and b.bidStatus = 'SUCCESSFULL' "
                                + "and tf.lotInfoFk = :lotInfoFk "
                                + "and b.ticket = :ticket order by b.bidPrice desc")
                        .setParameter("lotInfoFk", lotInfo.getId())
                        .setParameter("ticket", paRequest.getTicket())
                        .setMaxResults(1)
                        .getResultList();

                if (!bids.isEmpty()) {
                    bidPrice = bids.get(0).getBidPrice();
                }
            }

            boolean indWinner = lotInfo.getUserWinnerId() != null
                    && lotInfo.getUserWinnerId().equals(paRequest.getProfileId())
                    && !paRequest.getState().equals(LOParticipationRequestStatus.CANCELED.getCode());

            Application application = buildApplication(paRequest, eisAuction, bidPrice, indWinner);
            application.setIndActual(1L);
            application.setDateB(new Date());
            application.setPersCodeB(paUnid);
            application.setLotUnid(eisLot.getLotUnid());
            emEis.persist(application);

            return Result.ok(application);
        } catch (Exception ex) {
            logger.error("Error receive application", ex);
            return Result.error("Ошибка при переносе заявки №" + paRequest.getRequestCode() + ": " + ex.getMessage());
        }
    }

    private Result<SubjectHistory, String> receiveSubjectHistory(EntityManager emEis,
                                                                 UserProfile userProfile,
                                                                 Long paUnid,
                                                                 boolean ownerKio) {
        try {
            SubjectHistory sh = buildSubjectHistory(userProfile);
            if (ownerKio) {
                // Для КИО всегда берем из дадаты, т.к. нужен fias_id с учетом квартиры
                if (userProfile.getCompanyAddress() != null && !userProfile.getCompanyAddress().isEmpty()) {
                    AddressDto address = dadataService.createAddressDtoByAddress(userProfile.getCompanyAddress());
                    if (address != null) {
                        sh.setShSubAddrFactFiasId(address.getFiasId());
                    }
                }
                if (userProfile.getCompanyLegalAddress() != null && !userProfile.getCompanyLegalAddress().isEmpty()) {
                    AddressDto address = dadataService.createAddressDtoByAddress(userProfile.getCompanyLegalAddress());
                    if (address != null) {
                        sh.setShSubAddrLegalFiasId(address.getFiasId());
                    }
                }
            } else {
                // Для остальных берем с витрины
                Optional<VitrinaAddressDto> addressDtoResult = vitrinaController.getAddressByBLoProfileId(userProfile.getId());
                if (addressDtoResult.isPresent()) {
                    VitrinaAddressDto addressDto = addressDtoResult.get();
                    sh.setShSubAddrLegalFiasId(addressDto.getLegalAddressFiasId());
                    sh.setShSubAddrFactFiasId(addressDto.getPostAddressFiasId());
                }

                // Если с витрины fias_id не пришел, берем из дадаты, в приоритете юридический адрес
                if ((sh.getShSubAddrLegalFiasId() == null || sh.getShSubAddrLegalFiasId().isEmpty()) &&
                        (sh.getShSubAddrFactFiasId() == null || sh.getShSubAddrFactFiasId().isEmpty())) {
                    if (userProfile.getCompanyLegalAddress() != null && !userProfile.getCompanyLegalAddress().isEmpty()) {
                        AddressDto address = dadataService.createAddressDtoByAddress(userProfile.getCompanyLegalAddress());
                        if (address != null) {
                            sh.setShSubAddrLegalFiasId(address.getFiasId());
                        }
                    }
                }

                // Если юридического нет, пробуем фактический
                if ((sh.getShSubAddrLegalFiasId() == null || sh.getShSubAddrLegalFiasId().isEmpty()) &&
                        (sh.getShSubAddrFactFiasId() == null || sh.getShSubAddrFactFiasId().isEmpty())) {
                    if (userProfile.getCompanyAddress() != null && !userProfile.getCompanyAddress().isEmpty()) {
                        AddressDto address = dadataService.createAddressDtoByAddress(userProfile.getCompanyAddress());
                        if (address != null) {
                            sh.setShSubAddrFactFiasId(address.getFiasId());
                        }
                    }
                }
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
            return Result.error("Ошибка при переносе информации о заявителе " + userProfile.getProfileTitle() + ": " + ex.getMessage());
        }
    }

    private Result<Void, String> receiveEndCost(EntityManager emEis, Application eisApplication, Lot eisLot, Long paUnid, Auction auction) {
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
            endCost.setCostValue(eisApplication.getApplicatAmount());
            endCost.setTypeCosUnid(TypeCostConstant.RESULT.getId());
            endCost.setCostIndCurrent(1);
            endCost.setCurUnid(eisLot.getCurUnid() != null ? eisLot.getCurUnid() : EisCurrencyUnid.RUB.getUnid());
            if (endCost.getCurUnid().equals(EisCurrencyUnid.RUB.getUnid())) {
                endCost.setCostValueRub(eisApplication.getApplicatAmount());
            } else {
                Date date;
                if (auction.getTypeAuctionUnid().getTypeAuctionCode() == TypeAuctionCode.PUBLIC_SALE.getCode()) {
                    date = eisLot.getObjUnid().getObjSaleDate();
                } else {
                    date = auction.getAuctionDateB();
                }
                Result<BigDecimal, String> rezCost = rateController.calcObjCostValueRub(emEis, date, eisApplication.getApplicatAmount(), endCost.getCurUnid());
                if (rezCost.isError()) throw new Exception(rezCost.getError());
                endCost.setCostValueRub(rezCost.getResult());
            }
            ObjCost startCost = null;
            try {
                startCost = (ObjCost) emEis.createNamedQuery("ObjCost.findStartCostByLotUnid")
                        .setParameter("lotUnid", eisLot.getLotUnid())
                        .getSingleResult();
            } catch (NoResultException ex) {
                logger.warn("No start cost found for lot lotUnid = {}", eisLot.getLotUnid());
            }
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

    private ObjCost getMinCost(EntityManager emEis, Long lotUnid) {
        return (ObjCost) emEis.createNamedQuery("ObjCost.findMinCostByLotUnid")
                .setParameter("lotUnid", lotUnid)
                .getSingleResult();
    }

    private ObjCost getStartCost(EntityManager emEis, Long lotUnid) {
        return (ObjCost) emEis.createNamedQuery("ObjCost.findStartCostByLotUnid")
                .setParameter("lotUnid", lotUnid)
                .getSingleResult();
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


    private Application buildApplication(ParticipationRequest participationRequest,
                                         Auction eisAuction,
                                         BigDecimal bidPrice,
                                         Boolean indWinner) throws ParseException {
        Application application = new Application();
        application.setEntityUnid(EntityConstant.APPLICATION.getId());
        application.setTpaUnid(eisAuction.getTpaUnid());
        application.setTsUnid(getEisApplicationState(participationRequest.getState()));
        application.setApplicatAppTime(convertFromUTC(participationRequest.getCreationDate()));
        application.setApplicatNumber(APPL_ETP_PREFIX + participationRequest.getRequestCode());
        application.setApplicatDate(null);
        application.setApplicatAmount(bidPrice);
        application.setApplicatIndWinner(indWinner);
        application.setApplicatReviewResults(getApplicatReviewResult(application.getTsUnid()));
        application.setApplicatCardNumber(participationRequest.getTicket() != null ? participationRequest.getTicket() + "" : "");
        application.setApplicatIndPayDep(participationRequest.getDepositId() != null);
        application.setApplicatDepositNumber(null);
        application.setApplicatDepositDate(null);
        application.setApplicatApplForm(ApplicationForm.ELECTRONIC.getCode());
        application.setApplicatPayDocNum(null);
        application.setApplicatPayDocDate(null);
        application.setApplicatWithdrawTime(convertFromUTC(participationRequest.getCancelDate()));
        application.setApplicatWithdrawReason(participationRequest.getCancelReason());
        application.setApplicatRefusalFound(participationRequest.getLastRejectionReason());
        application.setApplicatIndTransfer(false);
        application.setApplicatTransferDate(null);
        application.setApplicatIndDocCompliance(false);
        return application;
    }

    private Long getEisApplicationState(String state) {
        Long tsUnid = null;
        if (LOParticipationRequestStatus.REJECTED.getCode().equals(state)) {
            tsUnid = TypeStateConstant.APP_REJECTED.getId();
        } else if (LOParticipationRequestStatus.LOT_OWNER_APPROVED.getCode().equals(state)) {
            tsUnid = TypeStateConstant.APP_ALLOWED.getId();
        } else if (LOParticipationRequestStatus.CANCELED.getCode().equals(state)) {
            tsUnid = TypeStateConstant.APP_RECALLED.getId();
        } else if (LOParticipationRequestStatus.PENDING.getCode().equals(state)) {
            tsUnid = TypeStateConstant.APP_CREATED.getId();
        }
        return tsUnid;
    }

    private Integer getApplicatReviewResult(Long tsUnid) {
        Integer result = null;
        if (tsUnid.equals(TypeStateConstant.APP_ALLOWED.getId())) {
            result = ApplicationReviewResult.ALLOWED.getCode();
        } else if (tsUnid.equals(TypeStateConstant.APP_CREATED.getId())) {
            result = ApplicationReviewResult.PENDING.getCode();
        } else if (tsUnid.equals(TypeStateConstant.APP_RECALLED.getId())) {
            result = ApplicationReviewResult.CANCELED.getCode();
        } else if (tsUnid.equals(TypeStateConstant.APP_REJECTED.getId())) {
            result = ApplicationReviewResult.REJECTED.getCode();
        }
        return result;
    }

    private SubjectHistory buildSubjectHistory(UserProfile userProfile) {
        SubjectHistory sh = new SubjectHistory();
        sh.setTypesUnid(getTypesUnid(userProfile.getProfileType()));
        if (sh.getTypesUnid().equals(TypeSubject.FL.getUnid())) {
            addSubjectHistoryFlFields(sh, userProfile);
        } else if (sh.getTypesUnid().equals(TypeSubject.YL.getUnid())) {
            addSubjectHistoryYlFields(sh, userProfile);
        } else if (sh.getTypesUnid().equals(TypeSubject.IP.getUnid())) {
            addSubjectHistoryIpFields(sh, userProfile);
        }
        sh.setShSubOkpo(userProfile.getOkpo());
        sh.setShSubInn(userProfile.getInn());
        sh.setShSubAddrLegal(StringUtils.abbreviate(userProfile.getCompanyLegalAddress(), 250));
        sh.setShSubAddrFact(StringUtils.abbreviate(userProfile.getCompanyAddress(), 250));
        sh.setShSubPhone(userProfile.getPersonPhone());
        sh.setShSubFax(userProfile.getCompanyFax());
        sh.setShSubEMail(userProfile.getPersonEmail());
        sh.setShSubWeb(userProfile.getCompanyWebsite());
        sh.setShSubCodeKpp(userProfile.getKpp());
        sh.setShSubAccountName(null);
        sh.setShSubAccountCode(userProfile.getTransactionalAccount());
        sh.setShSubBankName(userProfile.getBankFullName());
        sh.setShSubBankBic(userProfile.getBankIdentificationCode());
        sh.setShSubBankAccount(userProfile.getCorrespondentAccount());
        sh.setShSubOkato(userProfile.getOkato());

        return sh;
    }

    private Long getTypesUnid(String profileType) {
        Long typesUnid = null;
        if (LotOnlineProfileType.PHYSICAL.name().equals(profileType)) {
            typesUnid = TypeSubject.FL.getUnid();
        } else if (LotOnlineProfileType.LEGAL.name().equals(profileType)) {
            typesUnid = TypeSubject.YL.getUnid();
        } else if (LotOnlineProfileType.INDIVIDUAL.name().equals(profileType)) {
            typesUnid = TypeSubject.IP.getUnid();
        }
        return typesUnid;
    }

    private void addSubjectHistoryFlFields(SubjectHistory sh, UserProfile userProfile) {
        sh.setShSubName(userProfile.getPersonFio());
        sh.setShSubSname(userProfile.getPersonFio());
        addSubjectHistoryFlNames(sh, userProfile.getPersonFio());
        addSubjectHistoryPassportFields(sh, userProfile);
    }

    private void addSubjectHistoryFlNames(SubjectHistory sh, String fio) {
        if (fio == null || fio.isEmpty()) {
            return;
        }
        String[] names = fio.trim().split("\\s");
        if (names.length > 0) {
            sh.setShSubNameF(names[0]);
        }
        if (names.length > 1) {
            sh.setShSubNameI(names[1]);
        }
        if (names.length > 2) {
            sh.setShSubNameO(String.join(" ", Arrays.asList(names).subList(2, names.length)));
        }
    }

    private void addSubjectHistoryYlFields(SubjectHistory sh, UserProfile userProfile) {
        sh.setShSubName(userProfile.getCompanyFullName());
        sh.setShSubSname(userProfile.getCompanyShortName());
        sh.setShSubOgrn(userProfile.getOgrn());
        String fio = userProfile.getResponsibleEmployeesFio();
        if (fio != null && !fio.isEmpty()) {
            String[] names = fio.trim().split("\\s");
            if (names.length > 0) {
                sh.setShSubManF(names[0]);
            }
            if (names.length > 1) {
                sh.setShSubManI(names[1]);
            }
            if (names.length > 2) {
                sh.setShSubManO(String.join(" ", Arrays.asList(names).subList(2, names.length)));
            }
        }
    }

    private void addSubjectHistoryIpFields(SubjectHistory sh, UserProfile userProfile) {
        sh.setShSubName(userProfile.getCompanyFullName());
        sh.setShSubSname(userProfile.getCompanyShortName());
        addSubjectHistoryFlNames(sh, userProfile.getPersonFio());
        addSubjectHistoryPassportFields(sh, userProfile);
        sh.setShSubOgrn(userProfile.getOgrnip());
    }

    private void addSubjectHistoryPassportFields(SubjectHistory sh, UserProfile userProfile) {
        sh.setShSubDocumName(DOCUMENT_PASSPORT_NAME);
        sh.setShSubDocumNumber(userProfile.getPassportNumber());
        sh.setShSubDocumSeries(userProfile.getPassportSerial());
        sh.setShSubDocumIssueAgency(userProfile.getPassportIssued());
        sh.setShSubDocumIssueDate(null);
    }

    private DocFile createDocFile(byte[] buf, String extention) {
        File dir = new File(pathToFiles + File.separator + new SimpleDateFormat("yyyyMMdd").format(new Date()));
        if (!dir.exists()) {
            dir.mkdir();
        }
        try {
            File file = File.createTempFile("tmp-", extention, dir);

            BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(file));
            output.write(buf);
            DocFile docFile = new DocFile();
            docFile.setDfSize(buf.length);
            docFile.setDfFilePath(dir.getName() + File.separator + file.getName());
            docFile.setDfNameDb(file.getName());
            return docFile;
        } catch (Exception e) {
            logger.info("Error create doc file", e);
        }
        return null;
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

    private Date convertFromUTC(Date dateIn) throws ParseException {
        if (dateIn == null) {
            return null;
        }
        return sdfUTC.parse(sdfCur.format(dateIn));
    }

    private Step buildStep(Bids bid) throws ParseException {
        Step step = new Step();
        step.setStepValue(bid.getBidPrice());
        step.setStepCardNumber(bid.getTicket() + "");
        step.setStepTimeStamp(convertFromUTC(bid.getBidTimestamp()));

        return step;
    }

    private Long getTypeDocUnid(String reportCode) {
        Long typeDocUnid = null;
        if (LotOnlineProtocol.isParticipationProtocol(reportCode)) {
            typeDocUnid = TypeDocConstant.PROTOCOL_APPL.getUnid();
        } else if (LotOnlineProtocol.isSummingUpProtocol(reportCode)) {
            typeDocUnid = TypeDocConstant.PROTOCOL_RESULT.getUnid();
        }
        return typeDocUnid;
    }

    private String getProtocolFilePath(LotReport report) throws ParseException {
        String filePath = "";
        String serverExt = report.getReportFilename().substring(report.getReportFilename().lastIndexOf(".") + 1);
        filePath = report.getReportFilename().substring(0, report.getReportFilename().lastIndexOf("."));
        Instant dateTime = new Instant(convertFromUTC(report.getReportTime()));
        filePath = dateTime.toDateTime(DateTimeZone.UTC).toLocalDate().toString() + "/" + filePath + "_" + dateTime.getMillis() + "." + serverExt;
        return filePath;
    }

    private Long getLotTsUnid(String biddingResult) {
        Long tsUnid = null;
        if (LotOnlineBiddingResult.DID_NOT_TAKE_PLACE.name().equals(biddingResult)) {
            tsUnid = TypeStateConstant.LOT_NOT_DONE.getId();
        } else if (LotOnlineBiddingResult.TOOK_PLACE.name().equals(biddingResult)) {
            tsUnid = TypeStateConstant.LOT_DONE.getId();
        } else if (LotOnlineBiddingResult.CANCELED_BY_OPERATOR.name().equals(biddingResult)) {
            tsUnid = TypeStateConstant.LOT_CANCEL.getId();
        } else if (LotOnlineBiddingResult.CANCELED_BY_ORGANIZER.name().equals(biddingResult)) {
            tsUnid = TypeStateConstant.LOT_CANCEL.getId();
        } else if (LotOnlineBiddingResult.SINGLE.name().equals(biddingResult)) {
            tsUnid = TypeStateConstant.LOT_NOT_DONE.getId();
        }
        return tsUnid;
    }

    private Integer getLotStatus(String biddingResult) {
        Integer lotStatus = null;
        if (LotOnlineBiddingResult.DID_NOT_TAKE_PLACE.name().equals(biddingResult)) {
            lotStatus = LotStatus.TRADE_NOT_DONE.getCode();
        } else if (LotOnlineBiddingResult.TOOK_PLACE.name().equals(biddingResult)) {
            lotStatus = LotStatus.TRADE_DONE.getCode();
        } else if (LotOnlineBiddingResult.CANCELED_BY_OPERATOR.name().equals(biddingResult)) {
            lotStatus = LotStatus.TRADE_CANCEL.getCode();
        } else if (LotOnlineBiddingResult.CANCELED_BY_ORGANIZER.name().equals(biddingResult)) {
            lotStatus = LotStatus.TRADE_CANCEL.getCode();
        } else if (LotOnlineBiddingResult.SINGLE.name().equals(biddingResult)) {
            lotStatus = LotStatus.TRADE_NOT_DONE.getCode();
        } else {
            lotStatus = LotStatus.TRADE_NO.getCode();
        }
        return lotStatus;
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
