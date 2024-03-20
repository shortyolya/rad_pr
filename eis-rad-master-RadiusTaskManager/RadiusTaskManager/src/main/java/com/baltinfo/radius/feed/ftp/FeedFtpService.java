package com.baltinfo.radius.feed.ftp;

import com.baltinfo.radius.db.controller.DocFileController;
import com.baltinfo.radius.db.model.DocFile;
import com.baltinfo.radius.loadauction.service.LoadFileService;
import com.baltinfo.radius.utils.ImageWatermark;
import com.baltinfo.radius.utils.Result;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 25.06.2020
 */
public class FeedFtpService implements FeedFileService {

    private static final Logger logger = LoggerFactory.getLogger(FeedFtpService.class);

    private static final float WATERMARK_SCALE = 0.4f;
    private static final float WATERMARK_TRANSPARENCY = 0.8f;
    private final FeedFtpClient ftpClient;
    private final LoadFileService loadFileService;
    private final DocFileController docFileController;
    private final String pathToFiles;
    private final String pathPrefix;
    private final String asvWatermarkFilePath;
    private final String radiusPathToFiles;

    public FeedFtpService(FeedFtpClient ftpClient, LoadFileService loadFileService,
                          DocFileController docFileController, String pathToFiles,
                          String pathPrefix, String asvWatermarkFilePath, String radiusPathToFiles) {
        this.ftpClient = Objects.requireNonNull(ftpClient, "Can't get ftpClient");
        this.loadFileService = Objects.requireNonNull(loadFileService, "Can't get loadFileService");
        this.docFileController = Objects.requireNonNull(docFileController, "Can't get docFileController");
        this.pathToFiles = Objects.requireNonNull(pathToFiles, "Can't get pathToFiles");
        this.pathPrefix = Objects.requireNonNull(pathPrefix, "Can't get pathPrefix");
        this.asvWatermarkFilePath = Objects.requireNonNull(asvWatermarkFilePath, "Can't get asvWatermarkFilePath");
        this.radiusPathToFiles = Objects.requireNonNull(radiusPathToFiles, "Can't get radiusPathToFiles");
    }

    @Override
    public Result<Boolean, String> isFileExistsInDirectory(Long objUnid, String fileName) {
        try {
            boolean result = ftpClient.getFilesByPath(getFullPath(objUnid, fileName)).size() > 0;
            return Result.ok(result);
        } catch (Exception e) {
            logger.error("Can't define is file = {} exists in the directory = {}", fileName, objUnid, e);
            return Result.error("Can't define is file = " + fileName + " exists in the directory " + objUnid);
        }
    }

    @Override
    public Result<Void, String> deleteUnnecessaryFiles(Long objUnid, List<String> fileNames) {
        try {
            List<String> files = ftpClient.getFilesByPath(getFullPath(objUnid));
            if (files.size() == 0) {
                return Result.ok();
            }
            Set<String> ftpFiles = new HashSet<>(files);
            Set<String> actualFiles = new HashSet<>(fileNames);
            ftpFiles.removeAll(actualFiles);
            for (String ftpFile : ftpFiles) {
                if (!ftpFile.equals(".") && !ftpFile.equals("..")) {
                    deleteFile(objUnid, ftpFile);
                }
            }
            return Result.ok();
        } catch (Exception e) {
            logger.error("Can't delete unnecessary files from directory = {}", objUnid, e);
            return Result.error("Can't delete unnecessary files from directory = " + objUnid);
        }
    }

    @Override
    public Result<Void, String> deleteUnnecessaryDirectories(List<Long> objUnidList) {
        try {
            List<String> directories = ftpClient.getDirectoriesNameByPath(pathPrefix + pathToFiles);
            if (directories.size() == 0) {
                return Result.ok();
            }
            Set<String> ftpDirectories = new HashSet<>(directories);
            Set<String> actualDirectories = listToSet(objUnidList);
            ftpDirectories.removeAll(actualDirectories);
            for (String dir : ftpDirectories) {
                deleteDirectory(dir);
            }
            return Result.ok();
        } catch (Exception e) {
            logger.error("Can't delete unnecessary directories", e);
            return Result.error("Can't delete unnecessary directories");
        }
    }

