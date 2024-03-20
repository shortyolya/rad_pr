package com.baltinfo.radius.loadauction.ftp;

import com.baltinfo.radius.db.controller.LoadAuctionController;
import com.baltinfo.radius.db.dto.LoadFileDto;
import com.baltinfo.radius.db.model.DocFile;
import com.baltinfo.radius.db.model.LoadFile;
import com.baltinfo.radius.loadauction.constants.FileTypeConstant;
import com.baltinfo.radius.loadauction.model.Lot;
import com.baltinfo.radius.loadauction.service.LoadFileService;
import com.baltinfo.radius.utils.Result;
import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * <p>
 * FTP сервис для обработки данных поступаемых с FTP сервера
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 03.02.2020
 */
public class FtpService extends FileStorageAbstract implements FileStorage {

    private static final Logger logger = LoggerFactory.getLogger(FtpService.class);

    private final FtpClient ftpClient;
    private final LoadFileService loadFileService;
    private final LoadAuctionController loadAuctionController;
    private final String asvPathToFiles;
    private final String eisPathToFiles;
    private final String asvPathToResultFiles;
    private final String asvPathToRadFiles;
    private final Boolean isNewFileNameFormat;

    private static final String IS_XML = "\\.[xX][mM][lL]$";
    private static final String IS_JSON = "\\.[jJ][sS][oO][nN]";

    public FtpService(FtpClient ftpClient, LoadFileService loadFileService,
                      LoadAuctionController loadAuctionController,
                      String asvPathToFiles, String eisPathToFiles, String asvPathToResultFiles,
                      String asvPathToRadFiles, Boolean isNewFileNameFormat) {
        this.ftpClient = Objects.requireNonNull(ftpClient, "Can't get ftpClient");
        this.loadFileService = Objects.requireNonNull(loadFileService, "Can't get loadFileService");
        this.loadAuctionController = Objects.requireNonNull(loadAuctionController, "Can't get loadAuctionController");
        this.asvPathToFiles = Objects.requireNonNull(asvPathToFiles, "Can't get asvPathToFiles");
        this.eisPathToFiles = Objects.requireNonNull(eisPathToFiles, "Can't get eisPathToFiles");
        this.asvPathToResultFiles = Objects.requireNonNull(asvPathToResultFiles, "Can't get asvPathToResultFiles");
        this.asvPathToRadFiles = Objects.requireNonNull(asvPathToRadFiles, "Can't get asvPathToRadFiles");
        this.isNewFileNameFormat = Objects.requireNonNull(isNewFileNameFormat, "Can't get isNewFileNameFormat");
    }

    public List<TenderSource> getTenderSource() {
        List<TenderSource> sourceList = new ArrayList<>();
        String todayDirectory = asvPathToFiles + getTodayDirectoryName();
        try {
            FTPFile[] ftpFiles = ftpClient.getFilesByPath(todayDirectory);
            for (FTPFile file : ftpFiles) {
                String fileName = file.getName().replace('В', 'B');
                if (loadAuctionController.checkExistenceOfBlockAuctionWithTradeId(fileName)) {
                    sourceList.add(getSourceOfTenderFile(todayDirectory + fileName));
                }
            }
            return sourceList;
        } catch (Exception e) {
            logger.error("Can't get tender source from FTP", e);
            return new ArrayList<>();
        }
    }

