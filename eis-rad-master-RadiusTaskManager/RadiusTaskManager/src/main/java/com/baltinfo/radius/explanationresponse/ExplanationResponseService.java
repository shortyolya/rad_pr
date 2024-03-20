package com.baltinfo.radius.explanationresponse;

import com.baltinfo.radius.db.constants.ExchangeProcStatus;
import com.baltinfo.radius.db.constants.ExchangeProcs;
import com.baltinfo.radius.db.constants.TypeEventConstant;
import com.baltinfo.radius.db.controller.ExchangeProcController;
import com.baltinfo.radius.db.controller.ExplanationResponseController;
import com.baltinfo.radius.db.controller.LotController;
import com.baltinfo.radius.db.controller.NotificationController;
import com.baltinfo.radius.db.model.ExchangeProcRun;
import com.baltinfo.radius.db.model.ExplanationResponse;
import com.baltinfo.radius.db.model.Lot;
import com.baltinfo.radius.db.model.ObjectJPA;
import com.baltinfo.radius.radapi.client.RadApiClient;
import com.baltinfo.radius.radapi.model.ApiExplanationResponse;
import com.baltinfo.radius.radapi.security.TokenService;
import com.baltinfo.radius.template.TemplateHelper;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Suvorina Aleksandra
 * @since 23.01.2022
 */
public class ExplanationResponseService {

    private static final Logger logger = LoggerFactory.getLogger(ExplanationResponseService.class);

    private final RadApiClient radApiClient;
    private final TokenService tokenService;
    private final ExplanationResponseController explanationResponseController;
    private final ExchangeProcController exchangeProcController;
    private final LotController lotController;
    private final NotificationController notificationController;
    private final TemplateHelper templateHelper;
    private final String notificationThemeTemplate;
    private final String notificationTextTemplate;

    public ExplanationResponseService(RadApiClient radApiClient,
                                      TokenService tokenService, ExplanationResponseController explanationResponseController,
                                      ExchangeProcController exchangeProcController, LotController lotController,
                                      NotificationController notificationController,
                                      TemplateHelper templateHelper,
                                      String notificationThemeTemplate, String notificationTextTemplate) {
        this.radApiClient = radApiClient;
        this.tokenService = tokenService;
        this.explanationResponseController = explanationResponseController;
        this.exchangeProcController = exchangeProcController;
        this.lotController = lotController;
        this.notificationController = notificationController;
        this.templateHelper = templateHelper;
        this.notificationThemeTemplate = notificationThemeTemplate;
        this.notificationTextTemplate = notificationTextTemplate;
    }