    @Override
    public Result<Void, String> postImageFileOnServer(Long objUnid, String fileName, String filePath, Boolean withWatermark) {
        try {
            Result<Void, String> createDirectoryResult = ftpClient.createDirectory(getFullPath(objUnid));
            if (createDirectoryResult.isError()) {
                logger.error(createDirectoryResult.getError());
                return Result.error("Can't create directory by path =" + getFullPath(objUnid) + " on ftp server");
            }
            byte[] source = loadFileService.getFileFromServerByPath(filePath);
            if (source == null) {
                logger.error("Can't get source of file with path = {}", filePath);
                return Result.error("Can't get source of file with path = " + filePath);
            }
            String ftpPath = getPathForPost(objUnid, fileName, withWatermark);
            if (withWatermark) {
                source = getImageWithWatermark(radiusPathToFiles + File.separator + filePath);
                if (source == null) {
                    logger.error("Can't make source of file with watermark with path = {}", filePath);
                    return Result.error("Can't make source of file with watermark with path = " + filePath);
                }
            }
            boolean loadResult = ftpClient.loadFileToFtp(ftpPath, source);
            if (!loadResult) {
                logger.error("Can't load file with path = {} on ftp server", ftpPath);
                return Result.error("Can't load file with path =" + ftpPath + " on ftp server");
            }
            DocFile df = docFileController.getDocFileByDfFilePath(filePath);
            if (df != null) {
                df.setDfFtpPath(getFullPathForDocFile(objUnid, fileName));
                docFileController.updateDocFile(df);
            } else {
                logger.warn("Can't find docFile with ftpPath = {}", ftpPath);
            }
            return Result.ok();
        } catch (Exception e) {
            logger.error("Can't post image file on the server by path = {}", filePath, e);
            return Result.error("Can't post image file on the server by path = " + filePath);
        }
    }

    private void deleteFile(Long objUnid, String fileName) {
        try {
            DocFile df = docFileController.getDocFileByFtpPath(getFullPathForDocFile(objUnid, fileName));
            if (df != null) {
                Result<Boolean, String> result = isFileExistsInDirectory(objUnid, inverseWatermarkPostfix(fileName));
                result.ifSuccess(fileExist -> {
                    if (!fileExist) {
                        df.setDfFtpPath(null);
                    }
                });
                docFileController.updateDocFile(df);
            } else {
                logger.warn("Can't find docFile with ftpPath = {}", getFullPath(objUnid, fileName));
            }
            ftpClient.deleteFile(getFullPath(objUnid, fileName));
        } catch (Exception e) {
            logger.error("Can't delete file = {}", getFullPath(objUnid, fileName), e);
        }
    }

    private void deleteDirectory(String dir) {
        try {
            List<String> files = ftpClient.getFilesByPath(getFullPath(Long.valueOf(dir)));
            if (files.size() == 0) {
                ftpClient.deleteDirectory(getFullPath(Long.valueOf(dir)));
                return;
            }
            for (String file : files) {
                if (!file.equals(".") && !file.equals("..")) {
                    deleteFile(Long.valueOf(dir), file);
                }
            }
            ftpClient.deleteDirectory(getFullPath(Long.valueOf(dir)));
        } catch (Exception e) {
            logger.error("Can't delete directory = {}", dir, e);
        }
    }

    private Set<String> listToSet(List<Long> directories) {
        return directories.stream()
                .map(String::valueOf)
                .collect(Collectors.toSet());
    }

    private String getFullPath(Long objUnid, String fileName) {
        return pathPrefix + pathToFiles + FeedFtpClient.separator + objUnid + FeedFtpClient.separator + fileName;
    }

    private String getPathForPost(Long objUnid, String fileName, Boolean withWatermark) {
        if (withWatermark) {
            return pathPrefix + pathToFiles + FeedFtpClient.separator + objUnid + FeedFtpClient.separator + inverseWatermarkPostfix(fileName);
        } else {
            return pathPrefix + pathToFiles + FeedFtpClient.separator + objUnid + FeedFtpClient.separator + fileName;
        }
    }

    private String getFullPath(Long objUnid) {
        return pathPrefix + pathToFiles + FeedFtpClient.separator + objUnid;
    }

    private String getFullPathForDocFile(Long objUnid, String fileName) {
        return pathToFiles + FeedFtpClient.separator + objUnid + FeedFtpClient.separator + fileName.replace("_wm.", ".");
    }

    private String inverseWatermarkPostfix(String str) {
        if (str.lastIndexOf("_wm.") == -1) {
            return str.replace(".", "_wm.");
        } else {
            return str.replace("_wm.", ".");
        }
    }

    private Boolean withWatermark(String str) {
        return (str.lastIndexOf("_wm.") != -1);
    }

    private byte[] getImageWithWatermark(String filePath) {
        try (InputStream is = this.getClass().getResourceAsStream(asvWatermarkFilePath)) {
            return new ImageWatermark(filePath, is)
                    .add(Positions.BOTTOM_LEFT, WATERMARK_SCALE, WATERMARK_TRANSPARENCY);
        } catch (Exception ex) {
            logger.error("Error when getting picture data by filePath = {}", filePath, ex);
        }
        return null;
    }
}
