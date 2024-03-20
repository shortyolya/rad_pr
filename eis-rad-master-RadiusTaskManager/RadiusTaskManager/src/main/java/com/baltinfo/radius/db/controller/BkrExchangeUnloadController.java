package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.bankruptcy.BkrAdditionalDataParams;
import com.baltinfo.radius.bankruptcy.BkrLotParams;
import com.baltinfo.radius.bankruptcy.BkrPublicationBodyParams;
import com.baltinfo.radius.bankruptcy.BkrReductionScheduleParams;
import com.baltinfo.radius.bankruptcy.BkrTypePublicationBody;
import com.baltinfo.radius.bankruptcy.IBkrPublicationBodyParams;
import com.baltinfo.radius.bankruptcy.constant.ImageSize;
import com.baltinfo.radius.bankruptcy.export.AdditionalPropertiesService;
import com.baltinfo.radius.bankruptcy.export.BkrFileService;
import com.baltinfo.radius.db.constants.TypeObjDescrs;
import com.baltinfo.radius.db.controller.exchange.ExchangeUnloadController;
import com.baltinfo.radius.db.model.Address;
import com.baltinfo.radius.db.model.ObjDescr;
import com.baltinfo.radius.db.model.bankruptcy.Rubricator;
import com.baltinfo.radius.db.model.bankruptcy.VAuctionBkr;
import com.baltinfo.radius.exchange.ExchangeResult;
import com.baltinfo.radius.utils.HibernateUtil;
import com.baltinfo.radius.utils.ImageUtils;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Контроллер для работы с БД Банкротства
 *
 * @author Suvorina Aleksandra
 * @since 23.08.2018
 */
public class BkrExchangeUnloadController extends AbstractController implements ExchangeUnloadController<VAuctionBkr, BkrLotParams> {

    private static final Logger logger = LoggerFactory.getLogger(BkrExchangeUnloadController.class);

    private final AdditionalPropertiesService additionalPropertiesService;
    private BkrFileService bkrFileService;

    public BkrExchangeUnloadController(AdditionalPropertiesService additionalPropertiesService,
                                       BkrFileService bkrFileService) {
        this.additionalPropertiesService = Objects.requireNonNull(additionalPropertiesService, "Can't get additionalPropertiesService");
        this.bkrFileService = Objects.requireNonNull(bkrFileService, "Can't get bkrFileService");
    }

