package com.baltinfo.radius.db.constants;

/**
 *
 * @author svn
 */
public enum TypeAccountOperations {

    ADMISSION_TO_ACCOONT(1L), //Поступление на лицевой счет
    DEBIT(2L), //Списание
    RETURN(3L), //Возврат
    TRANSFER_TO_ORG(4L), //Перечисление организатору
    REALIZATION(5L); //Реализация

    private final Long taoUnid;

    TypeAccountOperations(Long taoUnid) {
        this.taoUnid = taoUnid;
    }

    public Long getTaoUnid() {
        return taoUnid;
    }
}
