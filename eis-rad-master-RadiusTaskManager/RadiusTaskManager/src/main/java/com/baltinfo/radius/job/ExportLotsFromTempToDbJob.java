package com.baltinfo.radius.job;

import com.baltinfo.radius.db.controller.LoadAuctionController;
import com.baltinfo.radius.db.controller.LotController;
import com.baltinfo.radius.db.controller.VLoadJournalController;
import com.baltinfo.radius.db.model.LoadAuction;
import com.baltinfo.radius.db.model.LoadFile;
import com.baltinfo.radius.db.model.LoadLot;
import com.baltinfo.radius.db.model.LoadRs;
import com.baltinfo.radius.db.model.LoadStatus;
import com.baltinfo.radius.db.model.Lot;
import com.baltinfo.radius.db.model.VLoadJournal;
import com.baltinfo.radius.loadauction.constants.FileTypeConstant;
import com.baltinfo.radius.loadauction.service.LoadJournalService;
import com.baltinfo.radius.loadauction.service.MessageAboutNewTenderService;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * </p>
 *
 * @author Lapenok Igor
 * @since 23.08.2018
 */
public class ExportLotsFromTempToDbJob {
    private static final Logger logger = LoggerFactory.getLogger(ExportLotsFromTempToDbJob.class);

    private final LoadAuctionController loadAuctionController;
    private final LotController lotController;
    private final LoadJournalService loadJournalService;
    private final MessageAboutNewTenderService messageAboutNewTenderService;
    private final VLoadJournalController vLoadJournalController;

    private static final String XLSX_EXT = "xlsx";

    public ExportLotsFromTempToDbJob(LoadAuctionController loadAuctionController, LotController lotController,
                                     LoadJournalService loadJournalService, MessageAboutNewTenderService messageAboutNewTenderService,
                                     VLoadJournalController vLoadJournalController) {
        this.loadAuctionController =
                Objects.requireNonNull(loadAuctionController, "Can't get load auction controller");
        this.lotController = Objects.requireNonNull(lotController, "Can't get lot controller");
        this.loadJournalService = Objects.requireNonNull(loadJournalService, "Can't get loadJournal ftp");
        this.messageAboutNewTenderService =
                Objects.requireNonNull(messageAboutNewTenderService, "Can't get messageAboutNewTender ftp");
        this.vLoadJournalController =
                Objects.requireNonNull(vLoadJournalController, "Can't get load journal controller");
    }

    @Scheduled(cron = "${job.cron.export.lots.to.db}")
    public void runJob() {
        Long startDate = new Date().getTime();
        logger.info("start ExportLotsFromTempToDbJob run job method at " + startDate);
        try {
            for (LoadAuction loadAuction : loadAuctionController.getLoadAuctionListForSaveLots()) {
                List<LoadFile> lotXlsxFiles = new ArrayList<>();
                List<LoadLot> loadLotList = loadAuctionController.getLoadLotForTransfer(loadAuction.getLaUnid());
                List<LoadRs> loadRsList = loadAuctionController.getLoadRsForTransfer(loadAuction.getLaUnid());
                List<String> laErrors = new ArrayList<>();
                if (loadAuction.getLaErrorInfo() != null) {
                    laErrors.add(loadAuction.getLaErrorInfo());
                }
                for (LoadLot loadLot : loadLotList) {
                    List<LoadFile> loadFileList = loadAuctionController.getLoadFilesByLoadLot(loadLot.getLlUnid());
                    lotXlsxFiles.addAll(loadFileList.stream()
                            .filter(lf -> lf.getLfType().equals(FileTypeConstant.DOCUMENT.getId())
                                    && lf.getLfFileExt().toLowerCase().equals(XLSX_EXT))
                            .collect(Collectors.toList()));
                    Result<Lot, String> createLotResult =
                            lotController.createLot(loadAuction.getAuctionUnid(), loadLot, loadRsList, loadFileList);
                    if (createLotResult.isSuccess()) {
                        loadAuctionController.setLoadLotSuccessResult(loadLot.getLlUnid(),
                                createLotResult.getResult().getLotUnid());
                    } else {
                        loadAuctionController.setLoadLotErrorResult(loadLot.getLlUnid(), createLotResult.getError());
                        laErrors.add(createLotResult.getError());
                    }
                }
                loadLotList = loadAuctionController.getLoadLotForTransfer(loadAuction.getLaUnid());
                if (loadLotList.isEmpty() && loadAuction.getAuctionUnid() != null) {
                    loadAuctionController.setEndLoadStatus(loadAuction, String.join("\n", laErrors));

                    if (loadAuction.getBaUnid() != null) {
                        VLoadJournal auctionFromJournal = vLoadJournalController.getLoadJournalByBaUnid(loadAuction.getBaUnid());
                        if (auctionFromJournal != null) {
                            if (!auctionFromJournal.getLstUnid().equals(LoadStatus.END_LOAD_STATUS)) {
                                messageAboutNewTenderService.createMessageEmail(auctionFromJournal);
                                List<LoadFile> resultOfLoadingFiles = loadAuctionController.getLoadFilesByBaUnid(loadAuction.getBaUnid());
                                Collection<LoadFile> uniqueLotXlsFiles = lotXlsxFiles.stream()
                                        .collect(Collectors.toMap(LoadFile::getLfFileName, lf -> lf))
                                        .values();
                                resultOfLoadingFiles.addAll(uniqueLotXlsFiles);

                                lotController.createDocFilesByBaUnid(resultOfLoadingFiles, loadAuction.getBaUnid());
                            }
                            loadJournalService.updateLoadStatus(loadAuction.getBaUnid(), null, LoadStatus.END_LOAD_STATUS);
                        }
                    }

                }
            }
        } catch (Exception ex) {
            logger.error("Error when run ExportLotsFromTempToDbJob ", ex);
        }
        logger.info("end ExportLotsFromTempToDbJob run job method at " + new Date().getTime() + " duration = " + (new Date().getTime() - startDate));
    }


}

