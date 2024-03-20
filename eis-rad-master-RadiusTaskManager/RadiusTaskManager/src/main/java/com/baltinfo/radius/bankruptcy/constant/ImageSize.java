package com.baltinfo.radius.bankruptcy.constant;

/**
 * <p>
 * </p>
 *
 * @author ssv
 * @since 21.08.2021
 */
public enum ImageSize {

    PICT_SIZE(146, 96),
    PICT_BIG_SIZE(246, 163),
    THUMBNAIL_SIZE(50, 50);

    private int width;
    private int height;

    ImageSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
