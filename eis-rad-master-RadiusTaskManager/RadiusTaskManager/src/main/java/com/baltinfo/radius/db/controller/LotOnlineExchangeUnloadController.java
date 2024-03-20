package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.bankruptcy.export.AdditionalPropertiesService;
import com.baltinfo.radius.db.constants.AddrInputMode;
import com.baltinfo.radius.db.constants.LotOnlineCountry;
import com.baltinfo.radius.db.constants.LotOnlineCurrency;
import com.baltinfo.radius.db.constants.LotOnlineDepositType;
import com.baltinfo.radius.db.constants.LotOnlineOfferStepType;
import com.baltinfo.radius.db.constants.LotOnlineTenderStatus;
import com.baltinfo.radius.db.constants.LotOnlineThumbnailSize;
import com.baltinfo.radius.db.constants.OksmConstant;
import com.baltinfo.radius.db.constants.SaleGuides;
import com.baltinfo.radius.db.controller.exchange.ExchangeUnloadController;
import com.baltinfo.radius.db.model.Address;
import com.baltinfo.radius.db.model.lotonline.Documentlist;
import com.baltinfo.radius.db.model.lotonline.LoAddress;
import com.baltinfo.radius.db.model.lotonline.LotInfo;
import com.baltinfo.radius.db.model.lotonline.Tender;
import com.baltinfo.radius.db.model.lotonline.UserDocument;
import com.baltinfo.radius.exchange.ExchangeResult;
import com.baltinfo.radius.fias.client.FiasGarApiClient;
import com.baltinfo.radius.fias.dto.FiasAddrobj;
import com.baltinfo.radius.lotonline.export.LotOnlineDocParams;
import com.baltinfo.radius.lotonline.export.LotOnlineFileService;
import com.baltinfo.radius.lotonline.export.LotOnlineLotParams;
import com.baltinfo.radius.lotonline.export.LotOnlinePictureParams;
import com.baltinfo.radius.utils.HibernateUtil;
import com.baltinfo.radius.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;
import java.util.UUID;

/**
 * @author Suvorina Aleksandra
 * @since 22.08.2019
 */
public class LotOnlineExchangeUnloadController extends AbstractController implements ExchangeUnloadController<Tender, LotOnlineLotParams> {

    private static final Logger logger = LoggerFactory.getLogger(LotOnlineExchangeUnloadController.class);


    private static final String DATEFORMAT = "dd.MM.yyyy HH:mm:ss";
    private static final SimpleDateFormat sdfCur = new SimpleDateFormat(DATEFORMAT);
    //Форматируем дату со сдвигом в UTC
    private static final SimpleDateFormat sdfUTC = new SimpleDateFormat(DATEFORMAT);

