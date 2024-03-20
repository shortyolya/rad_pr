package com.baltinfo.radius.xls;

import com.baltinfo.radius.db.constants.AuctionDealTypeCost;
import com.baltinfo.radius.db.constants.AuctionStepFormConstant;
import com.baltinfo.radius.db.constants.CalcPriceConstant;
import com.baltinfo.radius.db.constants.TypeAuctionConstant;
import com.baltinfo.radius.db.constants.TypeSubjectConstant;
import com.baltinfo.radius.db.dto.AuctionDto;
import com.baltinfo.radius.db.dto.DateDto;
import com.baltinfo.radius.db.dto.LoadLotDto;
import com.baltinfo.radius.db.dto.LoadRsDto;
import com.baltinfo.radius.utils.RangeUtils;
import com.baltinfo.radius.utils.Result;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.GeneralSecurityException;
import java.text.NumberFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * Загрузчик информации из файла формата xlsx
 * </p>
 *
 * @author Lapenok Igor
 * @since 16.08.2018
 */
public class XlsExportService {

    private static final Logger logger = LoggerFactory.getLogger(XlsExportService.class);

    private static final short ARBIT_MANAGER = 0;
    private static final short COMPETITION_MANAGER = 1;

    private final NumberFormat numberFormat;
    private final String asvTemplateVersion;
    private final String privatePropertyTemplateVersion;
    private final String sberbankLeasingTemplateVersion;
    private final LoadLotService loadLotService;

    public XlsExportService(String asvTemplateVersion, String privatePropertyTemplateVersion, String sberbankLeasingTemplateVersion, LoadLotService loadLotService) {
        this.asvTemplateVersion = Objects.requireNonNull(asvTemplateVersion, "Can't get asv template versions");
        this.privatePropertyTemplateVersion = Objects.requireNonNull(privatePropertyTemplateVersion, "Can't get private property template versions");
        this.sberbankLeasingTemplateVersion = Objects.requireNonNull(sberbankLeasingTemplateVersion, "Can't get sberbank leasing template versions");
        numberFormat = NumberFormat.getInstance();
        numberFormat.setGroupingUsed(false);
        this.loadLotService = loadLotService;
    }

    /**
     * Производит загрузку информации из файла формата xlsx разположенного по пути {@param path}
     * в объект {@link AuctionDto}
     *
     * @param path полный путь расположения файла
     * @return объект {@link AuctionDto} в случае успешной загрузки или строку, содержащую информацию об ошибке
     */
    public Result<AuctionDto, String> loadFromFile(String path) {
        Result<XSSFWorkbook, String> workbookResult = getXssfWorkbook(path);
        if (workbookResult.isError()) {
            return Result.error(workbookResult.getError());
        }
        XSSFWorkbook workbook = workbookResult.getResult();
        AuctionDto.AuctionDtoBuilder builder = AuctionDto.builder();
        String templateVersion = workbook.getSheetAt(1).getRow(1).getCell(5).getStringCellValue();
        Optional<XlsTemplateType> typeResult = getTemplateVersion(templateVersion);
        if (!typeResult.isPresent()) {
            return Result.error("Не удалось определить версию шаблона. Пожалуйста загрузите шаблон с сайта заново.");
        }
        XlsTemplateType type = typeResult.get();
        Result<Void, String> parseResult;
        switch (type) {
            case UNKNOWN_VERSION:
                return Result.error("Используется устаревшая версия шаблона. Пожалуйста загрузите шаблон с сайта заново.");
            case ASV:
                parseResult = parseAsvTemplate(workbook, builder);
                if (parseResult.isError()) {
                    return Result.error(parseResult.getError());
                }
                break;
            case PRIVATE_PROPERTY:
                parseResult = parsePrivatePropertyTemplate(workbook, builder);
                if (parseResult.isError()) {
                    return Result.error(parseResult.getError());
                }
                break;
            case SBER_LEASING:
                parseResult = parseSberbankLeasingTemplate(workbook, builder);
                if (parseResult.isError()) {
                    return Result.error(parseResult.getError());
                }
                break;
            default:
                return Result.error("Не удалось определить версию шаблона. Пожалуйста загрузите шаблон с сайта заново.");
        }
        if (TypeAuctionConstant.PUBLIC.equals(builder.getTypeAuction())) {
            Result<List<LoadRsDto>, String> loadRsResult = loadRs(workbook.getSheetAt(3));
            if (loadRsResult.isError()) {
                return Result.error(loadRsResult.getError());
            }
            builder.withRsList(loadRsResult.getResult());
        }
        return Result.ok(builder.build());
    }

