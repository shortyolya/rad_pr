package com.baltinfo.radius.notification.paydoc;

/**
 * @author aaa
 */
public enum PayDocNotificationStatus {


    NOT_SENT(0),
    SENT(1),
    ERROR_SENDING(2);

    private int id;

    PayDocNotificationStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
