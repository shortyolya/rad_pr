package com.baltinfo.radius.db.constants;

public enum NonExecReasons {
    ONE_APPLICATION(1L),
    NO_APPLICATIONS(2L);

    private final Long unid;

    NonExecReasons(Long unid) {
        this.unid = unid;
    }

    public Long getUnid() {
        return unid;
    }
}
