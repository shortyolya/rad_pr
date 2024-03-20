package com.baltinfo.radius.documents.report.excel;

import com.baltinfo.radius.db.controller.ReportController;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Map;

/**
 * @author Suvorina Aleksandra
 * @since 22.01.2019
 */
public interface ReportExecutor {

    ByteArrayOutputStream formReport(InputStream template, Map<String, Object> params) throws Exception;
    void setReportController(ReportController reportController);
}