    public List<LoadFileDto> getDocumentsForLot(Set<String> savedFiles, Lot lot, String blockTradeId, String tradeId) {
        String todayDirectory = asvPathToFiles + getTodayDirectoryName();
        if (blockTradeId != null) {
            todayDirectory = todayDirectory + blockTradeId + "/" + tradeId + "/";
        } else {
            todayDirectory = todayDirectory + tradeId + "/";
        }
        try {
            FTPFile[] ftpFiles = ftpClient.getDirectoriesByPath(todayDirectory);
            List<LoadFileDto> docFiles = new ArrayList<>();
            for (FTPFile file : ftpFiles) {
                FTPFile[] documents = ftpClient.getFilesByPath(todayDirectory + file.getName() + "/");
                for (FTPFile doc : documents) {
                    byte[] directoryNameByte = file.getName().getBytes(StandardCharsets.ISO_8859_1);
                    String directoryName = new String(directoryNameByte, StandardCharsets.UTF_8);
                    FileTypeConstant fileType = FileTypeConstant.getByName(directoryName).orElse(FileTypeConstant.DOCUMENT);
                    String directory = todayDirectory + directoryName + "/";
                    String pathToFile = todayDirectory + file.getName() + "/";
                    boolean isWantedFile = isNewFileNameFormat
                            ? isWantedFileByNewFormat(doc.getName(), lot.getId())
                            : isWantedFile(doc.getName(), lot.getNumber());
                    if (isWantedFile && !savedFiles.contains(doc.getName())) {
                        docFiles.add(fillLoadFileDto(doc, directory, fileType, pathToFile, lot.getId().toString()));
                    }
                }
            }
            return docFiles;
        } catch (Exception e) {
            logger.error("Can't get files from FTP today directory = {}", todayDirectory, e);
            return new ArrayList<>();
        }
    }

    public List<LoadFileDto> getDocumentsForAuction(String blockTradeId) {
        String todayDirectory = asvPathToFiles + getTodayDirectoryName() + blockTradeId + "/";
        try {
            FTPFile[] documents = ftpClient.getFilesByPath(todayDirectory);
            List<LoadFileDto> docFiles = new ArrayList<>();
            for (FTPFile doc : documents) {
                if (doc.isDirectory()) {
                    continue;
                }
                FileTypeConstant fileType = isXmlOrJson(doc.getName())
                        ? FileTypeConstant.XML_FILE
                        : FileTypeConstant.DOCUMENT;
                docFiles.add(fillLoadFileDto(doc, todayDirectory, fileType, todayDirectory, blockTradeId));
            }
            return docFiles;
        } catch (Exception e) {
            logger.error("Can't get files from FTP today directory = {}", todayDirectory, e);
            return new ArrayList<>();
        }
    }

    public Result<Void, String> postAsvTenderFile(String efrsbCode, String etpCode, LoadFile loadFile) {
        String fileName = getFileOtRadName(loadFile.getLfFileNameAsv(), efrsbCode, etpCode);
        try {
            byte[] source = loadFileService.getFileFromServerByPath(loadFile.getLfEisPath());
            boolean res = ftpClient.loadFileToFtp(asvPathToRadFiles, fileName, source);
            return res ? Result.ok() : Result.error("Can't load asv tender file to ftp storage. Filename = " + fileName);
        } catch (Exception e) {
            logger.error("Can't load asv tender file to file system storage. Filename = {}", fileName, e);
            return Result.error("Can't load asv tender file to file system storage. Filename = " + fileName);
        }
    }

    @Override
    public Result<Void, String> writeResultFile(String baAsvId, String json) {
        String fileName = baAsvId + ".json";
        try {
            boolean res = ftpClient.loadFileToFtp(asvPathToResultFiles, fileName, json.getBytes(StandardCharsets.UTF_8));
            return res ? Result.ok() : Result.error("Can't load asv results file to ftp storage. Filename = " + fileName);
        } catch (Exception e) {
            logger.error("Can't load asv results file to ftp storage. Filename = {}", fileName, e);
            return Result.error("Can't load asv results file to ftp storage. Filename = " + fileName);
        }
    }

