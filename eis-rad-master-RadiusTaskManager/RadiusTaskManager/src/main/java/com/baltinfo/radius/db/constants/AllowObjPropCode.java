package com.baltinfo.radius.db.constants;

public enum AllowObjPropCode {
    INN("INN"),
    DAYS_LATE_PAYMENT("days_late_payment"),
    AVAILABILITY_OF_DEBT_SECURITY("availability_of_debt_security"),
    COURTDECISION("Courtdecision"),
    AMOUNT_OF_DEBT("Amountofdebt");

    private final String code;

    AllowObjPropCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
