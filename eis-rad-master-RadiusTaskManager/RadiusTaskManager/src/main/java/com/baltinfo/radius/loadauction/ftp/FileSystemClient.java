package com.baltinfo.radius.loadauction.ftp;

import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 *     Заглушка, для полной обработки данных, приходящих от АСВ
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 11.02.2020
 */
public class FileSystemClient {
    private static final Logger logger = LoggerFactory.getLogger(FileSystemClient.class);

    public File[] getFilesByPath(String path) {
        try {
            File dir = new File(path);
            if (dir.isDirectory()) {
                return dir.listFiles();
            } else {
                return new File[0];
            }
        } catch (Exception e) {
            logger.error("Can't get list of files by path = {}", path, e);
            return new File[0];
        }
    }

    public List<File> getDirectoriesByPath(String path) {
        try {
            List<File> files = new ArrayList<>();
            File dir = new File(path);
            if (dir.isDirectory()) {
                if (dir.listFiles() != null) {
                    for (File file : dir.listFiles()) {
                        File fileInDir = new File(path + "/" + file.getName());
                        if (fileInDir.isDirectory()) {
                            files.add(fileInDir);
                        }
                    }
                }
            }
            return files;
        } catch (Exception e) {
            logger.error("Can't get list of directories by path = {}", path, e);
            return new ArrayList<>();
        }
    }

    public List<File> getFilesByPattern(File[] files, String regExp) {
        try {
            List<File> filesByPattern = new ArrayList<>();
            for (File file : files) {
                String name = file.getName();
                Pattern pattern = Pattern.compile(regExp);
                Matcher matcher = pattern.matcher(name);
                if (matcher.find()) {
                    filesByPattern.add(file);
                }
            }
            return filesByPattern;
        } catch (Exception e) {
            logger.error("Error while files being filtered by regular expression", e);
            return new ArrayList<>();
        }
    }

    public String getSourceFile(String filePath) {
        try {
            return FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        } catch (Exception e) {
            logger.error("Can't get xml file source", e);
            return null;
        }
    }

    public byte[] getDocFileSource(String filePath) {
        File file = new File(filePath);
        try {
            return Files.readAllBytes(file.toPath());
        } catch (Exception e) {
            logger.error("Can't get file source", e);
            return null;
        }
    }

    public boolean loadFileToFileServer(String filePath, byte[] source) {
        File file = new File(filePath);
        try {
            FileUtils.writeByteArrayToFile(file, source);
            return true;
        } catch (IOException e) {
            logger.error("Can't load file", e);
            return false;
        }
    }
}
