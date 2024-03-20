package com.baltinfo.radius.job;

import com.baltinfo.radius.db.constants.TypeAuctionConstant;
import com.baltinfo.radius.db.controller.LoadAuctionController;
import com.baltinfo.radius.db.dto.AuctionDto;
import com.baltinfo.radius.db.model.LoadAuction;
import com.baltinfo.radius.db.model.LoadStatus;
import com.baltinfo.radius.utils.Result;
import com.baltinfo.radius.xls.XlsExportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;
import java.util.Date;
import java.util.Objects;

/**
 * <p>
 * Задание на загрузку данных из файла шаблона в промежуточную БД
 * </p>
 *
 * @author Lapenok Igor
 * @since 20.08.2018
 */
public class XlsExportToTempJob {

    private static final Logger logger = LoggerFactory.getLogger(XlsExportToTempJob.class);

    private final XlsExportService xlsExportService;
    private final LoadAuctionController loadAuctionController;
    private final String xlsTemplatePath;

    public XlsExportToTempJob(XlsExportService xlsExportService, LoadAuctionController loadAuctionController, String xlsTemplatePath) {
        this.xlsExportService = Objects.requireNonNull(xlsExportService, "Can't get xls service ftp");
        this.loadAuctionController =
                Objects.requireNonNull(loadAuctionController, "Can't get load auction controller");
        this.xlsTemplatePath = Objects.requireNonNull(xlsTemplatePath, "Can't get xml template path");
    }

    @Scheduled(cron = "${job.cron.xls.export.to.temp}")
    public void runJob() {
        Long startDate = new Date().getTime();
        logger.info("start run job method at {}", startDate);
        try {
            for (LoadAuction loadAuction : loadAuctionController.getLoadAuctionListForRun()) {
                String path = xlsTemplatePath + File.separator + loadAuction.getLaFilePath();
                logger.info("start load xlsx from file = {}; duration = {}", path, (new Date().getTime() - startDate));
                Result<AuctionDto, String> exportResult = xlsExportService.loadFromFile(path);
                if (exportResult.isError()) {
                    loadAuctionController.updateLoadStatus(loadAuction, LoadStatus.XLS_LOAD_ERROR_STATUS, exportResult.getError());
                    return;
                }
                logger.info("after load xlsx file; duration = {}", (new Date().getTime() - startDate));
                AuctionDto auctionDto = exportResult.getResult();
                if (loadAuction.getBaUnid() != null
                        && (loadAuction.getLaStageNum().equals(1) || loadAuction.getLaStageNum().equals(2))
                        && auctionDto.getTypeAuction().equals(TypeAuctionConstant.PUBLIC)) {
                    loadAuctionController.updateLoadStatus(loadAuction, LoadStatus.XLS_LOAD_ERROR_STATUS, "Торги первого и второго этапа не должны быть публичным предложением.");
                    return;
                }
                Result<Long, String> saveResult = loadAuctionController.save(loadAuction, auctionDto, loadAuction.getSubUnid());
                if (saveResult.isError()) {
                    loadAuction = loadAuctionController.getLoadAuction(loadAuction.getLaUnid());
                    loadAuctionController.updateLoadStatus(loadAuction, LoadStatus.TEMP_DB_SAVE_ERROR_STATUS, exportResult.getError());
                }
                logger.info("after save in db; duration = {}", (new Date().getTime() - startDate));
            }
        } catch (Exception ex) {
            logger.error("Error when run XlsExportToTempJob", ex);
        } finally {
            logger.info("end run job method at {}; duration = {}", new Date().getTime(), (new Date().getTime() - startDate));
        }
    }


}