    public void processNewExplanationResponses() {
        Result<String, String> tokenResult = tokenService.getOpenPartToken();
        if (tokenResult.isError()) {
            ExchangeProcRun epr = createErrorEpr(tokenResult.getError());
            exchangeProcController.saveExchangeProcRun(epr);
            return;
        }
        Result<List<ApiExplanationResponse>, String> exrResult = radApiClient.getUnansweredExplanationResponses(tokenResult.getResult());
        if (exrResult.isError()) {
            ExchangeProcRun epr = createErrorEpr(exrResult.getError());
            exchangeProcController.saveExchangeProcRun(epr);
            return;
        }
        List<ApiExplanationResponse> apiExplanationResponses = exrResult.getResult();
        List<String> errors = new ArrayList<>();
        for (ApiExplanationResponse apiExplanationResponse : apiExplanationResponses) {
            boolean alreadyExist = explanationResponseController.checkEprExistsByEtpId(apiExplanationResponse.getId());
            if (alreadyExist) {
                continue;
            }
            Optional<Lot> eisLotResult = lotController.getLotByEtpTenderIdAndLotNumber(apiExplanationResponse.getTenderId(), apiExplanationResponse.getLotNumber().longValue());
            if (!eisLotResult.isPresent()) {
                errors.add("Лот в ЕИС не найден. Id запроса на разъяснение " + apiExplanationResponse.getId()
                        + ", Id процедуры на ЭТП " + apiExplanationResponse.getTenderId()
                        + ", Id лота на ЭТП " + apiExplanationResponse.getLotId()
                        + ", номер лота " + apiExplanationResponse.getLotNumber() + ".");
                continue;
            }
            Lot eisLot = eisLotResult.get();

            ExplanationResponse newExr = new ExplanationResponse();
            newExr.setExrEtpId(apiExplanationResponse.getId());
            newExr.setExrQuestionText(apiExplanationResponse.getQuestionText());
            newExr.setExrQuestionTime(Date.from(apiExplanationResponse.getDateExplanationReceipt()));
            newExr.setLotUnid(eisLot.getLotUnid());
            newExr = explanationResponseController.createExplanationResponse(newExr);
            if (newExr == null) {
                errors.add("Ошибка при создании запроса на разъяснение в ЕИС. Id запроса на разъяснение " + apiExplanationResponse.getId()
                        + ", Id процедуры на ЭТП " + apiExplanationResponse.getTenderId()
                        + ", Id лота на ЭТП " + apiExplanationResponse.getLotId()
                        + ", номер лота " + apiExplanationResponse.getLotNumber()
                        + ", код объекта в ЕИС " + eisLot.getObjUnid().getObjCode() + "."
                );
                continue;
            }
            Result<Void, List<String>> notificationResult = createNotification(eisLot);
            if (notificationResult.isError()) {
                errors.add(notificationResult.getError() + " Id запроса на разъяснение в ЕИС " + apiExplanationResponse.getId()
                        + ", код объекта в ЕИС " + eisLot.getObjUnid().getObjCode() + ".");
            }
        }
        if (errors.isEmpty()) {
            ExchangeProcRun epr = createSuccessEpr();
            exchangeProcController.saveExchangeProcRun(epr);
        } else {
            ExchangeProcRun epr = createErrorEpr(String.join("\n", errors));
            exchangeProcController.saveExchangeProcRun(epr);
        }


    }

    private Result<Void, List<String>> createNotification(Lot lot) {
        List<String> errors = new ArrayList<>();
        try {
            ObjectJPA object = lot.getObjUnid();

            Map<String, Object> data = new HashMap<>();
            data.put("lot", lot);
            String notificationTheme = templateHelper.formTextFromTemplate(notificationThemeTemplate, data);
            String notificationText = templateHelper.formTextFromTemplate(notificationTextTemplate, data);


            boolean curatorNotificationRes = notificationController.createNotification(TypeEventConstant.EXPLANATION_REQUEST.getUnid(),
                    object.getParPaUnid(), notificationTheme, notificationText);
            if (!curatorNotificationRes) {
                errors.add("Ошибка при создании уведомления о запросе разъяснений для куратора.");
            }

            boolean managerNotificationRes = notificationController.createNotification(TypeEventConstant.EXPLANATION_REQUEST.getUnid(),
                    object.getPaManagerUnid(), notificationTheme, notificationText);
            if (!managerNotificationRes) {
                errors.add("Ошибка при создании уведомления о запросе разъяснений для менеджера продаж.");
            }
        } catch (Exception ex) {
            logger.error("Error createNotification", ex);
            errors.add("Ошибка при создании уведомлений о запросе разъяснений.");
        }
        if (errors.isEmpty()) {
            return Result.ok();
        }
        return Result.error(errors);
    }

    private ExchangeProcRun createSuccessEpr() {
        ExchangeProcRun epr = new ExchangeProcRun();
        epr.setEprLoadStatus(ExchangeProcStatus.FINISHED.getCode());
        epr.setEpUnid(ExchangeProcs.RECEIVE_EXPLANATION_RESPONSES.getUnid());
        epr.setEprRunDate(new Date());
        epr.setEprPaUnid(1L);
        return epr;
    }

    private ExchangeProcRun createErrorEpr(String errors) {
        ExchangeProcRun epr = new ExchangeProcRun();
        epr.setEprLoadStatus(ExchangeProcStatus.ERROR_RUNNING.getCode());
        epr.setEpUnid(ExchangeProcs.RECEIVE_EXPLANATION_RESPONSES.getUnid());
        epr.setEprErrorText(errors);
        epr.setEprRunDate(new Date());
        epr.setEprPaUnid(1L);
        return epr;
    }

}
