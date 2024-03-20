/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.baltinfo.radius.utils;

import com.baltinfo.radius.bankruptcy.constant.ImageSize;
import com.baltinfo.radius.db.model.DocFile;
import com.sun.media.jai.codec.FileSeekableStream;
import com.sun.media.jai.codec.SeekableStream;
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.exif.ExifIFD0Directory;
import org.apache.commons.io.FilenameUtils;
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
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author ssv
 */
public class ImageUtils {
    private static Logger logger = LoggerFactory.getLogger(ImageUtils.class);
    private static final Tika TIKA = new Tika();
    private static final MimeTypes ALL_TYPES = MimeTypes.getDefaultMimeTypes();

    public static String getExtensionFromFile(File file) {
        try {
            String mimeType = TIKA.detect(file);
            MimeType type = ALL_TYPES.forName(mimeType);
            return type.getType().getSubtype();
        } catch (Exception ex) {
            logger.error("Error in getExtensionFromFile(), file = {}", file, ex);
        }
        return null;
    }

    public static byte[] createImage(byte[] fileBody, String fileName, ImageSize size) throws IOException {
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            ImageUtils.writeImage(fileBody, FilenameUtils.getExtension(fileName), size, output);
            return output.toByteArray();
        }
    }

    public static void alignOrientation(File file) {
        BufferedImage scaledImg = null;
        int orientation = 1;
        try {
            scaledImg = ImageIO.read(file);
            Metadata metadata = ImageMetadataReader.readMetadata(file);
            if (metadata.containsDirectoryOfType(ExifIFD0Directory.class)) {
                ExifIFD0Directory exifIFD0Directory = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
                orientation = exifIFD0Directory.getInt(ExifIFD0Directory.TAG_ORIENTATION);
            }
        } catch (MetadataException ex) {
            logger.error("Error checkOrientation for get TAG_ORIENTATION ", ex);
        } catch (IOException|ImageProcessingException ex) {
            logger.error("Error checkOrientation for get Metadata", ex);
        }
        if (scaledImg != null && orientation != 1) {
            switch (orientation) {
                case 2: // Flip X
                    scaledImg = Scalr.rotate(scaledImg, Scalr.Rotation.FLIP_HORZ);
                    break;
                case 3: // PI rotation
                    scaledImg = Scalr.rotate(scaledImg, Scalr.Rotation.CW_180);
                    break;
                case 4: // Flip Y
                    scaledImg = Scalr.rotate(scaledImg, Scalr.Rotation.FLIP_VERT);
                    break;
                case 5: // - PI/2 and Flip X
                    scaledImg = Scalr.rotate(scaledImg, Scalr.Rotation.CW_90);
                    scaledImg = Scalr.rotate(scaledImg, Scalr.Rotation.FLIP_HORZ);
                    break;
                case 6: // -PI/2 and -width
                    scaledImg = Scalr.rotate(scaledImg, Scalr.Rotation.CW_90);
                    break;
                case 7: // PI/2 and Flip
                    scaledImg = Scalr.rotate(scaledImg, Scalr.Rotation.CW_90);
                    scaledImg = Scalr.rotate(scaledImg, Scalr.Rotation.FLIP_VERT);
                    break;
                case 8: // PI / 2
                    scaledImg = Scalr.rotate(scaledImg, Scalr.Rotation.CW_270);
                    break;
                default:
                    break;
            }
            try {
                ImageIO.write(scaledImg, "jpg", file);
            } catch (IOException ex) {
                logger.error("Error checkOrientation for write Metadata", ex);
            }
        }
    }

    private static void writeImage(byte[] fileBody, String fileFormat, ImageSize size, OutputStream out) {
        BufferedImage resizedImage;
        try {
            BufferedImage photo = createImageFromBytes(fileBody);

            if (photo.getColorModel().hasAlpha()) {
                photo = dropAlphaChannel(photo);
            }

            resizedImage = Scalr.resize(photo,
                    Scalr.Method.ULTRA_QUALITY,
                    (float) size.getWidth() / size.getHeight() >= (float) photo.getWidth() / photo.getHeight()
                            ? Scalr.Mode.FIT_TO_WIDTH
                            : Scalr.Mode.FIT_TO_HEIGHT,
                    (float) size.getWidth() / size.getHeight() >= (float) photo.getWidth() / photo.getHeight()
                            ? size.getWidth()
                            : size.getHeight(),
                    Scalr.OP_ANTIALIAS);

            if (size == ImageSize.THUMBNAIL_SIZE) {
                resizedImage = normalizeImage(size, resizedImage);
            }

            String mimeFormat = getFormatFromMimeType(fileBody);
            ImageIO.write(resizedImage, mimeFormat != null ? mimeFormat : fileFormat, out);
        } catch (Exception ex) {
            logger.error("Error in writeImage():", ex);
        }
    }

    public static DocFile createPreviewFile(DocFile docFile, String pathToFiles) {
        DocFile previewFile = new DocFile();
        previewFile.setDfFilePath(FilenameUtils.removeExtension(docFile.getDfFilePath()) + "-preview.jpg");
        previewFile.setDfNameDb(FilenameUtils.removeExtension(docFile.getDfNameDb()) + "-preview.jpg");
        previewFile.setDfName(FilenameUtils.removeExtension(docFile.getDfName()) + "-preview.jpg");
        previewFile.setDfExt("jpg");

        File imgFile = new File(pathToFiles + docFile.getDfFilePath());

        try {
            BufferedImage previewImg = scale(ImageIO.read(imgFile), 0.5);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(previewImg, "jpg", baos);
            previewFile.setDfSize(baos.size());
            baos.close();

            ImageIO.write(previewImg, "jpg", new File(imgFile.getParent() + File.separator + previewFile.getDfNameDb()));
            return previewFile;
        } catch (Throwable ex) {
            logger.error("Error creating preview file for dfUnid = {}", docFile.getDfUnid(), ex);
        }
        return null;
    }

    private static BufferedImage scale(BufferedImage source,double ratio) {
        int w = (int) (source.getWidth() * ratio);
        int h = (int) (source.getHeight() * ratio);
        BufferedImage bi = getCompatibleImage(w, h);
        Graphics2D g2d = bi.createGraphics();
        double xScale = (double) w / source.getWidth();
        double yScale = (double) h / source.getHeight();
        AffineTransform at = AffineTransform.getScaleInstance(xScale,yScale);
        g2d.drawRenderedImage(source, at);
        g2d.dispose();
        return bi;
    }

    private static BufferedImage getCompatibleImage(int w, int h) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        GraphicsConfiguration gc = gd.getDefaultConfiguration();
        BufferedImage image = gc.createCompatibleImage(w, h);
        return image;
    }

    public static void writeResizeJpg(File file, int imageTargetSize, OutputStream out) throws IOException, MimeTypeException {
        writeResizeImage(file, imageTargetSize, "jpg", false, out);
    }

    public static void writeResizeImage(File file, int imageTargetSize, String imageFormat, boolean isThumbnail, OutputStream out) throws IOException, MimeTypeException {
        BufferedImage resizedImage = null;
        try (SeekableStream seekableStream = new FileSeekableStream(file);) {
            ParameterBlock pb = new ParameterBlock();
            pb.add(seekableStream);
            BufferedImage photo = JAI.create(getExtensionFromMimeType(getMimeType(file)), pb).getAsBufferedImage();

            if (isThumbnail) {
                // resize
                resizedImage = Scalr.resize(photo, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.AUTOMATIC, imageTargetSize, Scalr.OP_ANTIALIAS);
                // normalize with padding
                resizedImage = normalizeThumbnail(resizedImage);
            } else {
                if (photo.getColorModel().hasAlpha()) {
                    photo = dropAlphaChannel(photo);
                }
                // adaptive resize without padding
                int radapiHeight = photo.getHeight();
                int radapiWidth = photo.getWidth();

                if (radapiHeight >= radapiWidth) {
                    resizedImage = Scalr.resize(photo, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_TO_WIDTH, imageTargetSize, Scalr.OP_ANTIALIAS);
                } else {
                    resizedImage = Scalr.resize(photo, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_TO_HEIGHT, imageTargetSize, Scalr.OP_ANTIALIAS);
                }
            }

            try {
                ImageIO.write(resizedImage, imageFormat, out);
            } catch (Exception ex) {
                logger.error("Error write resised image. Error message = {}", ex.getMessage());
            }
        }
    }

    public static BufferedImage normalizeThumbnail(BufferedImage photoThumbnail) {
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

    public static String getExtensionFromMimeType(String mimeType) throws MimeTypeException {
        MimeType type = ALL_TYPES.forName(mimeType);
        return type.getType().getSubtype();
    }

    public static String getMimeType(File file) throws MimeTypeException, IOException {
        return TIKA.detect(file);
    }

    private static BufferedImage createImageFromBytes(byte[] imageData) {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(imageData)) {
            return ImageIO.read(bais);
        } catch (IOException ex) {
            logger.error("Error in createImageFromByte():", ex);
        }
        return null;
    }

    private static BufferedImage normalizeImage(ImageSize size, BufferedImage photo) {
        int x = (photo.getWidth() - size.getWidth()) / 2;
        int y = (photo.getHeight() - size.getHeight()) / 2;
        return Scalr.crop(photo, x, y, size.getWidth(), size.getHeight());
    }

    private static BufferedImage dropAlphaChannel(BufferedImage src) {
        BufferedImage convertedImg = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);
        convertedImg.getGraphics().drawImage(src, 0, 0, null);
        return convertedImg;
    }

    private static String getFormatFromMimeType(byte[] file) {
        try {
            String mimeType = TIKA.detect(file);
            MimeType type = ALL_TYPES.forName(mimeType);
            return type.getType().getSubtype();
        } catch (Exception ex) {
            logger.error("Error in getFormatFromMimeType(): ", ex);
        }
        return null;
    }
}
