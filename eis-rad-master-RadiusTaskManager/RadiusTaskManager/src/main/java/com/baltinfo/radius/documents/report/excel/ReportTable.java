package com.baltinfo.radius.documents.report.excel;


import com.baltinfo.radius.utils.Result;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Suvorina Aleksandra
 * @since 11.01.2019
 */
public class ReportTable {

    private static Logger logger = LoggerFactory.getLogger(ReportTable.class);

    private static final String HEADER_SUFFIX = "_header";
    private static final String BODY_SUFFIX = "_body";
    private static final String FOOTER_SUFFIX = "_footer";

    private final String tag;
    private final List<String> header;
    private final List<TableRow> rows;
    private final TableRow footer;
    private final Map<String, String> titleParams;
    private final ExcelCellStyleMode cellStyleMode;

    public ReportTable(String tag, List<String> header, List<TableRow> rows, TableRow footer, Map<String, String> titleParams) {
        this.tag = tag;
        this.header = header;
        this.rows = rows;
        this.footer = footer;
        this.titleParams = titleParams;
        this.cellStyleMode = ExcelCellStyleMode.FIRST_COLUMN;
    }

    public ReportTable(String tag, List<String> header, List<TableRow> rows, TableRow footer, Map<String, String> titleParams,
                       ExcelCellStyleMode cellStyleMode) {
        this.tag = tag;
        this.header = header;
        this.rows = rows;
        this.footer = footer;
        this.titleParams = titleParams;
        this.cellStyleMode = cellStyleMode;
    }

    public List<TableRow> getRows() {
        return rows;
    }

    public Result<Void, String> fill(XSSFSheet sheet) throws Exception {
        if (titleParams != null) {
            for (Row row : sheet) {
                for (String param : titleParams.keySet()) {
                    Optional<Cell> cell = findCellWithTitleParam(row, param);
                    cell.ifPresent(value -> fillParam(value, param, titleParams.get(param)));
                }
            }
        }
        Iterator<Row> iterator = sheet.iterator();
        if (header != null) {
            while (iterator.hasNext()) {
                Row row = iterator.next();
                Optional<Cell> cell = findCellWithTag(row, HEADER_SUFFIX);
                if (cell.isPresent()) {
                    fillHeader(row, cell.get());
                    break;
                }
            }
        }
        iterator = sheet.iterator();
        while (iterator.hasNext()) {
            Row row = iterator.next();
            Optional<Cell> cell = findCellWithTag(row, BODY_SUFFIX);
            if (cell.isPresent()) {
                fillBody(sheet, row, cell.get());
                break;
            }
        }
        if (footer != null) {
            iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                Optional<Cell> cell = findCellWithTag(row, FOOTER_SUFFIX);
                if (cell.isPresent()) {
                    fillFooter(row, cell.get());
                    break;
                }
            }
        }