    private LoadFileDto fillLoadFileDto(FTPFile file, String docDirectory, FileTypeConstant fileType, String pathToFile, String lotId) {
        LoadFileDto.LoadFileDtoBuilder fileBuilder = LoadFileDto.builder();
        String ext = getExtensionByName(file.getName());
        String docPath = pathToFile + file.getName();
        byte[] fileNameByte = file.getName().getBytes(StandardCharsets.ISO_8859_1);
        String fileName = new String(fileNameByte, StandardCharsets.UTF_8);
        fileBuilder.withLfExtension(ext)
                .withLfType(fileType.getId())
                .withLfFtpPath(docDirectory + fileName)
                .withLfFileNameAsv(fileName)
                .withLfAsvId(lotId)
                .withLfSize((int) file.getSize());

        if (fileType == FileTypeConstant.IMAGE) {
            loadFileService.saveImageFileToServer(fileBuilder, ext, getDocFileSource(docPath));
        } else {
            loadFileService.saveFileToServer(fileBuilder, ext, getDocFileSource(docPath));
        }
        return fileBuilder.build();
    }

    public byte[] getDocFileSource(String path) {
        try {
            return ftpClient.getDocFileSource(path);
        } catch (Exception e) {
            logger.error("Can't get file source from FTP", e);
            return null;
        }
    }

    private TenderSource getSourceOfTenderFile(String directory) {
        try {
            List<FTPFile> xmlFtpFiles = getFilesByPattern(ftpClient.getFilesByPath(directory), IS_XML);
            List<FTPFile> jsonFtpFiles = getFilesByPattern(ftpClient.getFilesByPath(directory), IS_JSON);
            Optional<FTPFile> xmlFile = xmlFtpFiles.stream().findFirst();
            Optional<FTPFile> jsonFile = jsonFtpFiles.stream().findFirst();

            TenderSource.TenderSourceBuilder builder = TenderSource.builder();
            if (xmlFile.isPresent()) {
                builder.withXmlFileName(xmlFile.get().getName())
                        .withDirectoryName(directory)
                        .withXmlFileSource(ftpClient.getSourceFile(directory + "/" + xmlFile.get().getName()));
            }
            if (jsonFile.isPresent()) {
                builder.withJsonFileName(jsonFile.get().getName())
                        .withDirectoryName(directory)
                        .withJsonFileSource(ftpClient.getSourceFile(directory + "/" + jsonFile.get().getName()));
            }
            return builder.build();
        } catch (Exception e) {
            logger.error("Can't get source of tender file from FTP", e);
            return null;
        }
    }

    public List<FTPFile> getFilesByPattern(FTPFile[] files, String regExp) {
        try {
            List<FTPFile> ftpFiles = new ArrayList<>();
            for (FTPFile file : files) {
                String name = file.getName();
                Pattern pattern = Pattern.compile(regExp);
                Matcher matcher = pattern.matcher(name);
                if (matcher.find()) {
                    ftpFiles.add(file);
                }
            }
            return ftpFiles;
        } catch (Exception e) {
            logger.error("Error while files being filtered by regular expression", e);
            return new ArrayList<>();
        }
    }

    public Result<Void, String> writeDocsToZip(String zipFileName, List<DocFile> docFiles) {
        byte[] bytes;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try (ZipOutputStream zos = new ZipOutputStream(bos)) {
                int index = 1;
                for (DocFile docFile : docFiles) {
                    Path docPath = Paths.get(eisPathToFiles + File.separator + docFile.getDfFilePath());
                    byte[] source = Files.readAllBytes(docPath);
                    String fileName = index + ". " + docFile.getDfName();
                    index++;
                    ZipEntry zipEntry = new ZipEntry(fileName);
                    zos.putNextEntry(zipEntry);
                    zos.write(source);
                    zos.closeEntry();
                }
            }
            bytes = bos.toByteArray();

            ftpClient.loadFileToFtp(asvPathToResultFiles, zipFileName, bytes);
            return Result.ok();
        } catch (Exception ex) {
            logger.error("Can't load asv application docs zip archive. zipFileName = {}", zipFileName, ex);
            return Result.error("Can't load asv application docs zip archive. zipFileName = " + zipFileName);
        }
    }
}
