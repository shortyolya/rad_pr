package com.baltinfo.radius.db.constants;


/**
 * @author Suvorina Aleksandra
 * @since 15.01.2021
 */
public enum BkrTypeAccOper {
    /**
     * Поступление
     */
    TAO_INCOME(1L, TypeAccountOperations.ADMISSION_TO_ACCOONT.getTaoUnid()),
    /**
     * Перечисление организатору
     */
    TAO_DEBIT(2L, TypeAccountOperations.TRANSFER_TO_ORG.getTaoUnid()),
    /**
     * Возврат
     */
    TAO_RETURN(3L, TypeAccountOperations.RETURN.getTaoUnid()),
    /**
     * Перечисление Оператору
     */
    TAO_TRANSFER_TO_OPERATOR(9L, TypeAccountOperations.REALIZATION.getTaoUnid());

    private final Long bkrUnid;
    private final Long eisUnid;

    BkrTypeAccOper(Long bkrUnid, Long eisUnid) {
        this.bkrUnid = bkrUnid;
        this.eisUnid = eisUnid;
    }

    public Long getBkrUnid() {
        return bkrUnid;
    }

    public Long getEisUnid() {
        return eisUnid;
    }
}

