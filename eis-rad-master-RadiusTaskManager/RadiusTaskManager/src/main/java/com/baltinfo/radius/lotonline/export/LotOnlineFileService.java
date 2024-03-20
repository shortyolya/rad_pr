package com.baltinfo.radius.lotonline.export;

import com.baltinfo.radius.application.configuration.LotOnlineServerProperties;
import com.baltinfo.radius.utils.Result;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.apache.poi.util.IOUtils;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author css
 */
public class LotOnlineFileService {

    protected static org.slf4j.Logger logger = LoggerFactory.getLogger(LotOnlineFileService.class);

    private final LotOnlineServerProperties lotOnlineServerProperties;

    public LotOnlineFileService(LotOnlineServerProperties lotOnlineServerProperties) {
        this.lotOnlineServerProperties = lotOnlineServerProperties;
    }

    public Result<Void, String> writePhotoToServer(String path, byte[] buf) {
        return writeFileToServer(path, lotOnlineServerProperties.getPhotoPath(), buf);
    }

    public Result<Void, String> writeDocumentToServer(String path, byte[] buf) {
        return writeFileToServer(path, lotOnlineServerProperties.getDocPath(), buf);
    }

    private Result<Void, String> writeFileToServer(String path, String dir, byte[] buf) {
        Session session = null;
        Channel channel = null;
        try {
            JSch jsch = new JSch();
            String home = System.getProperty("user.home");
            jsch.addIdentity(home + "/.ssh/id_rsa");
            session = jsch.getSession(lotOnlineServerProperties.getUser(),
                    lotOnlineServerProperties.getHost(),
                    lotOnlineServerProperties.getPort());
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            session.connect();

            // 2017-02-13 kya: выставляем права 775 на соответствующую директорию рекурсивно (Tomcat Lot-online создает свою структуру директорий с правами 755),
            // право на выполнение этого скрипта от root (и только от него) выставляется в /etc/sudoers;
            // вносить изменения в /etc/sudoers ТОЛЬКО посредством visudo, иначе можно потерять возможность выполнять sudo на сервере.
            String setPermissionsSh = "sudo /home/radius/setPermissionsOnDir.sh ";

            String[] parts = path.substring(0, path.lastIndexOf("/")).split("/");
            String script = "";
            String mdir = "";
            // 2017-02-13 kya: закомментировал версию css
//            for (int i = 0; i < parts.length; i++) {
//                mdir += parts[i] + "/";
//                script += "install -d -m 0775 " + dir + mdir + (i < (parts.length - 1) ? " && " : "");
//            }
//            executeCommand(script, session);

            // 2017-02-13 kya: TODO после доработки Lot-online (загрузка файлов в ФС) надо переписать это временное решение
            for (int i = 0; i < parts.length; i++) {
                mdir += parts[i] + "/";
                script = setPermissionsSh + dir + mdir;
                executeCommand(script, session);
            }
            String rfile = dir + path;
            String rdir = rfile.substring(0, rfile.lastIndexOf("/"));
            String filename = rfile.substring(rfile.lastIndexOf("/") + 1);

            channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel;

            sftpChannel.cd(rdir);
            sftpChannel.put(new ByteArrayInputStream(buf), filename);

            sftpChannel.exit();

        } catch (Exception e) {
            logger.error("Error writeFileToServer path = {}, dir = {}", path, dir, e);
            return Result.error(e.getMessage());
        } finally {
            if (channel != null) {
                channel.disconnect();
            }
            if (session != null) {
                session.disconnect();
            }
        }
        return Result.ok();
    }

    public Result<byte[], String> getFileFromServer(String path) {
        Session session = null;
        Channel channel = null;
        try {
            String user = lotOnlineServerProperties.getUser();
            String host = lotOnlineServerProperties.getHost();
            String dir = lotOnlineServerProperties.getProtocolPath();
            String rfile = dir + path;
            Integer port = lotOnlineServerProperties.getPort();

            JSch jsch = new JSch();
            String home = System.getProperty("user.home");
            jsch.addIdentity(home + "/.ssh/id_rsa");
            session = jsch.getSession(user, host, new Integer(port));
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            session.connect();

            String rdir = rfile.substring(0, rfile.lastIndexOf("/"));
            String filename = rfile.substring(rfile.lastIndexOf("/") + 1);

            channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel;

            sftpChannel.cd(rdir);
            byte[] buff;
            try (InputStream is = sftpChannel.get(filename)) {
                buff = IOUtils.toByteArray(is);
            }

            sftpChannel.exit();
            return Result.ok(buff);

        } catch (Exception e) {
            logger.error("Error getFileFromServer path = {}", path, e);
            return Result.error(e.getMessage());
        } finally {
            if (channel != null) {
                channel.disconnect();
            }
            if (session != null) {
                session.disconnect();
            }
        }
    }

    private void executeCommand(String script, Session session) throws JSchException, IOException {
        ChannelExec channel = (ChannelExec) session.openChannel("exec");
        channel.setCommand(script);

        InputStream in = channel.getInputStream();
        channel.connect();

        byte[] tmp = new byte[1024];
        while (true) {
            while (in.available() > 0) {
                int i = in.read(tmp, 0, 1024);
                if (i < 0) {
                    break;
                }
            }
            if (channel.isClosed()) {
                break;
            }
            try {
                Thread.sleep(100);
            } catch (Exception ee) {
                logger.error(ee.getMessage(), ee);
            }
        }
        channel.disconnect();
    }

}
