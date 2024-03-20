package com.baltinfo.radius.feed.ftp;

import com.baltinfo.radius.utils.Result;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

/**
 * <p>
 * Клиент для работы с FTP фидов
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 10.08.2020
 */
public class FeedFtpClient {

    private static final Logger logger = LoggerFactory.getLogger(FeedFtpClient.class);
    public static final String separator = "/";

    private final String server;
    private final int port;
    private final String user;
    private final String password;
    private final int timeout;

    public FeedFtpClient(String server, int port, String user, String password, int timeout) {
        this.server = server;
        this.port = port;
        this.user = user;
        this.password = password;
        this.timeout = timeout;
    }

    public List<String> getFilesByPath(String path) {

        ChannelSftp sftp = null;
        Session jschSession = null;
        try {
            jschSession = open();
            sftp = (ChannelSftp) jschSession.openChannel("sftp");
            sftp.connect();
            Vector files = sftp.ls(path);
            List<String> fileNames = new ArrayList<>();
            for (Object file : files) {
                if (file instanceof ChannelSftp.LsEntry) {
                    ChannelSftp.LsEntry entry = (ChannelSftp.LsEntry) file;
                    if (!entry.getAttrs().isDir()) {
                        fileNames.add(entry.getFilename());
                    }
                }
            }
            return fileNames;
        } catch (SftpException sftpException) {
            return new ArrayList<>();
        } catch (Exception e) {
            logger.error("Can't get list of files by path = {}", path, e);
            return new ArrayList<>();
        } finally {
            close(jschSession, sftp);
        }
    }

    public List<String> getDirectoriesNameByPath(String path) {
        ChannelSftp sftp = null;
        Session jschSession = null;
        try {
            jschSession = open();
            sftp = (ChannelSftp) jschSession.openChannel("sftp");
            sftp.connect();
            Vector files = sftp.ls(path);
            List<String> directories = new ArrayList<>();
            for (Object file : files) {
                if (file instanceof ChannelSftp.LsEntry) {
                    ChannelSftp.LsEntry entry = (ChannelSftp.LsEntry) file;
                    if (entry.getAttrs().isDir()
                            && !entry.getFilename().equals(".")
                            && !entry.getFilename().equals("..")) {
                        directories.add(entry.getFilename());
                    }
                }
            }
            return directories;
        } catch (SftpException sftpException) {
            return new ArrayList<>();
        } catch (Exception e) {
            logger.error("Can't get list of directories by path = {}", path, e);
            return new ArrayList<>();
        } finally {
            close(jschSession, sftp);
        }
    }


    public boolean loadFileToFtp(String filePath, byte[] source) {
        ChannelSftp sftp = null;
        Session jschSession = null;
        try {
            jschSession = open();
            sftp = (ChannelSftp) jschSession.openChannel("sftp");
            sftp.connect();
            try (InputStream input = new ByteArrayInputStream(source)) {
                sftp.put(input, filePath);
            }
            return true;
        } catch (Exception e) {
            logger.error("Can't upload file to FTP");
            return false;
        } finally {
            close(jschSession, sftp);
        }
    }

    public Result<Void, String> createDirectory(String fullPath) {
        ChannelSftp sftp = null;
        Session jschSession = null;
        try {
            jschSession = open();
            sftp = (ChannelSftp) jschSession.openChannel("sftp");
            sftp.connect();
            sftp.mkdir(fullPath);
            return Result.ok();
        } catch (SftpException sftpException) {
            if (sftpException.id == ChannelSftp.SSH_FX_FAILURE) {
                return Result.ok();  //Directory already exist
            } else {
                logger.error("Can't create directory by path = {}", fullPath, sftpException);
                return Result.error("Can't create directory by path = " + fullPath);
            }
        } catch (Exception e) {
            logger.error("Can't create directory by path = {}", fullPath, e);
            return Result.error("Can't create directory by path = " + fullPath);
        } finally {
            close(jschSession, sftp);
        }
    }

    public void deleteFile(String filePath) {
        ChannelSftp sftp = null;
        Session jschSession = null;
        try {
            jschSession = open();
            sftp = (ChannelSftp) jschSession.openChannel("sftp");
            sftp.connect();
            sftp.rm(filePath);
        } catch (Exception e) {
            logger.error("Can't delete file by path = {}", filePath, e);
        } finally {
            close(jschSession, sftp);
        }
    }

    public void deleteDirectory(String directoryPath) {
        ChannelSftp sftp = null;
        Session jschSession = null;
        try {
            jschSession = open();
            sftp = (ChannelSftp) jschSession.openChannel("sftp");
            sftp.connect();
            sftp.rmdir(directoryPath);
        } catch (Exception e) {
            logger.error("Can't delete file by path = {}", directoryPath, e);
        } finally {
            close(jschSession, sftp);
        }
    }

    private Session open() throws JSchException {
        JSch jsch = new JSch();
        Session jschSession = jsch.getSession(user, server, port);
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        jschSession.setConfig(config);
        jschSession.setPassword(password);
        jschSession.connect(timeout);
        return jschSession;
    }

    private void close(Session jschSession, ChannelSftp sftp) {
        try {
            if (sftp != null) {
                sftp.exit();
                sftp.disconnect();
            }
            if (jschSession != null) {
                jschSession.disconnect();
            }
        } catch (Exception e) {
            logger.error("Can't close SFTP connection");
        }
    }
}
