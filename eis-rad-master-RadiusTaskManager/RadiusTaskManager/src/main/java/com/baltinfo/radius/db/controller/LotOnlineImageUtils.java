package com.baltinfo.radius.db.controller;

import com.sun.media.jai.codec.FileSeekableStream;
import com.sun.media.jai.codec.SeekableStream;
import org.apache.tika.Tika;
import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.media.jai.JAI;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Suvorina Aleksandra
 * @since 19.03.2021
 */
public class LotOnlineImageUtils {
    private static final Logger logger = LoggerFactory.getLogger(LotOnlineImageUtils.class);

    private static final String THUMBNAIL_FORMAT = "png";
    private static final MimeTypes ALL_TYPES = MimeTypes.getDefaultMimeTypes();
    private final Tika tika;

    public LotOnlineImageUtils() {
        this.tika = new Tika();
    }


    public byte[] preparePhoto(File file, int size) {
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            writeResizeImage(file, size, THUMBNAIL_FORMAT, output);
            return output.toByteArray();
        } catch (Exception e) {
            logger.error("Error preparePhoto file = {}, size = {}", file, size, e);
        }
        return null;
    }

    private void writeResizeImage(File file, int imageTargetSize, String imageFormat, OutputStream out) throws IOException, MimeTypeException {
        BufferedImage resizedImage = null;
        try (SeekableStream seekableStream = new FileSeekableStream(file)) {

            ParameterBlock pb = new ParameterBlock();
            pb.add(seekableStream);
            BufferedImage photo = JAI.create(getExtensionFromMimeType(getMimeType(file)), pb).getAsBufferedImage();

            // resize
            resizedImage = Scalr.resize(photo, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.AUTOMATIC, imageTargetSize, Scalr.OP_ANTIALIAS);
            // normalize with padding
            resizedImage = normalizeThumbnail(resizedImage);
            ImageIO.write(resizedImage, imageFormat, out);
        }

    }

    private String getExtensionFromMimeType(String mimeType) throws MimeTypeException {
        MimeType type = ALL_TYPES.forName(mimeType);
        return type.getType().getSubtype();
    }

    private String getMimeType(File file) throws MimeTypeException, IOException {
        return tika.detect(file);
    }

    private BufferedImage normalizeThumbnail(BufferedImage photoThumbnail) {
        int thumbnailHeight = photoThumbnail.getHeight();
        int thumbnailWidth = photoThumbnail.getWidth();
        if (thumbnailHeight != thumbnailWidth) {
            int pad = Math.abs(thumbnailHeight - thumbnailWidth) / 2;
            if (pad > 0) {
                photoThumbnail = Scalr.pad(photoThumbnail, pad, new Color(0, 0, 0, 0));
                if (thumbnailHeight > thumbnailWidth) {
                    photoThumbnail = Scalr.crop(photoThumbnail, 0, pad, photoThumbnail.getWidth(), thumbnailHeight);
                } else {
                    photoThumbnail = Scalr.crop(photoThumbnail, pad, 0, thumbnailWidth, photoThumbnail.getHeight());
                }
            }
        }

        return photoThumbnail;
    }

}
