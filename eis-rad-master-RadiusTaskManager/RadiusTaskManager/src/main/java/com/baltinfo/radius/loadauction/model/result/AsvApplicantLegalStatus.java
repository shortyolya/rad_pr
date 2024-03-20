package com.baltinfo.radius.loadauction.model.result;

import com.baltinfo.radius.db.constants.TypeSubject;

import java.util.Arrays;

public enum AsvApplicantLegalStatus {
    FL(1, TypeSubject.FL.getUnid()),
    IP(2, TypeSubject.IP.getUnid()),
    YL(3, TypeSubject.YL.getUnid());

    private final Integer asvCode;
    private final Long eisCode;

    AsvApplicantLegalStatus(Integer asvCode, Long eisCode) {
        this.asvCode = asvCode;
        this.eisCode = eisCode;
    }

    public Integer getAsvCode() {
        return asvCode;
    }

    public static AsvApplicantLegalStatus getByEisCode(Long eisCode) {
        return Arrays.stream(values())
                .filter(c -> c.eisCode.equals(eisCode))
                .findFirst()
                .orElse(null);
    }
}
