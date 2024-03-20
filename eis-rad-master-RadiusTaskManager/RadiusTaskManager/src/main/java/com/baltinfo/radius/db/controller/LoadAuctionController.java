package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.db.constants.TransferResultConstant;
import com.baltinfo.radius.db.constants.TypeSubject;
import com.baltinfo.radius.db.dao.AuctionDao;
import com.baltinfo.radius.db.dao.BlockAuctionDao;
import com.baltinfo.radius.db.dao.LoadAuctionDao;
import com.baltinfo.radius.db.dao.SubjectDao;
import com.baltinfo.radius.db.dto.AuctionDto;
import com.baltinfo.radius.db.dto.BlockAuctionDto;
import com.baltinfo.radius.db.dto.LoadAuctionDto;
import com.baltinfo.radius.db.dto.LoadFileDto;
import com.baltinfo.radius.db.dto.LoadLotDto;
import com.baltinfo.radius.db.dto.LoadRsDto;
import com.baltinfo.radius.db.model.Auction;
import com.baltinfo.radius.db.model.BlockAuction;
import com.baltinfo.radius.db.model.LoadAuction;
import com.baltinfo.radius.db.model.LoadAuctionSettings;
import com.baltinfo.radius.db.model.LoadFile;
import com.baltinfo.radius.db.model.LoadLot;
import com.baltinfo.radius.db.model.LoadRs;
import com.baltinfo.radius.db.model.LoadStatus;
import com.baltinfo.radius.db.model.Subject;
import com.baltinfo.radius.db.model.SubjectHistory;
import com.baltinfo.radius.subject.SubjectUtils;
import com.baltinfo.radius.utils.HibernateUtil;
import com.baltinfo.radius.utils.Result;
import org.apache.http.util.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * <p>
 * Контроллер загрузки торгов для промежуточного сохранения в БД
 * </p>
 *
 * @author Lapenok Igor
 * @since 20.08.2018
 */
