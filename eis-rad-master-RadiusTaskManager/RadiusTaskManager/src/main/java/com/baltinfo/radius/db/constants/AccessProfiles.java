package com.baltinfo.radius.db.constants;

public enum AccessProfiles {

    RESULT_MESSAGE_ACCOUNTANT(84L),
    AUCTION_PUBLISH_MESSAGE_MANAGER(85L),
    LOT_PUBLISHED_MESSAGE(86L); //Уведомление "Перечень опубликованных лотов"

    private final Long apUnid;

    AccessProfiles(Long apUnid) {
        this.apUnid = apUnid;
    }

    public Long getApUnid() {
        return apUnid;
    }
}
