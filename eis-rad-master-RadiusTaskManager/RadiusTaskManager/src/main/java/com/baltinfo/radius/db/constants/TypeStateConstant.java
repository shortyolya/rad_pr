package com.baltinfo.radius.db.constants;

/**
 * <p>
 * </p>
 *
 * @author Lapenok Igor
 * @since 24.08.2018
 */
public enum TypeStateConstant {
    /**
     * В работе
     */
    IN_WORK(2L),
    /**
     * Продан
     */
    OBJ_SALED(3L),
    /**
     * Архив
     */
    ARCHIVE(4L),
    /**
     * Торги не проводились
     */
    NOT_TAKE_PLACE(17L),
    /**
     * Подана
     */
    APP_CREATED(6L),
    /**
     * Допущена
     */
    APP_ALLOWED(21L),
    /**
     * Отклонена
     */
    APP_REJECTED(22L),
    /**
     * Отозвана
     */
    APP_RECALLED(18L),
    /**
     * Торги по лоту не состоялись
     */
    LOT_NOT_DONE(16L),
    /**
     * Торги по лоту проведены
     */
    LOT_DONE(25L),
    /**
     * Торги по лоту отменены
     */
    LOT_CANCEL(24L),
    /**
     * Торги проведены
     */
    TRADE_DONE(13L),
    /**
     * Назначены торги
     */
    TRADE_ASSIGNED(5L),
    /**
     * Проводятся торги
     */
    TRADE_IN_PROCESS(12L),
    /**
     * Торги отменены
     */
    TRADE_CANCELED(14L),
    /**
     * Торги уже проведены
     */
    LOT_ALREADY_DONE(30L),

    /**
     * Подготовка договора
     */
    DEAL_PREPARATION(7L),
    /**
     * В стадии подготовки
     */
    ACT_FORMED(33L),
    /**
     * Выставлен в 1С
     */
    DISPLAY_IN_1C(36L),
    /**
     * Торги приостановлены
     */
    SUSPENDED(55L),
    /**
     * Торги по лоту приостановлены
     */
    LOT_SUSPENDED(56L),

    /**
     * Продан, ждем ДКП
     */
    SOLD_WAITING_FOR_CONTRACT(31L),

    /**
     * Отказ от ДКП
     */
    REFUSAL_OF_DKP(32L),

    /**
     * Подготовка к торгам
     */
    PREPARE_FOR_AUCTION(11L);

    private final Long id;

    TypeStateConstant(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public static boolean isAuctionFinishedState(Long id) {
        return TRADE_DONE.id.equals(id) || TRADE_CANCELED.id.equals(id);
    }

    public static boolean isLotFinishedState(Long id) {
        return LOT_DONE.id.equals(id)
                || LOT_NOT_DONE.id.equals(id)
                || LOT_CANCEL.id.equals(id);
    }
}
