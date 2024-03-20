package com.baltinfo.radius.db.constants;

public enum ClAsvCodeConstant {
    CAR_LOAN(21L),
    MORTGAGE_LOANS(22L),
    OVERDRAFT(35L),
    RIGHT_CLAIM_INDIVIDUAL(26L),
    RIGHT_CLAIM_LEGAL(25L),
    OTHER_LOANS(23L),
    RIGHT_CLAIM_OTHER(24L);

    private final Long code;

    ClAsvCodeConstant(Long code) {
        this.code = code;
    }

    public Long getCode() {
        return code;
    }

}