    private Result<Void, String> parseAsvTemplate(XSSFWorkbook workbook, AuctionDto.AuctionDtoBuilder builder) {
        Result<Void, String> loadAuctionResult = loadAuction(workbook.getSheetAt(1), builder);
        if (loadAuctionResult.isError()) {
            return Result.error(loadAuctionResult.getError());
        }

        Result<List<LoadLotDto>, String> loadLotsResult =
                loadLotService.loadLots(workbook.getSheetAt(2), builder.getTypeAuction(), numberFormat);
        if (loadLotsResult.isError()) {
            return Result.error(loadLotsResult.getError());
        }
        builder.withLotList(loadLotsResult.getResult());
        return Result.ok();
    }

    private Result<Void, String> parsePrivatePropertyTemplate(XSSFWorkbook workbook, AuctionDto.AuctionDtoBuilder builder) {
        Result<Void, String> loadAuctionResult = loadAuctionForPrivateProperty(workbook.getSheetAt(1), builder);
        if (loadAuctionResult.isError()) {
            return Result.error(loadAuctionResult.getError());
        }

        Result<List<LoadLotDto>, String> loadLotsResult =
                loadLotService.loadLotsForPrivateProperty(workbook.getSheetAt(2), builder.getTypeAuction(), numberFormat);
        if (loadLotsResult.isError()) {
            return Result.error(loadLotsResult.getError());
        }
        builder.withLotList(loadLotsResult.getResult());
        return Result.ok();
    }

    private Result<Void, String> parseSberbankLeasingTemplate(XSSFWorkbook workbook, AuctionDto.AuctionDtoBuilder builder) {
        Result<Void, String> loadAuctionResult = loadAuctionForPrivateProperty(workbook.getSheetAt(1), builder);
        if (loadAuctionResult.isError()) {
            return Result.error(loadAuctionResult.getError());
        }

        Result<List<LoadLotDto>, String> loadLotsResult =
                loadLotService.loadLotsForSberbankLeasing(workbook.getSheetAt(2), builder.getTypeAuction(), numberFormat);
        if (loadLotsResult.isError()) {
            return Result.error(loadLotsResult.getError());
        }
        builder.withLotList(loadLotsResult.getResult());
        return Result.ok();
    }

    private Result<XSSFWorkbook, String> getXssfWorkbook(String path) {
        try (FileInputStream file = new FileInputStream(new File(path))) {
            POIFSFileSystem filesystem = new POIFSFileSystem(file);
            EncryptionInfo encInfo = new EncryptionInfo(filesystem);
            Decryptor decryptor = Decryptor.getInstance(encInfo);
            if (!decryptor.verifyPassword(Decryptor.DEFAULT_PASSWORD)) {
                return Result.error("Не удалось проверить файл " + path);
            }
            return Result.ok(new XSSFWorkbook(decryptor.getDataStream(filesystem)));
        } catch (OfficeXmlFileException ex) {
            try (FileInputStream file = new FileInputStream(new File(path))) {
                XSSFWorkbook xssfWorkbook = new XSSFWorkbook(file);
                return Result.ok(xssfWorkbook);
            } catch (IOException e) {
                logger.warn("Ошибка при чтении файла" + path, e);
                return Result.error("Ошибка при чтении файла " + path);
            }
        } catch (GeneralSecurityException | IOException e) {
            logger.warn("Ошибка при чтении файла" + path, e);
            return Result.error("Ошибка при чтении файла " + path);
        }
    }

    private Optional<XlsTemplateType> getTemplateVersion(String templateVersion) {
        try {
            if (asvTemplateVersion == null) {
                return Optional.empty();
            }
            if (asvTemplateVersion.equals(templateVersion)) {
                return Optional.of(XlsTemplateType.ASV); // Шаблон АСВ
            } else if (privatePropertyTemplateVersion.equals(templateVersion)) {
                return Optional.of(XlsTemplateType.PRIVATE_PROPERTY); // Шаблон Частное имущество
            } else if (sberbankLeasingTemplateVersion.equals(templateVersion)) {
                return Optional.of(XlsTemplateType.SBER_LEASING); // Шаблон Сбербанк Лизинг
            } else {
                return Optional.of(XlsTemplateType.UNKNOWN_VERSION);
            }
        } catch (Exception e) {
            logger.warn("Error getTemplateVersion by templateVertion = {}", templateVersion, e);
            return Optional.empty();
        }
    }

