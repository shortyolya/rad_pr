package com.baltinfo.radius.xls;

import com.baltinfo.radius.db.constants.DepositTypeConstant;
import com.baltinfo.radius.db.constants.TypeAuctionConstant;
import com.baltinfo.radius.db.dto.LoadLotDto;
import com.baltinfo.radius.utils.Result;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * Загружает список лотов их excel в DTO лист
 * </p>
 *
 * @author Sergeev Yuriy
 * @since 24.09.2019
 */
@Service
public class LoadLotService {

    private static final Logger LOG = LoggerFactory.getLogger(LoadLotService.class);

    private static final String CANNOT_LOAD_LOT_LIST_RU = "Не удалось загрузить список лотов.";
    private static final String CANNOT_LOAD_LOT_LIST = "Can't load lot list.";
    private static final String SALE_CATEGORY_SEPARATOR = "-";

    public Result<List<LoadLotDto>, String> loadLots(XSSFSheet sheet, TypeAuctionConstant typeAuctionConstant,
                                                     NumberFormat numberFormat) {
        try {
            List<LoadLotDto> lotList = new ArrayList<>();
            for (Row row : sheet) {
                int rowNum = row.getRowNum();
                if (rowNum < 4) {
                    continue;
                }
                if (CellType.BLANK.equals(row.getCell(0).getCellTypeEnum())) {
                    break;
                }

                LoadLotDto.LoadLotDtoBuilder builder = LoadLotDto.builder();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    switch (cell.getColumnIndex()) {
                        case 0:
                            try {
                                builder.withLlLotNum((int) cell.getNumericCellValue() + "");
                            } catch (Exception e) {
                                logError("Lot number", rowNum, e);
                                return Result.error(getErrorMessage("Неправильно указан номер лота", rowNum));
                            }
                            break;
                        case 1:
                            try {
                                if (StringUtils.hasText(cell.getStringCellValue())) {
                                    builder.withLlScCode(parseSaleCategory(cell.getStringCellValue()));
                                } else {
                                    return Result.error(getErrorMessage("Не указана категория продаж", rowNum));
                                }
                            } catch (Exception e) {
                                logError("Sale category", rowNum, e);
                                return Result.error(getErrorMessage("Неправильно указана категория продаж", rowNum));
                            }
                            break;
                        case 2:
                            builder.withLlClAsv(cell.getStringCellValue());
                            break;
                        case 3:
                            builder.withLlRegion(cell.getStringCellValue());
                            break;
                        case 4:
                            builder.withLlAddress(cell.getStringCellValue());
                            break;
                        case 5:
                            if (cell.getNumericCellValue() == 0) {
                                break;
                            }
                            try {
                                builder.withLlLandCommonSqr(
                                        new BigDecimal(numberFormat.format(cell.getNumericCellValue())
                                                .replaceAll(",", ".")));
                            } catch (Exception e) {
                                logError("Land common sqr", rowNum, e);
                                return Result.error(getErrorMessage("Неправильно указана площадь земельных участков", rowNum));
                            }
                            break;
                        case 6:
                            if (cell.getNumericCellValue() == 0) {
                                break;
                            }
                            try {
                                builder.withLlFlatCommonSqr(new BigDecimal(numberFormat.format(cell.getNumericCellValue())
                                        .replaceAll(",", ".")));
                            } catch (Exception e) {
                                logError("Flat common sqr", rowNum, e);
                                return Result.error(getErrorMessage("Неправильно указана площадь помещений", rowNum));
                            }
                            break;
                        case 7:
                            builder.withLlLotSname(cell.getStringCellValue());
                            break;
                        case 8:
                            builder.withLlLotName(cell.getStringCellValue());
                            break;
                        case 9:
                            builder.withLlReviewDebitorOrder(cell.getStringCellValue());
                            break;
                        case 10:
                            try {
                                builder.withLlStartCost(new BigDecimal(numberFormat.format(cell.getNumericCellValue()).replaceAll(",", ".")));
                            } catch (Exception e) {
                                logError("Start price", rowNum, e);
                                return Result.error(getErrorMessage("Неправильно указана начальная стоимость", rowNum));
                            }
                            break;
                        case 11:
                            Optional<DepositTypeConstant> depositTypeConstant =
                                    DepositTypeConstant.getByName(cell.getStringCellValue());
                            if (!depositTypeConstant.isPresent()) {
                                LOG.error("{} {} error at row {}", CANNOT_LOAD_LOT_LIST, "Deposit type", rowNum);
                                return Result.error(getErrorMessage("Неправильно указан Тип расчета задатка", rowNum));
                            }
                            builder.withLlDepositAlg(depositTypeConstant.get());
                            break;
                        case 12:
                            try {
                                builder.withLlDepositSum(new BigDecimal(numberFormat.format(cell.getNumericCellValue()).replaceAll(",", ".")));
                            } catch (Exception e) {
                                logError("Deposit sum", rowNum, e);
                                return Result.error(getErrorMessage("Неправильно указан задаток", rowNum));
                            }
                            break;
                        case 13:
                            try {
                                builder.withLlStepValue(new BigDecimal(numberFormat.format(cell.getNumericCellValue()).replaceAll(",", ".")));
                            } catch (Exception e) {
                                logError("Step value", rowNum, e);
                                return Result.error(getErrorMessage("Неправильно указан шаг", rowNum));
                            }
                            break;
                        case 14:
                            if (cell.getNumericCellValue() == 0) {
                                break;
                            }
                            try {
                                builder.withLlStepDecreaseValue(new BigDecimal(numberFormat.format(cell.getNumericCellValue()).replaceAll(",", ".")));
                            } catch (Exception e) {
                                logError("Step Decrease value", rowNum, e);
                                return Result.error(getErrorMessage("Неправильно указан шаг на понижение", rowNum));
                            }
                            break;
                        case 15:
                            try {
                                builder.withLlMinPrice(new BigDecimal(numberFormat.format(cell.getNumericCellValue()).replaceAll(",", ".")));
                            } catch (Exception e) {
                                logError("Step Min Price", rowNum, e);
                                return Result.error(getErrorMessage("Неправильно указана цена отсечения", rowNum));
                            }
                            break;
                        case 16:
                            try {
                                builder.withLlIndRightEnsure(cell.getStringCellValue().toLowerCase().trim()
                                        .equals("да") ? Short.valueOf("1") : Short.valueOf("0"));
                            } catch (Exception e) {
                                logError("Indication right ensure", rowNum, e);
                                return Result.error(getErrorMessage("Неправильно указан признак наличия обеспечения", rowNum));
                            }
                            break;
                        case 17:
                            try {
                                builder.withLlMarketingRequirement(cell.getStringCellValue());
                            } catch (Exception e) {
                                logError("Change marketing requirement", rowNum, e);
                                return Result.error(getErrorMessage("Неправильно указаны требования к маркетинговым мероприятиям", rowNum));
                            }
                            break;
                        case 18:
                            if (TypeAuctionConstant.PUBLIC.equals(typeAuctionConstant)) {
                                try {
                                    builder.withLlChangePriceTimeH((short) cell.getNumericCellValue());
                                } catch (Exception e) {
                                    logError("Change time price (hour)", rowNum, e);
                                    return Result.error(getErrorMessage("Неправильно указано время изменения цены (часы)", rowNum));
                                }
                            }
                            break;
                        case 19:
                            if (TypeAuctionConstant.PUBLIC.equals(typeAuctionConstant)) {
                                try {
                                    builder.withLlChangePriceTimeM((short) cell.getNumericCellValue());
                                } catch (Exception e) {
                                    logError("Change time price (minute)", rowNum, e);
                                    return Result.error(getErrorMessage("Неправильно указано время изменения цены (минуты)", rowNum));
                                }
                            }
                            break;
                        case 20:
                            if (TypeAuctionConstant.PUBLIC.equals(typeAuctionConstant)) {
                                try {
                                    builder.withLlChangePricePeriod((int) cell.getNumericCellValue());
                                } catch (Exception e) {
                                    logError("Price period", rowNum, e);
                                    return Result.error(getErrorMessage("Неправильно указан период изменения цены", rowNum));
                                }
                            }
                            break;
                        case 21:
                            if (TypeAuctionConstant.PUBLIC.equals(typeAuctionConstant)) {
                                try {
                                    builder.withLlApplPeriod((int) cell.getNumericCellValue());
                                } catch (Exception e) {
                                    logError("Application period", rowNum, e);
                                    return Result.error(getErrorMessage("Неправильно указан период рассмотрения заявок", rowNum));
                                }
                            }
                            break;
                        case 22:
                            if (TypeAuctionConstant.PUBLIC.equals(typeAuctionConstant)) {
                                try {
                                    builder.withLlChangePriceValue(new BigDecimal(numberFormat.format(cell.getNumericCellValue()).replaceAll(",", ".")));
                                } catch (Exception e) {
                                    logError("Price value", rowNum, e);
                                    return Result.error(getErrorMessage("Неправильно указана величина (процент) снижения", rowNum));
                                }
                            }
                            break;
                        case 23:
                            if (TypeAuctionConstant.PUBLIC.equals(typeAuctionConstant)) {
                                builder.withLlChangePriceAlg((short) cell.getNumericCellValue());
                            }
                            break;
                        case 24:
                            break;
                    }
                }
                lotList.add(builder.build());
            }
            return Result.ok(lotList);
        } catch (Exception e) {
            LOG.error("Can't load lot list from sheet", e);
            return Result.error("Не удалось загрузить список лотов");
        }
    }

    public Result<List<LoadLotDto>, String> loadLotsForPrivateProperty(XSSFSheet sheet,
                                                                       TypeAuctionConstant typeAuctionConstant,
                                                                       NumberFormat numberFormat) {
        try {
            List<LoadLotDto> lotList = new ArrayList<>();
            for (Row row : sheet) {
                int rowNum = row.getRowNum();
                if (rowNum < 4) {
                    continue;
                }
                if (CellType.BLANK.equals(row.getCell(0).getCellTypeEnum())) {
                    break;
                }

                LoadLotDto.LoadLotDtoBuilder builder = LoadLotDto.builder();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    switch (cell.getColumnIndex()) {
                        case 0:
                            try {
                                builder.withLlLotNum((int) cell.getNumericCellValue() + "");
                            } catch (Exception e) {
                                logError("Lot number", rowNum, e);
                                return Result.error(getErrorMessage("Неправильно указан номер лота", rowNum));
                            }
                            break;
                        case 1:
                            try {
                                if (StringUtils.hasText(cell.getStringCellValue())) {
                                    builder.withLlScCode(parseSaleCategory(cell.getStringCellValue()));
                                } else {
                                    return Result.error(getErrorMessage("Не указана категория продаж", rowNum));
                                }
                            } catch (Exception e) {
                                logError("Sale category", rowNum, e);
                                return Result.error(getErrorMessage("Неправильно указана категория продаж", rowNum));
                            }
                            break;
                        case 2:
                            builder.withLlRegion(cell.getStringCellValue());
                            break;
                        case 3:
                            builder.withLlAddress(cell.getStringCellValue());
                            break;
                        case 4:
                            builder.withLlLotSname(cell.getStringCellValue());
                            break;
                        case 5:
                            builder.withLlLotName(cell.getStringCellValue());
                            break;
                        case 6:
                            builder.withLlReviewDebitorOrder(cell.getStringCellValue());
                            break;
                        case 7:
                            try {
                                builder.withLlStartCost(new BigDecimal(numberFormat.format(cell.getNumericCellValue()).replaceAll(",", ".")));
                            } catch (Exception e) {
                                logError("Start price", rowNum, e);
                                return Result.error(getErrorMessage("Неправильно указана начальная стоимость", rowNum));
                            }
                            break;
                        case 8:
                            Optional<DepositTypeConstant> depositTypeConstant =
                                    DepositTypeConstant.getByName(cell.getStringCellValue());
                            if (!depositTypeConstant.isPresent()) {
                                LOG.error("{} {} error at row {}", CANNOT_LOAD_LOT_LIST, "Deposit type", rowNum);
                                return Result.error(getErrorMessage("Неправильно указан Тип расчета задатка", rowNum));
                            }
                            builder.withLlDepositAlg(depositTypeConstant.get());
                            break;
                        case 9:
                            try {
                                builder.withLlDepositSum(new BigDecimal(numberFormat.format(cell.getNumericCellValue()).replaceAll(",", ".")));
                            } catch (Exception e) {
                                logError("Deposit sum", rowNum, e);
                                return Result.error(getErrorMessage("Неправильно указан задаток", rowNum));
                            }
                            break;
                        case 10:
                            try {
                                builder.withLlStepValue(new BigDecimal(numberFormat.format(cell.getNumericCellValue()).replaceAll(",", ".")));
                            } catch (Exception e) {
                                logError("Step value", rowNum, e);
                                return Result.error(getErrorMessage("Неправильно указан шаг на повышение", rowNum));
                            }
                            break;
                        case 11:
                            if (cell.getNumericCellValue() == 0) {
                                break;
                            }
                            try {
                                builder.withLlStepDecreaseValue(new BigDecimal(numberFormat.format(cell.getNumericCellValue()).replaceAll(",", ".")));
                            } catch (Exception e) {
                                logError("Step Decrease value", rowNum, e);
                                return Result.error(getErrorMessage("Неправильно указан шаг на понижение", rowNum));
                            }
                            break;
                        case 12:
                            try {
                                builder.withLlMinPrice(new BigDecimal(numberFormat.format(cell.getNumericCellValue()).replaceAll(",", ".")));
                            } catch (Exception e) {
                                logError("Step Min Price", rowNum, e);
                                return Result.error(getErrorMessage("Неправильно указана цена отсечения", rowNum));
                            }
                            break;
                        case 13:
                            try {
                                builder.withLlIndRightEnsure(cell.getStringCellValue().toLowerCase().trim()
                                        .equals("да") ? Short.valueOf("1") : Short.valueOf("0"));
                            } catch (Exception e) {
                                logError("Indication right ensure", rowNum, e);
                                return Result.error(getErrorMessage("Неправильно указан признак наличия обеспечения", rowNum));
                            }
                            break;
                        case 14:
                            try {
                                builder.withLlMarketingRequirement(cell.getStringCellValue());
                            } catch (Exception e) {
                                logError("Change marketing requirement", rowNum, e);
                                return Result.error(getErrorMessage("Неправильно указаны требования к маркетинговым мероприятиям", rowNum));
                            }
                            break;
                        case 15:
                            if (TypeAuctionConstant.PUBLIC.equals(typeAuctionConstant)) {
                                try {
                                    builder.withLlChangePriceTimeH((short) cell.getNumericCellValue());
                                } catch (Exception e) {
                                    logError("Change time price (hour)", rowNum, e);
                                    return Result.error(getErrorMessage("Неправильно указано время изменения цены (часы)", rowNum));
                                }
                            }
                            break;
                        case 16:
                            if (TypeAuctionConstant.PUBLIC.equals(typeAuctionConstant)) {
                                try {
                                    builder.withLlChangePriceTimeM((short) cell.getNumericCellValue());
                                } catch (Exception e) {
                                    logError("Change time price (minute)", rowNum, e);
                                    return Result.error(getErrorMessage("Неправильно указано время изменения цены (минуты)", rowNum));
                                }
                            }
                            break;
                        case 17:
                            if (TypeAuctionConstant.PUBLIC.equals(typeAuctionConstant)) {
                                try {
                                    builder.withLlChangePricePeriod((int) cell.getNumericCellValue());
                                } catch (Exception e) {
                                    logError("Price period", rowNum, e);
                                    return Result.error(getErrorMessage("Неправильно указан период изменения цены", rowNum));
                                }
                            }
                            break;
                        case 18:
                            if (TypeAuctionConstant.PUBLIC.equals(typeAuctionConstant)) {
                                try {
                                    builder.withLlApplPeriod((int) cell.getNumericCellValue());
                                } catch (Exception e) {
                                    logError("Application period", rowNum, e);
                                    return Result.error(getErrorMessage("Неправильно указан период рассмотрения заявок", rowNum));
                                }
                            }
                            break;
                        case 19:
                            if (TypeAuctionConstant.PUBLIC.equals(typeAuctionConstant)) {
                                try {
                                    builder.withLlChangePriceValue(new BigDecimal(numberFormat.format(cell.getNumericCellValue()).replaceAll(",", ".")));
                                } catch (Exception e) {
                                    logError("Price value", rowNum, e);
                                    return Result.error(getErrorMessage("Неправильно указана величина (процент) снижения", rowNum));
                                }
                            }
                            break;
                        case 20:
                            if (TypeAuctionConstant.PUBLIC.equals(typeAuctionConstant)) {
                                builder.withLlChangePriceAlg((short) cell.getNumericCellValue());
                            }
                            break;
                        case 21:
                            break;
                    }
                }
                lotList.add(builder.build());
            }
            return Result.ok(lotList);
        } catch (Exception e) {
            LOG.error("Can't load lot list from sheet", e);
            return Result.error("Не удалось загрузить список лотов");
        }
    }
    public Result<List<LoadLotDto>, String> loadLotsForSberbankLeasing(XSSFSheet sheet,
                                                                       TypeAuctionConstant typeAuctionConstant,
                                                                       NumberFormat numberFormat) {
        try {
            List<LoadLotDto> lotList = new ArrayList<>();
            for (Row row : sheet) {
                int rowNum = row.getRowNum();
                if (rowNum < 4) {
                    continue;
                }
                if (CellType.BLANK.equals(row.getCell(0).getCellTypeEnum())) {
                    break;
                }

                LoadLotDto.LoadLotDtoBuilder builder = LoadLotDto.builder();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    switch (cell.getColumnIndex()) {
                        case 0:
                            try {
                                builder.withLlLotNum((int) cell.getNumericCellValue() + "");
                            } catch (Exception e) {
                                logError("Lot number", rowNum, e);
                                return Result.error(getErrorMessage("Неправильно указан номер лота", rowNum));
                            }
                            break;
                        case 1:
                            try {
                                if (StringUtils.hasText(cell.getStringCellValue())) {
                                    builder.withLlScCode(parseSaleCategory(cell.getStringCellValue()));
                                } else {
                                    return Result.error(getErrorMessage("Не указана категория продаж", rowNum));
                                }
                            } catch (Exception e) {
                                logError("Sale category", rowNum, e);
                                return Result.error(getErrorMessage("Неправильно указана категория продаж", rowNum));
                            }
                            break;
                        case 2:
                            builder.withLlRegion(cell.getStringCellValue());
                            break;
                        case 3:
                            builder.withLlAddress(cell.getStringCellValue());
                            break;
                        case 4:
                            builder.withLlTzNum(cell.getStringCellValue());
                            break;
                        case 5:
                            builder.withLlTzDate(cell.getDateCellValue());
                            break;
                        case 6:
                            builder.withLlLotSname(cell.getStringCellValue());
                            break;
                        case 7:
                            builder.withLlLotName(cell.getStringCellValue());
                            break;
                        case 8:
                            builder.withLlReviewDebitorOrder(cell.getStringCellValue());
                            break;
                        case 9:
                            try {
                                builder.withLlStartCost(new BigDecimal(numberFormat.format(cell.getNumericCellValue()).replaceAll(",", ".")));
                            } catch (Exception e) {
                                logError("Start price", rowNum, e);
                                return Result.error(getErrorMessage("Неправильно указана начальная стоимость", rowNum));
                            }
                            break;
                        case 10:
                            Optional<DepositTypeConstant> depositTypeConstant =
                                    DepositTypeConstant.getByName(cell.getStringCellValue());
                            if (!depositTypeConstant.isPresent()) {
                                LOG.error("{} {} error at row {}", CANNOT_LOAD_LOT_LIST, "Deposit type", rowNum);
                                return Result.error(getErrorMessage("Неправильно указан Тип расчета задатка", rowNum));
                            }
                            builder.withLlDepositAlg(depositTypeConstant.get());
                            break;
                        case 11:
                            try {
                                builder.withLlDepositSum(new BigDecimal(numberFormat.format(cell.getNumericCellValue()).replaceAll(",", ".")));
                            } catch (Exception e) {
                                logError("Deposit sum", rowNum, e);
                                return Result.error(getErrorMessage("Неправильно указан задаток", rowNum));
                            }
                            break;
                        case 12:
                            try {
                                builder.withLlStepValue(new BigDecimal(numberFormat.format(cell.getNumericCellValue()).replaceAll(",", ".")));
                            } catch (Exception e) {
                                logError("Step value", rowNum, e);
                                return Result.error(getErrorMessage("Неправильно указан шаг на повышение", rowNum));
                            }
                            break;
                        case 13:
                            if (cell.getNumericCellValue() == 0) {
                                break;
                            }
                            try {
                                builder.withLlStepDecreaseValue(new BigDecimal(numberFormat.format(cell.getNumericCellValue()).replaceAll(",", ".")));
                            } catch (Exception e) {
                                logError("Step Decrease value", rowNum, e);
                                return Result.error(getErrorMessage("Неправильно указан шаг на понижение", rowNum));
                            }
                            break;
                        case 14:
                            try {
                                builder.withLlMinPrice(new BigDecimal(numberFormat.format(cell.getNumericCellValue()).replaceAll(",", ".")));
                            } catch (Exception e) {
                                logError("Step Min Price", rowNum, e);
                                return Result.error(getErrorMessage("Неправильно указана цена отсечения", rowNum));
                            }
                            break;
                        case 15:
                            try {
                                builder.withLlIndRightEnsure(cell.getStringCellValue().toLowerCase().trim()
                                        .equals("да") ? Short.valueOf("1") : Short.valueOf("0"));
                            } catch (Exception e) {
                                logError("Indication right ensure", rowNum, e);
                                return Result.error(getErrorMessage("Неправильно указан признак наличия обеспечения", rowNum));
                            }
                            break;
                        case 16:
                            try {
                                builder.withLlMarketingRequirement(cell.getStringCellValue());
                            } catch (Exception e) {
                                logError("Change marketing requirement", rowNum, e);
                                return Result.error(getErrorMessage("Неправильно указаны требования к маркетинговым мероприятиям", rowNum));
                            }
                            break;
                        case 17:
                            if (TypeAuctionConstant.PUBLIC.equals(typeAuctionConstant)) {
                                try {
                                    builder.withLlChangePriceTimeH((short) cell.getNumericCellValue());
                                } catch (Exception e) {
                                    logError("Change time price (hour)", rowNum, e);
                                    return Result.error(getErrorMessage("Неправильно указано время изменения цены (часы)", rowNum));
                                }
                            }
                            break;
                        case 18:
                            if (TypeAuctionConstant.PUBLIC.equals(typeAuctionConstant)) {
                                try {
                                    builder.withLlChangePriceTimeM((short) cell.getNumericCellValue());
                                } catch (Exception e) {
                                    logError("Change time price (minute)", rowNum, e);
                                    return Result.error(getErrorMessage("Неправильно указано время изменения цены (минуты)", rowNum));
                                }
                            }
                            break;
                        case 19:
                            if (TypeAuctionConstant.PUBLIC.equals(typeAuctionConstant)) {
                                try {
                                    builder.withLlChangePricePeriod((int) cell.getNumericCellValue());
                                } catch (Exception e) {
                                    logError("Price period", rowNum, e);
                                    return Result.error(getErrorMessage("Неправильно указан период изменения цены", rowNum));
                                }
                            }
                            break;
                        case 20:
                            if (TypeAuctionConstant.PUBLIC.equals(typeAuctionConstant)) {
                                try {
                                    builder.withLlApplPeriod((int) cell.getNumericCellValue());
                                } catch (Exception e) {
                                    logError("Application period", rowNum, e);
                                    return Result.error(getErrorMessage("Неправильно указан период рассмотрения заявок", rowNum));
                                }
                            }
                            break;
                        case 21:
                            if (TypeAuctionConstant.PUBLIC.equals(typeAuctionConstant)) {
                                try {
                                    builder.withLlChangePriceValue(new BigDecimal(numberFormat.format(cell.getNumericCellValue()).replaceAll(",", ".")));
                                } catch (Exception e) {
                                    logError("Price value", rowNum, e);
                                    return Result.error(getErrorMessage("Неправильно указана величина (процент) снижения", rowNum));
                                }
                            }
                            break;
                        case 22:
                            if (TypeAuctionConstant.PUBLIC.equals(typeAuctionConstant)) {
                                builder.withLlChangePriceAlg((short) cell.getNumericCellValue());
                            }
                            break;
                        case 23:
                            if (TypeAuctionConstant.PUBLIC.equals(typeAuctionConstant)) {
                                try {
                                    builder.withLlPeriodBeginningDecline((int) cell.getNumericCellValue());
                                } catch (Exception e) {
                                    logError("Application period", rowNum, e);
                                    return Result.error(getErrorMessage("Неправильно указан период рассмотрения заявок", rowNum));
                                }
                            }
                            break;
                        case 24:
                            break;
                    }
                }
                lotList.add(builder.build());
            }
            return Result.ok(lotList);
        } catch (Exception e) {
            LOG.error("Can't load lot list from sheet", e);
            return Result.error("Не удалось загрузить список лотов");
        }
    }

    private String parseSaleCategory(String saleCategory) {
        String[] parts = saleCategory.split(SALE_CATEGORY_SEPARATOR);
        if (parts.length > 1) {
            return parts[0].trim();
        }
        return null;
    }

    private void logError(String errorMessage, int rowNum, Exception e) {
        LOG.error("{} {} error at row {}", CANNOT_LOAD_LOT_LIST, errorMessage, rowNum, e);
    }

    private String getErrorMessage(String errorMessage, int rowNum) {
        return String.format("%s %s в строке %s", CANNOT_LOAD_LOT_LIST_RU, errorMessage, rowNum);
    }
}
