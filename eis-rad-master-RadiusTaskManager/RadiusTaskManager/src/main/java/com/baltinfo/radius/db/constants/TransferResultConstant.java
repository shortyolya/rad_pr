package com.baltinfo.radius.db.constants;

/**
 * <p>
 * </p>
 *
 * @author Lapenok Igor
 * @since 24.08.2018
 */
public enum TransferResultConstant {
    NOT_TRANSFERED(0),
    TRANSFERED(1),
    ERROR(2),
    DONT_TRANSFER(3);

    private final Integer id;

    TransferResultConstant(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