public class LoadAuctionController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(LoadAuctionController.class);
    private static final Long paUnid = 1L;

    private final AuctionDao auctionDao;
    private final LoadAuctionDao loadAuctionDao;
    private final SubjectUtils subjectUtils;
    private final SubjectDao subjectDao;
    private final BlockAuctionDao blockAuctionDao;

    public LoadAuctionController(AuctionDao auctionDao, LoadAuctionDao loadAuctionDao, SubjectUtils subjectUtils,
                                 SubjectDao subjectDao, BlockAuctionDao blockAuctionDao) {
        this.auctionDao = Objects.requireNonNull(auctionDao, "Can't get auction DAO");
        this.loadAuctionDao = Objects.requireNonNull(loadAuctionDao, "Can't get load auction DAO");
        this.subjectUtils = subjectUtils;
        this.subjectDao = subjectDao;
        this.blockAuctionDao = blockAuctionDao;
    }

    public List<LoadAuction> getLoadAuctionListForRun() {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return loadAuctionDao.getLoadAuction(em, LoadStatus.PREPARE_XLS_RUN_STATUS);
        } catch (Exception e) {
            logger.error("Can't get load auction", e);
            return new ArrayList<>();
        } finally {
            closeEntityManager(em);
        }
    }

    public List<LoadAuction> getLoadAuctionListFromXmlForRun() {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return loadAuctionDao.getLoadAuction(em, LoadStatus.PREPARE_XML_RUN_STATUS);
        } catch (Exception e) {
            logger.error("Can't get load auction", e);
            return new ArrayList<>();
        } finally {
            closeEntityManager(em);
        }
    }

    public List<LoadAuction> getLoadAuctionListForSaveLots() {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return loadAuctionDao.getLoadAuction(em, LoadStatus.RUN_LOTS_STATUS);
        } catch (Exception e) {
            logger.error("Can't get load auction", e);
            return new ArrayList<>();
        } finally {
            closeEntityManager(em);
        }
    }

    public List<LoadLot> getLoadLotForTransfer(Long laUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return loadAuctionDao.getLoadLotsForTransfer(em, laUnid);
        } catch (Exception e) {
            logger.error("Can't get load lots for transfer", e);
            return new ArrayList<>();
        } finally {
            closeEntityManager(em);
        }
    }

    public List<LoadFile> getLoadFilesByLoadLot(Long llUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return loadAuctionDao.getLoadFileByLoadLot(em, llUnid);
        } catch (Exception e) {
            logger.error("Can't get load files for transfer", e);
            return new ArrayList<>();
        } finally {
            closeEntityManager(em);
        }
    }

    public List<LoadRs> getLoadRsForTransfer(Long laUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return loadAuctionDao.getLoadRsForTransfer(em, laUnid);
        } catch (Exception e) {
            logger.error("Can't get load rs list for transfer", e);
            return new ArrayList<>();
        } finally {
            closeEntityManager(em);
        }
    }

    public Result<Long, String> save(LoadAuction loadAuction, AuctionDto auctionDto, Long subUnid) {

        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            em.getTransaction().begin();
            LoadAuctionSettings loadAuctionSettings = null;
            loadAuctionSettings = loadAuctionDao.getLoadAuctionSettings(em,
                    loadAuction.getLsUnid().getLsUnid(),
                    auctionDto.getTypeAuction().getNameInTemplate());
            if (loadAuctionSettings == null) {
                rollbackTransaction(em);
                return Result.error("Не удалось получить настройку, для загрузки информации по торгам");
            }

            Auction auction = auctionDao.createAuction(em, auctionDto, loadAuctionSettings.getAuctionUnid(), loadAuction);

            if (auction.getBaUnid() != null && auctionDto.getAuctionAsvOrderNum() != null && !auctionDto.getAuctionAsvOrderNum().trim().isEmpty()) {
                BlockAuction blockAuction = em.find(BlockAuction.class, auction.getBaUnid());
                if (blockAuction.getBaAsvOrderNum() == null  || blockAuction.getBaAsvOrderNum().trim().isEmpty()) {
                    blockAuction.setBaAsvOrderNum(auctionDto.getAuctionAsvOrderNum());
                    em.merge(blockAuction);
                }
            }

            SubjectHistory debtorSh = getDebtorSubjectHistory(em, auctionDto);
            loadAuction.setSubUnid(debtorSh == null ? subUnid : debtorSh.getSubUnid());

            loadAuction = loadAuctionDao.updateLoadAuction(em, auction, loadAuction, true);
            for (LoadLotDto lotDto : auctionDto.getLotList()) {
                loadAuctionDao.createLoadLot(em, loadAuction, auctionDto, lotDto);
            }
            for (LoadRsDto rsDto : auctionDto.getRsList()) {
                loadAuctionDao.createLoadRs(em, loadAuction, rsDto);
            }
            loadAuctionDao.updateLoadAuctionStatus(em, loadAuction, LoadStatus.RUN_LOTS_STATUS, null);
            em.getTransaction().commit();
            return Result.ok(subUnid);
        } catch (Exception e) {
            logger.error("Can't create auction", e);
            rollbackTransaction(em);
            return Result.error("Не удалось сохранить информацию о торгах.");
        } finally {
            closeEntityManager(em);
        }
    }

    public List<LoadFile> getLoadFilesByBaUnid(Long baUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return loadAuctionDao.getLoadFileByBaUnid(em, baUnid);
        } catch (Exception e) {
            logger.error("Can't get load files for transfer", e);/**/
            return new ArrayList<>();
        } finally {
            closeEntityManager(em);
        }
    }

    public Result<LoadFile, String> getLoadAsvTenderFileByBaUnid(Long baUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            Optional<LoadFile> resultLoadFile = loadAuctionDao.getLoadJsonFileByBaUnid(em, baUnid);
            if (resultLoadFile.isPresent()) {
                return Result.ok(resultLoadFile.get());
            } else {
                resultLoadFile = loadAuctionDao.getLoadXmlFileByBaUnid(em, baUnid);
                if (resultLoadFile.isPresent()) {
                    return Result.ok(resultLoadFile.get());
                } else {
                    return Result.error("LoadFile with baUnid = " + baUnid + "is null");
                }
            }
        } catch (Exception e) {
            logger.error("Can't get load asv tender file by baUnid = {}", baUnid, e);
            return Result.error("Can't get load asv tender file by baUnid = " + baUnid);
        } finally {
            closeEntityManager(em);
        }
    }

    public Optional<String> setEndLoadStatus(LoadAuction loadAuction, String laError) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            em.getTransaction().begin();
            LoadAuction savedLoadAuction = em.find(LoadAuction.class, loadAuction.getLaUnid());
            auctionDao.activateAuction(em, savedLoadAuction.getAuctionUnid().getAuctionUnid());
            loadAuctionDao.updateLoadAuctionStatus(em, savedLoadAuction, LoadStatus.END_LOAD_STATUS, laError);
            em.getTransaction().commit();
            return Optional.empty();
        } catch (Exception e) {
            logger.error("Can't update load auction status", e);
            rollbackTransaction(em);
            return Optional.of("Не удалось сохранить информацию о торгах.");
        } finally {
            closeEntityManager(em);
        }
    }

    public Optional<String> updateLoadStatus(LoadAuction loadAuction, Long lstUnid, String errorInfo) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            em.getTransaction().begin();
            loadAuctionDao.updateLoadAuctionStatus(em, loadAuction, lstUnid, errorInfo);
            em.getTransaction().commit();
            return Optional.empty();
        } catch (Exception e) {
            logger.error("Can't update load auction status", e);
            rollbackTransaction(em);
            return Optional.of("Не удалось сохранить информацию о торгах.");
        } finally {
            closeEntityManager(em);
        }
    }

    public Optional<String> setLoadLotSuccessResult(Long llUnid, Long lotUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            em.getTransaction().begin();
            LoadLot loadLot = em.find(LoadLot.class, llUnid);
            loadLot.setLotUnid(lotUnid);
            loadLot.setLlTransferResult(TransferResultConstant.TRANSFERED.getId());
            loadAuctionDao.updateLoadLot(em, loadLot);
            em.getTransaction().commit();
            return Optional.empty();
        } catch (Exception e) {
            logger.error("Can't update load auction status", e);
            rollbackTransaction(em);
            return Optional.of("Не удалось сохранить информацию о торгах.");
        } finally {
            closeEntityManager(em);
        }
    }

    public Optional<String> setLoadLotErrorResult(Long llUnid, String error) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            em.getTransaction().begin();
            LoadLot loadLot = em.find(LoadLot.class, llUnid);
            loadLot.setLlTransferResult(TransferResultConstant.ERROR.getId());
            loadLot.setLlTransferError(error);
            loadAuctionDao.updateLoadLot(em, loadLot);
            em.getTransaction().commit();
            return Optional.empty();
        } catch (Exception e) {
            logger.error("Can't update load auction status", e);
            rollbackTransaction(em);
            return Optional.of("Не удалось сохранить информацию о торгах.");
        } finally {
            closeEntityManager(em);
        }
    }

    public LoadAuction getLoadAuction(Long laUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return em.find(LoadAuction.class, laUnid);
        } catch (Exception e) {
            logger.error("Can't get load auction by laUnid = {}", laUnid, e);
            return null;
        } finally {
            closeEntityManager(em);
        }
    }

    public Boolean checkExistenceOfBlockAuctionWithTradeId(String tradeId) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            TypedQuery<BlockAuction> resultList = em.createQuery("select ba from BlockAuction ba where ba.indActual = 1 and " +
                            "ba.baAsvId = :baAsvId ", BlockAuction.class)
                    .setParameter("baAsvId", tradeId);
            return resultList.getResultList().isEmpty();
        } catch (Throwable ex) {
            logger.error("Can't checkExistenceOfBlockAuctionWithTradeId by tradeId = {}", tradeId, ex);
        } finally {
            closeEntityManager(em);
        }
        return false;
    }

    public Boolean checkExistenceOfAuctionWithTradeId(String tradeId) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            TypedQuery<Auction> resultList = em.createQuery("select a from Auction a where a.indActual = 1 and " +
                            "a.auctionAsvId = :auctionAsvId ", Auction.class)
                    .setParameter("auctionAsvId", tradeId);
            return resultList.getResultList().isEmpty();
        } catch (Exception e) {
            logger.error("Can't checkExistenceOfAuctionWithTradeId by tradeId = {}", tradeId, e);
        } finally {
            closeEntityManager(em);
        }
        return false;
    }

    public Result<Subject, String> getBankDebtorAsvByAuctionUnid(Long auctionUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            Subject resultSubject = loadAuctionDao.getBankDebtorAsvByAuctionUnid(em, auctionUnid);
            if (resultSubject != null) {
                return Result.ok(resultSubject);
            } else {
                return Result.error("BankDebtorAsv with auction unid = " + auctionUnid + "is null");
            }
        } catch (Exception e) {
            logger.error("Can't get BankDebtorAsv by auction unid = {}", auctionUnid, e);
            return Result.error("Can't get BankDebtorAsv by auction unid = " + auctionUnid);
        } finally {
            closeEntityManager(em);
        }
    }

    public Auction getAuctionByUnid(Long auctionUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return auctionDao.getAuction(em, auctionUnid);
        } catch (Exception e) {
            logger.error("Can't get auction by auiction unid = {}", auctionUnid, e);
            return null;
        } finally {
            closeEntityManager(em);
        }
    }

    public boolean isAsvAuction(Long auctionUnid) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            return loadAuctionDao.isAsvAuction(em, auctionUnid);
        } catch (Exception e) {
            logger.error("Can't define is ASV auction by auction unid = {}", auctionUnid, e);
            return false;
        } finally {
            closeEntityManager(em);
        }
    }


    private SubjectHistory buildSubjectHistory(AuctionDto auctionDto) {
        if (auctionDto.getTypeSubject() == null || TextUtils.isBlank(auctionDto.getDebitorSubName())) {
            return null;
        }
        SubjectHistory sh = new SubjectHistory();
        sh.setTypesUnid(auctionDto.getTypeSubject().getRadiusId());
        if (sh.getTypesUnid().equals(TypeSubject.FL.getUnid())) {
            addSubjectHistoryFlFields(sh, auctionDto);
        } else if (sh.getTypesUnid().equals(TypeSubject.YL.getUnid())) {
            addSubjectHistoryYlFields(sh, auctionDto);
        } else if (sh.getTypesUnid().equals(TypeSubject.IP.getUnid())) {
            addSubjectHistoryIpFields(sh, auctionDto);
        }
        sh.setShSubName(auctionDto.getDebitorSubName());
        sh.setShSubSname(auctionDto.getDebitorSubSName());
        sh.setShSubInn(auctionDto.getDebitorInn());
        sh.setShSubAddrLegal(auctionDto.getDebitorAddrLegal());
        sh.setShSubAddrFact(auctionDto.getDebitorAddrFact());


        return sh;
    }

    private void addSubjectHistoryFlFields(SubjectHistory sh, AuctionDto auctionDto) {
        sh.setShSubSnils(auctionDto.getDebitorPersonSnils());
        addSubjectHistoryFlNames(sh, auctionDto);
    }

    private void addSubjectHistoryYlFields(SubjectHistory sh, AuctionDto auctionDto) {
        sh.setShSubOgrn(auctionDto.getDebitorOgrn());
        sh.setShSubCodeKpp(auctionDto.getDebitorKpp());
        sh.setShSubManF(auctionDto.getDebitorPersonNameF());
        sh.setShSubManI(auctionDto.getDebitorPersonNameI());
        sh.setShSubManO(auctionDto.getDebitorPersonNameO());
    }

    private void addSubjectHistoryIpFields(SubjectHistory sh, AuctionDto auctionDto) {
        sh.setShSubOgrn(auctionDto.getDebitorOgrn());
        addSubjectHistoryFlNames(sh, auctionDto);
    }

    private void addSubjectHistoryFlNames(SubjectHistory sh, AuctionDto auctionDto) {
        sh.setShSubNameF(auctionDto.getDebitorPersonNameF());
        sh.setShSubNameI(auctionDto.getDebitorPersonNameI());
        sh.setShSubNameO(auctionDto.getDebitorPersonNameO());
    }

    public Result<Long, String> createBlockAuction(BlockAuctionDto blockAuctionDto, List<LoadAuctionDto> loadAuctions,
                                                   List<LoadFileDto> blockAuctionDocs) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getInstance().getEntityManager();
            em.getTransaction().begin();
            BlockAuction blockAuction = blockAuctionDao.createBlockAuction(em, blockAuctionDto);
            Long subUnid = null;
            for (LoadAuctionDto loadAuctionDto : loadAuctions) {
                AuctionDto auctionDto = loadAuctionDto.getAuctionDto();
                LoadAuctionSettings loadAuctionSettings = loadAuctionDao.getLoadAuctionSettings(em,
                        loadAuctionDto.getLsUnid(),
                        auctionDto.getTypeAuction().getRadiusTypeAuctionUnid(),
                        auctionDto.getTypeAuction().getRadiusStepForm().getId());
                if (loadAuctionSettings == null) {
                    rollbackTransaction(em);
                    return Result.error("Не удалось получить настройку, для загрузки информации по торгам");
                }
                LoadAuction loadAuction = loadAuctionDao.createLoadAuction(em, loadAuctionDto, loadAuctionSettings, blockAuction.getBaUnid());

                Auction auction = auctionDao.createAuction(em, auctionDto, loadAuctionSettings.getAuctionUnid(), loadAuction);

                if (subUnid == null) {
                    SubjectHistory debtorSh = getDebtorSubjectHistory(em, auctionDto);

                    subUnid = debtorSh == null
                            ? null
                            : debtorSh.getSubUnid();
                }

                loadAuction.setSubUnid(subUnid);

                loadAuction = loadAuctionDao.updateLoadAuction(em, auction, loadAuction, false);

                for (LoadLotDto lotDto : auctionDto.getLotList()) {
                    loadAuctionDao.createLoadLot(em, loadAuction, auctionDto, lotDto);
                }
                for (LoadRsDto rsDto : auctionDto.getRsList()) {
                    loadAuctionDao.createLoadRs(em, loadAuction, rsDto);
                }

                loadAuctionDao.updateLoadAuctionStatus(em, loadAuction, LoadStatus.RUN_LOTS_STATUS, null);
            }

            for (LoadFileDto fileDto : blockAuctionDocs) {
                loadAuctionDao.createLoadFilesByBaUnid(em, blockAuction.getBaUnid(), fileDto);
            }

            em.getTransaction().commit();
            return Result.ok(blockAuction.getBaUnid());
        } catch (Exception e) {
            logger.error("Can't create block auction", e);
            rollbackTransaction(em);
            return Result.error("Не удалось сохранить информацию о торгах.");
        } finally {
            closeEntityManager(em);
        }
    }

    private SubjectHistory getDebtorSubjectHistory(EntityManager em, AuctionDto auctionDto) {
        SubjectHistory debtorSh = buildSubjectHistory(auctionDto);
        if (debtorSh != null) {
            List<Subject> subjects = subjectUtils.findSubject(debtorSh);
            if (subjects.isEmpty()) {
                debtorSh = subjectDao.createSubjectFromSubjectHistory(em, debtorSh, paUnid);
            } else {
                debtorSh.setSubUnid(subjects.get(0).getSubUnid());
                debtorSh = subjectDao.createSubjectHistory(em, debtorSh, paUnid);
            }
        }
        return debtorSh;
    }
}
