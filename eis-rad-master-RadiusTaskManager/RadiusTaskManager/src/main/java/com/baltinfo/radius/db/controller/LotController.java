package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.dadata.dto.AddressDto;
import com.baltinfo.radius.dadata.services.DadataService;
import com.baltinfo.radius.db.constants.*;
import com.baltinfo.radius.db.dao.AuctionDao;
import com.baltinfo.radius.db.dao.LotDao;
import com.baltinfo.radius.db.dao.ObjRoleDao;
import com.baltinfo.radius.db.model.*;
import com.baltinfo.radius.links.constant.RecipientSite;
import com.baltinfo.radius.loadauction.model.AssetFull;
import com.baltinfo.radius.loadauction.service.JsonExportToAssetsService;
import com.baltinfo.radius.utils.DateUtils;
import com.baltinfo.radius.utils.HibernateUtil;
import com.baltinfo.radius.utils.RangeUtils;
import com.baltinfo.radius.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * </p>
 *
 * @author Lapenok Igor
 * @since 23.08.2018
 */
public class LotController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(LotController.class);
    private static final String RUBRIC_SEPARATOR = " - ";
    private static final String DEFAULT_MARKETING_REQUIREMENTS = "на усмотрение Менеджера";
    private static final String DEFAULT_REWARDS_RADIUS = "5%+КЭ";
    private static final BigDecimal DEFAULT_STEP_DECREASE_PERCENT = new BigDecimal("10.0");


    private final LotDao lotDao;
    private final AuctionDao auctionDao;
    private final ObjectController objectController;
    private final DadataService dadataService;
    private final JsonExportToAssetsService jsonExportToAssetsService;
    private final DealController dealController;
    private final PropertyValueController propertyValueController;
    private final ObjRoleDao objRoleDao;

    public LotController(LotDao lotDao,
                         AuctionDao auctionDao,
                         ObjectController objectController,
                         DadataService dadataService,
                         JsonExportToAssetsService jsonExportToAssetsService, DealController dealController, PropertyValueController propertyValueController, ObjRoleDao objRoleDao) {
        this.lotDao = Objects.requireNonNull(lotDao, "Can't get lot DAO");
        this.auctionDao = Objects.requireNonNull(auctionDao, "Can't get auction DAO");
        this.objectController = Objects.requireNonNull(objectController, "Can't get objectController");
        this.dadataService = Objects.requireNonNull(dadataService, "Can't get dadata.ru ftp");
        this.jsonExportToAssetsService = Objects.requireNonNull(jsonExportToAssetsService, "Can't get json-asset ftp");
        this.dealController = Objects.requireNonNull(dealController, "Can't get dealController");
        this.propertyValueController = Objects.requireNonNull(propertyValueController, "Can't get propertyValueController");
        this.objRoleDao = Objects.requireNonNull(objRoleDao, "Can't get objRoleDao");;
    }

    public Result<Lot, String> createLot(Auction auction, LoadLot loadLot, List<LoadRs> loadRsList, List<LoadFile> loadFileList) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            if (loadLot == null || loadLot.getLaUnid() == null) {
                return Result.error("Не передан лот");
            }
            em.getTransaction().begin();
            LoadAuction loadAuction = loadLot.getLaUnid();

            Boolean isCurrent = checkCurrent(em, auction, loadLot);
            Long scUnid = getSaleCategory(em, loadLot);
            TypeObject typeObject = null;
            ClAsv clAsv = null;
            if (loadLot.getLlClAsv() != null && !loadLot.getLlClAsv().isEmpty()) {
                clAsv = lotDao.getClAsvByName(em, loadLot.getLlClAsv());
            }
            if (scUnid != null) {
                typeObject = lotDao.getTypeObjectByScUnid(em, scUnid);
            } else if (clAsv != null) {
                typeObject = clAsv.getToUnid();
            }

            if (typeObject == null) {
                return Result.error("По лоту " + loadLot.getLlLotNum() + " не удалось определить вид объекта\n");
            }
            Result<ObjectJPA, String> createObjectResult = createObjectJPA(em, auction, loadLot, loadAuction,
                    typeObject.getToUnid());
            ObjectJPA objectJPA;
            if (createObjectResult.isSuccess()) {
                objectJPA = createObjectResult.getResult();
            } else {
                rollbackTransaction(em);
                return Result.error(createObjectResult.getError());
            }

            createObjectSaleCategory(em, objectJPA, scUnid);

            if (clAsv != null && clAsv.getCaCode() != null && AsvCode.isNfa(clAsv.getCaCode())) {
                fillPropertyValues(em, loadLot, scUnid, objectJPA.getObjUnid());
            }

            createDocFiles(em, loadFileList, objectJPA);
            Lot lot = createLot(em, auction, loadLot, objectJPA, isCurrent);
            lotDao.createObjCost(em, lot, objectJPA, TypeCostConstant.START.getId(), loadLot.getLlStartCost(), isCurrent);
            if (auction.getTypeAuctionUnid().getTypeAuctionCode().equals(TypeAuctionConstant.HOLLAND.getRadiusTypeAuctionCode())) {
                lotDao.createObjCost(em, lot, objectJPA, TypeCostConstant.MIN.getId(), loadLot.getLlMinPrice(), isCurrent);
            }
            Result<BigDecimal, String> createRsResult = createRs(em, lot, auction, loadLot, filterLoadRsByLotNumber(loadLot.getLlLotNum(), loadRsList));
            if (createRsResult.isSuccess()) {
                BigDecimal minCostValue = createRsResult.getResult();
                if (minCostValue != null) {
                    lotDao.createObjCost(em, lot, objectJPA, TypeCostConstant.MIN.getId(), minCostValue, false);
                }
            } else {
                rollbackTransaction(em);
                return Result.error(createRsResult.getError());
            }

            em.getTransaction().commit();
            return Result.ok(lot);
        } catch (Exception e) {
            logger.error("Can't create lot", e);
            rollbackTransaction(em);
            return Result.error("Не удалось сохранить информацию о лоте в БД.");
        } finally {
            closeEntityManager(em);
        }
    }

    private boolean checkCurrent(EntityManager em, Auction auction, LoadLot loadLot) {
        if (auction.getBaUnid() != null && auction.getAuctionStageNum() != null && loadLot.getLlLotNum() != null) {
            Optional<Integer> minStage =
                    lotDao.getMinStageForBlock(em, auction.getBaUnid(), Long.valueOf(loadLot.getLlLotNum()));
            logger.info("auction.getAuctionStageNum() = {}, loadLot.getLlUnid() = {}", auction.getAuctionStageNum(), loadLot.getLlUnid());
            if (!minStage.isPresent() || minStage.get().compareTo(auction.getAuctionStageNum()) > 0) {
                logger.info("auction.getAuctionStageNum() = {}, loadLot.getLlUnid() = {} = TRUE", auction.getAuctionStageNum(), loadLot.getLlUnid());
                return true;
            }
            return false;
        }
        return true;
    }

    /**
     * Возвращает {@link ObjectJPA}
     * Для блочных торгов сначала ищем объект по идентификатору блочных торгов и номеру, если не находим, то создаем
     *
     * @param em          {@link EntityManager}
     * @param auction     торги {@link Auction}
     * @param loadLot     загрузка лота {@link LoadLot}
     * @param loadAuction загрузка торгов {@link LoadAuction}
     * @return найденный или созданный объект {@link ObjectJPA}
     */
    private Result<ObjectJPA, String> createObjectJPA(EntityManager em, Auction auction, LoadLot loadLot,
                                                      LoadAuction loadAuction, Long toUnid) {
        if (auction.getBaUnid() != null && loadLot.getLlLotNum() != null) {
            Optional<ObjectJPA> objectJPAOptional =
                    lotDao.getObjectByBaUnidAndLotNumber(em, auction.getBaUnid(), Long.valueOf(loadLot.getLlLotNum()));
            if (objectJPAOptional.isPresent()) {
                return Result.ok(objectJPAOptional.get());
            }
        }
        ObjectJPA objectJPA = lotDao.createObject(em,
                loadAuction,
                loadLot,
                toUnid);
        lotDao.createObjRole(em, objectJPA, loadAuction.getTpaUnid());
        AddressDto addressDto = dadataService.createAddressDtoByAddress(loadLot.getLlAddress());
        String regionName = loadLot.getLlRegion();
        if (addressDto.getRegionFiasId() != null) {
            lotDao.createRussianAddress(em, objectJPA, addressDto, addressDto.getRegionFiasId(), regionName);
        } else {
            String regionId = makeRegionId(em, regionName);
            lotDao.createRussianAddress(em, objectJPA, addressDto, regionId, regionName);
        }
        objectJPA.setObjAddrFeed(addressDto.getSource());
        createObjDescrForObjSale(em, objectJPA.getObjUnid(), getMapWithTypeObjDescrsAndOdText(loadLot));
        createObjMarketingEvents(em, objectJPA.getObjUnid());
        if (loadAuction.getDealUnid() != null && StringUtils.isNotEmpty(loadLot.getLlTzNum()) && loadLot.getLlTzDate() != null) {
            dealController.createDeal(em, loadAuction.getTpaUnid(), objectJPA.getObjUnid(), loadLot.getLlTzNum(), loadLot.getLlTzDate(), loadAuction.getDealUnid());
        }
        if (loadLot.getLlAssets() != null) {
            Result<String, String> result = jsonExportToAssetsService.buildHtmlFromAsset(loadLot.getLlAssets());
            if (result.isSuccess()) {
                createObjDescrForAsset(em, result.getResult(), objectJPA.getObjUnid());
            } else {
                logger.warn("Error when trying to build HTML from Assets: " + result.getError());
            }
        }
        return Result.ok(objectJPA);
    }

    private String parseAmountOfDebt(String description) {
        String regex = "^.*\\(([\\s\\d,.]+)руб.\\).*$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(description.replace("\u00A0", " "));

        if (matcher.matches()) {
            try {
                String amount = matcher.group(1);
                amount = amount.replaceAll(" ", "");
                amount = amount.replaceAll(",", ".");
                return amount;
            } catch (Exception ex) {
                logger.error("Error extract Amountofdebt from description = {}", description, ex);
            }
        }
        return null;
    }

    private HashMap<Long, String> getMapWithTypeObjDescrsAndOdText(LoadLot loadLot) {
        HashMap<Long, String> typeObjDescrMap = new HashMap<>();
        typeObjDescrMap.put(TypeObjDescrs.CONTACTS_FOR_INSPECTION, loadLot.getLlContacts()); // Контакты для осмотра имущества
        typeObjDescrMap.put(TypeObjDescrs.MARKETING_REQUIREMENTS,
                loadLot.getLlMarketingRequirements() == null || loadLot.getLlMarketingRequirements().isEmpty()
                        ? DEFAULT_MARKETING_REQUIREMENTS
                        : loadLot.getLlMarketingRequirements()); // Требования к маркетинговым мероприятиям
        typeObjDescrMap.put(TypeObjDescrs.RAD_REWARDS,
                loadLot.getLlRewardRadius() == null || loadLot.getLlRewardRadius().isEmpty()
                        ? DEFAULT_REWARDS_RADIUS
                        : loadLot.getLlRewardRadius()); // Вознаграждение РАД
        typeObjDescrMap.put(TypeObjDescrs.SITE_ADVERTISEMENT_TEXT, loadLot.getLlLotName());
        return typeObjDescrMap;
    }

    private void createObjectSaleCategory(EntityManager em, ObjectJPA objectJPA, Long llSaleCategory) {
        if (llSaleCategory == null) {
            return;
        }
        SaleCategory saleCategory = objectController.getSaleCategory(em, llSaleCategory);
        if (saleCategory == null) {
            return;
        }
        List<ObjSaleCategory> categories = objectController.getObjSaleCategories(em, objectJPA.getObjUnid());
        if (categories.isEmpty()) {
            objectController.createObjectSaleCategory(em, objectJPA, saleCategory, true);
        } else {
            boolean objCategoryExist = categories.stream()
                    .anyMatch(osc -> osc.getScUnid().getScUnid() == saleCategory.getScUnid());
            if (!objCategoryExist) {
                objectController.createObjectSaleCategory(em, objectJPA, saleCategory, false);
            }
        }
    }

    private void fillPropertyValues(EntityManager em, LoadLot loadLot, Long scUnid, Long objUnid) {
        List<PropertyValue> values = propertyValueController.getPropertyValuesByObject(objUnid);
        if (!values.isEmpty()) {
            return;
        }
        if (scUnid != null) {
            values = createPropertyValuesBySaleCategory(em, scUnid, objUnid);
        } else {
            values = createPropertyValuesBySaleCategory(em, SaleCategoryConstant.TEMP_NFA.getCode(), objUnid);
        }
        if (loadLot.getLlAssets() != null && !loadLot.getLlAssets().isEmpty()) {
            Result<List<AssetFull>, String> assetFullResult = jsonExportToAssetsService.formJsonToFullAsset(loadLot.getLlAssets());
            if (assetFullResult.isError()) {
                logger.warn("Error when trying to build HTML from Assets: " + assetFullResult.getError());
            }
            List<AssetFull> assets = assetFullResult.getResult();
            AssetFull borrowerInn = assets.stream()
                    .filter(asset -> asset.getBorrowerInn() != null && !asset.getBorrowerInn().trim().isEmpty())
                    .findFirst()
                    .orElse(null);
            if (borrowerInn != null) {
                if (values != null) {
                    PropertyValue propertyValue = values.stream()
                            .filter(pv -> pv.getOpUnid().getAopUnid().getAopCode().equals(AllowObjPropCode.INN.getCode()))
                            .findFirst()
                            .orElse(null);
                    if (propertyValue != null) {
                        propertyValue.setPvValue(borrowerInn.getBorrowerInn());
                        em.merge(propertyValue);
                    }
                }
            }
            AssetFull overdue = assets.stream()
                    .filter(asset -> asset.getOverdue() != null)
                    .findFirst()
                    .orElse(null);
            if (overdue != null) {
                if (values != null) {
                    PropertyValue propertyValue = values.stream()
                            .filter(pv -> pv.getOpUnid().getAopUnid().getAopCode().equals(AllowObjPropCode.DAYS_LATE_PAYMENT.getCode()))
                            .findFirst()
                            .orElse(null);
                    if (propertyValue != null) {
                        propertyValue.setPvValue(overdue.getOverdue().toString());
                        em.merge(propertyValue);
                    }
                }
            }
            AssetFull availability = assets.stream()
                    .filter(asset -> asset.getAvailability() != null && !asset.getAvailability().trim().isEmpty())
                    .findFirst()
                    .orElse(null);
            if (availability != null) {
                String availabilityValue = availability.getAvailability().equalsIgnoreCase("да")
                        ? "Долг обеспечен"
                        : "Без опеспечения";
                if (values != null) {
                    PropertyValue propertyValue = values.stream()
                            .filter(pv -> pv.getOpUnid().getAopUnid().getAopCode().equals(AllowObjPropCode.AVAILABILITY_OF_DEBT_SECURITY.getCode()))
                            .findFirst()
                            .orElse(null);
                    if (propertyValue != null) {
                        AllowPropVal apv = propertyValueController.getAllowPropertyValueByValue(propertyValue.getOpUnid().getAopUnid().getAopUnid(),
                                availabilityValue);
                        if (apv != null) {
                            propertyValue.setApvUnid(apv);
                        }
                        propertyValue.setPvValue(availabilityValue);
                        em.merge(propertyValue);
                    }
                }
            }
            AssetFull trial = assets.stream()
                    .filter(asset -> asset.getTrial() != null && !asset.getTrial().trim().isEmpty())
                    .findFirst()
                    .orElse(null);
            String trialValue;
            if (trial != null) {
                trialValue = trial.getTrial().equalsIgnoreCase("да")
                        ? "Есть судебный акт"
                        : "Судебный акт отсутствует";
            } else {
                trialValue = "Информация отсутствует";
            }
            if (values != null) {
                PropertyValue propertyValue = values.stream()
                        .filter(pv -> pv.getOpUnid().getAopUnid().getAopCode().equals(AllowObjPropCode.COURTDECISION.getCode()))
                        .findFirst()
                        .orElse(null);
                if (propertyValue != null) {
                    AllowPropVal apv = propertyValueController.getAllowPropertyValueByValue(propertyValue.getOpUnid().getAopUnid().getAopUnid(),
                            trialValue);
                    if (apv != null) {
                        propertyValue.setApvUnid(apv);
                    }
                    propertyValue.setPvValue(trialValue);
                    em.merge(propertyValue);
                }
            }
        }

        String amountofdebt = parseAmountOfDebt(loadLot.getLlLotName());
        if (amountofdebt != null) {
            if (values != null) {
                PropertyValue propertyValue = values.stream()
                        .filter(pv -> pv.getOpUnid().getAopUnid().getAopCode().equals(AllowObjPropCode.AMOUNT_OF_DEBT.getCode()))
                        .findFirst()
                        .orElse(null);
                if (propertyValue != null) {
                    propertyValue.setPvValue(amountofdebt);
                    em.merge(propertyValue);
                }
            }
        }
    }

    public List<PropertyValue> createPropertyValuesBySaleCategory(EntityManager em, Long scUnid, Long objUnid) {
        Date now = new Date();
        List<PropertyValue> propertyValues = new ArrayList<>();
        List<PropertyGroup> propertyGroups = propertyValueController.getPropertyGroupsForSaleCategory(scUnid);
        for (PropertyGroup group : propertyGroups) {
            List<ObjProperty> objProperties = propertyValueController.getObjPropertiesByPgUnid(group.getPgUnid());
            for (ObjProperty objProperty : objProperties) {
                PropertyValue value = new PropertyValue();
                value.setOpUnid(objProperty);
                value.setObjUnid(objUnid);
                if (org.apache.commons.lang3.StringUtils.isNotEmpty(objProperty.getOpDefVal())) {
                    value.setPvValue(objProperty.getOpDefVal());
                }
                value.setIndActual(1);
                value.setDateB(now);
                value.setPersCodeB(1L);
                em.persist(value);
                propertyValues.add(value);
            }
        }
        return propertyValues;
    }

    private Long getSaleCategory(EntityManager em, LoadLot loadLot) {
        if (loadLot.getScUnid() == null) {
            return getScUnidByClAsv(em, loadLot);
        } else {
            return loadLot.getScUnid();
        }
    }

    private Long getScUnidByClAsv(EntityManager em, LoadLot loadLot) {
        ClAsv targetClAsv = lotDao.getClAsvByName(em, loadLot.getLlClAsv());
        if (targetClAsv != null && targetClAsv.getScUnid() != null) {
            return targetClAsv.getScUnid().getScUnid();
        } else {
            return null;
        }
    }

    public void createDocFilesByBaUnid(List<LoadFile> loadFileList, Long baUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            em.getTransaction().begin();
            Integer documOrderNum = 1;
            for (LoadFile loadFile : loadFileList) {
                DocFile docFile = new DocFile();
                docFile.setDfNameDb(loadFile.getLfFileName());
                docFile.setDfFilePath(loadFile.getLfEisPath());
                docFile.setDfName(loadFile.getLfFileNameAsv());
                docFile.setDfSize(loadFile.getLfFileSize());
                docFile.setDfExt(loadFile.getLfFileExt());
                lotDao.createDocFile(em, docFile);
                createDocumentByBaUnid(em, docFile, baUnid, documOrderNum++);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            logger.error("Can't create docFile with baUnid = {}", baUnid, e);
            rollbackTransaction(em);
        } finally {
            closeEntityManager(em);
        }
    }

    private void createDocFiles(EntityManager em, List<LoadFile> loadFileList, ObjectJPA objectJPA) {
        Integer pictureOrderNum = 1;
        Integer documOrderNum = 1;
        for (LoadFile loadFile : loadFileList) {
            DocFile docFile = new DocFile();
            docFile.setDfNameDb(loadFile.getLfFileName());
            docFile.setDfFilePath(loadFile.getLfEisPath());
            docFile.setDfName(loadFile.getLfFileNameAsv());
            docFile.setDfSize(loadFile.getLfFileSize());
            docFile.setDfExt(loadFile.getLfFileExt());
            lotDao.createDocFile(em, docFile);
            if (loadFile.getLfType() == 1) {
                createDocument(em, docFile, objectJPA, documOrderNum++);
            } else if (loadFile.getLfType() == 2) {
                createPicture(em, docFile, objectJPA, pictureOrderNum++);
            }
        }
    }

    private Document createDocumentByBaUnid(EntityManager em, DocFile docFile, Long baUnid, Integer orderNum) {
        Document document = new Document();
        document.setDfUnid(docFile);
        document.setTdUnid(em.find(TypeDoc.class, TypeDocConstant.NO_INFO.getUnid()));
        document.setBaUnid(baUnid);
        document.setDocumName(docFile.getDfName());
        document.setDocumIndExport(false);
        document.setDocumIndExportEtp(false);
        document.setDocumIndExportFeed(false);
        document.setDocumOrderNum(orderNum);
        return lotDao.createDocument(em, document);
    }

    private Document createDocument(EntityManager em, DocFile docFile, ObjectJPA objectJPA, Integer orderNum) {
        Document document = new Document();
        document.setDfUnid(docFile);
        document.setTdUnid(em.find(TypeDoc.class, TypeDocConstant.NO_INFO.getUnid()));
        document.setObjUnid(objectJPA.getObjUnid());
        document.setDocumName(docFile.getDfName());
        document.setDocumIndExport(true);
        document.setDocumIndExportEtp(true);
        document.setDocumIndExportFeed(true);
        document.setDocumOrderNum(orderNum);
        return lotDao.createDocument(em, document);
    }

    private Picture createPicture(EntityManager em, DocFile docFile, ObjectJPA objectJPA, Integer orderNum) {
        Picture picture = new Picture();
        picture.setDfUnid(docFile);
        picture.setTpUnid(TypePictureConstant.NOT_SPECIFIED.getUnid());
        picture.setObjUnid(objectJPA.getObjUnid());
        picture.setPictName(docFile.getDfName());
        picture.setPictIndExport(true);
        picture.setPictIndExportFeed(true);
        picture.setPictIndExportEtp(true);
        picture.setPictNum(orderNum);
        return lotDao.createPicture(em, picture);
    }

    private Lot createLot(EntityManager em, Auction auction, LoadLot loadLot, ObjectJPA objectJPA, boolean isCurrent) {
        Lot lot = new Lot();
        lot.setObjUnid(objectJPA);
        lot.setAuctionUnid(auction.getAuctionUnid());
        lot.setLotNumber(Long.parseLong(loadLot.getLlLotNum()));
        lot.setLotPublHeader(loadLot.getLlLotSname());
        lot.setLotAuctionTheme(loadLot.getLlLotName());
        boolean isPublicAuction = auction.getTypeAuctionUnid().getTypeAuctionCode().equals(TypeAuctionConstant.PUBLIC.getRadiusTypeAuctionCode());
        if (isPublicAuction) {
            lot.setLotStepDecreaseValue(loadLot.getLlStepDecreaseValue());
        } else {
            lot.setLotStepValue(loadLot.getLlStepValue());
            if (auction.getTypeAuctionUnid().getTypeAuctionCode().equals(TypeAuctionConstant.HOLLAND.getRadiusTypeAuctionCode())) {
                lot.setLotStepDecreaseValue(loadLot.getLlStepDecreaseValue());
                lot.setLotChangePricePeriod(loadLot.getLlChangePricePeriodMin().longValue());
                lot.setLotStartPriceTime(loadLot.getLlStartPriceTime().longValue());
            }
        }
        lot.setLotSumDeposit(calcDepositSum(DepositTypeConstant.getByName(loadLot.getLlDepositAlg())
                        .orElse(null),
                loadLot.getLlStartCost(),
                null,
                loadLot.getLlDepositSum()));
        if (!DepositTypeConstant.FIXED_VALUE.equals(DepositTypeConstant.getByName(loadLot.getLlDepositAlg()).orElse(null))) {
            lot.setLotDepositPer(loadLot.getLlDepositSum());
        }
        if (loadLot.getLlStepDecreaseValue() != null) {
            lot.setLotStepDecreaseProc(loadLot.getLlStepDecreaseValue().compareTo(BigDecimal.ZERO) == 0 ? DEFAULT_STEP_DECREASE_PERCENT : null);
        }
        lot.setLotIndCond(0);
        lot.setLotIndCurrent(isCurrent ? 1 : 0);
        lot.setLotStatus(0);
        lot.setTsUnid(TypeStateConstant.NOT_TAKE_PLACE.getId());
        lot.setEntityUnid(EntityConstant.LOT.getId());
        lot.setLotDateBPlan(auction.getAuctionDateB());
        lot.setLotDateEPlan(auction.getAuctionDateE());
        lot.setLotReviewDebitorOrder(loadLot.getLlReviewDebitorOrder());
        lot.setCaUnid(loadLot.getLlClAsv() == null ? null : convertClAsv(em, loadLot.getLlClAsv()));
        lot.setLotIndRightEnsure(loadLot.getLlIndRightEnsure());
        lot.setLotAsvLink(loadLot.getLlAsvLink());
        lot.setLotAsvId(loadLot.getLlAsvId());
        if (isCurrent && auction.getBaUnid() != null) {
            lotDao.clearIndCurrentLot(em, auction.getBaUnid(), lot.getLotNumber());
        }
        lot.setCurUnid(EisCurrencyUnid.RUB.getUnid());
        lot.setLotAsvStageId(loadLot.getLlAsvStageId());
        if (SaleGuides.PRIVATE_PROPERTY.equals(auction.getSgUnid())) {
            lot.setLotDealSingleAllow(true);
            lot.setLotDealSecondOfferAllow(true);
        }
        return lotDao.createLot(em, lot);
    }

    public ClAsv getClAsv(Long caCode) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return lotDao.getClAsvByCaCode(em, caCode);
        } catch (Exception e) {
            logger.error("Can't get clAsv", e);
            return null;
        } finally {
            closeEntityManager(em);
        }
    }


    private Result<BigDecimal, String> createRs(EntityManager em, Lot lot, Auction auction, LoadLot loadLot, List<LoadRs> loadRsList) {
        try {
            Optional<ChangePriceAlg> changePriceAlgOptional = ChangePriceAlg.getByCode(loadLot.getLlChangePriceAlg());
            BigDecimal lastPrice = null;
            if (changePriceAlgOptional.isPresent()) {
                ChangePriceAlg changePriceAlg = changePriceAlgOptional.get();
                Date dateB = null;
                Date dateE = null;
                BigDecimal price = null;
                BigDecimal prePrice = null;
                Integer periodNumber = 1;
                if ((changePriceAlg.equals(ChangePriceAlg.ALG5) && auction.getAuctionRecepDateE() != null)
                        || (auction.getAuctionRecepDateB() != null
                        && auction.getAuctionRecepDateE() != null
                        && loadLot.getLlStartCost() != null)) {

                    if (auction.getAuctionRecepDateB() != null
                            && auction.getAuctionRecepDateE() != null
                            && loadLot.getLlStartCost() != null) {

                        dateB = auction.getAuctionRecepDateB();
                        price = loadLot.getLlStartCost();
                        prePrice = loadLot.getLlStartCost();
                    }
                    switch (changePriceAlg) {
                        case ALG1:
                        case ALG2:
                        case ALG3:
                        case ALG4:
                            if (loadLot.getLlChangePricePeriod() != null && loadLot.getLlChangePriceValue() != null) {
                                while (DateUtils.truncate(dateB).compareTo(DateUtils.truncate(auction.getAuctionRecepDateE())) < 0
                                        && (dateE == null || DateUtils.truncate(dateE).compareTo(DateUtils.truncate(auction.getAuctionRecepDateE())) <= 0)
                                        && price.compareTo(BigDecimal.ZERO) > 0) {

                                    dateE = addDay(dateB, loadLot.getLlChangePricePeriod());

                                    Calendar cal = Calendar.getInstance();
                                    cal.setTime(DateUtils.truncate(dateE).compareTo(DateUtils.truncate(auction.getAuctionRecepDateE())) <= 0
                                            ? dateE
                                            : auction.getAuctionRecepDateE());
                                    cal.add(Calendar.DAY_OF_YEAR, loadLot.getLlApplPeriod() == null ? 0 : (-1 * loadLot.getLlApplPeriod()));
                                    if (cal.getTime().compareTo(dateB) < 0) {
                                        cal.setTime(auction.getAuctionRecepDateE());
                                    }
                                    if (dateB.compareTo(cal.getTime()) >= 0) {
                                        break;
                                    }

                                    BigDecimal depositSum =
                                            calcDepositSum(DepositTypeConstant.getByName(loadLot.getLlDepositAlg())
                                                            .orElse(null),
                                                    loadLot.getLlStartCost(),
                                                    price,
                                                    loadLot.getLlDepositSum());

                                    lotDao.createRs(em, lot, dateB, cal.getTime(), price, cal.getTime(), depositSum, prePrice.subtract(price));
                                    lastPrice = price;
                                    prePrice = price;
                                    dateB = dateE;
                                    periodNumber++;
                                    if (loadLot.getLlPeriodBeginningDecline() == null || periodNumber >= loadLot.getLlPeriodBeginningDecline()) {
                                        price = calcPrice(changePriceAlg, loadLot.getLlStartCost(), loadLot.getLlChangePriceValue(), price, prePrice);
                                    }
                                }
                            }
                            break;
                        case ALG5:
                            for (LoadRs loadRs : loadRsList) {
                                price = calculatePrice(CalcPriceConstant.getByValue(loadRs.getLrsCalcPriceAlg()),
                                        loadLot.getLlStartCost(),
                                        prePrice == null ? loadLot.getLlStartCost() : prePrice,
                                        loadRs.getLrsSumm());
                                if (prePrice == null) {
                                    prePrice = price;
                                }
                                BigDecimal depositSum = calcDepositSum(DepositTypeConstant.getByName(loadLot.getLlDepositAlg())
                                                .orElse(null),
                                        loadLot.getLlStartCost(),
                                        price, loadRs.getLrsDepositSumm()
                                );
                                lotDao.createRs(em,
                                        lot,
                                        loadRs.getLrsChangePriceDate(),
                                        loadRs.getLrsApplDate(),
                                        price,
                                        loadRs.getLrsApplDate(),
                                        depositSum,
                                        prePrice.subtract(price));
                                prePrice = price;
                                lastPrice = price;
                            }
                            break;
                    }
                }
            }
            return Result.ok(lastPrice);
        } catch (Exception e) {
            logger.error("Can't create reduction schedule", e);
            return Result.error("Ошибка при создании графика снижения цены");
        }
    }

    private BigDecimal calcPrice(ChangePriceAlg changePriceAlg, BigDecimal startCost, BigDecimal changePriceValue, BigDecimal price, BigDecimal prePrice) {
        switch (changePriceAlg) {
            case ALG1:
                return price.subtract(startCost.multiply(changePriceValue.divide(new BigDecimal("100")))).setScale(2, BigDecimal.ROUND_HALF_UP);
            case ALG2:
                return price.subtract(prePrice.multiply(changePriceValue.divide(new BigDecimal("100")))).setScale(2, BigDecimal.ROUND_HALF_UP);
            case ALG3:
                return startCost.subtract(changePriceValue).setScale(2, BigDecimal.ROUND_HALF_UP);
            case ALG4:
                return price.subtract(changePriceValue).setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        return price;
    }

    public BigDecimal calculatePrice(CalcPriceConstant calcPriceType, BigDecimal startPrice, BigDecimal prevPrice, BigDecimal price) {
        switch (calcPriceType) {
            case START_PRICE_PERCENT:
                if (startPrice == null || price == null) {
                    return null;
                }
                return startPrice.multiply(price).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
            //BigDecimal startPriceDelta = startPrice.multiply(price).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
            //return startPrice.subtract(startPriceDelta);
            case PREV_PRICE_PERCENT:
                if (prevPrice == null || price == null) {
                    return null;
                }
                return prevPrice.multiply(price).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
//                BigDecimal prevPriceDelta = prevPrice.multiply(price).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
//                return prevPrice.subtract(prevPriceDelta);
            default:
                return price;
        }
    }

    public BigDecimal calcDepositSum(DepositTypeConstant depositType, BigDecimal startCost, BigDecimal price, BigDecimal depositSum) {
        switch (depositType) {
            case START_PRICE_PROC:
                if (startCost == null || depositSum == null) {
                    return null;
                }
                return startCost.multiply(depositSum).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
            case PERIOD_PRICE_PROC:
                if (price == null || depositSum == null) {
                    return null;
                }
                return price.multiply(depositSum).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
            case FIXED_VALUE:
                return depositSum;
        }
        return null;
    }

    public String makeRegionId(EntityManager em, String llRegion) {
        String regionId = convertRegionFromASV(em, llRegion);
        return (regionId != null ? regionId : convertRegion(em, llRegion));
    }

    private String convertRegion(EntityManager em, String llRegion) {
        try {
            return lotDao.getRegionIdByName(em, llRegion);
        } catch (Exception e) {
            logger.warn("Can't get region in FIAS by name = {}", llRegion, e);
            return null;
        }
    }

    private String convertRegionFromASV(EntityManager em, String llRegion) {
        try {
            return lotDao.getRegionIdByNameInASV(em, llRegion);
        } catch (Exception ex) {
            logger.warn("Can't get region in ASV by name = {}", llRegion, ex);
            return null;
        }
    }

    private Long convertClAsv(EntityManager em, String llClAsv) {
        try {
            return lotDao.getClAsvByName(em, llClAsv).getCaUnid();
        } catch (Exception e) {
            logger.warn("Can't get ClAsv record by name = " + llClAsv, e);
            return null;
        }
    }


    private Date addDay(Date date, int numday) {
        if (date == null) return null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, numday);
        return calendar.getTime();
    }

    private Date addDayTrunc(Date date, int numday) {
        if (date == null) return null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR)
                , calendar.get(Calendar.MONTH)
                , (int) (calendar.get(Calendar.DAY_OF_MONTH) + numday)
                , 0
                , 0
                , 0);
        return calendar.getTime();
    }

    private String parseRubric(String rubric) {
        String[] rubrics = rubric.split(RUBRIC_SEPARATOR);
        if (rubrics.length > 1) {
            return rubrics[1];
        }
        return "";
    }

    public ClAsv getClAsvByUnid(Long caUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return lotDao.getClAsvByUnid(em, caUnid);
        } catch (Exception ex) {
            logger.error("Error getClAsvByUnid by caUnid = {}", caUnid, ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    private List<LoadRs> filterLoadRsByLotNumber(String lotNumber, List<LoadRs> oldList) {
        List<LoadRs> filteredList = new ArrayList<>();
        for (LoadRs loadRs : oldList) {
            if (loadRs.getLrsLots() == null || loadRs.getLrsLots().isEmpty()
                    || RangeUtils.consists(",", loadRs.getLrsLots(), lotNumber)) {
                filteredList.add(loadRs);
            }
        }
        return filteredList;
    }

    public Result<Void, String> updateObjectStatus(Long objUnid, Long tsUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            em.getTransaction().begin();
            ObjectJPA obj = em.find(ObjectJPA.class, objUnid);
            obj.setTsUnid(tsUnid);
            lotDao.updateObject(em, obj);
            em.getTransaction().commit();
            return Result.ok();
        } catch (Exception ex) {
            logger.error("Error updateObjectStatus by objUnid = {}, tsUnid = {}", objUnid, tsUnid, ex);
            rollbackTransaction(em);
            return Result.error(ex.getMessage());
        } finally {
            closeEntityManager(em);
        }
    }

    public Result<Void, String> updateAuctionAfterSendToEtp(Long auctionUnid, Long auctinEtpId, Long marketplaceUnid, Long tsUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            em.getTransaction().begin();
            Auction auction = em.find(Auction.class, auctionUnid);
            auction.setTsUnid(tsUnid);
            auction.setMpUnid(marketplaceUnid);
            auction.setAuctionEtpId(auctinEtpId);
            auctionDao.updateAuction(em, auction);
            em.getTransaction().commit();
            return Result.ok();
        } catch (Exception ex) {
            logger.error("Error updateAuctionStatus by auctionUnid {}, tsUnid = {}", auctionUnid, tsUnid, ex);
            rollbackTransaction(em);
            return Result.error(ex.getMessage());
        } finally {
            closeEntityManager(em);
        }
    }

    private void createObjMarketingEvents(EntityManager em, Long objUnid) {
        List<MarketingEvents> meList = em.createNamedQuery("MarketingEvents.findAll").getResultList();
        for (MarketingEvents me : meList) {
            lotDao.createObjMarketingEvent(em, objUnid, me.getMevUnid());
        }
    }

    private void createObjDescrForObjSale(EntityManager em, Long objUnid, Map<Long, String> valuesByTypeDescr) {
        List<TypeObjDescr> oddList = em.createNamedQuery("TypeObjDescr.findByDsubUnid")
                .setParameter("dsubUnid", DescriptionSubjects.OBJECT_SALE)
                .getResultList();
        for (TypeObjDescr tod : oddList) {
            lotDao.createObjDescr(em, valuesByTypeDescr.getOrDefault(tod.getTodUnid(), null), null,
                    objUnid, tod.getTodUnid());
        }
    }

    public List<Document> getDocumentsForExportToEtpByLotAndObject(long lotUnid, Long objUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return em.createQuery("select v from Document v where v.indActual = 1" +
                            " and (v.lotUnid = :lotUnid or v.objUnid = :objUnid) " +
                            " and v.documIndExportEtp = 1 order by v.documUnid")
                    .setParameter("lotUnid", lotUnid)
                    .setParameter("objUnid", objUnid)
                    .getResultList();
        } catch (Throwable ex) {
            logger.error("Error when getDocumentsForExportByLotAndObject by lotUnid = {}, objUnid = {}", lotUnid, objUnid, ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    public List<Document> getAllDocumentsForExportToEtp(Long auctionUnid, Long lotUnid, Long objUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return em.createQuery("select a from Document a " +
                            " where (a.objUnid = :objUnid or a.lotUnid = :lotUnid or a.auctionUnid = :auctionUnid) " +
                            " and a.indActual = 1 and a.documIndExportEtp = 1 order by a.documOrderNum", Document.class)
                    .setParameter("objUnid", objUnid)
                    .setParameter("lotUnid", lotUnid)
                    .setParameter("auctionUnid", auctionUnid)
                    .getResultList();
        } catch (Exception ex) {
            logger.error("Error when getAllDocumentsForExportToEtp by auctionUnid = {}, lotUnid = {}, objUnid = {}",
                    auctionUnid, lotUnid, objUnid, ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    public ObjCost getMinObjCost(Long lotUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return lotDao.getMinObjCost(em, lotUnid);
        } catch (NoResultException ignored) {
        } catch (Exception ex) {
            logger.error("Error when get min ObjCost by lotUnid = {}", lotUnid, ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    public BigDecimal getBankruptcySubRfUnidByObjUnid(Long objUnid) {
        EntityManager em = null;
        EntityManager emBkr = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            String regionCode = (String) em.createNativeQuery(
                            "select a.regioncode from fias.addrobj a, web.address addr " +
                                    "where a.aoguid = addr.addr_region_id " +
                                    "and addr.obj_unid = :objUnid " +
                                    "and addr.ind_actual = 1 " +
                                    "and (a.actstatus = 1 or a.actstatus = 49)")
                    .setParameter("objUnid", objUnid)
                    .getSingleResult();
            emBkr = HibernateUtil.getInstance().getEntityManagerBKR();
            BigDecimal srfUnid = (BigDecimal) emBkr.createNativeQuery("select srf_unid from web.SUBJECT_RF t where t.srf_code = :srfCode")
                    .setParameter("srfCode", regionCode)
                    .getSingleResult();
            return srfUnid;
        } catch (Exception ex) {
            logger.error("Error when getting bankruptcy subRfUnid by objUnid = {}", objUnid, ex);
        } finally {
            closeEntityManager(em);
            closeEntityManager(emBkr);
        }
        return null;
    }

    public SaleCategory getSaleCategoryByScCode(String scCode) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return lotDao.getSaleCategoryByScCode(em, scCode);
        } catch (Exception ex) {
            logger.error("Can't get SaleCategory by code = {}", scCode, ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    public SaleCategory getSaleCategoryByScUnid(Long scUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return objectController.getSaleCategory(em, scUnid);
        } catch (Exception ex) {
            logger.error("Error when get start SaleCategory by scUnid = {}", scUnid, ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    public ObjCost getStartObjCost(Long lotUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return lotDao.getStartObjCost(em, lotUnid);
        } catch (NoResultException ex) {
            logger.warn("Start ObjCost not found by lotUnid = {}", lotUnid);
        } catch (Exception ex) {
            logger.error("Error when get start ObjCost by lotUnid = {}", lotUnid, ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    public ObjSaleCategory getObjSaleCategoryByObjUnid(Long objUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return lotDao.getObjSaleCategoryByObjUnid(em, objUnid);
        } catch (Exception ex) {
            logger.error("Error when get start ObjSaleCategory by objUnid = {}", objUnid, ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    public Auction getAuction(Long auctionUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return auctionDao.getAuction(em, auctionUnid);
        } catch (Exception ex) {
            logger.error("Error when get Auction by auctionUnid = {}", auctionUnid, ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    public Lot getLot(Long lotUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return lotDao.getLot(em, lotUnid);
        } catch (Exception ex) {
            logger.error("Error when get Lot by lotUnid = {}", lotUnid, ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }


    public List<ReductionSchedule> getRedSchedsByLotUnid(long lotUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return em.createNamedQuery("ReductionSchedule.findByLotUnid")
                    .setParameter("lotUnid", lotUnid)
                    .getResultList();
        } catch (NoResultException e) {
        } catch (Throwable ex) {
            logger.error("Error when getting ReductionSchedule by lotUnid = {}", lotUnid, ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    public List<Picture> getPicturesForExportToEtp(long objUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            Query query = em.createQuery("select a from Picture a where a.indActual = 1 and a.dfUnid is not null and a.objUnid = :objUnid and a.pictIndExportEtp = 1 order by a.pictNum");
            query.setParameter("objUnid", objUnid);
            return query.getResultList();
        } catch (Throwable ex) {
            logger.error("Error when getting Pictures by objUnid = {}", objUnid, ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    public List<Lot> getCurrentLotsByAuction(long auctionUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return em.createQuery("select l from Lot l " +
                            "where l.auctionUnid = :auctionUnid and l.lotIndCurrent = 1 and l.indActual = 1 order by l.lotNumber")
                    .setParameter("auctionUnid", auctionUnid)
                    .getResultList();
        } catch (Throwable ex) {
            logger.error("Error getCurrentLotsByAuction by auctionUnid = {}", auctionUnid, ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    public Result<Void, String> updateLotLinkSite(Long lotUnid, String lotLinkSite) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            em.getTransaction().begin();
            Lot lot = em.find(Lot.class, lotUnid);
            lot.setLotLinkSite(lotLinkSite);
            lot.setDateBRec(new Date());
            lot.setPersCodeBRec(1L);
            em.merge(lot);
            em.getTransaction().commit();
            return Result.ok();
        } catch (Exception ex) {
            logger.error("Error when update lotLinkSite. lotUnid = {}, lotLinkSite = {}", lotUnid, lotLinkSite, ex);
            rollbackTransaction(em);
            return Result.error(getStackTrace(ex));
        } finally {
            closeEntityManager(em);
        }
    }

    public Address getAddress(ObjectJPA objUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return (Address) em.createQuery("select a from Address a where a.objUnid = :objUnid and a.indActual = 1")
                    .setParameter("objUnid", objUnid)
                    .getSingleResult();
        } catch (Exception ex) {
            logger.error("Error getAddress by objUnid = {}", objUnid, ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    public ObjectJPA getObject(Long objUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return em.find(ObjectJPA.class, objUnid);
        } catch (Exception ex) {
            logger.error("Error getObject by objUnid = {}", objUnid, ex);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    public void createObjDescrForAsset(EntityManager em, String html, Long objUnid) {
        lotDao.createObjDescr(em, html, null, objUnid, TypeObjDescrs.HTML_ASSET);
    }

    public Result<Void, String> updateAssets(Map<Long, String> objDescrs) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            em.getTransaction().begin();

            em.createQuery("update ObjDescr set indActual = 0 where todUnid = :todUnid and objUnid in :objUnids")
                    .setParameter("todUnid", TypeObjDescrs.HTML_ASSET)
                    .setParameter("objUnids", objDescrs.keySet())
                    .executeUpdate();

            for (Map.Entry<Long, String> entrySet: objDescrs.entrySet()) {
                Long objUnid = entrySet.getKey();
                String htmlAsset = entrySet.getValue();
                createObjDescrForAsset(em, htmlAsset, objUnid);
            }

            em.getTransaction().commit();
            return Result.ok();
        } catch (Exception ex) {
            logger.error("Error when trying to update assets", ex);
            rollbackTransaction(em);
            return Result.error(getStackTrace(ex));
        } finally {
            closeEntityManager(em);
        }
    }

    public List<Lot> findLotsByAuctionUnidOrderByLotNumber(Long auctionUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return (List<Lot>) em.createNamedQuery("Lot.findByAuctionUnidOrderByLotNumber")
                    .setParameter("auctionUnid", auctionUnid)
                    .getResultList();
        } catch (Exception ex) {
            logger.error("Error when trying to find Lots by auction_unid = {}", auctionUnid, ex);
            return new ArrayList<>();
        } finally {
            closeEntityManager(em);
        }
    }

    public List<Lot> findLotsByAuctionUnid(Long auctionUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return (List<Lot>) em.createNamedQuery("Lot.findByAuctionUnid")
                    .setParameter("auctionUnid", auctionUnid)
                    .getResultList();
        } catch (Exception ex) {
            logger.error("Error when trying to find Lots by auction_unid = {}", auctionUnid, ex);
            return new ArrayList<>();
        } finally {
            closeEntityManager(em);
        }
    }

    public Result<Lot, String> updateLot(Lot lot) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            em.getTransaction().begin();
            lotDao.updateLot(em, lot);
            em.getTransaction().commit();
            return Result.ok();
        } catch (Exception ex) {
            rollbackTransaction(em);
            logger.error("Error when trying to update lot. lot_unid = {}", lot.getLotUnid(), ex);
            return Result.error(ex.getMessage());
        } finally {
            closeEntityManager(em);
        }
    }

    public Result<ReductionSchedule, String> saveReductionSchedule(ReductionSchedule scheduleRad) {

        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            em.getTransaction().begin();
            lotDao.saveOrUpdate(em, scheduleRad);
            em.getTransaction().commit();
            return Result.ok();
        } catch (Exception ex) {
            rollbackTransaction(em);
            logger.error("Error when trying to save or update ReductionSchedule. RedSchedUnid = {}", scheduleRad.getRedSchedUnid(), ex);
            return Result.error(ex.getMessage());
        } finally {
            closeEntityManager(em);
        }
    }

    public ObjectJPA getObjectByLotUnid(Long lotUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            ObjectJPA obj = em.createQuery("select o from ObjectJPA o, Lot l where l.objUnid = o.objUnid and l.lotUnid = :lotUnid", ObjectJPA.class)
                    .setParameter("lotUnid", lotUnid)
                    .getSingleResult();
            return obj;
        } catch (Exception e) {
            logger.error("Can't getObjectByLotUnid by lotUnid = {}", lotUnid, e);
            return null;
        } finally {
            closeEntityManager(em);
        }
    }

    private String getStackTrace(Exception ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        return sw.toString();
    }

    public Optional<Lot> getLotByEtpTenderIdAndLotNumber(Long auctionEtpId, Long lotNumber) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            Lot lot = em.createQuery("select l from Lot l, Auction a where l.indActual = 1 and l.auctionUnid = a.auctionUnid and a.auctionEtpId = :auctionEtpId and l.lotNumber = :lotNumber", Lot.class)
                    .setParameter("auctionEtpId", auctionEtpId)
                    .setParameter("lotNumber", lotNumber)
                    .getSingleResult();
            return Optional.of(lot);
        } catch(NoResultException ex) {
            logger.info("No lot found by auctionEtpId = {} and lotNumber = {}", auctionEtpId, lotNumber);
            return Optional.empty();
        } catch (Exception e) {
            logger.error("Can't getLotByEtpTenderIdAndLotNumber by auctionEtpId = {} and lotNumber = {}", auctionEtpId, lotNumber, e);
            return Optional.empty();
        } finally {
            closeEntityManager(em);
        }
    }

    public List<VAsvLot> getAsvLotsByAuctionUnid(Long auctionUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return em.createNamedQuery("VAsvLot.findByAuctionUnid", VAsvLot.class)
                    .setParameter("auctionUnid", auctionUnid)
                    .getResultList();
        } catch (Exception ex) {
            logger.error("Error getAsvLotsByAuctionUnid auctionUnid = {}", auctionUnid, ex);
            return new ArrayList<>();
        } finally {
            closeEntityManager(em);
        }
    }

    public List<VAsvApplication> getAsvLotApplications(Long lotUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return em.createNamedQuery("VAsvApplication.findByLotUnid", VAsvApplication.class)
                    .setParameter("lotUnid", lotUnid)
                    .getResultList();
        } catch (Exception ex) {
            logger.error("Error getAsvLotApplications lotUnid = {}", lotUnid, ex);
            return new ArrayList<>();
        } finally {
            closeEntityManager(em);
        }
    }

    public List<Document> getApplicationDocuments(Long applicatUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return em.createNamedQuery("Document.findByApplicatUnid", Document.class)
                    .setParameter("applicatUnid", applicatUnid)
                    .getResultList();
        } catch (Exception ex) {
            logger.error("Error getApplicationDocuments applicatUnid = {}", applicatUnid, ex);
            return new ArrayList<>();
        } finally {
            closeEntityManager(em);
        }
    }

    /**
     * Возвращает список кодов лотов на площадке БКР для торгов Траст
     *
     * @return список кодов лотов
     */
    public List<Lot> getTrustLots() {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return em.createQuery("SELECT l " +
                            "FROM ObjectJPA o, Lot l " +
                            "WHERE o = l.objUnid " +
                            "  AND o.indActual = 1" +
                            "  AND l.indActual = 1" +
                            "  AND o.lsUnid IN (5, 6, 7, 8)" +
                            "  AND l.lotEtpCode is not null" +
                            "  AND l.lotIndCurrent = 1", Lot.class)
                    .getResultList();
        } catch (Exception e) {
            logger.error("Can't get Trust lot list", e);
            return Collections.emptyList();
        } finally {
            closeEntityManager(em);
        }
    }

    public ObjRole getObjRoleSale(Long objUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return objRoleDao.getSaleObjRole(em, objUnid);
        } catch (Exception ex) {
            logger.error("Error getObjRoleSale objUnid = {}", objUnid, ex);
            return null;
        } finally {
            closeEntityManager(em);
        }
    }
}
