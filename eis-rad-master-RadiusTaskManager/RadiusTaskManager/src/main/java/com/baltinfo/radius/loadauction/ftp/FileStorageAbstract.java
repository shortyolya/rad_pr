package com.baltinfo.radius.loadauction.ftp;

import org.apache.commons.io.FilenameUtils;
import org.joda.time.DateTime;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.format;

/**
 * <p>
 *     Абстрактный класс реализующий общие методы для классов, его расширяющих
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 03.03.2020
 */
public abstract class FileStorageAbstract {

    private static final String IS_XML = "\\.[xX][mM][lL]$";
    private static final String IS_JSON = "\\.[jJ][sS][oO][nN]$";
    private static final String EXTENSION = "\\.[0-9a-zA-Z]{1,5}$";
    private static final String LETTERS_IN_STRING = "\\D";
    private static final String STRING_FORMAT_FOR_TODAY_FOLDER_NAME = "%04d/%04d-%02d/%04d-%02d-%02d/";
    private static final String STRING_FORMAT_FOR_ASV_TENDER_FILE_SENT_TO_ASV = "_%s_%s.%s";

    protected boolean isXmlOrJson(String name) {
        Pattern pattern = Pattern.compile(IS_XML);
        Matcher matcher = pattern.matcher(name);
        Pattern patternJson = Pattern.compile(IS_JSON);
        Matcher matcherJson = patternJson.matcher(name);
        return matcher.find() || matcherJson.find();
    }

    //L001003.jpg
    protected boolean isWantedFile(String name, Long lotNumber) {
        Pattern pattern = Pattern.compile("^l" + String.format("%03d", lotNumber));
        Matcher matcher = pattern.matcher(name.toLowerCase());
        return matcher.find();
    }

    //L<ID лота>_<ID фото (документа) СУА>_<четырехзначный порядковый номер>.расширение (L7595_1234567_0005.jpg)
    protected boolean isWantedFileByNewFormat(String name, Long lotId) {
        Pattern pattern = Pattern.compile("^l" + lotId + "_");
        Matcher matcher = pattern.matcher(name.toLowerCase());
        return matcher.find();
    }

    protected boolean containsLetter(String name) {
        Pattern pattern = Pattern.compile(LETTERS_IN_STRING);
        Matcher matcher = pattern.matcher(name.toLowerCase());
        return matcher.find();
    }

    protected String getExtensionByName(String name) {
        Pattern pattern = Pattern.compile(EXTENSION);
        Matcher matcher = pattern.matcher(name);
        if (matcher.find()) {
            return matcher.group(0).substring(1);
        } else {
            return null;
        }
    }

    protected String getTodayDirectoryName() {
        DateTime now = new DateTime();
        int year = now.getYear();
        int month = now.getMonthOfYear();
        int day = now.getDayOfMonth();
        return format(STRING_FORMAT_FOR_TODAY_FOLDER_NAME, year, year, month, year, month, day);
    }

    protected String getFileOtRadName(String fileOtAsvName, String efrsbCode, String etpCode) {
        String prefix = FilenameUtils.getBaseName(fileOtAsvName); // Имя файла без расширения
        String extension = FilenameUtils.getExtension(fileOtAsvName);
        return prefix + format(STRING_FORMAT_FOR_ASV_TENDER_FILE_SENT_TO_ASV, efrsbCode, etpCode, extension);
    }
}
