package com.baltinfo.radius.documents.report.excel;

import java.util.List;
import java.util.Map;

/**
 * @author Suvorina Aleksandra
 * @since 21.01.2019
 */
public interface ReportTableSheet {

    List<String> getHeader();

    List<TableRow> getRows();

    TableRow getFooter();

   default Map<String, String> getTitleParams() {
       return null;
   }
}
