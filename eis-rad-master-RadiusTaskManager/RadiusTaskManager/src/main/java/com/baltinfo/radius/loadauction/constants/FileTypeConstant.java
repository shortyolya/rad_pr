package com.baltinfo.radius.loadauction.constants;

import java.util.Arrays;
import java.util.Optional;

/**
 * <p>
 * </p>
 *
 * @author Maxim Kuznetsov
 * @since 14.02.2020
 */
public enum FileTypeConstant {
    DOCUMENT((short) 1, "docs"),
    IMAGE((short) 2, "foto"),
    XML_FILE((short) 3, "xml");

    private final short id;
    private final String name;

    FileTypeConstant(short id, String name) {
        this.id = id;
        this.name = name;
    }

    public short getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static Optional<FileTypeConstant> getByName(String name) {
        return Arrays.stream(values()).filter(x -> x.name.toLowerCase().equals(name.toLowerCase())).findFirst();
    }
}
