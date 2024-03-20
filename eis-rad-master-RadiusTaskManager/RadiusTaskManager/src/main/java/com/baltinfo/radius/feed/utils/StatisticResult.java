package com.baltinfo.radius.feed.utils;



public class StatisticResult<R, E> {

    private final R result;
    private final E error;
    private final boolean success;

    protected StatisticResult(R result, E error, boolean success) {
            this.result = result;
            this.error = error;
            this.success = success;
    }

    public static <R, E> StatisticResult<R, E> ok(R result) {
        return new StatisticResult(result, null, true);
    }

    public static <R, E> StatisticResult<R, E> ok(R result, E error) {
        return new StatisticResult(result, error, true);
    }

    public static <R, E> StatisticResult<R, E> error(E error) {
        return new StatisticResult(null, error, false);
    }

    public boolean isSuccess() {
        return this.success;
    }

    public boolean isError() {
        return !this.success;
    }

    public R getResult() {
        return this.result;
    }

    public E getError() {
        return this.error;
    }
}
