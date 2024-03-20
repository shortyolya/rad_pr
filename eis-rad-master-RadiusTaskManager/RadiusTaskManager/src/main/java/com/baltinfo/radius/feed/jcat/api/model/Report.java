package com.baltinfo.radius.feed.jcat.api.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.Date;

/**
 * @author Suvorina Aleksandra
 * @since 30.03.2021
 */
@JacksonXmlRootElement
public class Report {

    @JacksonXmlProperty(localName = "feed-change-date")
    private final Date feedChangeDate;
    @JacksonXmlProperty(localName = "last-parse-date")
    private final Date lastParseDate;
    @JacksonXmlProperty(localName = "added")
    private final Items added;
    @JacksonXmlProperty(localName = "missing")
    private final Items missing;

    public Report(@JacksonXmlProperty(localName = "feed-change-date") Date feedChangeDate,
                  @JacksonXmlProperty(localName = "last-parse-date") Date lastParseDate,
                  @JacksonXmlProperty(localName = "added") Items added,
                  @JacksonXmlProperty(localName = "missing") Items missing) {
        this.feedChangeDate = feedChangeDate;
        this.lastParseDate = lastParseDate;
        this.added = added;
        this.missing = missing;
    }

    public Date getFeedChangeDate() {
        return feedChangeDate;
    }

    public Date getLastParseDate() {
        return lastParseDate;
    }

    public Items getAdded() {
        return added;
    }

    public Items getMissing() {
        return missing;
    }
}
