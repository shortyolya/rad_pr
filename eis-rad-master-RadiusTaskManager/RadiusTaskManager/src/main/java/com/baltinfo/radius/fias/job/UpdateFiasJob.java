package com.baltinfo.radius.fias.job;

import com.baltinfo.radius.fias.model.FiasInfoFile;
import com.baltinfo.radius.fias.model.Version;
import com.baltinfo.radius.fias.service.FiasCommonService;
import com.baltinfo.radius.fias.service.FiasServiceProvider;
import com.baltinfo.radius.fias.service.impl.VersionService;
import com.baltinfo.radius.utils.HibernateUtil;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.FileSystemUtils;

import javax.persistence.EntityManager;
import java.io.*;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.baltinfo.radius.fias.utils.FiasConstants.*;

/**
 * Class for updating the database FIAS
 *
 * @author Andrei Shymko
 * @since 12.10.2020
 */
public class UpdateFiasJob {

    private static final Logger logger = LoggerFactory.getLogger(UpdateFiasJob.class);
    private final FiasServiceProvider serviceProvider;
    private final VersionService versionService;

    @Value("${fias.download.file.info.url}")
    private String downloadFileInfoUrl;

    @Value("${fias.unzip.directory.path}")
    private String unZipDirectoryPath;

    public UpdateFiasJob(FiasServiceProvider serviceProvider, VersionService versionService) {
        this.serviceProvider = serviceProvider;
        this.versionService = versionService;
    }

    /**
     * Method responsible for updating the database FIAS, which starts according to the schedule.
     * If there are no records in the database, a full data archive load and save.
     * If there are records in the database, an updated archive load and than save or update.
     */
    @Scheduled(cron = "${job.cron.load.fias.update}")
    public void updateFiasGar() {

        logger.info("Update Fias Gar is starting.....");
        EntityManager entityManager = HibernateUtil.getInstance().getEntityManagerFias();

        Date lastUpdateDate = getLastUpdateDate(entityManager);

        if (findVersionByStatusLoadingInProgress(entityManager) != null) {
            logger.warn(FIAS_LOADING_IN_PROGRESS);
        } else if (lastUpdateDate == null) {
            logger.warn(FIAS_WARNING_MESSAGE);
            saveFullFiasGar(entityManager);
        } else {
            logger.info("Latest date of update FIAS GAR: {}", lastUpdateDate.toString());
            saveDeltaFiasGar(entityManager, lastUpdateDate);
        }
        logger.info("Update Fias Gar finished");
    }

    /**
     * Save update information about FIAS from fias.nalog.ru to database
     *
     * @param entityManager  for connection database
     * @param lastUpdateDate latest date of successful update FIAS
     */
    private void saveDeltaFiasGar(EntityManager entityManager, Date lastUpdateDate) {

        List<FiasInfoFile> updateFiasInfoFiles = getDeltaFiasInfoFiles(lastUpdateDate);

        for (FiasInfoFile fiasInfoFile : updateFiasInfoFiles) {
            saveVersionWhenStartUpdate(entityManager, fiasInfoFile);

            try {
                String deltaFiasURL = fiasInfoFile.getGarXMLDeltaURL();

                logger.info("Starting download deltaFiasURL: {}", deltaFiasURL);
                downloadZipFile(unZipDirectoryPath, deltaFiasURL);

                long sizeAllFiles = getSizeFiles(unZipDirectoryPath);
                long sizeLoadedFiles = 0L;

                Iterator<File> fileIterator = FileUtils.iterateFiles(new File(unZipDirectoryPath), XML_EXTENSIONS, true);
                while (fileIterator.hasNext()) {
                    File next = fileIterator.next();
                    String absolutePath = next.getCanonicalPath();

                    sizeLoadedFiles = sizeLoadedFiles + next.length();
                    logger.info("Parsing and saving file: {}", FilenameUtils.getName(absolutePath));
                    logger.info("Loaded... {} %", String.format("%.2f", ((sizeLoadedFiles * 1.00) / sizeAllFiles) * 100));

                    FiasCommonService fiasCommonService = serviceProvider.getService(absolutePath);
                    if (fiasCommonService != null) {
                        fiasCommonService.saveOrUpdateAll(entityManager, absolutePath);
                    }
                }
            } catch (Exception exception) {
                logger.error("Failed to update FIAS files", exception);
                saveVersionWhenFailedUpdate(entityManager, fiasInfoFile, exception.toString());
                entityManager.close();
                return;
            } finally {
                deleteTemporaryFiles(unZipDirectoryPath);
            }
            if (entityManager.isOpen()) {
                saveVersionWhenSuccessUpdate(entityManager, fiasInfoFile);
            }
        }
        entityManager.close();
    }

