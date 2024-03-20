package com.baltinfo.radius.documents.report.squarematercost;

import com.baltinfo.radius.db.controller.ReportController;
import com.baltinfo.radius.documents.report.excel.HyperlinkCell;
import com.baltinfo.radius.documents.report.excel.ReportTableSheet;
import com.baltinfo.radius.documents.report.excel.TableCell;
import com.baltinfo.radius.documents.report.excel.TableRow;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SquareMeterCostSheetCommon implements ReportTableSheet {

    private final String sql = "SELECT o.obj_unid,\n" +
            "       o.obj_code,                                                                                         -- Код объекта\n" +
            "       TO_CHAR(o.obj_sale_date, 'dd.MM.yyyy')                                      obj_sale_date,          -- Дата продажи\n" +
            "       TO_CHAR(o.obj_transfer_date, 'dd.MM.yyyy')                                  obj_transfer_date,      -- Дата передачи в ДП\n" +
            "       ta.type_auction_name,                                                                               -- Вид торгов\n" +
            "       l.lot_auction_theme,                                                                                -- Предмет торгов\n" +
            "       'http://eis.lot-online.ru/account-obj-card.xhtml?objUnid=' || o.obj_unid    link,                   -- ссылка на ЕИС\n" +
            "       l.lot_link_site,                                                                                    -- ссылка на ЭТП\n" +
            "       sc.sc_name,                                                                                         -- Классификатор ДП\n" +
            "       addr.addr_region_name,                                                                              -- регион\n" +
            "       addr.addr_address,                                                                                  -- адрес\n" +
            "       lot_count.lot_count,                                                                                -- Кол-во проведенных торгов, шт. (в т.ч. назначенные)\n" +
            "       ROUND(start_cost.cost_value_rub, 0)                                         start_cost_value_rub,   -- Начальная цена\n" +
            "       ROUND(COALESCE(deal_sale_cost.cost_value_rub, end_cost.cost_value_rub), 0) sale_cost_value_rub,    -- Цена продажи\n" +
            "       CASE\n" +
            "           WHEN (start_cost.cost_value_rub IS NULL OR start_cost.cost_value_rub = 0) THEN NULL\n" +
            "           ELSE ROUND(((COALESCE(deal_sale_cost.cost_value_rub, end_cost.cost_value_rub) /\n" +
            "                       start_cost.cost_value_rub) - 1) * 100,\n" +
            "                      0) END AS                                                    exces,                  -- Превышение  над начальной ценой (цена продажи/начальная цена)\n" +
            "       l.lot_appl_count,                                                                                   -- Кол-во заявок\n" +
            "       ROUND(((cast(o.obj_sale_date as date) - cast(o.obj_transfer_date as date)) / 30.0),\n" +
            "             3)                                                                    exposure_period,        -- Срок экспозиции, мес.\n" +
            "       o.obj_flat_comman_sqr,                                                                              -- Общая площадь помещений\n" +
            "       o.obj_land_comman_sqr,                                                                              -- Общая площадь ЗУ\n" +
            "       CASE\n" +
            "           WHEN o.obj_flat_comman_sqr IS NULL OR o.obj_flat_comman_sqr = 0 THEN NULL\n" +
            "           ELSE ROUND((COALESCE(deal_sale_cost.cost_value_rub, end_cost.cost_value_rub) / o.obj_flat_comman_sqr),\n" +
            "                      3) END                                                       flat_square_cost,       -- Стоимость 1 кв.м. помещений\n" +
            "       CASE\n" +
            "           WHEN o.obj_land_comman_sqr IS NULL OR o.obj_land_comman_sqr = 0 THEN NULL\n" +
            "           ELSE ROUND((COALESCE(deal_sale_cost.cost_value_rub, end_cost.cost_value_rub) / o.obj_land_comman_sqr),\n" +
            "                      3) END                                                       land_square_cost,       -- Стоимость 1 кв.м. ЗУ, руб.\n" +
            "       CASE\n" +
            "           WHEN o.obj_land_comman_sqr IS NULL OR o.obj_land_comman_sqr = 0 THEN NULL\n" +
            "           ELSE ROUND((COALESCE(deal_sale_cost.cost_value_rub, end_cost.cost_value_rub) * 100 / o.obj_land_comman_sqr),\n" +
            "                      3) END                                                       land_square_cost_sotka, -- Стоимость 1 сотки ЗУ, руб.\n" +
            "       CASE\n" +
            "           WHEN o.obj_land_comman_sqr IS NULL OR o.obj_land_comman_sqr = 0 THEN NULL\n" +
            "           ELSE ROUND(\n" +
            "                   (COALESCE(deal_sale_cost.cost_value_rub, end_cost.cost_value_rub) * 10000 / o.obj_land_comman_sqr),\n" +
            "                   3) END                                                          land_square_cost_hektar -- Стоимость 1 Га ЗУ, руб.\n" +
            "\n" +
            "FROM web.object o\n" +
            "         LEFT JOIN web.obj_cost deal_sale_cost\n" +
            "                   ON deal_sale_cost.ind_actual = 1 AND deal_sale_cost.type_cos_unid = 5 AND\n" +
            "                      deal_sale_cost.obj_unid = o.obj_unid\n" +
            "         LEFT JOIN web.obj_sale_category osc\n" +
            "                   ON osc.obj_unid = o.obj_unid AND osc.osc_ind_main = TRUE AND osc.ind_actual = 1\n" +
            "         LEFT JOIN (SELECT sc.sc_unid,\n" +
            "                           ((WITH RECURSIVE sale_category(sc_unid, parent_sc_unid, depth, path) AS (SELECT sc.sc_unid,\n" +
            "                                                                                                           sc.parent_sc_unid,\n" +
            "                                                                                                           1           AS depth,\n" +
            "                                                                                                           cast(sc.sc_name as text) AS path\n" +
            "                                                                                                    FROM web.sale_category AS sc\n" +
            "                                                                                                    WHERE sc.sc_unid IN (1, 42, 71)\n" +
            "                                                                                                    UNION ALL\n" +
            "                                                                                                    SELECT c.sc_unid,\n" +
            "                                                                                                           c.parent_sc_unid,\n" +
            "                                                                                                           p.depth + 1 AS depth,\n" +
            "                                                                                                           (p.path || '\\' || cast(c.sc_name as text))\n" +
            "                                                                                                    FROM sale_category AS p,\n" +
            "                                                                                                         web.sale_category AS c\n" +
            "                                                                                                    WHERE c.parent_sc_unid = p.sc_unid\n" +
            "                                                                                                      AND p.depth < 4)\n" +
            "                             SELECT path\n" +
            "                             FROM sale_category AS n\n" +
            "                             WHERE sc.sc_unid = n.sc_unid)) AS sc_name--Категория ДП\n" +
            "                    FROM web.sale_category sc) AS sc\n" +
            "                   ON sc.sc_unid = osc.sc_unid AND osc.ind_actual = 1 AND osc.osc_ind_main IS TRUE\n" +
            "         LEFT JOIN web.type_state ts ON ts.ts_unid = o.ts_unid\n" +
            "         LEFT JOIN web.type_state_trade tst ON tst.tst_unid = o.tst_unid\n" +
            "         LEFT JOIN web.address addr ON addr.ind_actual = 1 AND addr.obj_unid = o.obj_unid\n" +
            "         LEFT JOIN (SELECT obj_unid, lot_unid, auction_date_b\n" +
            "                    FROM (SELECT ROW_NUMBER() OVER (PARTITION BY l.obj_unid ORDER BY a.auction_date_b DESC) rn,\n" +
            "                                 l.obj_unid,\n" +
            "                                 l.lot_unid,\n" +
            "                                 a.auction_date_b\n" +
            "                          FROM web.lot l,\n" +
            "                               web.auction a\n" +
            "                          WHERE l.ind_actual = 1\n" +
            "                            AND l.auction_unid = a.auction_unid\n" +
            "                            AND l.lot_status <> 0) lfl\n" +
            "                    WHERE lfl.rn = 1) last_finished_lot ON last_finished_lot.obj_unid = o.obj_unid\n" +
            "         LEFT JOIN web.lot current_lot\n" +
            "                   ON o.obj_unid = current_lot.obj_unid AND current_lot.ind_actual = 1 AND\n" +
            "                      current_lot.lot_ind_current = 1\n" +
            "         LEFT JOIN web.lot l\n" +
            "                   ON l.lot_unid =\n" +
            "                      CASE WHEN tst.tst_unid = 11 THEN last_finished_lot.lot_unid ELSE current_lot.lot_unid END\n" +
            "         LEFT JOIN web.cl_asv ca ON ca.ca_unid = l.ca_unid\n" +
            "         LEFT JOIN web.obj_cost start_cost\n" +
            "                   ON start_cost.ind_actual = 1 AND start_cost.lot_unid = l.lot_unid AND start_cost.type_cos_unid = 1\n" +
            "         LEFT JOIN web.obj_cost end_cost\n" +
            "                   ON end_cost.ind_actual = 1 AND end_cost.lot_unid = l.lot_unid AND end_cost.type_cos_unid = 2\n" +
            "         LEFT JOIN web.obj_cost cut_cost\n" +
            "                   ON cut_cost.ind_actual = 1 AND cut_cost.lot_unid = l.lot_unid AND cut_cost.type_cos_unid = 9\n" +
            "         LEFT JOIN web.auction a ON l.auction_unid = a.auction_unid AND a.ind_actual = 1\n" +
            "         LEFT JOIN web.type_auction ta ON a.type_auction_unid = ta.type_auction_unid AND ta.ind_actual = 1\n" +
            "         LEFT JOIN (SELECT COUNT(*) AS lot_count,\n" +
            "                           l.obj_unid\n" +
            "                    FROM web.lot l\n" +
            "                    WHERE l.ind_actual = 1\n" +
            "                    GROUP BY obj_unid) lot_count ON lot_count.obj_unid = o.obj_unid,\n" +
            "\n" +
            "         web.obj_role sale_obj_role\n" +
            "WHERE o.ind_actual = 1\n" +
            "  AND (ts.ts_unid = 3 OR tst.tst_unid = 1 OR tst.tst_unid = 11)\n" +
            "and sale_obj_role.obj_unid = o.obj_unid\n" +
            "and sale_obj_role.ind_actual = 1\n" +
            "and sale_obj_role.or_ind_sale = 1\n" +
            "and sale_obj_role.tpa_unid = 57 -- Отдел продаж Москва\n" +
            " ###where_clause### " +
            "ORDER BY obj_sale_date DESC NULLS LAST";

    private final String footerCommonSql = "SELECT\n" +
            "       avg(CASE\n" +
            "           WHEN o.obj_flat_comman_sqr IS NULL OR o.obj_flat_comman_sqr = 0 THEN NULL\n" +
            "           ELSE ROUND((COALESCE(deal_sale_cost.cost_value_rub, end_cost.cost_value_rub) / o.obj_flat_comman_sqr),\n" +
            "                      3) END)                                                       flat_square_cost,       -- Стоимость 1 кв.м. помещений\n" +
            "       avg(CASE\n" +
            "           WHEN o.obj_land_comman_sqr IS NULL OR o.obj_land_comman_sqr = 0 THEN NULL\n" +
            "           ELSE ROUND((COALESCE(deal_sale_cost.cost_value_rub, end_cost.cost_value_rub) / o.obj_land_comman_sqr),\n" +
            "                      3) END)                                                       land_square_cost,       -- Стоимость 1 кв.м. ЗУ, руб.\n" +
            "       avg(CASE\n" +
            "           WHEN o.obj_land_comman_sqr IS NULL OR o.obj_land_comman_sqr = 0 THEN NULL\n" +
            "           ELSE ROUND((COALESCE(deal_sale_cost.cost_value_rub, end_cost.cost_value_rub) * 100 / o.obj_land_comman_sqr),\n" +
            "                      3) END)                                                       land_square_cost_sotka, -- Стоимость 1 сотки ЗУ, руб.\n" +
            "       avg(CASE\n" +
            "           WHEN o.obj_land_comman_sqr IS NULL OR o.obj_land_comman_sqr = 0 THEN NULL\n" +
            "           ELSE ROUND(\n" +
            "                   (COALESCE(deal_sale_cost.cost_value_rub, end_cost.cost_value_rub) * 10000 / o.obj_land_comman_sqr),\n" +
            "                   3) END)                                                          land_square_cost_hektar -- Стоимость 1 Га ЗУ, руб.\n" +
            "FROM web.object o\n" +
            "         LEFT JOIN web.obj_cost deal_sale_cost\n" +
            "                   ON deal_sale_cost.ind_actual = 1 AND deal_sale_cost.type_cos_unid = 5 AND\n" +
            "                      deal_sale_cost.obj_unid = o.obj_unid\n" +
            "         LEFT JOIN web.obj_sale_category osc\n" +
            "                   ON osc.obj_unid = o.obj_unid AND osc.osc_ind_main = TRUE AND osc.ind_actual = 1\n" +
            "         LEFT JOIN web.type_state ts ON ts.ts_unid = o.ts_unid\n" +
            "         LEFT JOIN web.type_state_trade tst ON tst.tst_unid = o.tst_unid\n" +
            "         LEFT JOIN web.address addr ON addr.ind_actual = 1 AND addr.obj_unid = o.obj_unid\n" +
            "         LEFT JOIN (SELECT obj_unid, lot_unid, auction_date_b\n" +
            "                    FROM (SELECT ROW_NUMBER() OVER (PARTITION BY l.obj_unid ORDER BY a.auction_date_b DESC) rn,\n" +
            "                                 l.obj_unid,\n" +
            "                                 l.lot_unid,\n" +
            "                                 a.auction_date_b\n" +
            "                          FROM web.lot l,\n" +
            "                               web.auction a\n" +
            "                          WHERE l.ind_actual = 1\n" +
            "                            AND l.auction_unid = a.auction_unid\n" +
            "                            AND l.lot_status <> 0) lfl\n" +
            "                    WHERE lfl.rn = 1) last_finished_lot ON last_finished_lot.obj_unid = o.obj_unid\n" +
            "         LEFT JOIN web.lot current_lot\n" +
            "                   ON o.obj_unid = current_lot.obj_unid AND current_lot.ind_actual = 1 AND\n" +
            "                      current_lot.lot_ind_current = 1\n" +
            "         LEFT JOIN web.lot l\n" +
            "                   ON l.lot_unid =\n" +
            "                      CASE WHEN tst.tst_unid = 11 THEN last_finished_lot.lot_unid ELSE current_lot.lot_unid END\n" +
            "         LEFT JOIN web.obj_cost end_cost\n" +
            "                   ON end_cost.ind_actual = 1 AND end_cost.lot_unid = l.lot_unid AND end_cost.type_cos_unid = 2,\n" +
            "         web.obj_role sale_obj_role\n" +
            "WHERE o.ind_actual = 1\n" +
            "  AND (ts.ts_unid = 3 OR tst.tst_unid = 1 OR tst.tst_unid = 11)\n" +
            "and sale_obj_role.obj_unid = o.obj_unid\n" +
            "and sale_obj_role.ind_actual = 1\n" +
            "and sale_obj_role.or_ind_sale = 1\n" +
            " ###where_clause### " +
            "and sale_obj_role.tpa_unid = 57 -- Отдел продаж Москва";

    private final List<TableRow> rows;
    private final TableRow footer;

    public SquareMeterCostSheetCommon(String additionalWhereCondition, ReportController reportController) {
        String where = " and " + additionalWhereCondition;
        String rowsSql = sql.replace("###where_clause###", where);


        List<Object[]> rowValues = reportController.getTableValues(rowsSql);

        rows = new ArrayList<>();
        int num = 1;
        for (Object[] row : rowValues) {
            List<TableCell> cells = new ArrayList<>();
            cells.add(new TableCell<>(num++));
            for (int i = 1; i < 5; i++) {
                String value = "";
                if (row[i] != null) {
                    value = row[i].toString();
                }
                cells.add(new TableCell<>(value));
            }
            if (row[5] != null && !row[5].toString().isEmpty()) {
                HyperlinkCell hyperlinkCell = new HyperlinkCell(row[5].toString(), row[6].toString());
                cells.add(new TableCell<>(hyperlinkCell));
            } else {
                cells.add(new TableCell<>(""));
            }
            if (row[7] != null && !row[7].toString().isEmpty()) {
                HyperlinkCell hyperlinkCell = new HyperlinkCell(row[7].toString(), row[7].toString());
                cells.add(new TableCell<>(hyperlinkCell));
            } else {
                cells.add(new TableCell<>(""));
            }
            for (int i = 8; i < 11; i++) {
                String value = "";
                if (row[i] != null) {
                    value = row[i].toString();
                }
                cells.add(new TableCell<>(value));
            }
            for (int i = 11; i < row.length; i++) {
                if (row[i] != null) {
                    BigDecimal bdValue = new BigDecimal(row[i].toString());
                    Double value = bdValue.doubleValue();
                    cells.add(new TableCell<>(value));
                } else {
                    cells.add(new TableCell<>(""));
                }
            }
            rows.add(new TableRow(cells));
        }
        String footerSql = footerCommonSql.replace("###where_clause###", where);
        Object[] footerResults = reportController.getFooterValues(footerSql);

        List<TableCell> footerCells = new ArrayList<>();
        for (int i = 0; i < 18; i++) {
            footerCells.add(new TableCell<>(""));
        }
        for (Object footerResult : footerResults) {
            if (footerResult != null) {
                BigDecimal bdValue = new BigDecimal(footerResult.toString());
                Double value = bdValue.doubleValue();
                footerCells.add(new TableCell<>(value));
            } else {
                footerCells.add(new TableCell<>(""));
            }
        }
        footer = new TableRow(footerCells);
    }


    @Override
    public List<String> getHeader() {
        return null;
    }

    @Override
    public List<TableRow> getRows() {
        return rows;
    }

    @Override
    public TableRow getFooter() {
        return footer;
    }
}
