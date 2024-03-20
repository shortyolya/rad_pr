package com.baltinfo.radius.db.constants;

import java.io.Serializable;

/**
 * @author sas
 */
public class SaleGuides implements Serializable {

    public static final Long DEBITOR_PROPERTY = 3L; // Продажа имущества банкротов
    public static final Long PRIVATIZATION = 4L; // Приватизация государственного имущества
    public static final Long PRIVATE_PROPERTY = 5L; // Продажа имущества частных собственников
    public static final Long BANK_ASSETS = 7L;  // Продажа активов банков
    public static final Long LEASING_BANK_ASSETS = 22L; // Аренда активов банков
    public static final Long MORTGAGE_PROPERTY = 1L;    // Продажа залогового имущества
    public static final Long DISTRESSED_PROPERTY = 15L; // Продажа проблемного имущества
    public static final Long STATE_PROPERTY_MOSCOW = 8L; // Госимущество из казны Московской области
    public static final Long STATE_PROPERTY_MUNICIPAL = 13L; // Продажа муниципального имущества

}
