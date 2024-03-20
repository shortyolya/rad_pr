package com.baltinfo.radius.loadauction.service;

import com.baltinfo.radius.db.controller.LoadAuctionController;
import com.baltinfo.radius.db.controller.LoadJournalController;
import com.baltinfo.radius.db.dto.BlockAuctionDto;
import com.baltinfo.radius.db.dto.LoadAuctionDto;
import com.baltinfo.radius.db.dto.LoadFileDto;
import com.baltinfo.radius.db.model.LoadJournal;
import com.baltinfo.radius.db.model.LoadStatus;
import com.baltinfo.radius.loadauction.ftp.TenderSource;
import com.baltinfo.radius.loadauction.model.Publication;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * Загрузка данных в БД, создание блочных торгов
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 20.01.2020
 */

public class ExportDtoToDb {

    private static final Logger logger = LoggerFactory.getLogger(ExportDtoToDb.class);

    private final XmlExportService xmlExportService;
    private final JsonExportService jsonExportService;
    private final LoadAuctionController loadAuctionController;
    private final PublicationConverterService publicationConverterService;
    private final LoadJournalController loadJournalController;


    public ExportDtoToDb(XmlExportService xmlExportService, JsonExportService jsonExportService, LoadAuctionController loadAuctionController,
                         PublicationConverterService publicationConverterService, LoadJournalController loadJournalController) {
        this.xmlExportService =
                Objects.requireNonNull(xmlExportService, "Can't get xml service ftp");
        this.jsonExportService =
                Objects.requireNonNull(jsonExportService, "Can't get jsonExportService");
        this.loadAuctionController =
                Objects.requireNonNull(loadAuctionController, "Can't get load auction controller");
        this.publicationConverterService =
                Objects.requireNonNull(publicationConverterService, "Can't get publicationConverterService");
        this.loadJournalController =
                Objects.requireNonNull(loadJournalController, "Can't get loadJournalController");
    }

    /**
     * Производит экспорт в БД из xml dto объектов, которые создаёт XmlExportService
     */
    public Result<Void, String> exportToDb(TenderSource tenderSource) {
        try {
            // Если есть json, грузим его. Если ошибка, пытаемся загрузить xml
            Result<Void, Void> loadJsonResult = processJsonSource(tenderSource);
            if (loadJsonResult.isSuccess()) {
                return Result.ok();
            }
            Result<Void, Void> loadXmlResult = processXmlSource(tenderSource);
            if (loadXmlResult.isSuccess()) {
                return Result.ok();
            }
            return Result.error("Не удалось загрузить торги. tenderSource = " + tenderSource.toString());
        } catch (Exception ex) {
            logger.error("Error exportToDb tenderSource = {}", tenderSource, ex);
            String errorMessage = "Произошла ошибка при загрузке файла " + tenderSource.toString() + ". Ошибка: " + ex.getMessage();
            saveErrorLoadJournal(new LoadJournal(), errorMessage);
            return Result.error(errorMessage);
        }
    }

    private Result<Void, Void> processJsonSource(TenderSource tenderSource) {
        try {
            if (tenderSource.getJsonFileSource() == null) {
                logger.warn("Файл json отсутствует. tenderSource = {}", tenderSource);
                return Result.error();
            }
            Result<Publication, String> resultExportJson = jsonExportService.loadJsonFromString(tenderSource.getJsonFileSource(),
                    tenderSource.getJsonFileName());
            if (resultExportJson.isError()) {
                saveErrorLoadJournal(new LoadJournal(), resultExportJson.getError());
                return Result.error();
            }
            Publication publication = resultExportJson.getResult();
            return processPublication(publication);
        } catch (Exception ex) {
            logger.error("Error processJsonSource tenderSource = {}", tenderSource, ex);
            String errorMessage = "Произошла ошибка при загрузке json файла " + tenderSource.toString() + ". Ошибка: " + ex.getMessage();
            saveErrorLoadJournal(new LoadJournal(), errorMessage);
            return Result.error();
        }
    }

    private Result<Void, Void> processXmlSource(TenderSource tenderSource) {
        try {
            if (tenderSource.getXmlFileSource() == null) {
                logger.warn("Файл xml отсутствует. tenderSource = {}", tenderSource);
                return Result.error();
            }
            Result<Publication, String> resultExportXml = xmlExportService.loadXmlFromString(tenderSource.getXmlFileSource(),
                    tenderSource.getXmlFileName());
            if (resultExportXml.isError()) {
                saveErrorLoadJournal(new LoadJournal(), resultExportXml.getError());
                return Result.error();
            }
            Publication publication = resultExportXml.getResult();
            return processPublication(publication);
        } catch (Exception ex) {
            logger.error("Error processXmlSource tenderSource = {}", tenderSource, ex);
            String errorMessage = "Произошла ошибка при загрузке xml файла " + tenderSource.toString() + ". Ошибка: " + ex.getMessage();
            saveErrorLoadJournal(new LoadJournal(), errorMessage);
            return Result.error();
        }
    }

    private Result<Void, Void> processPublication(Publication publication) {
        LoadJournal loadJournal = publicationConverterService.formLoadJournal(publication);

        BlockAuctionDto blockAuctionDto = publicationConverterService.formBlockAuction(publication);
        Result<List<LoadAuctionDto>, String> loadAuctionsResult = publicationConverterService.formLoadAuctions(publication);
        if (loadAuctionsResult.isError()) {
            saveErrorLoadJournal(loadJournal, loadAuctionsResult.getError());
            return Result.error();
        }
        List<LoadAuctionDto> loadAuctions = loadAuctionsResult.getResult();
        List<LoadFileDto> blockAuctionDocs = publicationConverterService.getDocsForTenders(publication);
        Result<Long, String> blockAuctionResult = loadAuctionController.createBlockAuction(blockAuctionDto, loadAuctions,
                blockAuctionDocs);
        if (blockAuctionResult.isError()) {
            saveErrorLoadJournal(loadJournal, blockAuctionResult.getError());
            return Result.error();
        }
        Long baUnid = blockAuctionResult.getResult();
        saveLoadJournal(loadJournal, baUnid);
        return Result.ok();
    }

    private void saveErrorLoadJournal(LoadJournal loadJournal, String error) {
        loadJournal.setLjResult(error);
        loadJournal.setLstUnid(LoadStatus.XLS_LOAD_ERROR_STATUS);
        loadJournal.setLjDate(new Date());
        loadJournalController.saveLoadJournal(loadJournal);
    }

    private void saveLoadJournal(LoadJournal loadJournal, Long baUnid) {
        loadJournal.setBaUnid(baUnid);
        loadJournal.setLstUnid(LoadStatus.RUN_LOTS_STATUS);
        loadJournal.setLjDate(new Date());
        loadJournalController.saveLoadJournal(loadJournal);
    }

}
