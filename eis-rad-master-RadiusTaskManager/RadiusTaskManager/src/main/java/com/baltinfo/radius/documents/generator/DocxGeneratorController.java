package com.baltinfo.radius.documents.generator;

import com.baltinfo.radius.db.constants.TypeDocConstant;
import com.baltinfo.radius.db.controller.AbstractController;
import com.baltinfo.radius.db.controller.TemplateController;
import com.baltinfo.radius.db.model.DocTemplate;
import com.baltinfo.radius.db.model.DocTemplatePart;
import com.baltinfo.radius.db.model.HtmlCorrection;
import com.baltinfo.radius.utils.HibernateUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DocxGeneratorController extends AbstractController {

    private static Log log = LogFactory.getLog(DocxGeneratorController.class);

    private final Docx4jUtils docx4jUtils;
    private final TemplateController templateController;

    public DocxGeneratorController(Docx4jUtils docx4jUtils, TemplateController templateController) {
        this.docx4jUtils = docx4jUtils;
        this.templateController = templateController;
    }

    /**
     * Формирует документ по шаблону
     *
     * @param dtUnid            - идентификатор шаблона
     * @param params            - параметры для SQL
     * @param radiusPathToFiles - путь к хранилищу файлов
     * @return возвращает сформированный документ
     * @throws DocxGeneratorException
     */
    public DocumentResult createReport(long dtUnid, Map<String, Object> params, String radiusPathToFiles) throws DocxGeneratorException {
        return createReport(dtUnid, params, radiusPathToFiles, DbSchema.MAIN);
    }

    /**
     * Формирует документ по шаблону
     *
     * @param dtUnid            - идентификатор шаблона
     * @param params            - параметры для SQL
     * @param radiusPathToFiles - путь к хранилищу файлов
     * @param dbSchema          - схема БД
     * @return возвращает сформированный документ
     * @throws DocxGeneratorException
     */
    public DocumentResult createReport(long dtUnid, Map<String, Object> params, String radiusPathToFiles, DbSchema dbSchema) throws DocxGeneratorException {
        Template template = null;
        try {
            template = loadTemplate(dtUnid, params, radiusPathToFiles, dbSchema);
            return docx4jUtils.createReport(template);
        } catch (Exception e) {
            String msg = "Error in createReport, error: " + e.getMessage();
            throw new DocxGeneratorException(msg, e);
        }
    }

    private Template loadTemplate(long dtUnid, Map<String, Object> params,
                                  String radiusPathToFiles, DbSchema dbSchema) throws DocxGeneratorException {
        Template result = new Template();

        try {
            DocTemplate dt = templateController.getDocTemplate(dtUnid);
            if (dt.getTdUnid().getTdUnid().equals(TypeDocConstant.DIGEST.getUnid())) {
                result.setMultiple(true);
            }
            result.setName(dt.getDtName());
            result.setImgFilePath(radiusPathToFiles);
            result.setMode(Template.MODE_DOCX4J);
            String path = radiusPathToFiles + File.separator + dt.getDfUnid().getDfFilePath();
            FileInputStream stream = new FileInputStream(path);
            result.setIs(stream);

            String sql = prepareSql(dt.getDtSql(), params);
            List<Map<String, String>> info = getMap(sql, dbSchema);
            result.setInfo(info);
            //set subreports
            List<DocTemplatePart> parts = templateController.getDocTemplatePartList(dtUnid);
            for (DocTemplatePart part : parts) {
                SubReportInfo sr = new SubReportInfo();
                sr.setName(part.getDtpCode());
                boolean addEmptyRow = part.getDtpIndList() == null || part.getDtpIndList().equals(new Short("0"));

                List<Map<String, String>> subReportInfoList = new ArrayList<>();
                List<List<Map<String, String>>> srInfo = new ArrayList<>(); //Используется для сопоставления отчётов и подотчётов в методе fillSubReport

                sr.setInfo(subReportInfoList);

                String srSql = prepareSql(part.getDtpSql(), params);
                for (Map<String, String> values : result.getInfo()) {
                    String additionalParameterStr = values.get("unid");
                    Long additionalParameter = additionalParameterStr != null
                            ? Long.parseLong(additionalParameterStr)
                            : null;
                    sr.getInfo().addAll(getList(srSql, additionalParameter, addEmptyRow));
                    srInfo.add(getList(srSql, additionalParameter, addEmptyRow));
                }
                sr.setIndList(part.getDtpIndList());
                result.getSubreports().add(sr);
                result.setSrInfo(srInfo);
            }

            List<HtmlCorrection> correctionList = templateController.getHtmlCorrectionList();
            Map<String, String> corrections = new LinkedHashMap<>();
            if (correctionList != null) {
                for (HtmlCorrection hc : correctionList) {
                    corrections.put(hc.getHcFrom(), hc.getHcTo() == null ? "" : hc.getHcTo());
                }
            }
            result.setHtmlCorrection(corrections);

        } catch (Exception ex) {
            throw new DocxGeneratorException("Template loading error: " + ex.getMessage(), ex);
        }
        return result;
    }

    private List<Map<String, String>> getMap(String sql, DbSchema dbSchema) throws DocxGeneratorException {
        if (sql == null || sql.isEmpty()) {
            return new ArrayList<>();
        }
        Connection conn = null;
        try {
            switch (dbSchema) {
                case MAIN:
                    conn = HibernateUtil.getInstance().getConnection();
                    break;
                case DEP:
                    conn = HibernateUtil.getInstance().getConnectionDep();
                    break;
                default:
                    conn = HibernateUtil.getInstance().getConnection();
            }

            try (PreparedStatement st = conn.prepareStatement(sql)) {
                try (ResultSet rs = st.executeQuery()) {
                    List<Map<String, String>> result = new ArrayList<>();
                    while (rs.next()) {
                        ResultSetMetaData rsMetaData = rs.getMetaData();
                        Map<String, String> map = new HashMap<>();
                        for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
                            Object obj = rs.getObject(i);
                            if (obj == null) {
                                obj = "";
                            }
                            map.put(rsMetaData.getColumnName(i), obj.toString());
                        }
                        result.add(map);
                    }
                    return result;
                }
            }
        } catch (Exception e) {
            log.error("Error in getMap: request = " + sql);
            throw new DocxGeneratorException("Error in getMap" + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
    }

    private List<Map<String, String>> getList(String sql,
                                              Long additionalArg, boolean addEmptyRow) throws DocxGeneratorException {
        Connection conn = null;
        try {
            conn = HibernateUtil.getInstance().getConnection();
            try (PreparedStatement st = conn.prepareStatement(sql)) {
                if (additionalArg != null) {
                    st.setLong(1, additionalArg);
                }
                try (ResultSet rs = st.executeQuery()) {
                    List<Map<String, String>> result = new ArrayList<>();
                    boolean noRows = true;
                    while (rs.next()) {
                        noRows = false;
                        ResultSetMetaData rsMetaData = rs.getMetaData();
                        Map<String, String> map = new HashMap<>();
                        for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
                            Object obj = rs.getObject(i);
                            if (obj == null) {
                                obj = "";
                            }
                            map.put(rsMetaData.getColumnName(i), obj.toString());
                        }
                        result.add(map);
                    }
                    if (noRows && addEmptyRow) {
                        ResultSetMetaData rsMetaData = rs.getMetaData();
                        Map<String, String> map = new HashMap<>();
                        for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
                            map.put(rsMetaData.getColumnName(i), "");
                        }
                        result.add(map);
                    }
                    return result;
                }
            }
        } catch (Exception e) {
            log.error("Error in getList: request = " + sql);
            throw new DocxGeneratorException("Error in getList" + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
    }

    private String prepareSql(String sql, Map<String, Object> params) {
        String replacedSql = sql;
        if (params != null && !params.isEmpty()) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd");
            for (String key : params.keySet()) {
                Object value = params.get(key);
                String valueStr = "";
                if (value instanceof Date) {
                    valueStr = "'" + df.format(value) + "'::date";
                } else {
                    valueStr = value.toString();
                }
                String tag = "&&&" + key + "&&&";
                replacedSql = replacedSql.replace(tag.toLowerCase(), valueStr)
                        .replace(tag.toUpperCase(), valueStr);
            }
        }
        return replacedSql;
    }

}
