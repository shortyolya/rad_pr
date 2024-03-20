package com.baltinfo.radius.loadauction.model.result;

public enum AsvParticipantType {
    SECOND(1),
    WINNER(2),
    SINGLE(3),
    PARTICIPANT(4);

    private final Integer code;

    AsvParticipantType(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
