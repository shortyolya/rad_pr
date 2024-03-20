package com.baltinfo.radius.db.constants;

public enum AsvCode {

    CAR_LOANS(21L),
    MORTGAGE_LOANS(22L),
    OTHER_LOANS(23L),
    OTHER_CLAIM(24L),
    LEGAL_CLAIM(25L),
    INDIVIDUAL_CLAIM(26L),
    OVERDRAFTS(35L);
    private final Long code;

    AsvCode(Long code) {
        this.code = code;
    }

    public Long getCode() {
        return code;
    }

    public static boolean isNfa(Long c) {
        return CAR_LOANS.code.equals(c)
                || MORTGAGE_LOANS.code.equals(c)
                || OTHER_LOANS.code.equals(c)
                || OTHER_CLAIM.code.equals(c)
                || LEGAL_CLAIM.code.equals(c)
                || INDIVIDUAL_CLAIM.code.equals(c)
                || OVERDRAFTS.code.equals(c);
    }
}
