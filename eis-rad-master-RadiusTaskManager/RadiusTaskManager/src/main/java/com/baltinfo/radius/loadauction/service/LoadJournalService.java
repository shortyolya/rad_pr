package com.baltinfo.radius.loadauction.service;

import com.baltinfo.radius.db.controller.LoadJournalController;
import com.baltinfo.radius.db.dto.AuctionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * <p>
 *     Сервис для журналирования загрузки торгов при экспорте из xml с FTP
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 20.02.2020
 */
public class LoadJournalService {
    private static Logger logger = LoggerFactory.getLogger(LoadJournalService.class);

    private final LoadJournalController loadJournalController;

    public LoadJournalService(LoadJournalController loadJournalController) {
        this.loadJournalController = Objects.requireNonNull(loadJournalController, "Can't get loadJournal controller");
    }

    public void updateLoadStatus(Long baUnid, String errorInfo, Long loadStatus) {
        try {
            loadJournalController.updateLoadStatus(baUnid, errorInfo, loadStatus);
        } catch (Exception e) {
            logger.error("Can't update LoadStatus in LoadJournal");
        }
    }

}
