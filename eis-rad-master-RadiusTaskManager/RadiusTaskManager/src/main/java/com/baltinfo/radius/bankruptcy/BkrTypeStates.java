package com.baltinfo.radius.bankruptcy;

/**
 * @author Suvorina Aleksandra
 * @since 24.08.2018
 */
public enum BkrTypeStates {

    AUCTION_TYPE_STATE_DRAFT(663),          // Черновик
    DO_TYPE_STATE_AUCTION_CREATED(21),      // Объявлены торги
    AUCTION_TYPE_STATE_DONE(25),            // Торги проведены
    AUCTION_RECEIVING_APPLICATIONS(23),     // Идет прием заявок
    DO_TYPE_STATE_NOT_DONE(43),             // Торги по лоту не состоялись
    DO_TYPE_STATE_REPEATED(62),             // Назначены повторные торги
    DO_TYPE_STATE_DONE(44),                 // Торги по лоту проведены
    DO_TYPE_STATE_CANCELED(637),            // Торги отменены
    DO_TYPE_STATE_ANNULED(773),             // Торги аннулированы
    DO_TYPE_STATE_SUSPENDED(638),           // Торги по лоту приостановлены
    AUCTION_TYPE_STATE_AUCTION_CREATED(3);  // Объявлены

    private final long unid;

    BkrTypeStates(long unid) {
        this.unid = unid;
    }

    public long getUnid() {
        return unid;
    }
}
