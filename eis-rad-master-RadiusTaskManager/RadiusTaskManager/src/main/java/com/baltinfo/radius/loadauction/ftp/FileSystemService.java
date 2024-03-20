package com.baltinfo.radius.loadauction.ftp;

import com.baltinfo.radius.db.controller.LoadAuctionController;
import com.baltinfo.radius.db.dto.LoadFileDto;
import com.baltinfo.radius.db.model.DocFile;
import com.baltinfo.radius.db.model.LoadFile;
import com.baltinfo.radius.loadauction.constants.FileTypeConstant;
import com.baltinfo.radius.loadauction.model.Lot;
import com.baltinfo.radius.loadauction.service.LoadFileService;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * <p>
 * Сервис для загрузки xml из папки на локальной машине
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 11.02.2020
 */
public class FileSystemService extends FileStorageAbstract implements FileStorage {

    private static final Logger logger = LoggerFactory.getLogger(FileSystemService.class);

    private final FileSystemClient fileSystemClient;
    private final LoadFileService loadFileService;
    private final LoadAuctionController loadAuctionController;
    private final String asvPathToFiles;
    private final String eisPathToFiles;
    private final String asvPathToResultFiles;
    private final String asvPathToRadFiles;
    private final Boolean isNewFileNameFormat;

    private static final String IS_XML = "\\.[xX][mM][lL]$";
    private static final String IS_JSON = "\\.[jJ][sS][oO][nN]";

    public FileSystemService(FileSystemClient fileSystemClient, LoadFileService loadFileService,
                             LoadAuctionController loadAuctionController,
                             String asvPathToFiles, String eisPathToFiles, String asvPathToResultFiles,
                             String asvPathToRadFiles, Boolean isNewFileNameFormat) {
        this.fileSystemClient = Objects.requireNonNull(fileSystemClient, "Can't get fileSystemClient");
        this.loadFileService = Objects.requireNonNull(loadFileService, "Can't get loadFileService");
        this.loadAuctionController = Objects.requireNonNull(loadAuctionController, "Can't get loadAuctionController");
        this.asvPathToFiles = Objects.requireNonNull(asvPathToFiles, "Can't get asvPathToFiles");
        this.asvPathToResultFiles = Objects.requireNonNull(asvPathToResultFiles, "Can't get asvPathToResultFiles");
        this.asvPathToRadFiles = Objects.requireNonNull(asvPathToRadFiles, "Can't get asvPathToRadFiles");

        this.isNewFileNameFormat = Objects.requireNonNull(isNewFileNameFormat, "Can't get isNewFileNameFormat");
        this.eisPathToFiles = Objects.requireNonNull(eisPathToFiles, "Can't get eisPathToFiles");
    }

    public List<TenderSource> getTenderSource() {
        List<TenderSource> sourceList = new ArrayList<>();
        String todayDirectory = asvPathToFiles + getTodayDirectoryName();
        try {
            File[] ftpFiles = fileSystemClient.getFilesByPath(todayDirectory);
            for (File file : ftpFiles) {
                String fileName = file.getName().replace('В', 'B');
                if (loadAuctionController.checkExistenceOfBlockAuctionWithTradeId(fileName)) {
                    sourceList.add(getSourceOfTenderFile(todayDirectory + fileName));
                }
            }
            return sourceList;
        } catch (Exception e) {
            logger.error("Can't parse xml from FTP", e);
            return new ArrayList<>();
        }
    }

    public List<LoadFileDto> getDocumentsForLot(Set<String> alreadyLoaded, Lot lot, String blockTradeId, String tradeId) {
        String todayDirectory = asvPathToFiles + getTodayDirectoryName();
        if (blockTradeId != null) {
            todayDirectory = todayDirectory + blockTradeId + "/" + tradeId + "/";
        } else {
            todayDirectory = todayDirectory + tradeId + "/";
        }
        try {
            List<File> systemFiles = fileSystemClient.getDirectoriesByPath(todayDirectory);
            List<LoadFileDto> docFiles = new ArrayList<>();
            for (File file : systemFiles) {
                File[] documents = fileSystemClient.getFilesByPath(todayDirectory + file.getName() + "/");
                for (File doc : documents) {
                    String directory = todayDirectory + file.getName() + "/";
                    boolean isWantedFile = isNewFileNameFormat
                            ? isWantedFileByNewFormat(doc.getName(), lot.getId())
                            : isWantedFile(doc.getName(), lot.getNumber());
                    if (isWantedFile && !alreadyLoaded.contains(doc.getName())) {
                        docFiles.add(fillLoadFileDto(doc, directory, file.getName(), lot.getId().toString()));
                    }
                }
            }
            return docFiles;
        } catch (Exception e) {
            logger.error("Can't get files from system today directory = {}", todayDirectory, e);
            return new ArrayList<>();
        }
    }

