package com.baltinfo.radius.documents;

import com.baltinfo.radius.db.constants.DocGenerationTypes;
import com.baltinfo.radius.db.constants.DocTemplateFormTypes;
import com.baltinfo.radius.db.constants.DocumStatus;
import com.baltinfo.radius.db.constants.TypeTemplates;
import com.baltinfo.radius.db.controller.DocParamValueController;
import com.baltinfo.radius.db.controller.DocTemplateController;
import com.baltinfo.radius.db.controller.DocumentController;
import com.baltinfo.radius.db.controller.NotificationController;
import com.baltinfo.radius.db.model.DocParamValue;
import com.baltinfo.radius.db.model.DocTemplate;
import com.baltinfo.radius.db.model.Document;
import com.baltinfo.radius.documents.generator.CreatingDocumentService;
import org.joda.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

public class CreatingDocumentJob {
    private static final Logger logger = LoggerFactory.getLogger(CreatingDocumentJob.class);

    private final DocumentController documentController;
    private final DocParamValueController docParamValueController;
    private final CreatingDocumentService creatingDocumentService;
    private final DocTemplateController docTemplateController;
    private final NotificationController notificationController;

    public CreatingDocumentJob(DocumentController documentController, DocParamValueController docParamValueController,
                               CreatingDocumentService creatingDocumentService, DocTemplateController docTemplateController, NotificationController notificationController) {
        this.documentController = documentController;
        this.docParamValueController = docParamValueController;
        this.creatingDocumentService = creatingDocumentService;
        this.docTemplateController = docTemplateController;
        this.notificationController = notificationController;
    }

    @Scheduled(cron = "${job.cron.creating.document}")
    public void run() {
        logger.info("start run job method at {}", Instant.now());
        try {
            List<Document> documentList = documentController.getGeneratedDocuments(DocumStatus.ON_FORMATION.getStatuseId(), DocGenerationTypes.ASYNCHRONOUS.getDgtUnid());
            for (Document document : documentList) {
                document.setDocumStatus(DocumStatus.FORMING.getStatuseId());
                documentController.saveDocument(document);
                List<DocParamValue> docParamValueList = docParamValueController.getDpValueByDocUnid(document.getDocumUnid());
                DocTemplate docTemplate = docTemplateController.getDocTemplateByDtUnid(document.getDtUnid().getDtUnid());
                boolean isSuccessful = false;
                if (docTemplate.getTtUnid() == TypeTemplates.REPORT_TEMPLATE && docTemplate.getDtType() == DocTemplateFormTypes.JASPER_REPORTS) {
                    isSuccessful = creatingDocumentService.createReport(document, docParamValueList, docTemplate);
                } else if (docTemplate.getTtUnid() == TypeTemplates.REPORT_TEMPLATE && docTemplate.getDtType() == DocTemplateFormTypes.APACHE_POI) {
                    isSuccessful = creatingDocumentService.createPoiReport(document, docParamValueList, docTemplate);
                } else {
                    isSuccessful = creatingDocumentService.createDocument(document, docParamValueList, docTemplate);
                }

                notificationController.createDocumentFormNotification(document);
            }
        } catch (Exception ex) {
            logger.error("Error when run CreatingDocumentJob", ex);
        }
        logger.info("end run job method at {}", Instant.now());
    }
}

