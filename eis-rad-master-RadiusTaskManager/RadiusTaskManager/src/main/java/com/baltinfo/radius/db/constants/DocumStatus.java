package com.baltinfo.radius.db.constants;

public enum DocumStatus {
    ON_FORMATION(1L),
    FORMING(2L),
    FORMATION_COMPLETED(3L),
    FORMATION_ERROR(4L);

    private final Long statuseId;

    DocumStatus(Long dgtUnid) {
        this.statuseId = dgtUnid;
    }

    public Long getStatuseId() {
        return statuseId;
    }
}
