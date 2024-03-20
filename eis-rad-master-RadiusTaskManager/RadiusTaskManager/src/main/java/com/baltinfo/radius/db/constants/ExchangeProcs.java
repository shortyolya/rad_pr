package com.baltinfo.radius.db.constants;

/**
 * Процедуры информационного обмена
 *
 * @author Suvorina Aleksandra
 * @since 23.08.2018
 */
public enum ExchangeProcs {
    SEND_AUCTION_TO_AUCTION_HOUSE(1L),                            // Передача торгов на auction-house.ru
    SEND_OBJ_TO_AUCTION_HOUSE(2L),
    SEND_AUCTION_TO_BANKRUPTCY(18L),
    SEND_AUCTION_TO_LOTONLINE(4L),
    SEND_OBJ_TO_PLEDGE(19L),
    RECEIVE_AUCTION_FROM_BANKRUPTCY(20L),
    RECEIVE_AUCTION_FROM_LOT_ONLINE(14L),
    SEND_AUCTION_TO_ASV(22L),
    RECEIVE_CODES_FROM_BANKRUPTCY(23L),
    RECEIVE_CODES_FROM_LOT_ONLINE(29L),
    LOAD_AVITO_AUTO_CLASSIFIER(30L),
    LOAD_AVITO_TRUCK_CLASSIFIER(32L),
    UPDATE_LOT_VIEW_COUNT(31L),
    UPDATE_DATES_FROM_BANKRUPTCY(33L),
    UPDATE_DATES_FROM_LOT_ONLINE(34L),
    SYNCHRONIZE_CATEGORIES(37L),
    AVITO_AD_STATISTIC(38L),
    CIAN_AD_STATISTIC(39L),
    JCAT_AD_STATISTIC(40L),
    EXPORT_PHOTO_TO_SFTP(41L),
    UPDATE_VITRINA_LINK(44L),
    CHECK_SUSPENDED_AUCTION(45L),
    UPDATE_STATISTIC_FROM_AUCTION_HOUSE(47L),
    UPDATE_STATISTIC_FROM_ETP(48L),
    UPDATE_STATISTIC_FROM_PLEDGE(49L),
    CHECK_RESUMED_AUCTION(46L),
    RECEIVE_EXPLANATION_RESPONSES(50L),
    PUBLISH_AUCION_ON_ETP(51L),
    UPDATE_ASSETS(52L),
    SEND_BLOCK_AUCTION_RESULTS_TO_ASV(54L),
    SEND_AUCTION_RESULTS_TO_ASV(55L),
    SEND_LOT_RESULTS_TO_ASV(56L),
    DELETE_ARCHIVE_FEED(59L),
    // Обновление номера торгов/лотов
    UPDATE_AUCTION_AND_LOT_NUMBER(61L),
    // Получение сведений о лицевых счетах Банкротство
    DEP_BKR_ACCOUNT_INFO(5L),
    // Получение информации о возврате денежных средств по заявлению пользователя Банкротство
    DEP_BKR_GET_RETURN_INFO(9L),
    // Передача информации о поступлении денежных средств на лицевой счет Банкротство
    DEP_BKR_PAYMENT_INFO(7L),
    // Передача подтверждения факта возврата денежных средств Банкротство
    DEP_BKR_SEND_RETURN_INFO(11L),
    // Получение сведений о перечислении средств Организатору торгов Банкротство
    DEP_BKR_GET_RETURN_TO_ORG_INFO(15L),
    // Получение сведений о перечислении средств Оператору Банкротство
    DEP_BKR_GET_TRANSFER_TO_OPERATOR_INFO(36L),
    // Получение сведений о лицевых счетах Lot-online
    DEP_LO_ACCOUNT_INFO(6L),
    // Получение информации о возврате денежных средств по заявлению пользователя Lot-online
    DEP_LO_GET_RETURN_INFO(10L),
    // Передача информации о поступлении денежных средств на лицевой счет Lot-online
    DEP_LO_PAYMENT_INFO(8L),
    // Получение сведений о перечислении средств Организатору торгов Lot-online
    DEP_LO_GET_RETURN_TO_ORG_INFO(16L),
    // Получение сведений о реализации Lot-online
    DEP_LO_GET_REALIZATION_INFO(17L),
    // Обновления количества звонков для лотов Траста
    UPDATE_CALLS_COUNT_FOR_TRUST_LOTS(62L),
    SEND_AUCTION_TO_RAD_HOLDING(63L),
    SYNCHRONIZE_LINKS_AUCTION_HOUSE(67L),
    SYNCHRONIZE_LINKS_RAD_HOLDING(68L);
    private final long unid;

    ExchangeProcs(long unid) {
        this.unid = unid;
    }

    public long getUnid() {
        return unid;
    }
}
