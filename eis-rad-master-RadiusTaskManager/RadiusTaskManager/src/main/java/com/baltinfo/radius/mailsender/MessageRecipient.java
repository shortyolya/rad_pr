package com.baltinfo.radius.mailsender;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)

public class MessageRecipient {
    @JsonProperty("o")
    Long o;
    @JsonProperty("m")
    Long m;
    @JsonProperty("lp")
    Long lp;
    @JsonProperty("lu")
    Long lu;
    @JsonProperty("bp")
    Long bp;
    @JsonProperty("bu")
    Long bu;
    @JsonProperty("e")
    String e;

    public Long getO() {
        return o;
    }

    public void setO(Long o) {
        this.o = o;
    }

    public Long getM() {
        return m;
    }

    public void setM(Long m) {
        this.m = m;
    }

    public Long getLp() {
        return lp;
    }

    public void setLp(Long lp) {
        this.lp = lp;
    }

    public Long getLu() {
        return lu;
    }

    public void setLu(Long lu) {
        this.lu = lu;
    }

    public Long getBp() {
        return bp;
    }

    public void setBp(Long bp) {
        this.bp = bp;
    }

    public Long getBu() {
        return bu;
    }

    public void setBu(Long bu) {
        this.bu = bu;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }
}
