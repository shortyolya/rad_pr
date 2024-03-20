package com.baltinfo.radius.exchange;

/**
 * @author Suvorina Aleksandra
 * @since 25.12.2019
 */
public class ExchangeResult {
    private final boolean isLoaded;
    private final Long lotUnid;
    private final String error;

    private ExchangeResult(boolean isLoaded, Long lotUnid, String error) {
        this.isLoaded = isLoaded;
        this.lotUnid = lotUnid;
        this.error = error;
    }

    public static ExchangeResult loaded(Long lotUnid) {
        return new ExchangeResult(true, lotUnid, null);
    }

    public static ExchangeResult loadedWithErrors(Long lotUnid, String error) {
        return new ExchangeResult(true, lotUnid, error);
    }

    public static ExchangeResult notLoaded(String error) {
        return new ExchangeResult(false, null, error);
    }

    public String getError() {
        return error;
    }

    public boolean isLoaded() {
        return isLoaded;
    }

    public boolean hasError() {
        return error != null;
    }

    public Long getLotUnid() {
        return lotUnid;
    }
}