    @Override
    public ExchangeResult exportLot(VAuctionBkr auctionBkr, BkrLotParams params) {

        Rubricator rubricator = getRubricator(params.getSingleClassifCode());
        if (rubricator == null) {
            return ExchangeResult.notLoaded("Не удалось получить рубрику БКР по коду единого классификатора = " + params.getSingleClassifCode());
        }

        EntityManager emBkr = null;
        CallableStatement cs = null;
        Connection conn = null;
        try {
            emBkr = HibernateUtil.getInstance().getEntityManagerBKR();
            conn = HibernateUtil.getInstance().getConnectionProviderBKR().getConnection();
            emBkr.getTransaction().begin();
            cs = conn.prepareCall("{call WEB.CREATE_ENTITIES.PROC_CREATE_AUCTION_LOT_ALL(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setLong(1, auctionBkr.getWbUnid());
            cs.setLong(2, params.getSaleGuideUnid());
            cs.setLong(3, params.getTypeAuctionUnid());
            cs.setString(4, params.getLotAuctionTheme());
            cs.setString(5, params.getAuctionRecepDateB());
            cs.setString(6, params.getAuctionRecepDateE());
            cs.setString(7, params.getAuctionDepDateE());
            cs.setString(8, params.getAuctionDateB());
            cs.setBigDecimal(9, params.getLotStepValue());
            cs.setBigDecimal(10, params.getLotStepDecreaseValue());
            cs.setBigDecimal(11, params.getLotSumDeposit());
            cs.setBigDecimal(12, BigDecimal.valueOf(rubricator.getOcUnid()));
            cs.setBigDecimal(13, params.getStartCost());
            cs.setInt(14, params.getAuctionIndDown());
            cs.setString(15, params.getAuctionDateE());
            cs.setInt(16, params.getAuctionEndTerm());
            cs.setLong(17, params.getAuctionEndTime());
            cs.setInt(18, params.getIndPublish());
            cs.setString(19, params.getLotPublHeader());
            cs.setString(20, params.getLotNumber());
            cs.setLong(21, auctionBkr.getAuctionUnid());
            cs.setBigDecimal(22, BigDecimal.valueOf(rubricator.getRubrUnid()));
            cs.setLong(23, params.getLotUnidForUpdate());
            cs.setString(24, params.getLotReviewDebitorOrder());
            cs.setBigDecimal(25, params.getMinCostValue());
            cs.setLong(26, params.getLotChangePriceHour());
            cs.setLong(27, params.getLotChangePriceMin());
            cs.setBigDecimal(28, params.getLotStepProcent());
            cs.setBigDecimal(29, params.getTypeDealObject());
            cs.setBigDecimal(30, params.getSrfUnid());
            cs.setInt(31, params.getLotIndPledge());
            cs.setString(32, params.getLotPledgeBank());
            cs.setBigDecimal(33, params.getCaCode());
            cs.setBigDecimal(33, params.getCaCode());

            cs.setBigDecimal(34, null);
            cs.setBigDecimal(35, null);
            cs.setBigDecimal(36, null);
            cs.setString(37, null);
            cs.setString(38, null);
            cs.setBigDecimal(39, null);
            cs.setBigDecimal(40, (params.getLotDepositPer() == null) ? null : params.getLotDepositPer());
            cs.setInt(41, params.getLotIndRightEnsure());
            cs.setString(42, params.getLotAuctionHouseLink());
            cs.setString(43, params.getLotManagerFio());
            cs.setString(44, params.getLotManagerEmail());
            cs.setString(45, params.getLotManagerPhone());
            cs.setString(46, params.getLotAsvLink());
            cs.setString(47, params.getDoNamePublHeader());
            cs.setString(48, params.getLotAsvId());
            cs.setInt(49, 1);
            cs.setInt(50, 1);
            cs.setInt(51, 1);
            cs.setBigDecimal(52, params.getTcpStartPriceUnid());
            cs.setBigDecimal(53, params.getLotStartPricePeriod());
            cs.setBigDecimal(54, params.getLotOperatorDeposit());
            cs.setLong(55, params.getCurUnid());
            cs.registerOutParameter(56, Types.DECIMAL);
            cs.registerOutParameter(57, Types.VARCHAR);
            cs.executeUpdate();
            long lotUnid = cs.getLong(56);
            String ret = cs.getString(57);

            if (lotUnid < 0 || !ret.equals("SUCCESS")) {
                rollbackConnection(conn);
                rollbackTransaction(emBkr);
                logger.error("Error call PROC_CREATE_AUCTION_LOT_ALL. ret = " + ret);
                return ExchangeResult.notLoaded(ret);
            }

            Result<Long, String> wpUnidResult = getWebPublishingUnid(conn, lotUnid);
            if (wpUnidResult.isError()) {
                rollbackConnection(conn);
                rollbackTransaction(emBkr);
                return ExchangeResult.notLoaded(wpUnidResult.getError());
            }

            Long wpUnid = wpUnidResult.getResult();

            List<BkrReductionScheduleParams> redScheds = params.getReductionSchedule();
            if (redScheds != null) {
                for (BkrReductionScheduleParams reductionScheduleBkr : redScheds) {
                    Result<Void, String> resRs = exportReductionSchedule(conn, reductionScheduleBkr, lotUnid);
                    if (resRs.isError()) {
                        rollbackConnection(conn);
                        rollbackTransaction(emBkr);
                        return ExchangeResult.notLoaded(resRs.getError());
                    }
                }
            }

            List<String> errors = new ArrayList<>();

            List<IBkrPublicationBodyParams> pictures = params.getPictures();
            for (IBkrPublicationBodyParams picture : pictures) {
                Result<Void, String> resPicture = exportPublicationBody(conn, wpUnid, picture);
                if (resPicture.isError()) {
                    errors.add("Ошибка при выгрузке изображения: " + resPicture.getError());
                }
            }

            List<IBkrPublicationBodyParams> otherDocs = params.getOtherDocs();
            for (IBkrPublicationBodyParams doc : otherDocs) {
                Result<Void, String> resDoc = exportPublicationBody(conn, wpUnid, doc);
                if (resDoc.isError()) {
                    errors.add("Ошибка при выгрузке документа: " + resDoc.getError());
                }
            }

            try {
                Optional<String> additionalPropertiesJson = additionalPropertiesService.getAdditionalPropertiesJsonByObjUnid(params.getObject().getObjUnid());
                if (additionalPropertiesJson.isPresent()) {
                    byte[] fileBody = getBytesFromStringInUtf8(additionalPropertiesJson.get());
                    IBkrPublicationBodyParams bodyParams = new BkrPublicationBodyParams("additional_properties.json", fileBody, BkrTypePublicationBody.ADDITIONAL_PROPERTIES.getUnid());
                    Result<Void, String> exportResult = exportPublicationBody(conn, wpUnid, bodyParams);
                    if (exportResult.isError()) {
                        errors.add("Ошибка при выгрузке дополнительных свойств в формате JSON: " + exportResult.getError());
                    }
                }
            } catch (Exception e) {
                logger.error("Error when trying to export additional properties json. wpUnid = {} objUnid = {} ", wpUnid, params.getObject().getObjUnid(), e);
                errors.add("Ошибка при выгрузке дополнительных свойств в формате JSON: " + e.getMessage());
            }

            try {
                ObjDescr objDescr = getObjDescrByObjUnid(params.getObject().getObjUnid(), TypeObjDescrs.HTML_ASSET);
                if (objDescr != null) {
                    byte[] fileBody = getBytesFromStringInUtf8(objDescr.getOdText());
                    IBkrPublicationBodyParams bodyParams = new BkrPublicationBodyParams("html_asset.html", fileBody, BkrTypePublicationBody.HTML.getUnid());
                    Result<Void, String> resAsset = exportPublicationBody(conn, wpUnid, bodyParams);
                    if (resAsset.isError()) {
                        errors.add("Ошибка при выгрузке HTML-описания: " + resAsset.getError());
                    }
                }
            } catch (Exception e) {
                logger.error("Error when trying to service Asset. wpUnid = {} objUnid = {} ", wpUnid, params.getObject().getObjUnid(), e);
                errors.add("Ошибка при выгрузке HTML-описания: " + e.getMessage());
            }

            Result<Long, String> doUnidResult = getBkrDoUnid(conn, lotUnid);
            if (doUnidResult.isError()) {
                rollbackConnection(conn);
                rollbackTransaction(emBkr);
                return ExchangeResult.notLoaded(doUnidResult.getError());
            }

            Long doUnid = doUnidResult.getResult();

            BkrAdditionalDataParams lotDepositDocument = params.getLotDepositDocument();
            if (lotDepositDocument != null) {
                Result<Void, String> resDeposit = exportDocument(conn, doUnid, lotDepositDocument);
                if (resDeposit.isError()) {
                    errors.add("Ошибка при выгрузке документа: " + resDeposit.getError());
                }
            }
            BkrAdditionalDataParams lotSaleDealDraftPropertyDocument = params.getSaleDealDraftPropertyDocument();
            if (lotSaleDealDraftPropertyDocument != null) {
                Result<Void, String> resSaleDealDraft = exportDocument(conn, doUnid, lotSaleDealDraftPropertyDocument);
                if (resSaleDealDraft.isError()) {
                    errors.add("Ошибка при выгрузке документа: " + resSaleDealDraft.getError());
                }
            }

            if (params.getAddress() != null) {
                Result<Void, String> result = createAddress(conn, params.getAddress(), doUnid);
                if (result.isError()) {
                    errors.add("Ошибка при выгрузке адреса объекта: " + result.getError());
                }
            }

            conn.commit();
            emBkr.getTransaction().commit();
            if (errors.isEmpty()) {
                return ExchangeResult.loaded(lotUnid);
            } else {
                return ExchangeResult.loadedWithErrors(lotUnid, String.join("\n", errors));
            }
        } catch (Exception ex) {
            logger.error("Error when service lot to bankruptcy", ex);
            rollbackConnection(conn);
            rollbackTransaction(emBkr);
            return ExchangeResult.notLoaded(ex.getMessage());
        } finally {
            closeCallableStatement(cs);
            closeConnection(conn);
            closeEntityManager(emBkr);
        }
    }

    private Result<Void, String> createAddress(Connection conn, Address address, Long doUnid) {
        try (CallableStatement cs = conn.prepareCall("{call WEB.CREATE_ENTITIES.PROC_UPDATE_ADDRESS(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}")) {
            cs.setLong(1, 0);
            cs.setLong(2, address.getOkcmUnid());
            cs.setString(3, address.getAddrAddress());
            cs.setString(4, address.getAddrHouse());
            cs.setString(5, address.getAddrIndex());
            cs.setString(6, address.getAddrCorp());
            cs.setString(7, address.getAddrBuild());
            cs.setString(8, address.getAddrFlat());
            cs.setString(9, address.getAddrLat());
            cs.setString(10, address.getAddrLong());
            cs.setString(11, address.getAddrNote());
            cs.setLong(12, address.getAddrInputMode());
            cs.setString(13, address.getAddrRegionId());
            cs.setString(14, address.getAddrAutoId());
            cs.setString(15, address.getAddrAreaId());
            cs.setString(16, address.getAddrCityId());
            cs.setString(17, address.getAddrCtarId());
            cs.setString(18, address.getAddrPlaceId());
            cs.setString(19, address.getAddrStreetId());
            cs.setLong(20, doUnid);
            cs.setLong(21, 1);

            cs.registerOutParameter(22, Types.DECIMAL);
            cs.registerOutParameter(23, Types.VARCHAR);

            cs.executeUpdate();
            String ret = cs.getString(23);

            if (!ret.equals("SUCCESS")) {
                return Result.error(ret);
            }
            return Result.ok();
        } catch (Exception ex) {
            return Result.error(ex.toString());
        }
    }

    @Override
    public VAuctionBkr getEtpAuction(Long bkrAuctionUnid) {
        EntityManager emBkr = null;
        try {
            emBkr = HibernateUtil.getInstance().getEntityManagerBKR();
            return emBkr.find(VAuctionBkr.class, bkrAuctionUnid);
        } catch (Exception ex) {
            logger.error("Error getVAuctionBkr by bkrAuctionUnid = {}", bkrAuctionUnid, ex);
        } finally {
            closeEntityManager(emBkr);
        }
        return null;
    }

    /**
     * Получение рубрикатора на банкротстве по коду единого классификатора
     *
     * @param singleClassifCode код единого классификатора
     * @return рубрикатор
     */
    public Rubricator getRubricator(String singleClassifCode) {
        EntityManager emBkr = null;
        try {
            emBkr = HibernateUtil.getInstance().getEntityManagerBKR();
            return emBkr.createQuery("select r from Rubricator r " +
                    "where r.rubrSingleClassifCode = :singleClassifCode", Rubricator.class)
                    .setParameter("singleClassifCode", singleClassifCode)
                    .getSingleResult();
        } catch (Exception ex) {
            logger.error("Can't get Rubricator by single classif code = {}", singleClassifCode, ex);
        } finally {
            closeEntityManager(emBkr);
        }
        return null;
    }

    private Result<Long, String> getWebPublishingUnid(Connection conn, Long bankruptcyLotUnid) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT wp_unid from web.auction_lot_all where lot_unid = ?");
            preparedStatement.setLong(1, bankruptcyLotUnid);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Long wpUnid = rs.getLong("wp_unid");
                return Result.ok(wpUnid);
            } else {
                return Result.error("wp_unid not found by lotUnid = " + bankruptcyLotUnid);
            }
        } catch (Exception ex) {
            logger.error("Error when get webPublishing unid from bankruptcy by bankruptcyLotUnid = {}", bankruptcyLotUnid, ex);
            return Result.error("Error when get wp_unid from bankruptcy by lotUnid = " + bankruptcyLotUnid + ". Error: " + ex.getMessage());
        }
    }

    private Result<Void, String> exportReductionSchedule(Connection conn, BkrReductionScheduleParams r, long lotUnid) throws Exception {
        CallableStatement cs = null;
        try {
            cs = conn.prepareCall("{call WEB.CREATE_ENTITIES.PROC_CREATE_REDUCTION_SCHEDULE(?,?,?,?,?,?,?,?,?,?)}");
            cs.setLong(1, lotUnid);
            cs.setBigDecimal(2, r.getRedSchedReductionValue());
            cs.setBigDecimal(3, r.getRedSchedAskPrice());
            cs.setString(4, r.getRedSchedDateB());
            cs.setString(5, r.getRedSchedDateE());
            cs.setString(6, r.getRedSchedDepDateE());
            cs.setBigDecimal(7, r.getRedSchedDepSum());
            cs.setLong(8, r.getRedSchedUnid());
            cs.registerOutParameter(9, Types.VARCHAR);
            cs.registerOutParameter(10, Types.DECIMAL);

            cs.executeUpdate();
            String ret = cs.getString(9);
            Long rsUnid = cs.getLong(10);

            if (!ret.equals("SUCCESS")) {
                logger.info("service reductionSchedule to bankruptcy: FAIL ret = {}", ret);
                return Result.error("service reductionSchedule to bankruptcy: FAIL ret = " + ret);
            }
            return Result.ok();
        } catch (Exception ex) {
            logger.error("Error when service ReductionSchedule to bankruptcy", ex);
            return Result.error("Error when service ReductionSchedule to bankruptcy. Error: " + ex.getMessage());
        } finally {
            closeCallableStatement(cs);
        }
    }

    private Result<Void, String> exportPublicationBody(Connection conn, long wpUnid, IBkrPublicationBodyParams pb) {
        CallableStatement cs = null;

        try {
            Long dfUnid = bkrFileService.sendFileToStorage(pb.getFileBody(), pb.getFileName());
            if (dfUnid == null) {
                return Result.error("Error while sending publication body to storage, file name = " + pb.getFileName());
            }

            Long dfSmallUnid = null;
            byte[] thumbnailFileBody;
            if (pb.getTypePublicationBody().equals(BkrTypePublicationBody.GALLERY.getUnid())) {

                thumbnailFileBody = ImageUtils.createImage(pb.getFileBody(), pb.getFileName(), ImageSize.THUMBNAIL_SIZE);
                if (thumbnailFileBody != null) {
                    dfSmallUnid = bkrFileService.sendFileToStorage(thumbnailFileBody, pb.getFileName());
                    if (dfSmallUnid == null) {
                        return Result.error("Error while sending publication body thumbnail to storage, file name = " + pb.getFileName());
                    }
                }
            }

            cs = conn.prepareCall("{call WEB.CREATE_ENTITIES.PROC_CREATE_PUBLICATION_BODY(?,?,?,?,?,?,?,?,?)}");
            cs.setLong(1, 0);
            cs.setLong(2, wpUnid);
            cs.setLong(3, pb.getTypePublicationBody());
            cs.setString(4, pb.getFileName());
            cs.setBigDecimal(5, pb.getPbNum());
            cs.setLong(6, dfUnid);
            cs.setBigDecimal(7, dfSmallUnid == null ? null : BigDecimal.valueOf(dfSmallUnid));
            cs.registerOutParameter(8, Types.VARCHAR);
            cs.registerOutParameter(9, Types.INTEGER);

            cs.executeUpdate();
            String publicationBodyRet = cs.getString(8);
            if (!publicationBodyRet.equals("SUCCESS")) {
                logger.warn("service publication body to bankruptcy: FAIL ret = {}", publicationBodyRet);
                return Result.error("service publication body to bankruptcy: FAIL ret = " + publicationBodyRet);
            }
            return Result.ok();
        } catch (Exception ex) {
            logger.error("Error when service publication body to bankruptcy", ex);
            return Result.error("Error when service publication body to bankruptcy. Error: " + ex.getMessage());
        } finally {
            closeCallableStatement(cs);
        }
    }

    private Result<Void, String> exportDocument(Connection conn, Long doUnid, BkrAdditionalDataParams addData) {
        CallableStatement cs = null;

        try {
            Long dfUnid = bkrFileService.sendFileToStorage(addData.getAddData(), addData.getFileName());
            if (dfUnid == null) {
                return Result.error("Error while sending document to storage, file name = " + addData.getFileName());
            }

            cs = conn.prepareCall("{call WEB.CREATE_ENTITIES.PROC_CREATE_ADDITIONAL_DATA(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setLong(1, 0);
            cs.setBigDecimal(2, new BigDecimal(doUnid));
            cs.setBigDecimal(3, null);
            cs.setString(4, addData.getFileName());
            cs.setString(5, addData.getNote());
            cs.setLong(6, addData.getTadUnid());
            cs.setInt(7, addData.getIndInclude());
            cs.setInt(8, addData.getAddNum());
            cs.setBigDecimal(9, addData.getAuctionUnid());
            cs.setBigDecimal(10, addData.getAddAddUnid());
            cs.setLong(11, addData.getIndActual());
            cs.setLong(12, addData.getRsUnid());
            cs.setBigDecimal(13, null);
            cs.setLong(14, dfUnid);
            cs.setNull(15, Types.NUMERIC);
            cs.registerOutParameter(16, Types.VARCHAR);
            cs.registerOutParameter(17, Types.DECIMAL);
            cs.executeUpdate();
            String ret = cs.getString(16);
            if (!ret.equals("SUCCESS")) {
                logger.warn("service document to bankruptcy: FAIL ret = {}", ret);
                return Result.error("service document to bankruptcy: FAIL ret = " + ret);
            }

            return Result.ok();
        } catch (Exception ex) {
            logger.error("Error when service document to bankruptcy", ex);
            return Result.error("Error when service document to bankruptcy. Error: " + ex.getMessage());
        } finally {
            closeCallableStatement(cs);
        }
    }

    private Result<Long, String> getBkrDoUnid(Connection conn, Long bankruptcyLotUnid) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT do_unid from web.auction_lot_all where lot_unid = ?");
            preparedStatement.setLong(1, bankruptcyLotUnid);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Long doUnid = rs.getLong("do_unid");
                return Result.ok(doUnid);
            } else {
                return Result.error("do_unid not found by lotUnid = " + bankruptcyLotUnid);
            }
        } catch (Exception ex) {
            logger.error("Error when get do_unid from bankruptcy by bankruptcyLotUnid = {}", bankruptcyLotUnid, ex);
            return Result.error("Error when get do_unid from bankruptcy by lotUnid = " + bankruptcyLotUnid + ". Error: " + ex.getMessage());
        }
    }

    private ObjDescr getObjDescrByObjUnid(Long objUnid, Long todUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return (ObjDescr) em.createQuery("SELECT od FROM ObjDescr od" +
                    " WHERE od.objUnid = :objUnid and od.todUnid = :todUnid and od.indActual = 1")
                    .setParameter("objUnid", objUnid)
                    .setParameter("todUnid", todUnid)
                    .getSingleResult();
        } catch (NoResultException ex) {
            logger.warn("No ObjDescr found by objUnid = {} and todUnid = {}", objUnid, todUnid);
        } catch (Exception e) {
            logger.error("Error when trying to find ObjDescr by objUnid = {}", objUnid, e);
        } finally {
            closeEntityManager(em);
        }
        return null;
    }

    private byte[] getBytesFromStringInUtf8(String text) {
        Charset charset = StandardCharsets.UTF_8;
        return text.getBytes(charset);
    }
}
