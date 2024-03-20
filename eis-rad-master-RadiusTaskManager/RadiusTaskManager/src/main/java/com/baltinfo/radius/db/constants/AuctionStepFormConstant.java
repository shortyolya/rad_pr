package com.baltinfo.radius.db.constants;

/**
 * <p>
 * Форма подачи предложений
 * </p>
 *
 * @author Lapenok Igor
 * @since 16.08.2018
 */
public enum AuctionStepFormConstant {
    /**
     * Открытая
     */
    OPEN(0),
    /**
     * Закрытая
     */
    CLOSE(1);

    private final Integer id;

    AuctionStepFormConstant(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
