package com.baltinfo.radius.notification.paydoc;

import com.baltinfo.radius.db.controller.DocTemplateController;
import com.baltinfo.radius.db.controller.PayDocNotificationController;
import com.baltinfo.radius.db.model.DocTemplate;
import com.baltinfo.radius.db.model.Document;
import com.baltinfo.radius.db.model.LotonlineProfile;
import com.baltinfo.radius.db.model.dep.PayDoc;
import com.baltinfo.radius.db.model.dep.PayDocNotice;
import com.baltinfo.radius.documents.generator.CreatingDocumentService;
import com.baltinfo.radius.mailsender.MailSenderClient;
import com.baltinfo.radius.mailsender.MessagePack;
import com.baltinfo.radius.mailsender.MessageRecipient;
import com.baltinfo.radius.radapi.client.RadApiClient;
import com.baltinfo.radius.radapi.model.StringDto;
import com.baltinfo.radius.radapi.security.Token;
import com.baltinfo.radius.radapi.security.TokenService;
import com.baltinfo.radius.template.TemplateHelper;
import com.baltinfo.radius.utils.Result;
import com.baltinfo.radius.vitrina.VitrinaClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author aaa
 */
public class PayDocNotificationService {

    private static final Logger logger = LoggerFactory.getLogger(PayDocNotificationService.class);

    private final PayDocNotificationController payDocNotificationController;
    private final DocTemplateController docTemplateController;
    private final CreatingDocumentService creatingDocumentService;
    private final TokenService tokenService;
    private final RadApiClient radApiClient;
    private final VitrinaClient vitrinaClient;
    private final MailSenderClient mailSenderClient;

    private final Long payDocDocTemplateUnid;
    private final TemplateHelper templateHelper;
    private final String notificationThemeTemplate;
    private final String notificationTextTemplate;
    private final String radiusPathToFiles;


    public PayDocNotificationService(PayDocNotificationController payDocNotificationController,
                                     DocTemplateController docTemplateController,
                                     CreatingDocumentService creatingDocumentService,
                                     TokenService tokenService,
                                     RadApiClient radApiClient, VitrinaClient vitrinaClient,
                                     MailSenderClient mailSenderClient,
                                     Long payDocDocTemplateUnid,
                                     TemplateHelper templateHelper,
                                     String notificationThemeTemplate,
                                     String notificationTextTemplate, String radiusPathToFiles) {
        this.payDocNotificationController = payDocNotificationController;
        this.docTemplateController = docTemplateController;
        this.creatingDocumentService = creatingDocumentService;
        this.tokenService = tokenService;
        this.radApiClient = radApiClient;
        this.vitrinaClient = vitrinaClient;
        this.mailSenderClient = mailSenderClient;
        this.payDocDocTemplateUnid = payDocDocTemplateUnid;
        this.templateHelper = templateHelper;
        this.notificationThemeTemplate = notificationThemeTemplate;
        this.notificationTextTemplate = notificationTextTemplate;
        this.radiusPathToFiles = radiusPathToFiles;
    }

