package com.baltinfo.radius.mailsender;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MessagePackResult {

    @JsonProperty("chunk")
    private Integer chunk;

    @JsonProperty("cabinet")
    private Integer cabinet;

    @JsonProperty("disterb")
    private Integer disterb;

    @JsonProperty("email")
    private Integer email;

    @JsonProperty("message")
    private String message;

    public MessagePackResult() {
    }

    public Integer getChunk() {
        return chunk;
    }

    public void setChunk(Integer chunk) {
        this.chunk = chunk;
    }

    public Integer getCabinet() {
        return cabinet;
    }

    public void setCabinet(Integer cabinet) {
        this.cabinet = cabinet;
    }

    public Integer getDisterb() {
        return disterb;
    }

    public void setDisterb(Integer disterb) {
        this.disterb = disterb;
    }

    public Integer getEmail() {
        return email;
    }

    public void setEmail(Integer email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MessagePackResult{" +
                "chunk=" + chunk +
                ", cabinet=" + cabinet +
                ", disterb=" + disterb +
                ", email=" + email +
                ", message='" + message + '\'' +
                '}';
    }
}
