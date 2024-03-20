package com.baltinfo.radius.notification;

import java.util.Arrays;

/**
 * <p>
 * </p>
 *
 * @author ssv
 * @since 23.10.2020
 */
public enum NotificationType {

    ASV(new Long[]{1L, 2L, 3L}),
    TRUST(new Long[]{5L, 6L, 7L, 8L});

    private final Long[] loadSourceUnids;

    NotificationType(Long[] loadSourceUnids) {
        this.loadSourceUnids = loadSourceUnids;
    }

    public static NotificationType getByLsUnid(Long lsUnid) {
        return Arrays.stream(values())
                .filter(i -> Arrays.stream(i.loadSourceUnids).anyMatch(s -> s.equals(lsUnid)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("NotificationType not found by lsUnid = " + lsUnid));
    }
}