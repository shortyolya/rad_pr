package com.baltinfo.radius.db.constants;

public enum TypeActConstant {
    OWNER(1L, "Собственник"),
    WINNER(2L, "Победитель"),
    SINGLE_PARTICIPANT(3L, "Единственный участник");

    private final long unid;
    private final String name;

    TypeActConstant(long unid, String name) {
        this.unid = unid;
        this.name = name;
    }

    public long getUnid() {
        return unid;
    }

    public String getName() {
        return name;
    }
}
