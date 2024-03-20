package com.baltinfo.radius.feed.cian.api.client;

import com.baltinfo.radius.application.configuration.UnirestConfigurationTest;
import com.baltinfo.radius.feed.cian.api.model.GetOrderResponse;
import com.baltinfo.radius.utils.Result;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author Igor Lapenok
 * @since 10.02.2021
 */
public class CianApiClientTest {

    @Before
    public void beforeAll() {
        new UnirestConfigurationTest();
    }

    @Test
    @Ignore
    public void shouldNotEmptyList_whenGetOrder() {
        CianApiClient client = new CianApiClient("https://public-api.cian.ru/v1",
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjQ1MzA1ODQ3fQ.p5NcwO-oORlnR5_qOYGi-p8Gxr0jOGfY9uMdLDZhNF0");

        Result<GetOrderResponse, String> result = client.getOrder();
        assertTrue(result.isSuccess());
        System.out.println(result.getResult());
        assertTrue(!result.getResult().getOperationId().isEmpty());
        assertTrue(result.getResult().getResult().getOffers() != null);
    }

    @Test
    @Ignore
    public void shouldResultError_whenInvalidAuth() {
        CianApiClient client = new CianApiClient("https://public-api.cian.ru/v1",
                "123");

        Result<GetOrderResponse, String> result = client.getOrder();
        assertTrue(result.isError());
    }
}
