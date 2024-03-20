package com.baltinfo.radius.db.constants;

public enum ParticipantAgentConstant {

    SYSTEM(0L);

    private Long paUnid;

    ParticipantAgentConstant(Long paUnid) {
        this.paUnid = paUnid;
    }

    public Long getPaUnid() {
        return paUnid;
    }
}
