package com.baltinfo.radius.mailsender;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class MessagePack {
    @JsonProperty("isEmail")
    private Boolean isEmail = false;
    @JsonProperty("isCabinet")
    private Boolean isCabinet = false;
    @JsonProperty("isDisterb")
    private Boolean isDisterb = false;
    @JsonProperty("chunk")
    private Integer chunk;
    @JsonProperty("subject")
//    @NotBlank(message = "Тема сообщения обязательна!")
    private String subject;
    //    @NotBlank(message = "Текст сообщения обязателен!")
    @JsonProperty("text")
    private String text;
    @JsonProperty("template")
    private String template;
    @JsonProperty("severity")
    private String severity;

    @JsonProperty("from")
    private MessageRecipient from;

    @JsonProperty("to")
    private List<MessageRecipient> to;

   //Идентификаторы документов на загруженных на витрину (rad_documents)
    @JsonProperty("documents")
    private List<Long> documents;


    public MessagePack() {
    }

    public Boolean getEmail() {
        return isEmail;
    }

    public void setEmail(Boolean email) {
        isEmail = email;
    }

    public Boolean getCabinet() {
        return isCabinet;
    }

    public void setCabinet(Boolean cabinet) {
        isCabinet = cabinet;
    }

    public Boolean getDisterb() {
        return isDisterb;
    }

    public void setDisterb(Boolean disterb) {
        isDisterb = disterb;
    }

    public Integer getChunk() {
        return chunk;
    }

    public void setChunk(Integer chunk) {
        this.chunk = chunk;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public MessageRecipient getFrom() {
        return from;
    }

    public void setFrom(MessageRecipient from) {
        this.from = from;
    }

    public List<MessageRecipient> getTo() {
        return to;
    }

    public void setTo(List<MessageRecipient> to) {
        this.to = to;
    }

    public List<Long> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Long> documents) {
        this.documents = documents;
    }

    @Override
    public String toString() {
        return "MessagePack{" +
                "isEmail=" + isEmail +
                ", isCabinet=" + isCabinet +
                ", isDisterb=" + isDisterb +
                ", chunk=" + chunk +
                ", subject='" + subject + '\'' +
                ", text='" + text + '\'' +
                ", template='" + template + '\'' +
                ", severity='" + severity + '\'' +
                ", from=" + from +
                ", to=" + to +
                ", documents=" + documents +
                '}';
    }
}

