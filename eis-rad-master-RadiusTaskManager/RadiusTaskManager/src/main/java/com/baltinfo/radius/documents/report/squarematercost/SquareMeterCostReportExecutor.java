package com.baltinfo.radius.documents.report.squarematercost;

import com.baltinfo.radius.db.controller.ReportController;
import com.baltinfo.radius.documents.report.excel.ExcelCellStyleMode;
import com.baltinfo.radius.documents.report.excel.ReportExecutor;
import com.baltinfo.radius.documents.report.excel.ReportTable;
import com.baltinfo.radius.documents.report.excel.ReportTableSheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class SquareMeterCostReportExecutor implements ReportExecutor {

    private ReportController reportController;
    private final Map<Integer, String> categories;
    private final String sheet8;
    private final String sheet8And9;
    private final String sheet23;
    private final String sheet24;


    public SquareMeterCostReportExecutor() {
        categories = new HashMap<>();
        // Особняки, ОСЗ, здания свободного назначения
        // Офисные здания, БЦ
        categories.put(0, "22, 18");
        // Торговые здания, ТРЦ
        categories.put(1, "19");
        // Гостиницы, пансионаты
        categories.put(2, "20");
        // Имущественные комплексы (земельные участки со зданиями)
        // Производственные объекты
        // Складские помещения
        // АЗС
        // Инженерная инфраструктура
        // Объекты незавершенного строительства
        categories.put(3, "40, 25, 90, 28, 29, 31");
        // Объекты спорта и ФОК
        // Объекты общепита: рестораны, кафе, столовые
        // Объекты стрит-ритейла
        categories.put(4, "27, 21, 24");
        // Нежилые встроенные помещения
        categories.put(5, "23");
        // Паркинги, гаражи, машино-места
        categories.put(6, "26");

        // Земельные участки для размещения/застройки:
        //- Административные здания
        //- Многоэтажная жилая застройка
        //- Малоэтажная жилая застройка
        //- Торговые объекты
        //- Гостиницы/базы отдыха
        //- Складские объекты
        //- Объекты промышленного назначения
        //- АЗС
        //- Инженерная инфраструктура и благоустройство
        //- Недра и месторождения
        //- ИЖС
        //- ДНП
        //- СНТ
        //- Сельхозназначение
        //- Паркинг и гаражи
        sheet8 = "3, 4, 5, 6, 7, 8, 10, 11, 13, 15";

        //- ИЖС
        //- ДНП
        //- СНТ
        //- Сельхозназначение
        //- Паркинг и гаражи
        sheet8And9 = "9, 88, 89, 12, 14";

        // Квартиры
        // Апартаменты
        // Комнаты
        categories.put(9, "34, 36, 35");

        // Дачи
        // Таунхаусы
        // Дома, коттеджи
        categories.put(10, "39, 37, 38");

        //Акции и доли предприятий
        sheet23 = "85";

        // Иное
        // Карьеры, месторождения, недра
        sheet24 = "32, 41, 16, 30";
    }

    @Override
    public ByteArrayOutputStream formReport(InputStream template, Map<String, Object> params) throws Exception {

        try (XSSFWorkbook xlsx = new XSSFWorkbook(template)) {
            // 11 листов - регион Москва
            String moskowCode = "77";
            form11Sheets(xlsx, moskowCode, 0);

            // 11 листов - регион Московская область
            String moskowRegionCode = "50";
            form11Sheets(xlsx, moskowRegionCode, 11);

            String sheet23WhereCaluse = " osc.sc_unid in (" + sheet23 + ")";
            fillSheet(xlsx, 22, new SquareMeterCostSheetCommon(sheet23WhereCaluse, reportController));
            String sheet24WhereCaluse = " osc.sc_unid in (" + sheet24 + ")";
            fillSheet(xlsx, 23, new SquareMeterCostSheetCommon(sheet24WhereCaluse, reportController));

            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                xlsx.write(out);
                return out;
            }
        }
    }

    @Override
    public void setReportController(ReportController reportController) {
        this.reportController = reportController;
    }

    private void form11Sheets(XSSFWorkbook xlsx, String regionCode, Integer offset) throws Exception {
        for (Integer sheet : categories.keySet()) {
            String whereCondition = "  addr.addr_region_code = '" + regionCode + "' " +
                    " and osc.sc_unid in (" + categories.get(sheet) + ")";
            fillSheet(xlsx, sheet + offset, new SquareMeterCostSheetCommon(whereCondition, reportController));
        }
        // Лист 8
        String whereCondition = " addr.addr_region_code = '" + regionCode + "' " +
                " and (osc.sc_unid in (" + sheet8 + ") or (o.obj_land_comman_sqr > 8000 and osc.sc_unid in (" + sheet8And9 + ")) )";
        fillSheet(xlsx, 7 + offset, new SquareMeterCostSheetCommon(whereCondition, reportController));

        // Лист 9
        whereCondition = " addr.addr_region_code = '" + regionCode + "' " +
                " and (o.obj_land_comman_sqr <= 8000 or o.obj_land_comman_sqr is null) and osc.sc_unid in (" + sheet8And9 + ") ";
        fillSheet(xlsx, 8 + offset, new SquareMeterCostSheetCommon(whereCondition, reportController));
    }

    private void fillSheet(XSSFWorkbook xlsx, int index, ReportTableSheet reportTableSheet) throws Exception {
        XSSFSheet sheet = xlsx.getSheetAt(index);
        ReportTable reportTable = new ReportTable("table", reportTableSheet.getHeader(),
                reportTableSheet.getRows(), reportTableSheet.getFooter(), reportTableSheet.getTitleParams(), ExcelCellStyleMode.COLUMNS);
        reportTable.fill(sheet);
    }
}
