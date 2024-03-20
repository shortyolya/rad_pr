package com.baltinfo.radius.loadauction.service;

import com.baltinfo.radius.db.dto.LoadFileDto;
import com.baltinfo.radius.utils.ImageUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * <p>
 *     Сервис загрузки файлов на файловый сервер площадки РАД
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 05.02.2020
 */
public class LoadFileService {
    private static Logger logger = LoggerFactory.getLogger(LoadFileService.class);

    private final String pathToFiles;

    public LoadFileService(String pathToFiles) {
        this.pathToFiles = Objects.requireNonNull(pathToFiles, "Can't get pathToFiles");
    }

    public void saveFileToServer(LoadFileDto.LoadFileDtoBuilder builder, String extension, byte[] source) {
        File dir = new File(pathToFiles + File.separator + new SimpleDateFormat("yyyyMMdd").format(new Date()));
        if (!dir.exists()) {
            dir.mkdir();
        }
        try {
            File file = File.createTempFile("tmp-", "." + extension, dir);
            FileUtils.writeByteArrayToFile(file, source);
            builder.withLfEisPath(File.separator + dir.getName() + File.separator + file.getName())
                    .withLfFileName(file.getName());
        } catch (Exception e) {
            logger.error("Error while LoadFile was being created", e);
        }
    }

    public void saveImageFileToServer(LoadFileDto.LoadFileDtoBuilder builder, String extension, byte[] source) {
        File dir = new File(pathToFiles + File.separator + new SimpleDateFormat("yyyyMMdd").format(new Date()));
        if (!dir.exists()) {
            dir.mkdir();
        }
        try {
            File file = File.createTempFile("tmp-", "." + extension, dir);
            FileUtils.writeByteArrayToFile(file, source);
            ImageUtils.alignOrientation(file);
            builder.withLfEisPath(File.separator + dir.getName() + File.separator + file.getName())
                    .withLfFileName(file.getName());
        } catch (Exception e) {
            logger.error("Error while LoadFile was being created", e);
        }
    }

    public byte[] getFileFromServerByPath(String eisPathToFile) {
        try {
            File readFile = new File(pathToFiles + File.separator + eisPathToFile);
            return FileUtils.readFileToByteArray(readFile);
        } catch (Exception e) {
            logger.error("Can't get asv tender file from server filepath = {}", eisPathToFile, e);
            return null;
        }
    }
}