    /**
     * Save full information about FIAS from fias.nalog.ru to database
     *
     * @param entityManager for connection database
     */
    private void saveFullFiasGar(EntityManager entityManager) {

        FiasInfoFile fullFiasInfoFile = getFullFiasInfoFile();

        saveVersionWhenStartUpdate(entityManager, fullFiasInfoFile);

        try {
            String fullFiasURL = fullFiasInfoFile.getGarXMLFullURL();

            logger.info("Starting download fullFiasURL: {}", fullFiasURL);
            downloadZipFile(unZipDirectoryPath, fullFiasURL);

            long sizeAllFiles = getSizeFiles(unZipDirectoryPath);
            long sizeLoadedFiles = 0L;

            Iterator<File> fileIterator = FileUtils.iterateFiles(new File(unZipDirectoryPath), XML_EXTENSIONS, true);
            while (fileIterator.hasNext()) {
                File next = fileIterator.next();
                String absolutePath = next.getCanonicalPath();

                sizeLoadedFiles = sizeLoadedFiles + next.length();
                logger.info("Parsing and saving file: {}", FilenameUtils.getName(absolutePath));
                logger.info("Loaded... {} %", String.format("%.2f", ((sizeLoadedFiles * 1.00) / sizeAllFiles) * 100));

                FiasCommonService fiasCommonService = serviceProvider.getService(absolutePath);
                if (fiasCommonService != null) {
                    fiasCommonService.saveOrUpdateAll(entityManager, absolutePath);
                }
            }
        } catch (Exception exception) {
            logger.error("Failed to update FIAS files", exception);
            saveVersionWhenFailedUpdate(entityManager, fullFiasInfoFile, exception.toString());
            entityManager.close();
            return;
        } finally {
            deleteTemporaryFiles(unZipDirectoryPath);
        }
        if (entityManager.isOpen()) {
            saveVersionWhenSuccessUpdate(entityManager, fullFiasInfoFile);
        }
        entityManager.close();
    }

    /**
     * Get files with newest information about loading delta FIAS
     *
     * @param lastUpdateDate of successful update FIAS
     * @return list of FIAS information files
     */
    private List<FiasInfoFile> getDeltaFiasInfoFiles(Date lastUpdateDate) {

        Date updateDate = Date.valueOf(lastUpdateDate.toLocalDate().plusDays(1L));

        return getAllFiasInfoFiles().stream()
                .filter(infoFile -> infoFile.getDate().after(updateDate))
                .filter(infoFile -> infoFile.getGarXMLDeltaURL().trim().length() != 0)
                .sorted(Comparator.comparingLong(FiasInfoFile::getVersionId))
                .collect(Collectors.toList());
    }

    /**
     * Get a file with newest information about loading full FIAS
     *
     * @return FIAS information file
     */
    private FiasInfoFile getFullFiasInfoFile() {

        return getAllFiasInfoFiles().stream()
                .filter(infoFile -> infoFile.getGarXMLFullURL().trim().length() != 0)
                .max(Comparator.comparing(FiasInfoFile::getDate))
                .orElse(null);
    }

    /**
     * Get latest date of successful update FIAS
     *
     * @param entityManager for connection database
     * @return latest date of successful update FIAS
     */
    private Date getLastUpdateDate(EntityManager entityManager) {
        Version version = versionService.findByLatestDateAndLoadSign(entityManager, SUCCESSFUL_LOAD_SIGN);

        return version != null ? version.getDate() : null;
    }

