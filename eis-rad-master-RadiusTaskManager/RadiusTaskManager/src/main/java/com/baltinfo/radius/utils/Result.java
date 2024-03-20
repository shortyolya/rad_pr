package com.baltinfo.radius.utils;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class Result<R, E> {

    private final R result;
    private final E error;
    private final boolean success;

    protected Result(R result, E error, boolean success) {
        if (result != null && error != null) {
            throw new IllegalArgumentException("can't create result with error and success");
        } else {
            this.result = result;
            this.error = error;
            this.success = success;
        }
    }

    public static <ResultT, ErrorT> Result<ResultT, ErrorT> ok(ResultT result) {
        return new Result(result, null, true);
    }

    public static <ResultT, ErrorT> Result<ResultT, ErrorT> ok() {
        return new Result(null, null, true);
    }

    public static <R, E> Result<R, E> error(E error) {
        return new Result(null, error, false);
    }

    public static <R> Result<R, Void> error() {
        return new Result(null, null, false);
    }

    public static <R> Result<R, Void> fromOptionalResult(Optional<R> result) {
        return result.isPresent() ? ok(result.get()) : error();
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

    public R getResultOrThrow() {
        return this.getResultOrThrow((e) -> new IllegalArgumentException("cannot get result. error=" + e));
    }

    public R getResultOrThrow(Function<E, RuntimeException> runtimeExceptionSupplier) {
        if (this.success) {
            return this.result;
        } else {
            throw runtimeExceptionSupplier.apply(this.error);
        }
    }

    public E getError() {
        return this.error;
    }

    public <NR, NE> Result<NR, NE> map(Function<R, NR> resultMap, Function<E, NE> errorMap) {
        return this.success ? ok(resultMap.apply(this.result)) : error(errorMap.apply(this.error));
    }

    public <NR> Result<NR, E> mapResult(Function<R, NR> resultMapFunction) {
        return this.map(resultMapFunction, Function.identity());
    }

    public <NE> Result<R, NE> mapError(Function<E, NE> errorMapFunction) {
        return this.map(Function.identity(), errorMapFunction);
    }

    public Result<R, E> ifSuccess(Consumer<R> onSuccess) {
        if (this.success) {
            onSuccess.accept(this.result);
        }
        return this;
    }

    public Result<R, E> ifError(Consumer<E> onFail) {
        if (this.isError()) {
            onFail.accept(this.error);
        }
        return this;
    }
}

