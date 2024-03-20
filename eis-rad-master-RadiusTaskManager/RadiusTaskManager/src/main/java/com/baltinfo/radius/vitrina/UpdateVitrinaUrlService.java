package com.baltinfo.radius.vitrina;

import com.baltinfo.radius.db.constants.ExchangeProcStatus;
import com.baltinfo.radius.db.constants.ExchangeProcs;
import com.baltinfo.radius.db.constants.RunningDetailsStatus;
import com.baltinfo.radius.db.controller.ExchangeProcController;
import com.baltinfo.radius.db.controller.LotController;
import com.baltinfo.radius.db.controller.VitrinaController;
import com.baltinfo.radius.db.model.ExchangeProcRun;
import com.baltinfo.radius.db.model.Lot;
import com.baltinfo.radius.db.model.RunningDetails;
import com.baltinfo.radius.notification.impl.ManagerNotificationService;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Suvorina Aleksandra
 * @since 18.10.2021
 */
public class UpdateVitrinaUrlService {

    private static final Logger logger = LoggerFactory.getLogger(UpdateVitrinaUrlService.class);

    private final ExchangeProcController exchangeProcController;
    private final VitrinaController vitrinaController;
    private final VitrinaOuterLinkService vitrinaOuterLinkService;
    private final LotController lotController;
    private final ManagerNotificationService managerNotificationService;

    public UpdateVitrinaUrlService(ExchangeProcController exchangeProcController, VitrinaController vitrinaController,
                                   VitrinaOuterLinkService vitrinaOuterLinkService, LotController lotController,
                                   ManagerNotificationService managerNotificationService) {
        this.exchangeProcController = exchangeProcController;
        this.vitrinaController = vitrinaController;
        this.vitrinaOuterLinkService = vitrinaOuterLinkService;
        this.lotController = lotController;
        this.managerNotificationService = managerNotificationService;
    }

    public void runProcedure() {
        List<ExchangeProcRun> exchangeProcRunsFromBkr = exchangeProcController.getEPRSendWithoutFinishedReceive(ExchangeProcs.RECEIVE_CODES_FROM_BANKRUPTCY.getUnid(),
                ExchangeProcs.UPDATE_VITRINA_LINK.getUnid());

        for (ExchangeProcRun epr : exchangeProcRunsFromBkr) {
            List<RunningDetails> updateLinkRunningDetails = new ArrayList<>();
            List<RunningDetails> runningDetails = exchangeProcController.getRunningDetails(epr.getEprUnid());
            runningDetails = runningDetails.stream()
                    .filter(rd -> rd.getRdReceiveId() != null)
                    .collect(Collectors.toList());
            for (RunningDetails rd : runningDetails) {
                Result<Long, String> productIdResult = vitrinaController.getProductIdByBkrLotId(rd.getRdReceiveId());
                RunningDetails updateLinkRd = formRunningDetail(rd.getRdSourceId(), productIdResult);
                updateLinkRunningDetails.add(updateLinkRd);
            }

            List<String> errors = updateLinkRunningDetails.stream()
                    .map(RunningDetails::getRdErrorInfo)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            saveExchangeProcRun(epr.getEprSourceId(), updateLinkRunningDetails, errors);
            managerNotificationService.createTradePublishedNotification(epr.getEprSourceId());
        }

        List<ExchangeProcRun> exchangeProcRunsFromLo = exchangeProcController.getEPRSendWithoutFinishedReceive(ExchangeProcs.RECEIVE_CODES_FROM_LOT_ONLINE.getUnid(),
                ExchangeProcs.UPDATE_VITRINA_LINK.getUnid());

        for (ExchangeProcRun epr : exchangeProcRunsFromLo) {
            List<RunningDetails> updateLinkRunningDetails = new ArrayList<>();
            List<RunningDetails> runningDetails = exchangeProcController.getRunningDetails(epr.getEprUnid());
            runningDetails = runningDetails.stream()
                    .filter(rd -> rd.getRdReceiveId() != null)
                    .collect(Collectors.toList());
            for (RunningDetails rd : runningDetails) {
                Result<Long, String> productIdResult = vitrinaController.getProductIdByLoLotId(rd.getRdReceiveId());
                RunningDetails updateLinkRd = formRunningDetail(rd.getRdSourceId(), productIdResult);
                updateLinkRunningDetails.add(updateLinkRd);
            }

            List<String> errors = updateLinkRunningDetails.stream()
                    .map(RunningDetails::getRdErrorInfo)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            saveExchangeProcRun(epr.getEprSourceId(), updateLinkRunningDetails, errors);
            if (errors.isEmpty()) {
                managerNotificationService.createTradePublishedNotification(epr.getEprSourceId());
            }
        }
    }

