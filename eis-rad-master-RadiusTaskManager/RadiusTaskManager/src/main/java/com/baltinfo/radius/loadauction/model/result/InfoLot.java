package com.baltinfo.radius.loadauction.model.result;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class InfoLot {

    @JsonProperty("idLot")
    private final Long idLot;

    @JsonCreator
    public InfoLot(@JsonProperty("idLot") Long idLot) {
        this.idLot = idLot;
    }

    public Long getIdLot() {
        return idLot;
    }
}
