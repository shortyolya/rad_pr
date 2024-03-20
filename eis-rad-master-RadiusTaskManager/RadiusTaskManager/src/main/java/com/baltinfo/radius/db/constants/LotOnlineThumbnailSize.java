package com.baltinfo.radius.db.constants;

/**
 * @author Suvorina Aleksandra
 * @since 19.03.2021
 */
public enum LotOnlineThumbnailSize {

    THUMBNAIL(200),
    SMALL_THUMBNAIL(70);

    private final int size;

    LotOnlineThumbnailSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
