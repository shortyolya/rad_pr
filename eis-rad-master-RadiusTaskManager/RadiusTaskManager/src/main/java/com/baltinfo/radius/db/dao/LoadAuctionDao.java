package com.baltinfo.radius.db.dao;

import com.baltinfo.radius.db.constants.TypeAuctionConstant;
import com.baltinfo.radius.db.controller.LotController;
import com.baltinfo.radius.db.dto.AuctionDto;
import com.baltinfo.radius.db.dto.LoadAuctionDto;
import com.baltinfo.radius.db.dto.LoadFileDto;
import com.baltinfo.radius.db.dto.LoadLotDto;
import com.baltinfo.radius.db.dto.LoadRsDto;
import com.baltinfo.radius.db.model.Auction;
import com.baltinfo.radius.db.model.LoadAuction;
import com.baltinfo.radius.db.model.LoadAuctionSettings;
import com.baltinfo.radius.db.model.LoadFile;
import com.baltinfo.radius.db.model.LoadLot;
import com.baltinfo.radius.db.model.LoadRs;
import com.baltinfo.radius.db.model.LoadSource;
import com.baltinfo.radius.db.model.LoadStatus;
import com.baltinfo.radius.db.model.SaleCategory;
import com.baltinfo.radius.db.model.Subject;
import org.apache.http.util.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * DAO работы с загрузкой торгов, лотов и графика по 5 алгоритму
 * </p>
 *
 * @author Lapenok Igor
 * @since 20.08.2018
 */
public class LoadAuctionDao extends AbstractDao {

    private static Logger logger = LoggerFactory.getLogger(AuctionDao.class);

    private final LotController lotController;

    public LoadAuctionDao(LotController lotController) {
        this.lotController = Objects.requireNonNull(lotController, "Can't get lotController");
    }

    /**
     * Получение списка загрузок торгов по статусу загрузки
     *
     * @param em      экземпляр {@link EntityManager}
     * @param lstUnid идентификатор статуса загрузки торгов
     * @return список объектов, загрузка торгов {@link LoadAuction}
     */
    public List<LoadAuction> getLoadAuction(EntityManager em, Long lstUnid) {
        logger.debug("start method: getLoadAuctionSettings() at " + new Date().getTime());
        try {
            return em.createNamedQuery("LoadAuction.findByStatus")
                    .setParameter("lstUnid", lstUnid)
                    .getResultList();
        } catch (Exception ex) {
            logger.error("Can't get load auction lstUnid = " + lstUnid, ex);
            return new ArrayList<>();
        } finally {
            logger.debug("finish method: getLoadAuctionSettings() at " + new Date().getTime());
        }
    }

    public List<LoadLot> getLoadLotsForTransfer(EntityManager em, Long laUnid) {
        logger.debug("start method: getLoadLotsForTransfer() at " + new Date().getTime());
        try {
            return em.createNamedQuery("LoadLot.findForTransferByLaUnid")
                    .setParameter("laUnid", laUnid)
                    .getResultList();
        } catch (Exception ex) {
            logger.error("Can't get load lots laUnid = " + laUnid, ex);
            return new ArrayList<>();
        } finally {
            logger.debug("finish method: getLoadLotsForTransfer() at " + new Date().getTime());
        }
    }

    public List<LoadRs> getLoadRsForTransfer(EntityManager em, Long laUnid) {
        logger.debug("start method: getLoadRsForTransfer() at " + new Date().getTime());
        try {
            return em.createNamedQuery("LoadRs.findForTransferByLaUnid")
                    .setParameter("laUnid", laUnid)
                    .getResultList();
        } catch (Exception ex) {
            logger.error("Can't get load rs list by laUnid = " + laUnid, ex);
            return new ArrayList<>();
        } finally {
            logger.debug("finish method: getLoadRsForTransfer() at " + new Date().getTime());
        }
    }

    public List<LoadFile> getLoadFileByLoadLot(EntityManager em, Long llUnid) {
        logger.debug("start method: getLoadFileByLoadLot() at " + new Date().getTime());
        try {
            return em.createNamedQuery("LoadFile.findLoadFileByLoadLot")
                    .setParameter("llUnid", llUnid)
                    .getResultList();
        } catch (Exception ex) {
            logger.error("Can't get load rs list by llUnid = " + llUnid, ex);
            return new ArrayList<>();
        } finally {
            logger.debug("finish method: getLoadFileByLoadLot() at " + new Date().getTime());
        }
    }

