package com.baltinfo.radius.documents.generator;

import com.baltinfo.radius.db.constants.DocumStatus;
import com.baltinfo.radius.db.constants.FormatLoads;
import com.baltinfo.radius.db.constants.TypeDatabase;
import com.baltinfo.radius.db.constants.TypeDocConstant;
import com.baltinfo.radius.db.constants.TypeDocTemplateParams;
import com.baltinfo.radius.db.controller.DocumentController;
import com.baltinfo.radius.db.controller.ReportController;
import com.baltinfo.radius.db.model.DocFile;
import com.baltinfo.radius.db.model.DocParamValue;
import com.baltinfo.radius.db.model.DocTemplate;
import com.baltinfo.radius.db.model.Document;
import com.baltinfo.radius.documents.report.excel.ReportExecutor;
import com.baltinfo.radius.utils.HibernateUtil;
import com.baltinfo.radius.utils.Result;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.oasis.JROdsExporter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOdsReportConfiguration;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreatingDocumentService {

    private static final Logger logger = LoggerFactory.getLogger(CreatingDocumentService.class);

    private final DocxGeneratorController docxGeneratorController;
    private final DocumentController documentController;
    private final ReportController reportController;
    private final String radiusPathToFiles;


    public CreatingDocumentService(DocxGeneratorController docxGeneratorController, DocumentController documentController, ReportController reportController, String radiusPathToFiles) {
        this.docxGeneratorController = docxGeneratorController;
        this.documentController = documentController;
        this.reportController = reportController;
        this.radiusPathToFiles = radiusPathToFiles;
    }

    public boolean createDocument(Document document, List<DocParamValue> docParamValueList, DocTemplate docTemplate) {
        byte[] buf = null;
        DocumentResult documentResult = null;
        try {
            Map<String, Object> params = createParams(docParamValueList);
            documentResult = docxGeneratorController.createReport(docTemplate.getDtUnid(), params, radiusPathToFiles);
            buf = documentResult.getBuf();
            createFile(document, docTemplate, buf, "doc-", ".docx");
            if (documentResult != null && !documentResult.getInvalidObjectList().isEmpty()) {
                String message = "Документ сформирован. По следующим объектам возникли ошибки при формировании HTML описания: "
                        + String.join(", ", documentResult.getInvalidObjectList());
                document.setDocumFormResult(message);
                logger.info(message);
            }
            document.setDocumStatus(DocumStatus.FORMATION_COMPLETED.getStatuseId());
            documentController.saveDocument(document);
            return true;
        } catch (DocxGeneratorException ex) {
            document.setDocumStatus(DocumStatus.FORMATION_ERROR.getStatuseId());
            document.setDocumFormResult(ex.getMessage());
            documentController.saveDocument(document);
            logger.error("Error createDocument by documUnid = {}", document.getDocumUnid(), ex);
            return false;
        }
    }

    public boolean createReport(Document document, List<DocParamValue> docParamValueList, DocTemplate docTemplate) {
        Map<String, Object> params = createParams(docParamValueList);
        try (Connection conn = getReportConnection(docTemplate);
             FileInputStream stream = new FileInputStream(new File(radiusPathToFiles + File.separator + docTemplate.getDfUnid().getDfFilePath()));
             ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            byte[] buf;
            conn.setAutoCommit(false);

            JasperReport jasperReport = JasperCompileManager.compileReport(stream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conn);

            Long flUnid = docTemplate.getFlUnid().getFlUnid();
            if (flUnid == FormatLoads.PDF) {
                buf = JasperExportManager.exportReportToPdf(jasperPrint);
                createFile(document, docTemplate, buf, "report-", ".pdf");
            } else if (flUnid == FormatLoads.DOCX) {
                JRDocxExporter exporter = new JRDocxExporter();
                exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(os));
                exporter.exportReport();

                buf = os.toByteArray();
                createFile(document, docTemplate, buf, "report-", ".docx");
            } else if (flUnid == FormatLoads.XLSX) {
                JRXlsxExporter exporter = new JRXlsxExporter();
                exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(os));
                SimpleXlsxReportConfiguration config = new SimpleXlsxReportConfiguration();
                config.setOnePagePerSheet(false);
                config.setDetectCellType(true);
                config.setRemoveEmptySpaceBetweenColumns(true);
                config.setRemoveEmptySpaceBetweenRows(true);
                exporter.setConfiguration(config);
                exporter.exportReport();

                buf = os.toByteArray();
                createFile(document, docTemplate, buf, "report-", ".xlsx");
            } else if (flUnid == FormatLoads.HTML) {
                DocFile df = new DocFile();
                document.setDfUnid(df);
                document.setDocumDate(new Date());
                document.setDocumName(docTemplate.getDtName());
                document.setDocumIndCheck(false);

                File dir = new File(radiusPathToFiles + File.separator + new SimpleDateFormat("yyyyMMdd").format(new Date()));
                if (!dir.exists()) {
                    dir.mkdir();
                }
                File file = File.createTempFile("report-", ".html", dir);
                JasperExportManager.exportReportToHtmlFile(jasperPrint, file.getPath());

                df.setDfExt("xlsx");
                df.setDfSize((int) file.length());
                df.setDfFilePath(dir.getName() + File.separator + file.getName());
                df.setDfNameDb(file.getName());
                df.setDfName(docTemplate.getDtName().concat(".html"));
                df.setDfLoadDate(new Date());
                documentController.saveDocument(document, df);
            } else if (flUnid == FormatLoads.PPTX) {
                JRPptxExporter exporter = new JRPptxExporter();
                exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(os));
                exporter.exportReport();

                buf = os.toByteArray();
                createFile(document, docTemplate, buf, "report-", ".pptx");
            } else if (flUnid == FormatLoads.ODT) {
                JROdtExporter exporter = new JROdtExporter();
                exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(os));
                exporter.exportReport();

                buf = os.toByteArray();
                createFile(document, docTemplate, buf, "report-", ".odt");
            } else if (flUnid == FormatLoads.ODS) {
                JROdsExporter exporter = new JROdsExporter();
                exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(os));
                SimpleOdsReportConfiguration config = new SimpleOdsReportConfiguration();
                config.setOnePagePerSheet(false);
                config.setDetectCellType(true);
                exporter.setConfiguration(config);
                exporter.exportReport();

                buf = os.toByteArray();
                createFile(document, docTemplate, buf, "report-", ".ods");
            }

            document.setDocumStatus(DocumStatus.FORMATION_COMPLETED.getStatuseId());
            documentController.saveDocument(document);
            return true;
        } catch (Exception ex) {
            document.setDocumStatus(DocumStatus.FORMATION_ERROR.getStatuseId());
            document.setDocumFormResult(ex.getMessage());
            documentController.saveDocument(document);
            logger.error("Error creatReport by documUnid = {}", document.getDocumUnid(), ex);
            return false;
        }
    }

    public boolean createPoiReport(Document document, List<DocParamValue> docParamValueList, DocTemplate docTemplate) {
        if (docTemplate.getDtCallStr() == null || docTemplate.getDtCallStr().isEmpty()) {
            document.setDocumStatus(DocumStatus.FORMATION_ERROR.getStatuseId());
            document.setDocumFormResult("Отсутствует строка параметров");
            documentController.saveDocument(document);
            logger.error("Error create poi report by dtUnid = {}: docTemplate dtCallStr is null or empty", docTemplate.getDtUnid());
        }
        DocFile docFile = docTemplate.getDfUnid();
        if (docFile == null) {
            document.setDocumStatus(DocumStatus.FORMATION_ERROR.getStatuseId());
            document.setDocumFormResult("Отсутствует файл шаблона");
            documentController.saveDocument(document);
            logger.error("Error create poi report by dtUnid = {}: template docFile is null", docTemplate.getDtUnid());
        }

        Map<String, Object> params = createParams(docParamValueList);

        String filePath = radiusPathToFiles + File.separator + docTemplate.getDfUnid().getDfFilePath();
        try (FileInputStream stream = new FileInputStream(filePath)) {
            Class<?> clazz = Class.forName(docTemplate.getDtCallStr());
            ReportExecutor reportExecutor = (ReportExecutor) clazz.newInstance();
            reportExecutor.setReportController(reportController);
            ByteArrayOutputStream outputStream = reportExecutor.formReport(stream, params);
            byte[] buf = outputStream.toByteArray();
            String ext = "." + docTemplate.getFlUnid().getFlCode();
            createFile(document, docTemplate, buf, "report-", ext);

            document.setDocumStatus(DocumStatus.FORMATION_COMPLETED.getStatuseId());
            documentController.saveDocument(document);
            return true;
        } catch (Exception e) {
            document.setDocumStatus(DocumStatus.FORMATION_ERROR.getStatuseId());
            document.setDocumFormResult(e.getMessage());
            documentController.saveDocument(document);
            logger.error("Error create poi report by dtUnid = {}", docTemplate.getDtUnid(), e);
            return false;
        }
    }

    private Map<String, Object> createParams(List<DocParamValue> docParamValueList) {
        Map<String, Object> params = new HashMap<>();
        for (DocParamValue docParamValue : docParamValueList) {
            String key = docParamValue.getDtParamUnid().getDtParamName();
            long tdtpUnid = docParamValue.getDtParamUnid().getTdtpUnid().getTdtpUnid();
            if (tdtpUnid == TypeDocTemplateParams.TDTP_STRING || tdtpUnid == TypeDocTemplateParams.TDTP_ARRAY) {
                params.put(key, docParamValue.getDpvValue());
            } else if (tdtpUnid == TypeDocTemplateParams.TDTP_NUMBER) {
                params.put(key, Long.parseLong(docParamValue.getDpvValue()));
            } else if (tdtpUnid == TypeDocTemplateParams.TDTP_DECIMAL) {
                params.put(key, new BigDecimal(docParamValue.getDpvValue()));
            } else if (tdtpUnid == TypeDocTemplateParams.TDTP_DATE) {
                params.put(key, new Date(Long.parseLong(docParamValue.getDpvValue())));
            } else if (tdtpUnid == TypeDocTemplateParams.TDTP_DICT) {
                params.put(key, Long.parseLong(docParamValue.getDpvValue()));
            } else if (tdtpUnid == TypeDocTemplateParams.TDTP_DICT_STR) {
                params.put(key, docParamValue.getDpvValue());
            }
        }
        return params;
    }

    private void createFile(Document document, DocTemplate docTemplate, byte[] buf, String prefix, String suffix) throws DocxGeneratorException {
        try {
            DocFile df = new DocFile();
            document.setDfUnid(df);
            document.setDocumDate(new Date());
            document.setDocumName(docTemplate.getDtName());
            document.setDocumIndCheck(false);

            File dir = new File(radiusPathToFiles + File.separator + new SimpleDateFormat("yyyyMMdd").format(new Date()));
            if (!dir.exists()) {
                dir.mkdir();
            }
            File file = File.createTempFile(prefix, suffix, dir);

            try (OutputStream output = new BufferedOutputStream(Files.newOutputStream(file.toPath()))) {
                output.write(buf);
            }
            df.setDfExt(suffix.substring(1));
            df.setDfSize(buf.length);

            df.setDfFilePath(dir.getName() + File.separator + file.getName());
            df.setDfNameDb(file.getName());
            df.setDfName(docTemplate.getDtName().concat(suffix));
            df.setDfLoadDate(new Date());

            documentController.saveDocument(document, df);
        } catch (Exception ex) {
            logger.error("Error in runProcedure in createFile by documUnid = {}", document.getDocumUnid(), ex);
            throw new DocxGeneratorException("Error in runProcedure in createFile by documUnid" + document.getDocumUnid(), ex);
        }
    }

    private Connection getReportConnection(DocTemplate docTemplate) {
        try {
            switch (docTemplate.getDtTypeDb()) {
                case TypeDatabase.EIS:
                    return HibernateUtil.getInstance().getConnection();
                case TypeDatabase.BKR:
                    return HibernateUtil.getInstance().getConnectionProviderBKR().getConnection();
                case TypeDatabase.LO:
                    return HibernateUtil.getInstance().getConnectionProviderLO().getConnection();
            }
        } catch (Exception ex) {
            logger.error("Error in getReportConnection:", ex);
        }
        return HibernateUtil.getInstance().getConnection();
    }

    public Result<Document, String> createPayDocDocument(DocTemplate docTemplate, Long payDocUnid) {
        try {
            Document document = new Document();
            document.setTdUnid(docTemplate.getTdUnid());
            document.setDocumPayDocId(payDocUnid);

            Map<String, Object> params = new HashMap<>();
            params.put("payDocUnid", payDocUnid);

            DocumentResult docResult = docxGeneratorController.createReport(docTemplate.getDtUnid(), params, radiusPathToFiles, DbSchema.DEP);
            if (docResult.getBuf() == null) {
                return Result.error("Не удалось сформировать документ по шаблону");
            }
            byte[] buf = docResult.getBuf();

            document = documentController.createDocument(document);
            createFile(document, docTemplate, buf, "doc-", ".docx");
            return Result.ok(document);
        } catch (Exception ex) {
            logger.error("Error generatePayDoc by payDocUnid = {}", payDocUnid, ex);
            return Result.error("Ошибка при формировании документа по шаблону");
        }
    }
}
