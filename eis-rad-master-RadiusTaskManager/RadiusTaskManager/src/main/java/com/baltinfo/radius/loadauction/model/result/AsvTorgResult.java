package com.baltinfo.radius.loadauction.model.result;

public enum AsvTorgResult {

    NOT_DONE(0),
    DONE(1),
    CANCELED(2);

    private final Integer asvCode;

    AsvTorgResult(Integer asvCode) {
        this.asvCode = asvCode;
    }

    public Integer getAsvCode() {
        return asvCode;
    }
}
