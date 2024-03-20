package com.baltinfo.radius.db.constants;

public enum SaleCategoryCodeConstant {
    RIGHT_CLAIM_PERSON("301001"), //Права требования к физ. Лицам
    RIGHT_CLAIM_LEGAL_LOAN("301003"), //Права требования к юр. лицам (займы)
    RIGHT_CLAIM_LEGAL("301004"), //Права требования к юр. лицам (дебиторская задолженность)
    RIGHT_CLAIM_INDIVIDUAL("301002"); //Права требования к ИП

    private final String code;

    SaleCategoryCodeConstant(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}