    public void runProcedure() {
        try {
            List<PayDocNotice> notices = payDocNotificationController.getNotSentPayDocNotice();
            DocTemplate docTemplate = docTemplateController.getDocTemplateByDtUnid(payDocDocTemplateUnid);
            if (docTemplate == null) {
                logger.error("Error runProcedure PayDocNotificationService. DocTemplate not found by dtUnid = {}", payDocDocTemplateUnid);
                return;
            }

            for (PayDocNotice notice : notices) {
                Result<Document, String> pdDocumentResult = creatingDocumentService.createPayDocDocument(docTemplate, notice.getPayDocUnid().getPayDocUnid());
                if (pdDocumentResult.isError()) {
                    notice.setPdnError(pdDocumentResult.getError());
                    notice.setPdnStatus(PayDocNotificationStatus.ERROR_SENDING.getId());
                    payDocNotificationController.savePayDocNotice(notice);
                    continue;
                }

                Document eisDocument = pdDocumentResult.getResult();
                File eisFile = new File(radiusPathToFiles + File.separator + eisDocument.getDfUnid().getDfFilePath());
                Result<DocumentDto, String> uploadResult = vitrinaClient.uploadFile(eisFile);
                if (uploadResult.isError()) {
                    notice.setPdnError(uploadResult.getError());
                    notice.setPdnStatus(PayDocNotificationStatus.ERROR_SENDING.getId());
                    payDocNotificationController.savePayDocNotice(notice);
                    continue;
                }

                Result<String, String> tokenResult = getToken();
                if (tokenResult.isError()) {
                    notice.setPdnError(tokenResult.getError());
                    notice.setPdnStatus(PayDocNotificationStatus.ERROR_SENDING.getId());
                    payDocNotificationController.savePayDocNotice(notice);
                    continue;

                }
                Result<StringDto, String> updateDocumentResult = radApiClient.uploadEisFile(tokenResult.getResult(), uploadResult.getResult(), eisDocument.getDfUnid().getDfUnid());
                if (updateDocumentResult.isError()) {
                    notice.setPdnError(updateDocumentResult.getError());
                    notice.setPdnStatus(PayDocNotificationStatus.ERROR_SENDING.getId());
                    payDocNotificationController.savePayDocNotice(notice);
                    continue;
                }

                Result<Void, String> sendMessageResult = sendMessage(notice, updateDocumentResult.getResult().getValue());
                if (sendMessageResult.isError()) {
                    notice.setPdnError(sendMessageResult.getError());
                    notice.setPdnStatus(PayDocNotificationStatus.ERROR_SENDING.getId());
                    payDocNotificationController.savePayDocNotice(notice);
                } else {
                    notice.setPdnError(null);
                    notice.setPdnStatus(PayDocNotificationStatus.SENT.getId());
                    payDocNotificationController.savePayDocNotice(notice);
                }
            }

        } catch (Exception ex) {
            logger.error("Error runProcedure PayDocNotificationService", ex);
        }
    }

    public Result<Void, String> sendMessage(PayDocNotice notice, String vitrinaFileUrl) {
        String text;
        String theme;
        try {
            text = formText(notice.getPayDocUnid().getPayDocLotNumber(), vitrinaFileUrl);
            theme = formTheme(notice.getPayDocUnid().getPayDocLotNumber());
        } catch (Exception e) {
            logger.warn("Can't sendMessage", e);
            return Result.error("Не удалось сформировать уведомление.");
        }

        PayDoc payDoc = notice.getPayDocUnid();
        MessagePack message = new MessagePack();
        message.setEmail(false);
        message.setCabinet(true);
        message.setDisterb(false);
        message.setSubject(theme);
        message.setText(text);
        List<MessageRecipient> messageRecipients = new ArrayList<>();
        if (payDoc.getPayDocProfileId() != null) {
            MessageRecipient tmp = new MessageRecipient();
            tmp.setLp(payDoc.getPayDocProfileId());
            messageRecipients.add(tmp);
        } else if (payDoc.getPayDocWbUnid() != null) {
            MessageRecipient tmp = new MessageRecipient();
            tmp.setBp(payDoc.getPayDocWbUnid());
            messageRecipients.add(tmp);
        } else {
            return Result.error("Отсутствует идентификатор получателя");
        }
        message.setTo(messageRecipients);

        return mailSenderClient.sendMessage(message);
    }

    public Result<String, String> getToken() {
        try {
            return tokenService.getOpenPartToken();
        } catch (Exception e) {
            logger.warn("Can't get user token", e);
            return Result.error("Не удалось получить токен пользователя");
        }
    }

    public String formTheme(String lotNumber) throws Exception {
        Map<String, Object> data = new HashMap<>();
        data.put("lotNumber", lotNumber);
        return templateHelper.formTextFromTemplate(notificationThemeTemplate, data);
    }

    public String formText(String lotNumber, String fileURL) throws Exception {
        Map<String, Object> data = new HashMap<>();
        data.put("fileURL", fileURL);
        data.put("lotNumber", lotNumber);
        return templateHelper.formTextFromTemplate(notificationTextTemplate, data);
    }


}
