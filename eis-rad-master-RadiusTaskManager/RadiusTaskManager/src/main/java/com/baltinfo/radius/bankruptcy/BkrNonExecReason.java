package com.baltinfo.radius.bankruptcy;

/**
 * @author ssv
 * @since 17.06.2019
 */
public enum BkrNonExecReason {

    ONE_APPLICATION(1);          // Подана одна заявка

    private final long unid;

    BkrNonExecReason(long unid) {
        this.unid = unid;
    }

    public long getUnid() {
        return unid;
    }
}