        return Result.ok();
    }

    private void fillParam(Cell cell, String param, String value) {
        String cellValue = cell.getStringCellValue();
        cellValue = cellValue.replace("#{" + param + "}", value);
        cell.setCellValue(cellValue);
    }

    private Result<Void, String> fillBody(XSSFSheet sheet, Row row, Cell cell) throws UnsupportedEncodingException {
        CreationHelper createHelper = sheet.getWorkbook().getCreationHelper();
        int startRowNum = row.getRowNum();
        int startColumnIndex = cell.getColumnIndex();

        Map<Integer, CellStyle> cellStyles = new HashMap<>();
        if (rows.size() > 1) {
            if (cellStyleMode.equals(ExcelCellStyleMode.COLUMNS)) {
                for (int i = 0; i < rows.get(0).getCells().size(); i++) {
                    Cell cell1 = row.getCell(i + startColumnIndex);
                    cellStyles.put(i, cell1.getCellStyle());
                }
            } else {
                Cell cell1 = row.getCell(startColumnIndex);
                CellStyle cellStyle1 = cell1.getCellStyle();
                cellStyles.put(0, cellStyle1);
                Cell cell2 = row.getCell(startColumnIndex + 1);
                CellStyle cellStyle2 = cell2.getCellStyle();
                for (int i = 1; i < rows.get(0).getCells().size(); i++) {
                    cellStyles.put(i, cellStyle2);
                }
            }
        }
        sheet.removeRow(row);
        if (rows.size() > 1) {
            sheet.shiftRows(startRowNum, sheet.getLastRowNum(), rows.size() - 1);
        }
        int tableRowNum = 0;
        for (TableRow tableRow : rows) {
            Row newRow = sheet.createRow(startRowNum + tableRowNum);
            List<TableCell> cells = tableRow.getCells();
            int columnIndex = 0;
            for (TableCell tableCell : cells) {
                Cell newCell = newRow.createCell(startColumnIndex + columnIndex);
                if (tableCell.getType().equals(Double.class)) {

                    newCell.setCellValue(tableCell.getDoubleValue());
                } else if (tableCell.getType().equals(HyperlinkCell.class)) {
                    HyperlinkCell hyperlink = tableCell.getHyperlinkValue();
                    newCell.setCellValue(hyperlink.getValue());
                    Hyperlink link = createHelper.createHyperlink(HyperlinkType.URL);
                    link.setAddress(URLEncoder.encode(hyperlink.getAddress(), StandardCharsets.UTF_8.toString()));
                    newCell.setHyperlink(link);
                } else {
                    newCell.setCellValue(tableCell.getStringValue());
                }
                newCell.setCellStyle(cellStyles.get(columnIndex));
                columnIndex++;
            }
            tableRowNum++;
        }
        return Result.ok();
    }

    private void fillHeader(Row headerRow, Cell firstCell) {
        int startColumnIndex = firstCell.getColumnIndex();
        Cell headerCell1 = headerRow.getCell(startColumnIndex);
        headerCell1.setCellValue(header.get(0));
        Cell headerCell2 = headerRow.getCell(startColumnIndex + 1);
        headerCell2.setCellValue(header.get(1));
        CellStyle cellStyle = headerCell2.getCellStyle();
        int columnIndex = 2;
        for (String headerCellText : header.stream().skip(2).collect(Collectors.toList())) {
            Cell newCell = headerRow.getCell(startColumnIndex + columnIndex);
            if (newCell == null) {
                newCell = headerRow.createCell(startColumnIndex + columnIndex);
            }
            newCell.setCellValue(headerCellText);
            newCell.setCellStyle(cellStyle);
            columnIndex++;
        }
    }

    private void fillFooter(Row footerRow, Cell firstCell) {
        int startColumnIndex = firstCell.getColumnIndex();

        Map<Integer, CellStyle> cellStyles = new HashMap<>();
        if (cellStyleMode.equals(ExcelCellStyleMode.COLUMNS)) {
            for (int i = 0; i < footer.getCells().size(); i++) {
                Cell cell1 = footerRow.getCell(i + startColumnIndex);
                cellStyles.put(i, cell1.getCellStyle());
            }
        } else {
            Cell cell1 = footerRow.getCell(startColumnIndex);
            CellStyle cellStyle1 = cell1.getCellStyle();
            cellStyles.put(0, cellStyle1);
            Cell cell2 = footerRow.getCell(startColumnIndex + 1);
            CellStyle cellStyle2 = cell2.getCellStyle();
            for (int i = 1; i < footer.getCells().size(); i++) {
                cellStyles.put(i, cellStyle2);
            }
        }
        List<TableCell> cells = footer.getCells();
        int columnIndex = 0;
        for (TableCell tableCell : cells) {
            Cell newCell = footerRow.createCell(startColumnIndex + columnIndex);
            if (tableCell.getType().equals(Double.class)) {
                newCell.setCellValue(tableCell.getDoubleValue());
            } else {
                newCell.setCellValue(tableCell.getStringValue());
            }
            newCell.setCellStyle(cellStyles.get(columnIndex));
            columnIndex++;
        }
    }

    private Optional<Cell> findCellWithTag(Row row, String suffix) {
        String fullTag = "#{" + tag + suffix + "}";
        for (Cell cell : row) {
            if (cell.getCellTypeEnum().equals(CellType.STRING) && fullTag.equals(cell.getStringCellValue())) {
                return Optional.of(cell);
            }
        }
        return Optional.empty();
    }

    private Optional<Cell> findCellWithTitleParam(Row row, String param) {
        String fullTag = "#{" + param + "}";
        for (Cell cell : row) {
            if (cell.getCellTypeEnum().equals(CellType.STRING) && cell.getStringCellValue() != null && cell.getStringCellValue().contains(fullTag)) {
                return Optional.of(cell);
            }
        }
        return Optional.empty();
    }


}
