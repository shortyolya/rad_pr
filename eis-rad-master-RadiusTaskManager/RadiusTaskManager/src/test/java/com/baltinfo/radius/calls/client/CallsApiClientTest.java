package com.baltinfo.radius.calls.client;

import com.baltinfo.radius.application.configuration.UnirestConfigurationTest;
import com.baltinfo.radius.calls.model.CallServiceResponse;
import com.baltinfo.radius.utils.Result;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.*;

/**
 * @author Igor Lapenok
 * @since 01.09.2023
 */
public class CallsApiClientTest {

    private final String BASE_URL = "https://rad-reports-backend-develop-test.228.by/api/v1";
    private final String API_KEY = "ryKiFkAyqc42yatMyUklGFnBKGLEI5";

    @Before
    public void beforeAll() {
        new UnirestConfigurationTest();
    }

    @Ignore
    @Test
    public void shouldTrue_whenGetCallCount() {
        CallsApiClient callsApiClient = new CallsApiClient(BASE_URL, API_KEY);
        Result<CallServiceResponse, String> result = callsApiClient.getCallCount("334244");
        assertTrue(result.isSuccess());
        Result<CallServiceResponse, String> result2 = callsApiClient.getCallCount("РАД-334244");
        assertTrue(result2.isSuccess());
        assertTrue(Objects.equals(result.getResult().getCount(), result2.getResult().getCount()));
        assertTrue(Objects.equals(result.getResult().getUniqueCount(), result2.getResult().getUniqueCount()));
    }
}