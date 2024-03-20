package com.baltinfo.radius.exchange;

import com.baltinfo.radius.utils.Result;

/**
 * @author Suvorina Aleksandra
 * @since 22.08.2019
 */
public interface ExchangeParamsBuilder<T extends ExchangeEntityParams> {

    Result<T, String> buildParams(Long lotUnid) throws Exception;
}
