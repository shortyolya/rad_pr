package com.baltinfo.radius.utils;

import net.coobird.thumbnailator.filters.Watermark;
import net.coobird.thumbnailator.geometry.Positions;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * <p>
 * </p>
 *
 * @author ssv
 * @since 27.06.2019
 */
public class ImageWatermark {

    private static Logger logger = LoggerFactory.getLogger(ImageWatermark.class);
    private static final float WATERMARK_SCALE = 0.2f;
    private static final float WATERMARK_TRANSPARENCY = 0.4f;
    private static final Positions WATERMARK_POSITIONS = Positions.CENTER;
    private String filePath;
    private InputStream watermarkFile;

    public ImageWatermark(String filePath, InputStream watermarkFile) {
        this.filePath = filePath;
        this.watermarkFile = watermarkFile;
    }

    public byte[] add() {
        byte[] bytes = putWatermark(WATERMARK_POSITIONS, WATERMARK_SCALE, WATERMARK_TRANSPARENCY);
        if (bytes != null) {
            return bytes;
        } else {
            try {
                Path path = Paths.get(filePath);
                return Files.readAllBytes(path);
            } catch (Exception ex) {
                logger.error("Error in add(), filePath = {}", filePath, ex);
            }
        }
        return null;
    }

    public byte[] add(Positions positions, float wmScale, float wmTransparency) {
        return putWatermark(positions, wmScale, wmTransparency);
    }

    private byte[] putWatermark(Positions positions, float wmScale, float wmTransparency) {
        try {
            File file = new File(filePath);

            BufferedImage originalImage = ImageIO.read(file);
            BufferedImage watermarkImage = ImageIO.read(watermarkFile);

            int watermarkImageWidth = Math.round(originalImage.getWidth() * wmScale);
            BufferedImage watermarkImageScaled = Scalr.resize(watermarkImage, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.AUTOMATIC, watermarkImageWidth, Scalr.OP_ANTIALIAS);
            Watermark watermark = new Watermark(positions, watermarkImageScaled, wmTransparency);
            BufferedImage watermarkedImage = watermark.apply(originalImage);

            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                String ext = getValidExt(file);
                ImageIO.write(watermarkedImage, ext, baos);
                return baos.toByteArray();
            }
        } catch (Exception ex) {
            logger.error("Error in putWatermark(), filePath = {}", filePath, ex);
        }
        return null;
    }

    private String getValidExt(File file) {
        String ext = ImageUtils.getExtensionFromFile(file);
        if (ext == null) {
            ext = filePath.substring(filePath.lastIndexOf("."));
        }
        return ext;
    }
}