    private Result<Void, String> loadAuction(XSSFSheet sheet, AuctionDto.AuctionDtoBuilder builder) {
        try {
            Result<Void, String> resultDefaultPartLoad = defaultLoadAuction(sheet, builder);
            if (resultDefaultPartLoad.isError()) {
                return Result.error(resultDefaultPartLoad.getError());
            }

            if (!TypeAuctionConstant.PUBLIC.equals(builder.getTypeAuction())
                    && builder.getAuctionDateE() == null && builder.getAuctionDatePlan() != null) {
                DateDto dateB = builder.getAuctionDatePlan();
                Instant dateTimeE = dateB.getDate().toInstant().plus(1, ChronoUnit.HOURS);
                builder.withAuctionDateE(new DateDto(dateTimeE));
            }

            Optional<String> fillDebtorResult = fillDebtor(sheet, builder);
            if (fillDebtorResult.isPresent()) {
                return Result.error(fillDebtorResult.get());
            }

            builder.withDpCrashFileNum(sheet.getRow(75).getCell(1).getStringCellValue());
            builder.withDpArbitrage(sheet.getRow(77).getCell(1).getStringCellValue());
            builder.withDpDecreeNum(sheet.getRow(79).getCell(1).getStringCellValue());
            builder.withDpDecreeDate(new DateDto(sheet.getRow(81).getCell(1).getDateCellValue()));

            String tenderManagerName = sheet.getRow(107).getCell(1).getStringCellValue();
            builder.withDpTendManagerName(tenderManagerName);
            Short dpIndTendManager = tenderManagerName != null && !"".equals(tenderManagerName.trim())
                    ? COMPETITION_MANAGER
                    : ARBIT_MANAGER;
            builder.withDpIndTendManager(dpIndTendManager);

            if (dpIndTendManager.equals(ARBIT_MANAGER)) {
                // Арбитражный
                builder.withDpArbitManagerNameF(getStringValue(sheet, 85));
                builder.withDpArbitManagerNameI(getStringValue(sheet, 87));
                builder.withDpArbitManagerNameO(getStringValue(sheet, 89));
                builder.withDpArbitManagerInn(getStringValue(sheet, 91));
                builder.withDpArbitManagerRegNum(getStringValue(sheet, 93));
            } else {
                // Конкурсный
                builder.withDpTendManagerSname(getStringValue(sheet, 109));
                builder.withDpArbitManagerInn(getStringValue(sheet, 111));
                builder.withDpTendManagerOgrn(getStringValue(sheet, 113));
                builder.withDpArbitManagerNameF(getStringValue(sheet, 115));
                builder.withDpArbitManagerNameI(getStringValue(sheet, 117));
                builder.withDpArbitManagerNameO(getStringValue(sheet, 119));
                builder.withDpTendManagerProxy(getStringValue(sheet, 121));
            }

            builder.withDpArbitManagerCro(getStringValue(sheet, 95));
            builder.withDpSroPaymentValue(getNumericCellValue(sheet, 97));
            builder.withDpSroPaymentSum(getNumericCellValue(sheet, 99));
            builder.withDpSroPaymentDate(new DateDto(sheet.getRow(101).getCell(1).getDateCellValue()));
            builder.withDpSroPaymentNote(getStringValue(sheet,103));

            builder.withDpAuctionRegOrder(getStringValue(sheet, 125));
            builder.withDpAuctionWinnerOrder(getStringValue(sheet, 128));
            builder.withDpAuctionResultPlace(getStringValue(sheet, 131));

            builder.withDpDealOrder(getStringValue(sheet, 136));
            builder.withDpPaymentsDates(getStringValue(sheet, 139));
            builder.withDpPayeeName(getStringValue(sheet, 142));
            builder.withDpPayeeInn(getStringValue(sheet, 144));
            builder.withDpPayeeKpp(getStringValue(sheet, 146));
            builder.withDpPayeeAccount(getStringValue(sheet, 148));
            builder.withDpPayeeBankName(getStringValue(sheet, 150));
            builder.withDpPayeeBankBik(getStringValue(sheet, 152));
            builder.withDpPayeeBankAccount(getStringValue(sheet, 154));

            builder.withDpDatePublJournal(new DateDto(sheet.getRow(158).getCell(1).getDateCellValue()));
            builder.withDpDatePublEfir(new DateDto(sheet.getRow(160).getCell(1).getDateCellValue()));

            builder.withDpContactEmail(getStringValue(sheet, 162));
            builder.withDpContactAsv(getStringValue(sheet, 164));
            builder.withAuctionAsvOrderNum(getStringValue(sheet, 166));
            builder.withDpDealAsv(getStringValue(sheet, 168));
            builder.withDpObjectLinks(getStringValue(sheet, 170));
            builder.withDpContacts(getStringValue(sheet, 172));
            builder.withDpRewardRadius(getStringValue(sheet, 174));

            return Result.ok();
        } catch (Exception e) {
            logger.error("Can't load auction", e);
            return Result.error("Ошибка при загрузке информации по торгам");
        }
    }