    private RunningDetails formRunningDetail(Long sourceId, Result<Long, String> productIdResult) {
        RunningDetails updateLinkRd = new RunningDetails();
        updateLinkRd.setRdSourceId(sourceId);
        if (productIdResult.isError()) {
            updateLinkRd.setRdErrorInfo(productIdResult.getError());
            updateLinkRd.setRdResult(RunningDetailsStatus.ERROR.getCode());
            return updateLinkRd;
        }
        Long productId = productIdResult.getResult();
        updateLinkRd.setRdReceiveId(productId);

        Result<Void, String> updateLotLinkSiteResult = updateLotLinkSite(productId, sourceId);
        if (updateLotLinkSiteResult.isError()) {
            updateLinkRd.setRdErrorInfo(updateLotLinkSiteResult.getError());
            updateLinkRd.setRdResult(RunningDetailsStatus.ERROR.getCode());
            return updateLinkRd;
        }

        updateLinkRd.setRdResult(RunningDetailsStatus.SENT.getCode());
        return updateLinkRd;
    }

    /**
     * Сохранение результата выполнения операции по обновлению ссылки на витрину.
     *
     * Так как на витрине происходят задержки с обновлением данных, то по
     * идентификатору лоту на ЭТП не всегда можно получить идентификатор лота на витрине.
     * Поэтому, если приходит какая-либо ошибка по лоту, то в процедуре выставляем ошибку
     * и она должна выполниться снова
     *
     * @param sourceId
     * @param runningDetails
     * @param errors
     */
    private void saveExchangeProcRun(Long sourceId, List<RunningDetails> runningDetails, List<String> errors) {
        ExchangeProcRun updateExchangeProcRun = new ExchangeProcRun();
        updateExchangeProcRun.setEpUnid(ExchangeProcs.UPDATE_VITRINA_LINK.getUnid());
        updateExchangeProcRun.setEprSourceId(sourceId);
        updateExchangeProcRun.setEprRunDate(new Date());
        updateExchangeProcRun.setEprPaUnid(1L);
        updateExchangeProcRun.setEprLoadStatus(errors.isEmpty()
                ? ExchangeProcStatus.FINISHED.getCode()
                : ExchangeProcStatus.ERROR_RUNNING.getCode());
        updateExchangeProcRun.setEprErrorText(String.join("\n", errors));
        exchangeProcController.saveExchangeProcRunWithDetails(updateExchangeProcRun, runningDetails);
    }

    private Result<Void, String> updateLotLinkSite(Long productId, Long lotUnid) {
        try {
            Lot lot = lotController.getLot(lotUnid);
            if (lot.getIndActual() == null || !lot.getIndActual().equals(1) || lot.getLotIndCurrent() == null || !lot.getLotIndCurrent().equals(1)) {
                return Result.error("Лот не является текущим.");
            }
            String vitrinaLink = vitrinaOuterLinkService.formOuterLink(productId);
            return lotController.updateLotLinkSite(lotUnid, vitrinaLink);
        } catch (Exception ex) {
            logger.error("Error updateLotLinkSite by productId = {}, lotUnid = {}", productId, lotUnid, ex);
            return Result.error("Не удалось обновить ссылку на сайт");
        }
    }
}
