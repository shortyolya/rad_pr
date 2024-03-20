package com.baltinfo.radius.documents.image;

import org.imgscalr.Scalr;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author Suvorina Aleksandra
 * @since 08.05.2020
 */
public class ImageScale {

    public static BufferedImage scaleImage(BufferedImage image, int targetSize) throws IOException {
        BufferedImage resizedImage = null;
        if (image.getColorModel().hasAlpha()) {
            image = dropAlphaChannel(image);
        }
        resizedImage = Scalr.resize(image, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.AUTOMATIC, targetSize, Scalr.OP_ANTIALIAS);

        return resizedImage;
    }

    private static BufferedImage dropAlphaChannel(BufferedImage src) {
        BufferedImage convertedImg = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);
        convertedImg.getGraphics().drawImage(src, 0, 0, null);

        return convertedImg;
    }
}