    static {
        //Форматируем дату со сдвигом в UTC
        sdfUTC.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    private static final SimpleDateFormat sdfPathPhoto = new SimpleDateFormat("yyyyMMdd");
    private static final SimpleDateFormat sdfPathDoc = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat sdfPathYear = new SimpleDateFormat("yyyy");

    private final LotOnlineFileService lotOnlineFileService;
    private final LotOnlineImageUtils lotOnlineImageUtils;
    private final FiasGarApiClient fiasGarApiClient;
    private final AdditionalPropertiesService additionalPropertiesService;

    public LotOnlineExchangeUnloadController(LotOnlineFileService lotOnlineFileService,
                                             LotOnlineImageUtils lotOnlineImageUtils, FiasGarApiClient fiasGarApiClient,
                                             AdditionalPropertiesService additionalPropertiesService) {
        this.lotOnlineFileService = lotOnlineFileService;
        this.lotOnlineImageUtils = lotOnlineImageUtils;
        this.fiasGarApiClient = fiasGarApiClient;
        this.additionalPropertiesService = additionalPropertiesService;
    }

    @Override
    public ExchangeResult exportLot(Tender auction, LotOnlineLotParams params) {
        EntityManager emLo = null;
        Date now = new Date();
        try {
            emLo = HibernateUtil.getInstance().getEntityManagerLOLS3();
            emLo.getTransaction().begin();
            LotInfo lotInfo = new LotInfo();
            lotInfo.setLotTenderOrder(null);
            lotInfo.setTenderType(auction.getTenderType());
            lotInfo.setCategoryFk(params.getSaleCategory());
            lotInfo.setDescription(params.getObject().getObjNote());
            lotInfo.setInterDescription("");
            lotInfo.setName(params.getObject().getObjName());
            lotInfo.setInterName("");
            lotInfo.setPriceAmt(null);
            lotInfo.setDepositAmt(params.getLot().getLotSumDeposit());
            if (params.getMinCost() != null) {
                lotInfo.setMinAmt(params.getMinCost().getCostValue());
            }
            lotInfo.setBiddingStart(convertToUTC(params.getLot().getLotDateBPlan()));
            lotInfo.setBiddingStop(convertToUTC(params.getLot().getLotDateEPlan()));
            if (params.getStartCost() != null) {
                lotInfo.setStartAmt(params.getStartCost().getCostValue());
            }
            lotInfo.setStepDownAmt(params.getLot().getLotStepDecreaseValue());
            lotInfo.setStepUpAmt(params.getLot().getLotStepValue());
            lotInfo.setTenderStatus(LotOnlineTenderStatus.DRAFT.name());
            lotInfo.setExtensionTime(null);
            lotInfo.setTimeLeftForExtension(null);
            lotInfo.setCreationDate(convertToUTC(now));
            lotInfo.setRegionFk(null);
            lotInfo.setDocumentListFk(null);
            lotInfo.setLotCode(null);
            lotInfo.setAppSubmitStart(convertToUTC(params.getAuction().getAuctionRecepDateB()));
            lotInfo.setAppSubmitStop(convertToUTC(params.getAuction().getAuctionRecepDateE()));
            lotInfo.setDepositRecieptStart(convertToUTC(params.getAuction().getAuctionRecepDateB()));
            if (params.getAuction().getSgUnid().equals(SaleGuides.PRIVATIZATION)
                    || params.getAuction().getSgUnid().equals(SaleGuides.STATE_PROPERTY_MOSCOW)) {
                lotInfo.setDepositRecieptStop(convertToUTC(params.getAuction().getAuctionDepAccountDate()));
            } else {
                lotInfo.setDepositRecieptStop(convertToUTC(params.getAuction().getAuctionDepDateE()));
            }
            lotInfo.setShowOnSite(null);
            lotInfo.setPublicAccess(null);
            lotInfo.setOnlineAppReciept(false);
            lotInfo.setResultDate(convertToUTC(params.getAuction().getAuctionResultDate()));
            lotInfo.setLastUpdate(convertToUTC(now));
            lotInfo.setEvaluationCriterion("");
            lotInfo.setPaymentConditions(params.getLot().getLotPayTerms());
            lotInfo.setDeliveryPlace("");
            lotInfo.setDeliveryTimeReq("");
            lotInfo.setQuantity("");
            lotInfo.setApplicationDeposit(params.getLot().getLotAppProvideAmount());
            lotInfo.setRequiredDocuments(null);
            lotInfo.setInterEvaluationCriterion("");
            lotInfo.setInterPaymentConditions("");
            lotInfo.setInterDeliveryPlace("");
            lotInfo.setInterDeliveryTimeReq("");
            lotInfo.setInterQuantity("");
            lotInfo.setInterApplicationDeposit("");
            lotInfo.setInterRequiredDocuments(null);
            lotInfo.setFixedStep(true);
            lotInfo.setUserWinnerId(null);
            lotInfo.setShortDescription(params.getObject().getObjNote());
            lotInfo.setInterShortDescription(null);
            lotInfo.setAutoBidPeriod(params.getLot().getLotChangePricePeriod() == null
                    ? null
                    : BigInteger.valueOf(params.getLot().getLotChangePricePeriod()));
            lotInfo.setEmailSent(false);
            lotInfo.setWinnerOfferId(null);
            lotInfo.setWinnerPrice(null);
            lotInfo.setIsPublished(false);
            Date processAppDate = null;
            if (params.getAuction().getAuctionRecepDateE() != null) {
                processAppDate = convertToUTC(new Date(params.getAuction().getAuctionRecepDateE().getTime() + 60000));
            }
            lotInfo.setProcessAppDate(processAppDate);
            lotInfo.setIsAppsProcessed(false);
            lotInfo.setBiddingResult(null);
            lotInfo.setCancelReasonFk(null);
            lotInfo.setApplicationDocuments(null);
            lotInfo.setLotNumber(params.getLot().getLotNumber().intValue());
            lotInfo.setWinnerIdUpdated(false);
            lotInfo.setStartPriceBidPeriod(params.getLot().getLotStartPriceTime() == null
                    ? null
                    : BigInteger.valueOf(params.getLot().getLotStartPriceTime()));
            lotInfo.setAfterDirectionFlipBidPeriod(null);
            if (params.getAddress() != null && params.getAddress().getAddrInputMode().equals(AddrInputMode.FIAS.getCode())) {
                lotInfo.setCountryFk(LotOnlineCountry.RUSSIA.getCode());
                lotInfo.setFiasRegionGuid(params.getAddress().getAddrRegionId());
            } else if (params.getAddress().getOkcmUnid() != null && params.getAddress().getOkcmUnid().equals(OksmConstant.RUSSIA.getId())) {
                lotInfo.setCountryFk(LotOnlineCountry.RUSSIA.getCode());
                lotInfo.setFiasRegionGuid(params.getAddress().getAddrRegionId());
            }
            lotInfo.setEndProcessAppDate(convertToUTC(params.getAuction().getAuctionApplicatDateE()));
            if (params.getLot().getLotStepValue() == null || params.getLot().getLotStepValue().compareTo(BigDecimal.ZERO) == 0) {
                lotInfo.setStepPercent(params.getLot().getLotStepProcent());
            }
            lotInfo.setActualPublishDate(null);
            lotInfo.setCurrencyKeyFk(LotOnlineCurrency.RUB.getCode());
            lotInfo.setStartAmtDescription(null);
            lotInfo.setHasntStartAmt(false);
            lotInfo.setDidntTakePlaceReason(null);
            lotInfo.setGuid(UUID.randomUUID().toString());
            lotInfo.setRootId(null);
            lotInfo.setVersionDate(null);
            lotInfo.setVersion(null);
            lotInfo.setIsActive(true);
            if (params.getAuction().getDpUnid() != null && params.getAuction().getDpUnid().getDpIndSendOper()) {
                lotInfo.setDepositType(LotOnlineDepositType.OPERATOR.name());
            } else {
                lotInfo.setDepositType(LotOnlineDepositType.OWNER.name());
                // пока убираем передачу этого поля #32765
//                lotInfo.setDepositInfo(params.getAuction().getDpUnid() != null ? params.getAuction().getDpUnid().getDpAccountsDetails() : null);
            }
            lotInfo.setBiddingResultDoclistFk(null);
            lotInfo.setTerm(null);
            lotInfo.setOkato(null);
            lotInfo.setStartPriceType(null);
            lotInfo.setPriceAmtNds(null);
            lotInfo.setNdsRate(null);
            lotInfo.setContractDecision(null);
            lotInfo.setFrustratedReason(null);
            lotInfo.setAllowAltDeposit(false);
            lotInfo.setAltDepositInfo(null);
            lotInfo.setOfferStepType(LotOnlineOfferStepType.SINGLE.name());
            lotInfo.setPurchasePlanGuid(null);
            lotInfo.setPurchasePlanPositionGuid(null);
            lotInfo.setSubcontractorsRequirement(null);
            lotInfo.setProcessOffersDate(null);
            if (params.getSaleManager() != null) {
                lotInfo.setSalesManagerName(params.getSaleManager().getSubName());
                lotInfo.setSalesManagerEmail(params.getSaleManager().getSubEMail());
                lotInfo.setSalesManagerPhone(params.getSaleManager().getSubMobilePhone());
            }
            lotInfo.setTenderFk(auction);


            Optional<String> additionalPropertiesJson = additionalPropertiesService.getAdditionalPropertiesJsonByObjUnid(params.getObject().getObjUnid());
            if (additionalPropertiesJson.isPresent()) {
                lotInfo.setAddPropertyJson(additionalPropertiesJson.get());
            }

            emLo.persist(lotInfo);
            List<String> errors = new ArrayList<>();

            Result<Void, String> exportPictureResult = exportPictures(lotInfo, params.getPictures());
            if (exportPictureResult.isError()) {
                errors.add("Ошибка при выгрузке изображений: " + exportPictureResult.getError());
            }
            Result<Void, String> exportDocsResult = exportDocuments(emLo, auction.getUserId(), lotInfo, params.getDocuments());
            if (exportDocsResult.isError()) {
                errors.add("Ошибка при выгрузке документов: " + exportDocsResult.getError());
            }

            // Создаем адрес
            if (params.getAddress() != null) {
                Result<Void, String> addressResult = exportAddress(emLo, lotInfo, params.getAddress());
                if (addressResult.isError()) {
                    errors.add("Ошибка при выгрузке адреса: " + addressResult.getError());
                }
            }

            emLo.merge(lotInfo);
            emLo.getTransaction().commit();
            if (errors.isEmpty()) {
                return ExchangeResult.loaded(lotInfo.getId());
            } else {
                return ExchangeResult.loadedWithErrors(lotInfo.getId(), String.join("\n", errors));
            }
        } catch (Exception ex) {
            logger.error("Error when exportLot to lot-online", ex);
            rollbackTransaction(emLo);
            return ExchangeResult.notLoaded(ex.getMessage());
        } finally {
            closeEntityManager(emLo);
        }
    }

    private Result<Void, String> exportPictures(LotInfo lotInfo, List<LotOnlinePictureParams> pictures) {
        List<String> photos = new ArrayList<>();
        List<String> errors = new ArrayList<>();
        for (LotOnlinePictureParams pict : pictures) {
            String path = sdfPathPhoto.format(lotInfo.getCreationDate()) + "/" + lotInfo.getId() + "/";
            //Пережимаем картинки и отправляем на сервер по ssh
            Result<Void, String> result = lotOnlineFileService.writePhotoToServer(path + pict.getFileName() + "." + pict.getFileExt(), pict.getData());

            if (result.isSuccess()) {
                Path pathIn = Paths.get(pict.getEisFilePath());

                byte[] thumbnail = lotOnlineImageUtils.preparePhoto(pathIn.toFile(), LotOnlineThumbnailSize.THUMBNAIL.getSize());
                if (thumbnail != null) {
                    lotOnlineFileService.writePhotoToServer(path + "thumbnails/" + pict.getFileName() + ".png", thumbnail);
                }

                byte[] smallThumbnail = lotOnlineImageUtils.preparePhoto(pathIn.toFile(), LotOnlineThumbnailSize.SMALL_THUMBNAIL.getSize());
                if (smallThumbnail != null) {
                    lotOnlineFileService.writePhotoToServer(path + "thumbnails/small/" + pict.getFileName() + ".png", smallThumbnail);
                }
                photos.add("\"" + pict.getFileName() + "." + pict.getFileExt() + "\"");
                logger.info("send auction to lot-online: pictures added: " + path + pict.getFileName() + "." + pict.getFileExt());
            } else {
                errors.add(result.getError());
            }
        }
        if (!photos.isEmpty()) {
            lotInfo.setPhotoFiles("[" + String.join(",", photos) + "]");
        }
        if (errors.isEmpty()) {
            return Result.ok();
        } else {
            return Result.error(String.join("; ", errors));
        }
    }

    private Result<Void, String> exportDocuments(EntityManager emLo, Long userId, LotInfo lotInfo, List<LotOnlineDocParams> documents) {
        if (documents.isEmpty()) {
            return Result.ok();
        }
        Documentlist documentList = new Documentlist();
        documentList.setUserId(userId);
        emLo.persist(documentList);
        lotInfo.setDocumentListFk(documentList);
        Date now = new Date();

        List<String> errors = new ArrayList<>();
        for (LotOnlineDocParams doc : documents) {
            try {
                UserDocument userDocument = new UserDocument();
                userDocument.setFileName(doc.getFileName());
                userDocument.setUserId(userId);
                userDocument.setState(doc.getState());
                userDocument.setDocumentName(doc.getDocumentName());
                userDocument.setHashSum(doc.getHashSum());
                userDocument.setCreationDate(convertToUTC(now));
                userDocument.setIsPublic(Boolean.FALSE);
                userDocument.setSize(doc.getData().length);
                userDocument.setDocumentlistId(documentList.getId());
                emLo.persist(userDocument);

                String path = sdfPathYear.format(userDocument.getCreationDate()) + "/" + sdfPathDoc.format(userDocument.getCreationDate()) + "/";
                String name = doc.getFileName().substring(0, doc.getFileName().lastIndexOf(".")) + "_" + userDocument.getId();

                Result<Void, String> res = lotOnlineFileService.writeDocumentToServer(path + name + "." + doc.getFileExt(), doc.getData());
                if (res.isError()) {
                    errors.add(res.getError());
                } else {
                    logger.info("send auction to lot-online: lot document added: " + path + name + "." + doc.getFileExt());
                }
            } catch (Exception ex) {
                logger.error("Error create UserDocument", ex);
                errors.add("Error create UserDocument:" + ex.getMessage());
            }
        }
        if (errors.isEmpty()) {
            return Result.ok();
        } else {
            return Result.error(String.join("; ", errors));
        }
    }

    private Result<Void, String> exportAddress(EntityManager emLo, LotInfo lotInfo, Address eisAddress) {
        try {
            LoAddress loAddress = new LoAddress();
            loAddress.setAddress(eisAddress.getAddrAddress() != null
                    ? clearSpecialChars(eisAddress.getAddrAddress())
                    : null);
            loAddress.setIndex(eisAddress.getAddrIndex());
            loAddress.setCorp(eisAddress.getAddrCorp());
            loAddress.setBuild(eisAddress.getAddrBuild());
            loAddress.setHouse(eisAddress.getAddrHouse());
            Boolean indBuild = eisAddress.getAddrIndBuild() == null ? null : eisAddress.getAddrIndBuild().equals(BigInteger.ONE);
            loAddress.setIndBuild(indBuild);
            loAddress.setFlat(eisAddress.getAddrFlat());
            loAddress.setLatitude(eisAddress.getAddrLat());
            loAddress.setLongitude(eisAddress.getAddrLong());
            loAddress.setNote(eisAddress.getAddrNote() != null
                    ? clearSpecialChars(eisAddress.getAddrNote())
                    : null);
            String lastAddrObjId = null;
            FiasAddrobj region = getFiasAddrobj(eisAddress.getAddrRegionId());
            loAddress.setRegionId(region != null ? region.getRegioncode() : null);
            lastAddrObjId = eisAddress.getAddrRegionId() != null
                    ? eisAddress.getAddrRegionId()
                    : lastAddrObjId;
            FiasAddrobj auto = getFiasAddrobj(eisAddress.getAddrAutoId());
            loAddress.setAutoId(auto != null ? auto.getAutocode() : null);
            lastAddrObjId = eisAddress.getAddrAutoId() != null
                    ? eisAddress.getAddrAutoId()
                    : lastAddrObjId;
            FiasAddrobj area = getFiasAddrobj(eisAddress.getAddrAreaId());
            loAddress.setAreaId(area != null ? area.getAreacode() : null);
            lastAddrObjId = eisAddress.getAddrAreaId() != null
                    ? eisAddress.getAddrAreaId()
                    : lastAddrObjId;
            FiasAddrobj city = getFiasAddrobj(eisAddress.getAddrCityId());
            loAddress.setCityId(city != null ? city.getCitycode() : null);
            lastAddrObjId = eisAddress.getAddrCityId() != null
                    ? eisAddress.getAddrCityId()
                    : lastAddrObjId;
            FiasAddrobj ctar = getFiasAddrobj(eisAddress.getAddrCtarId());
            loAddress.setCtarId(ctar != null ? ctar.getCtarcode() : null);
            lastAddrObjId = eisAddress.getAddrCtarId() != null
                    ? eisAddress.getAddrCtarId()
                    : lastAddrObjId;
            FiasAddrobj place = getFiasAddrobj(eisAddress.getAddrPlaceId());
            loAddress.setPlaceId(place != null ? place.getPlacecode() : null);
            lastAddrObjId = eisAddress.getAddrPlaceId() != null
                    ? eisAddress.getAddrPlaceId()
                    : lastAddrObjId;
            FiasAddrobj street = getFiasAddrobj(eisAddress.getAddrStreetId());
            loAddress.setStreetId(street != null ? street.getStreetcode() : null);
            lastAddrObjId = eisAddress.getAddrStreetId() != null
                    ? eisAddress.getAddrStreetId()
                    : lastAddrObjId;
            loAddress.setFiasId(lastAddrObjId);
            loAddress.setLotInfo(lotInfo);
            emLo.persist(loAddress);
            return Result.ok();
        } catch (Exception ex) {
            logger.error("Error export Address", ex);
            return Result.error(ex.getMessage());
        }

    }

    private FiasAddrobj getFiasAddrobj(String aoguid) {
        if (StringUtils.isBlank(aoguid)) {
            return null;
        }
        return fiasGarApiClient.getAddrobjByAoguid(aoguid);
    }

    @Override
    public Tender getEtpAuction(Long etpAuctionUnid) {
        EntityManager emLo = null;
        try {
            emLo = HibernateUtil.getInstance().getEntityManagerLOLS3();
            return emLo.find(Tender.class, etpAuctionUnid);
        } catch (Exception ex) {
            logger.error("Error getEtpAuction by etpAuctionUnid = {}", etpAuctionUnid, ex);
        } finally {
            closeEntityManager(emLo);
        }
        return null;
    }


    private static Date convertToUTC(Date dateIn) throws ParseException {
        if (dateIn == null) {
            return null;
        }
        return sdfCur.parse(sdfUTC.format(dateIn));
    }

    private String clearSpecialChars(String str) {
        return str.replaceAll("[\r\n]", "")
                .replaceAll("(?<=[^\\\\])\"", "")
                .replaceAll("(?<=[^\\\\])\'", "");
    }
}