    /**
     * Download and unzip files
     *
     * @param unZipDirectory path, where need to unzip archive
     * @param fileURL        URL of zip archive
     * @throws IOException throw, when have no file access
     */
    private void downloadZipFile(String unZipDirectory, String fileURL) throws IOException {

        File destDir = new File(unZipDirectory);
        if (!destDir.exists()) {
            destDir.mkdir();
        } else {
            FileUtils.cleanDirectory(destDir);
        }

        byte[] buffer = new byte[10240];

        try (InputStream inputStream = new URL(fileURL).openStream();
             BufferedInputStream bis = new BufferedInputStream(inputStream);
             ZipInputStream zis = new ZipInputStream(bis)) {

            ZipEntry zipEntry = zis.getNextEntry();

            while (zipEntry != null) {
                if (!zipEntry.isDirectory()) {
                    File newFile = createNewFile(destDir, zipEntry);
                    FileOutputStream fos = new FileOutputStream(newFile, true);

                    logger.info("Download and unzip {} ({} Kb)", FilenameUtils.getName(zipEntry.getName()), zipEntry.getSize() / 1000);

                    int length;
                    while ((length = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, length);
                    }
                    fos.close();
                    zipEntry = zis.getNextEntry();
                }
            }
        }
    }

    /**
     * Load information from fias.nalog.ru
     * about date archives and download URL update FIAS GAR
     *
     * @return list of files, which contain information about date archives and download URL
     */
    private List<FiasInfoFile> getAllFiasInfoFiles() {

        List<FiasInfoFile> fiasInfoFiles = null;
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet(downloadFileInfoUrl);
            HttpResponse httpResponse = httpclient.execute(httpget);

            String json = EntityUtils.toString(httpResponse.getEntity());

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

            fiasInfoFiles = Arrays.asList(objectMapper.readValue(json, FiasInfoFile[].class));
        } catch (IOException exception) {
            logger.error("Failed to load FiasInfoFiles", exception);
        }
        return fiasInfoFiles;
    }

    /**
     * Create files in unzip directory with the same name like in zip entry file and return its
     *
     * @param destinationDir directory, where store unzip files
     * @param zipEntry       file in zip archive
     * @return created File
     * @throws IOException throw, when have no file access
     */
    private File createNewFile(File destinationDir, ZipEntry zipEntry) throws IOException {

        String fileName = FilenameUtils.getName(zipEntry.getName());
        File destFile = new File(destinationDir, fileName);

        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }
        return destFile;
    }

    /**
     * Method for recursively deleting files from file system
     *
     * @param files path file
     */
    private void deleteTemporaryFiles(String... files) {

        for (String fileName : files) {
            try {
                FileSystemUtils.deleteRecursively(Paths.get(fileName));
            } catch (IOException exception) {
                logger.warn("Failed to delete temporary file: {}", fileName);
            }
        }
    }

    private void saveVersionWhenStartUpdate(EntityManager entityManager, FiasInfoFile fiasInfoFile) {
        Version version = new Version(fiasInfoFile.getVersionId(), fiasInfoFile.getDate(), START_LOAD_SIGN);
        versionService.saveOrUpdate(entityManager, version);
    }

    private void saveVersionWhenSuccessUpdate(EntityManager entityManager, FiasInfoFile fiasInfoFile) {
        Version version = new Version(fiasInfoFile.getVersionId(), fiasInfoFile.getDate(), SUCCESSFUL_LOAD_SIGN);
        versionService.saveOrUpdate(entityManager, version);
    }

    private void saveVersionWhenFailedUpdate(EntityManager entityManager, FiasInfoFile fiasInfoFile, String errorMessage) {
        Version version = new Version(fiasInfoFile.getVersionId(), fiasInfoFile.getDate(), ERROR_LOAD_SIGN, errorMessage);
        versionService.saveOrUpdate(entityManager, version);
    }

    private Version findVersionByStatusLoadingInProgress(EntityManager entityManager) {
        return versionService.findVersionByStatus(entityManager, START_LOAD_SIGN);
    }

    private long getSizeFiles(String unZipDirectoryPath) {
        long sizeAllFiles = 0L;
        Iterator<File> fileIterator = FileUtils.iterateFiles(new File(unZipDirectoryPath), XML_EXTENSIONS, true);

        while (fileIterator.hasNext()) {
            sizeAllFiles = sizeAllFiles + fileIterator.next().length();
        }
        return sizeAllFiles;
    }
}