    private Result<Void, String> loadAuctionForPrivateProperty(XSSFSheet sheet, AuctionDto.AuctionDtoBuilder builder) {
        try {
            Result<Void, String> result = defaultLoadAuction(sheet, builder);
            if (result.isError()) {
                return Result.error(result.getError());
            }

            fillDebtor(sheet, builder);

            builder.withDpAuctionRegOrder(getStringValue(sheet, 75));
            builder.withDpAuctionWinnerOrder(getStringValue(sheet, 78));
            builder.withDpAuctionResultPlace(getStringValue(sheet, 81));

            builder.withDpDealOrder(getStringValue(sheet, 86));
            builder.withDpPaymentsDates(getStringValue(sheet, 89));
            builder.withDpPayeeName(getStringValue(sheet, 92));
            builder.withDpPayeeInn(getStringValue(sheet, 94));
            builder.withDpPayeeKpp(getStringValue(sheet, 96));
            builder.withDpPayeeAccount(getStringValue(sheet, 98));
            builder.withDpPayeeBankName(getStringValue(sheet, 100));
            builder.withDpPayeeBankBik(getStringValue(sheet, 102));
            builder.withDpPayeeBankAccount(getStringValue(sheet, 104));

            if (sheet.getRow(106) != null) {
                double auctionDealTypeCost = sheet.getRow(106).getCell(1).getNumericCellValue();
                if (auctionDealTypeCost == 1) {
                    builder.withAuctionDealTypeCost(AuctionDealTypeCost.START_COST.getCode());
                } else if (auctionDealTypeCost == 2) {
                    builder.withAuctionDealTypeCost(AuctionDealTypeCost.MIN_COST.getCode());
                }
            }
            return Result.ok(result.getResult());
        } catch (Exception e) {
            logger.error("Can't load auction", e);
            return Result.error("Ошибка при загрузке информации по торгам частного имущества!");
        }
    }

