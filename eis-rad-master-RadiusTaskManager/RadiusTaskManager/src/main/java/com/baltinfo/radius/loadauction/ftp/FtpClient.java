package com.baltinfo.radius.loadauction.ftp;

import com.baltinfo.radius.utils.Result;
import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * FTP клиент для доступа к FTP серверу
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 30.01.2020
 */
public class FtpClient {

    private static final Logger logger = LoggerFactory.getLogger(FtpClient.class);
    public static final String separator = "/";

    private final String server;
    private final int port;
    private final String user;
    private final String password;
    private final int timeout;

    public FtpClient(String server, int port, String user, String password, int timeout) {
        this.server = server;
        this.port = port;
        this.user = user;
        this.password = password;
        this.timeout = timeout;
    }

    public FTPFile[] getFilesByPath(String path) {
        FTPClient ftp = null;
        try {
            ftp = open();
            return ftp.listFiles(path);
        } catch (Exception e) {
            logger.error("Can't get list of files by path = {}", path, e);
            return new FTPFile[0];
        } finally {
            close(ftp);
        }
    }

    public FTPFile[] getDirectoriesByPath(String path) {
        FTPClient ftp = null;
        try {
            ftp = open();
            return ftp.listDirectories(path);
        } catch (Exception e) {
            logger.error("Can't get list of directories by path = {}", path, e);
            return new FTPFile[0];
        } finally {
            close(ftp);
        }
    }

    public String getSourceFile(String filePath) {
        FTPClient ftp = null;
        try {
            ftp = open();
            try (InputStream inputStream = ftp.retrieveFileStream(filePath)) {
                return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            }
        } catch (Exception e) {
            logger.error("Can't get xml file source", e);
            return null;
        } finally {
            close(ftp);
        }
    }

    public byte[] getDocFileSource(String filePath) {
        FTPClient ftp = null;
        try {
            ftp = open();
            try (InputStream inputStream = ftp.retrieveFileStream(filePath)) {
                return IOUtils.toByteArray(inputStream);
            }
        } catch (Exception e) {
            logger.error("Can't get file source/ filePath = {}", filePath, e);
            return null;
        } finally {
            close(ftp);
        }
    }

    public boolean loadFileToFtp(String dir, String fileName, byte[] source) {
        FTPClient ftp = null;
        try {
            ftp = open();
            try (InputStream input = new ByteArrayInputStream(source)) {
                if (makeDirectories(ftp, dir)) {
                    return ftp.storeFile(dir + fileName, input);
                }
            }
        } catch (IOException e) {
            logger.error("Can't load asv tender file. dir = {}, fileName = {}", dir, fileName, e);
        } finally {
            close(ftp);
        }
        return false;
    }

    public static boolean makeDirectories(FTPClient ftpClient, String dirPath) throws IOException {
        String[] pathElements = dirPath.split("/");
        if (pathElements != null && pathElements.length > 0) {
            for (String singleDir : pathElements) {
                if (singleDir.isEmpty()) continue;
                boolean existed = ftpClient.changeWorkingDirectory(singleDir);
                if (!existed) {
                    boolean created = ftpClient.makeDirectory(singleDir);
                    if (created) {
                        ftpClient.changeWorkingDirectory(singleDir);
                    } else {
                        logger.error("Can't create directory on ftp. dirPath = {}, dir = {}", dirPath, singleDir);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public Result<Boolean, String> createDirectory(String fullPath) {
        FTPClient ftp = null;
        try {
            ftp = open();
            return Result.ok(ftp.makeDirectory(fullPath));
        } catch (Exception e) {
            logger.error("Can't create directory by path = {}", fullPath, e);
            return Result.error("Can't create directory by path = " + fullPath);
        } finally {
            close(ftp);
        }
    }

    public boolean deleteFile(String filePath) {
        FTPClient ftp = null;
        try {
            ftp = open();
            return ftp.deleteFile(filePath);
        } catch (Exception e) {
            logger.error("Can't delete file by path = {}", filePath, e);
            return false;
        } finally {
            close(ftp);
        }
    }

    public boolean deleteDirectory(String directoryPath) {
        FTPClient ftp = null;
        try {
            ftp = open();
            return ftp.removeDirectory(directoryPath);
        } catch (Exception e) {
            logger.error("Can't delete file by path = {}", directoryPath, e);
            return false;
        } finally {
            close(ftp);
        }
    }

    private FTPClient open() throws IOException {
        FTPClient ftp = new FTPClient();
        ftp.connect(server, port);
        ftp.enterLocalPassiveMode();
        int reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            throw new IOException("Can't connect to FTP server");
        }
        ftp.login(user, password);
        ftp.setFileType(FTP.BINARY_FILE_TYPE);
        ftp.setDefaultTimeout(timeout);
        ftp.setDataTimeout(timeout);
        ftp.setConnectTimeout(timeout);
        ftp.setSoTimeout(timeout);
        ftp.setControlKeepAliveTimeout(convertToSeconds(timeout));
        ftp.setControlKeepAliveReplyTimeout(timeout);
        return ftp;
    }

    private int convertToSeconds(int time) {
        return time / 1000;
    }

    private void close(FTPClient ftp) {
        if (ftp == null) {
            return;
        }
        if (!ftp.isConnected()) {
            return;
        }
        try {
            ftp.disconnect();
        } catch (IOException ioe) {
            logger.error("Can't close FTP connection");
        }
    }

}