    public List<LoadFileDto> getDocumentsForAuction(String blockTradeId) {
        String todayDirectory = asvPathToFiles + getTodayDirectoryName() + blockTradeId + "/";
        try {
            File[] documents = fileSystemClient.getFilesByPath(todayDirectory);
            List<LoadFileDto> docFiles = new ArrayList<>();
            for (File doc : documents) {
                if (!isXmlOrJson(doc.getName()) && !doc.isDirectory()) {
                    docFiles.add(fillLoadFileDto(doc, todayDirectory, FileTypeConstant.DOCUMENT.getName(), blockTradeId));
                } else if (isXmlOrJson(doc.getName())) {
                    docFiles.add(fillLoadFileDto(doc, todayDirectory, FileTypeConstant.XML_FILE.getName(), blockTradeId));
                }
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
            String pathFile = asvPathToRadFiles + "/" + fileName;
            byte[] source = loadFileService.getFileFromServerByPath(loadFile.getLfEisPath());
            boolean res = fileSystemClient.loadFileToFileServer(pathFile, source);
            return res ? Result.ok() : Result.error("Can't load asv tender file to file system storage. Filename = " + fileName);
        } catch (Exception e) {
            logger.error("Can't load asv tender file to file system storage. Filename = {}", fileName, e);
            return Result.error("Can't load asv tender file to file system storage. Filename = " + fileName);
        }
    }

    @Override
    public Result<Void, String> writeResultFile(String baAsvId, String json) {
        String fileName = baAsvId + ".json";
        try {
            String pathFile = asvPathToResultFiles + "/" + fileName;
            boolean res = fileSystemClient.loadFileToFileServer(pathFile, json.getBytes(StandardCharsets.UTF_8));
            return res ? Result.ok() : Result.error("Can't load asv results file to file system storage. Filename = " + fileName);
        } catch (Exception e) {
            logger.error("Can't load asv results file to file system storage. Filename = {}", fileName, e);
            return Result.error("Can't load asv results file to file system storage. Filename = " + fileName);
        }
    }

    private LoadFileDto fillLoadFileDto(File file, String docDirectory, String directory, String lotId) {
        LoadFileDto.LoadFileDtoBuilder fileBuilder = LoadFileDto.builder();
        Optional<FileTypeConstant> fileType = FileTypeConstant.getByName(directory);
        if (!fileType.isPresent()) {
            logger.warn("Can't define type of file = {}", directory);
        }
        String ext = getExtensionByName(file.getName());
        String docPath = docDirectory + file.getName();
        fileBuilder.withLfExtension(ext)
                .withLfType(fileType.map(FileTypeConstant::getId).orElseGet(FileTypeConstant.DOCUMENT::getId))
                .withLfFileNameAsv(file.getName())
                .withLfFtpPath(docPath)
                .withLfAsvId(lotId)
                .withLfSize((int) file.length());
        loadFileService.saveFileToServer(fileBuilder, ext, getDocFileSource(docPath));
        return fileBuilder.build();
    }

    public byte[] getDocFileSource(String path) {
        try {
            return fileSystemClient.getDocFileSource(path);
        } catch (Exception e) {
            logger.error("Can't get file source from system", e);
            return null;
        }
    }

    private TenderSource getSourceOfTenderFile(String directory) {
        try {
            List<File> xmlFiles = fileSystemClient.getFilesByPattern(fileSystemClient.getFilesByPath(directory), IS_XML);
            List<File> jsonFiles = fileSystemClient.getFilesByPattern(fileSystemClient.getFilesByPath(directory), IS_JSON);
            Optional<File> xmlFile = xmlFiles.stream().findFirst();
            Optional<File> jsonFile = jsonFiles.stream().findFirst();

            TenderSource.TenderSourceBuilder builder = TenderSource.builder();
            if (xmlFile.isPresent()) {
                builder.withXmlFileName(xmlFile.get().getName())
                        .withDirectoryName(directory)
                        .withXmlFileSource(fileSystemClient.getSourceFile(directory + "/" + xmlFile.get().getName()));
            }
            if (jsonFile.isPresent()) {
                builder.withJsonFileName(jsonFile.get().getName())
                        .withDirectoryName(directory)
                        .withJsonFileSource(fileSystemClient.getSourceFile(directory + "/" + jsonFile.get().getName()));
            }
            return builder.build();
        } catch (Exception e) {
            logger.error("Can't get source of tender file from FTP", e);
            return null;
        }
    }

    public Result<Void, String> writeDocsToZip(String zipFileName, List<DocFile> docFiles) {
        String archivePath = asvPathToResultFiles + "/" + zipFileName;

        try (FileOutputStream fos = new FileOutputStream(archivePath)) {
            try (ZipOutputStream zos = new ZipOutputStream(fos)) {
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
            return Result.ok();
        } catch (Exception ex) {
            logger.error("Can't load asv application docs zip archive. zipFileName = {}", zipFileName, ex);
            return Result.error("Can't load asv application docs zip archive. zipFileName = " + zipFileName);
        }
    }
}