    private Result<Void, String> defaultLoadAuction(XSSFSheet sheet, AuctionDto.AuctionDtoBuilder builder) {
        Optional<TypeAuctionConstant> typeAuctionConstantOptional =
                TypeAuctionConstant.getByNameInTemplate(sheet.getRow(4).getCell(1).getStringCellValue());
        if (!typeAuctionConstantOptional.isPresent()) {
            return Result.error("Не заполнен вид аукциона");
        }
        builder.withAuctionNote(sheet.getRow(6).getCell(1).getStringCellValue());
        builder.withTypeAuction(typeAuctionConstantOptional.get());
        builder.withAuctionParticipantLimitation(sheet.getRow(8).getCell(1).getBooleanCellValue() ? 1 : 0);

        builder.withDpParticipantRequirements(sheet.getRow(10).getCell(1).getStringCellValue());

        if (builder.getTypeAuction().equals(TypeAuctionConstant.OPEN_AUCTION) ||
                builder.getTypeAuction().equals(TypeAuctionConstant.OPEN_CONCURS_AUCTION) ||
                builder.getTypeAuction().equals(TypeAuctionConstant.OPEN_CONCURS) ||
                builder.getTypeAuction().equals(TypeAuctionConstant.HOLLAND)) {
            builder.withAuctionDatePlan(new DateDto(sheet.getRow(13).getCell(1).getDateCellValue(),
                    (int) sheet.getRow(13).getCell(2).getNumericCellValue(),
                    (int) sheet.getRow(13).getCell(3).getNumericCellValue()));
        } else {
            if (builder.getTypeAuction().getRadiusStepForm().equals(AuctionStepFormConstant.CLOSE)) {
                builder.withAuctionDatePlan(new DateDto(sheet.getRow(19).getCell(1).getDateCellValue(),
                        (int) sheet.getRow(19).getCell(2).getNumericCellValue(),
                        (int) sheet.getRow(19).getCell(3).getNumericCellValue()));
                builder.withAuctionDateE(new DateDto(sheet.getRow(21).getCell(1).getDateCellValue(),
                        (int) sheet.getRow(21).getCell(2).getNumericCellValue(),
                        (int) sheet.getRow(21).getCell(3).getNumericCellValue()));
            }
        }

        builder.withAuctionRecepDateB(new DateDto(sheet.getRow(24).getCell(1).getDateCellValue(),
                (int) sheet.getRow(24).getCell(2).getNumericCellValue(),
                (int) sheet.getRow(24).getCell(3).getNumericCellValue()));
        builder.withAuctionRecepDateE(new DateDto(sheet.getRow(26).getCell(1).getDateCellValue(),
                (int) sheet.getRow(26).getCell(2).getNumericCellValue(),
                (int) sheet.getRow(26).getCell(3).getNumericCellValue()));

        if (TypeAuctionConstant.PUBLIC.equals(builder.getTypeAuction())) {
            builder.withAuctionDatePlan(builder.getAuctionRecepDateB());
            builder.withAuctionDateE(builder.getAuctionRecepDateE());
        } else {
            builder.withAuctionDepDateE(new DateDto(sheet.getRow(29).getCell(1).getDateCellValue(),
                    (int) sheet.getRow(29).getCell(2).getNumericCellValue(),
                    (int) sheet.getRow(29).getCell(3).getNumericCellValue()));
        }
        if (builder.getTypeAuction().equals(TypeAuctionConstant.HOLLAND)) {
            builder.withChangePricePeriodMin((int) sheet.getRow(31).getCell(1).getNumericCellValue());
            builder.withStartPriceTime((int) sheet.getRow(33).getCell(1).getNumericCellValue());
        }

        builder.withDpIndSendOper(sheet.getRow(35).getCell(1).getBooleanCellValue() ? new Short("1") : new Short("0"));
        builder.withDpDepositOrder(sheet.getRow(37).getCell(1).getStringCellValue());
        builder.withDpAccountsDetails(sheet.getRow(40).getCell(1).getStringCellValue());

        return Result.ok();
    }

    private String getStringValue(XSSFSheet sheet, int row) {
        return sheet.getRow(row).getCell(1).getStringCellValue();
    }

    private BigDecimal getNumericCellValue(XSSFSheet sheet, int row) {
        Cell cell = sheet.getRow(row).getCell(1);
        if (cell.getNumericCellValue() == 0) {
            return null;
        }
        try {
           return new BigDecimal(numberFormat.format(cell.getNumericCellValue())
                    .replaceAll(",", "."));
        } catch (Exception e) {
            logger.warn("Convert cell error. row = {}", row, e);
            return null;
        }
    }