    public List<LoadFile> getLoadFileByBaUnid(EntityManager em, Long baUnid) {
        logger.debug("start method: getLoadFileByBaUnid() at " + new Date().getTime());
        try {
            TypedQuery<LoadFile> resultList = em.createQuery("SELECT lf FROM BlockAuction ba, LoadFile lf " +
                    "WHERE lf.indActual = 1 AND ba.indActual = 1 " +
                    "AND ba.baUnid = :baUnid AND lf.baUnid = ba.baUnid", LoadFile.class)
                    .setParameter("baUnid", baUnid);
            return resultList.getResultList();
        } catch (Exception ex) {
            logger.error("Can't get load file list by baUnid = " + baUnid, ex);
            return new ArrayList<>();
        } finally {
            logger.debug("finish method: getLoadFileByBaUnid() at " + new Date().getTime());
        }
    }


    public Optional<LoadFile> getLoadXmlFileByBaUnid(EntityManager em, Long baUnid) {
        try {
            LoadFile result = (LoadFile) em.createQuery("SELECT lf FROM BlockAuction ba, LoadFile lf " +
                    "WHERE lf.indActual = 1 AND ba.indActual = 1 " +
                    "AND ba.baUnid = :baUnid AND lf.baUnid = ba.baUnid " +
                    "AND lf.lfFileExt = 'xml' " +
                    "AND lf.lfType = 3")
                    .setParameter("baUnid", baUnid)
                    .getSingleResult();
            return Optional.of(result);
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    public Optional<LoadFile> getLoadJsonFileByBaUnid(EntityManager em, Long baUnid) {
        try {
            LoadFile result = (LoadFile) em.createQuery("SELECT lf FROM BlockAuction ba, LoadFile lf " +
                    "WHERE lf.indActual = 1 AND ba.indActual = 1 " +
                    "AND ba.baUnid = :baUnid AND lf.baUnid = ba.baUnid " +
                    "AND lf.lfFileExt = 'json' " +
                    "AND lf.lfType = 3")
                    .setParameter("baUnid", baUnid)
                    .getSingleResult();
            return Optional.of(result);
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    public LoadAuctionSettings getLoadAuctionSettings(EntityManager em, Long lsUnid, String typeAuctionName) {
        logger.debug("start method: getLoadAuctionSettings() at " + new Date().getTime());
        try {
            return (LoadAuctionSettings) em.createNamedQuery("LoadAuctionSettings.findBySourceForTypeAuction")
                    .setParameter("lsUnid", lsUnid)
                    .setParameter("typeAuctionName", typeAuctionName)
                    .getSingleResult();
        } catch (Exception ex) {
            logger.error("Can't get load auction lsUnid = " + lsUnid + "; typeAuctionName = " + typeAuctionName, ex);
            return null;
        } finally {
            logger.debug("finish method: getLoadAuctionSettings() at " + new Date().getTime());
        }
    }

    public LoadAuctionSettings getLoadAuctionSettings(EntityManager em, Long lsUnid, Long typeAuctionUnid, Integer stepForm) {
        try {
            return (LoadAuctionSettings) em.createNamedQuery("LoadAuctionSettings.findByTypeAuctionAndStepForm")
                    .setParameter("lsUnid", lsUnid)
                    .setParameter("typeAuctionUnid", typeAuctionUnid)
                    .setParameter("lasStepForm", stepForm)
                    .getSingleResult();
        } catch (Exception ex) {
            logger.error("Can't get load auction lsUnid = {}, typeAuctionUnid = {}, stepForm = {}",
                    lsUnid, typeAuctionUnid, stepForm,  ex);
            return null;
        }
    }

    /**
     * Обновление загрузки торгов
     *
     * @param em          экземпляр {@link EntityManager}
     * @param auction     экземпляр {@link Auction}, содержащий информацию о торгах
     * @param loadAuction экземпляр {@link LoadAuction}, содержащий информацию о загрузке торгах
     * @return загрузка торгов {@link LoadAuction}
     */
    public LoadAuction updateLoadAuction(EntityManager em, Auction auction, LoadAuction loadAuction, Boolean fromXls) {
        if (fromXls) {
            loadAuction.setLstUnid(em.find(LoadStatus.class, LoadStatus.XLS_RUN_STATUS));
        } else {
            loadAuction.setLstUnid(em.find(LoadStatus.class, LoadStatus.XML_RUN_STATUS));
        }
        loadAuction.setAuctionUnid(auction);
        setRecHisory(new Date(), loadAuction);
        return em.merge(loadAuction);
    }

    /**
     * Обновление загрузки торгов
     *
     * @param em          экземпляр {@link EntityManager}
     * @param loadAuction экземпляр {@link LoadAuction}, содержащий информацию о загрузке торгах
     * @param lstUnid     идентификатор статуса загрузки торгов
     * @return загрузка торгов {@link LoadAuction}
     */
    public LoadAuction updateLoadAuctionStatus(EntityManager em, LoadAuction loadAuction, Long lstUnid, String error) {
        loadAuction.setLstUnid(em.find(LoadStatus.class, lstUnid));
        loadAuction.setLaErrorInfo(error);
        setRecHisory(new Date(), loadAuction);
        return em.merge(loadAuction);
    }

    public LoadAuction updateLoadAuctionStatus(EntityManager em, LoadAuction loadAuction, Long lstUnid) {
        loadAuction.setLstUnid(em.find(LoadStatus.class, lstUnid));
        setRecHisory(new Date(), loadAuction);
        return em.merge(loadAuction);
    }


    /**
     * Создание загрузки лота
     *
     * @param em          экземпляр {@link EntityManager}
     * @param loadAuction экземпляр {@link LoadAuction}, содержащий информацию о загрузке торгов
     * @param auctionDto  экземпляр {@link AuctionDto}, содержащий информацию о аукционе
     * @param loadLotDto  экземпляр DTO {@link LoadLotDto}, содержащий информацию о лоте
     * @return загрузка лота {@link LoadLot}
     */
    public LoadLot createLoadLot(EntityManager em, LoadAuction loadAuction, AuctionDto auctionDto, LoadLotDto loadLotDto) {
        LoadLot loadLot = convertLot(loadLotDto, auctionDto);
        loadLot.setLaUnid(loadAuction);
        setNewHisory(new Date(), loadLot);
        em.persist(loadLot);
        for (LoadFileDto loadFile : loadLotDto.getLlFiles()) {
            createLoadFiles(em, loadLot, loadFile);
        }
        return loadLot;
    }

    /**
     * Создание загрузки графика снижения цены для 5 алгоритма
     *
     * @param em          экземпляр {@link EntityManager}
     * @param loadAuction экземпляр {@link LoadAuction}, содержащий информацию о загрузке торгов
     * @param loadRsDto   экземпляр DTO {@link LoadRsDto}, содержащий информацию о графике снижения цены для 5 алгоритма
     * @return загрузка графика снижения цены для 5 алгоритма {@link LoadRs}
     */
    public LoadRs createLoadRs(EntityManager em, LoadAuction loadAuction, LoadRsDto loadRsDto) {
        LoadRs loadRs = convertRs(loadRsDto);
        loadRs.setLaUnid(loadAuction);
        setNewHisory(new Date(), loadRs);
        em.persist(loadRs);
        return loadRs;
    }

    /**
     * Создание загрузки файлов
     *
     * @param em      экземпляр {@link EntityManager}
     * @param loadLot экземпляр {@link LoadLot}, содержащий информацию о загрузке лотов
     * @param fileDto экземпляр DTO {@link LoadFileDto}, содержащий информацию о загружаемом файле
     * @return загрузка файла {@link LoadFile}
     */
    private LoadFile createLoadFiles(EntityManager em, LoadLot loadLot, LoadFileDto fileDto) {
        LoadFile loadFile = new LoadFile();
        setNewHisory(new Date(), loadFile);
        loadFile.setLfFtpPath(fileDto.getLfFtpPath());
        loadFile.setLfType(fileDto.getLfType());
        loadFile.setLfAsvId(fileDto.getLfAsvId());
        loadFile.setLlUnid(loadLot.getLlUnid());
        loadFile.setLfEisPath(fileDto.getLfEisPath());
        loadFile.setLfFileName(fileDto.getLfFileName());
        loadFile.setLfFileSize(fileDto.getLfSize());
        loadFile.setLfFileExt(fileDto.getLfExtension());
        loadFile.setLfFileNameAsv(fileDto.getLfFileNameAsv());
        em.persist(loadFile);
        return loadFile;
    }

    public LoadFile createLoadFilesByBaUnid(EntityManager em, Long baUnid, LoadFileDto fileDto) {
        LoadFile loadFile = new LoadFile();
        setNewHisory(new Date(), loadFile);
        loadFile.setLfFtpPath(fileDto.getLfFtpPath());
        loadFile.setLfType(fileDto.getLfType());
        loadFile.setLfAsvId(fileDto.getLfAsvId());
        loadFile.setLfEisPath(fileDto.getLfEisPath());
        loadFile.setBaUnid(baUnid);
        loadFile.setLfFileName(fileDto.getLfFileName());
        loadFile.setLfFileSize(fileDto.getLfSize());
        loadFile.setLfFileExt(fileDto.getLfExtension());
        loadFile.setLfFileNameAsv(fileDto.getLfFileNameAsv());
        em.persist(loadFile);
        return loadFile;
    }

    public LoadLot updateLoadLot(EntityManager em, LoadLot loadLot) {
        setRecHisory(new Date(), loadLot);
        return em.merge(loadLot);
    }


    private LoadLot convertLot(LoadLotDto loadLotDto, AuctionDto auctionDto) {
        LoadLot loadLot = new LoadLot();
        loadLot.setLlLotNum(loadLotDto.getLlLotNum());
        loadLot.setLlRegion(loadLotDto.getLlRegion());
        loadLot.setLlLotSname(loadLotDto.getLlLotSname());
        loadLot.setLlLotName(loadLotDto.getLlLotName());
        loadLot.setLlReviewDebitorOrder(loadLotDto.getLlReviewDebitorOrder());
        loadLot.setLlStartCost(loadLotDto.getLlStartCost());
        loadLot.setLlDepositSum(loadLotDto.getLlDepositSum());
        if (auctionDto.getTypeAuction().equals(TypeAuctionConstant.HOLLAND)) {
            loadLot.setLlStepDecreaseValue(loadLotDto.getLlStepDecreaseValue());
            loadLot.setLlMinPrice(loadLotDto.getLlMinPrice());
            loadLot.setLlChangePricePeriodMin(auctionDto.getChangePricePeriodMin());
            loadLot.setLlStartPriceTime(auctionDto.getStartPriceTime());
        }
        if (auctionDto.getTypeAuction().equals(TypeAuctionConstant.PUBLIC)) {
            loadLot.setLlStepDecreaseValue(loadLotDto.getLlStepDecreaseValue());
        }
        loadLot.setLlStepValue(loadLotDto.getLlStepValue());
        loadLot.setLlMinPrice(loadLotDto.getLlMinPrice());
        loadLot.setLlChangePricePeriodMin(auctionDto.getChangePricePeriodMin());
        loadLot.setLlStartPriceTime(auctionDto.getStartPriceTime());
        loadLot.setLlChangePriceTimeH(loadLotDto.getLlChangePriceTimeH());
        loadLot.setLlChangePriceTimeM(loadLotDto.getLlChangePriceTimeM());
        loadLot.setLlChangePricePeriod(loadLotDto.getLlChangePricePeriod());
        loadLot.setLlApplPeriod(loadLotDto.getLlApplPeriod());
        loadLot.setLlChangePriceValue(loadLotDto.getLlChangePriceValue());
        loadLot.setLlChangePriceAlg(loadLotDto.getLlChangePriceAlg());
        loadLot.setLlDepositAlg(loadLotDto.getLlDepositAlg() == null ? null : loadLotDto.getLlDepositAlg().getName());
        loadLot.setLlTypeObject(loadLotDto.getLlTypeObject());
        loadLot.setLlAddress(loadLotDto.getLlAddress());
        loadLot.setLlClAsv(loadLotDto.getLlClAsv());
        loadLot.setLlIndRightEnsure(loadLotDto.getLlIndRightEnsure());
        loadLot.setLlLandCommonSqr(loadLotDto.getLlLandCommonSqr());
        loadLot.setLlFlatCommonSqr(loadLotDto.getLlFlatCommonSqr());
        loadLot.setLlMarketingRequirements(loadLotDto.getLlMarketingRequirements());
        loadLot.setLlContacts(auctionDto.getDpContacts());
        loadLot.setLlObjectLinks(auctionDto.getDpObjectLinks());
        loadLot.setLlRewardRadius(auctionDto.getDpRewardRadius());
        loadLot.setLlAsvId(loadLotDto.getLlAsvId());
        loadLot.setScUnid(getScUnid(loadLotDto));
        loadLot.setLlAssets(loadLotDto.getLlAssets());
        loadLot.setLlAsvLink(loadLotDto.getLlAsvLink());
        loadLot.setLlTzNum(loadLotDto.getLlTzNum());
        loadLot.setLlTzDate(loadLotDto.getLlTzDate());
        loadLot.setLlAsvStageId(loadLotDto.getLlAsvStageId());
        loadLot.setLlPeriodBeginningDecline(loadLotDto.getLlPeriodBeginningDecline());
        return loadLot;
    }

    private Long getScUnid(LoadLotDto loadLotDto) {
        if (loadLotDto.getScUnid() == null && !TextUtils.isBlank(loadLotDto.getScCode())) {
            SaleCategory saleCategory = lotController.getSaleCategoryByScCode(loadLotDto.getScCode());
            return saleCategory.getScUnid();
        } else {
            return loadLotDto.getScUnid();
        }
    }

    private LoadRs convertRs(LoadRsDto loadRsDto) {
        LoadRs loadRs = new LoadRs();
        loadRs.setLrsChangePriceDate(loadRsDto.getLrsChangePriceDate().getDate());
        loadRs.setLrsApplDate(loadRsDto.getLrsApplDate().getDate());
        loadRs.setLrsSumm(loadRsDto.getLrsSumm());
        loadRs.setLrsDepositSumm(loadRsDto.getLrsDepositSumm());
        loadRs.setLrsCalcPriceAlg(loadRsDto.getCalcPriceType().getValue());
        loadRs.setLrsLots(loadRsDto.getLotsNumbers().isEmpty()
                ? ""
                : String.join(",", loadRsDto.getLotsNumbers().stream()
                .map(Object::toString)
                .collect(Collectors.toList())));
        return loadRs;
    }

    public Subject getBankDebtorAsvByAuctionUnid(EntityManager em, Long auctionUnid) {
        return (Subject) em.createQuery("select s " +
                "from Subject s, LoadAuction la, Auction a " +
                "where s.subUnid = la.subUnid " +
                "and a.indActual = 1 " +
                "and la.indActual = 1 " +
                "and s.indActual = 1 " +
                "and a = la.auctionUnid " +
                "and a.auctionUnid = :auctionUnid")
                .setParameter("auctionUnid", auctionUnid)
                .getSingleResult();
    }

    public boolean isAsvAuction(EntityManager em, Long auctionUnid) {
            Long count = (Long) em.createQuery("select count(a) from LoadAuction la, Auction a " +
                "where a = la.auctionUnid " +
                "and a.auctionUnid = :auctionUnid " +
                "and a.indActual = 1 " +
                "and la.indActual = 1 " +
                "and (la.lsUnid.lsUnid = 1 or la.lsUnid.lsUnid = 2) " +
                "and a.auctionAsvId is not null " +
                "and a.auctionAsvId <> ''")
                .setParameter("auctionUnid", auctionUnid)
                .getSingleResult();
        return count > 0;
    }

    public LoadAuction createLoadAuction(EntityManager em, LoadAuctionDto loadAuctionDto, LoadAuctionSettings loadAuctionSettings, Long baUnid) {
        LoadAuction loadAuction = new LoadAuction();
        loadAuction.setLaAuctionName(loadAuctionDto.getLaAuctionName());
        loadAuction.setBaUnid(baUnid);
        loadAuction.setLstUnid(em.find(LoadStatus.class, loadAuctionDto.getLstUnid()));
        loadAuction.setLsUnid(em.find(LoadSource.class, loadAuctionDto.getLsUnid()));
        loadAuction.setLaStageNum(loadAuctionDto.getLaStageNum());
        loadAuction.setTpaUnid(loadAuctionDto.getTpaUnid());
        loadAuction.setPaUnid(1L);
        loadAuction.setLasUnid(loadAuctionSettings);
        loadAuction.setSgUnid(loadAuctionSettings.getLssUnid().getSgUnid());
        loadAuction.setSmUnid(loadAuctionSettings.getLssUnid().getSmUnid());
        loadAuction.setToUnid(loadAuctionSettings.getLssUnid().getToUnid());
        setNewHisory(new Date(), loadAuction);
        em.persist(loadAuction);
        return loadAuction;

    }
}
