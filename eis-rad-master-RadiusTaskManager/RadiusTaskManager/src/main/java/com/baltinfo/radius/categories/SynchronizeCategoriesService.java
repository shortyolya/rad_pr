package com.baltinfo.radius.categories;

import com.baltinfo.radius.bankruptcy.categories.SynchronizeCategoriesFromEisToBkrService;
import com.baltinfo.radius.db.constants.ExchangeProcStatus;
import com.baltinfo.radius.db.constants.ExchangeProcs;
import com.baltinfo.radius.db.controller.ExchangeProcController;
import com.baltinfo.radius.db.controller.SaleCategoryController;
import com.baltinfo.radius.db.model.ExchangeProcRun;
import com.baltinfo.radius.lotonline.categories.SynchronizeCategoriesFromEisToLOService;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Optional;

/**
 * @author Suvorina Aleksandra
 * @since 09.02.2021
 */
public class SynchronizeCategoriesService {

    private static final Logger logger = LoggerFactory.getLogger(SynchronizeCategoriesService.class);

    private final ExchangeProcController exchangeProcController;
    private final SaleCategoryController saleCategoryController;
    private final SynchronizeCategoriesFromEisToBkrService synchronizeBkrService;
    private final SynchronizeCategoriesFromEisToLOService synchronizeLoService;

    public SynchronizeCategoriesService(ExchangeProcController exchangeProcController,
                                        SaleCategoryController saleCategoryController,
                                        SynchronizeCategoriesFromEisToBkrService synchronizeBkrService,
                                        SynchronizeCategoriesFromEisToLOService synchronizeLoService) {
        this.exchangeProcController = exchangeProcController;
        this.saleCategoryController = saleCategoryController;
        this.synchronizeBkrService = synchronizeBkrService;
        this.synchronizeLoService = synchronizeLoService;
    }

    public void runProcedure() {
        Optional<ExchangeProcRun> exchangeProcRunOpt = exchangeProcController.getFirstNotStartedExchangeProcRun(ExchangeProcs.SYNCHRONIZE_CATEGORIES);
        if (!exchangeProcRunOpt.isPresent()) {
            return;
        }
        ExchangeProcRun exchangeProcRun = exchangeProcRunOpt.get();
        boolean result = changeProcedureStateToRunning(exchangeProcRun);
        if (!result) {
            return;
        }

        boolean hasErrors = false;

        Result<String, String> bkrResult = synchronizeBkrService.synchronize();
        String messageBkr = "Ход синхронизации категорий на ЭТП Банкротство: \n\n";
        if (bkrResult.isError()) {
            hasErrors = true;
            messageBkr += bkrResult.getError();
        } else {
            messageBkr += bkrResult.getResult();
        }
        saveProcedureInfo(exchangeProcRun, messageBkr);

        Result<String, String> loResult = synchronizeLoService.synchronize();
        String messageLo = "\n\nХод синхронизации категорий на ЭТП lot-online: \n\n";
        if (loResult.isError()) {
            hasErrors = true;
            messageLo += loResult.getError();
        } else {
            messageLo += loResult.getResult();
        }

        ExchangeProcStatus status = hasErrors ? ExchangeProcStatus.ERROR_RUNNING : ExchangeProcStatus.RUNNING;

        saveProcedureInfo(exchangeProcRun, messageLo, status.getCode());

        if (hasErrors) {
            return;
        }

        Result<Void, String> deleteResult = saleCategoryController.deleteCategories();
        String deleteMessage = "\n\n";
        if (deleteResult.isError()) {
            deleteMessage += "При удалении категорий в ЕИС РАД произошла ошибка: " + deleteResult.getError();
            status = ExchangeProcStatus.ERROR_RUNNING;
        } else {
            deleteMessage += "Категории успешно синхронизированы.";
            status = ExchangeProcStatus.FINISHED;
        }

        saveProcedureInfo(exchangeProcRun, deleteMessage, status.getCode());
    }

    private boolean changeProcedureStateToRunning(ExchangeProcRun exchangeProcRun) {
        exchangeProcRun.setEprRunDate(new Date());
        exchangeProcRun.setEprLoadStatus(ExchangeProcStatus.RUNNING.getCode());
        return exchangeProcController.saveExchangeProcRun(exchangeProcRun);
    }

    private boolean saveProcedureInfo(ExchangeProcRun exchangeProcRun, String details) {
        return saveProcedureInfo(exchangeProcRun, details, exchangeProcRun.getEprLoadStatus());
    }

    private boolean saveProcedureInfo(ExchangeProcRun exchangeProcRun, String details, Integer status) {
        String text = exchangeProcRun.getEprErrorText() == null
                ? ""
                : exchangeProcRun.getEprErrorText();
        text += details;
        exchangeProcRun.setEprErrorText(text);
        exchangeProcRun.setEprLoadStatus(status);
        return exchangeProcController.saveExchangeProcRun(exchangeProcRun);
    }

}