    private Optional<String> fillDebtor(XSSFSheet sheet, AuctionDto.AuctionDtoBuilder builder) {
        Optional<TypeSubjectConstant> typeSubjectOptional =
                TypeSubjectConstant.getByName(sheet.getRow(45).getCell(1).getStringCellValue());
        if (!typeSubjectOptional.isPresent()) {
            return Optional.of("Не заполнен статус должника");
        }
        builder.withTypeSubject(typeSubjectOptional.get());
        switch (typeSubjectOptional.get()) {
            case FL:
                String flPersonNameF = sheet.getRow(51).getCell(1).getStringCellValue().trim();
                String flPersonNameI = sheet.getRow(53).getCell(1).getStringCellValue().trim();
                String flPersonNameO = sheet.getRow(55).getCell(1).getStringCellValue().trim();
                builder.withDebitorPersonNameF(flPersonNameF);
                builder.withDebitorPersonNameI(flPersonNameI);
                builder.withDebitorPersonNameO(flPersonNameO);

                String flSubName = String.join(" ", Stream.of(flPersonNameF, flPersonNameI, flPersonNameO)
                        .filter(n -> !n.isEmpty())
                        .collect(Collectors.toList()));

                if (flSubName.isEmpty()) {
                    return Optional.of("Не заполнено ФИО должника");
                }

                builder.withDebitorSubName(flSubName);
                builder.withDebitorSubSName(flSubName);
                builder.withDebitorInn(sheet.getRow(57).getCell(1).getStringCellValue());
                builder.withDebitorPersonSnils(sheet.getRow(61).getCell(1).getStringCellValue());
                builder.withDebitorAddrFact(sheet.getRow(69).getCell(1).getStringCellValue());
                break;
            case YL:
                String ylSubName = sheet.getRow(47).getCell(1).getStringCellValue().trim();
                if (ylSubName.isEmpty()) {
                    return Optional.of("Не заполнено наименование должника");
                }
                builder.withDebitorSubName(ylSubName);
                String ylSubSName = sheet.getRow(49).getCell(1).getStringCellValue().trim();
                ylSubSName = ylSubSName.isEmpty() ? ylSubName : ylSubSName;
                builder.withDebitorSubSName(ylSubSName);
                builder.withDebitorInn(sheet.getRow(57).getCell(1).getStringCellValue().trim());
                builder.withDebitorKpp(sheet.getRow(59).getCell(1).getStringCellValue().trim());
                builder.withDebitorOgrn(sheet.getRow(63).getCell(1).getStringCellValue().trim());
                builder.withDebitorAddrLegal(sheet.getRow(65).getCell(1).getStringCellValue());
                builder.withDebitorAddrFact(sheet.getRow(69).getCell(1).getStringCellValue());
                break;
            case IP:
                String ipPersonNameF = sheet.getRow(51).getCell(1).getStringCellValue().trim();
                String ipPersonNameI = sheet.getRow(53).getCell(1).getStringCellValue().trim();
                String ipPersonNameO = sheet.getRow(55).getCell(1).getStringCellValue().trim();
                builder.withDebitorPersonNameF(ipPersonNameF);
                builder.withDebitorPersonNameI(ipPersonNameI);
                builder.withDebitorPersonNameO(ipPersonNameO);

                String ipFio = String.join(" ", Stream.of(ipPersonNameF, ipPersonNameI, ipPersonNameO)
                        .filter(n -> !n.isEmpty())
                        .collect(Collectors.toList()));

                if (ipFio.isEmpty()) {
                    return Optional.of("Не заполнено ФИО должника");
                }

                String ipSubName = "Индивидуальный предприниматель " + ipFio;
                String ipSubSName = "ИП " + ipFio;
                builder.withDebitorSubName(ipSubName);
                builder.withDebitorSubSName(ipSubSName);
                builder.withDebitorInn(sheet.getRow(57).getCell(1).getStringCellValue().trim());
                builder.withDebitorPersonSnils(sheet.getRow(61).getCell(1).getStringCellValue().trim());
                builder.withDebitorOgrn(sheet.getRow(63).getCell(1).getStringCellValue().trim());
                builder.withDebitorAddrFact(sheet.getRow(69).getCell(1).getStringCellValue().trim());
                break;
        }
        return Optional.empty();
    }

    private Result<List<LoadRsDto>, String> loadRs(XSSFSheet sheet) {
        try {
            List<LoadRsDto> rsList = new ArrayList<>();
            CalcPriceConstant calcPriceType =
                    CalcPriceConstant.getByValue(sheet.getRow(3).getCell(6).getStringCellValue());
            for (Row rowRed : sheet) {
                if (rowRed.getRowNum() < 3) {
                    continue;
                }
                if (CellType.BLANK.equals(rowRed.getCell(0).getCellTypeEnum())) {
                    break;
                }
                LoadRsDto loadRsDto = new LoadRsDto(new DateDto(rowRed.getCell(0).getDateCellValue()),
                        new DateDto(rowRed.getCell(1).getDateCellValue()),
                        new BigDecimal(numberFormat.format(rowRed.getCell(2).getNumericCellValue()).replaceAll(",", ".")),
                        new BigDecimal(numberFormat.format(rowRed.getCell(3).getNumericCellValue()).replaceAll(",", ".")),
                        calcPriceType,
                        RangeUtils.parseRange(rowRed.getCell(4).getStringCellValue())
                );
                rsList.add(loadRsDto);
            }
            return Result.ok(rsList);
        } catch (Exception e) {
            logger.error("Can't load reduction schedule from sheet", e);
            return Result.error("Не удалось загрузить график снижения цены");
        }
    }
}
